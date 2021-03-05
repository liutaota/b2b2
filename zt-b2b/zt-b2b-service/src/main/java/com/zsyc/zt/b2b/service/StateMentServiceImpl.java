package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.framework.util.SnowFlakeIdWorker;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.service.PayOrderService;
import com.zsyc.pay.vo.PayOrderVo;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.common.IncaUtils;
import com.zsyc.zt.b2b.common.PinYinUtils;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.service.FinanceService;
import com.zsyc.zt.inca.vo.FinanceVerificationDocVo;
import com.zsyc.zt.inca.vo.FinanceVerificationDtlVo;
import com.zsyc.zt.inca.vo.FinanceVerificationResultDocVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tang on 2020/11/19.
 */
@Service
@Transactional
@Slf4j
public class StateMentServiceImpl implements StateMentService {
    @Autowired
    private StatementMapper statementMapper;
    @Autowired
    private StatementRecDocMapper statementRecDocMapper;

    @Autowired
    private RecDocMapper recDocMapper;

    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private FinanceService financeService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private RefundDetailMapper refundDetailMapper;
    @Autowired
    private OrderLogMapper orderLogMapper;
    @Autowired
    private RecDtlMapper recDtlMapper;
    @Autowired(required = false)
    private PayOrderService payOrderService;

    @Autowired
    @Qualifier("payWorker")
    private SnowFlakeIdWorker payFlakeIdWorker;

    @Autowired
    @Qualifier("cashWorker")
    private SnowFlakeIdWorker cashFlakeIdWorker;

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private SettingService settingService;

    @Autowired
    private RefundReturnMapper refundReturnMapper;

    @Autowired
    private TmpOrderRecDocMapper tmpOrderRecDocMapper;

    @Autowired
    private ErpB2bOrderRecDocMapper erpB2bOrderRecDocMapper;
    @Autowired
    private UserMapper userMapper;

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public void addStateMent() {
        List<Member> memberList = this.memberMapper.selectList(new QueryWrapper<Member>().orderByAsc("id"));
        for (Member member : memberList) {
            List<RecDoc> recDocList = this.statementMapper.getMonthRecDoc(member.getId());
            if (recDocList.size() > 0) {
                Long time = this.settingService.getOrderScore("PAY_STATEMENT_DATE");

                Statement statement = new Statement();
                statement.setMemberId(member.getId());
                statement.setStatementNo(this.payFlakeIdWorker.genNo());
                statement.setLines(recDocList.size());
                statement.setPayType(Statement.ERecType.CASH.val());
                statement.setSource("1");
                statement.setStatus(Statement.EStatus.UNPAID.val());
                statement.setPayStatementTime(LocalDateTime.now().plusMinutes(time));
                statement.setCreateTime(LocalDateTime.now());
                this.statementMapper.insert(statement);
                double total = 0L;
                for (RecDoc recDoc : recDocList) {
                    total += recDoc.getTotal();
                    StatementRecDoc statementRecDoc = new StatementRecDoc();
                    statementRecDoc.setRecDocId(recDoc.getId());
                    statementRecDoc.setStatementId(statement.getId());
                    statementRecDoc.setCreateTime(LocalDateTime.now());
                    this.statementRecDocMapper.insert(statementRecDoc);
                    recDoc.setIsTrue(1L);
                    this.recDocMapper.updateById(recDoc);
                }

                statement.setTotel(total);
                this.statementMapper.updateById(statement);
            }
        }
    }

    @Override
    public IPage<StatementVo> getStateMentList(Page page, StatementVo statementVo) {
        return this.statementMapper.getStateMentList(page, statementVo);
    }

    @Override
    public IPage<RecDocVo> getRecDocList(Page page, RecDocVo recDocVo) {
        IPage<RecDocVo> recDocVoIPage = this.recDocMapper.getRecDocList(page, recDocVo);
        recDocVoIPage.getRecords().forEach(recDocVo1 -> {
            recDocVo1.setRecDtlList(this.recDtlMapper.getRecDtlList(recDocVo1.getId()));
        });
        return recDocVoIPage;
    }

    @Override
    public void updateRecDocStatus(Long[] ids, String remark, Long userId) {
        AssertExt.notNull(ids, "id为空");
        for(Long id:ids){
            RecDoc recDocDB = this.recDocMapper.selectById(id);
            AssertExt.notNull(recDocDB, "无效id【%s】", id);
            AssertExt.notNull(recDocDB.getConfirmDate(),"请先确认收款/退款");
            recDocDB.setRemark(remark);
            recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
            recDocDB.setErpFinanceRemark(null);
            OrderInfo orderInfo = this.orderInfoMapper.selectById(recDocDB.getOrderId());
            if (!recDocDB.getRecMethod().equals(RecDoc.ERecMethod.NO_ORDER.val()) && !recDocDB.getRecMethod().equals(RecDoc.ERecMethod.REFUND_GOODS.val())) {
                AssertExt.isTrue(orderInfo.getOrderState().equals(OrderInfo.EOrderState.TO_RECEIVED.val()), "只有已收货才能核销");
            }

            orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());

            if (orderInfo.getFinanceTrue() == 1) {
                recDocDB.setFinanceTrue(1L);
                recDocDB.setFinanceTime(LocalDateTime.now());
            }
            if (recDocDB.getRecMethod().equals(RecDoc.ERecMethod.NO_ORDER.val()) || recDocDB.getRecMethod().equals(RecDoc.ERecMethod.SHORT.val())) {
                recDocDB.setFinanceTrue(1L);
                orderInfo.setFinanceTrue(1L);
            } else {
                recDocDB.setFinanceTrue(2L);
            }

            if (recDocDB.getRecMethod().equals(RecDoc.ERecMethod.REFUND_GOODS.val())) {
                RefundReturn refundReturnDB = this.refundReturnMapper.selectById(recDocDB.getRefundId());
                if (null != refundReturnDB) {
                    AssertExt.isTrue(refundReturnDB.getRefundState().equals(RefundReturn.ERefundState.DELIVERY_DONE.val()) || refundReturnDB.getRefundState().equals(RefundReturn.ERefundState.DONE.val()), "退款单只有已收货或者已完成才能核销");
                }
            }
            recDocDB.setFinanceUserId(userId);
            this.recDocMapper.updateById(recDocDB);
            this.orderInfoMapper.updateById(orderInfo);

        }

