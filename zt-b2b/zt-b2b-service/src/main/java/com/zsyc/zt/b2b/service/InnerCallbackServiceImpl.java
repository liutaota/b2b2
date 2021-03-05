package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.service.InnerCallbackService;
import com.zsyc.pay.vo.PayOrderVo;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.common.IncaUtils;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.GoodsInfoVo;
import com.zsyc.zt.b2b.vo.RecDocVo;
import com.zsyc.zt.inca.service.OrderService;
import com.zsyc.zt.inca.vo.OrderInfoDocVo;
import com.zsyc.zt.inca.vo.OrderInfoDtlVo;
import com.zsyc.zt.inca.vo.OrderResultDocVo;
import com.zsyc.zt.inca.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@Slf4j
public class InnerCallbackServiceImpl implements InnerCallbackService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private OrderLogMapper orderLogMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    //TODO erp服务单独版本参考这个
    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private OrderService orderService;
    @Autowired
    private RecDocMapper recDocMapper;
    @Autowired
    private RefundReturnMapper refundReturnMapper;

    @Autowired
    private StatementMapper statementMapper;
    @Autowired
    private TmpOrderRecDocMapper tmpOrderRecDocMapper;

    @Autowired
    private ErpB2bOrderRecDocMapper erpB2bOrderRecDocMapper;

    @Override
    public void payCallback(PayOrder payOrder) {
        String payOrderNo = payOrder.getOrderNo().split("_")[0];
        if (payOrderNo.equals("ZT")) {
            OrderInfo orderInfo = this.orderInfoMapper.selectOne(new QueryWrapper<OrderInfo>().eq("PAY_ORDER_NO", payOrder.getOrderNo()));
            log.info("订单号{}", payOrder.getOrderNo());
            AssertExt.notNull(orderInfo, "无效订单号");
            if (orderInfo.getOrderState().equals(OrderInfo.EOrderState.UNPAID.val())) {
                orderInfo.setOrderState(OrderInfo.EOrderState.TO_ERP.val());
            }
            orderInfo.setPayType(payOrder.getPayType());
            orderInfo.setPayTypeDoc(OrderInfo.EPayTypeDoc.valueOf(payOrder.getPayType()).desc());
            orderInfo.setPayMethod(OrderInfo.EPayMethod.ON_LINE.val());
            orderInfo.setPayTime(LocalDateTime.now());
            orderInfo.setPayFlowNo(payOrder.getPayFlowNo());
            orderInfo.setActuallyMoney((double) payOrder.getTotalFee().longValue() / 100);
            this.orderInfoMapper.updateById(orderInfo);

            OrderLog orderLog = new OrderLog();
            orderLog.setCreateTime(LocalDateTime.now());
            orderLog.setLogMsg("支付回调:支付单号" + payOrder.getOrderNo());
            orderLog.setOrderId(orderInfo.getId());
            orderLog.setToErpNum("v1");
            orderLog.setOrderStatus(orderInfo.getOrderState());
            this.orderLogMapper.insert(orderLog);

            //修改收款单状态
            List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()));
            for (RecDoc recDocDB : recDocList) {
                recDocDB.setPayAbcNo(payOrder.getOrderNo());
                recDocDB.setPayTime(LocalDateTime.now());
                recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                recDocDB.setRecType(RecDoc.ERecType.ON_LINE.val());
                this.recDocMapper.updateById(recDocDB);
            }

        } else if (payOrderNo.equals("PAY")) {
            Statement statement = this.statementMapper.selectOne(new QueryWrapper<Statement>().eq("PAY_ORDER_NO", payOrder.getOrderNo()));
            log.info("支付单号{}", payOrder.getOrderNo());
            AssertExt.notNull(statement, "无效订单号");
            statement.setPayAmount((double) Long.valueOf(payOrder.getTotalFee()) / 100);
            statement.setPayTime(LocalDateTime.now());
            statement.setPayFlowNo(payOrder.getPayFlowNo());
            statement.setPayType(Statement.ERecType.ON_LINE.val());
            statement.setStatus(Statement.EStatus.PAID.val());
            this.statementMapper.updateById(statement);

            //修改收款单状态
            List<RecDocVo> recDocList = this.recDocMapper.getMyRecDocAll(statement.getId());
            for (RecDocVo recDocDB : recDocList) {
                recDocDB.setPayAbcNo(payOrder.getOrderNo());
                recDocDB.setPayTime(LocalDateTime.now());
                recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                recDocDB.setRecType(RecDoc.ERecType.ON_LINE.val());
                this.recDocMapper.updateById(recDocDB);
            }
        } else {
            ErpB2bOrderRecDoc erpB2bOrderRecDoc = this.erpB2bOrderRecDocMapper.selectOne(new QueryWrapper<ErpB2bOrderRecDoc>().eq("TMP_NO", payOrder.getOrderNo()));
            AssertExt.notNull(erpB2bOrderRecDoc, "无效订单号");
            erpB2bOrderRecDoc.setUpdateTime(LocalDateTime.now());
            erpB2bOrderRecDoc.setPayAbcDoc(payOrder.getPayType());
            erpB2bOrderRecDoc.setPayFlowNo(payOrder.getPayFlowNo());
            erpB2bOrderRecDoc.setPayTime(LocalDateTime.now());
            erpB2bOrderRecDoc.setPayTotal((double) Long.valueOf(payOrder.getTotalFee()) / 100);
            this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);

            List<TmpOrderRecDoc> tmpOrderRecDocList = this.tmpOrderRecDocMapper.selectList(new QueryWrapper<TmpOrderRecDoc>().eq("TMP_NO", payOrder.getOrderNo()));
            log.info("支付单号{}", payOrder.getOrderNo());
            AssertExt.notNull(tmpOrderRecDocList, "无效订单号");
            for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {
                //tmpOrderRecDoc.setPayAmount((double)Long.valueOf(payOrder.getTotalFee()) / 100);
                tmpOrderRecDoc.setPayTime(LocalDateTime.now());
                tmpOrderRecDoc.setPayFlowNo(payOrder.getPayFlowNo());
                this.tmpOrderRecDocMapper.updateById(tmpOrderRecDoc);
                if (null != tmpOrderRecDoc.getOrderId()) {
                    OrderInfo orderInfo = this.orderInfoMapper.selectById(tmpOrderRecDoc.getOrderId());
                    if (null != orderInfo) {
                        orderInfo.setPayType(payOrder.getPayType());
                        orderInfo.setPayTypeDoc(OrderInfo.EPayTypeDoc.valueOf(payOrder.getPayType()).desc());
                        orderInfo.setPayTime(LocalDateTime.now());
                        orderInfo.setPayFlowNo(payOrder.getPayFlowNo());
                        this.orderInfoMapper.updateById(orderInfo);

                        //修改收款单状态
                        List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()));
                        for (RecDoc recDocDB : recDocList) {
                            recDocDB.setPayAbcNo(payOrder.getOrderNo());
                            recDocDB.setPayTime(LocalDateTime.now());
                            recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                            recDocDB.setRecType(RecDoc.ERecType.ON_LINE.val());
                            this.recDocMapper.updateById(recDocDB);
                        }
                    }
                }

            }

        }


    }

    @Override
    public void refundCallback(PayOrder payOrder) {
        OrderInfo orderInfo = this.orderInfoMapper.selectOne(new QueryWrapper<OrderInfo>().eq("PAY_ORDER_NO", payOrder.getOrderNo()));
        log.info("订单号{}", payOrder.getOrderNo());
        AssertExt.notNull(orderInfo, "无效订单号");
        orderInfo.setOrderState(OrderInfo.EOrderState.SUCCESS_REFUND.val());
        orderInfo.setRefundFlowNo(payOrder.getPayFlowNo());
        orderInfo.setRefundAmount(orderInfo.getRefundAmount() + Long.valueOf(payOrder.getRefundFee()));
        this.orderInfoMapper.updateById(orderInfo);

        RefundReturn refundReturnDB = this.refundReturnMapper.selectOne(new QueryWrapper<RefundReturn>().eq("order_id", orderInfo.getId()));
        refundReturnDB.setRefundState(RefundReturn.ERefundState.DONE.val());
        this.refundReturnMapper.updateById(refundReturnDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("退款回调:支付单号" + payOrder.getOrderNo());
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setOrderStatus(orderInfo.getOrderState());
        this.orderLogMapper.insert(orderLog);
    }

}
