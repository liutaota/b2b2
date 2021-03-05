package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esotericsoftware.minlog.Log;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.pay.service.PayOrderService;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.common.IncaUtils;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.RefundDetailVo;
import com.zsyc.zt.b2b.vo.RefundReturnVo;
import com.zsyc.zt.inca.service.OrderBackService;
import com.zsyc.zt.inca.vo.OrderBackInfoDocVo;
import com.zsyc.zt.inca.vo.OrderBackInfoDtlVo;
import com.zsyc.zt.inca.vo.OrderResultDocVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RefundReturnServiceImpl implements RefundReturnService {
    @Autowired
    private RefundReturnMapper refundReturnMapper;

    @Autowired
    private RefundDetailMapper refundDetailMapper;

    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private OrderBackService orderBackService;

    @Autowired
    private OrderLogMapper orderLogMapper;
    @Autowired
    private RecDocMapper recDocMapper;

    @Autowired
    private RecDtlMapper recDtlMapper;
    @Autowired(required = false)
    private PayOrderService payOrderService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public IPage<RefundReturnVo> getRefundReturnList(Page page, RefundReturnVo refundReturnVo) {
        return this.refundReturnMapper.getRefundReturnList(page, refundReturnVo);
    }

    @Override
    public List<RefundDetailVo> getRefundDetailList(Long id) {
        AssertExt.hasId(id, "退货单ID无效");
        return this.refundDetailMapper.getRefundDetailList(id);
    }

    @Override
    public void checkRefundReturn(Long id, String userMessage,String refundState) {
        AssertExt.hasId(id, "退货单ID为空");
        AssertExt.notBlank(refundState, "审核状态为空");
        RefundReturn refundReturnDB = this.refundReturnMapper.selectById(id);
        AssertExt.notNull(refundReturnDB, "退货单不存在");
        AssertExt.notBlank(userMessage, "审核意见为空");
        refundReturnDB.setUserMessage(userMessage);
        refundReturnDB.setRefundState(refundState);
        this.refundReturnMapper.updateById(refundReturnDB);
        this.addRecOrder(refundReturnDB);
        /*OrderInfo orderInfo = this.orderInfoMapper.selectById(refundReturnDB.getOrderId());
        if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.WX_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ZFB_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ABC_LINE.val())) {
            String num = RandomStringUtils.random(3, false, true);
            this.payOrderService.refund(orderInfo.getPayOrderNo(),num, 1);
            orderInfo.setOrderState(OrderInfo.EOrderState.IN_REFUND.val());
            this.orderInfoMapper.updateById(orderInfo);
        }*/
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("订单退款审核通过");
        orderLog.setOrderId(refundReturnDB.getOrderId());
        orderLog.setOrderStatus(refundReturnDB.getRefundState());
        this.orderLogMapper.insert(orderLog);
    }

    //退货审核通过
    private void addRecOrder(RefundReturn refundReturn) {
        List<RefundDetail> refundDetailList = this.refundDetailMapper.selectList(new QueryWrapper<RefundDetail>().eq("REFUND_ID", refundReturn.getId()));
        RecDoc recDocDB = this.recDocMapper.selectOne(new QueryWrapper<RecDoc>().eq("REFUND_ID",refundReturn.getId()));
        if (null != recDocDB) return;
        RecDoc recDoc = new RecDoc();
        recDoc.setEntryId(1L);
        recDoc.setMemberId(refundReturn.getMemberId());
        recDoc.setOrderId(refundReturn.getOrderId());
        recDoc.setStatus(RecDoc.EStatus.TO_PAY.val());
        recDoc.setRecMethod(refundReturn.getApplyType());
        recDoc.setRefundId(refundReturn.getId());
        OrderInfo orderInfo = this.orderInfoMapper.selectById(refundReturn.getOrderId());
        recDoc.setRecType(orderInfo.getPayMethod());
        recDoc.setDtlLines(refundDetailList.size());
        double amountTotal = (IncaUtils.toErpPriceDouble(refundReturn.getRefundAmount()));
        recDoc.setTotal(-amountTotal);
        recDoc.setPremoney(-amountTotal);
        recDoc.setRemark(refundReturn.getMemberMessage());
        recDoc.setCreateTime(LocalDateTime.now());
        recDoc.setRefundState(RecDoc.ERefundState.NO_REFUND.val());
        this.recDocMapper.insert(recDoc);
        for (RefundDetail refundDetailVo : refundDetailList) {
            RecDtl recDtl = new RecDtl();
            recDtl.setGoodsId(refundDetailVo.getGoodsId());
            recDtl.setGoodsQty(refundDetailVo.getGoodsNum());
            recDtl.setUntPrice(refundDetailVo.getGoodsPayPrice());
            Long total = (long) IncaUtils.toErpPriceDouble4(refundDetailVo.getGoodsNum() * refundDetailVo.getGoodsPayPrice());
            recDtl.setTotalLine(total);
            recDtl.setSaRecId(recDoc.getId());
            recDtl.setCreateTime(LocalDateTime.now());
            this.recDtlMapper.insert(recDtl);
        }


    }

    @Override
    public void updateRefundReturnReturnType(Long id, String returnState) {
        AssertExt.hasId(id, "退货单ID无效");
        RefundReturn refundReturnDB = this.refundReturnMapper.selectById(id);
        AssertExt.notNull(refundReturnDB, "退货单不存在");
        refundReturnDB.setRefundState(returnState);
        this.refundReturnMapper.updateById(refundReturnDB);
    }

    @Override
    public void dealWithApplyRefundOrder() {
        List<RefundReturn> refundReturnList = this.refundReturnMapper.selectList(new QueryWrapper<RefundReturn>().eq("REFUND_STATE", RefundReturn.ERefundState.DEAL_WITH.val()).orderByAsc("USER_TIME"));

        if (null == refundReturnList || refundReturnList.size() == 0) return;

        RefundReturn refundReturnDB = refundReturnList.get(0);
        //审核通过，对接erp
        String num = RandomStringUtils.random(4, false, true);
        OrderBackInfoDocVo orderBackInfoDocVo = new OrderBackInfoDocVo();
        orderBackInfoDocVo.setOrderNo(refundReturnDB.getApplyNo());
        orderBackInfoDocVo.setOrderId(refundReturnDB.getId());
        orderBackInfoDocVo.setSrcOrderId(refundReturnDB.getOrderId());
        orderBackInfoDocVo.setSrcOrderNo(refundReturnDB.getOrderNo());
        //TODO 当前登陆人对应erp的账号
        orderBackInfoDocVo.setApproveManId(1047L);
        orderBackInfoDocVo.setStorerId(Constant.STORER_ID);
        orderBackInfoDocVo.setEntryId(1);
        orderBackInfoDocVo.setCreateDate(LocalDateTime.now());
        orderBackInfoDocVo.setAmountDiscount(IncaUtils.toErpPriceDouble4(refundReturnDB.getRefundAmount()));
        orderBackInfoDocVo.setAmountPay(IncaUtils.toErpPriceDouble4(refundReturnDB.getRefundAmount()));
        orderBackInfoDocVo.setAmountTotal(IncaUtils.toErpPriceDouble4(refundReturnDB.getRefundAmount()));
        orderBackInfoDocVo.setCustomId(refundReturnDB.getErpCustomerId());
        orderBackInfoDocVo.setRemark(refundReturnDB.getMemberMessage());
        orderBackInfoDocVo.setVersion("v1." + num);


        List<RefundDetailVo> refundDetailVoList = this.refundDetailMapper.getRefundDetailList(refundReturnDB.getId());
        RefundReturn finalRefundReturnDB = refundReturnDB;
        List<OrderBackInfoDtlVo> orderBackInfoDtlVoList = refundDetailVoList.stream().map(refundDetailVo -> {
            OrderBackInfoDtlVo orderBackInfoDtlVo = new OrderBackInfoDtlVo();
            orderBackInfoDtlVo.setGoodsDtlId(refundDetailVo.getGoodsDtlId());
            orderBackInfoDtlVo.setBatchId(refundDetailVo.getBatchId());
            orderBackInfoDtlVo.setGoodsId(refundDetailVo.getGoodsId());
            orderBackInfoDtlVo.setPriceDiscount(IncaUtils.toErpPriceDouble4(refundDetailVo.getGoodsPayPrice()));
            orderBackInfoDtlVo.setAmount(IncaUtils.toErpPriceDouble4(refundDetailVo.getRefundAmount()));
            orderBackInfoDtlVo.setGoodsSource(refundDetailVo.getGoodsSource().intValue());
            orderBackInfoDtlVo.setLotId(refundDetailVo.getLotId());
            orderBackInfoDtlVo.setLotNo(refundDetailVo.getLotNo());
            orderBackInfoDtlVo.setNum(refundDetailVo.getGoodsNum());
            orderBackInfoDtlVo.setPrice(IncaUtils.toErpPriceDouble4(refundDetailVo.getGoodsPrice()));
            orderBackInfoDtlVo.setSrcErpOrderDtlId(refundDetailVo.getSrcErpOrderDtlId());
            orderBackInfoDtlVo.setSrcErpOrderId(refundDetailVo.getSrcErpOrderId());
            orderBackInfoDtlVo.setSrcOrderDtlId(refundDetailVo.getOrderGoodsId().toString());
            orderBackInfoDtlVo.setSrcOrderId(refundDetailVo.getOrderId().toString());
            orderBackInfoDtlVo.setStorageId(refundDetailVo.getErpStorageId());
            orderBackInfoDtlVo.setReason(refundDetailVo.getReasonId().intValue());
            orderBackInfoDtlVo.setOrderNo(finalRefundReturnDB.getApplyNo());
            orderBackInfoDtlVo.setSrcOrderNo(finalRefundReturnDB.getOrderNo());
            return orderBackInfoDtlVo;
        }).collect(Collectors.toList());

        orderBackInfoDocVo.setOrderInfoDtlList(orderBackInfoDtlVoList);
        try {
            OrderResultDocVo orderResultDocVo = this.orderBackService.genBackOrderV1(orderBackInfoDocVo);

            if (orderResultDocVo.getErrorCode() == 0) {
                Log.info("erp已通过--{}", orderResultDocVo.toString());

                refundReturnDB.setRefundState(RefundReturn.ERefundState.TO_SEND.val());
                if (refundReturnDB.getApplyType().equals(RefundReturn.EApplyType.REFUND.val())) {
                    refundReturnDB.setRefundState(RefundReturn.ERefundState.DONE.val());
                }

                this.refundReturnMapper.updateById(refundReturnDB);


                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("客户申请退款/退货通过");
                orderLog.setOrderId(refundReturnDB.getOrderId());
                orderLog.setOrderStatus(refundReturnDB.getRefundState());
                orderLog.setToErpNum("v1." + num);
                this.orderLogMapper.insert(orderLog);
            } else {
                //异常
                refundReturnDB.setRefundMessage(orderResultDocVo.getErrorMessage());
                refundReturnDB.setRefundState(RefundReturn.ERefundState.ORDER_EXP.val());
                this.refundReturnMapper.updateById(refundReturnDB);

                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("客户申请退款/退货异常：" + orderResultDocVo.getErrorMessage());
                orderLog.setOrderId(refundReturnDB.getOrderId());
                orderLog.setOrderStatus(refundReturnDB.getRefundState());
                orderLog.setToErpNum("v1." + num);
                this.orderLogMapper.insert(orderLog);
            }
        } catch (RuntimeException e) {
            log.info("dealWithApplyRefundOrder--{}", e);
            //异常
            refundReturnDB.setRefundMessage(e.getMessage());
            refundReturnDB.setRefundState(RefundReturn.ERefundState.ORDER_EXP.val());
            this.refundReturnMapper.updateById(refundReturnDB);

            OrderLog orderLog = new OrderLog();
            orderLog.setCreateTime(LocalDateTime.now());
            orderLog.setLogMsg("erp异常：" + e.getMessage());
            orderLog.setOrderId(refundReturnDB.getOrderId());
            orderLog.setOrderStatus(refundReturnDB.getRefundState());
            orderLog.setToErpNum("v1." + num);
            this.orderLogMapper.insert(orderLog);
        }


    }

    @Override
    public void reRefundOrderErp(Long id, String remark, Long userId) {
        AssertExt.hasId(id, "退货单ID无效");
        RefundReturn refundReturnDB = this.refundReturnMapper.selectById(id);
        AssertExt.notNull(refundReturnDB, "退货单不存在");
        AssertExt.isTrue(refundReturnDB.getRefundState().equals(RefundReturn.ERefundState.ORDER_EXP.val()), "只有erp异常才能重新下发");
        refundReturnDB.setRefundState(RefundReturn.ERefundState.DEAL_WITH.val());
        refundReturnDB.setReErpUserId(userId);
        refundReturnDB.setReErpRemark(remark);
        this.refundReturnMapper.updateById(refundReturnDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("重新下发退货单");
        orderLog.setOrderId(refundReturnDB.getOrderId());
        orderLog.setOrderStatus(refundReturnDB.getRefundState());
        orderLog.setToErpNum("v1.");
        this.orderLogMapper.insert(orderLog);
    }

    @Override
    public void sureReceive(Long id, String receiveMessage, Long userId) {
        AssertExt.hasId(id, "退货单ID无效");
        RefundReturn refundReturnDB = this.refundReturnMapper.selectById(id);
        AssertExt.notNull(refundReturnDB, "退货单不存在");
        AssertExt.isFalse(refundReturnDB.getRefundState().equals(RefundReturn.ERefundState.DELIVERY_DONE.val()) || refundReturnDB.getRefundState().equals(RefundReturn.ERefundState.DONE.val()), "请勿重复确认收货");
        refundReturnDB.setRefundState(RefundReturn.ERefundState.DELIVERY_DONE.val());
        refundReturnDB.setReceiveMessage(receiveMessage);
        refundReturnDB.setReceiveTime(LocalDateTime.now());
        refundReturnDB.setReceiveUserId(userId);
        this.refundReturnMapper.updateById(refundReturnDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("退货单-确认收货");
        orderLog.setOrderId(refundReturnDB.getOrderId());
        orderLog.setOrderStatus(refundReturnDB.getRefundState());
        orderLog.setToErpNum("v1.");
        this.orderLogMapper.insert(orderLog);
    }

    @Override
    public RefundReturnVo getRefundDetailByOrderIdList(Long orderId) {
        AssertExt.hasId(orderId, "订单id为空");
        RefundReturnVo refundReturnDB = this.refundReturnMapper.getRefundReturnVoInfo(orderId);
        AssertExt.notNull(refundReturnDB, "无效订单id【%s】", orderId);
        refundReturnDB.setRefundDetailList(this.refundDetailMapper.getRefundDetailList(refundReturnDB.getId()));
        return refundReturnDB;
    }


}