        // if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.WX_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ZFB_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ABC_LINE.val())) {


    }

    @Override
    public void handFinanceRecDoc() {
        List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("FINANCE_TRUE", 2).isNotNull("CONFIRM_DATE"));
        for (RecDoc recDocDB : recDocList) {
            if (!recDocDB.getRecMethod().equals(RecDoc.ERecMethod.NO_ORDER.val()) || !recDocDB.getRecMethod().equals(RecDoc.ERecMethod.SHORT.val())) {
                OrderInfo orderInfo = this.orderInfoMapper.selectById(recDocDB.getOrderId());
                if (!orderInfo.getOrderState().equals(OrderInfo.EOrderState.TO_RECEIVED.val())) continue;
                if (!orderInfo.getExpStatus().equals(OrderInfo.EExpStatus.NORMAL.val())) continue;
                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("订单核销");
                orderLog.setOrderId(orderInfo.getId());
                FinanceVerificationDocVo financeVerificationDocVo = new FinanceVerificationDocVo();
                financeVerificationDocVo.setAmountPay(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount()));
                financeVerificationDocVo.setAmountTotal(IncaUtils.toErpPriceDouble4(orderInfo.getOrderAmount()));
                financeVerificationDocVo.setAmountDiscrepancy(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount() - orderInfo.getErpAmount()));
                financeVerificationDocVo.setCustomId(orderInfo.getErpCustomerId());
                financeVerificationDocVo.setOrderId(orderInfo.getId());
                financeVerificationDocVo.setOrderNo(orderInfo.getOrderNo());
                financeVerificationDocVo.setRemark(recDocDB.getConfirmRemark());
                financeVerificationDocVo.setCreateDate(orderInfo.getCreateTime());
                financeVerificationDocVo.setSettleMethod(orderInfo.getPayMethod());
                financeVerificationDocVo.setEntryId(1);
                User user = this.userMapper.selectById(recDocDB.getConfirmUserId());
                financeVerificationDocVo.setConfirmManId(user.getErpEmployeeId());
                financeVerificationDocVo.setInputManId(1L);
                financeVerificationDocVo.setOrderNo(orderInfo.getOrderNo());
                financeVerificationDocVo.setOrderId(orderInfo.getId());
                financeVerificationDocVo.setRecDocNo(recDocDB.getRecDocNo());
                financeVerificationDocVo.setPayDate(orderInfo.getPayTime());
                financeVerificationDocVo.setPayType(orderInfo.getPayMethod());
                financeVerificationDocVo.setBankId(44147L);
                financeVerificationDocVo.setAmountVerification(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount()));

                financeVerificationDocVo.setPayFlowNo(orderInfo.getPayFlowNo());
                financeVerificationDocVo.setVerificationDate(recDocDB.getConfirmDate());

                String num = RandomStringUtils.random(3, false, true);
                financeVerificationDocVo.setVersion("v1." + num);

                orderLog.setToErpNum("v1." + num);

                List<FinanceVerificationDtlVo> financeVerificationDtlVoList = new ArrayList<>();
                if (!orderInfo.getRefundState().equals(OrderInfo.ERefundState.NO_REFUND.val())) {
                    financeVerificationDocVo.setOrderType(3);
                    financeVerificationDocVo.setVerificationType(4);
                    //List<RefundDetail> refundDetailList = this.refundDetailMapper.selectList(new QueryWrapper<RefundDetail>().eq("ORDER_ID", orderInfo.getId()));
                    List<OrderGoodsVo> orderGoodsList = this.orderGoodsMapper.orderGoodsDeliveryList(orderInfo.getId());
                    for (OrderGoodsVo orderGoods : orderGoodsList) {
                        QueryWrapper queryWrapper = new QueryWrapper<RefundDetail>().eq("ORDER_ID", orderInfo.getId()).eq("GOODS_ID", orderGoods.getGoodsId());

                        if (null != orderGoods.getLotId()) {
                            queryWrapper.eq("LOT_ID", orderGoods.getLotId());
                        }
                        if (null != orderGoods.getLotno()) {
                            queryWrapper.eq("LOT_NO", orderGoods.getLotno());
                        }
                        if (null != orderGoods.getBatchid()) {
                            queryWrapper.eq("BATCH_ID", orderGoods.getBatchid());
                        }
                        RefundDetail refundDetail = this.refundDetailMapper.selectOne(queryWrapper);

                        if (null == refundDetail) continue;
                        RefundReturn refundReturnDB = this.refundReturnMapper.selectById(refundDetail.getRefundId());

                        financeVerificationDocVo.setOrderNo(refundReturnDB.getApplyNo());
                        financeVerificationDocVo.setOrderId(refundDetail.getRefundId());

                        FinanceVerificationDtlVo financeVerificationDtlVo = new FinanceVerificationDtlVo();
                        financeVerificationDtlVo.setNum(Double.valueOf(refundDetail.getGoodsNum()));
                        financeVerificationDtlVo.setAmount(Double.valueOf(refundDetail.getRefundAmount()));
                        financeVerificationDtlVo.setGoodsId(orderGoods.getGoodsId());
                        financeVerificationDtlVo.setSrcOrderId(orderInfo.getId().toString());
                        financeVerificationDtlVo.setSrcOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                        financeVerificationDtlVo.setSrcErpOrderId(orderGoods.getSrcErpOrderId());
                        financeVerificationDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
                        financeVerificationDtlVo.setOrderId(refundReturnDB.getId().toString());
                        financeVerificationDtlVo.setOrderDtlId(refundDetail.getId().toString());
                        financeVerificationDtlVo.setStorageId(orderGoods.getErpStorageId());
                  /*  if (orderGoods.getOrderGoodsType().equals(4)) {
                        financeVerificationDtlVo.setIsGift(true);
                    } else {
                        financeVerificationDtlVo.setIsGift(false);
                    }*/
                        financeVerificationDtlVoList.add(financeVerificationDtlVo);
                    }
                } else {
                    //if(orderInfo.getFinanceTrue()==1)return;
                    financeVerificationDocVo.setOrderType(1);
                    if (recDocDB.getRecType().equals(RecDoc.ERecType.CASH.val())) {
                        financeVerificationDocVo.setVerificationType(2);
                    } else if (recDocDB.getRecType().equals(RecDoc.ERecType.MONTH.val())) {
                        financeVerificationDocVo.setVerificationType(3);
                    } else if (recDocDB.getRecType().equals(RecDoc.ERecType.ON_LINE.val())) {
                        financeVerificationDocVo.setVerificationType(1);
                    } else {
                        financeVerificationDocVo.setVerificationType(0);
                    }
                    List<OrderGoodsVo> orderGoodsList = this.orderGoodsMapper.orderGoodsDeliveryList(orderInfo.getId());
                    for (OrderGoodsVo orderGoods : orderGoodsList) {

                        if (financeVerificationDtlVoList.size() > 0) {

                            if (financeVerificationDtlVoList.stream().anyMatch(item -> item.getSrcErpOrderId().equals(orderGoods.getSrcErpOrderId()))) {
                                financeVerificationDtlVoList.forEach(financeVerificationDtlVo1 -> {
                                    if (financeVerificationDtlVo1.getSrcErpOrderId().equals(orderGoods.getSrcErpOrderId())) {
                                        financeVerificationDtlVo1.setAmount(IncaUtils.toErpPriceDouble4(financeVerificationDtlVo1.getAmount() + orderGoods.getTotal()));
                                    }
                                });

                            } else {
                                FinanceVerificationDtlVo financeVerificationDtlVo = new FinanceVerificationDtlVo();
                                financeVerificationDtlVo.setNum(orderGoods.getDtlgoodsqty());
                                financeVerificationDtlVo.setAmount(IncaUtils.toErpPriceDouble4(orderGoods.getTotal()));
                                financeVerificationDtlVo.setGoodsId(orderGoods.getGoodsId());
                                financeVerificationDtlVo.setSrcOrderId(orderInfo.getId().toString());
                                //financeVerificationDtlVo.setSrcOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                                financeVerificationDtlVo.setSrcErpOrderId(orderGoods.getSrcErpOrderId());
                                // financeVerificationDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
                                financeVerificationDtlVo.setOrderId(orderInfo.getId().toString());
                                //financeVerificationDtlVo.setOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                                financeVerificationDtlVo.setStorageId(orderGoods.getErpStorageId());
                                financeVerificationDtlVoList.add(financeVerificationDtlVo);
                            }
                        } else {
                            FinanceVerificationDtlVo financeVerificationDtlVo = new FinanceVerificationDtlVo();
                            financeVerificationDtlVo.setNum(orderGoods.getDtlgoodsqty());
                            financeVerificationDtlVo.setAmount(Double.valueOf(orderGoods.getTotal()));
                            financeVerificationDtlVo.setGoodsId(orderGoods.getGoodsId());
                            financeVerificationDtlVo.setSrcOrderId(orderInfo.getId().toString());
                            //financeVerificationDtlVo.setSrcOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                            financeVerificationDtlVo.setSrcErpOrderId(orderGoods.getSrcErpOrderId());
                            // financeVerificationDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
                            financeVerificationDtlVo.setOrderId(orderInfo.getId().toString());
                            // financeVerificationDtlVo.setOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                            financeVerificationDtlVo.setStorageId(orderGoods.getErpStorageId());
                            financeVerificationDtlVoList.add(financeVerificationDtlVo);
                        }
                    }
                }

                financeVerificationDocVo.setFinanceVerificationDtlVoList(financeVerificationDtlVoList);


                try {
                    //FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verification(financeVerificationDocVo);
                    // FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV2(financeVerificationDocVo);
                    FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV3(financeVerificationDocVo);
                    if (financeVerificationResultDocVo.getErrorCode() != 0) {
                        recDocDB.setFinanceTrue(3L);
                        recDocDB.setFinanceTime(LocalDateTime.now());
                        recDocDB.setErpFinanceRemark(financeVerificationResultDocVo.getErrorMessage());
                        orderInfo.setFinanceTrue(0L);
                        orderInfo.setExpStatus(OrderInfo.EExpStatus.FINANCE_EXP.val());
                        orderInfo.setExpRemark("订单核销--" + financeVerificationResultDocVo.getErrorMessage());
                    } else {
                        recDocDB.setFinanceTrue(1L);
                        recDocDB.setErpFinanceRemark("核销成功");
                        orderInfo.setFinanceTrue(1L);
                        orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                    /*if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.WX_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ZFB_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ABC_LINE.val())) {
                        if (orderInfo.getRefundState().equals(OrderInfo.ERefundState.PART_REFUND.val()) || orderInfo.getRefundState().equals(OrderInfo.ERefundState.ALL_REFUND.val())) {
                            this.payOrderService.refund(orderInfo.getPayOrderNo(), num, 1);
                            orderInfo.setOrderState(OrderInfo.EOrderState.IN_REFUND.val());

                        }
                    }*/
                    }

                    this.orderInfoMapper.updateById(orderInfo);
                    this.recDocMapper.updateById(recDocDB);


                    orderLog.setOrderStatus(orderInfo.getOrderState());
                    orderLog.setDoneTime(LocalDateTime.now());
                    this.orderLogMapper.insert(orderLog);

                } catch (RuntimeException e) {
                    log.error("StateMentService.handFinanceRecDoc error ", e);
                    orderInfo.setExpRemark(e.getMessage());
                    orderInfo.setExpStatus(OrderInfo.EExpStatus.CATCH_EXP.val());
                    orderInfo.setIsTrue("0");
                    this.orderInfoMapper.updateById(orderInfo);
                    recDocDB.setFinanceTrue(3L);
                    recDocDB.setErpFinanceRemark(e.getMessage());
                    this.recDocMapper.updateById(recDocDB);

                    orderLog.setOrderStatus(OrderInfo.EExpStatus.CATCH_EXP.val());
                    orderLog.setDoneTime(LocalDateTime.now());
                    this.orderLogMapper.insert(orderLog);
                }

            }
        }

    }

    @Override
    public IPage<RecDocVo> getMyRecDocList(Page page, RecDocVo recDocVo) {
        return this.recDocMapper.getMyRecDocList(page, recDocVo);
    }

    @Override
    public IPage<StatementVo> getMyStateMentList(Page page, StatementVo statementVo) {
        IPage<StatementVo> statementVoIPag = this.statementMapper.getMyStateMentList(page, statementVo);
        statementVoIPag.getRecords().forEach(statementVo1 -> {
            List<RecDocVo> recDocVoList = new ArrayList<>();
            recDocVoList = this.recDocMapper.getMyRecDocAll(statementVo1.getId());
            statementVo1.setRecDocVoList(recDocVoList);
            statementVo1.setOrderNum(this.recDocMapper.getStatementOrderNum(statementVo1.getId()));
        });
        return statementVoIPag;
    }

    @Override
    public PayOrder payMyStatement(Long id, String ip, String openid, String orderFrom, String paymentType) {
        AssertExt.hasId(id, "id为空");
        Statement statement = this.statementMapper.selectById(id);
        AssertExt.notNull(statement, "无效id[%s]", id);
        String payOrderNo = statement.getStatementNo() + "_ZF_" + System.currentTimeMillis();
        statement.setPayOrderNo(payOrderNo);
        this.statementMapper.updateById(statement);
        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setOrderNo(payOrderNo);
        payOrderVo.setDataSource(orderFrom);
        payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(statement.getTotel()));
        payOrderVo.setBody("中天预售在线支付");
        payOrderVo.setOpenid(openid);
        payOrderVo.setSpbillCreateIp(ip);
        payOrderVo.setPaymentType(paymentType);
        PayOrder payOrder = this.payOrderService.payOrder(payOrderVo);
        return payOrder;
    }

    @Override
    public StatementVo getMyRecDocAll(StatementVo statementVo) {
        AssertExt.hasId(statementVo.getId(), "id为空");
        Statement statement = this.statementMapper.selectById(statementVo.getId());
        AssertExt.notNull(statement, "无效id[%s]", statementVo.getId());
        Member member = this.memberMapper.selectById(statement.getMemberId());
        //StatementVo statementVo = new StatementVo();
        BeanUtils.copyProperties(statement, statementVo);
        statementVo.setUserName(member.getUserName());
        statementVo.setNoPayTotal(this.recDocMapper.getMyNoPayTotal(statement.getMemberId()));
        List<RecDocVo> recDocVoList = new ArrayList<>();
        recDocVoList = this.recDocMapper.getMyRecDocListById(statement.getId(), statementVo.getStartTime(), statementVo.getEndTime());
        statementVo.setRecDocVoList(recDocVoList);
        return statementVo;
    }

    @Override
    public void updateStatement(Long id, String remark) {
        AssertExt.hasId(id, "id为空");
        Statement statement = this.statementMapper.selectById(id);
        AssertExt.notNull(statement, "无效id[%s]", id);
        statement.setRemark(remark);
        statement.setPayTime(LocalDateTime.now());
        statement.setStatus(Statement.EStatus.PAID.val());
        statement.setPayType(Statement.ERecType.OFF_LINE.val());
        this.statementMapper.updateById(statement);
        //修改收款单状态
        List<RecDocVo> recDocList = this.recDocMapper.getMyRecDocAll(statement.getId());
        for (RecDocVo recDocDB : recDocList) {
            recDocDB.setPayTime(LocalDateTime.now());
            recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
            recDocDB.setRecType(Statement.ERecType.OFF_LINE.val());
            this.recDocMapper.updateById(recDocDB);
        }
    }

    @Override
    public void refundRecDocOrder(Long id, String refundRemark, Long userId) {
        AssertExt.hasId(id, "id为空");
        //AssertExt.notNull(refundRemark, "备注为空");
        RecDoc recDocDB = this.recDocMapper.selectById(id);
        AssertExt.notNull(recDocDB, "无效id【%s】", id);
        OrderInfo orderInfo = this.orderInfoMapper.selectById(recDocDB.getOrderId());

        AssertExt.isTrue(recDocDB.getRefundState().equals(RecDoc.ERefundState.NO_REFUND.val()) || recDocDB.getRefundState().equals(RecDoc.ERefundState.REFUND_FAIL.val()), "只有未退款和退款失败才能发起退款");
        AssertExt.isTrue(orderInfo.getExpStatus().equals(OrderInfo.EExpStatus.NORMAL.val()) , "该订单存在异常，请先去异常列表确认异常");

        if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.WX_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ZFB_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ABC_LINE.val())) {
            String num = "TK_" + DATE_TIME_FORMATTER.format(LocalDateTime.now());
            PayOrderVo payOrder = new PayOrderVo();
            if (orderInfo.getRefundState().equals(OrderInfo.ERefundState.PART_REFUND.val()) || orderInfo.getRefundState().equals(OrderInfo.ERefundState.ALL_REFUND.val())) {
                RefundReturn refundReturnDB = this.refundReturnMapper.selectOne(new QueryWrapper<RefundReturn>().eq("order_id", orderInfo.getId()));

                try {
                    payOrder = this.payOrderService.refund(orderInfo.getPayOrderNo(), num, (int) (refundReturnDB.getRefundAmount() * 100));
                    double amount = orderInfo.getRefundAmount() + (double) payOrder.getRefundFee() / 100;
                    orderInfo.setRefundAmount(amount);
                    orderInfo.setRefundFlowNo(payOrder.getRefundFlowNo());
                    this.orderInfoMapper.updateById(orderInfo);


                    refundReturnDB.setRefundState(RefundReturn.ERefundState.DONE.val());
                    this.refundReturnMapper.updateById(refundReturnDB);

                    OrderLog orderLog = new OrderLog();
                    orderLog.setCreateTime(LocalDateTime.now());
                    orderLog.setOrderId(orderInfo.getId());
                    orderLog.setLogMsg("退款单号" + orderInfo.getPayOrderNo() + "_" + num + "；退款流水号：" + payOrder.getRefundFlowNo());
                    orderLog.setOrderStatus(orderInfo.getOrderState());
                    orderLog.setDoneTime(LocalDateTime.now());
                    orderLog.setToErpNum("v1");
                    this.orderLogMapper.insert(orderLog);


                    //修改收款单状态
                    recDocDB.setAbcMessage(payOrder.toString());
                    recDocDB.setRefundState(RecDoc.ERefundState.REFUND_SUCCESS.val());
                    recDocDB.setPayRefundNo(payOrder.getRefundFlowNo());
                    recDocDB.setRefundTime(LocalDateTime.now());
                    recDocDB.setPayAbcNo(orderInfo.getPayOrderNo() + "_" + num);
                    recDocDB.setRefundRemark(refundRemark);
                    recDocDB.setRefundUserId(userId);
                    recDocDB.setRefundMoney(amount);
                    recDocDB.setPayTime(LocalDateTime.now());
                    recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                    recDocDB.setRecType(orderInfo.getPayMethod());
                    this.recDocMapper.updateById(recDocDB);
                } catch (RuntimeException e) {
                    log.error("orderService.refundRecDocOrder error ", e);
                    OrderLog orderLog = new OrderLog();
                    orderLog.setCreateTime(LocalDateTime.now());
                    orderLog.setOrderId(orderInfo.getId());
                    orderLog.setLogMsg("退款异常" + e.getMessage());
                    orderLog.setOrderStatus(orderInfo.getOrderState());
                    orderLog.setDoneTime(LocalDateTime.now());
                    orderLog.setToErpNum("v1");
                    this.orderLogMapper.insert(orderLog);


                    //修改收款单状态
                    recDocDB.setRefundState(RecDoc.ERefundState.REFUND_FAIL.val());
                    recDocDB.setAbcMessage(e.getMessage());
                    recDocDB.setRefundTime(LocalDateTime.now());
                    recDocDB.setPayAbcNo(orderInfo.getPayOrderNo() + "_" + num);
                    recDocDB.setRefundRemark(refundRemark);
                    recDocDB.setRefundUserId(userId);
                    recDocDB.setRefundMoney(0L);
                    //recDocDB.setPayTime(LocalDateTime.now());
                    // recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                    //recDocDB.setRecType(orderInfo.getPayMethod());
                    this.recDocMapper.updateById(recDocDB);
                }


            }
            else if (recDocDB.getRecMethod().equals(RecDoc.ERecMethod.NO_ORDER.val())|| recDocDB.getRecMethod().equals(RecDoc.ERecMethod.SHORT.val())) {
                     log.info("22222222222222{}",id);
                try {
                    if(recDocDB.getRecMethod().equals(RecDoc.ERecMethod.SHORT.val())){
                        payOrder = this.payOrderService.refund(orderInfo.getPayOrderNo(), num, (int) ((orderInfo.getActuallyMoney()-orderInfo.getErpAmount()) * 100));

                    }
                    else{
                        payOrder = this.payOrderService.refund(orderInfo.getPayOrderNo(), num, (int) (orderInfo.getActuallyMoney()* 100));

                    }
                    //payOrder = this.payOrderService.refund(orderInfo.getPayOrderNo(), num, (int) ((orderInfo.getActuallyMoney()-orderInfo.getErpAmount()) * 100));
                    double amount = orderInfo.getRefundAmount() + (double) payOrder.getRefundFee() / 100;
                    orderInfo.setRefundAmount(amount);
                    orderInfo.setRefundFlowNo(payOrder.getRefundFlowNo());
                    this.orderInfoMapper.updateById(orderInfo);

                    OrderLog orderLog = new OrderLog();
                    orderLog.setCreateTime(LocalDateTime.now());
                    orderLog.setOrderId(orderInfo.getId());
                    orderLog.setLogMsg("退款单号" + orderInfo.getPayOrderNo() + "_" + num + "；退款流水号：" + payOrder.getRefundFlowNo());
                    orderLog.setOrderStatus(orderInfo.getOrderState());
                    orderLog.setDoneTime(LocalDateTime.now());
                    orderLog.setToErpNum("v1");
                    this.orderLogMapper.insert(orderLog);

                    //修改收款单状态
                    recDocDB.setAbcMessage(payOrder.toString());
                    recDocDB.setRefundState(RecDoc.ERefundState.REFUND_SUCCESS.val());
                    recDocDB.setPayRefundNo(payOrder.getRefundFlowNo());
                    recDocDB.setRefundTime(LocalDateTime.now());
                    recDocDB.setPayAbcNo(orderInfo.getPayOrderNo() + "_" + num);
                    recDocDB.setRefundRemark(refundRemark);
                    recDocDB.setRefundUserId(userId);
                    recDocDB.setRefundMoney(amount);
                    recDocDB.setPayTime(LocalDateTime.now());
                    recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                    recDocDB.setRecType(orderInfo.getPayMethod());
                    this.recDocMapper.updateById(recDocDB);
                } catch (RuntimeException e) {
                    log.error("orderService.refundRecDocOrder111 error ", e);
                    OrderLog orderLog = new OrderLog();
                    orderLog.setCreateTime(LocalDateTime.now());
                    orderLog.setOrderId(orderInfo.getId());
                    orderLog.setLogMsg("退款异常" + e.getMessage());
                    orderLog.setOrderStatus(orderInfo.getOrderState());
                    orderLog.setDoneTime(LocalDateTime.now());
                    orderLog.setToErpNum("v1");
                    this.orderLogMapper.insert(orderLog);

                    //修改收款单状态
                    recDocDB.setRefundState(RecDoc.ERefundState.REFUND_FAIL.val());
                    recDocDB.setAbcMessage(e.getMessage());
                    recDocDB.setPayAbcNo(orderInfo.getPayOrderNo() + "_" + num);
                    recDocDB.setRefundRemark(refundRemark);
                    recDocDB.setRefundUserId(userId);
                    recDocDB.setRefundMoney(0L);
                    //recDocDB.setPayTime(LocalDateTime.now());
                    //recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                    //recDocDB.setRecType(orderInfo.getPayMethod());
                    this.recDocMapper.updateById(recDocDB);
                }

            }

        }
    }

    @Override
    public Statement addStateMentByRecDoc(List<RecDoc> recDocList) {
        AssertExt.notNull(recDocList, "数据为空");
        Statement statement = new Statement();
        Long time = this.settingService.getOrderScore("PAY_STATEMENT_DATE");
        statement.setLines(recDocList.size());
        statement.setPayType(Statement.ERecType.CASH.val());
        statement.setStatementNo(this.payFlakeIdWorker.genNo());
        statement.setSource("1");
        statement.setStatus(Statement.EStatus.UNPAID.val());
        statement.setPayStatementTime(LocalDateTime.now().plusMinutes(time));
        statement.setCreateTime(LocalDateTime.now());
        this.statementMapper.insert(statement);
        double total = 0L;
        for (RecDoc recDoc : recDocList) {
            AssertExt.hasId(recDoc.getMemberId(), "memberId为空");
            AssertExt.hasId(recDoc.getId(), "id为空");

            statement.setMemberId(recDoc.getMemberId());
            total += recDoc.getTotal();
            StatementRecDoc statementRecDoc = new StatementRecDoc();
            statementRecDoc.setRecDocId(recDoc.getId());
            statementRecDoc.setStatementId(statement.getId());
            statementRecDoc.setCreateTime(LocalDateTime.now());
            this.statementRecDocMapper.insert(statementRecDoc);
            recDoc.setIsTrue(1L);
            this.recDocMapper.updateById(recDoc);
        }

        statement.setTotel(total);
        this.statementMapper.updateById(statement);
        return statement;
    }

    @Override
    public Statement addStateMentByMemberId(Long memberId) {
        AssertExt.hasId(memberId, "memberId为空");
        StatementRecDoc statementRecDocDB = this.statementRecDocMapper.getStatementRecDocByMemberId(memberId);
        if (null != statementRecDocDB) {
            Statement statement = this.statementMapper.selectById(statementRecDocDB.getStatementId());
            return statement;
        } else {
            List<RecDoc> recDocList = this.recDocMapper.getRecDocByMemberId(memberId);
            Statement statement = new Statement();
            Long time = this.settingService.getOrderScore("PAY_STATEMENT_DATE");
            statement.setLines(recDocList.size());
            statement.setPayType(Statement.ERecType.CASH.val());
            statement.setStatementNo(this.payFlakeIdWorker.genNo());
            statement.setSource("1");
            statement.setStatus(Statement.EStatus.UNPAID.val());
            statement.setPayStatementTime(LocalDateTime.now().plusMinutes(time));
            statement.setCreateTime(LocalDateTime.now());
            this.statementMapper.insert(statement);
            double total = 0L;
            for (RecDoc recDoc : recDocList) {
                AssertExt.hasId(recDoc.getMemberId(), "memberId为空");
                AssertExt.hasId(recDoc.getId(), "id为空");

                statement.setMemberId(recDoc.getMemberId());
                total += recDoc.getTotal();
                StatementRecDoc statementRecDoc = new StatementRecDoc();
                statementRecDoc.setRecDocId(recDoc.getId());
                statementRecDoc.setStatementId(statement.getId());
                statementRecDoc.setCreateTime(LocalDateTime.now());
                this.statementRecDocMapper.insert(statementRecDoc);
                recDoc.setIsTrue(1L);
                this.recDocMapper.updateById(recDoc);
            }

            statement.setTotel(total);
            this.statementMapper.updateById(statement);
            return statement;
        }

    }

    @Override
    public PayOrder addTmpOrderRecDoc(Long[] orderIds, String ip, Long userId, String paymentType) {
        AssertExt.notNull(orderIds, "订单id为空");
        String tmpNo = this.cashFlakeIdWorker.genNo();
        double total = 0;
        for (Long id : orderIds) {
            OrderInfo orderInfo = this.orderInfoMapper.selectById(id);

            TmpOrderRecDoc tmpOrderRecDocDB = new TmpOrderRecDoc();
            tmpOrderRecDocDB.setTotal(orderInfo.getErpAmount());
            tmpOrderRecDocDB.setOrderNo(orderInfo.getOrderNo());
            tmpOrderRecDocDB.setOrderId(id);
            tmpOrderRecDocDB.setTmpNo(tmpNo);
            tmpOrderRecDocDB.setType(TmpOrderRecDoc.EType.B2B_ORDER.val());
            tmpOrderRecDocDB.setCreateTime(LocalDateTime.now());
            tmpOrderRecDocDB.setUserId(userId);
            this.tmpOrderRecDocMapper.insert(tmpOrderRecDocDB);
            total += orderInfo.getErpAmount();
        }

        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setOrderNo(tmpNo);
        payOrderVo.setDataSource("6");
        payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(total));
        payOrderVo.setBody("中天预售在线支付");
        payOrderVo.setOpenid(null);
        payOrderVo.setSpbillCreateIp(ip);
        payOrderVo.setPaymentType(paymentType);
        PayOrder payOrder = this.payOrderService.payOrder(payOrderVo);
        return payOrder;
    }

    @Override
    public PayOrder addTmpErpOrderRecDoc(List<ErpOrderInfo> erpOrderInfoList, String ip, Long userId) {
        AssertExt.notNull(erpOrderInfoList, "数据为空");
        String tmpNo = this.cashFlakeIdWorker.genNo();
        double total = 0;
        for (ErpOrderInfo erpOrderInfo : erpOrderInfoList) {
            //ErpOrderInfo erpOrderInfo= this.orderInfoMapper.getErpOrderInfoByMemberId(erpUserId,id);

            TmpOrderRecDoc tmpOrderRecDocDB = new TmpOrderRecDoc();
            tmpOrderRecDocDB.setTotal(erpOrderInfo.getPayTotaline());
            //tmpOrderRecDocDB.setOrderNo(erpOrderInfo.getOrderNo());
            tmpOrderRecDocDB.setOrderId(erpOrderInfo.getB2bOrderId());
            tmpOrderRecDocDB.setSalesid(erpOrderInfo.getSalesid());
            tmpOrderRecDocDB.setTmpNo(tmpNo);
            tmpOrderRecDocDB.setType(TmpOrderRecDoc.EType.ERP_ORDER.val());
            tmpOrderRecDocDB.setCreateTime(LocalDateTime.now());
            tmpOrderRecDocDB.setUserId(userId);
            this.tmpOrderRecDocMapper.insert(tmpOrderRecDocDB);
            total += erpOrderInfo.getPayTotaline();
        }

        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setOrderNo(tmpNo);
        payOrderVo.setDataSource("6");
        //payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(0.1));
        payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(total));
        payOrderVo.setBody("中天预售在线支付");
        payOrderVo.setOpenid(null);
        payOrderVo.setSpbillCreateIp(ip);
        payOrderVo.setPaymentType(erpOrderInfoList.get(0).getPaymentType());
        PayOrder payOrder = this.payOrderService.payOrder(payOrderVo);
        return payOrder;
    }

    @Override
    public ErpB2bOrderRecDoc getTmpOrderRecDoc(String tmpNo) {
        AssertExt.notNull(tmpNo, "单号为空");
        return this.erpB2bOrderRecDocMapper.selectOne(new QueryWrapper<ErpB2bOrderRecDoc>().eq("TMP_NO", tmpNo));
    }

    @Override
    public PayOrder addErpB2bOrderRecDoc(Long[] salesids, Long erpUserId, String ip, Long userId, String paymentType, double cashAmount, String tranposname, String transortno, String payType, String openid) {
        AssertExt.notNull(salesids, "订单id为空");
        AssertExt.notNull(erpUserId, "客户id为空");
        AssertExt.notNull(payType, "支付方式为空");
        // AssertExt.isTrue(cashAmount >= 0, "现结金额要大于等于0");

        String tmpNo = this.cashFlakeIdWorker.genNo();
        double total = 0;
        //cashAmount = IncaUtils.toErpPriceDouble(cashAmount);
        ErpB2bOrderRecDoc erpB2bOrderRecDoc = new ErpB2bOrderRecDoc();
        erpB2bOrderRecDoc.setPayType(payType);

        erpB2bOrderRecDoc.setToErpNum(1.0);
        erpB2bOrderRecDoc.setErpUserId(erpUserId);
        erpB2bOrderRecDoc.setCashTotal(0.0);
        erpB2bOrderRecDoc.setDifferenceAmount(0.0);
        erpB2bOrderRecDoc.setCreateTime(LocalDateTime.now());
        erpB2bOrderRecDoc.setUpdateTime(LocalDateTime.now());

        ErpOrderInfo erpOrderInfo1 = this.orderInfoMapper.getErpOrderInfoBySalesid(erpUserId, salesids[0]);

        if (null != erpOrderInfo1.getTranposname()) {
            String pin = PinYinUtils.toPinYin(erpOrderInfo1.getTranposname());
            tmpNo = pin + "_" + tmpNo;
        }

        erpB2bOrderRecDoc.setUserId(userId);
        erpB2bOrderRecDoc.setFinanceTrue(0);
        erpB2bOrderRecDoc.setType(TmpOrderRecDoc.EType.ERP_ORDER.val());
        erpB2bOrderRecDoc.setRefundState(RecDoc.ERefundState.NO_REFUND.val());
        this.erpB2bOrderRecDocMapper.insert(erpB2bOrderRecDoc);

        for (Long id : salesids) {
            ErpOrderInfo erpOrderInfo = this.orderInfoMapper.getErpOrderInfoBySalesid(erpUserId, id);


            erpB2bOrderRecDoc.setTranposname(erpOrderInfo.getTranposname());
            erpB2bOrderRecDoc.setTransortno(erpOrderInfo.getTransortno());
            TmpOrderRecDoc tmpOrderRecDocDB = new TmpOrderRecDoc();
            tmpOrderRecDocDB.setTotal(erpOrderInfo.getPayTotaline());
            //tmpOrderRecDocDB.setOrderNo(erpOrderInfo.getOrderNo());
            tmpOrderRecDocDB.setOrderId(erpOrderInfo.getB2bOrderId());
            tmpOrderRecDocDB.setSalesid(erpOrderInfo.getSalesid());
            tmpOrderRecDocDB.setSatypeid(erpOrderInfo.getSatypeid());
            tmpOrderRecDocDB.setSourcetype(erpOrderInfo.getSourcetype());
            tmpOrderRecDocDB.setTmpNo(tmpNo);
            tmpOrderRecDocDB.setType(TmpOrderRecDoc.EType.ERP_ORDER.val());
            tmpOrderRecDocDB.setCreateTime(LocalDateTime.now());
            tmpOrderRecDocDB.setUserId(userId);
            tmpOrderRecDocDB.setErpUserId(erpUserId);
            tmpOrderRecDocDB.setErpB2bOrderRecDocId(erpB2bOrderRecDoc.getId());
            if (payType.equals(ErpB2bOrderRecDoc.EPayType.CASH.val())) {
                tmpOrderRecDocDB.setPayTime(LocalDateTime.now());
            }
            this.tmpOrderRecDocMapper.insert(tmpOrderRecDocDB);
            total += IncaUtils.toErpPriceDouble(erpOrderInfo.getPayTotaline());
        }
        total = IncaUtils.toErpPriceDouble(total);
        // double amount = IncaUtils.toErpPriceDouble(total - cashAmount);

        //AssertExt.isTrue(cashAmount <= total, "现金金额不能大于总金额");
        erpB2bOrderRecDoc.setOnlineTotal(total);
        erpB2bOrderRecDoc.setTmpNo(tmpNo);
        this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);

        if (payType.equals(ErpB2bOrderRecDoc.EPayType.CASH.val())) {

            erpB2bOrderRecDoc.setOnlineTotal(0.0);
            erpB2bOrderRecDoc.setPayTime(LocalDateTime.now());
            erpB2bOrderRecDoc.setDifferenceAmount(IncaUtils.toErpPriceDouble(total - IncaUtils.toErpPrice(total)));
            erpB2bOrderRecDoc.setCashTotal(IncaUtils.toErpPrice(total));
            this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);
            return null;
        }

        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setOrderNo(tmpNo);
        payOrderVo.setDataSource("6");
        if (paymentType.equals("B")) {
            payOrderVo.setDataSource("2");
        }
        //payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(0.1));
        payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(total));
        payOrderVo.setBody("中天预售在线支付");
        payOrderVo.setOpenid(openid);
        payOrderVo.setSpbillCreateIp(ip);
        payOrderVo.setPaymentType(paymentType);
        PayOrder payOrder = this.payOrderService.payOrder(payOrderVo);
        return payOrder;
    }

    @Override
    public IPage<ErpB2bOrderRecDocVo> getErpB2bOrderRecDocVoList(Page page, ErpB2bOrderRecDocVo erpB2bOrderRecDocVo) {
        AssertExt.notNull(erpB2bOrderRecDocVo.getTranposnames(), "线路为空");
        IPage<ErpB2bOrderRecDocVo> erpB2bOrderRecDocVoIPage = this.erpB2bOrderRecDocMapper.getErpB2bOrderRecDocVoList(page, erpB2bOrderRecDocVo);
        erpB2bOrderRecDocVoIPage.getRecords().forEach(erpB2bOrderRecDocVo1 -> {
            erpB2bOrderRecDocVo1.setTmpErpOrderRecDocList(this.tmpOrderRecDocMapper.selectList(new QueryWrapper<TmpOrderRecDoc>().eq("ERP_B2B_ORDER_REC_DOC_ID", erpB2bOrderRecDocVo1.getId())));
        });
        return erpB2bOrderRecDocVoIPage;
    }

    @Override
    public void financeErpB2bOrderRecDocVo(Long id, String financeRemark, Long userId) {
        AssertExt.hasId(id, "id为空");
        ErpB2bOrderRecDoc erpB2bOrderRecDocDB = this.erpB2bOrderRecDocMapper.selectById(id);
        AssertExt.notNull(erpB2bOrderRecDocDB, "无效id");
        AssertExt.isFalse(erpB2bOrderRecDocDB.getFinanceTrue()==1,"该订单[%s]已核销,请勿重复核销",erpB2bOrderRecDocDB.getTmpNo());
        AssertExt.isFalse(erpB2bOrderRecDocDB.getFinanceTrue()==2,"该订单[%s]在核销中,请稍后",erpB2bOrderRecDocDB.getTmpNo());
        erpB2bOrderRecDocDB.setToErpNum(erpB2bOrderRecDocDB.getToErpNum() + 1);
        erpB2bOrderRecDocDB.setFinanceTrue(2);
        erpB2bOrderRecDocDB.setFinanceRemark(financeRemark);
        erpB2bOrderRecDocDB.setFinanceUserId(userId);
        erpB2bOrderRecDocDB.setUpdateTime(LocalDateTime.now());
        this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);
    }

    @Override
    public void updateFinanceErpB2bOrderRecDocVo(Long id, String financeRemark, Long userId, Integer financeTrue) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(financeTrue, "是否核销为空");
        ErpB2bOrderRecDoc erpB2bOrderRecDocDB = this.erpB2bOrderRecDocMapper.selectById(id);
        AssertExt.notNull(erpB2bOrderRecDocDB, "无效id");
        AssertExt.isFalse(erpB2bOrderRecDocDB.getFinanceTrue()==1,"该订单[%s]已核销,请勿重复核销",erpB2bOrderRecDocDB.getTmpNo());
        AssertExt.isFalse(erpB2bOrderRecDocDB.getFinanceTrue()==2,"该订单[%s]在核销中,请稍后",erpB2bOrderRecDocDB.getTmpNo());
        if (erpB2bOrderRecDocDB.getFinanceTrue() == 2) return;
        erpB2bOrderRecDocDB.setFinanceTrue(financeTrue);
        erpB2bOrderRecDocDB.setFinanceRemark(financeRemark);
        erpB2bOrderRecDocDB.setFinanceUserId(userId);
        erpB2bOrderRecDocDB.setUpdateTime(LocalDateTime.now());
        this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);
        if (financeTrue == 1) {
            List<TmpOrderRecDoc> tmpOrderRecDocList = this.tmpOrderRecDocMapper.selectList(new QueryWrapper<TmpOrderRecDoc>().eq("TMP_NO", erpB2bOrderRecDocDB.getTmpNo()));
            for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {

                if (null != tmpOrderRecDoc.getOrderId()) {
                    OrderInfo orderInfo = this.orderInfoMapper.selectById(tmpOrderRecDoc.getOrderId());
                    if (null != orderInfo) {
                        orderInfo.setFinanceTrue(1L);
                        this.orderInfoMapper.updateById(orderInfo);
                        OrderLog orderLog = new OrderLog();
                        orderLog.setCreateTime(LocalDateTime.now());
                        orderLog.setLogMsg("订单变更已核销");
                        orderLog.setUserId(userId);
                        orderLog.setToErpNum("v1");
                        orderLog.setOrderId(orderInfo.getId());
                        orderLog.setOrderStatus(orderInfo.getOrderState());
                        orderLog.setDoneTime(LocalDateTime.now());
                        this.orderLogMapper.insert(orderLog);

                        //修改收款单状态
                        List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()));
                        for (RecDoc recDocDB : recDocList) {
                            recDocDB.setFinanceTrue(1L);
                            this.recDocMapper.updateById(recDocDB);
                        }
                    }
                }

            }
        }
    }

    @Override
    public void updateFinanceErpB2bOrderRecDocVos(Long[] ids, String financeRemark, Long userId, Integer financeTrue) {
        AssertExt.notNull(ids, "ids为空");
        AssertExt.notNull(financeTrue, "是否核销为空");
        for (Long id : ids) {
            ErpB2bOrderRecDoc erpB2bOrderRecDocDB = this.erpB2bOrderRecDocMapper.selectById(id);
            AssertExt.notNull(erpB2bOrderRecDocDB, "无效id");
            erpB2bOrderRecDocDB.setUpdateTime(LocalDateTime.now());
            AssertExt.isFalse(erpB2bOrderRecDocDB.getFinanceTrue()==1,"该订单[%s]已核销,请勿重复核销",erpB2bOrderRecDocDB.getTmpNo());
            AssertExt.isFalse(erpB2bOrderRecDocDB.getFinanceTrue()==2,"该订单[%s]在核销中,请稍后",erpB2bOrderRecDocDB.getTmpNo());
            if (erpB2bOrderRecDocDB.getFinanceTrue() == 2) continue;
            erpB2bOrderRecDocDB.setFinanceTrue(financeTrue);
            erpB2bOrderRecDocDB.setFinanceRemark(financeRemark);
            erpB2bOrderRecDocDB.setFinanceUserId(userId);
            this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);
            if (financeTrue == 1) {
                List<TmpOrderRecDoc> tmpOrderRecDocList = this.tmpOrderRecDocMapper.selectList(new QueryWrapper<TmpOrderRecDoc>().eq("TMP_NO", erpB2bOrderRecDocDB.getTmpNo()));
                for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {

                    if (null != tmpOrderRecDoc.getOrderId()) {
                        OrderInfo orderInfo = this.orderInfoMapper.selectById(tmpOrderRecDoc.getOrderId());
                        if (null != orderInfo) {
                            orderInfo.setFinanceTrue(1L);
                            this.orderInfoMapper.updateById(orderInfo);
                            OrderLog orderLog = new OrderLog();
                            orderLog.setCreateTime(LocalDateTime.now());
                            orderLog.setLogMsg("订单变更已核销");
                            orderLog.setUserId(userId);
                            orderLog.setToErpNum("v1");
                            orderLog.setOrderId(orderInfo.getId());
                            orderLog.setOrderStatus(orderInfo.getOrderState());
                            orderLog.setDoneTime(LocalDateTime.now());
                            this.orderLogMapper.insert(orderLog);

                            //修改收款单状态
                            List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()));
                            for (RecDoc recDocDB : recDocList) {
                                recDocDB.setFinanceTrue(1L);
                                this.recDocMapper.updateById(recDocDB);
                            }
                        }
                    }

                }
            }
        }

    }

    @Override
    public void handAppFinanceErpRecDoc() {
        List<ErpB2bOrderRecDoc> erpB2bOrderRecDocVoList = this.erpB2bOrderRecDocMapper.selectList(new QueryWrapper<ErpB2bOrderRecDoc>()
                .eq("FINANCE_TRUE", 2).isNotNull("CONFIRM_TIME"));
        for (ErpB2bOrderRecDoc erpB2bOrderRecDoc : erpB2bOrderRecDocVoList) {


            FinanceVerificationDocVo financeVerificationDocVo = new FinanceVerificationDocVo();
            financeVerificationDocVo.setAmountPay(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getCashTotal() + erpB2bOrderRecDoc.getOnlineTotal()));
            financeVerificationDocVo.setAmountTotal(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getCashTotal() + erpB2bOrderRecDoc.getOnlineTotal()));
            financeVerificationDocVo.setCustomId(erpB2bOrderRecDoc.getErpUserId());
            financeVerificationDocVo.setOrderId(erpB2bOrderRecDoc.getId());

            String orderNo = erpB2bOrderRecDoc.getTmpNo();
            financeVerificationDocVo.setOrderNo(orderNo);
            financeVerificationDocVo.setCreateDate(erpB2bOrderRecDoc.getCreateTime());
            financeVerificationDocVo.setSettleMethod("CASH");
            financeVerificationDocVo.setEntryId(1);

            User user = this.userMapper.selectById(erpB2bOrderRecDoc.getUserId());
            financeVerificationDocVo.setInputManId(user.getErpEmployeeId());

            User userConfirmManId = this.userMapper.selectById(erpB2bOrderRecDoc.getConfirmUserId());
            financeVerificationDocVo.setConfirmManId(userConfirmManId.getErpEmployeeId());
            financeVerificationDocVo.setAmountDiscrepancy(erpB2bOrderRecDoc.getDifferenceAmount());
            financeVerificationDocVo.setPayFlowNo(erpB2bOrderRecDoc.getPayFlowNo());
            // financeVerificationDocVo.setRecDocNo(recDocDB.getRecDocNo());
            financeVerificationDocVo.setPayDate(erpB2bOrderRecDoc.getPayTime());
            // financeVerificationDocVo.setPayType(orderInfo.getPayType());
            financeVerificationDocVo.setBankId(44147L);
            financeVerificationDocVo.setAmountVerification(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getOnlineTotal()));
            if (erpB2bOrderRecDoc.getPayType().equals(ErpB2bOrderRecDoc.EPayType.CASH.val())) {
                financeVerificationDocVo.setBankId(0L);
                financeVerificationDocVo.setAmountVerification(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getCashTotal()));
            }
            financeVerificationDocVo.setPayType(erpB2bOrderRecDoc.getPayType());
            financeVerificationDocVo.setPayFlowNo(erpB2bOrderRecDoc.getPayFlowNo());
            financeVerificationDocVo.setVerificationDate(erpB2bOrderRecDoc.getConfirmTime());
            financeVerificationDocVo.setRemark(erpB2bOrderRecDoc.getConfirmRemark());


            financeVerificationDocVo.setVersion("v." + erpB2bOrderRecDoc.getToErpNum());


            List<FinanceVerificationDtlVo> financeVerificationDtlVoList = new ArrayList<>();

            //if(orderInfo.getFinanceTrue()==1)return;
            financeVerificationDocVo.setOrderType(1);
            financeVerificationDocVo.setVerificationType(2);
            List<TmpOrderRecDoc> tmpOrderRecDocList = this.tmpOrderRecDocMapper.selectList(new QueryWrapper<TmpOrderRecDoc>().eq("ERP_B2B_ORDER_REC_DOC_ID", erpB2bOrderRecDoc.getId()));

            for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {


                FinanceVerificationDtlVo financeVerificationDtlVo = new FinanceVerificationDtlVo();
                financeVerificationDtlVo.setNum(0.0);
                financeVerificationDtlVo.setAmount(IncaUtils.toErpPriceDouble4(tmpOrderRecDoc.getTotal()));
                financeVerificationDtlVo.setGoodsId(0L);
                financeVerificationDtlVo.setSrcOrderId(erpB2bOrderRecDoc.getId().toString());
                // financeVerificationDtlVo.setSrcOrderDtlId(tmpOrderRecDoc.getId().toString());
                financeVerificationDtlVo.setSrcErpOrderId(Long.valueOf(tmpOrderRecDoc.getSalesid()));
                // financeVerificationDtlVo.setSrcErpOrderDtlId(Long.valueOf(tmpOrderRecDoc.getSalesid()));
                financeVerificationDtlVo.setOrderId(erpB2bOrderRecDoc.getId().toString());
                // financeVerificationDtlVo.setOrderDtlId(tmpOrderRecDoc.getId().toString());
                financeVerificationDtlVo.setStorageId(0);
                financeVerificationDtlVo.setSourceType(tmpOrderRecDoc.getSourcetype());
                financeVerificationDtlVoList.add(financeVerificationDtlVo);

            }

            financeVerificationDocVo.setFinanceVerificationDtlVoList(financeVerificationDtlVoList);


            try {
                //FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verification(financeVerificationDocVo);
                //FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV2(financeVerificationDocVo);
                FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV3(financeVerificationDocVo);
                if (financeVerificationResultDocVo.getErrorCode() != 0) {
                    erpB2bOrderRecDoc.setFinanceTrue(3);
                    erpB2bOrderRecDoc.setUpdateTime(LocalDateTime.now());
                    erpB2bOrderRecDoc.setFinanceTime(LocalDateTime.now());
                    erpB2bOrderRecDoc.setErpFinanceRemark(financeVerificationResultDocVo.getErrorMessage());
                    this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);
                } else {
                    erpB2bOrderRecDoc.setUpdateTime(LocalDateTime.now());
                    erpB2bOrderRecDoc.setFinanceTrue(1);
                    erpB2bOrderRecDoc.setErpFinanceRemark("核销成功");
                    for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {

                        if (null != tmpOrderRecDoc.getOrderId()) {
                            OrderInfo orderInfo = this.orderInfoMapper.selectById(tmpOrderRecDoc.getOrderId());
                            if (null != orderInfo) {
                                orderInfo.setFinanceTrue(1L);
                                orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                                this.orderInfoMapper.updateById(orderInfo);
                                OrderLog orderLog = new OrderLog();
                                orderLog.setCreateTime(LocalDateTime.now());
                                orderLog.setLogMsg("订单核销;线下单核销");
                                orderLog.setOrderId(orderInfo.getId());
                                orderLog.setToErpNum("v." + erpB2bOrderRecDoc.getToErpNum());
                                orderLog.setOrderId(orderInfo.getId());
                                orderLog.setOrderStatus(orderInfo.getOrderState());
                                orderLog.setDoneTime(LocalDateTime.now());
                                this.orderLogMapper.insert(orderLog);
                                //修改收款单状态
                                List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()));
                                for (RecDoc recDocDB : recDocList) {
                                    recDocDB.setFinanceTrue(1L);
                                    recDocDB.setErpFinanceRemark("核销成功");
                                    this.recDocMapper.updateById(recDocDB);
                                }
                            }
                        }
                    }
                    this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);
                }

            } catch (RuntimeException e) {
                log.error("StateMentService.handAppFinanceErpRecDoc error ", e);
                erpB2bOrderRecDoc.setUpdateTime(LocalDateTime.now());
                erpB2bOrderRecDoc.setFinanceTrue(3);
                erpB2bOrderRecDoc.setFinanceTime(LocalDateTime.now());
                erpB2bOrderRecDoc.setErpFinanceRemark(e.getMessage());
                this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);
            }

        }
    }

    @Override
    public void refundErpB2bOrderRecDoc(Long id, Long userId, String refundRemark) {
        AssertExt.hasId(id, "id为空");
        //AssertExt.notNull(refundRemark, "备注为空");
        ErpB2bOrderRecDoc erpB2bOrderRecDocDB = this.erpB2bOrderRecDocMapper.selectById(id);
        AssertExt.notNull(erpB2bOrderRecDocDB, "无效id【%s】", id);

        AssertExt.isTrue(erpB2bOrderRecDocDB.getRefundState().equals(ErpB2bOrderRecDoc.ERefundState.NO_REFUND.val()) || erpB2bOrderRecDocDB.getRefundState().equals(ErpB2bOrderRecDoc.ERefundState.REFUND_FAIL.val()), "只有未退款和退款失败才能发起退款");
        if (erpB2bOrderRecDocDB.getOnlineTotal() > 0) {
            String num = "TK_" + DATE_TIME_FORMATTER.format(LocalDateTime.now());
            PayOrderVo payOrder = new PayOrderVo();
            erpB2bOrderRecDocDB.setUpdateTime(LocalDateTime.now());
            try {
                payOrder = this.payOrderService.refund(erpB2bOrderRecDocDB.getTmpNo(), num, (int) (erpB2bOrderRecDocDB.getOnlineTotal() * 100));
                double amount = (double) payOrder.getRefundFee() / 100;
                erpB2bOrderRecDocDB.setRefundMoney(amount);
                erpB2bOrderRecDocDB.setPayRefundNo(payOrder.getRefundFlowNo());
                erpB2bOrderRecDocDB.setRefundRemark(refundRemark);
                erpB2bOrderRecDocDB.setRefundUserId(userId);
                erpB2bOrderRecDocDB.setRefundTime(LocalDateTime.now());
                erpB2bOrderRecDocDB.setAbcMessage(payOrder.toString());
                erpB2bOrderRecDocDB.setRefundState(RecDoc.ERefundState.REFUND_SUCCESS.val());
                erpB2bOrderRecDocDB.setPayAbcNo(erpB2bOrderRecDocDB.getTmpNo() + "_" + num);
                this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);

            } catch (RuntimeException e) {
                log.error("StateMentService.refundErpB2bOrderRecDoc error ", e);
                erpB2bOrderRecDocDB.setRefundState(RecDoc.ERefundState.REFUND_FAIL.val());
                this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);
            }
        }

    }

    @Override
    public void confirmErpB2bOrderRecDoc(Long id, Long userId, String confirmRemark,LocalDateTime confirmTime) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(confirmTime,"确认时间为空");
        //AssertExt.notNull(confirmRemark, "备注为空");
        ErpB2bOrderRecDoc erpB2bOrderRecDocDB = this.erpB2bOrderRecDocMapper.selectById(id);
        AssertExt.notNull(erpB2bOrderRecDocDB, "无效id【%s】", id);
        AssertExt.isTrue(null==erpB2bOrderRecDocDB.getConfirmTime(),"该订单[%s]已确认收款,请勿重复确认",erpB2bOrderRecDocDB.getTmpNo());
        erpB2bOrderRecDocDB.setUpdateTime(LocalDateTime.now());
        erpB2bOrderRecDocDB.setConfirmRemark(confirmRemark);
        erpB2bOrderRecDocDB.setConfirmTime(confirmTime);
        erpB2bOrderRecDocDB.setConfirmUserId(userId);
        this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);

    }

    @Override
    public void confirmErpB2bOrderRecDocList(Long[] ids, Long userId, String confirmRemark,LocalDateTime confirmTime) {
        AssertExt.notNull(ids, "ids为空");
        AssertExt.notNull(confirmTime,"确认时间为空");
        for (Long id : ids) {
            ErpB2bOrderRecDoc erpB2bOrderRecDocDB = this.erpB2bOrderRecDocMapper.selectById(id);
            AssertExt.notNull(erpB2bOrderRecDocDB, "无效id【%s】", id);
            AssertExt.isTrue(null==erpB2bOrderRecDocDB.getConfirmTime(),"该订单[%s]已确认收款,请勿重复确认",erpB2bOrderRecDocDB.getTmpNo());
            erpB2bOrderRecDocDB.setUpdateTime(LocalDateTime.now());
            erpB2bOrderRecDocDB.setConfirmRemark(confirmRemark);
            erpB2bOrderRecDocDB.setConfirmTime(confirmTime);
            erpB2bOrderRecDocDB.setConfirmUserId(userId);
            this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);
        }
    }

    @Override
    public void confirmB2bOrderRecDocList(Long[] ids, Long userId, String confirmRemark, LocalDateTime confirmTime) {
        AssertExt.notNull(ids, "ids为空");
        AssertExt.notNull(confirmTime,"确认时间为空");
        for (Long id : ids) {
            RecDoc recDoc = this.recDocMapper.selectById(id);
            AssertExt.notNull(recDoc, "无效id【%s】", id);
            AssertExt.isTrue(null==recDoc.getConfirmDate(),"该订单[%s]已确认,请勿重复确认",recDoc.getRecDocNo());
            recDoc.setConfirmRemark(confirmRemark);
            recDoc.setConfirmDate(confirmTime);
            recDoc.setConfirmUserId(userId);
            this.recDocMapper.updateById(recDoc);

            this.refundRecDocOrder(id,confirmRemark,userId);

            log.info("111111111111111111{}",id);

        }
    }

    @Override
    public void delErpB2bOrderRecDoc(Long id, Long userId, String delRemark) {
        AssertExt.hasId(id, "id为空");
        ErpB2bOrderRecDoc erpB2bOrderRecDocDB = this.erpB2bOrderRecDocMapper.selectById(id);
        AssertExt.notNull(erpB2bOrderRecDocDB, "无效id【%s】", id);
        AssertExt.isTrue(erpB2bOrderRecDocDB.getPayType().equals(ErpB2bOrderRecDoc.EPayType.CASH.val()), "只有现金付款才能撤销");
        erpB2bOrderRecDocDB.setIsDel(Constant.IS_DEL.YES);
        erpB2bOrderRecDocDB.setDelTime(LocalDateTime.now());
        erpB2bOrderRecDocDB.setDelUserId(userId);
        erpB2bOrderRecDocDB.setDelRemark(delRemark);
        erpB2bOrderRecDocDB.setUpdateTime(LocalDateTime.now());
        this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDocDB);
        List<TmpOrderRecDoc> tmpOrderRecDocList = this.tmpOrderRecDocMapper.selectList(new QueryWrapper<TmpOrderRecDoc>().eq("ERP_B2B_ORDER_REC_DOC_ID", id));
        for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {
            tmpOrderRecDoc.setIsDel(Constant.IS_DEL.YES);
            this.tmpOrderRecDocMapper.updateById(tmpOrderRecDoc);
        }
    }

    @Override
    public IPage<TmpOrderRecDocVo> getTmpOrderRecDocList(Page page, TmpOrderRecDocVo tmpOrderRecDoc) {
        Member member = this.memberMapper.selectById(tmpOrderRecDoc.getErpUserId());
        tmpOrderRecDoc.setErpUserId(member.getErpUserId());
        return this.tmpOrderRecDocMapper.getTmpOrderRecDocList(page, tmpOrderRecDoc);
    }

    @Override
    public IPage<TmpOrderRecDocVo> getAdminTmpOrderRecDocList(Page page, TmpOrderRecDocVo tmpOrderRecDoc) {
        return this.tmpOrderRecDocMapper.getTmpOrderRecDocList(page, tmpOrderRecDoc);
    }

    @Override
    public void autoAppFinanceOrder() {
        String finance = this.settingService.genPCMINTime("APP_AUTO_FINANCE").getValue();
        if (!finance.equals("1")) return;
        List<ErpB2bOrderRecDoc> erpB2bOrderRecDocVoList = this.erpB2bOrderRecDocMapper.selectList(new QueryWrapper<ErpB2bOrderRecDoc>()
                .eq("FINANCE_TRUE", 0).isNotNull("CONFIRM_TIME"));
        for (ErpB2bOrderRecDoc erpB2bOrderRecDoc : erpB2bOrderRecDocVoList) {


            FinanceVerificationDocVo financeVerificationDocVo = new FinanceVerificationDocVo();
            financeVerificationDocVo.setAmountPay(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getCashTotal() + erpB2bOrderRecDoc.getOnlineTotal()));
            financeVerificationDocVo.setAmountTotal(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getCashTotal() + erpB2bOrderRecDoc.getOnlineTotal()));
            financeVerificationDocVo.setCustomId(erpB2bOrderRecDoc.getErpUserId());
            financeVerificationDocVo.setOrderId(erpB2bOrderRecDoc.getId());

            String orderNo = erpB2bOrderRecDoc.getTmpNo();
            financeVerificationDocVo.setOrderNo(orderNo);
            financeVerificationDocVo.setCreateDate(erpB2bOrderRecDoc.getCreateTime());
            financeVerificationDocVo.setSettleMethod("CASH");
            financeVerificationDocVo.setEntryId(1);
            User user = this.userMapper.selectById(erpB2bOrderRecDoc.getUserId());
            financeVerificationDocVo.setInputManId(user.getErpEmployeeId());

            User userConfirmManId = this.userMapper.selectById(erpB2bOrderRecDoc.getConfirmUserId());
            financeVerificationDocVo.setConfirmManId(userConfirmManId.getErpEmployeeId());

            financeVerificationDocVo.setAmountDiscrepancy(erpB2bOrderRecDoc.getDifferenceAmount());
            financeVerificationDocVo.setPayFlowNo(erpB2bOrderRecDoc.getPayFlowNo());
            // financeVerificationDocVo.setRecDocNo(recDocDB.getRecDocNo());
            financeVerificationDocVo.setPayDate(erpB2bOrderRecDoc.getPayTime());
            // financeVerificationDocVo.setPayType(orderInfo.getPayType());
            financeVerificationDocVo.setBankId(44147L);
            financeVerificationDocVo.setAmountVerification(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getOnlineTotal()));
            if (erpB2bOrderRecDoc.getPayType().equals(ErpB2bOrderRecDoc.EPayType.CASH.val())) {

                financeVerificationDocVo.setBankId(0L);
                financeVerificationDocVo.setAmountVerification(IncaUtils.toErpPriceDouble4(erpB2bOrderRecDoc.getCashTotal()));
            }
            financeVerificationDocVo.setPayType(erpB2bOrderRecDoc.getPayType());
            financeVerificationDocVo.setPayFlowNo(erpB2bOrderRecDoc.getPayFlowNo());
            financeVerificationDocVo.setVerificationDate(erpB2bOrderRecDoc.getConfirmTime());
            financeVerificationDocVo.setRemark(erpB2bOrderRecDoc.getConfirmRemark());


            financeVerificationDocVo.setVersion("v." + erpB2bOrderRecDoc.getToErpNum());


            List<FinanceVerificationDtlVo> financeVerificationDtlVoList = new ArrayList<>();

            //if(orderInfo.getFinanceTrue()==1)return;
            financeVerificationDocVo.setOrderType(1);
            financeVerificationDocVo.setVerificationType(2);
            List<TmpOrderRecDoc> tmpOrderRecDocList = this.tmpOrderRecDocMapper.selectList(new QueryWrapper<TmpOrderRecDoc>().eq("ERP_B2B_ORDER_REC_DOC_ID", erpB2bOrderRecDoc.getId()));

            for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {


                FinanceVerificationDtlVo financeVerificationDtlVo = new FinanceVerificationDtlVo();
                financeVerificationDtlVo.setNum(0.0);
                financeVerificationDtlVo.setAmount(IncaUtils.toErpPriceDouble4(tmpOrderRecDoc.getTotal()));
                financeVerificationDtlVo.setGoodsId(0L);
                financeVerificationDtlVo.setSrcOrderId(erpB2bOrderRecDoc.getId().toString());
                // financeVerificationDtlVo.setSrcOrderDtlId(tmpOrderRecDoc.getId().toString());
                financeVerificationDtlVo.setSrcErpOrderId(Long.valueOf(tmpOrderRecDoc.getSalesid()));
                // financeVerificationDtlVo.setSrcErpOrderDtlId(Long.valueOf(tmpOrderRecDoc.getSalesid()));
                financeVerificationDtlVo.setOrderId(erpB2bOrderRecDoc.getId().toString());
                // financeVerificationDtlVo.setOrderDtlId(tmpOrderRecDoc.getId().toString());
                financeVerificationDtlVo.setStorageId(0);
                financeVerificationDtlVo.setSourceType(tmpOrderRecDoc.getSourcetype());
                financeVerificationDtlVoList.add(financeVerificationDtlVo);

            }

            financeVerificationDocVo.setFinanceVerificationDtlVoList(financeVerificationDtlVoList);
            erpB2bOrderRecDoc.setUpdateTime(LocalDateTime.now());

            try {
                //FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verification(financeVerificationDocVo);
                //FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV2(financeVerificationDocVo);
                FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV3(financeVerificationDocVo);
                if (financeVerificationResultDocVo.getErrorCode() != 0) {
                    erpB2bOrderRecDoc.setFinanceTrue(3);
                    erpB2bOrderRecDoc.setFinanceTime(LocalDateTime.now());
                    erpB2bOrderRecDoc.setErpFinanceRemark(financeVerificationResultDocVo.getErrorMessage());
                    this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);
                } else {
                    erpB2bOrderRecDoc.setFinanceTrue(1);
                    erpB2bOrderRecDoc.setErpFinanceRemark("核销成功");
                    for (TmpOrderRecDoc tmpOrderRecDoc : tmpOrderRecDocList) {

                        if (null != tmpOrderRecDoc.getOrderId()) {
                            OrderInfo orderInfo = this.orderInfoMapper.selectById(tmpOrderRecDoc.getOrderId());
                            if (null != orderInfo) {
                                orderInfo.setFinanceTrue(1L);
                                orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                                this.orderInfoMapper.updateById(orderInfo);
                                OrderLog orderLog = new OrderLog();
                                orderLog.setCreateTime(LocalDateTime.now());
                                orderLog.setLogMsg("订单核销;线下单核销");
                                orderLog.setOrderId(orderInfo.getId());
                                orderLog.setToErpNum("v." + erpB2bOrderRecDoc.getToErpNum());
                                orderLog.setOrderId(orderInfo.getId());
                                orderLog.setOrderStatus(orderInfo.getOrderState());
                                orderLog.setDoneTime(LocalDateTime.now());
                                this.orderLogMapper.insert(orderLog);
                                //修改收款单状态
                                List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()));
                                for (RecDoc recDocDB : recDocList) {
                                    recDocDB.setFinanceTrue(1L);
                                    recDocDB.setErpFinanceRemark("核销成功");
                                    this.recDocMapper.updateById(recDocDB);
                                }
                            }
                        }
                    }
                    this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);
                }

            } catch (RuntimeException e) {
                log.error("StateMentService.handAppFinanceErpRecDoc error ", e);
                erpB2bOrderRecDoc.setFinanceTrue(3);
                erpB2bOrderRecDoc.setFinanceTime(LocalDateTime.now());
                erpB2bOrderRecDoc.setErpFinanceRemark(e.getMessage());
                this.erpB2bOrderRecDocMapper.updateById(erpB2bOrderRecDoc);
            }

        }
    }

    @Override
    public void updateRecDocRefundState(Long id, Long userId, String refundRemark, String refundState) {
        AssertExt.hasId(id, "id为空");
        //AssertExt.notNull(refundRemark, "备注为空");
        RecDoc recDocDB = this.recDocMapper.selectById(id);
        AssertExt.notNull(recDocDB, "无效id【%s】", id);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setOrderId(recDocDB.getOrderId());
        orderLog.setLogMsg("修改退款状态");
        orderLog.setOrderStatus(refundState);
        orderLog.setDoneTime(LocalDateTime.now());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);

        //修改收款单状态
        recDocDB.setRefundState(refundState);
        recDocDB.setRefundRemark(refundRemark);
        recDocDB.setRefundUserId(userId);
        this.recDocMapper.updateById(recDocDB);


    }


}
