package com.zsyc.zt.b2b.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esotericsoftware.minlog.Log;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.framework.util.SnowFlakeIdWorker;
import com.zsyc.invoice.service.InvoiceService;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.service.PayOrderService;
import com.zsyc.pay.vo.PayOrderVo;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.common.IncaUtils;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.b2b.vo.CustomerVo;
import com.zsyc.zt.fs.service.B2BFileService;
import com.zsyc.zt.inca.entity.CustomTransVo;
import com.zsyc.zt.inca.service.*;
import com.zsyc.zt.inca.service.GoodsService;
import com.zsyc.zt.inca.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by tang on 2020/7/30.
 */
//@Service(timeout = 100_000)
//@Transactional
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
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
    @Autowired
    @Qualifier("orderIdWorker")
    private SnowFlakeIdWorker snowFlakeIdWorker;
    @Autowired
    @Qualifier("refundReturnWorker")
    private SnowFlakeIdWorker refundReturnWorker;

    @Autowired
    @Qualifier("payWorker")
    private SnowFlakeIdWorker payFlakeIdWorker;

    @Autowired
    private RefundReturnMapper refundReturnMapper;
    @Autowired
    private RefundDetailMapper refundDetailMapper;

    //TODO erp服务单独版本参考这个
    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private OrderService orderService;
    @Reference
    private CustomService customService;
    @Reference
    private GoodsService goodsService;
    @Autowired
    private SettingMapper settingMapper;
    @Reference
    private DeliveryAmountService deliveryAmountService;
    @Autowired
    private B2BFileService fileService;
    @Reference
    private com.zsyc.zt.b2b.service.GoodsService goodsServiceB2b;

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityGoodsMapper activityGoodsMapper;
    @Autowired
    private ActivityStrategyMapper activityStrategyMapper;
    @Autowired
    private ActivityGiftMapper activityGiftMapper;
    @Autowired
    private ActivitySetMapper activitySetMapper;
    @Autowired
    private ActivityOrderMapper activityOrderMapper;
    @Reference
    private SettingService settingService;
    @Autowired
    private CouponReceiveMapper couponReceiveMapper;

    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private CouponLogMapper couponLogMapper;

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartGiftTmpMapper cartGiftTmpMapper;
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;

    @Autowired(required = false)
    private PayOrderService payOrderService;

    @Autowired
    private RecDocMapper recDocMapper;

    @Autowired
    private RecDtlMapper recDtlMapper;

    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private FinanceService financeService;

    @Autowired
    private InvoiceService invoiceService;

    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private FapiaoService fapiaoService;

    @Resource
    RedissonClient redissonClient;

    @Autowired
    private InOrderDateMapper inOrderDateMapper;

    @Autowired
    private ManuallyInterceptMapper manuallyInterceptMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    @Qualifier("recDocReturnWorker")
    private SnowFlakeIdWorker recDocReturnWorker;

    @Reference
    private LoginService loginService;

    @Autowired
    private MemberLogMapper memberLogMapper;

    @Autowired
    private LotterDailQualificationsMapper lotterDailQualificationsMapper;
    @Autowired
    private LotteryLogMapper lotteryLogMapper;
    @Autowired
    private LotteryDialDetailMapper lotteryDialDetailMapper;

    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private TransControlService transControlService;

    @Autowired
    private UserMapper userMapper;

    private final static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter DATE_TIME_MONTH = DateTimeFormatter.ofPattern("yyyy-MM");

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 下单校验
     * <p>
     * 1，客户销售状态
     * 2，起送金额，省市区设置金额
     * 3，是否超过信用天数
     * 4，经营范围校验，证件是否过期
     * 5，库存是否足够
     * 6，路线是否为空--默认路线
     * 7，客服是否为空--默认客服
     */

    @Transactional
    @Override
    //@Async
    public OrderInfoVo memberPayOrder(OrderInfoVo orderInfo) {
        AssertExt.notNull(orderInfo.getAddressDetail(), "地址为空");
        AssertExt.notNull(orderInfo.getOrderFrom(), "订单来源为空");
        Member memberDB = this.memberMapper.selectById(orderInfo.getMemberId());
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        CustomerVo pubCustomerDB = this.memberMapper.getPhone(memberDB.getErpUserId());
        //客户销售状态
        AssertExt.isTrue(pubCustomerDB.getSausestatus().equals("可销售"), "不可销售状态，请联系客服了解详情");
        AssertExt.isTrue(pubCustomerDB.getGspusestatus() == 1, "禁止登录");
        //证件是否过期
        // this.getMemberLicenseAll(pubCustomerDB);
        ResultVo customLicense = this.customService.valid(memberDB.getErpUserId(), 1);
        log.info("证件是否过期customLicense--{}", customLicense);
        if (customLicense.getErrorCode() != 0) {
            AssertExt.fail(customLicense.getErrorMessage());
        }
        //是否超过信用天数
        ResultVo resultVo = this.customService.validCredit(memberDB.getErpUserId(), 1);
        log.info("是否超过信用天数resultVo--{}", resultVo);
        if (resultVo.getErrorCode() != 0) {
            AssertExt.fail(resultVo.getErrorMessage());
        }
        List<CartVo> cartVoList = this.cartMapper.getPitchOnList(orderInfo.getMemberId(), Constant.IS_PITCH_ON.ON, null, 0);
        if (cartVoList.size() == 0) return null;
        RLock lock = redissonClient.getLock("b2b_order_gen:" + memberDB.getErpUserId());
        /**
         * 获取锁资源
         */
        lock.lock(2, TimeUnit.MINUTES);

        try {
            //AssertExt.notEmpty(orderInfo.getOrderGoodsList(), "商品信息为空");

            orderInfo.setFpStatus(OrderInfo.EFpStatus.OFF.val());
            orderInfo.setErpCustomerId(memberDB.getErpUserId());

            if(!orderInfo.getOrderFrom().equals(4)){
                Long time = this.settingService.getOrderScore("ORDER_TIME");
                //Integer orderNum = this.orderInfoMapper.getOrderInfoByCustomerId(memberDB.getErpUserId(), time);
                Integer orderNum = this.inOrderDateMapper.getOrderInfoByCustomerId(memberDB.getErpUserId(), time, LocalDateTime.now().minusMinutes(time), LocalDateTime.now());
                if (orderNum > 0) return null;
            }


            String orderNo = this.snowFlakeIdWorker.genNo();

            InOrderDate inOrderDate = new InOrderDate();
            inOrderDate.setOrderNo(orderNo);
            inOrderDate.setMemberId(memberDB.getId());
            inOrderDate.setErpCustomerId(memberDB.getErpUserId());
            inOrderDate.setCreateTime(LocalDateTime.now());
            this.inOrderDateMapper.insert(inOrderDate);




       /* List<CartVo> cartVoList5 = this.cartMapper.getPitchOnList(orderInfo.getMemberId(), Constant.IS_PITCH_ON.ON, 5L, 0);
        List<CartVo> cartVoList9 = this.cartMapper.getPitchOnList(orderInfo.getMemberId(), Constant.IS_PITCH_ON.ON, 691L, 9);
        cartVoList.addAll(cartVoList5);
        cartVoList.addAll(cartVoList9);*/
            List<Long> longList = cartVoList.stream().map(orderGoodsVo -> {
                return orderGoodsVo.getErpGoodsId();
            }).collect(Collectors.toList());

            //经营类别校验
            ResultVo resultVo1 = this.goodsService.valid(memberDB.getErpUserId(), 1, longList);
            log.info("经营类别校验resultVo1--{}", resultVo1);
            if (resultVo1.getErrorCode() != 0) {
                AssertExt.fail(resultVo1.getErrorMessage());
            }


            log.info("生成订单---------业务逻辑----------");

            orderInfo.setOrderNo(orderNo);
            orderInfo.setCreateTime(LocalDateTime.now());
            orderInfo.setInterceptStatus(OrderInfo.EInterceptStatus.NORMAL.val());
            orderInfo.setOrderState(OrderInfo.EOrderState.TO_ERP.val());
            orderInfo.setRefundAmount(0L);

            if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val()) || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ABC_LINE.val())
                    || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.WX_LINE.val())
                    || orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ZFB_LINE.val())) {
                orderInfo.setOrderState(OrderInfo.EOrderState.UNPAID.val());
            }


            transactionTemplate.execute(item -> {

                orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                orderInfo.setOrderType(OrderInfo.EOrderType.COMMON.val());
                orderInfo.setEvaluationAgainState(OrderInfo.EEvaluationAgainState.UN_EVALUATION.val());
                orderInfo.setEvaluationState(OrderInfo.EEvaluationState.UN_EVALUATION.val());
                orderInfo.setLockState(OrderInfo.ELockState.NORMAL.val());
                orderInfo.setRefundState(OrderInfo.ERefundState.NO_REFUND.val());
                this.orderInfoMapper.insert(orderInfo);
                //orderInfo.setId(1L);


                return item;
            });


            log.info("orderInfo-----------------------------{}", orderInfo);
            // this.activityGoodsOrder(orderInfo);
            this.getCartUseCoupon(orderInfo);

            if (!orderInfo.getOrderFrom().equals(3)) {
                //起送金额，省市区设置金额
                Long deliveryAmount = this.deliveryAmountService.getDeliveryAmountByCustomerId(orderInfo.getMemberId());
                Integer addOrder = this.addOrderToDay(orderInfo.getMemberId());
                if (addOrder != 1) {
                    AssertExt.isTrue(deliveryAmount <= orderInfo.getGoodsAmount(), "起送金额为[%s]", deliveryAmount);
                }
            }


            transactionTemplate.execute(item -> {
                this.cartGiftTmpMapper.delMyCartGiftTmp(orderInfo.getMemberId());

                orderLog.setLogMsg("客户下单");
                orderLog.setOrderId(orderInfo.getId());
                orderLog.setToErpNum("v1");
                orderLog.setDoneTime(LocalDateTime.now());
                orderLog.setOrderStatus(orderInfo.getOrderState());
                this.orderLogMapper.insert(orderLog);

                this.cartMapper.deleteBatchIds(cartVoList.stream().map(Cart::getId).collect(Collectors.toList()));
                //删除购物车商品
                //this.cartMapper.delCartPitchOnGoods(Constant.IS_PITCH_ON.ON, memberDB.getId());
                //生成收款单
                this.addRecDocDtlOrder(orderInfo);
                List<LotterDailQualifications> lotterDailQualificationsList = this.lotterDailQualificationsMapper.selectList(new QueryWrapper<LotterDailQualifications>().eq("member_id", orderInfo.getMemberId()).eq("status", LotterDailQualifications.EStatus.TMP.val()));
                if (lotterDailQualificationsList.size() > 0) {
                    for (LotterDailQualifications lotterDailQualifications : lotterDailQualificationsList) {
                        lotterDailQualifications.setOrderId(orderInfo.getId());
                        lotterDailQualifications.setStatus(LotterDailQualifications.EStatus.ORDER.val());
                        this.lotterDailQualificationsMapper.updateById(lotterDailQualifications);
                        LotteryLog lotteryLog = new LotteryLog();
                        lotteryLog.setLotId(lotterDailQualifications.getLotId());
                        lotteryLog.setMemberId(lotterDailQualifications.getMemberId());
                        lotteryLog.setRemark("下单赠送抽奖次数");
                        lotteryLog.setCreateTime(LocalDateTime.now());
                        this.lotteryLogMapper.insert(lotteryLog);
                    }

                }
                return item;
            });


        } catch (Throwable e) {
            log.error("执行出现了异常!", e);
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);
        } finally {
            log.info("订单下发程序---------解锁----------");
            lock.unlock();
        }

        return orderInfo;
    }

    @Override
    public Integer addOrderToDay(Long memberId) {
        String addOrderToday = this.settingService.getOneOrderPay("ADD_ORDER");
        Long deliveryAmount = this.deliveryAmountService.getDeliveryAmountByCustomerId(memberId);
        if (null != addOrderToday) {
            String times = DATE_TIME.format(LocalDateTime.now()) + " " + addOrderToday;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(times, dateTimeFormatter);

            if (this.orderInfoMapper.getAddOrderToDayAmount(memberId, deliveryAmount) >= 1) {
                log.info("this.orderInfoMapper.getAddOrderToDayAmount(memberId){}", this.orderInfoMapper.getAddOrderToDayAmount(memberId, deliveryAmount));
                if (localDateTime.isAfter(LocalDateTime.now())) {
                    return this.orderInfoMapper.getAddOrderToDay(memberId, deliveryAmount);
                }
            }
        }
        return 1;
    }

    //生成收款单
    private void addRecDocDtlOrder(OrderInfoVo orderInfo) {
        List<OrderGoods> orderGoodsList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfo.getId()));
        RecDoc recDoc = new RecDoc();
        recDoc.setEntryId(1L);
        recDoc.setMemberId(orderInfo.getMemberId());
        recDoc.setOrderId(orderInfo.getId());
        recDoc.setStatus(RecDoc.EStatus.TO_PAY.val());
        recDoc.setRecMethod(RecDoc.ERecMethod.PAY_ORDER.val());
        recDoc.setRecType(orderInfo.getPayMethod());
        recDoc.setTotal(orderInfo.getGoodsAmount());
        recDoc.setDtlLines(orderGoodsList.size());
        recDoc.setPremoney(orderInfo.getGoodsAmount());
        recDoc.setRemark(orderInfo.getRemark());
        recDoc.setCreateTime(LocalDateTime.now());
        recDoc.setRecDocNo(this.recDocReturnWorker.genNo());
        recDoc.setRefundState(RecDoc.ERefundState.NO_REFUND.val());
        this.recDocMapper.insert(recDoc);

        for (OrderGoods orderGoods : orderGoodsList) {
            RecDtl recDtl = new RecDtl();
            recDtl.setGoodsId(orderGoods.getErpGoodsId());
            recDtl.setGoodsQty(orderGoods.getGoodsNum());
            recDtl.setUntPrice(orderGoods.getGoodsPayPrice());
            recDtl.setTotalLine(orderGoods.getAmountPay());
            recDtl.setErpLeastsaleqty(orderGoods.getErpLeastsaleqty());
            recDtl.setSaRecId(recDoc.getId());
            recDtl.setCreateTime(LocalDateTime.now());
            this.recDtlMapper.insert(recDtl);
        }

    }

    //赠送优惠券
    private void addCouponReceive(OrderInfoVo orderInfo, Long couponId, Long activityId, Long asId) {
        Integer memberCouponNum = this.couponReceiveMapper.selectCount(new QueryWrapper<CouponReceive>().eq("COUPON_ID", couponId).eq("MEMBER_ID", orderInfo.getErpCustomerId()));
        Coupon coupon = this.couponMapper.selectById(couponId);
        AssertExt.notNull(coupon, "优惠券id[%s]无效", couponId);
        if (memberCouponNum > 0) {
            if (memberCouponNum > coupon.getLimitNum()) return;
        }
        CouponReceive couponReceive = new CouponReceive();
        couponReceive.setMemberId(orderInfo.getErpCustomerId());
        couponReceive.setCouponId(couponId);
        couponReceive.setCouponCode(orderInfo.getOrderNo());
        couponReceive.setStatus(CouponReceive.EStatus.TO_RECEIVE.val());
        couponReceive.setIsDel(Constant.IS_DEL.NO);
        couponReceive.setSource(CouponReceive.ESource.ORDER.val());
        couponReceive.setCreateTime(LocalDateTime.now());
        couponReceive.setOrderId(orderInfo.getId());
        this.couponReceiveMapper.insert(couponReceive);
        this.addActivityOrder(activityId, orderInfo.getId(), asId);
        // 赠送成功减少优惠券 剩余量  -->每次赠出1张
        coupon.setRemainNum(coupon.getRemainNum() - 1);
        this.couponMapper.updateById(coupon);
    }


    //优惠券记录
    private void addCouponLog(CouponLog couponLog) {
        couponLog.setCreateTime(LocalDateTime.now());
        this.couponLogMapper.insert(couponLog);
    }


    /**
     * 参与活动的添加记录
     *
     * @param activityId
     * @param orderId
     */
    private void addActivityOrder(Long activityId, Long orderId, Long asId) {
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(orderId);
        ActivityOrder activityOrder = new ActivityOrder();
        activityOrder.setActivityId(activityId);
        activityOrder.setCreateTime(LocalDateTime.now());
        activityOrder.setMemberId(orderInfoDB.getMemberId());
        activityOrder.setOrderId(orderId);
        activityOrder.setOrderNo(orderInfoDB.getOrderNo());
        activityOrder.setStatus(orderInfoDB.getOrderState());
        activityOrder.setAsId(asId);
        this.activityOrderMapper.insert(activityOrder);
    }

    /**
     * 是否参与过活动
     *
     * @param activityId
     * @param memberId
     */
    private int isJoinActivity(Long activityId, Long memberId) {
        Activity activityDB = this.activityMapper.selectById(activityId);
        if (null != activityDB.getTimes() && null != activityDB.getTimesStrategy()) {
            List<ActivityOrder> activityOrderList = new ArrayList<>();
            //  0全场一次，1每天，2周，3月，4年
            switch (activityDB.getTimesStrategy()) {
                case 0:
                    if (this.activityOrderMapper.getActivityOrderByMemberId(activityId, memberId, null).size() > activityDB.getTimes()) {
                        return 1;
                    }
                    break;
                case 1:
                    if (this.activityOrderMapper.getActivityOrderByMemberId(activityId, memberId, DATE_TIME.format(LocalDateTime.now())).size() > activityDB.getTimes()) {
                        return 1;
                    }
                    break;
                case 2:
                    if (this.activityOrderMapper.getActivityOrderByMemberId(activityId, memberId, "D").size() > activityDB.getTimes()) {
                        return 1;
                    }
                    break;
                case 3:
                    if (this.activityOrderMapper.getActivityOrderByMemberId(activityId, memberId, DATE_TIME_MONTH.format(LocalDateTime.now())).size() > activityDB.getTimes()) {
                        return 1;
                    }
                    break;
                case 4:
                    if (this.activityOrderMapper.getActivityOrderByMemberId(activityId, memberId, String.format("%s", LocalDateTime.now().getYear())).size() > activityDB.getTimes()) {
                        return 1;
                    }
                    break;
            }
        }
        return 0;
    }

    @Transactional
    @Override
    public void erpOrder(OrderInfoVo orderInfo, String toErpNum) {
        //erp对接
        OrderInfoDocVo orderInfoDocVo = new OrderInfoDocVo();
        orderInfoDocVo.setPayOrderNo(orderInfo.getPayOrderNo());
        orderInfoDocVo.setPayFlowNo(orderInfo.getPayFlowNo());
        orderInfoDocVo.setSrcOrderId(orderInfo.getSrcOrderId());
        orderInfoDocVo.setSrcOrderNo(orderInfo.getSrcOrderNo());
        orderInfoDocVo.setSrcOrderTime(orderInfo.getSrcOrderTime());
        orderInfoDocVo.setOrderNo(orderInfo.getOrderNo());
        orderInfoDocVo.setCustomId(orderInfo.getErpCustomerId());
        orderInfoDocVo.setOrderId(orderInfo.getId());
        orderInfoDocVo.setEntryId(1);
        orderInfoDocVo.setOrderFrom(orderInfo.getOrderFrom());
        orderInfoDocVo.setOrderType(orderInfo.getOrderType());
        orderInfoDocVo.setTargetPosId(orderInfo.getTargetPosId());
        orderInfoDocVo.setPayType(orderInfo.getPayMethod());
        orderInfoDocVo.setPayCode(orderInfo.getPayType());
        orderInfoDocVo.setPayCodeCn(orderInfo.getPayTypeDoc());
        /**
         * peiqy增加 门店id
         */
        orderInfoDocVo.setStoreId(orderInfo.getStoreId());

        List<OrderInfoDtlVo> orderInfoDtlVoList = new ArrayList<>();
        List<Long> longList = new ArrayList<>();
        for (OrderGoods orderGoods : orderInfo.getOrderGoodsList()) {

            OrderInfoDtlVo orderInfoDtlVo = new OrderInfoDtlVo();
            longList.add(orderGoods.getErpGoodsId());

            orderInfoDtlVo.setNum(IncaUtils.toErpPriceDouble4(orderGoods.getGoodsNum()));

            double price = IncaUtils.toErpPriceDouble4(orderGoods.getGoodsPrice());
            double pricePay = IncaUtils.toErpPriceDouble4(orderGoods.getGoodsPayPrice());
            //double amount = price * orderGoods.getGoodsNum();
            orderInfoDtlVo.setStoreId(orderGoods.getStoreId());
            orderInfoDtlVo.setSrcOrderDtlId(orderGoods.getSrcOrderDtlId());
            orderInfoDtlVo.setOrderId(orderInfo.getId());
            orderInfoDtlVo.setPrice(price);
            orderInfoDtlVo.setPriceDiscount(pricePay);
            orderInfoDtlVo.setAmount(IncaUtils.toErpPriceDouble4(orderGoods.getAmountNum()));
            orderInfoDtlVo.setAmountDiscount(IncaUtils.toErpPriceDouble4(orderGoods.getAmountPay()));
            orderInfoDtlVo.setGoodsId(orderGoods.getErpGoodsId());
            orderInfoDtlVo.setOrderDtlId(orderGoods.getId().toString());
            orderInfoDtlVo.setGoodsSource(orderGoods.getGoodType());
            orderInfoDtlVo.setOrderNo(orderInfo.getOrderNo());
            orderInfoDtlVo.setPriceId(orderGoods.getPriceId());
            orderInfoDtlVo.setStorageId(orderGoods.getErpStorageId());
            orderInfoDtlVo.setLotNo(orderGoods.getErpLotNo());
            if (orderGoods.getGoodType() == 9) {
                orderInfoDtlVo.setPrice(0.0);
                orderInfoDtlVo.setPriceDiscount(0.0);
                orderInfoDtlVo.setAmount(0.0);
                orderInfoDtlVo.setAmountDiscount(0.0);
            }
            orderInfoDtlVoList.add(orderInfoDtlVo);

            //amountDiscount +=orderGoods.getGoodsNum()*orderInfoDtlVo.getPriceDiscount();
        }

        orderInfoDocVo.setAmountPay(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount()));
        orderInfoDocVo.setAmountTotal(IncaUtils.toErpPriceDouble4(orderInfo.getOrderAmount()));
        orderInfoDocVo.setAmountDiscount(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount()));


        orderInfoDocVo.setCreateDate(orderInfo.getCreateTime());

        String remark = orderInfo.getRemark() == null ? "" : orderInfo.getRemark();
        //orderInfoDocVo.setRemark(remark + this.getActivityContent(orderInfo.getId()));
        orderInfoDocVo.setRemark(remark);
        log.info("{}" + orderInfoDocVo.getRemark());
        orderInfoDocVo.setOrderInfoDtlList(orderInfoDtlVoList);

        log.info("orderInfoDocVo{}", orderInfoDocVo);
        OrderLog orderLog = new OrderLog();
        String logMsg = "订单下发:";
        orderLog.setCreateTime(LocalDateTime.now());

        orderLog.setToErpNum(toErpNum);
        orderInfoDocVo.setVersion(toErpNum);
        // if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val())) return;

        try {
            OrderResultDocVo resultDocVo = this.orderService.genOrder(orderInfoDocVo);
            log.info("erp下单resultDocVo--{}", resultDocVo);
            log.info("erp下单返回参数 getUndoneDtlList--{}", resultDocVo.getUndoneDtlList());
            if (resultDocVo.getErrorCode() != 0) {
                //AssertExt.fail(resultDocVo.getErrorMessage());
                logMsg += resultDocVo.getErrorMessage();
                orderLog.setOrderStatus(OrderLog.EOrderStatus.ORDER_EXP.val());
                orderInfo.setExpStatus(OrderInfo.EExpStatus.ORDER_EXP.val());
                orderInfo.setIsTrue("0");
                orderInfo.setExpRemark(logMsg);
                log.info("订单异常{}", resultDocVo.getErrorMessage());
            } else {
                if (null == resultDocVo.getUndoneDtlList() || orderInfo.getOrderGoodsList().size() == 0) {
                    log.info("TO_SEND{}", resultDocVo.getUndoneDtlList());
                    orderInfo.setOrderState(OrderInfo.EOrderState.TO_SEND.val());
                    orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                    orderLog.setOrderStatus(OrderInfo.EOrderState.TO_SEND.val());
                } else {
                    if (resultDocVo.getUndoneDtlList().size() == orderInfo.getOrderGoodsList().size()) {
                        logMsg += "整单不出";
                        orderLog.setOrderStatus(OrderLog.EOrderStatus.NO_ORDER.val());
                        log.info("整单不出{}", resultDocVo.getUndoneDtlList());
                        orderInfo.setExpStatus(OrderInfo.EExpStatus.NO_ORDER.val());
                        orderInfo.setIsTrue("0");
                    } else {
                        // 短减
                        List<String> stringList = new ArrayList<>();
                        resultDocVo.getUndoneDtlList().forEach(orderResultDtlVo -> {
                            OrderGoods orderGoods = this.orderGoodsMapper.selectById(orderResultDtlVo.getOrderDtlId());
                            //orderGoods.setSellNum(orderResultDtlVo.getNum().intValue());
                            //this.orderGoodsMapper.updateById(orderGoods);
                          /*  Double goodsNum=  IncaUtils.toErpPriceDouble4(Long.valueOf(orderGoods.getGoodsNum()));
                            if (!orderResultDtlVo.getNum().equals(goodsNum)) {*/
                            // 短减
                            String logMsgShort = "短减:" + "商品id：" + orderGoods.getErpGoodsId() + "商品名称：" + orderGoods.getGoodsName() + ",下单数量：" + orderGoods.getGoodsNum() + ",erp短减数量" + orderResultDtlVo.getNum() + ";";
                            stringList.add(logMsgShort);
                            log.info("短减{}", resultDocVo.getUndoneDtlList());
                        });
                        logMsg += stringList.toString();
                        orderInfo.setOrderState(OrderInfo.EOrderState.TO_SEND.val());
                        orderLog.setOrderStatus(OrderInfo.EOrderState.TO_SEND.val());
                        orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                        if (null != stringList && stringList.size() > 0) {
                            orderLog.setOrderStatus(OrderLog.EOrderStatus.SHORT.val());
                            orderInfo.setExpStatus(OrderInfo.EExpStatus.SHORT.val());
                            orderInfo.setExpStatus(OrderInfo.EExpStatus.SHORT.val());
                            orderInfo.setIsTrue("0");
                        }
                    }
                    orderInfo.setExpRemark(logMsg);
                }

            }
            this.orderInfoMapper.updateById(orderInfo);

            orderLog.setLogMsg(logMsg);
            orderLog.setOrderId(orderInfo.getId());
            orderLog.setDoneTime(LocalDateTime.now());
            orderLog.setToErpNum("v1");
            this.orderLogMapper.insert(orderLog);
        } catch (RuntimeException e) {
            log.error("orderService.genOrder error ", e);
            orderInfo.setExpRemark(e.getMessage());
            orderInfo.setExpStatus(OrderInfo.EExpStatus.CATCH_EXP.val());
            orderInfo.setIsTrue("0");
            this.orderInfoMapper.updateById(orderInfo);
            orderLog.setLogMsg(e.getMessage());
            orderLog.setOrderStatus(OrderLog.EOrderStatus.CATCH_EXP.val());
            orderLog.setOrderId(orderInfo.getId());
            orderLog.setDoneTime(LocalDateTime.now());
            orderLog.setToErpNum("v1");
            this.orderLogMapper.insert(orderLog);
        }

    }

    private String getActivityContent(Long orderId) {
        List<OrderGoodsVo> orderGoodsVoList = this.orderGoodsMapper.getActivityContent(orderId);
        if (orderGoodsVoList.size() == 0) return " ";
        String content = ";参与的活动：";
        if (orderGoodsVoList.size() > 0) {
            int i = 1;
            for (OrderGoodsVo orderGoodsVo : orderGoodsVoList) {
                if (null == orderGoodsVo.getGiftId()) {
                    content += i + ",【" + orderGoodsVo.getErpGoodsId() + ":" + orderGoodsVo.getGoodsName() + "】" + "有促销" + "【" + orderGoodsVo.getContent() + "】" + ";";
                    i++;
                } else {
                    content += "赠送【" + orderGoodsVo.getErpGoodsId() + ":" + orderGoodsVo.getGoodsName() + "】;";
                }

            }
        }


        return content;
    }

    @Transactional
    @Override
    public PayOrder memberRePayOrder(Long orderId, String ip, String openId, Long orderFrom, String paymentType) {
        AssertExt.hasId(orderId, "订单id为空");
        AssertExt.hasId(orderFrom, "订单来源为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(orderId);
        AssertExt.notNull(orderInfoDB, "无效id");
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        // AssertExt.isTrue(orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.UNPAID.val()), "不是待支付状态");
        String payOrderNo = orderInfoDB.getOrderNo() + "_ZF_" + System.currentTimeMillis();
        orderInfoDB.setPayOrderNo(payOrderNo);
        this.orderInfoMapper.updateById(orderInfoDB);

        if (orderInfoDB.getOrderFrom().equals(2)) {
            AssertExt.notNull(openId, "未授权，请重新登录");
        }
        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setOrderNo(payOrderNo);
        payOrderVo.setDataSource(orderFrom.toString());
        //payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(0.1));

        payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(orderInfoDB.getGoodsAmount()));
        if (orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.TO_DELIVERY.val())) {
            payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(orderInfoDB.getErpOrderAmount()));
        }

        payOrderVo.setBody("中天预售在线支付");
        payOrderVo.setOpenid(openId);
        payOrderVo.setSpbillCreateIp(ip);
        payOrderVo.setPaymentType(paymentType);
        PayOrder payOrder = this.payOrderService.payOrder(payOrderVo);

        orderLog.setLogMsg("客户支付订单;支付单号：" + payOrderNo);
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setToErpNum("v1");
        orderLog.setDoneTime(LocalDateTime.now());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        this.orderLogMapper.insert(orderLog);
        return payOrder;
    }

    @Override
    public PayOrder appSentPayOrder(Long orderId, String ip, Long sentUserId, Long orderFrom, String paymentType) {
        AssertExt.hasId(orderId, "订单id为空");
        AssertExt.hasId(orderFrom, "订单来源为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(orderId);
        AssertExt.notNull(orderInfoDB, "无效id");
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        // AssertExt.isTrue(orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.UNPAID.val()), "不是待支付状态");
        String payOrderNo = orderInfoDB.getOrderNo() + "_ZF_" + System.currentTimeMillis();
        orderInfoDB.setPayOrderNo(payOrderNo);
        this.orderInfoMapper.updateById(orderInfoDB);

        PayOrderVo payOrderVo = new PayOrderVo();
        payOrderVo.setOrderNo(payOrderNo);
        payOrderVo.setDataSource(orderFrom.toString());
        payOrderVo.setPaymentType(paymentType);
        payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(orderInfoDB.getGoodsAmount()));
        if (orderInfoDB.getErpAmount() > 0) {
            payOrderVo.setTotalFee(IncaUtils.toErpPriceDouble2(orderInfoDB.getErpAmount()));
        }

        payOrderVo.setBody("中天预售在线支付");
        payOrderVo.setOpenid(null);
        payOrderVo.setSpbillCreateIp(ip);
        PayOrder payOrder = this.payOrderService.payOrder(payOrderVo);

        orderLog.setLogMsg("app订单收款；支付单号：" + payOrderNo);
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setToErpNum("v1");
        orderLog.setDoneTime(LocalDateTime.now());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        this.orderLogMapper.insert(orderLog);
        return payOrder;
    }

    @Override
    public IPage<OrderInfoVo> getMyOrderInfoList(Page page, OrderInfoVo orderInfoVo) {
        IPage<OrderInfoVo> orderInfoVoIPage = this.orderInfoMapper.getMyOrderInfoList(page, orderInfoVo);
        orderInfoVoIPage.getRecords().forEach(orderInfoVo1 -> {
            orderInfoVo1.setOrderGoodsList(this.orderGoodsMapper.getOrderGoodsVoInfo(orderInfoVo1.getId()));
        });
        return orderInfoVoIPage;
    }

    @Override
    public OrderInfoVo getOrderInfoById(Long id) {
        AssertExt.hasId(id, "id为空");
        OrderInfoVo orderInfoVo = this.orderInfoMapper.getOrderInfoById(id);
        AssertExt.notNull(orderInfoVo, "无效id[%s]", id);
        orderInfoVo.setOrderGoodsList(this.orderGoodsMapper.getOrderGoodsVoInfo(orderInfoVo.getId()));
        orderInfoVo.setErpOrderInfoList(this.orderInfoMapper.getErpOrderId(orderInfoVo.getOrderNo()));
        if (orderInfoVo.getOrderState().equals(OrderInfo.EOrderState.TO_DELIVERY.val())
                || orderInfoVo.getOrderState().equals(OrderInfo.EOrderState.APPLY_REFUND.val())
                || orderInfoVo.getOrderState().equals(OrderInfo.EOrderState.ERP_TO_RECEIVED.val())
                || orderInfoVo.getOrderState().equals(OrderInfo.EOrderState.TO_RECEIVED.val())
                || orderInfoVo.getOrderState().equals(OrderInfo.EOrderState.SUCCESS_REFUND.val())
                || orderInfoVo.getOrderState().equals(OrderInfo.EOrderState.DONE.val())) {
            List<OrderGoodsVo> orderGoodsVoList = this.orderGoodsMapper.orderGoodsDeliveryList(orderInfoVo.getId());
            orderGoodsVoList.forEach(orderGoodsVo -> {
                if (null != orderGoodsVo.getLotno() && null != orderGoodsVo.getGoodsId()) {
                    String qualityImg = this.orderInfoMapper.getGoodsInfoReport(orderGoodsVo.getGoodsId(), orderGoodsVo.getLotno());
                    orderGoodsVo.setQualityImg(qualityImg != null ? qualityImg : null);
                }

                OrderGoods orderGoodsDB = this.orderGoodsMapper.selectById(orderGoodsVo.getB2bOrderDtlId());
                orderGoodsVo.setErpLeastsaleqty(orderGoodsDB.getErpLeastsaleqty());

                RefundDetail refundDetail = this.refundDetailMapper.selectOne(new QueryWrapper<RefundDetail>().eq("ORDER_GOODS_ID", orderGoodsVo.getB2bOrderDtlId()).eq("BATCH_ID", orderGoodsVo.getBatchid()));
                if (null != refundDetail) orderGoodsVo.setRefundNum(refundDetail.getGoodsNum());
                else orderGoodsVo.setRefundNum(orderGoodsDB.getRefundNum());

            });
            orderInfoVo.setOrderGoodsDeliveryList(orderGoodsVoList);
        }

        return orderInfoVo;
    }

    @Transactional
    @Override
    public void delMyOrderInfo(Long id, Integer deleteState) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(deleteState, "删除状态为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfoDB, "无效id");
        orderInfoDB.setDeleteState(deleteState);
        this.orderInfoMapper.updateById(orderInfoDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("删除订单");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Transactional
    @Override
    public void recoverDelMyOrderInfo(Long id) {
        AssertExt.hasId(id, "id为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfoDB, "无效id");
        orderInfoDB.setDeleteState(Constant.IS_DEL.NO);
        this.orderInfoMapper.updateById(orderInfoDB);
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());

        orderLog.setLogMsg("恢复订单");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Override
    public IPage<OrderInfoVo> getAdminOrderInfoList(Page page, OrderInfoVo orderInfo) {
        IPage<OrderInfoVo> orderInfoVoIPage = this.orderInfoMapper.getAdminOrderInfoList(page, orderInfo);
        orderInfoVoIPage.getRecords().forEach(orderInfoVo1 -> {
            //orderInfoVo1.setErpOrderInfoList(this.orderInfoMapper.getErpOrderId(orderInfoVo1.getOrderNo()));
            orderInfoVo1.setOrderGoodsList(this.orderGoodsMapper.getOrderGoodsVoInfo(orderInfoVo1.getId()));
        });
        return orderInfoVoIPage;
    }

    @Override
    public IPage<OrderGoodsVo> getMyOrderGoods(Long memberId, Page page) {
        return this.orderGoodsMapper.getMyOrderGoods(memberId, page);
    }

    @Override
    public List<OrderInfoVo> getOrderInfoVoAll(Long memberId, String orderState) {
        return this.orderInfoMapper.getMyOrderInfoAll(memberId, orderState);
    }

    @Transactional
    @Override
    public void cancelOrder(Long id) {
        AssertExt.hasId(id, "id为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfoDB, "无效id[%s]", id);

        // Setting settingDB = this.getOrderCancelTime();

        AssertExt.isTrue(orderInfoDB.getCreateTime().plusMinutes(this.settingService.getOrderCancelTime()).isAfter(LocalDateTime.now()), "订单已超过可取消时间");
        AssertExt.isFalse(orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.CANCEL.val()), "订单已取消，请勿重复取消");
        if (!orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.UNPAID.val())) {
            AssertExt.isTrue(orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.TO_ERP.val()), "货品已拣货，不能取消");
        } else {
            //修改收款单状态
            RecDoc recDocDB = this.recDocMapper.selectOne(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfoDB.getId()));
            recDocDB.setStatus(RecDoc.EStatus.CANCEL.val());
            recDocDB.setRecType(orderInfoDB.getPayMethod());
            this.recDocMapper.updateById(recDocDB);
        }

        orderInfoDB.setOrderState(OrderInfo.EOrderState.CANCEL.val());
        this.orderInfoMapper.updateById(orderInfoDB);

        this.activityOrderMapper.updateActivityOrderByOrderId(orderInfoDB.getId());

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("客户取消订单");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Transactional
    @Override
    public void reErpOrder(Long id) {
        AssertExt.hasId(id, "id为空");
        OrderInfoVo orderInfoDB = this.getOrderInfoById(id);
        AssertExt.notNull(orderInfoDB, "无效id[%s]", id);
        orderInfoDB.setOrderState(OrderInfo.EOrderState.TO_ERP.val());
        orderInfoDB.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
        this.orderInfoMapper.updateById(orderInfoDB);

        List<OrderGoods> orderGoodsList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoDB.getId()));
        orderGoodsList.forEach(orderGoods -> {
            orderGoods.setSellNum(0L);
            this.orderGoodsMapper.updateById(orderGoods);
        });
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("订单重新下发");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        String toErpNum1 = this.orderLogMapper.selectList(new QueryWrapper<OrderLog>().eq("order_id", orderInfoDB.getId()).orderByDesc("create_time")).get(0).getToErpNum();

        String num = RandomStringUtils.random(3, false, true);

        orderLog.setToErpNum(toErpNum1 + '.' + num);
        this.orderLogMapper.insert(orderLog);




     /*   List<OrderLog> orderLogList = this.orderLogMapper.selectList(new QueryWrapper<OrderLog>().eq("order_id", id).eq("order_status", OrderLog.EOrderStatus.TO_ERP.val()).orderByDesc("create_time"));
        String toErpNum = "v2";
        if (null != orderLogList && orderLogList.size() > 0) {
            toErpNum = "v2." + orderLogList.get(0).getToErpNum();
        }
        this.erpOrder(orderInfoDB, toErpNum);*/
    }

    @Transactional
    @Override
    public void minutesToErpOrder() {
        //订单待下发
        // List<OrderInfo> orderInfoList = this.orderInfoMapper.selectList(new QueryWrapper<OrderInfo>().eq("EXP_STATUS", OrderInfo.EExpStatus.NORMAL.val()).eq("order_state", OrderInfo.EOrderState.TO_ERP.val()));

        //获取系统设置下发时间
        Long toErpOrderTimes = this.settingService.getOrderCancelTime();
        //当前时间XXX分钟之前的订单
        List<OrderInfo> orderInfoList = this.orderInfoMapper.getNowOrderInfo(toErpOrderTimes);
        //List<OrderInfo> orderInfoList = this.orderInfoMapper.getNowOrderInfo(0);
        orderInfoList.forEach(orderInfo -> {
            List<OrderLog> orderLogList = this.orderLogMapper.selectList(new QueryWrapper<OrderLog>().eq("order_id", orderInfo.getId()).eq("order_status", OrderLog.EOrderStatus.TO_ERP.val()).orderByDesc("create_time"));
            OrderInfoVo orderInfoDB = this.getOrderInfoById(orderInfo.getId());
            String v1 = "v1." + orderInfoDB.getId();
            if (orderLogList.size() != 0) {
                log.info("orderLogList{}", orderLogList);
                v1 = orderLogList.get(0).getToErpNum();
            }
            this.erpOrder(orderInfoDB, v1);

           /* if (orderLogList.size() <= 3) {
                OrderInfoVo orderInfoDB = this.getOrderInfoById(orderInfo.getId());
                this.erpOrder(orderInfoDB, orderLogList.get(0).getToErpNum());

            } else {
                orderInfo.setExpStatus(OrderInfo.EExpStatus.OUT_TIME.val());
                orderInfo.setExpRemark("下发次数超过3次");
                this.orderInfoMapper.updateById(orderInfo);
                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("下发次数超过3次");
                orderLog.setOrderId(orderInfo.getId());
                orderLog.setOrderStatus(orderInfo.getExpStatus());
                orderLog.setToErpNum("v1");
                this.orderLogMapper.insert(orderLog);
            }*/

        });
    }

    @Override
    public IPage<OrderInfoVo> getOrderInfoExceptionList(Page page, OrderInfoVo orderInfoVo) {
        return this.orderInfoMapper.getOrderInfoExceptionList(page, orderInfoVo);
    }

    @Transactional
    @Override
    public void applyRefundReturn(RefundReturnVo refundReturnVo) {
        AssertExt.hasId(refundReturnVo.getOrderId(), "订单id为空");
        //AssertExt.hasId(refundReturnVo.getOrderGoodsId(), "订单商品id为空");
        //AssertExt.hasId(refundReturnVo.getGoodsId(), "订单商品id为空");
        AssertExt.notNull(refundReturnVo.getApplyType(), "申请类型为空");


        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(refundReturnVo.getOrderId());
        AssertExt.notNull(orderInfoDB, "无效订单id");

        AssertExt.isTrue(orderInfoDB.getCreateTime().plusDays(10L).isAfter(LocalDateTime.now()), "订单已超过可申请退款/退货时间");

        AssertExt.isTrue(orderInfoDB.getRefundState().equals(OrderInfo.ERefundState.NO_REFUND.val()), "订单已申请退货/退款");

        refundReturnVo.setApplyType(RefundReturn.EApplyType.REFUND_GOODS.val());
        refundReturnVo.setOrderNo(orderInfoDB.getOrderNo());
        refundReturnVo.setErpCustomerId(orderInfoDB.getErpCustomerId());
        refundReturnVo.setApplyNo(this.refundReturnWorker.genNo());
        refundReturnVo.setMemberName(orderInfoDB.getMemberName());
        refundReturnVo.setRefundState(RefundReturn.ERefundState.PENDING.val());
        refundReturnVo.setCreateTime(LocalDateTime.now());
        this.refundReturnMapper.insert(refundReturnVo);

        //全部退
       /* if (refundReturnVo.getGoodsId() == 0 && refundReturnVo.getOrderGoodsId() == 0) {
            List<OrderGoodsVo> orderGoodsVoList = this.orderGoodsMapper.orderGoodsDeliveryList(orderInfoDB.getOrderNo());
            orderGoodsVoList.forEach(orderGoodsVo -> {
                RefundDetail refundDetail = new RefundDetail();
                refundDetail.setGoodsId(orderGoodsVo.getGoodsId());
                refundDetail.setOrderGoodsId(orderGoodsVo.getB2bOrderDtlId());
                refundDetail.setGoodsName(orderGoodsVo.getGoodsName());
                refundDetail.setGodosImage(orderGoodsVo.getGoodsImage());
                refundDetail.setGoodsNum(orderGoodsVo.getGoodsNum());
                refundDetail.setLotNo(orderGoodsVo.getLotno());
                refundDetail.setPayAmount(orderGoodsVo.getGoodsPayPrice());
                refundDetail.setRefundAmount(orderGoodsVo.getB2bAmountTotal());
                refundDetail.setPdAmount(orderGoodsVo.getGoodsPayPrice());
                refundDetail.setRefundState(RefundDetail.ERefundState.PENDING.val());
                refundDetail.setRefundId(refundReturnVo.getId());
                refundDetail.setOrderId(orderInfoDB.getId());
                refundDetail.setGoodsSource(orderGoodsVo.getGoodsSource());
                refundDetail.setErpStorageId(orderGoodsVo.getErpStorageId());
                refundDetail.setSrcErpOrderDtlId(orderGoodsVo.getSrcErpOrderDtlId());
                refundDetail.setSrcErpOrderId(orderGoodsVo.getSrcErpOrderId());
                refundDetail.setCreateTime(LocalDateTime.now());
                this.refundDetailMapper.insert(refundDetail);
            });
            orderInfoDB.setRefundState(OrderInfo.ERefundState.ALL_REFUND.val());
        }*/
        //部分退or全部退
        AssertExt.notNull(refundReturnVo.getRefundDetailList(), "退货数据为空");

        List<OrderGoods> orderGoodsList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", refundReturnVo.getOrderId()));


        if (refundReturnVo.getRefundDetailList().size() == orderGoodsList.size())
            orderInfoDB.setRefundState(OrderInfo.ERefundState.ALL_REFUND.val());
        else orderInfoDB.setRefundState(OrderInfo.ERefundState.PART_REFUND.val());

        double refundAmount = 0L;
        for (RefundDetailVo refundDetail : refundReturnVo.getRefundDetailList()) {
            AssertExt.hasId(refundDetail.getOrderGoodsId(), "订单商品id为空");
            AssertExt.isTrue(refundDetail.getGoodsNum() != 0, "退货数量不能为0");

            OrderGoods orderGoodsDB = this.orderGoodsMapper.selectById(refundDetail.getOrderGoodsId());

            AssertExt.isTrue(refundDetail.getGoodsNum() <= orderGoodsDB.getSellNum() - orderGoodsDB.getRefundNum(), "退货数量不能超过实际发货数量");

            if (refundDetail.getGoodsNum() != orderGoodsDB.getSellNum()) {
                orderInfoDB.setRefundState(OrderInfo.ERefundState.PART_REFUND.val());
            }

            refundDetail.setGoodsDtlId(orderGoodsDB.getGoodsDtlId());
            refundDetail.setOrderGoodsType(RefundDetail.EOrderGoodsType.DEFAULTS.val());
            refundDetail.setGoodsPrice(orderGoodsDB.getGoodsPrice());
            refundDetail.setGoodsPayPrice(orderGoodsDB.getGoodsPayPrice());
            double amount = IncaUtils.toErpPriceDouble(orderGoodsDB.getGoodsPayPrice() * refundDetail.getGoodsNum());
            refundDetail.setRefundAmount(amount);
            refundDetail.setRefundState(RefundDetail.ERefundState.PENDING.val());
            refundDetail.setRefundId(refundReturnVo.getId());
            refundDetail.setOrderId(orderInfoDB.getId());
            refundDetail.setCreateTime(LocalDateTime.now());
            this.refundDetailMapper.insert(refundDetail);

            orderGoodsDB.setRefundNum(orderGoodsDB.getRefundNum() + refundDetail.getGoodsNum());
            this.orderGoodsMapper.updateById(orderGoodsDB);

            refundAmount += amount;
        }

        refundReturnVo.setRefundAmount(IncaUtils.toErpPriceDouble(refundAmount));
        this.refundReturnMapper.updateById(refundReturnVo);

        this.orderInfoMapper.updateById(orderInfoDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("客户申请退款/退货");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getRefundState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Override
    public IPage<RefundReturnVo> getMyRefundReturnVoList(Page page, RefundReturnVo refundReturnVo) {
        IPage<RefundReturnVo> refundReturnVoList = this.refundReturnMapper.getMyRefundReturnVoList(page, refundReturnVo);
        refundReturnVoList.getRecords().forEach(refundReturnVo1 -> {
            refundReturnVo1.setRefundDetailList(this.refundDetailMapper.getRefundDetailList(refundReturnVo1.getId()));
        });
        return refundReturnVoList;
    }

    @Override
    public RecDoc noOrderAddRecOrder(Long id, String noOrderRemark, Long noOrderUserId) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(noOrderRemark, "备注为空");
        OrderInfo orderInfo = this.orderInfoMapper.selectById(id);
        orderInfo.setNoOrderRemark(noOrderRemark);
        orderInfo.setNoOrderUserId(noOrderUserId);

        List<OrderGoods> orderGoodsList = this.orderGoodsMapper.getShortOrderGoodsList(orderInfo.getId());
        if (orderInfo.getExpStatus().equals(RecDoc.ERecMethod.NO_ORDER.val())) {
            List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()).eq("REC_METHOD", RecDoc.ERecMethod.NO_ORDER.val()).eq("MEMBER_ID", orderInfo.getMemberId()));
            if (recDocList.size() > 0) return null;
        }

        RecDoc recDoc = new RecDoc();
        recDoc.setEntryId(1L);
        recDoc.setMemberId(orderInfo.getMemberId());
        recDoc.setOrderId(orderInfo.getId());
        recDoc.setRecDocNo(this.recDocReturnWorker.genNo());
        recDoc.setStatus(RecDoc.EStatus.TO_PAY.val());
        recDoc.setRecMethod(orderInfo.getExpStatus());
        recDoc.setRecType(orderInfo.getPayMethod());
        recDoc.setDtlLines(orderGoodsList.size());
        recDoc.setTotal(-orderInfo.getGoodsAmount());
        recDoc.setPremoney(-orderInfo.getGoodsAmount());
        if (orderInfo.getExpStatus().equals(RecDoc.ERecMethod.NO_ORDER.val())) {
            recDoc.setTotal(-orderInfo.getGoodsAmount());
            recDoc.setPremoney(-orderInfo.getGoodsAmount());
        }

        recDoc.setRemark(orderInfo.getRemark());
        recDoc.setCreateTime(LocalDateTime.now());
        recDoc.setRefundState(RecDoc.ERefundState.NO_REFUND.val());
        this.recDocMapper.insert(recDoc);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("整单不出生成退款单");
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setOrderStatus(orderInfo.getOrderState());
        orderLog.setDoneTime(LocalDateTime.now());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);

        orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
        orderInfo.setOrderState(OrderInfo.EOrderState.CANCEL.val());
        this.orderInfoMapper.updateById(orderInfo);
        return recDoc;
    }

    @Transactional
    @Override
    public void updateOrderState() {

        List<Long> longList = this.orderInfoMapper.getOrderByToSend();
        if (longList.size() > 0) {
            Integer num = longList.size() / 50;
            if (num < 1) {
                num = 1;
            }
            for (int i = 1; i <= num; i++) {
                List<Long> longList1 = this.orderInfoMapper.getOrderByToSend(new Page(i, 50));
                if (longList1.size() > 0) {

                    List<OrderStatusVo> orderStatusVoList = this.orderService.selectOrderStatusList(longList1);
                    log.info("orderStatusVoList--{}", orderStatusVoList);
                    for (OrderStatusVo orderStatusVo : orderStatusVoList) {
                        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(orderStatusVo.getB2bOrderId());
                        OrderLog orderLog = new OrderLog();
                        orderLog.setCreateTime(LocalDateTime.now());
                        //整单不出
                        if (orderStatusVo.getStatus().equals(1)) {
                            orderInfoDB.setExpStatus(OrderInfo.EExpStatus.NO_ORDER.val());
                            orderInfoDB.setExpRemark("订单状态改为待收货--整单不出");
                            orderLog.setOrderStatus(OrderInfo.EExpStatus.NO_ORDER.val());


                            orderInfoDB.setIsTrue("0");
                            orderLog.setLogMsg("订单状态改为待收货--整单不出");
                            //this.addRecOrder(orderInfoDB);
                        }

                        //订单已出库
                        if (orderStatusVo.getStatus().equals(3)) {
                            //发货单
                            List<OrderGoodsVo> orderGoodsVoList = this.orderGoodsMapper.orderGoodsDeliveryList(orderInfoDB.getId());

                            //判断是否短减
                            String logMsg = "订单状态改为待收货--";
                            // 短减内容
                            List<String> stringList = new ArrayList<>();
                            log.info("发货单详情{}", orderGoodsVoList);

                            //异常金额
                            double erpAmount = 0L;
                            for (OrderGoodsVo orderGoodsVo : orderGoodsVoList) {
                                OrderGoods orderGoodsDB = this.orderGoodsMapper.selectById(orderGoodsVo.getB2bOrderDtlId());
                                if (null == orderGoodsDB) continue;
                                erpAmount += IncaUtils.toErpPriceDouble(orderGoodsVo.getTotal());
                                if (orderGoodsDB.getSellNum() > 0) {
                                    orderGoodsDB.setSellNum(orderGoodsDB.getSellNum() + orderGoodsVo.getDtlgoodsqty());
                                } else {
                                    orderGoodsDB.setSellNum(orderGoodsVo.getDtlgoodsqty());
                                }

                                orderGoodsDB.setGoodsDtlId(orderGoodsVo.getGoodsdtlid());
                                //orderGoodsDB.setSellNum( (orderGoodsVo.getDtlgoodsqty() ));
                                this.orderGoodsMapper.updateById(orderGoodsDB);

                            }



                            List<OrderGoods> orderGoodsList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoDB.getId()));
                            for (OrderGoods orderGoods : orderGoodsList) {
                                if (orderGoods.getGoodsNum() != orderGoods.getSellNum()) {
                                    String logMsgShort = "短减:" + "商品id：" + orderGoods.getErpGoodsId() + ";商品名称：" + orderGoods.getGoodsName() + ";实际发货数量：" + orderGoods.getSellNum() + ";客户下单数量：" + orderGoods.getGoodsNum();
                                    stringList.add(logMsgShort);
                                }
                            }

                            log.info("erpOrderAmount(){}", erpAmount);
                            erpAmount = IncaUtils.toErpPriceDouble(erpAmount);
                            orderInfoDB.setErpOrderAmount(erpAmount);
                            log.info("orderInfoDB.getGoodsAmount(){}", orderInfoDB.getGoodsAmount());
                            log.info("erpOrderAmount(){}", erpAmount);
                            if (!ObjectUtil.equal(orderInfoDB.getGoodsAmount(), erpAmount)) {
                                orderLog.setOrderStatus(OrderLog.EOrderStatus.AMOUNT_EXP.val());
                                orderInfoDB.setExpStatus(OrderInfo.EExpStatus.AMOUNT_EXP.val());
                            }

                            logMsg += stringList.toString();

                            orderInfoDB.setErpAmount(IncaUtils.toErpPriceDouble(erpAmount));

                            orderInfoDB.setOrderState(OrderInfo.EOrderState.TO_DELIVERY.val());
                            orderLog.setOrderStatus(OrderInfo.EOrderState.TO_DELIVERY.val());

                            if (null != stringList && stringList.size() > 0) {
                                orderLog.setOrderStatus(OrderLog.EOrderStatus.SHORT.val());
                                orderInfoDB.setExpStatus(OrderInfo.EExpStatus.SHORT.val());

                                orderInfoDB.setIsTrue("0");
                                orderInfoDB.setExpRemark(logMsg);
                                this.addRecOrder(orderInfoDB);
                            }

                            orderLog.setLogMsg(logMsg);
                        }


                        this.orderInfoMapper.updateById(orderInfoDB);


                        orderLog.setOrderId(orderInfoDB.getId());
                        orderLog.setDoneTime(LocalDateTime.now());
                        orderLog.setToErpNum("v1");
                        this.orderLogMapper.insert(orderLog);
                    }
                }
            }
        }


    }

    //修改订单状态 -->待收货  生成收款单
    private void addRecOrder(OrderInfo orderInfo) {
        List<OrderGoods> orderGoodsList = this.orderGoodsMapper.getShortOrderGoodsList(orderInfo.getId());
        if (orderInfo.getExpStatus().equals(RecDoc.ERecMethod.NO_ORDER.val())) {
            List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()).eq("REC_METHOD", RecDoc.ERecMethod.NO_ORDER.val()).eq("MEMBER_ID", orderInfo.getMemberId()));
            if (recDocList.size() > 0) return;
        }

        RecDoc recDoc = new RecDoc();
        recDoc.setEntryId(1L);
        recDoc.setMemberId(orderInfo.getMemberId());
        recDoc.setOrderId(orderInfo.getId());
        recDoc.setStatus(RecDoc.EStatus.TO_PAY.val());
        recDoc.setRecMethod(orderInfo.getExpStatus());
        recDoc.setRecType(orderInfo.getPayMethod());
        recDoc.setDtlLines(orderGoodsList.size());
        recDoc.setTotal(-orderInfo.getGoodsAmount());
        recDoc.setPremoney(-orderInfo.getGoodsAmount());
        if (orderInfo.getExpStatus().equals(RecDoc.ERecMethod.NO_ORDER.val())) {
            recDoc.setTotal(-orderInfo.getGoodsAmount());
            recDoc.setPremoney(-orderInfo.getGoodsAmount());
        }

        recDoc.setRemark(orderInfo.getRemark());
        recDoc.setCreateTime(LocalDateTime.now());
        recDoc.setRecDocNo(this.recDocReturnWorker.genNo());
        recDoc.setRefundState(RecDoc.ERefundState.NO_REFUND.val());
        this.recDocMapper.insert(recDoc);
        double goodsAmount = 0L;
        if (orderInfo.getExpStatus().equals(RecDoc.ERecMethod.SHORT.val())) {
            for (OrderGoods orderGoods : orderGoodsList) {
                RecDtl recDtl = new RecDtl();
                recDtl.setGoodsId(orderGoods.getErpGoodsId());
                double num = orderGoods.getGoodsNum() - orderGoods.getSellNum();
                recDtl.setGoodsQty(num);
                recDtl.setUntPrice(orderGoods.getGoodsPayPrice());
                double total = IncaUtils.toErpPriceDouble4(num * orderGoods.getGoodsPayPrice());
                recDtl.setTotalLine(total);
                recDtl.setErpLeastsaleqty(orderGoods.getErpLeastsaleqty());
                recDtl.setSaRecId(recDoc.getId());
                recDtl.setCreateTime(LocalDateTime.now());
                this.recDtlMapper.insert(recDtl);
                goodsAmount += orderGoods.getAmountPay();
            }
            double amountTotal = IncaUtils.toErpPriceDouble(goodsAmount);

            recDoc.setTotal(-amountTotal);
            recDoc.setPremoney(-amountTotal);
            this.recDocMapper.updateById(recDoc);
        }

    }

    @Transactional
    @Override
    public void getOrderInfoCpfr(Long id) {
        AssertExt.hasId(id, "id为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfoDB, "无效id[%s]", id);

        AssertExt.isTrue(orderInfoDB.getExpStatus().equals(OrderInfo.EExpStatus.SHORT.val()), "不需要补货");
        orderInfoDB.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
        orderInfoDB.setOrderType(OrderInfo.EOrderType.CPFR.val());
        this.orderInfoMapper.updateById(orderInfoDB);


        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("订单补货");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setDoneTime(LocalDateTime.now());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Override
    public List<OrderLog> getOrderLogList(Long orderId) {
        AssertExt.hasId(orderId, "orderId为空");
        return this.orderLogMapper.selectList(new QueryWrapper<OrderLog>().eq("order_id", orderId).orderByDesc("CREATE_TIME"));
    }

    @Override
    public IPage<OrderLog> getOrderLogListPage(Page page) {
        AssertExt.notNull(page, "无效页码");
        return this.orderLogMapper.selectPage(page, new QueryWrapper<OrderLog>().orderByDesc("CREATE_TIME"));
    }

    @Transactional
    @Override
    public void autoOrderToDelivery() {
        Long valueToDelivery = this.settingService.getOrderScore("ORDER_TO_RECEIVED");
        Long valueScore = this.settingService.getOrderScore("ORDER_SCORE");
        List<OrderInfo> orderInfoList = this.orderInfoMapper.getAutoOrderToDelivery(valueToDelivery);
        if (orderInfoList.size() > 0) {
            for (OrderInfo orderInfo : orderInfoList) {
                int orderScore = (int) (orderInfo.getGoodsAmount());
                Member memberDB = this.memberMapper.selectById(orderInfo.getMemberId());

                if (valueScore > 0) {
                    Long score = orderScore / valueScore;
                    if (score > 0) {
                        ScoreRecord scoreRecord = ScoreRecord.builder().chanScore(score).orignScore(memberDB.getIntegral()).content("下单自动收货赠积分")
                                .fromId(orderInfo.getId()).fromType("1").memberId(memberDB.getId()).build();
                        this.addScoreRecord(scoreRecord);
                        orderInfo.setSendScore(score);

                    }
                }
                orderInfo.setOrderState(OrderInfo.EOrderState.TO_RECEIVED.val());
                this.orderInfoMapper.updateById(orderInfo);

                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("自动确认收货");
                orderLog.setOrderId(orderInfo.getId());
                orderLog.setOrderStatus(orderInfo.getOrderState());
                orderLog.setDoneTime(LocalDateTime.now());
                orderLog.setToErpNum("v1");
                this.orderLogMapper.insert(orderLog);
            }
        }

    }

    @Override
    public IPage<OrderInfoVo> getFastOrderInfo(Page page, OrderInfoVo orderInfoVo) {
        Member member = this.memberMapper.selectById(orderInfoVo.getMemberId());
        orderInfoVo.setErpCustomerId(member.getErpUserId());
        return this.orderInfoMapper.getFastOrderInfo(page, orderInfoVo);
    }

    @Transactional
    @Override
    public void sureOrderToDelivery(Long id) {
        AssertExt.hasId(id, "id为空");
        OrderInfoVo orderInfoDB = this.getOrderInfoById(id);

        AssertExt.isTrue(orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.TO_DELIVERY.val()), "不是待收货状态");

        orderInfoDB.setOrderState(OrderInfo.EOrderState.TO_RECEIVED.val());


        Long valueScore = this.settingService.getOrderScore("ORDER_SCORE");


        int orderScore = (int) (orderInfoDB.getGoodsAmount());
        Member memberDB = this.memberMapper.selectById(orderInfoDB.getMemberId());

        if (valueScore > 0) {
            Long score = orderScore / valueScore;
            if (score > 0) {
                ScoreRecord scoreRecord = ScoreRecord.builder().chanScore(score).orignScore(memberDB.getIntegral()).content("客户确认收货赠积分")
                        .fromId(orderInfoDB.getId()).fromType("1").memberId(memberDB.getId()).build();
                this.addScoreRecord(scoreRecord);
                orderInfoDB.setSendScore(score);
            }
        }
        this.orderInfoMapper.updateById(orderInfoDB);



     /*   if (orderInfoDB.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val())) {
            orderInfoDB.setOrderState(OrderInfo.EOrderState.DONE.val());
        }

        OrderInfoDocVo orderInfoDocVo = new OrderInfoDocVo();
        orderInfoDocVo.setOrderFrom(orderInfoDB.getOrderFrom());
        orderInfoDocVo.setOrderNo(orderInfoDB.getOrderNo());
        //下单人
        orderInfoDocVo.setCustomId(orderInfoDB.getErpCustomerId());
        orderInfoDocVo.setOrderId(orderInfoDB.getId());
        orderInfoDocVo.setTargetPosId(orderInfoDB.getTargetPosId());
        orderInfoDocVo.setOrderType(orderInfoDB.getOrderType());
        orderInfoDocVo.setEntryId(1);
        //TODO 收货人！=下单人
        orderInfoDocVo.setReceiptManId(0L);
        orderInfoDocVo.setCreateDate(LocalDateTime.now());
        List<OrderInfoDtlVo> orderInfoDtlVoList = new ArrayList<>();
        orderInfoDB.getOrderGoodsDeliveryList().forEach(orderGoods -> {
            OrderInfoDtlVo orderInfoDtlVo = new OrderInfoDtlVo();

            orderInfoDtlVo.setAmountDiscount(IncaUtils.toErpPriceDouble4(orderGoods.getB2bPriceDiscount()));
            orderInfoDtlVo.setBatchId(orderGoods.getBatchid());
            orderInfoDtlVo.setLotId(orderGoods.getLotid());
            orderInfoDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
            orderInfoDtlVo.setSrcErpOrderId(orderGoods.getSrcErpOrderId());
            orderInfoDtlVo.setSrcOrderId(Long.valueOf(orderGoods.getB2bOrderId()));
            orderInfoDtlVo.setSrcOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
            orderInfoDtlVo.setOrderId(Long.valueOf(orderGoods.getB2bOrderId()));
            orderInfoDtlVo.setOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
            orderInfoDtlVo.setGoodsId(orderGoods.getGoodsid());
            orderInfoDtlVo.setGoodsSource(orderGoods.getGoodsSource().intValue());
            orderInfoDtlVo.setNum(orderGoods.getDtlgoodsqty());
            orderInfoDtlVo.setPrice(IncaUtils.toErpPriceDouble4( orderGoods.getUnitprice()));
            orderInfoDtlVo.setAmount(IncaUtils.toErpPriceDouble4( orderGoods.getTotal()));
            orderInfoDtlVo.setPriceDiscount(IncaUtils.toErpPriceDouble4(orderGoods.getB2bPriceDiscount()));
            orderInfoDtlVo.setOrderNo(orderGoods.getB2bOrderNo());
            orderInfoDtlVo.setPriceId(orderGoods.getPriceId());
            orderInfoDtlVo.setStorageId(orderGoods.getErpStorageId());
            orderInfoDtlVo.setLotNo(orderGoods.getLotno());
            orderInfoDtlVoList.add(orderInfoDtlVo);
        });


        this.orderService.receiptOrder(orderInfoDocVo);

        this.orderInfoMapper.updateById(orderInfoDB);*/
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("客户确认收货");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setDoneTime(LocalDateTime.now());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Transactional
    @Override
    public void sureOrderToDeliveryJob() {
        List<OrderInfo> orderInfoList = this.orderInfoMapper.selectList(new QueryWrapper<OrderInfo>().in("orderState", OrderInfo.EOrderState.TO_RECEIVED.val(), OrderInfo.EOrderState.DONE.val()));
        if (null != orderInfoList && orderInfoList.size() != 0) {
            for (OrderInfo orderInfo : orderInfoList) {
                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                OrderInfoVo orderInfoDB = this.getOrderInfoById(orderInfo.getId());

                OrderInfoDocVo orderInfoDocVo = new OrderInfoDocVo();
                orderInfoDocVo.setOrderFrom(orderInfoDB.getOrderFrom());
                orderInfoDocVo.setOrderNo(orderInfoDB.getOrderNo());
                //下单人
                orderInfoDocVo.setCustomId(orderInfoDB.getErpCustomerId());
                orderInfoDocVo.setOrderId(orderInfoDB.getId());
                orderInfoDocVo.setTargetPosId(orderInfoDB.getTargetPosId());
                orderInfoDocVo.setOrderType(orderInfoDB.getOrderType());
                orderInfoDocVo.setEntryId(1);
                //TODO 收货人！=下单人
                orderInfoDocVo.setReceiptManId(0L);
                orderInfoDocVo.setCreateDate(LocalDateTime.now());
                List<OrderInfoDtlVo> orderInfoDtlVoList = new ArrayList<>();
                orderInfoDB.getOrderGoodsDeliveryList().forEach(orderGoods -> {
                    OrderInfoDtlVo orderInfoDtlVo = new OrderInfoDtlVo();

                    orderInfoDtlVo.setAmountDiscount(IncaUtils.toErpPriceDouble4(orderGoods.getB2bPriceDiscount()));
                    orderInfoDtlVo.setBatchId(orderGoods.getBatchid());
                    orderInfoDtlVo.setLotId(orderGoods.getLotid());
                    orderInfoDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
                    orderInfoDtlVo.setSrcErpOrderId(orderGoods.getSrcErpOrderId());
                    orderInfoDtlVo.setSrcOrderId(Long.valueOf(orderGoods.getB2bOrderId()));
                    orderInfoDtlVo.setSrcOrderDtlId(orderGoods.getB2bOrderDtlId());
                    orderInfoDtlVo.setOrderId(Long.valueOf(orderGoods.getB2bOrderId()));
                    orderInfoDtlVo.setOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                    orderInfoDtlVo.setGoodsId(orderGoods.getGoodsid());
                    orderInfoDtlVo.setGoodsSource(orderGoods.getGoodsSource().intValue());
                    orderInfoDtlVo.setNum(orderGoods.getDtlgoodsqty());
                    orderInfoDtlVo.setPrice(IncaUtils.toErpPriceDouble4(orderGoods.getUnitprice()));
                    orderInfoDtlVo.setAmount(IncaUtils.toErpPriceDouble4(orderGoods.getTotal()));
                    orderInfoDtlVo.setPriceDiscount(IncaUtils.toErpPriceDouble4(orderGoods.getB2bPriceDiscount()));
                    orderInfoDtlVo.setOrderNo(orderGoods.getB2bOrderNo());
                    orderInfoDtlVo.setPriceId(orderGoods.getPriceId());
                    orderInfoDtlVo.setStorageId(orderGoods.getErpStorageId());
                    orderInfoDtlVo.setLotNo(orderGoods.getLotno());
                    orderInfoDtlVoList.add(orderInfoDtlVo);
                });

                OrderResultDocVo orderResultDocVo = this.orderService.receiptOrder(orderInfoDocVo);

                if (orderResultDocVo.getErrorCode() == 0) {
                    Log.info("erp已收货--{}", orderResultDocVo.toString());

                    orderInfo.setOrderState(OrderInfo.EOrderState.ERP_TO_RECEIVED.val());
                    this.orderInfoMapper.updateById(orderInfo);


                    orderLog.setLogMsg("erp已收货");
                    orderLog.setOrderId(orderInfoDB.getId());
                    orderLog.setOrderStatus(orderInfoDB.getOrderState());
                    orderLog.setDoneTime(LocalDateTime.now());
                    orderLog.setToErpNum("v1");
                    this.orderLogMapper.insert(orderLog);
                }

            }
        }
    }

    @Override
    public void updateAdminOrderState(Long id, String orderState, Long userId) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(orderState, "状态为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfoDB, "无效id[%s]", id);
        orderInfoDB.setOrderState(orderState);
        if (orderState.equals(OrderInfo.EOrderState.TO_ERP.val())) {
            this.reErpOrder(id);
        } else {
            this.orderInfoMapper.updateById(orderInfoDB);
            OrderLog orderLog = new OrderLog();
            orderLog.setCreateTime(LocalDateTime.now());
            orderLog.setLogMsg("后台修改订单状态");
            orderLog.setOrderId(orderInfoDB.getId());
            orderLog.setOrderStatus(orderInfoDB.getOrderState());
            orderLog.setDoneTime(LocalDateTime.now());
            orderLog.setUserId(userId);
            orderLog.setToErpNum("v1");
            this.orderLogMapper.insert(orderLog);
        }
    }

    @Override
    public Integer getB2BOrderNum() {
        // 当天日期
        LocalDateTime minDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime maxDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        Date startTime = Date.from(minDate.atZone(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(maxDate.atZone(ZoneId.systemDefault()).toInstant());
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("CREATE_TIME", startTime)
                .lt("CREATE_TIME", endTime);
        List<OrderInfo> orderInfoList = this.orderInfoMapper.selectList(queryWrapper);
        return orderInfoList != null && orderInfoList.size() > 0 ? orderInfoList.size() : 0;
    }

    @Override
    public List<OrderInfo> getInterceptOrderList() {

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ORDER_STATE", OrderInfo.EOrderState.INTERCEPT.val())
                .orderByDesc("CREATE_TIME");
        List<OrderInfo> orderInfoList = this.orderInfoMapper.selectList(queryWrapper);

        return orderInfoList;
    }

    @Override
    public List<OrderGoodsVo> getTwoBuyGoodsInfoVo(Long orderId, Long memberId, String ip) {
        AssertExt.hasId(orderId, "订单id为空");
        AssertExt.hasId(memberId, "memberId为空");
        Member memberDB = this.memberMapper.selectById(memberId);
        List<OrderGoodsVo> orderGoodsVoList = this.orderGoodsMapper.getTwoBuyGoodsInfoVo(orderId, memberDB.getErpUserId());
        orderGoodsVoList.forEach(orderGoodsVo -> {
            Long[] storageIds = new Long[]{Long.valueOf(orderGoodsVo.getErpStorageId())};
            int stqty = this.goodsInfoMapper.getStqty(orderGoodsVo.getErpGoodsId(), memberDB.getErpUserId(), storageIds, orderGoodsVo.getAccflag(), orderGoodsVo.getLotId());
            if (orderGoodsVo.getGoodsNum() > stqty) {
                orderGoodsVo.setGoodsNum((stqty));
                orderGoodsVo.setUpdateGoodsName(orderGoodsVo.getGoodsName());
                orderGoodsVo.setUpdateGoodsNum(orderGoodsVo.getGoodsNum());
            }
        });

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        memberLog.setContent("再次购买:" + orderId);
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);

        return orderGoodsVoList;
    }

    @Override
    public List<CouponReceiveVo> getCouponByOrder(Long orderId) {
        AssertExt.hasId(orderId, "订单id为空");
        return this.couponMapper.getCouponByOrder(orderId);
    }

    @Override
    public List<CouponVo> getByCartCoupon(Long memberId, List<Cart> cartList, String type) {
        AssertExt.notNull(cartList, "购物车数据为空");
        Member member = this.memberMapper.selectById(memberId);
        List<Long> longList = cartList.stream().map(cart -> {
            return cart.getErpGoodsId();
        }).collect(Collectors.toList());
        //我的可使用优惠券
        List<CouponVo> couponVoList = this.couponMapper.getByCartCoupon(member.getErpUserId(), longList, type);
        //List<CouponVo> couponVoList1 = new ArrayList<>();
        if (null != couponVoList && couponVoList.size() > 0) {
            couponVoList.forEach(couponVo -> {
                couponVo.setRemark("优惠券可用");
                if (!couponVo.getType().equals(Coupon.EType.CASH_TICKET.val())) {
                    List<Cart> cartList1 = new ArrayList<>();
                    if (couponVo.getGoodsSetType().equals(Coupon.EGoodsSetType.PART.val())) {
                        List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetById(longList, couponVo.getId());
                        couponVo.setErpGoodsList(erpGoodsList);
                        if (null != erpGoodsList || erpGoodsList.size() > 0) {
                            cartList.forEach(cart -> {
                                if (erpGoodsList.stream().anyMatch(item -> item.equals(cart.getErpGoodsId()))) {
                                    cartList1.add(cart);
                                }
                            });
                            if (null != cartList1 && cartList1.size() > 0) {

                                double couponAccount = 0L;
                                double couponAccountPrice = 0L;
                                for (Cart cart : cartList1) {
                                    GoodsInfoVo goodsInfoVo = this.goodsInfoMapper.getGoodsInfo(cart.getErpGoodsId(), member.getErpUserId(), Math.toIntExact(cart.getStorageId()));

                                    if (cart.getStorageId() == 5) {
                                        goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(cart.getErpGoodsId(), member.getErpUserId(), cart.getLotId());
                                    }
                                    double cartPrice = (IncaUtils.toErpPriceDouble4(cart.getGoodsNum() * goodsInfoVo.getPrice() * goodsInfoVo.getZxB2bNumLimit()));

                                    if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                        double couponDisAccount = (IncaUtils.toErpPriceDouble4(cartPrice * couponVo.getDiscount()));
                                        couponAccount += cartPrice - couponDisAccount;
                                    }
                                    couponAccountPrice += cartPrice;

                                }
                                if (couponAccountPrice < couponVo.getFullAmount()) {
                                    couponVo.setRemark("优惠券不可用,不满足金额:" + couponVo.getFullAmount());
                                }

                                if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                    couponVo.setCouponDisAccount(couponAccount);
                                }

                            } else {
                                couponVo.setRemark("优惠券不可用,不满足金额:" + couponVo.getFullAmount());
                            }
                        }
                    } else if (couponVo.getGoodsSetType().equals(Coupon.EGoodsSetType.UN_VISIBLE.val())) {
                        List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetByIdNoSee(couponVo.getId());
                        cartList.forEach(cart -> {
                            if (erpGoodsList.stream().anyMatch(item -> !item.equals(cart.getErpGoodsId()))) {
                                cartList1.add(cart);
                            }
                        });

                        if (null != cartList1 && cartList1.size() > 0) {

                            double couponAccount = 0L;
                            for (Cart cart : cartList1) {
                                GoodsInfoVo goodsInfoVo = this.goodsInfoMapper.getGoodsInfo(cart.getErpGoodsId(), member.getErpUserId(), Math.toIntExact(cart.getStorageId()));

                                if (cart.getStorageId() == 5) {
                                    goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(cart.getErpGoodsId(), member.getErpUserId(), cart.getLotId());
                                }
                                double cartPrice = (IncaUtils.toErpPriceDouble4(cart.getGoodsNum() * goodsInfoVo.getPrice() * goodsInfoVo.getZxB2bNumLimit()));
                                if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                    double couponDisAccount = (IncaUtils.toErpPriceDouble4(cartPrice * couponVo.getDiscount()));
                                    couponAccount += cartPrice - couponDisAccount;
                                } else {
                                    couponAccount += cartPrice;
                                }


                            }
                            if (couponAccount < couponVo.getFullAmount()) {
                                couponVo.setRemark("优惠券不可用,不满足金额:" + couponVo.getFullAmount());
                            }

                            if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                couponVo.setCouponDisAccount(couponAccount);
                            }

                        } else {
                            couponVo.setRemark("优惠券不可用,不满足金额:" + couponVo.getFullAmount());
                        }
                    } else {

                        double couponAccount = 0L;
                        double couponAccountPrice = 0L;
                        for (Cart cart : cartList) {
                            GoodsInfoVo goodsInfoVo = this.goodsInfoMapper.getGoodsInfo(cart.getErpGoodsId(), member.getErpUserId(), Math.toIntExact(cart.getStorageId()));

                            if (cart.getStorageId() == 5) {
                                goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(cart.getErpGoodsId(), member.getErpUserId(), cart.getLotId());
                            }

                            double cartPrice = (IncaUtils.toErpPriceDouble4(cart.getGoodsNum() * goodsInfoVo.getPrice() * goodsInfoVo.getZxB2bNumLimit()));
                            if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                double couponDisAccount = (IncaUtils.toErpPriceDouble4(cartPrice * couponVo.getDiscount()));
                                couponAccount += cartPrice - couponDisAccount;
                            }
                            couponAccountPrice += cartPrice;
                        }

                        if (couponAccountPrice < couponVo.getFullAmount()) {
                            couponVo.setRemark("优惠券不可用,不满足金额:" + couponVo.getFullAmount());
                        }
                        if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                            couponVo.setCouponDisAccount(couponAccount);
                        }

                    }
                }
            });
        }

        return couponVoList;
    }

    @Override
    public List<CouponVo> getByCartCoupon1(Long memberId) {
        AssertExt.hasId(memberId, "memberId为空");
        Member member = this.memberMapper.selectById(memberId);
        Map<Long, List<CartVo>> listMap = this.getCartToActivity(memberId, Constant.IS_PITCH_ON.ON);

        if (listMap.size() == 0) return new ArrayList<>();

        List<CartVo> cartVoListMap = new ArrayList<>();
        for (Map.Entry<Long, List<CartVo>> entry : listMap.entrySet()) {
            cartVoListMap.addAll(entry.getValue());
        }
        List<CartVo> cartVoList = new ArrayList<>();
        cartVoListMap.forEach(cartVo -> {
            if (cartVo.getGoodType() != 4 && cartVo.getGoodType() != 9 && cartVo.getStorageId() != 691) {
                cartVoList.add(cartVo);
            }
        });


        List<Long> longList = cartVoList.stream().map(cart -> {
            return cart.getErpGoodsId();
        }).collect(Collectors.toList());

        if (longList.size() == 0) return new ArrayList<>();

        //我的可使用优惠券
        List<CouponVo> couponVoList = this.couponMapper.getByCartCoupon(member.getErpUserId(), longList, null);

        if (null != couponVoList && couponVoList.size() > 0) {
            couponVoList.forEach(couponVo -> {
                couponVo.setRemark("优惠券可用");
                if (!couponVo.getType().equals(Coupon.EType.CASH_TICKET.val())) {
                    List<CartVo> cartList1 = new ArrayList<>();
                    if (couponVo.getGoodsSetType().equals(Coupon.EGoodsSetType.PART.val())) {
                        List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetById(longList, couponVo.getId());
                        couponVo.setErpGoodsList(erpGoodsList);
                        if (null != erpGoodsList || erpGoodsList.size() > 0) {
                            cartVoList.forEach(cart -> {
                                if (erpGoodsList.stream().anyMatch(item -> item.equals(cart.getErpGoodsId()))) {
                                    cartList1.add(cart);
                                }
                            });
                            if (null != cartList1 && cartList1.size() > 0) {

                                double couponAccount = 0L;
                                double couponAccountPrice = 0L;
                                for (CartVo cart : cartList1) {

                                    double cartPrice = (IncaUtils.toErpPriceDouble4(cart.getGoodsNum() * cart.getGoodsPayPrice() * cart.getZxB2bNumLimit()));

                                    if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                        double couponDisAccount = (IncaUtils.toErpPriceDouble4(cartPrice * couponVo.getDiscount()));
                                        couponAccount += cartPrice - couponDisAccount;
                                    }
                                    couponAccountPrice += cartPrice;

                                }
                                if (couponAccountPrice < couponVo.getFullAmount()) {
                                    couponVo.setRemark("优惠券不可用,不满足金额:" + (double) couponVo.getFullAmount());
                                    couponVo.setDifferenceAmount(couponVo.getFullAmount() - couponAccountPrice);
                                }

                                if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                    couponVo.setCouponDisAccount(couponAccount);
                                }

                            } else {
                                couponVo.setRemark("优惠券不可用,不满足金额:" + (double) couponVo.getFullAmount());
                                couponVo.setDifferenceAmount(couponVo.getFullAmount());
                            }
                        }
                    } else if (couponVo.getGoodsSetType().equals(Coupon.EGoodsSetType.UN_VISIBLE.val())) {
                        List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetByIdNoSee(couponVo.getId());
                        cartVoList.forEach(cart -> {
                            if (erpGoodsList.stream().anyMatch(item -> !item.equals(cart.getErpGoodsId()))) {
                                cartList1.add(cart);
                            }
                        });

                        if (null != cartList1 && cartList1.size() > 0) {

                            double couponAccount = 0L;
                            for (CartVo cart : cartList1) {

                                double cartPrice = (IncaUtils.toErpPriceDouble4(cart.getGoodsNum() * cart.getGoodsPayPrice() * cart.getZxB2bNumLimit()));
                                if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                    double couponDisAccount = (IncaUtils.toErpPriceDouble4(cartPrice * couponVo.getDiscount()));
                                    couponAccount += cartPrice - couponDisAccount;
                                } else {
                                    couponAccount += cartPrice;
                                }


                            }
                            if (couponAccount < couponVo.getFullAmount()) {
                                couponVo.setRemark("优惠券不可用,不满足金额:" + (double) couponVo.getFullAmount());
                                couponVo.setDifferenceAmount(couponVo.getFullAmount() - couponAccount);
                            }

                            if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                couponVo.setCouponDisAccount(couponAccount);
                            }

                        } else {
                            couponVo.setRemark("优惠券不可用,不满足金额:" + (double) couponVo.getFullAmount());
                            couponVo.setDifferenceAmount(couponVo.getFullAmount());
                        }
                    } else {

                        double couponAccount = 0L;
                        double couponAccountPrice = 0L;
                        for (CartVo cart : cartVoList) {
                            if (cart.getStorageId() == 691) continue;
                            double cartPrice = (IncaUtils.toErpPriceDouble4(cart.getGoodsNum() * cart.getGoodsPayPrice() * cart.getZxB2bNumLimit()));
                            if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                                double couponDisAccount = (IncaUtils.toErpPriceDouble4(cartPrice * couponVo.getDiscount()));
                                couponAccount += cartPrice - couponDisAccount;
                            }
                            couponAccountPrice += cartPrice;


                        }

                        if (couponAccountPrice < couponVo.getFullAmount()) {
                            couponVo.setRemark("优惠券不可用,不满足金额:" + (double) couponVo.getFullAmount());
                            couponVo.setDifferenceAmount(couponVo.getFullAmount() - couponAccountPrice);
                        }
                        if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                            couponVo.setCouponDisAccount(couponAccount);
                        }

                    }
                }
            });
        }
        return couponVoList;
    }

    @Override
    @Transactional
    public Map<Long, List<CartVo>> getCartToActivity(Long memberId, Long pitchOn) {
        AssertExt.hasId(memberId, "memberId为空");
        Member member = this.memberMapper.selectById(memberId);
        //删除临时抽奖次数
        this.lotterDailQualificationsMapper.delLotterDailQualificationsByMemberId(memberId);

        Map<Long, List<CartVo>> map = new HashMap<>();
        List<OrderInfo> orderInfoList = this.orderInfoMapper.selectList(new QueryWrapper<OrderInfo>().eq("MEMBER_ID", memberId));
        double orderPrice = 0L;
        String goodsId = null;
        //首次下单减30
        if (orderInfoList.size() == 0) {
            String goodsIds = this.settingService.getOneOrderPay("ONE_ORDER_PAY");
            if (null != goodsIds) {
                orderPrice = 30;
                goodsId = goodsIds;
            }
        }
        log.info("map--begin{}");
        List<CartVo> cartVoList = this.cartMapper.getPitchOnList(memberId, pitchOn, null, 0);

        if (null != cartVoList && cartVoList.size() > 0) {


            List<Long> longList = new ArrayList<>();

            List<Long> activityIdList = new ArrayList<>();

            //特价商品
            List<CartVo> cartVoList5 = new ArrayList<>();

            //积分商品
            List<CartVo> cartVoList9 = new ArrayList<>();

            for (CartVo cartVo : cartVoList) {
                if (cartVo.getPitchOn() == Constant.IS_PITCH_ON.ON) {
                    if (cartVo.getStorageId() == 1 || cartVo.getStorageId() == 732) {
                        longList.add(cartVo.getErpGoodsId());
                    }
                }

                if (null != cartVo.getActivityId() && cartVo.getActivityId() > 0) {
                    activityIdList.add(cartVo.getActivityId());
                }
            }

            List<CartVo> cartVoListNo = new ArrayList<>();
            List<CartVo> cartVoListYes = new ArrayList<>();
            for (CartVo cartVo : cartVoList) {
                cartVo.setRptAmount(0L);
                cartVo.setOrderPrice(0L);
                cartVo.setGoodType(1);
                Long[] storageIds = Constant.STO_RANGE_IDS;
                if (null != cartVo.getStorageId() && cartVo.getStorageId() == 5) {
                    storageIds = Constant.STO_RANGE_IDS_V;
                    //goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(goodsid, memberDB.getErpUserId(), lotid);
                } else if (cartVo.getStorageId() == 691) {
                    storageIds = new Long[]{691L};
                }

                int stqty = this.goodsInfoMapper.getStqty(cartVo.getErpGoodsId(), member.getErpUserId(), storageIds, cartVo.getAccflag(), cartVo.getLotId());
                //AssertExt.isTrue(stqty - cartVo.getGoodsNum() >= 0, "[%s]库存不足", cartVo.getGoodsName());
                cartVo.setStqty(stqty);

                if (cartVo.getStorageId() == 691) {
                    cartVo.setGoodType(9);
                    cartVo.setScoreTotal(cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit() * cartVo.getConvertibleIntegral());
                    cartVoList9.add(cartVo);
                    continue;
                }

                cartVo.setGoodsPayPrice(cartVo.getGoodsPrice());

                //原价
                List<PubGoodsPriceVo> pubGoodsPriceList5 = this.goodsInfoMapper.getPubGoodsPriceList(cartVo.getErpGoodsId(), member.getErpUserId());

                //特价
                if (cartVo.getStorageId() == 5) {
                    cartVo.setGoodType(5);
                    pubGoodsPriceList5 = this.goodsInfoMapper.getPubGoodsPriceList5(cartVo.getErpGoodsId(), member.getErpUserId());
                }
                if (null != pubGoodsPriceList5 && pubGoodsPriceList5.size() != 0) {
                    cartVo.setGoodsPrice(pubGoodsPriceList5.get(0).getPrice());
                    cartVo.setGoodsPayPrice(pubGoodsPriceList5.get(0).getPrice());
                }

                //单价
                double activityPrice = cartVo.getGoodsPrice();

                cartVo.setPubGoodsPriceList(pubGoodsPriceList5);

                double erpLeastsaleqty = cartVo.getZxB2bNumLimit();
                //商品单价小计
                double amount = (cartVo.getGoodsPrice() * cartVo.getGoodsNum() * erpLeastsaleqty) * 1L;
                //优惠后的小计
                double priceTotal = (cartVo.getGoodsPrice() * cartVo.getGoodsNum() * erpLeastsaleqty) * 1L;
                //商品数量小计
                double numTotal = cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit();

                //原价小计
                cartVo.setAmountNum(IncaUtils.toErpPriceDouble(amount));
                //优惠后小计
                cartVo.setAmountPay(IncaUtils.toErpPriceDouble(priceTotal));
                //数量小计-用来叠加减
                cartVo.setNumPay(numTotal);
                //数量小计
                cartVo.setNumTotal(numTotal);
                //优惠后小计-用来叠加减
                cartVo.setAmountPay1(IncaUtils.toErpPriceDouble(priceTotal));

                if (cartVo.getStorageId() == 5) {
                    cartVoList5.add(cartVo);
                    continue;
                }


                //抽奖
                ActivityVo activityVo110 = this.activityMapper.getActivity90ByGoods(110L, cartVo.getErpGoodsId(), member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());

                if (null != activityVo110) {
                    LotterDailQualifications lotterDailQualificationsDB = this.lotterDailQualificationsMapper.selectOne(new QueryWrapper<LotterDailQualifications>().eq("LOT_ID", activityVo110.getId()).eq("member_id", memberId).eq("status", LotterDailQualifications.EStatus.TMP.val()));
                    if (null == lotterDailQualificationsDB) {
                        QueryWrapper queryWrapper = new QueryWrapper<ActivityStrategy>().eq("ACTIVITY_ID", activityVo110.getId()).eq("IS_USE", Constant.IS_USE.ON);

                        queryWrapper.orderByDesc("id");
                        List<ActivityStrategy> activityStrategyList110 = this.activityStrategyMapper.selectList(queryWrapper);
                        Integer lotNum = 0;
                        double asNum10 = 0;
                        double asPrice10 = 0;
                        for (CartVo cartVoNP : cartVoList) {
                            if (cartVoNP.getPitchOn() == 1) {
                                asNum10 += cartVoNP.getGoodsNum() * cartVoNP.getZxB2bNumLimit();

                                List<PubGoodsPriceVo> pubGoodsPriceList10 = this.goodsInfoMapper.getPubGoodsPriceList(cartVoNP.getErpGoodsId(), member.getErpUserId());


                                asPrice10 += IncaUtils.toErpPriceDouble4(pubGoodsPriceList10.get(0).getPrice() * cartVoNP.getGoodsNum() * cartVoNP.getZxB2bNumLimit());
                            }
                        }
                        for (ActivityStrategy activityStrategyDB : activityStrategyList110) {

                            //任选商品数量
                            if (activityStrategyDB.getGoodsStrategy() == 10) {
                                boolean a = true;

                                Integer goodsCount = this.activityGoodsMapper.selectCount(new QueryWrapper<ActivityGoods>().eq("IS_USE", Constant.IS_USE.ON).eq("ACTIVITY_ID", activityVo110.getId()).eq("AS_ID", activityStrategyDB.getId()));
                                if (goodsCount != cartVoList.size()) continue;

                                List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.getActivityGoodsByAcIdAsId(activityVo110.getId(), activityStrategyDB.getId());

                                do {
                                    outterLoop:
                                    for (ActivityGoods activityGoods : activityGoodsList) {
                                        for (CartVo cart10 : cartVoList) {
                                            if (cart10.getPitchOn() == 2) {
                                                a = false;
                                                break outterLoop;
                                            }
                                            if (cart10.getErpGoodsId().equals(activityGoods.getErpGoodsId())) {
                                               /* //判断策略商品策略数量是否有上限
                                                if (activityStrategyDB.getTopLimit() != 0) {
                                                     AssertExt.isTrue(cartVo.getNumPay() <= activityStrategyDB.getTopLimit(), "该商品[%s]限购[%s]", activityGoods.getGoodsName(), activityGoods.getTopLimit());
                                                }*/
                                                //判断是否满足数量
                                                AssertExt.isFalse(0 == activityGoods.getConditionNum(), "商品满足数量为空或者为0【%s】-【%s】-【%s】", activityGoods.getGoodsName(), activityGoods.getActivityId(), activityGoods.getAsId());

                                                if (cart10.getNumPay() >= activityGoods.getConditionNum()) {
                                                    a = true;
                                                } else {
                                                    a = false;
                                                    break outterLoop;
                                                }
                                            }
                                        }
                                    }

                                    if (a) {
                                        if (activityVo110.getIsSuperposition() == 1) {
                                            lotNum += activityStrategyDB.getLotNum();
                                            cartVoList.forEach(cart10 -> {
                                                ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityVo110.getId(), activityStrategyDB.getId(), cart10.getErpGoodsId());
                                                cart10.setNumPay(cart10.getNumPay() - activityGoodsDB.getConditionNum());
                                            });
                                            a = true;

                                        } else {

                                            lotNum = activityStrategyDB.getLotNum();
                                            a = false;
                                        }
                                    }


                                } while (a);
                                continue;
                            }
                            //任选商品金额
                            else if (activityStrategyDB.getGoodsStrategy() == 30) {

                                boolean a = true;
                                Integer goodsCount = this.activityGoodsMapper.selectCount(new QueryWrapper<ActivityGoods>().eq("IS_USE", Constant.IS_USE.ON).eq("ACTIVITY_ID", activityVo110.getId()).eq("AS_ID", activityStrategyDB.getId()));

                                if (goodsCount != cartVoList.size()) continue;
                                List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.getActivityGoodsByAcIdAsId(activityVo110.getId(), activityStrategyDB.getId());

                                do {
                                    outterLoop:
                                    for (ActivityGoods activityGoods : activityGoodsList) {
                                        for (CartVo cartVo100 : cartVoList) {
                                            if (cartVo.getPitchOn() == 2) {
                                                a = false;
                                                break outterLoop;
                                            }
                                            if (cartVo100.getErpGoodsId().equals(activityGoods.getErpGoodsId())) {
                                                //判断是否满足价格
                                                AssertExt.isFalse(0 == activityGoods.getConditionPrice(), "商品满足金额为空或者为0【%s】-【%s】-【%s】", activityGoods.getGoodsName(), activityGoods.getActivityId(), activityGoods.getAsId());

                                                if (cartVo100.getAmountPay1() >= activityGoods.getConditionPrice()) {
                                                    a = true;
                                                } else {
                                                    a = false;
                                                    break outterLoop;
                                                }
                                            }
                                        }
                                    }

                                    if (a) {
                                        if (activityVo110.getIsSuperposition() == 1) {

                                            lotNum += activityStrategyDB.getLotNum();

                                            cartVoList.forEach(cartVo100 -> {
                                                ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityVo110.getId(), activityStrategyDB.getId(), cartVo100.getErpGoodsId());
                                                cartVo100.setAmountPay1(cartVo100.getAmountPay1() - activityGoodsDB.getConditionPrice());
                                            });

                                            a = true;

                                        } else {
                                            lotNum = activityStrategyDB.getLotNum();
                                            a = false;
                                        }

                                    }
                                    if (activityVo110.getIsSuperposition() != 1) {
                                        break;
                                    }

                                } while (a);
                                continue;
                            }
                            //任选策略金额
                            else if (activityStrategyDB.getGoodsStrategy() == 40) {
                                //判断是否满足金额
                                AssertExt.isFalse(0 == activityStrategyDB.getAmountSatisfied(), "满足金额为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                if (asPrice10 >= activityStrategyDB.getAmountSatisfied()) {
                                    if (activityVo110.getIsSuperposition() == 1) {
                                        int price = (int) (asPrice10 / activityStrategyDB.getAmountSatisfied());

                                        lotNum += price * activityStrategyDB.getLotNum();

                                    } else {
                                        lotNum = activityStrategyDB.getLotNum();

                                    }

                                    if (activityVo110.getIsSuperposition() != 1) {
                                        break;
                                    }
                                }
                                continue;

                            }
                            //任选策略数量
                            else {
                                //判断是否满足数量
                                AssertExt.isFalse(0 == activityStrategyDB.getMeetQuantity(), "满足数量为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                if (asNum10 >= activityStrategyDB.getMeetQuantity()) {
                                    if (activityVo110.getIsSuperposition() == 1) {
                                        int num = (int) (asNum10 / activityStrategyDB.getMeetQuantity());

                                        lotNum += num * activityStrategyDB.getLotNum();


                                    } else {

                                        lotNum = activityStrategyDB.getLotNum();
                                    }

                                    if (activityVo110.getIsSuperposition() != 1) {
                                        break;
                                    }
                                }
                                continue;
                            }
                        }
                        if (lotNum > 0) {
                            log.info("lotNum++++++{}", lotNum);
                            LotterDailQualifications lotterDailQualifications = new LotterDailQualifications();
                            lotterDailQualifications.setMemberId(memberId);
                            lotterDailQualifications.setLotId(activityVo110.getId());
                            lotterDailQualifications.setLotNum(lotNum);
                            lotterDailQualifications.setStatus(LotterDailQualifications.EStatus.TMP.val());
                            lotterDailQualifications.setCreateTime(LocalDateTime.now());
                            if (null != activityVo110.getLotEndTime()) {
                                lotterDailQualifications.setLotEndTime(activityVo110.getLotEndTime().plusDays(1));
                            }
                            this.lotterDailQualificationsMapper.insert(lotterDailQualifications);

                        }
                    }

                }


                //单品赠start---
                ActivityVo activityVo9 = this.activityMapper.getActivity90ByGoods(90L, cartVo.getErpGoodsId(), member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
                if (null != activityVo9) {
                    QueryWrapper queryWrapper = new QueryWrapper<ActivityStrategy>().eq("ACTIVITY_ID", activityVo9.getId()).eq("IS_USE", Constant.IS_USE.ON);

                    queryWrapper.orderByDesc("id");
                    List<ActivityStrategy> activityStrategyList9 = this.activityStrategyMapper.selectList(queryWrapper);

                    for (ActivityStrategy activityStrategy9 : activityStrategyList9) {
                        ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityVo9.getId(), activityStrategy9.getId(), cartVo.getErpGoodsId());

                        //任选商品数量
                        if (activityStrategy9.getGoodsStrategy() == 10) {

                            AssertExt.isFalse(0 == activityGoodsDB.getConditionNum(), "商品满足数量为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());

                            //判断是否满足数量
                            if (numTotal >= activityGoodsDB.getConditionNum()) {
                                List<ActivityGiftVo> activityGiftList = this.activityGiftMapper.getActivityGiftVoList(activityStrategy9.getId());
                                List<ActivityGiftVo> activityGiftList9 = new ArrayList<>();
                                if (activityVo9.getIsSuperposition() == 1) {
                                    int num = (int) (numTotal / activityGoodsDB.getConditionNum());

                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum() * num) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum() * num);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum() * num) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum() * num);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }

                                    }


                                } else {
                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0L;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum()) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum());
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {

                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum()) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum());
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }

                                }

                                cartVo.setActivityGiftList(activityGiftList9);
                                cartVo.setRemark("参与单品赠--" + activityVo9.getId());
                                break;
                            }
                        }

                        //任选商品金额
                        else if (activityStrategy9.getGoodsStrategy() == 30) {
                            AssertExt.isFalse(0 == activityGoodsDB.getConditionPrice(), "商品满足金额为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());
                            //判断是否满足金额
                            if (priceTotal >= activityGoodsDB.getConditionPrice()) {
                                List<ActivityGiftVo> activityGiftList = this.activityGiftMapper.getActivityGiftVoList(activityStrategy9.getId());
                                List<ActivityGiftVo> activityGiftList9 = new ArrayList<>();
                                if (activityVo9.getIsSuperposition() == 1) {
                                    int num = (int) (priceTotal / activityGoodsDB.getConditionPrice());

                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum() * num);
                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum() * num) * goodsPrice);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {

                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum() * num);
                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum() * num) * goodsPrice);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                } else {


                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum());
                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum()) * goodsPrice);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {

                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum());
                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum()) * goodsPrice);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }


                                }


                                cartVo.setActivityGiftList(activityGiftList9);
                                cartVo.setRemark("参与单品赠--" + activityVo9.getId());
                                break;
                            }

                        }

                        //任选商品策略数量
                        else if (activityStrategy9.getGoodsStrategy() == 20) {

                            AssertExt.isFalse(0 == activityStrategy9.getMeetQuantity(), "满足数量为空或者为0【%s】-【%s】-【%s】", activityStrategy9.getStrategyName(), activityStrategy9.getActivityId(), activityStrategy9.getId());//判断是否满足数量
                            if (numTotal >= activityStrategy9.getMeetQuantity()) {
                                List<ActivityGiftVo> activityGiftList = this.activityGiftMapper.getActivityGiftVoList(activityStrategy9.getId());
                                List<ActivityGiftVo> activityGiftList9 = new ArrayList<>();
                                if (activityVo9.getIsSuperposition() == 1) {
                                    int num = (int) (numTotal / activityStrategy9.getMeetQuantity());

                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }

                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");


                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum() * num) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum() * num);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");


                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum() * num) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum() * num);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }

                                    }

                                } else {

                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");


                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum()) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum());
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");


                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum()) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum());
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }

                                    }

                                }


                                cartVo.setActivityGiftList(activityGiftList9);
                                cartVo.setRemark("参与单品赠--" + activityVo9.getId());
                                break;
                            }

                        } else {
                            AssertExt.isFalse(0 == activityStrategy9.getAmountSatisfied(), "满足金额为空或者为0【%s】-【%s】-【%s】", activityStrategy9.getStrategyName(), activityStrategy9.getActivityId(), activityStrategy9.getId());//任选商品策略金额
                            if (priceTotal >= activityStrategy9.getAmountSatisfied()) {
                                List<ActivityGiftVo> activityGiftList = this.activityGiftMapper.getActivityGiftVoList(activityStrategy9.getId());
                                List<ActivityGiftVo> activityGiftList9 = new ArrayList<>();
                                if (activityVo9.getIsSuperposition() == 1) {
                                    int num = (int) (priceTotal / activityStrategy9.getAmountSatisfied());

                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum() * num);
                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum() * num) * goodsPrice);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum() * num) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum() * num);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }

                                    }
                                } else {

                                    //任选赠品策略
                                    if (activityStrategy9.getGiftStrategy() == 10) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");

                                            activityGiftVo.setGiftNum(activityGiftVo.getGiftNum());
                                            activityGiftVo.setAmountPay((activityGiftVo.getGiftNum()) * goodsPrice);
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }
                                    }
                                    //固定赠品策略
                                    else if (activityStrategy9.getGiftStrategy() == 30) {
                                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                                            Long[] giftStorageIds = new Long[]{activityGiftVo.getStorageId()};
                                            List<Long> longList1 = new ArrayList<>();
                                            longList1.add(activityGiftVo.getErpGoodsId());
                                            //经营类别校验
                                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                                            log.info("经营类别校验resultVo1--{}", resultVo1);
                                            if (resultVo1.getErrorCode() != 0) {
                                                activityGiftVo.setGiftRemark(resultVo1.getErrorMessage());
                                            }
                                            Integer giftStqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), giftStorageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                                            activityGiftVo.setStqty(giftStqty);

                                            double goodsPrice = 0.01;
                                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                                goodsPrice = 0;
                                            }
                                            activityGiftVo.setGoodsPrice(goodsPrice);
                                            activityGiftVo.setGiftRemark("0");


                                            activityGiftVo.setAmountPay((activityGiftVo.getAsGiftNum()) * goodsPrice);
                                            activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum());
                                            if (giftStqty == 0) {
                                                activityGiftVo.setGiftNum(0L);
                                                activityGiftVo.setAmountPay(0L);
                                            }
                                            activityGiftList9.add(activityGiftVo);
                                        }

                                    }
                                }

                                cartVo.setActivityGiftList(activityGiftList9);
                                cartVo.setRemark("参与单品赠--" + activityVo9.getId());
                                break;
                            }

                        }
                    }

                }

                //单品赠end---
                cartVo.setActivityVo(this.activityMapper.getActivityAllDiscount(member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));
                List<ActivityVo> activityVoListDB = this.activityMapper.getActivityById(null, cartVo.getErpGoodsId(), 0, member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());

                cartVo.setActivityVoList(activityVoListDB);
                //按活动id分组
                if (null != cartVo.getActivityId() && cartVo.getActivityId() > 0) {
                    cartVoListYes.add(cartVo);
                } else {
                    cartVoListNo.add(cartVo);
                }
                if (cartVo.getPitchOn() == 2) continue;

                //秒杀/折扣/品牌/限购
                List<ActivityVo> activityVoList = this.activityMapper.getActivityByGoodsId(60L, null, cartVo.getErpGoodsId(), member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
                List<ActivityVo> activityVoList20Or60 = new ArrayList<>();
                if (null != activityVoList && activityVoList.size() > 0) {
                    for (ActivityVo activityVo : activityVoList) {
                        if (activityVo.getActivityStrategy().equals(60)) {
                            activityVoList20Or60.add(activityVo);
                            break;
                        } else if (activityVo.getActivityStrategy().equals(100)) {
                            activityVoList20Or60.add(activityVo);
                            break;
                        } else if (activityVo.getActivityStrategy().equals(80)) {
                            activityVoList20Or60.add(activityVo);
                            break;
                        } else {
                            activityVoList20Or60.add(activityVo);
                            break;
                        }
                    }

                    for (ActivityVo activityDB : activityVoList20Or60) {

                        if (isJoinActivity(activityDB.getId(), member.getErpUserId()) > 0) break;
                        if (longList.size() > 0) {
                            if (activityDB.getActivityStrategy() == 60 || activityDB.getActivityStrategy() == 80) {
                                //限时秒杀&品牌特价
                                List<ActivityStrategy> activityStrategyList6 = this.activityStrategyMapper.selectList(new QueryWrapper<ActivityStrategy>().eq("ACTIVITY_ID", activityDB.getId()).eq("IS_USE", Constant.IS_USE.ON).orderByDesc("id"));
                                cartVo.setGoodType(activityDB.getActivityStrategy());
                                for (ActivityStrategy activityStrategyDB : activityStrategyList6) {
                                    cartVo.setAsId(activityStrategyDB.getId());
                                    ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityDB.getId(), activityStrategyDB.getId(), cartVo.getErpGoodsId());

                                    if (activityDB.getActivityStrategy() != 10) {
                                        //AssertExt.notNull(activityGoodsDB, "活动id[%s],活动策略id[%s]没有匹配到该商品[%s]", orderGoods.getActivityId(), activityStrategyDB.getId(), orderGoods.getGoodsId());
                                        if (null == activityGoodsDB) break;

                                    }
                                    /*//判断商品数量是否有上限
                                    if (activityGoodsDB.getTopLimit() != 0) {
                                        AssertExt.isTrue(cartVo.getGoodsNum() <= activityGoodsDB.getTopLimit(), "该商品[%s]限购[%s]", activityGoodsDB.getGoodsName(), activityGoodsDB.getTopLimit());
                                    }*/

                                    //任选商品数量
                                    if (activityStrategyDB.getGoodsStrategy() == 10) {

                                        AssertExt.isFalse(0 == activityGoodsDB.getConditionNum(), "商品满足数量为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());


                                        //判断是否满足数量
                                        if (numTotal >= activityGoodsDB.getConditionNum()) {
                                            activityPrice = activityGoodsDB.getGoodsPrice();
                                            priceTotal = (activityPrice * IncaUtils.toErpPriceDouble4(numTotal));
                                            cartVo.setGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                                            cartVo.setTopLimit(activityGoodsDB.getTopLimit());
                                        }
                                    }

                                    //任选商品金额
                                    else if (activityStrategyDB.getGoodsStrategy() == 30) {
                                        AssertExt.isFalse(0 == activityGoodsDB.getConditionPrice(), "商品满足金额为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());
                                        //判断是否满足金额
                                        if (priceTotal >= activityGoodsDB.getConditionPrice()) {
                                            activityPrice = activityGoodsDB.getGoodsPrice();
                                            priceTotal = (activityPrice * IncaUtils.toErpPriceDouble4(numTotal));
                                            cartVo.setGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                                            cartVo.setTopLimit(activityGoodsDB.getTopLimit());
                                        }

                                    }

                                    //任选商品策略数量
                                    else if (activityStrategyDB.getGoodsStrategy() == 20) {

                                        AssertExt.isFalse(0 == activityStrategyDB.getMeetQuantity(), "满足数量为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());//判断是否满足数量
                                        if (numTotal >= activityStrategyDB.getMeetQuantity()) {
                                            activityPrice = activityGoodsDB.getGoodsPrice();
                                            priceTotal = (activityPrice * IncaUtils.toErpPriceDouble4(numTotal));
                                            cartVo.setGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                                            cartVo.setTopLimit(activityGoodsDB.getTopLimit());
                                        }

                                    } else {
                                        AssertExt.isFalse(0 == activityStrategyDB.getAmountSatisfied(), "满足金额为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());//任选商品策略金额
                                        if (priceTotal >= activityStrategyDB.getAmountSatisfied()) {
                                            activityPrice = activityGoodsDB.getGoodsPrice();
                                            priceTotal = (activityPrice * IncaUtils.toErpPriceDouble4(numTotal));
                                            cartVo.setGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                                            cartVo.setTopLimit(activityGoodsDB.getTopLimit());
                                        }

                                    }
                                }

                                if (activityPrice != (cartVo.getGoodsPrice())) {
                                    if (activityDB.getActivityStrategy() == 60) {
                                        cartVo.setRptAmount(cartVo.getAmountNum() - cartVo.getAmountPay());
                                        cartVo.setKillOrDiscount("秒杀");
                                        cartVo.setRemark("参与秒杀--" + activityDB.getId() + "--" + activityDB.getContent());
                                    } else {
                                        cartVo.setRptAmount(cartVo.getAmountNum() - cartVo.getAmountPay());
                                        cartVo.setKillOrDiscount("品牌特价");
                                        cartVo.setRemark("参与品牌特价--" + activityDB.getId() + "--" + activityDB.getContent());
                                    }

                                }
                                continue;
                            } else if (activityDB.getActivityStrategy() == 20) {

                                //该商品的所有折扣策略
                                List<ActivityStrategy> activityStrategyList2 = this.activityMapper.getActivityGoodsDiscount(member.getErpUserId(), cartVo.getErpGoodsId(), LocalDateTime.now().getDayOfWeek().getValue());
                                for (ActivityStrategy activityStrategyDB : activityStrategyList2) {
                                    cartVo.setAsId(activityStrategyDB.getId());
                                    cartVo.setGoodType(20);

                                   /* if (activityDB.getActivityStrategy() != 10) {
                                        //AssertExt.notNull(activityGoodsDB, "活动id[%s],活动策略id[%s]没有匹配到该商品[%s]", orderGoods.getActivityId(), activityStrategyDB.getId(), orderGoods.getGoodsId());
                                        if (null == activityGoodsDB) break;

                                    }
                                    //判断商品数量是否有上限
                                    if (activityGoodsDB.getTopLimit() != 0) {
                                        AssertExt.isTrue(cartVo.getGoodsNum() <= activityGoodsDB.getTopLimit(), "该商品[%s]限购[%s]", activityGoodsDB.getGoodsName(), activityGoodsDB.getTopLimit());
                                    }
*/
                                    //任选商品数量
                                    if (activityStrategyDB.getGoodsStrategy() == 10) {
                                        ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityStrategyDB.getActivityId(), activityStrategyDB.getId(), cartVo.getErpGoodsId());

                                        AssertExt.isFalse(0 == activityGoodsDB.getConditionNum(), "商品满足数量为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());

                                        //判断是否满足数量
                                        if (numTotal >= activityGoodsDB.getConditionNum()) {

                                            double goodsPayPrice = (IncaUtils.toErpPriceDouble4(activityStrategyDB.getDiscount() * activityPrice));
                                            priceTotal = (goodsPayPrice * IncaUtils.toErpPriceDouble4(numTotal));
                                            cartVo.setTopLimit(activityGoodsDB.getTopLimit());
                                            cartVo.setGoodsPrice(activityPrice);
                                            cartVo.setGoodsPayPrice(goodsPayPrice);
                                            cartVo.setRptAmount(cartVo.getAmountNum() - cartVo.getAmountPay());
                                            cartVo.setKillOrDiscount("折扣");
                                            cartVo.setRemark("参与折扣--" + activityDB.getId() + "--" + activityDB.getContent() + "--" + activityStrategyDB.getId() + "--" + activityStrategyDB.getStrategyName());

                                            break;
                                        }

                                        continue;
                                    }
                                    //任选商品金额
                                    else if (activityStrategyDB.getGoodsStrategy() == 30) {
                                        ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityStrategyDB.getActivityId(), activityStrategyDB.getId(), cartVo.getErpGoodsId());

                                        AssertExt.isFalse(0 == activityGoodsDB.getConditionPrice(), "商品满足金额为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());
                                        //判断是否满足金额
                                        if (priceTotal >= activityGoodsDB.getConditionPrice()) {
                                            double goodsPayPrice = (IncaUtils.toErpPriceDouble4(activityStrategyDB.getDiscount() * activityPrice));
                                            priceTotal = (goodsPayPrice * IncaUtils.toErpPriceDouble4(numTotal));

                                            cartVo.setTopLimit(activityGoodsDB.getTopLimit());
                                            cartVo.setGoodsPrice(activityPrice);
                                            cartVo.setGoodsPayPrice(goodsPayPrice);
                                            cartVo.setRptAmount(cartVo.getAmountNum() - cartVo.getAmountPay());
                                            cartVo.setKillOrDiscount("折扣");
                                            cartVo.setRemark("参与折扣--" + activityDB.getId() + "--" + activityDB.getContent() + "--" + activityStrategyDB.getId() + "--" + activityStrategyDB.getStrategyName());

                                            break;
                                        }

                                        continue;
                                    }
                                    //任选商品策略数量
                                    else if (activityStrategyDB.getGoodsStrategy() == 20) {
                                        Double asNum3 = 0.0;
                                        //List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.selectList(new QueryWrapper<ActivityGoods>().eq("as_id", activityStrategyDB.getId()));
                                        List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.getActivityGoodsByAsId(activityStrategyDB.getId(), longList);
                                        List<Double> goodsNumList = cartVoList.stream().map(orderGoodsVo -> {
                                            if (activityGoodsList.stream().anyMatch(item -> item.getErpGoodsId().equals(orderGoodsVo.getErpGoodsId()))) {
                                                return orderGoodsVo.getGoodsNum() * orderGoodsVo.getZxB2bNumLimit();
                                            }
                                            return 0.0;
                                        }).collect(Collectors.toList());


                                        for (Double num : goodsNumList) {
                                            asNum3 += num;
                                        }
                                        AssertExt.isFalse(0 == activityStrategyDB.getMeetQuantity(), "满足数量为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                        //判断是否满足数量
                                        if (asNum3 >= activityStrategyDB.getMeetQuantity()) {


                                            double goodsPayPrice = (IncaUtils.toErpPriceDouble4(activityStrategyDB.getDiscount() * activityPrice));
                                            priceTotal = (goodsPayPrice * IncaUtils.toErpPriceDouble4(numTotal));

                                            cartVo.setTopLimit(activityStrategyDB.getTopLimit());
                                            cartVo.setGoodsPrice(activityPrice);
                                            cartVo.setGoodsPayPrice(goodsPayPrice);
                                            cartVo.setRptAmount(cartVo.getAmountNum() - cartVo.getAmountPay());
                                            cartVo.setKillOrDiscount("折扣");
                                            cartVo.setRemark("参与折扣--" + activityDB.getId() + "--" + activityDB.getContent() + "--" + activityStrategyDB.getId() + "--" + activityStrategyDB.getStrategyName());
                                            break;
                                        }

                                        continue;
                                    } else {
                                        //任选商品策略金额

                                        double asPrice3 = 0L;

                                        // List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.selectList(new QueryWrapper<ActivityGoods>().eq("as_id", activityStrategyDB.getId()).eq("ERP_GOODS_ID",orderGoods.getErpGoodsId()));
                                        List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.getActivityGoodsByAsId(activityStrategyDB.getId(), longList);
                                        // Long finalActivityPrice = activityPrice;
                                        List<Double> goodsNumList = cartVoList.stream().map(orderGoodsVo -> {
                                            if (activityGoodsList.stream().anyMatch(item -> item.getErpGoodsId().equals(orderGoodsVo.getErpGoodsId()))) {
                                                List<PubGoodsPriceVo> pubGoodsPriceListPrice = this.goodsInfoMapper.getPubGoodsPriceList(orderGoodsVo.getErpGoodsId(), member.getErpUserId());
                                                log.info("num*b2b{}", orderGoodsVo.getGoodsNum() * orderGoodsVo.getZxB2bNumLimit());
                                                log.info("toErpPriceDouble4{}", IncaUtils.toErpPriceDouble4(pubGoodsPriceListPrice.get(0).getPrice() * orderGoodsVo.getGoodsNum() * orderGoodsVo.getZxB2bNumLimit()));
                                                return IncaUtils.toErpPriceDouble4(pubGoodsPriceListPrice.get(0).getPrice() * orderGoodsVo.getGoodsNum() * orderGoodsVo.getZxB2bNumLimit());
                                            }
                                            return 0.0;
                                        }).collect(Collectors.toList());


                                        for (Double num : goodsNumList) {
                                            asPrice3 += num;
                                        }
                                        AssertExt.isFalse(0 == activityStrategyDB.getAmountSatisfied(), "满足金额为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                        if (asPrice3 >= activityStrategyDB.getAmountSatisfied()) {
                                            double goodsPayPrice = (IncaUtils.toErpPriceDouble4(activityStrategyDB.getDiscount() * activityPrice));
                                            priceTotal = (goodsPayPrice * IncaUtils.toErpPriceDouble4(numTotal));

                                            cartVo.setTopLimit(activityStrategyDB.getTopLimit());
                                            cartVo.setGoodsPrice(activityPrice);
                                            cartVo.setGoodsPayPrice(goodsPayPrice);
                                            cartVo.setRptAmount(cartVo.getAmountNum() - cartVo.getAmountPay());
                                            cartVo.setKillOrDiscount("折扣");
                                            cartVo.setRemark("参与折扣--" + activityDB.getId() + "--" + activityDB.getContent() + "--" + activityStrategyDB.getId() + "--" + activityStrategyDB.getStrategyName());
                                            break;
                                        }
                                        continue;
                                    }
                                }

                                continue;
                            } else if (activityDB.getActivityStrategy() == 100) {
                                int count = this.orderInfoMapper.getOrderGoodsCountPurchase(memberId, activityDB.getId(), cartVo.getErpGoodsId(), activityDB.getTimesStrategy());
                                log.info("count------{}", count);

                                //限购
                                List<ActivityStrategy> activityStrategyList100 = this.activityStrategyMapper.selectList(new QueryWrapper<ActivityStrategy>().eq("ACTIVITY_ID", activityDB.getId()).eq("IS_USE", Constant.IS_USE.ON).orderByDesc("id"));
                                cartVo.setGoodType(activityDB.getActivityStrategy());
                                for (ActivityStrategy activityStrategyDB : activityStrategyList100) {
                                    cartVo.setAsId(activityStrategyDB.getId());
                                    ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityDB.getId(), activityStrategyDB.getId(), cartVo.getErpGoodsId());
                                    AssertExt.isFalse(0 == activityGoodsDB.getTopLimit(), "商品限购数量为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());
                                    //AssertExt.isTrue(cartVo.getGoodsNum() <= activityGoodsDB.getConditionNum(), "该商品[%s]限购[%s]", activityGoodsDB.getGoodsName(), activityGoodsDB.getTopLimit());
                                    if (count != 0) {
                                        Long lcnum = activityGoodsDB.getTopLimit() - count;
                                        if (lcnum > 0) {
                                            cartVo.setTopLimit(lcnum);
                                            activityGoodsDB.setTopLimit(lcnum);
                                        } else {
                                            cartVo.setTopLimit(0);
                                            activityGoodsDB.setTopLimit(0L);
                                        }


                                    }
                                    if (cartVo.getGoodsNum() > activityGoodsDB.getTopLimit()) {
                                        cartVo.setLimTop("该商品" + activityGoodsDB.getGoodsName() + "限购数量" + activityGoodsDB.getTopLimit());
                                        double limNum = cartVo.getGoodsNum() - activityGoodsDB.getTopLimit();
                                        double limPrice = (activityGoodsDB.getTopLimit() * activityGoodsDB.getGoodsPrice()) + (limNum * cartVo.getGoodsPrice());

                                        activityPrice = limPrice / cartVo.getGoodsNum();
                                        priceTotal = (activityPrice * IncaUtils.toErpPriceDouble4(numTotal));
                                        cartVo.setLimGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                                        cartVo.setGoodsPayPrice(IncaUtils.toErpPriceDouble4(activityPrice));
                                        cartVo.setPurchaseId(activityDB.getId());
                                        cartVo.setRptAmount(amount - priceTotal);
                                        cartVo.setKillOrDiscount("限购");
                                        cartVo.setRemark("参与限购--" + activityDB.getId() + "--" + activityDB.getContent());
                                        break;
                                    } else {
                                        activityPrice = activityGoodsDB.getGoodsPrice();
                                        priceTotal = (activityPrice * IncaUtils.toErpPriceDouble4(numTotal));
                                        cartVo.setLimGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                                        cartVo.setGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                                        cartVo.setPurchaseId(activityDB.getId());
                                        cartVo.setRptAmount(amount - priceTotal);
                                        cartVo.setKillOrDiscount("限购");
                                        cartVo.setRemark("参与限购--" + activityDB.getId() + "--" + activityDB.getContent());
                                        break;
                                    }

                                }
                            }
                        }
                    }

                }

                //优惠后小计

                cartVo.setAmountPay(IncaUtils.toErpPriceDouble(priceTotal));
/**
 * 限购数量
 */
                ActivityVo activityVo120 = this.activityMapper.getActivity90ByGoods(120L, cartVo.getErpGoodsId(), member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
                if (null != activityVo120) {

                    int count = this.orderInfoMapper.getOrderGoodsCountPurchase(memberId, activityVo120.getId(), cartVo.getErpGoodsId(), activityVo120.getTimesStrategy());
                log.info("count------{}", count);

                //限购
                List<ActivityStrategy> activityStrategyList100 = this.activityStrategyMapper.selectList(new QueryWrapper<ActivityStrategy>()
                        .eq("ACTIVITY_ID", activityVo120.getId()).eq("IS_USE", Constant.IS_USE.ON).orderByDesc("id"));
                cartVo.setGoodType(activityVo120.getActivityStrategy());
                for (ActivityStrategy activityStrategyDB : activityStrategyList100) {
                    cartVo.setAsId(activityStrategyDB.getId());
                    ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityVo120.getId(), activityStrategyDB.getId(), cartVo.getErpGoodsId());
                    AssertExt.isFalse(0 == activityGoodsDB.getTopLimit(), "商品限购数量为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());
                    //AssertExt.isTrue(cartVo.getGoodsNum() <= activityGoodsDB.getConditionNum(), "该商品[%s]限购[%s]", activityGoodsDB.getGoodsName(), activityGoodsDB.getTopLimit());
                    if (count != 0) {
                        Long lcnum = activityGoodsDB.getTopLimit() - count;
                        if (lcnum > 0) {
                            if(cartVo.getGoodsNum()>lcnum){
                                cartVo.setGoodsNum(Math.toIntExact(lcnum));
                            }
                            cartVo.setTopLimit(lcnum);
                            activityGoodsDB.setTopLimit(lcnum);
                        } else {
                            cartVo.setTopLimit(0);
                            cartVo.setGoodsNum(0);
                            activityGoodsDB.setTopLimit(0L);
                        }

                    }
                        //cartVo.setLimGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                        //cartVo.setGoodsPayPrice(activityGoodsDB.getGoodsPrice());
                        cartVo.setPurchaseId(activityVo120.getId());
                        cartVo.setRptAmount(amount - priceTotal);
                        cartVo.setKillOrDiscount("限购数量");
                        cartVo.setRemark("参与限购数量--" + activityVo120.getId() + "--" + activityVo120.getContent());
                        break;

                }


            }









            }
            //所有没有活动的商品
            if (null != cartVoListNo && cartVoListNo.size() > 0) {
                map.put(1L, cartVoListNo);
            }

            if (null != cartVoList5 && cartVoList5.size() > 0) {
                map.put(5L, cartVoList5);
            }

            if (null != cartVoList9 && cartVoList9.size() > 0) {
                map.put(9L, cartVoList9);
            }


            //按活动id分组
            for (Long activityId : activityIdList) {
                if (activityId > 0) {
                    List<CartVo> cartVoList1 = new ArrayList<>();
                    for (CartVo cartVo : cartVoListYes) {
                        if (cartVo.getActivityId().equals(activityId)) {
                            cartVoList1.add(cartVo);
                        }
                    }
                    if (cartVoList1.size() > 0) {
                        map.put(activityId, cartVoList1);
                    }
                }
            }
            for (Map.Entry<Long, List<CartVo>> entry : map.entrySet()) {
                if (entry.getKey() > 9) {
                    List<CartVo> cartVoList1 = entry.getValue();
                    //活动
                    Activity activityDB = this.activityMapper.selectById(entry.getKey());

                    //删除临时表--赠品表不存在的赠品
                    if (activityDB.getActivityStrategy() == 40) {
                        this.cartGiftTmpMapper.delCartGiftTmp(memberId, activityDB.getId());
                    }

                    double asNum3 = 0.0;
                    double asNum30 = 0.0;

                    double asPrice3 = 0.0;
                    double asPrice30 = 0.0;


                    for (CartVo cartVo : cartVoList1) {
                        if (cartVo.getPitchOn() == 1) {
                            asNum3 += cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit();
                            asPrice3 += IncaUtils.toErpPriceDouble4(cartVo.getGoodsPayPrice() * cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit());
                        }
                    }
                    asNum30 = asNum3;

                    asPrice30 = asPrice3;

                    //不平摊金额
                    double reducedAmount = 0L;
                    int reduceNum = 0;
                    QueryWrapper queryWrapper = new QueryWrapper<ActivityStrategy>().eq("ACTIVITY_ID", activityDB.getId()).eq("IS_USE", Constant.IS_USE.ON);

                    if (activityDB.getActivityStrategy() == 30) {
                        queryWrapper.orderByDesc("REDUCED_AMOUNT");
                    } else {
                        queryWrapper.orderByDesc("GIFT_NUM");
                    }
                    queryWrapper.orderByDesc("id");
                    List<ActivityStrategy> activityStrategyList3 = this.activityStrategyMapper.selectList(queryWrapper);
                    List<ActivityGiftVo> activityGiftList = new ArrayList<>();
                    for (ActivityStrategy activityStrategyDB : activityStrategyList3) {
                        List<ActivityGiftVo> activityGiftList1 = new ArrayList<>();
                        if (activityDB.getActivityStrategy() == 40) {
                            if (activityStrategyDB.getGiftStrategy() == 40) continue;
                            activityGiftList1 = this.activityGiftMapper.getActivityGiftVoList(activityStrategyDB.getId());
                            if (activityGiftList1.size() == 0) continue;
                        }


                        //任选商品数量
                        if (activityStrategyDB.getGoodsStrategy() == 10) {
                            boolean a = true;

                            Integer goodsCount = this.activityGoodsMapper.selectCount(new QueryWrapper<ActivityGoods>().eq("IS_USE", Constant.IS_USE.ON).eq("ACTIVITY_ID", activityDB.getId()).eq("AS_ID", activityStrategyDB.getId()));
                            if (goodsCount != cartVoList1.size()) continue;

                            List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.getActivityGoodsByAcIdAsId(activityDB.getId(), activityStrategyDB.getId());

                            do {
                                outterLoop:
                                for (ActivityGoods activityGoods : activityGoodsList) {
                                    for (CartVo cartVo : cartVoList1) {
                                        if (cartVo.getPitchOn() == 2) {
                                            a = false;
                                            break outterLoop;
                                        }
                                        if (cartVo.getErpGoodsId().equals(activityGoods.getErpGoodsId())) {
                                               /* //判断策略商品策略数量是否有上限
                                                if (activityStrategyDB.getTopLimit() != 0) {
                                                     AssertExt.isTrue(cartVo.getNumPay() <= activityStrategyDB.getTopLimit(), "该商品[%s]限购[%s]", activityGoods.getGoodsName(), activityGoods.getTopLimit());
                                                }*/
                                            //判断是否满足数量
                                            AssertExt.isFalse(0 == activityGoods.getConditionNum(), "商品满足数量为空或者为0【%s】-【%s】-【%s】", activityGoods.getGoodsName(), activityGoods.getActivityId(), activityGoods.getAsId());

                                            if (cartVo.getNumPay() >= activityGoods.getConditionNum()) {
                                                a = true;
                                            } else {
                                                a = false;
                                                break outterLoop;
                                            }
                                        }
                                    }
                                }

                                if (a) {
                                    if (activityDB.getIsSuperposition() == 1) {

                                        reducedAmount += activityStrategyDB.getReducedAmount();

                                        cartVoList1.forEach(cartVo -> {
                                            ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityDB.getId(), activityStrategyDB.getId(), cartVo.getErpGoodsId());
                                            cartVo.setNumPay(cartVo.getNumPay() - activityGoodsDB.getConditionNum());
                                        });
                                        a = true;

                                    } else {

                                        reducedAmount = activityStrategyDB.getReducedAmount();
                                        a = false;
                                    }

                                    //满赠--赠品
                                    if (activityDB.getActivityStrategy() == 40) {
                                        activityGiftList = setActivityGiftVo(activityStrategyDB.getGiftStrategy(), activityGiftList, activityGiftList1);

                                    }
                                    if (activityDB.getIsSuperposition() != 1) {
                                        break;
                                    }
                                }


                            } while (a);
                            continue;
                        }
                        //任选商品金额
                        else if (activityStrategyDB.getGoodsStrategy() == 30) {

                            boolean a = true;
                            Integer goodsCount = this.activityGoodsMapper.selectCount(new QueryWrapper<ActivityGoods>().eq("IS_USE", Constant.IS_USE.ON).eq("ACTIVITY_ID", activityDB.getId()).eq("AS_ID", activityStrategyDB.getId()));

                            if (goodsCount != cartVoList1.size()) continue;
                            List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.getActivityGoodsByAcIdAsId(activityDB.getId(), activityStrategyDB.getId());

                            do {
                                outterLoop:
                                for (ActivityGoods activityGoods : activityGoodsList) {
                                    for (CartVo cartVo : cartVoList1) {
                                        if (cartVo.getPitchOn() == 2) {
                                            a = false;
                                            break outterLoop;
                                        }
                                        if (cartVo.getErpGoodsId().equals(activityGoods.getErpGoodsId())) {
                                            //判断是否满足价格
                                            AssertExt.isFalse(0 == activityGoods.getConditionPrice(), "商品满足金额为空或者为0【%s】-【%s】-【%s】", activityGoods.getGoodsName(), activityGoods.getActivityId(), activityGoods.getAsId());

                                            if (cartVo.getAmountPay1() >= activityGoods.getConditionPrice()) {
                                                a = true;
                                            } else {
                                                a = false;
                                                break outterLoop;
                                            }
                                        }
                                    }
                                }

                                if (a) {
                                    if (activityDB.getIsSuperposition() == 1) {

                                        reducedAmount += activityStrategyDB.getReducedAmount();

                                        cartVoList1.forEach(cartVo -> {
                                            ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityDB.getId(), activityStrategyDB.getId(), cartVo.getErpGoodsId());
                                            cartVo.setAmountPay1(cartVo.getAmountPay1() - activityGoodsDB.getConditionPrice());
                                        });

                                        a = true;

                                    } else {
                                        reducedAmount = activityStrategyDB.getReducedAmount();
                                        a = false;
                                    }
                                    //满赠--赠品
                                    if (activityDB.getActivityStrategy() == 40) {
                                        activityGiftList = setActivityGiftVo(activityStrategyDB.getGiftStrategy(), activityGiftList, activityGiftList1);
                                    }
                                }
                                if (activityDB.getIsSuperposition() != 1) {
                                    break;
                                }

                            } while (a);
                            continue;
                        }
                        //任选策略金额
                        else if (activityStrategyDB.getGoodsStrategy() == 40) {
                            //判断是否满足金额
                            AssertExt.isFalse(0 == activityStrategyDB.getAmountSatisfied(), "满足金额为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                            if (asPrice3 >= activityStrategyDB.getAmountSatisfied()) {
                                if (activityDB.getIsSuperposition() == 1) {
                                    int price = (int) (asPrice3 / activityStrategyDB.getAmountSatisfied());
                                    reduceNum = price;

                                    reducedAmount += price * activityStrategyDB.getReducedAmount();

                                    asPrice3 -= activityStrategyDB.getAmountSatisfied() * price;

                                } else {
                                    reducedAmount = activityStrategyDB.getReducedAmount();
                                    reduceNum = 1;
                                    asPrice3 -= activityStrategyDB.getAmountSatisfied();

                                }
                                //满赠-赠品
                                if (activityDB.getActivityStrategy() == 40) {
                                    int finalReduceNum = reduceNum;
                                    //任选赠品策略
                                    if (activityStrategyDB.getGiftStrategy() == 10) {
                                        if (activityGiftList.size() == 0) {

                                            List<ActivityGiftVo> finalActivityGiftList = activityGiftList;
                                            activityGiftList1.forEach(activityGiftVo -> {
                                                activityGiftVo.setGiftNum(activityGiftVo.getGiftNum() * finalReduceNum);
                                                finalActivityGiftList.add(activityGiftVo);
                                            });
                                        } else {

                                            for (ActivityGiftVo activityGift1 : activityGiftList1) {
                                                boolean b = true;
                                                for (ActivityGiftVo activityGift : activityGiftList) {
                                                    if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                        b = true;
                                                        break;
                                                    } else
                                                        b = false;

                                                }

                                                if (b) {
                                                    for (ActivityGiftVo activityGift : activityGiftList) {
                                                        if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                            activityGift.setGiftNum(activityGift.getGiftNum() + activityGift1.getGiftNum() * finalReduceNum);
                                                        }
                                                    }
                                                } else {
                                                    activityGift1.setGiftNum(activityGift1.getGiftNum() * finalReduceNum);
                                                    activityGiftList.add(activityGift1);
                                                }
                                            }

                                        }

                                    }
                                    //固定赠品策略
                                    else if (activityStrategyDB.getGiftStrategy() == 30) {
                                        if (activityGiftList.size() == 0) {

                                            List<ActivityGiftVo> finalActivityGiftList1 = activityGiftList;
                                            activityGiftList1.forEach(activityGiftVo -> {
                                                activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum() * finalReduceNum);
                                                finalActivityGiftList1.add(activityGiftVo);
                                            });

                                        } else {
                                            for (ActivityGiftVo activityGift1 : activityGiftList1) {
                                                boolean b = true;
                                                for (ActivityGiftVo activityGift : activityGiftList) {
                                                    if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                        b = true;
                                                        break;
                                                    } else
                                                        b = false;

                                                }

                                                if (b) {
                                                    for (ActivityGiftVo activityGift : activityGiftList) {
                                                        if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                            activityGift.setGiftNum(activityGift.getGiftNum() + activityGift1.getAsGiftNum() * finalReduceNum);
                                                        }
                                                    }
                                                } else {
                                                    activityGift1.setGiftNum(activityGift1.getAsGiftNum() * finalReduceNum);
                                                    activityGiftList.add(activityGift1);
                                                }
                                            }

                                        }
                                    }

                                }

                                if (activityDB.getIsSuperposition() != 1) {
                                    break;
                                }
                            }
                            continue;

                        }
                        //任选策略数量
                        else {
                            //判断是否满足数量
                            AssertExt.isFalse(0 == activityStrategyDB.getMeetQuantity(), "满足数量为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                            if (asNum3 >= activityStrategyDB.getMeetQuantity()) {
                                if (activityDB.getIsSuperposition() == 1) {
                                    int num = (int) (asNum3 / activityStrategyDB.getMeetQuantity());
                                    reduceNum = num;

                                    reducedAmount += num * activityStrategyDB.getReducedAmount();

                                    asNum3 -= activityStrategyDB.getMeetQuantity() * num;

                                } else {
                                    reducedAmount = activityStrategyDB.getReducedAmount();
                                    reduceNum = 1;
                                    asNum3 -= activityStrategyDB.getMeetQuantity();

                                }

                                //满赠-赠品
                                if (activityDB.getActivityStrategy() == 40) {
                                    int finalReduceNum = reduceNum;
                                    //任选赠品策略
                                    if (activityStrategyDB.getGiftStrategy() == 10) {
                                        if (activityGiftList.size() == 0) {

                                            List<ActivityGiftVo> finalActivityGiftList2 = activityGiftList;
                                            activityGiftList1.forEach(activityGiftVo -> {
                                                activityGiftVo.setGiftNum(activityGiftVo.getGiftNum() * finalReduceNum);
                                                finalActivityGiftList2.add(activityGiftVo);
                                            });
                                        } else {
                                            for (ActivityGiftVo activityGift1 : activityGiftList1) {
                                                boolean b = true;
                                                for (ActivityGiftVo activityGift : activityGiftList) {
                                                    if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                        b = true;
                                                        break;
                                                    } else
                                                        b = false;

                                                }

                                                if (b) {
                                                    for (ActivityGiftVo activityGift : activityGiftList) {
                                                        if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                            activityGift.setGiftNum(activityGift.getGiftNum() + activityGift1.getGiftNum() * finalReduceNum);
                                                        }
                                                    }
                                                } else {
                                                    activityGift1.setGiftNum(activityGift1.getGiftNum() * finalReduceNum);
                                                    activityGiftList.add(activityGift1);
                                                }
                                            }

                                        }

                                    }
                                    //固定赠品策略
                                    else if (activityStrategyDB.getGiftStrategy() == 30) {

                                        if (activityGiftList.size() == 0) {

                                            List<ActivityGiftVo> finalActivityGiftList3 = activityGiftList;
                                            activityGiftList1.forEach(activityGiftVo -> {
                                                activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum() * finalReduceNum);
                                                finalActivityGiftList3.add(activityGiftVo);
                                            });
                                        } else {
                                            for (ActivityGiftVo activityGift1 : activityGiftList1) {
                                                boolean b = true;
                                                for (ActivityGiftVo activityGift : activityGiftList) {
                                                    if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                        b = true;
                                                        break;
                                                    } else
                                                        b = false;

                                                }

                                                if (b) {
                                                    for (ActivityGiftVo activityGift : activityGiftList) {
                                                        if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                            activityGift.setGiftNum(activityGift.getGiftNum() + activityGift1.getAsGiftNum() * finalReduceNum);
                                                        }
                                                    }
                                                } else {
                                                    activityGift1.setGiftNum(activityGift1.getAsGiftNum() * finalReduceNum);
                                                    activityGiftList.add(activityGift1);
                                                }
                                            }

                                        }
                                    }

                                }
                                if (activityDB.getIsSuperposition() != 1) {
                                    break;
                                }
                            }
                            continue;
                        }

                    }

                    log.info("reducedAmount{}", reducedAmount);

                    //满减
                    if (activityDB.getActivityStrategy() == 30) {
                        if (reducedAmount > 0) {
                            double finalReducedAmount = reducedAmount;
                            double finalAsPrice3 = asPrice30;


                            double reducedAmount1 = 0L;

                            for (int i = 0; i < cartVoList1.size(); i++) {
                                if (cartVoList1.get(i).getGoodType() == 4 || cartVoList1.get(i).getGoodType() == 9)
                                    continue;


                                double allDiscount1 = ((reducedAmount * cartVoList1.get(i).getAmountPay()) / finalAsPrice3) * 1L;
                                reducedAmount1 += allDiscount1;

                                double amountPay1 = 0L;
                                if (i == cartVoList1.size()) {
                                    amountPay1 = cartVoList1.get(i).getAmountPay() - (reducedAmount - reducedAmount1);
                                    cartVoList1.get(i).setAmountPay(IncaUtils.toErpPriceDouble(amountPay1));
                                    cartVoList1.get(i).setRptAmount(cartVoList1.get(i).getRptAmount() + (reducedAmount - reducedAmount1));
                                } else {
                                    amountPay1 = cartVoList1.get(i).getAmountPay() - allDiscount1;
                                    cartVoList1.get(i).setAmountPay(IncaUtils.toErpPriceDouble(amountPay1));
                                    cartVoList1.get(i).setRptAmount(cartVoList1.get(i).getRptAmount() + allDiscount1);
                                }
                                double num = cartVoList1.get(i).getGoodsNum() * cartVoList1.get(i).getZxB2bNumLimit();
                                double price = amountPay1 / num;
                                cartVoList1.get(i).setGoodsPayPrice(price);
                                String remark = cartVoList1.get(i).getRemark();

                                cartVoList1.get(i).setRemark(remark + "参与满减--" + activityDB.getId() + "--");
                                cartVoList1.get(i).setReducePrice(finalReducedAmount);
                            }

                        }

                    }
                    //满赠
                    else if (activityDB.getActivityStrategy() == 40) {
                        if (activityGiftList.size() == 0) continue;
                        cartVoList1.forEach(cartVo -> {
                            cartVo.setGoodType(40);
                            cartVo.setRemark(cartVo.getRemark() + "参与满赠--" + activityDB.getId() + "--");
                        });

                        for (ActivityGiftVo activityGiftVo : activityGiftList) {
                            Long cartGiftTmpId = null;
                            int giftNum = -1;
                            double tmpNum = activityGiftVo.getGiftNum();
                            if (activityGiftVo.getGiftStrategy() == 30) {
                                if (activityGiftList.size() > 1) {
                                    CartGiftTmp cartGiftTmp = this.cartGiftTmpMapper.selectOne(new QueryWrapper<CartGiftTmp>().eq("MEMBER_ID", memberId)
                                            .eq("AC_ID", activityDB.getId()).eq("ERP_GOODS_ID", activityGiftVo.getErpGoodsId())
                                            .eq("AS_ID", activityGiftVo.getAsId()));
                                    if (null != cartGiftTmp) {

                                        giftNum = Math.toIntExact(cartGiftTmp.getGoodsNum());
                                        if (tmpNum != cartGiftTmp.getNUM()) {
                                            if (cartGiftTmp.getGoodsNum() > tmpNum) {
                                                cartGiftTmp.setGoodsNum(0L);
                                            }
                                            cartGiftTmp.setNUM((long) tmpNum);
                                            this.cartGiftTmpMapper.updateById(cartGiftTmp);
                                        }

                                    } else {
                                        cartGiftTmp = new CartGiftTmp();
                                        cartGiftTmp.setAcId(activityDB.getId());
                                        cartGiftTmp.setAsId(activityGiftVo.getAsId());
                                        cartGiftTmp.setMemberId(memberId);
                                        cartGiftTmp.setCreateTime(LocalDateTime.now());
                                        cartGiftTmp.setErpGoodsId(activityGiftVo.getErpGoodsId());
                                        cartGiftTmp.setGoodsNum(0L);
                                        cartGiftTmp.setNUM((long) tmpNum);
                                        this.cartGiftTmpMapper.insert(cartGiftTmp);
                                        giftNum = 0;
                                    }
                                    cartGiftTmpId = cartGiftTmp.getId();
                                }
                            }
                            CartVo cartVo = new CartVo();
                            cartVo.setNum(tmpNum);
                            cartVo.setGiftCount(activityGiftList.size());
                            cartVo.setGiftStrategy(activityGiftVo.getGiftStrategy());
                            cartVo.setCartGiftTmpId(cartGiftTmpId);
                            cartVo.setZxB2bNumLimit(activityGiftVo.getZxB2bNumLimit());
                            cartVo.setGoodType(4);
                            cartVo.setGoodsImage(activityGiftVo.getGoodsImg());
                            cartVo.setGoodstype(activityGiftVo.getGoodstype());
                            cartVo.setFactoryname(activityGiftVo.getFactoryname());
                            cartVo.setGoodsName(activityGiftVo.getGoodsName());
                            cartVo.setCurrencyname(activityGiftVo.getCurrencyname());
                            cartVo.setLotId(activityGiftVo.getLotId());
                            cartVo.setLotNo(activityGiftVo.getLotno());
                            cartVo.setBarcode(activityGiftVo.getBarcode());
                            cartVo.setGoodsunit(activityGiftVo.getGoodsunit());
                            cartVo.setStorageId(activityGiftVo.getStorageId());
                            cartVo.setErpGoodsId(activityGiftVo.getErpGoodsId());
                            cartVo.setAccflag(activityGiftVo.getErpAccflag());
                            Long[] storageIds = new Long[]{activityGiftVo.getStorageId()};
                            List<Long> longList1 = new ArrayList<>();
                            longList1.add(activityGiftVo.getErpGoodsId());
                            //经营类别校验
                            ResultVo resultVo1 = this.goodsService.valid(member.getErpUserId(), 1, longList1);
                            log.info("经营类别校验resultVo1--{}", resultVo1);
                            if (resultVo1.getErrorCode() != 0) {
                                cartVo.setGiftRemark(resultVo1.getErrorMessage());
                            }

                            Integer stqty = this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), member.getErpUserId(), storageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId());
                            activityGiftVo.setStqty(stqty);
                            cartVo.setStqty(stqty);

                            double goodsPrice = 0.01;
                            if (null != activityGiftVo.getAccflag() && activityGiftVo.getAccflag() == 5) {
                                goodsPrice = 0;
                            }
                            cartVo.setGoodsPrice(goodsPrice);
                            cartVo.setGoodsPayPrice(goodsPrice);


                            cartVo.setNumTotal(tmpNum * activityGiftVo.getZxB2bNumLimit());
                            if (giftNum != -1) {
                                tmpNum = Long.valueOf(giftNum);
                                cartVo.setNumTotal(giftNum * activityGiftVo.getZxB2bNumLimit());
                            }
                            cartVo.setGoodsNum((int) tmpNum);
                            if (stqty == 0) {
                                cartVo.setGoodsNum(0);
                            }

                            cartVo.setAmountPay(tmpNum * goodsPrice);
                            cartVo.setAmountNum(tmpNum * goodsPrice);
                            cartVo.setRemark("赠品");
                            cartVo.setGiftRemark("0");


                            cartVoList1.add(cartVo);
                        }
                    }

                    map.put(entry.getKey(), cartVoList1);
                }
            }

            //首次下单满1000减30
            if (orderPrice > 0 && null != goodsId) {
                List<CartVo> onePayOrderCart = new ArrayList<>();
                double amountPay = 0L;
                for (Map.Entry<Long, List<CartVo>> entry : map.entrySet()) {
                    if (entry.getKey() != 9L) {
                        onePayOrderCart.addAll(entry.getValue());
                    }
                }

                String[] goodsList = goodsId.split(",");
                for (CartVo cartVo : onePayOrderCart) {
                    if (cartVo.getGoodType() != 4) {
                        if (!Arrays.asList(goodsList).contains(cartVo.getErpGoodsId().toString())) {
                            amountPay += cartVo.getAmountPay();
                        }
                    }
                }
                if (amountPay >= 1000) {

                    for (Map.Entry<Long, List<CartVo>> entry : map.entrySet()) {
                        for (CartVo cartVoListOne : entry.getValue()) {
                            cartVoListOne.setOrderPrice(orderPrice);

                        }
                        map.put(entry.getKey(), entry.getValue());
                    }

                }
            }

        }

        return map;
    }

    private List<ActivityGiftVo> setActivityGiftVo(Integer giftStrategy, List<ActivityGiftVo> activityGiftList, List<ActivityGiftVo> activityGiftList1) {

        //固定赠品
        if (giftStrategy == 10) {
            if (activityGiftList.size() == 0) {
                activityGiftList1.forEach(item -> {
                    ActivityGiftVo newx = new ActivityGiftVo();
                    BeanUtils.copyProperties(item, newx);
                    activityGiftList.add(newx);
                });
            } else {
                for (ActivityGiftVo activityGift1 : activityGiftList1) {
                    boolean b = true;
                    for (ActivityGiftVo activityGift : activityGiftList) {
                        if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                            b = true;
                            break;
                        } else
                            b = false;

                    }

                    if (b) {
                                                        /*for (ActivityGiftVo activityGift : activityGiftList) {
                                                            if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                                                activityGift.setGiftNum(activityGift.getGiftNum() + activityGift1.getGiftNum());
                                                            }
                                                        }*/

                        activityGiftList.forEach(activityGift -> {
                            if (ObjectUtil.equal(activityGift1.getErpGoodsId(), activityGift.getErpGoodsId())) {
                                activityGift.setGiftNum(activityGift.getGiftNum() + activityGift1.getGiftNum());
                            }
                        });

                    } else {
                        activityGiftList.add(activityGift1);
                    }
                }
            }

        }
        //赠品策略
        else if (giftStrategy == 30) {
            if (activityGiftList.size() == 0) {
                activityGiftList1.forEach(activityGiftVo -> {
                    activityGiftVo.setGiftNum(activityGiftVo.getAsGiftNum());
                    activityGiftList.add(activityGiftVo);
                });
            } else {

                for (ActivityGiftVo activityGift1 : activityGiftList1) {
                    boolean b = true;
                    for (ActivityGiftVo activityGift : activityGiftList) {
                        if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                            b = true;
                            break;
                        } else
                            b = false;

                    }

                    if (b) {
                        for (ActivityGiftVo activityGift : activityGiftList) {
                            if (activityGift1.getErpGoodsId().equals(activityGift.getErpGoodsId())) {
                                activityGift.setGiftNum(activityGift.getGiftNum() + activityGift1.getAsGiftNum());
                            }
                        }
                    } else {
                        activityGift1.setGiftNum(activityGift1.getAsGiftNum());
                        activityGiftList.add(activityGift1);
                    }
                }
            }
        }
        return activityGiftList;
    }

    @Transactional
    @Override
    public void getCartUseCoupon(OrderInfoVo orderInfoVo) {

        Member member = this.memberMapper.selectById(orderInfoVo.getMemberId());
        Map<Long, List<CartVo>> listMap = this.getCartToActivity(orderInfoVo.getMemberId(), Constant.IS_PITCH_ON.ON);
        List<CartVo> cartVoList = new ArrayList<>();
        //积分商品
        List<CartVo> cartVoList9 = new ArrayList<>();
        for (Map.Entry<Long, List<CartVo>> entry : listMap.entrySet()) {
            cartVoList.addAll(entry.getValue());
            if (entry.getKey() == 9L) {
                cartVoList9.addAll(entry.getValue());
            }
        }

        if (null != cartVoList9 && cartVoList9.size() > 0) {
            double scoreGoods = 0L;
            for (CartVo cartVo : cartVoList9) {
                scoreGoods += cartVo.getScoreTotal();
            }

            AssertExt.isTrue(scoreGoods <= member.getIntegral(), "积分不够【%s】", scoreGoods);

            orderInfoVo.setUseScore((long) scoreGoods);
            ScoreRecord scoreRecord = ScoreRecord.builder().chanScore((long) -scoreGoods).orignScore(member.getIntegral()).content("下单积分抵扣金额")
                    .fromId(orderInfoVo.getId()).fromType("1").memberId(member.getId()).build();
            this.addScoreRecord(scoreRecord);
        }

        List<Long> longList = new ArrayList<>();

        List<OrderInfo> orderInfoList = this.orderInfoMapper.selectList(new QueryWrapper<OrderInfo>().eq("MEMBER_ID", orderInfoVo.getMemberId()));
        double orderPrice = 0L;
        String[] goodsList = new String[]{};
        double oneOrderAmount = 0L;
        //首次下单减30
        if (orderInfoList.size() == 1) {
            String goodsIds = this.settingService.getOneOrderPay("ONE_ORDER_PAY");
            if (null != goodsIds) {
                orderPrice = 30;
                goodsList = goodsIds.split(",");
            }
        }

        double amount = 0L;
        double amountPay = 0L;
        double notGiftAmount = 0L;
        double giftAmount = 0L;
        double rptAmount = 0L;
        for (CartVo cartVo : cartVoList) {
            if (cartVo.getGoodsNum() == 0) continue;
            if (cartVo.getGoodType() == 4) {
                if (!cartVo.getGiftRemark().equals("0")) continue;
            }
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsPrice(cartVo.getGoodsPrice());
           /* if (cartVo.getGoodsPrice()) {
                orderGoods.setGoodsPrice(0L);
            }*/
            orderGoods.setGoodsName(cartVo.getGoodsName());
            orderGoods.setGoodsImage(cartVo.getGoodsImage());
            orderGoods.setGoodsPayPrice(cartVo.getGoodsPayPrice());
           /* if (cartVo.getGoodsPayPrice()) {
                orderGoods.setGoodsPayPrice(0L);
            }*/
            orderGoods.setSrcOrderDtlId(cartVo.getSrcOrderDtlId());
            orderGoods.setStoreId(cartVo.getStoreId());
            orderGoods.setGoodsNum(cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit());
            orderGoods.setErpLeastsaleqty(cartVo.getZxB2bNumLimit());
            orderGoods.setAmountPay(cartVo.getAmountPay());
            orderGoods.setAmountNum(cartVo.getAmountNum());
            orderGoods.setGoodsId(cartVo.getErpGoodsId());
            orderGoods.setErpGoodsId(cartVo.getErpGoodsId());
            orderGoods.setErpStorageId(Math.toIntExact(cartVo.getStorageId()));
            orderGoods.setErpGoodsUnit(cartVo.getGoodsunit());
            orderGoods.setErpLotNo(cartVo.getLotNo());
            orderGoods.setLotId(cartVo.getLotId());
            orderGoods.setGoodsSpec(cartVo.getGoodstype());
            orderGoods.setAsId(cartVo.getAsId());
            orderGoods.setActivityId(cartVo.getActivityId());
            orderGoods.setGoodType(cartVo.getGoodType());
            orderGoods.setOrderId(orderInfoVo.getId());
            orderGoods.setMemberId(orderInfoVo.getMemberId());
            if (null != cartVo.getPriceId()) {
                orderGoods.setPriceId(cartVo.getPriceId().intValue());
            }
            orderGoods.setGoodsScore((long) cartVo.getScoreTotal());
            orderGoods.setGcId(cartVo.getGcId());
            orderGoods.setCreateTime(LocalDateTime.now());
            orderGoods.setPurchaseId(cartVo.getPurchaseId());
            if (null != cartVo.getRemark()) {
                orderGoods.setRemark(cartVo.getRemark().replace("null", ""));
            }

            double price = IncaUtils.toErpPriceDouble(cartVo.getAmountNum());
            amount += price;

            orderGoods.setAmountNum(price);


            double pricePay = IncaUtils.toErpPriceDouble(cartVo.getAmountPay());
            amountPay += pricePay;
            orderGoods.setAmountPay(pricePay);


            this.orderGoodsMapper.insert(orderGoods);

            //单品赠start---
            if (null != cartVo.getActivityGiftList() && cartVo.getActivityGiftList().size() > 0) {
                for (ActivityGiftVo activityGiftVo : cartVo.getActivityGiftList()) {
                    if (activityGiftVo.getGiftNum() == 0) continue;
                    if (!activityGiftVo.getGiftRemark().equals("0")) continue;
                    OrderGoods orderGoods9 = new OrderGoods();
                    orderGoods9.setGoodsPrice(activityGiftVo.getGoodsPrice());
                    orderGoods9.setGoodsName(activityGiftVo.getGoodsName());
                    orderGoods9.setGoodsImage(activityGiftVo.getGoodsImg());
                    orderGoods9.setGoodsPayPrice(activityGiftVo.getGoodsPrice());

                    double giftNum = (activityGiftVo.getGiftNum() * activityGiftVo.getZxB2bNumLimit());
                    orderGoods9.setGoodsNum(giftNum);
                    orderGoods9.setErpLeastsaleqty(activityGiftVo.getZxB2bNumLimit());

                    orderGoods9.setGoodsId(activityGiftVo.getErpGoodsId());
                    orderGoods9.setErpGoodsId(activityGiftVo.getErpGoodsId());
                    orderGoods9.setErpStorageId(Math.toIntExact(activityGiftVo.getStorageId()));
                    orderGoods9.setErpGoodsUnit(activityGiftVo.getGoodsunit());
                    orderGoods9.setErpLotNo(activityGiftVo.getLotno());
                    orderGoods9.setLotId(activityGiftVo.getLotId());
                    orderGoods9.setGoodsSpec(activityGiftVo.getGoodstype());
                    orderGoods9.setAsId(activityGiftVo.getAsId());
                    orderGoods9.setActivityId(activityGiftVo.getActivityId());
                    orderGoods9.setGoodType(4);
                    orderGoods9.setOrderId(orderInfoVo.getId());
                    orderGoods9.setMemberId(orderInfoVo.getMemberId());
                    orderGoods9.setCreateTime(LocalDateTime.now());
                    orderGoods9.setRemark("单品赠--" + activityGiftVo.getActivityId());


                    giftAmount += activityGiftVo.getAmountPay();
                    amount += activityGiftVo.getAmountPay();
                    amountPay += activityGiftVo.getAmountPay();
                    orderGoods9.setAmountPay(activityGiftVo.getAmountPay());
                    orderGoods9.setAmountNum(activityGiftVo.getAmountPay());
                    this.orderGoodsMapper.insert(orderGoods9);
                }
            }
//单品赠end---

            if (cartVo.getGoodType() == 4) {
                giftAmount += cartVo.getAmountPay();
            } else if (cartVo.getGoodType() != 4 && cartVo.getGoodType() != 9) {
                longList.add(cartVo.getErpGoodsId());
                notGiftAmount += cartVo.getAmountPay();
                if (goodsList.length > 0) {
                    if (!Arrays.asList(goodsList).contains(cartVo.getErpGoodsId().toString())) {
                        oneOrderAmount += cartVo.getAmountPay();
                    }
                }
            }

            rptAmount += cartVo.getRptAmount();


        }

        orderInfoVo.setRptAmount(rptAmount);
        orderInfoVo.setOrderAmount(IncaUtils.toErpPriceDouble(amount));
        orderInfoVo.setGoodsAmount(IncaUtils.toErpPriceDouble(amountPay));
        orderInfoVo.setAllDiscount(0);
        this.orderInfoMapper.updateById(orderInfoVo);

        //商品赠优惠券
        this.sendCouponToMember(orderInfoVo);

        //使用优惠券
        if (StringUtils.isNotBlank(orderInfoVo.getCouponIds())) {
            String[] couponIds1 = StringUtils.split(orderInfoVo.getCouponIds(), ',');
            for (String couponId : couponIds1) {
                double couponAdmount = 0L;
                //使用优惠券
                if (null != couponId) {
                    Coupon couponDB = this.couponMapper.selectById(couponId);
                    AssertExt.notNull(couponDB, "优惠券id[%s]不存在", couponId);
                    //现金券
                    if (couponDB.getType().equals(Coupon.EType.CASH_TICKET.val())) {

                        orderInfoVo.setGoodsAmount(IncaUtils.toErpPriceDouble(notGiftAmount - couponDB.getReduceAmount() + giftAmount));
                        couponAdmount = couponDB.getReduceAmount();
                    }
                    //满减券
                    if (couponDB.getType().equals(Coupon.EType.FULL_REDUCTION_TICKET.val())) {
                        if (couponDB.getGoodsSetType().equals(Coupon.EGoodsSetType.PART.val())) {
                            List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetById(longList, couponDB.getId());
                            if (null != erpGoodsList || erpGoodsList.size() > 0) {
                                List<OrderGoods> orderGoodsList1 = this.couponMapper.getOrderGoodsByCouponId(orderInfoVo.getId(), couponDB.getId());
                                double asPrice3 = 0L;
                                List<Double> goodsNumList = orderGoodsList1.stream().map(orderGoodsVo -> {
                                    if (cartVoList.stream().anyMatch(item -> item.getErpGoodsId().equals(orderGoodsVo.getErpGoodsId()))) {

                                        return orderGoodsVo.getAmountPay();
                                    }
                                    return 0.0;
                                }).collect(Collectors.toList());


                                for (double num : goodsNumList) {
                                    asPrice3 += num;
                                }
                                notGiftAmount = asPrice3;
                                if (asPrice3 >= couponDB.getFullAmount()) {
                                    couponAdmount = couponDB.getReduceAmount();
                                }

                            }
                        } else if (couponDB.getGoodsSetType().equals(Coupon.EGoodsSetType.UN_VISIBLE.val())) {
                            List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetByIdNoSee(couponDB.getId());
                            List<OrderGoods> orderGoodsListNotGift = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoVo.getId()).ne("GOOD_TYPE", 4));
                            List<OrderGoods> orderGoodsList1 = new ArrayList<>();
                            orderGoodsListNotGift.forEach(cart -> {
                                if (erpGoodsList.stream().anyMatch(item -> !item.equals(cart.getErpGoodsId()))) {
                                    orderGoodsList1.add(cart);
                                }
                            });

                            if (null != orderGoodsList1 && orderGoodsList1.size() > 0) {
                                double cartPrice = 0L;
                                for (OrderGoods orderGoods : orderGoodsList1) {
                                    //GoodsInfoVo goodsInfoVo = this.goodsInfoMapper.getGoodsInfo(orderGoods.getErpGoodsId(), orderInfoVo.getErpCustomerId(), Math.toIntExact(orderGoods.getErpStorageId()));
                                    cartPrice += orderGoods.getAmountPay();
                                }
                                notGiftAmount = cartPrice;
                                if (cartPrice >= couponDB.getFullAmount()) {
                                    couponAdmount = couponDB.getReduceAmount();
                                }
                            }
                        } else {
                            if (notGiftAmount >= couponDB.getFullAmount()) {
                                couponAdmount = couponDB.getReduceAmount();
                            }
                        }
                    }
                    //折扣券
                    if (couponDB.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
                        if (couponDB.getGoodsSetType().equals(Coupon.EGoodsSetType.PART.val())) {
                            List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetById(longList, couponDB.getId());
                            if (null != erpGoodsList || erpGoodsList.size() > 0) {
                                List<OrderGoods> orderGoodsList1 = this.couponMapper.getOrderGoodsByCouponId(orderInfoVo.getId(), couponDB.getId());
                                double asPrice3 = 0L;
                                List<Double> goodsNumList = cartVoList.stream().map(orderGoodsVo -> {
                                    if (orderGoodsList1.stream().anyMatch(item -> item.getErpGoodsId().equals(orderGoodsVo.getErpGoodsId()))) {
                                        return orderGoodsVo.getAmountPay();
                                    }
                                    return 0.0;
                                }).collect(Collectors.toList());

                                for (double price : goodsNumList) {
                                    asPrice3 += price;
                                }
                                notGiftAmount = asPrice3;
                                if (asPrice3 >= couponDB.getFullAmount()) {
                                    couponAdmount = notGiftAmount - (IncaUtils.toErpPriceDouble4(couponDB.getDiscount()) * notGiftAmount) * 1L;

                                }

                            }
                        } else if (couponDB.getGoodsSetType().equals(Coupon.EGoodsSetType.UN_VISIBLE.val())) {
                            List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetByIdNoSee(couponDB.getId());
                            List<OrderGoods> orderGoodsListNotGift = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoVo.getId()).ne("GOOD_TYPE", 4));
                            List<OrderGoods> orderGoodsList1 = new ArrayList<>();
                            orderGoodsListNotGift.forEach(cart -> {
                                if (erpGoodsList.stream().anyMatch(item -> !item.equals(cart.getErpGoodsId()))) {
                                    orderGoodsList1.add(cart);
                                }
                            });

                            if (null != orderGoodsList1 && orderGoodsList1.size() > 0) {
                                double cartPrice = 0L;
                                for (OrderGoods orderGoods : orderGoodsList1) {
                                    cartPrice += orderGoods.getAmountPay();
                                }
                                notGiftAmount = cartPrice;
                                if (cartPrice >= couponDB.getFullAmount()) {
                                    couponAdmount = notGiftAmount - (IncaUtils.toErpPriceDouble4(couponDB.getDiscount()) * notGiftAmount) * 1L;
                                }
                            }
                        } else {
                            couponAdmount = notGiftAmount - (IncaUtils.toErpPriceDouble4(couponDB.getDiscount()) * notGiftAmount) * 1L;
                        }
                    }
                    if (couponAdmount > 0) {
                        List<CouponReceive> couponReceiveDB = this.couponReceiveMapper.selectList(new QueryWrapper<CouponReceive>().eq("status", CouponReceive.EStatus.UNUSED.val()).eq("COUPON_ID", couponId).eq("MEMBER_ID", orderInfoVo.getErpCustomerId()).orderByAsc("USE_END_TIME"));

                        if (null != couponReceiveDB && couponReceiveDB.size() > 0) {

                            couponReceiveDB.get(0).setStatus(CouponReceive.EStatus.USE.val());
                            this.couponReceiveMapper.updateById(couponReceiveDB.get(0));

                            CouponLog couponLog = CouponLog.builder().giftNum(0L).couponId(Long.valueOf(couponId))
                                    .memberId(orderInfoVo.getErpCustomerId()).couponAmount(couponAdmount).orderAmount(notGiftAmount)
                                    .orderFinalAmount(orderInfoVo.getGoodsAmount())
                                    .couponCode(couponReceiveDB.get(0).getCouponCode())
                                    .orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(1).build();
                            this.addCouponLog(couponLog);

                            List<OrderGoods> orderGoodsListDiscount = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoVo.getId()).notIn("GOOD_TYPE", 4, 9));
                            if (couponDB.getGoodsSetType().equals(Coupon.EGoodsSetType.PART.val())) {
                                List<OrderGoods> orderGoodsList1 = this.couponMapper.getOrderGoodsByCouponId(orderInfoVo.getId(), couponDB.getId());
                                orderGoodsListDiscount = orderGoodsList1;
                            } else if (couponDB.getGoodsSetType().equals(Coupon.EGoodsSetType.UN_VISIBLE.val())) {
                                List<Long> erpGoodsList = this.couponMapper.getErpGoodsSetByIdNoSee(couponDB.getId());
                                List<OrderGoods> orderGoodsListUn = new ArrayList<>();
                                orderGoodsListDiscount.forEach(orderGoods -> {
                                    if (erpGoodsList.stream().anyMatch(item -> !item.equals(orderGoods.getErpGoodsId()))) {
                                        orderGoodsListUn.add(orderGoods);
                                    }
                                });
                                if (null != orderGoodsListUn && orderGoodsListUn.size() > 0) {
                                    orderGoodsListDiscount = orderGoodsListUn;
                                }

                            }

                            double goodsAmountCoupon = 0L;
                            double couponAdmount1 = 0L;

                            for (int i = 0; i < orderGoodsListDiscount.size(); i++) {
                                if (orderGoodsListDiscount.get(i).getGoodType() == 4 || orderGoodsListDiscount.get(i).getGoodType() == 9)
                                    continue;


                                double allDiscount1 = ((couponAdmount * orderGoodsListDiscount.get(i).getAmountPay()) / notGiftAmount) * 1L;
                                couponAdmount1 += allDiscount1;

                                double amountPay1 = 0L;
                                if (i == orderGoodsListDiscount.size()) {
                                    amountPay1 = orderGoodsListDiscount.get(i).getAmountPay() - (couponAdmount - couponAdmount1);
                                    double amountPay2 = IncaUtils.toErpPriceDouble(amountPay1);
                                    orderGoodsListDiscount.get(i).setAmountPay(amountPay2);
                                } else {
                                    amountPay1 = orderGoodsListDiscount.get(i).getAmountPay() - allDiscount1;
                                    double amountPay2 = IncaUtils.toErpPriceDouble(amountPay1);
                                    orderGoodsListDiscount.get(i).setAmountPay(amountPay2);
                                }


                                orderGoodsListDiscount.get(i).setGoodsPayPrice(amountPay1 / IncaUtils.toErpPriceDouble4(orderGoodsListDiscount.get(i).getGoodsNum()));
                                String remark = orderGoodsListDiscount.get(i).getRemark();
                                if (couponAdmount > 0) {
                                    remark += "参与使用优惠券--" + couponDB.getId();
                                }

                                orderGoodsListDiscount.get(i).setRemark(remark);
                                this.orderGoodsMapper.updateById(orderGoodsListDiscount.get(i));


                            }
                        }
                    }
                }
                if (couponAdmount > 0) {

                    List<OrderGoods> orderGoodsList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoVo.getId()).notIn("GOOD_TYPE", 4, 9));

                    notGiftAmount = 0L;
                    for (OrderGoods orderGoods : orderGoodsList) {
                        notGiftAmount += orderGoods.getAmountPay();
                    }
                    if (null != orderInfoVo.getReturnAmount()) {
                        orderInfoVo.setRptAmount(orderInfoVo.getReturnAmount() + couponAdmount);
                    } else {
                        orderInfoVo.setRptAmount(couponAdmount);
                    }
                    orderInfoVo.setGoodsAmount((IncaUtils.toErpPriceDouble(notGiftAmount + giftAmount)));
                    this.orderInfoMapper.updateById(orderInfoVo);
                }
            }

        }


        //全场折扣
        ActivityVo activityDiscount = this.activityMapper.getActivityAllDiscount(member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());

        double discountAmount = 0L;
        if (null != activityDiscount) {
            if (isJoinActivity(activityDiscount.getId(), orderInfoVo.getMemberId()) == 0) {
                log.info("全场折扣-activityDiscount.getDiscount()--", activityDiscount.getDiscount());
                if (null != activityDiscount.getAmountSatisfied() || activityDiscount.getAmountSatisfied() > 0) {

                    if (notGiftAmount >= activityDiscount.getAmountSatisfied()) {
                        discountAmount = (IncaUtils.toErpPriceDouble4(activityDiscount.getDiscount()) * notGiftAmount * 1L);
                        this.addActivityOrder(activityDiscount.getId(), orderInfoVo.getId(), activityDiscount.getAsId());
                        orderInfoVo.setGoodsAmount((IncaUtils.toErpPriceDouble(discountAmount + giftAmount)));
                    }
                } else {
                    discountAmount = IncaUtils.toErpPriceDouble4(activityDiscount.getDiscount()) * notGiftAmount;
                    orderInfoVo.setGoodsAmount((IncaUtils.toErpPriceDouble(discountAmount + giftAmount)));
                    this.addActivityOrder(activityDiscount.getId(), orderInfoVo.getId(), activityDiscount.getAsId());
                }

                List<OrderGoods> orderGoodsListDiscount = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoVo.getId()).notIn("GOOD_TYPE", 4, 9));
                log.info("优惠价格discountAmount{}", discountAmount);


                if (discountAmount > 0) {

                    double discountAmount1 = 0L;
                    double sumDiscount = notGiftAmount - discountAmount;
                    for (int i = 0; i < orderGoodsListDiscount.size(); i++) {
                        if (orderGoodsListDiscount.get(i).getGoodType() == 4 || orderGoodsListDiscount.get(i).getGoodType() == 9)
                            continue;


                        double allDiscount1 = ((sumDiscount * orderGoodsListDiscount.get(i).getAmountPay()) / notGiftAmount) * 1L;
                        discountAmount1 += allDiscount1;

                        double amountPay1 = orderGoodsListDiscount.get(i).getAmountPay();
                        if (i == orderGoodsListDiscount.size()) {
                            amountPay1 = orderGoodsListDiscount.get(i).getAmountPay() - (discountAmount - discountAmount1);

                        } else {
                            amountPay1 = orderGoodsListDiscount.get(i).getAmountPay() - allDiscount1;

                        }
                        double amountPay2 = IncaUtils.toErpPriceDouble(amountPay1);
                        orderGoodsListDiscount.get(i).setAmountPay(amountPay2);

                        orderGoodsListDiscount.get(i).setGoodsPayPrice(amountPay1 / IncaUtils.toErpPriceDouble4(orderGoodsListDiscount.get(i).getGoodsNum()));
                        orderGoodsListDiscount.get(i).setRemark(orderGoodsListDiscount.get(i).getRemark() + "参与全场折扣--" + activityDiscount.getId() + activityDiscount.getContent() + "---" + activityDiscount.getAsId());
                        this.orderGoodsMapper.updateById(orderGoodsListDiscount.get(i));


                    }
                    if (null != orderInfoVo.getReturnAmount()) {
                        orderInfoVo.setRptAmount(orderInfoVo.getReturnAmount() + sumDiscount);
                    } else {
                        orderInfoVo.setRptAmount(sumDiscount);
                    }
                    orderInfoVo.setAllDiscount(1);
                    this.orderInfoMapper.updateById(orderInfoVo);
                }
            }
        }


        //全场赠优惠券
        ActivityVo activityCoupon = this.activityMapper.getActivityByCoupon(member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
        if (null != activityCoupon) {
            log.info("全场赠优惠券-activityCoupon.getCouponId()--", activityCoupon.getCouponId());

            if (isJoinActivity(activityCoupon.getId(), orderInfoVo.getMemberId()) == 0) {

                if (null != activityCoupon.getAmountSatisfied() || activityCoupon.getAmountSatisfied() > 0) {
                    if (orderInfoVo.getGoodsAmount() >= activityCoupon.getAmountSatisfied()) {
                        if (null != activityCoupon.getCouponId() && null != activityCoupon.getGiftNum() && activityCoupon.getGiftNum() > 0) {
                            Long giftNumCoupon = Long.valueOf(activityCoupon.getGiftNum());
                            for (int i = 0; i < giftNumCoupon; i++) {
                                this.addCouponReceive(orderInfoVo, activityCoupon.getCouponId(), activityCoupon.getId(), activityCoupon.getAsId());
                            }
                            this.addActivityOrder(activityCoupon.getId(), orderInfoVo.getId(), activityCoupon.getAsId());
                            CouponLog couponLog = CouponLog.builder().giftNum(giftNumCoupon).couponId(activityCoupon.getCouponId())
                                    .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                            this.addCouponLog(couponLog);
                        }
                    }

                } else {
                    if (null != activityCoupon.getCouponId() && null != activityCoupon.getGiftNum() && activityCoupon.getGiftNum() > 0) {
                        Long giftNumCoupon = Long.valueOf(activityCoupon.getGiftNum());
                        for (int i = 0; i < giftNumCoupon; i++) {
                            this.addCouponReceive(orderInfoVo, activityCoupon.getCouponId(), activityCoupon.getId(), activityCoupon.getAsId());
                        }
                        this.addActivityOrder(activityCoupon.getId(), orderInfoVo.getId(), activityCoupon.getAsId());
                        CouponLog couponLog = CouponLog.builder().giftNum(giftNumCoupon).couponId(activityCoupon.getCouponId())
                                .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                        this.addCouponLog(couponLog);
                    }
                }
            }
        }

        //首次下单满1000减30

        if (orderPrice > 0 && oneOrderAmount >= 1000) {
            log.info("notGiftAmount{}", oneOrderAmount);
            log.info("首次下单满1000减30{}", orderPrice);
            List<OrderGoods> orderGoodsListOneOrder = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>()
                    .eq("order_id", orderInfoVo.getId()).notIn("GOOD_TYPE", 4, 9)
                    .notIn("ERP_GOODS_ID", goodsList));

            double discountAmount1 = 0L;
            //Long sumDiscount = oneOrderAmount - orderPrice;
            for (int i = 0; i < orderGoodsListOneOrder.size(); i++) {
                if (orderGoodsListOneOrder.get(i).getGoodType() == 4 || orderGoodsListOneOrder.get(i).getGoodType() == 9)
                    continue;

                double amountPay2 = orderGoodsListOneOrder.get(i).getAmountPay();
                log.info("amountPay2{}", amountPay2);

                double allDiscount1 = ((orderPrice * amountPay2) / oneOrderAmount) * 1L;
                discountAmount1 += allDiscount1;

                if (i == orderGoodsListOneOrder.size()) {
                    amountPay2 = amountPay2 - orderPrice - discountAmount1;

                } else {
                    amountPay2 = amountPay2 - allDiscount1;

                }
                double amountPay3 = IncaUtils.toErpPriceDouble(amountPay2);
                orderGoodsListOneOrder.get(i).setAmountPay(amountPay3);
                log.info("amountPay2{}", amountPay2);
                orderGoodsListOneOrder.get(i).setGoodsPayPrice(amountPay2 / IncaUtils.toErpPriceDouble4(orderGoodsListOneOrder.get(i).getGoodsNum()));


                if (null != orderGoodsListOneOrder.get(i).getRemark()) {
                    orderGoodsListOneOrder.get(i).setRemark(orderGoodsListOneOrder.get(i).getRemark() + "首次下单满1000减30--");
                } else {
                    orderGoodsListOneOrder.get(i).setRemark("首次下单满1000减30--");
                }

                this.orderGoodsMapper.updateById(orderGoodsListOneOrder.get(i));
            }
            if (null != orderInfoVo.getReturnAmount()) {
                orderInfoVo.setRptAmount(orderInfoVo.getReturnAmount() + orderPrice);
            } else {
                orderInfoVo.setRptAmount(orderPrice);
            }

            orderInfoVo.setGoodsAmount(IncaUtils.toErpPriceDouble(orderInfoVo.getGoodsAmount() - orderPrice));
            this.orderInfoMapper.updateById(orderInfoVo);
        }


        List<OrderGoods> orderGoodsList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoVo.getId()));

        double orderAmount = 0L;
        double goodsAmount = 0L;
        for (OrderGoods orderGoods : orderGoodsList) {

            orderAmount += orderGoods.getAmountNum();
            goodsAmount += orderGoods.getAmountPay();
        }
        orderInfoVo.setOrderAmount(orderAmount);
        orderInfoVo.setGoodsAmount(goodsAmount);
        this.orderInfoMapper.updateById(orderInfoVo);

        List<LotteryDialDetailVo> goodsInfoVoList = this.lotteryDialDetailMapper.getMyGoodsInfoVoByLotteryList(orderInfoVo.getMemberId());
        if (goodsInfoVoList.size() > 0) {
            for (LotteryDialDetailVo goodsInfoVo : goodsInfoVoList) {
                LotteryDialDetail lotteryDialDetailDB = this.lotteryDialDetailMapper.selectById(goodsInfoVo.getId());
                lotteryDialDetailDB.setPrizeState(LotteryDialDetail.EPrizeState.TO_PRIZE.val());
                this.lotteryDialDetailMapper.updateById(lotteryDialDetailDB);

                OrderGoods orderGoods10 = new OrderGoods();
                orderGoods10.setGoodsPrice(0);
                orderGoods10.setGoodsName(goodsInfoVo.getGoodsname());
                orderGoods10.setGoodsImage(goodsInfoVo.getGoodsImg());
                orderGoods10.setGoodsPayPrice(0);

                double giftNum = (goodsInfoVo.getPrizeNum() * goodsInfoVo.getZxB2bNumLimit());
                orderGoods10.setGoodsNum(giftNum);
                orderGoods10.setErpLeastsaleqty(goodsInfoVo.getZxB2bNumLimit());

                orderGoods10.setGoodsId(goodsInfoVo.getErpGoodsId());
                orderGoods10.setErpGoodsId(goodsInfoVo.getErpGoodsId());
                orderGoods10.setErpStorageId(691);
                orderGoods10.setErpGoodsUnit(goodsInfoVo.getGoodsunit());
                //orderGoods10.setErpLotNo(goodsInfoVo.getLotno());
                // orderGoods10.setLotId(goodsInfoVo.getLotid());
                orderGoods10.setGoodsSpec(goodsInfoVo.getGoodstype());
                // orderGoods10.setAsId(goodsInfoVo.getAsId());
                orderGoods10.setActivityId(goodsInfoVo.getActivityId());
                orderGoods10.setGoodType(10);
                orderGoods10.setOrderId(orderInfoVo.getId());
                orderGoods10.setMemberId(orderInfoVo.getMemberId());
                orderGoods10.setCreateTime(LocalDateTime.now());
                orderGoods10.setRemark("抽奖奖品--" + goodsInfoVo.getActivityId());
                orderGoods10.setPrizeId(goodsInfoVo.getId());
                orderGoods10.setAmountPay(0);
                orderGoods10.setAmountNum(0);
                this.orderGoodsMapper.insert(orderGoods10);
            }

            LotteryLog lotteryLog = new LotteryLog();
            lotteryLog.setMemberId(orderInfoVo.getMemberId());
            lotteryLog.setRemark("兑换奖品，订单id" + orderInfoVo.getId());
            lotteryLog.setCreateTime(LocalDateTime.now());
            this.lotteryLogMapper.insert(lotteryLog);
        }

    }

    private void sendCouponToMember(OrderInfoVo orderInfoVo) {
        List<Long> longList = this.orderGoodsMapper.getOrderIdByErpGoodsId(orderInfoVo.getId());
        if (longList.size() == 0) return;
        List<OrderGoodsVo> activityVoList = this.activityMapper.getActivityByPitchOnGoodsId(longList, orderInfoVo.getErpCustomerId(), LocalDateTime.now().getDayOfWeek().getValue(), orderInfoVo.getId());
        Map<Long, List<OrderGoodsVo>> map = new HashMap<>();
        List<Long> activityIdList = new ArrayList<>();

        for (OrderGoodsVo orderGoodsVo : activityVoList) {
            if (null != orderGoodsVo.getAcId() && orderGoodsVo.getAcId() > 0) {
                if (!activityIdList.stream().anyMatch(item -> item.equals(orderGoodsVo.getAcId()))) {
                    activityIdList.add(orderGoodsVo.getAcId());
                }
            }

        }


        //按活动id分组
        for (Long activityId : activityIdList) {
            if (activityId > 0) {
                List<OrderGoodsVo> orderGoodsVoList = new ArrayList<>();
                for (OrderGoodsVo orderGoodsVo : activityVoList) {
                    if (orderGoodsVo.getAcId().equals(activityId)) {
                        orderGoodsVoList.add(orderGoodsVo);
                    }
                }
                map.put(activityId, orderGoodsVoList);
            }
        }

        for (Map.Entry<Long, List<OrderGoodsVo>> entry : map.entrySet()) {
            if (entry.getKey() > 1) {
                List<OrderGoodsVo> orderGoodsVoList = entry.getValue();
                //活动
                Activity activityDB = this.activityMapper.selectById(entry.getKey());

                Double asNum3 = 0.0;


                List<Double> goodsNumList = orderGoodsVoList.stream().map(orderGoodsVo -> {
                    return orderGoodsVo.getGoodsNum();
                }).collect(Collectors.toList());

                for (Double num : goodsNumList) {
                    asNum3 += num;
                }

                Double asPrice3 = 0.0;

                List<Double> goodsPriceList = orderGoodsVoList.stream().map(orderGoodsVo -> {
                    return orderGoodsVo.getAmountPay();
                }).collect(Collectors.toList());

                for (Double price : goodsPriceList) {
                    asPrice3 += price;
                }


                List<ActivityStrategy> activityStrategyList5 = this.activityStrategyMapper.selectList(new QueryWrapper<ActivityStrategy>().eq("ACTIVITY_ID", activityDB.getId()).eq("IS_USE", Constant.IS_USE.ON).isNotNull("COUPON_ID"));
                if (null == activityStrategyList5) return;

                for (ActivityStrategy activityStrategyDB : activityStrategyList5) {
                    //任选商品数量
                    if (activityStrategyDB.getGoodsStrategy() == 10) {
                        boolean a = true;
                        List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.getActivityGoodsByAcIdAsId(activityDB.getId(), activityStrategyDB.getId());
                        if (null != activityGoodsList && activityGoodsList.size() != orderGoodsVoList.size())
                            continue;

                        do {
                            outterLoop:
                            for (ActivityGoods activityGoods : activityGoodsList) {
                                for (OrderGoodsVo orderGoodsVo : orderGoodsVoList) {
                                    if (orderGoodsVo.getErpGoodsId().equals(activityGoods.getErpGoodsId())) {
                                        //判断策略商品策略数量是否有上限
                                        if (activityStrategyDB.getTopLimit() != 0) {
                                            AssertExt.isTrue(orderGoodsVo.getGoodsNum() <= activityStrategyDB.getTopLimit(), "该商品[%s]限购[%s]", activityGoods.getGoodsName(), activityGoods.getTopLimit());
                                        }
                                        //判断是否满足数量
                                        AssertExt.isFalse(0 == activityGoods.getConditionNum(), "商品满足数量为空或者为0【%s】-【%s】-【%s】", activityGoods.getGoodsName(), activityGoods.getActivityId(), activityGoods.getAsId());

                                        if (orderGoodsVo.getGoodsNum() >= activityGoods.getConditionNum()) {
                                            a = true;
                                        } else {
                                            a = false;
                                            break outterLoop;
                                        }
                                    }
                                }
                            }

                            if (a) {
                                if (activityDB.getIsSuperposition() == 1) {

                                    this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                    this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                    CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                            .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                    this.addCouponLog(couponLog);

                                    orderGoodsVoList.forEach(orderGoodsVo -> {
                                        ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityDB.getId(), activityStrategyDB.getId(), orderGoodsVo.getErpGoodsId());
                                        orderGoodsVo.setGoodsNum(orderGoodsVo.getGoodsNum() - activityGoodsDB.getConditionNum());
                                    });
                                    a = true;

                                } else {

                                    this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                    this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                    CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                            .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                    this.addCouponLog(couponLog);

                                    a = false;
                                    break;
                                }

                            }


                        } while (a);
                        continue;
                    }
                    //任选商品金额
                    else if (activityStrategyDB.getGoodsStrategy() == 30) {

                        boolean a = true;
                        List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.selectList(new QueryWrapper<ActivityGoods>().eq("IS_USE", Constant.IS_USE.ON).eq("ACTIVITY_ID", activityDB.getId()).eq("AS_ID", activityStrategyDB.getId()));
                        if (null != activityGoodsList && activityGoodsList.size() != orderGoodsVoList.size())
                            continue;


                        do {
                            outterLoop:
                            for (ActivityGoods activityGoods : activityGoodsList) {
                                for (OrderGoodsVo orderGoodsVo : orderGoodsVoList) {
                                    if (orderGoodsVo.getErpGoodsId().equals(activityGoods.getErpGoodsId())) {
                                        //判断是否满足价格
                                        AssertExt.isFalse(0 == activityGoods.getConditionPrice(), "商品满足价格为空或者为0【%s】-【%s】-【%s】", activityGoods.getGoodsName(), activityGoods.getActivityId(), activityGoods.getAsId());

                                        if (orderGoodsVo.getAmountPay() >= activityGoods.getConditionPrice()) {
                                            a = true;
                                        } else {
                                            a = false;
                                            break outterLoop;
                                        }
                                    }
                                }
                            }

                            if (a) {
                                if (activityDB.getIsSuperposition() == 1) {

                                    this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                    this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                    CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                            .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                    this.addCouponLog(couponLog);

                                    orderGoodsVoList.forEach(orderGoodsVo -> {
                                        ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityDB.getId(), activityStrategyDB.getId(), orderGoodsVo.getErpGoodsId());
                                        orderGoodsVo.setAmountPay(orderGoodsVo.getAmountPay() - activityGoodsDB.getConditionPrice());
                                    });

                                    a = true;

                                } else {
                                    this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                    this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                    CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                            .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                    this.addCouponLog(couponLog);
                                    a = false;
                                    break;
                                }
                            }


                        } while (a);

                        continue;
                    }
                    //任选策略金额
                    else if (activityStrategyDB.getGoodsStrategy() == 40) {
                        //判断是否满足金额
                        AssertExt.isFalse(0 == activityStrategyDB.getAmountSatisfied(), "满足价格为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());

                        if (asPrice3 >= activityStrategyDB.getAmountSatisfied()) {
                            if (activityDB.getIsSuperposition() == 1) {
                                int num = (int) (asPrice3 / activityStrategyDB.getAmountSatisfied());
                                asPrice3 -= activityStrategyDB.getAmountSatisfied() * num;

                                for (int i = 0; i < num; i++) {
                                    this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                    this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                    CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                            .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                    this.addCouponLog(couponLog);
                                }
                            } else {

                                this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                        .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                this.addCouponLog(couponLog);

                                break;
                            }
                        }
                        continue;

                    }
                    //任选策略数量
                    else {
                        //判断是否满足数量
                        AssertExt.isFalse(0 == activityStrategyDB.getMeetQuantity(), "满足数量为空或者为0【%s】-【%s】-【%s】", activityStrategyDB.getStrategyName(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());

                        if (asNum3 >= activityStrategyDB.getMeetQuantity()) {
                            if (activityDB.getIsSuperposition() == 1) {
                                int num = (int) (asNum3 / activityStrategyDB.getMeetQuantity());
                                asNum3 -= activityStrategyDB.getMeetQuantity() * num;

                                for (int i = 0; i < num; i++) {
                                    this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                    this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                    CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                            .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                    this.addCouponLog(couponLog);
                                }

                            } else {
                                this.addCouponReceive(orderInfoVo, activityStrategyDB.getCouponId(), activityStrategyDB.getActivityId(), activityStrategyDB.getId());
                                this.addActivityOrder(activityStrategyDB.getActivityId(), orderInfoVo.getId(), activityStrategyDB.getId());
                                CouponLog couponLog = CouponLog.builder().giftNum(1L).couponId(activityStrategyDB.getCouponId())
                                        .memberId(orderInfoVo.getErpCustomerId()).orderId(orderInfoVo.getId()).orderNo(orderInfoVo.getOrderNo()).status(4).build();
                                this.addCouponLog(couponLog);

                                break;
                            }

                        }
                        continue;
                    }
                }
            }
        }
    }

    @Override
    public List<CartVo> getCartToPitchOff(Long memberId) {
        Member member = this.memberMapper.selectById(memberId);

        List<CartVo> cartVoListOff = this.cartMapper.getPitchOnList(memberId, Constant.IS_PITCH_ON.OFF, 1L, 0);
        if (null != cartVoListOff && cartVoListOff.size() > 0) {
            for (CartVo cartVo : cartVoListOff) {

                Long[] storageIds = Constant.STO_RANGE_IDS;

                int stqty = this.goodsInfoMapper.getStqty(cartVo.getErpGoodsId(), member.getErpUserId(), storageIds, cartVo.getAccflag(), cartVo.getLotId());
                //AssertExt.isTrue(stqty - cartVo.getGoodsNum() >= 0, "[%s]库存不足", cartVo.getGoodsName());
                cartVo.setStqty(stqty);
                cartVo.setGoodsPayPrice(cartVo.getGoodsPrice());

                //原价
                List<PubGoodsPriceVo> pubGoodsPriceList5 = this.goodsInfoMapper.getPubGoodsPriceList(cartVo.getErpGoodsId(), member.getErpUserId());

                //特价
                if (cartVo.getStorageId() == 5) {
                    pubGoodsPriceList5 = this.goodsInfoMapper.getPubGoodsPriceList5(cartVo.getErpGoodsId(), member.getErpUserId());
                }
                if (null != pubGoodsPriceList5 && pubGoodsPriceList5.size() != 0) {
                    cartVo.setGoodsPrice(pubGoodsPriceList5.get(0).getPrice());
                    cartVo.setGoodsPayPrice(pubGoodsPriceList5.get(0).getPrice());
                }

                //单价
                Double activityPrice = cartVo.getGoodsPrice();

                cartVo.setPubGoodsPriceList(pubGoodsPriceList5);

                double erpLeastsaleqty = IncaUtils.toErpPriceDouble4(cartVo.getZxB2bNumLimit());
                //商品单价小计
                double amount = (cartVo.getGoodsPrice() * cartVo.getGoodsNum() * erpLeastsaleqty) * 1L;
                //优惠后的小计
                double priceTotal = (cartVo.getGoodsPrice() * cartVo.getGoodsNum() * erpLeastsaleqty) * 1L;

                cartVo.setActivityVo(this.activityMapper.getActivityAllDiscount(member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));
                cartVo.setActivityVoList(this.goodsServiceB2b.getActivityVoById(null, cartVo.getErpGoodsId(), member.getErpUserId(), 0));


                cartVo.setAmountNum(amount);
                cartVo.setAmountPay(priceTotal);

            }
        }
        return cartVoListOff;
    }

    @Override
    public List<CartVo> getCartToPitchOff5(Long memberId) {
        Member member = this.memberMapper.selectById(memberId);
        List<CartVo> cartVoList5 = this.cartMapper.getPitchOnList(memberId, Constant.IS_PITCH_ON.OFF, 5L, 0);
        if (null != cartVoList5 && cartVoList5.size() > 0) {
            for (CartVo cartVo : cartVoList5) {

                Long[] storageIds = Constant.STO_RANGE_IDS_V;
                //goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(goodsid, memberDB.getErpUserId(), lotid);

                int stqty = this.goodsInfoMapper.getStqty(cartVo.getErpGoodsId(), member.getErpUserId(), storageIds, cartVo.getAccflag(), cartVo.getLotId());
                //AssertExt.isTrue(stqty - cartVo.getGoodsNum() >= 0, "[%s]库存不足", cartVo.getGoodsName());
                cartVo.setStqty(stqty);
                cartVo.setGoodsPayPrice(cartVo.getGoodsPrice());

                //原价
                List<PubGoodsPriceVo> pubGoodsPriceList5 = this.goodsInfoMapper.getPubGoodsPriceList(cartVo.getErpGoodsId(), member.getErpUserId());

                //特价
                if (cartVo.getStorageId() == 5) {
                    pubGoodsPriceList5 = this.goodsInfoMapper.getPubGoodsPriceList5(cartVo.getErpGoodsId(), member.getErpUserId());
                }
                if (null != pubGoodsPriceList5 && pubGoodsPriceList5.size() != 0) {
                    cartVo.setGoodsPrice(pubGoodsPriceList5.get(0).getPrice());
                    cartVo.setGoodsPayPrice(pubGoodsPriceList5.get(0).getPrice());
                }

                //单价
                Double activityPrice = cartVo.getGoodsPrice();

                cartVo.setPubGoodsPriceList(pubGoodsPriceList5);

                double erpLeastsaleqty = IncaUtils.toErpPriceDouble4(cartVo.getZxB2bNumLimit());
                //商品单价小计
                double amount = (cartVo.getGoodsPrice() * cartVo.getGoodsNum() * erpLeastsaleqty) * 1L;
                //优惠后的小计
                double priceTotal = (cartVo.getGoodsPrice() * cartVo.getGoodsNum() * erpLeastsaleqty) * 1L;
                //商品数量小计
                double numTotal = cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit();

                cartVo.setAmountNum(amount);
                cartVo.setAmountPay(priceTotal);

            }
        }
        return cartVoList5;
    }

    @Override
    public List<CartVo> getCartToPitchOff9(Long memberId) {
        Member member = this.memberMapper.selectById(memberId);
        List<CartVo> cartVoList9 = this.cartMapper.getPitchOnList(memberId, Constant.IS_PITCH_ON.OFF, 691L, 9);
        if (null != cartVoList9 && cartVoList9.size() > 0) {
            for (CartVo cartVo : cartVoList9) {

                Long[] storageIds = new Long[]{691L};
                int stqty = this.goodsInfoMapper.getStqty(cartVo.getErpGoodsId(), member.getErpUserId(), storageIds, cartVo.getAccflag(), cartVo.getLotId());
                //AssertExt.isTrue(stqty - cartVo.getGoodsNum() >= 0, "[%s]库存不足", cartVo.getGoodsName());
                cartVo.setStqty(stqty);
                cartVo.setGoodsPayPrice(0L);
                cartVo.setGoodsPrice(0L);

                double erpLeastsaleqty = cartVo.getZxB2bNumLimit();

                //商品数量小计
                double numTotal = cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit();
                cartVo.setNumTotal(numTotal);
                cartVo.setAmountNum(cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit() * cartVo.getConvertibleIntegral());
                cartVo.setAmountPay(cartVo.getGoodsNum() * cartVo.getZxB2bNumLimit() * cartVo.getConvertibleIntegral());

            }
        }
        return cartVoList9;
    }

    @Transactional
    @Override
    public void updateCartGiftTmp(CartGiftTmp cartGiftTmp) {
        AssertExt.hasId(cartGiftTmp.getId(), "id为空");
        AssertExt.hasId(cartGiftTmp.getGoodsNum(), "数量为空");
        CartGiftTmp cartGiftTmpDB = this.cartGiftTmpMapper.selectById(cartGiftTmp.getId());
        AssertExt.notNull(cartGiftTmpDB, "无效id【%s】", cartGiftTmp.getId());
        AssertExt.isTrue(cartGiftTmp.getGoodsNum() <= cartGiftTmpDB.getNUM(), "不能超过可选数量");
        this.cartGiftTmpMapper.updateById(cartGiftTmp);

    }


    private void addScoreRecord(ScoreRecord scoreRecord) {

        scoreRecord.setCreateTime(LocalDateTime.now());
        this.scoreRecordMapper.insert(scoreRecord);

        Member memberDB = this.memberMapper.selectById(scoreRecord.getMemberId());
        memberDB.setIntegral(memberDB.getIntegral() + scoreRecord.getChanScore());
        this.memberMapper.updateById(memberDB);

    }

    @Override
    public void isTrueExpOrder(Long[] ids, String userRemark, Long userId) {
        AssertExt.notNull(ids, "id为空");
        for(Long id:ids){
            OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
            AssertExt.notNull(orderInfoDB, "无效id【%s】", id);
            if (orderInfoDB.getIsTrue().equals("0")) {
                orderInfoDB.setUserRemark(userRemark);
                orderInfoDB.setIsTrue("1");
                orderInfoDB.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                orderInfoDB.setExpRemark("-");
                this.orderInfoMapper.updateById(orderInfoDB);
                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("确认异常订单");
                orderLog.setUserId(userId);
                orderLog.setOrderId(orderInfoDB.getId());
                orderLog.setOrderStatus(OrderInfo.EExpStatus.NORMAL.val());
                orderLog.setDoneTime(LocalDateTime.now());
                orderLog.setToErpNum("v1");
                this.orderLogMapper.insert(orderLog);
            }
        }

    }

    @Override
    public IPage<OrderGoodsVo> getMyGoodsReport(Page page, OrderGoodsVo orderGoodsVo) {
        log.info("page.getSize()--{}", page.getSize(), page.getCurrent());
        log.info("page.getCurrent()--{}", page.getCurrent());
        Member member = this.memberMapper.selectById(orderGoodsVo.getMemberId());
        orderGoodsVo.setMemberId(member.getErpUserId());
        return this.orderInfoMapper.getMyGoodsReport(page, orderGoodsVo);
    }

    @Transactional
    @Override
    public String getOneGoodsReport(Page page, OrderGoodsVo orderGoodsVo) {
        Member member = this.memberMapper.selectById(orderGoodsVo.getMemberId());
        orderGoodsVo.setMemberId(member.getErpUserId());
        IPage<OrderGoodsVo> orderGoodsVoIPage = this.orderInfoMapper.getMyGoodsReport(page, orderGoodsVo);
        String qualityImg = "";
        for (OrderGoodsVo orderGoodsVo1 : orderGoodsVoIPage.getRecords()) {
            qualityImg += orderGoodsVo1.getQualityImg() + ",";
        }
        return qualityImg;
    }

    @Transactional
    @Override
    public void autoCancelOrder() {
        List<OrderInfo> orderInfoList = this.orderInfoMapper.autoCancelOrder();
        if (orderInfoList.size() > 0) {
            for (OrderInfo orderInfo : orderInfoList) {
                orderInfo.setOrderState(OrderInfo.EOrderState.CANCEL.val());
                this.orderInfoMapper.updateById(orderInfo);


                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("半小时自动取消未支付订单");
                orderLog.setOrderId(orderInfo.getId());
                orderLog.setOrderStatus(orderInfo.getOrderState());
                orderLog.setDoneTime(LocalDateTime.now());
                orderLog.setToErpNum("v1");
                this.orderLogMapper.insert(orderLog);

                //修改收款单状态
                RecDoc recDocDB = this.recDocMapper.selectOne(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfo.getId()));
                if (null != recDocDB) {
                    recDocDB.setStatus(RecDoc.EStatus.CANCEL.val());
                    recDocDB.setRecType(orderInfo.getPayMethod());
                    this.recDocMapper.updateById(recDocDB);
                }

            }
        }

    }

    @Transactional
    @Override
    public void autoFinanceOrder() {
        String finance = this.settingService.genPCMINTime("REC_FINANCE").getValue();
        if (!finance.equals("1")) return;
        List<OrderInfoVo> orderInfoList = this.orderInfoMapper.autoFinanceOrder();
        if (orderInfoList.size() > 0) {
            for (OrderInfoVo orderInfo : orderInfoList) {
                if (orderInfo.getFinanceTrue() == 1) return;

                FinanceVerificationDocVo financeVerificationDocVo = new FinanceVerificationDocVo();
                financeVerificationDocVo.setAmountPay(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount()));
                if (orderInfo.getErpAmount() > 0) {
                    financeVerificationDocVo.setAmountPay(IncaUtils.toErpPriceDouble4(orderInfo.getErpAmount()));
                }
                financeVerificationDocVo.setOrderType(1);
                financeVerificationDocVo.setAmountTotal(IncaUtils.toErpPriceDouble4(orderInfo.getOrderAmount()));
                financeVerificationDocVo.setAmountDiscount(IncaUtils.toErpPriceDouble4(orderInfo.getOrderAmount()) - IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount()));
                financeVerificationDocVo.setAmountDiscrepancy(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount() - orderInfo.getErpAmount()));
                financeVerificationDocVo.setCustomId(orderInfo.getErpCustomerId());
                financeVerificationDocVo.setOrderId(orderInfo.getId());
                financeVerificationDocVo.setOrderNo(orderInfo.getOrderNo());
                financeVerificationDocVo.setRemark(orderInfo.getConfirmRemark());
                financeVerificationDocVo.setCreateDate(orderInfo.getCreateTime());
                financeVerificationDocVo.setSettleMethod(orderInfo.getPayMethod());
                financeVerificationDocVo.setEntryId(1);
                User user = this.userMapper.selectById(orderInfo.getConfirmUserId());
                financeVerificationDocVo.setConfirmManId(user.getErpEmployeeId());
                financeVerificationDocVo.setInputManId(1L);
                financeVerificationDocVo.setOrderNo(orderInfo.getOrderNo());
                financeVerificationDocVo.setOrderId(orderInfo.getId());
                financeVerificationDocVo.setAmountVerification(IncaUtils.toErpPriceDouble4(orderInfo.getGoodsAmount()));
                RecDoc recDocDBPay = this.recDocMapper.selectOne(new QueryWrapper<RecDoc>()
                        .eq("ORDER_ID", orderInfo.getId())
                        .eq("REC_METHOD", RecDoc.ERecMethod.PAY_ORDER.val()));
                financeVerificationDocVo.setRecDocNo(recDocDBPay.getRecDocNo());
                financeVerificationDocVo.setPayDate(orderInfo.getPayTime());
                financeVerificationDocVo.setPayType(orderInfo.getPayMethod());
                financeVerificationDocVo.setBankId(44147L);
                financeVerificationDocVo.setPayFlowNo(orderInfo.getPayFlowNo());
                String num = RandomStringUtils.random(3, false, true);
                financeVerificationDocVo.setVersion("v1." + num);
                financeVerificationDocVo.setVerificationDate(orderInfo.getConfirmDate());

                if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.CASH.val())) {
                    financeVerificationDocVo.setVerificationType(2);
                } else if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.MONTH.val())) {
                    financeVerificationDocVo.setVerificationType(3);
                } else if (orderInfo.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val())) {
                    financeVerificationDocVo.setVerificationType(1);
                } else {
                    financeVerificationDocVo.setVerificationType(0);
                }

                List<FinanceVerificationDtlVo> financeVerificationDtlVoList = new ArrayList<>();

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
                            //financeVerificationDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
                            financeVerificationDtlVo.setOrderId(orderInfo.getId().toString());
                            // financeVerificationDtlVo.setOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                            financeVerificationDtlVo.setStorageId(orderGoods.getErpStorageId());
                            financeVerificationDtlVoList.add(financeVerificationDtlVo);
                        }
                    } else {
                        FinanceVerificationDtlVo financeVerificationDtlVo = new FinanceVerificationDtlVo();
                        financeVerificationDtlVo.setNum(orderGoods.getDtlgoodsqty());
                        financeVerificationDtlVo.setAmount(Double.valueOf(orderGoods.getTotal()));
                        financeVerificationDtlVo.setGoodsId(orderGoods.getGoodsId());
                        financeVerificationDtlVo.setSrcOrderId(orderInfo.getId().toString());
                        // financeVerificationDtlVo.setSrcOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                        financeVerificationDtlVo.setSrcErpOrderId(orderGoods.getSrcErpOrderId());
                        // financeVerificationDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
                        financeVerificationDtlVo.setOrderId(orderInfo.getId().toString());
                        // financeVerificationDtlVo.setOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                        financeVerificationDtlVo.setStorageId(orderGoods.getErpStorageId());
                        financeVerificationDtlVoList.add(financeVerificationDtlVo);
                    }

                  /*  FinanceVerificationDtlVo financeVerificationDtlVo = new FinanceVerificationDtlVo();
                    financeVerificationDtlVo.setNum(orderGoods.getDtlgoodsqty());
                    financeVerificationDtlVo.setAmount(Double.valueOf(orderGoods.getTotal()));
                    financeVerificationDtlVo.setGoodsId(orderGoods.getGoodsId());
                    financeVerificationDtlVo.setSrcOrderId(orderInfo.getId().toString());
                    financeVerificationDtlVo.setSrcOrderDtlId(orderGoods.getB2bOrderDtlId().toString());
                    financeVerificationDtlVo.setSrcErpOrderId(orderGoods.getSrcErpOrderId());
                    financeVerificationDtlVo.setSrcErpOrderDtlId(orderGoods.getSrcErpOrderDtlId());
                    financeVerificationDtlVo.setStorageId(orderGoods.getErpStorageId());

                    financeVerificationDtlVo.setOrderId(orderInfo.getId().toString());
                    financeVerificationDtlVo.setOrderDtlId(orderGoods.getB2bOrderDtlId().toString());

                    financeVerificationDtlVoList.add(financeVerificationDtlVo);*/
                }

                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());

                financeVerificationDocVo.setFinanceVerificationDtlVoList(financeVerificationDtlVoList);

                try {
                    // FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV(financeVerificationDocVo);
                    // FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV2(financeVerificationDocVo);
                    FinanceVerificationResultDocVo financeVerificationResultDocVo = this.financeService.verificationV3(financeVerificationDocVo);
                    if (financeVerificationResultDocVo.getErrorCode() != 0) {
                        orderLog.setLogMsg("订单核销--" + financeVerificationResultDocVo.getErrorMessage());
                        orderInfo.setFinanceTrue(0L);
                        orderInfo.setExpStatus(OrderInfo.EExpStatus.FINANCE_EXP.val());
                        orderInfo.setExpRemark("订单核销--" + financeVerificationResultDocVo.getErrorMessage());
                    } else {
                        orderLog.setLogMsg("订单核销成功");
                        orderInfo.setFinanceTrue(1L);
                        orderInfo.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
                        //修改收款单状态
                        List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>()
                                .eq("ORDER_ID", orderInfo.getId())
                                .ne("REC_METHOD", RecDoc.ERecMethod.REFUND_GOODS.val())
                                .ne("REC_METHOD", RecDoc.ERecMethod.SHORT.val()));
                        if (null != recDocList) {
                            for (RecDoc recDocDB : recDocList) {
                                recDocDB.setFinanceTrue(1L);
                                recDocDB.setErpFinanceRemark("核销成功");
                                recDocDB.setFinanceTime(LocalDateTime.now());
                                recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                                //recDocDB.setRecType(RecDoc.ERecType.ON_LINE.val());
                                this.recDocMapper.updateById(recDocDB);
                            }
                        }
                    }
                    this.orderInfoMapper.updateById(orderInfo);

                    orderLog.setOrderId(orderInfo.getId());
                    orderLog.setOrderStatus(orderInfo.getOrderState());
                    orderLog.setDoneTime(LocalDateTime.now());
                    orderLog.setToErpNum("v1." + num);
                    this.orderLogMapper.insert(orderLog);

                } catch (RuntimeException e) {
                    log.error("orderService.autoFinanceOrder error ", e);
                    orderInfo.setExpRemark(e.getMessage());
                    orderInfo.setExpStatus(OrderInfo.EExpStatus.CATCH_EXP.val());
                    orderInfo.setIsTrue("0");
                    this.orderInfoMapper.updateById(orderInfo);
                    orderLog.setLogMsg(e.getMessage());
                    orderLog.setOrderStatus(OrderLog.EOrderStatus.CATCH_EXP.val());
                    orderLog.setOrderId(orderInfo.getId());
                    orderLog.setDoneTime(LocalDateTime.now());
                    orderLog.setToErpNum("v1");
                    this.orderLogMapper.insert(orderLog);
                }

            }
        }

    }

    @Transactional
    @Override
    public void autoReturnOrder() {
        List<OrderInfoVo> orderInfoList = this.orderInfoMapper.autoReturnOrder();
        if (orderInfoList.size() > 0) {
            for (OrderInfoVo orderInfoVo : orderInfoList) {
                if (orderInfoVo.getActuallyMoney() >= orderInfoVo.getReturnAmount()) {
                    String num = "TK_" + System.currentTimeMillis();
                    try {

                        PayOrderVo payOrder = this.payOrderService.refund(orderInfoVo.getPayOrderNo(), num, Math.toIntExact(orderInfoVo.getReturnAmount() * 100));
                        Double amount = orderInfoVo.getRefundAmount() + payOrder.getRefundFee() / 100;
                        orderInfoVo.setRefundAmount(amount);
                        //orderInfoVo.setOrderState(OrderInfo.EOrderState.SUCCESS_REFUND.val());
                        orderInfoVo.setRefundFlowNo(payOrder.getRefundFlowNo());
                        this.orderInfoMapper.updateById(orderInfoVo);

                        if (!orderInfoVo.getRefundState().equals(OrderInfo.ERefundState.NO_REFUND.val())) {
                            RefundReturn refundReturnDB = this.refundReturnMapper.selectOne(new QueryWrapper<RefundReturn>().eq("order_id", orderInfoVo.getId()));
                            refundReturnDB.setRefundState(RefundReturn.ERefundState.DONE.val());
                            this.refundReturnMapper.updateById(refundReturnDB);
                        }

                        OrderLog orderLog = new OrderLog();
                        orderLog.setCreateTime(LocalDateTime.now());
                        orderLog.setLogMsg("短减/整单不出-->自动退款；退款单号：" + orderInfoVo.getPayOrderNo() + "_" + num + "退款流水号：" + payOrder.getRefundFlowNo() + ";退款金额：" + Long.valueOf(payOrder.getRefundFee()) * 100);
                        orderLog.setOrderId(orderInfoVo.getId());
                        orderLog.setOrderStatus(orderInfoVo.getOrderState());
                        orderLog.setDoneTime(LocalDateTime.now());
                        orderLog.setToErpNum("v1");
                        this.orderLogMapper.insert(orderLog);


                        //修改收款单状态
                        List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderInfoVo.getId()).in("REC_METHOD", RecDoc.ERecMethod.SHORT.val()));
                        for (RecDoc recDocDB : recDocList) {
                            recDocDB.setAbcMessage(payOrder.toString());
                            recDocDB.setPayRefundNo(payOrder.getRefundFlowNo());
                            recDocDB.setRefundState(RecDoc.ERefundState.REFUND_SUCCESS.val());
                            recDocDB.setRefundTime(LocalDateTime.now());
                            recDocDB.setPayAbcNo(orderInfoVo.getPayOrderNo() + "_" + num);
                            recDocDB.setRefundMoney(amount);
                            recDocDB.setPayTime(LocalDateTime.now());
                            recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                            recDocDB.setRecType(orderInfoVo.getPayMethod());
                            this.recDocMapper.updateById(recDocDB);
                        }
                    } catch (RuntimeException e) {
                        log.error("orderService.autoReturnOrder error ", e);
                        OrderLog orderLog = new OrderLog();
                        orderLog.setCreateTime(LocalDateTime.now());
                        orderLog.setLogMsg("短减/整单不出-->自动退款；异常：" + e.getMessage());
                        orderLog.setOrderId(orderInfoVo.getId());
                        orderLog.setOrderStatus(orderInfoVo.getOrderState());
                        orderLog.setDoneTime(LocalDateTime.now());
                        orderLog.setToErpNum("v1");
                        this.orderLogMapper.insert(orderLog);


                        //修改收款单状态
                        List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>().in("REC_METHOD", RecDoc.ERecMethod.SHORT.val()));
                        for (RecDoc recDocDB : recDocList) {
                            recDocDB.setAbcMessage(e.getMessage());
                            //recDocDB.setPayRefundNo(payOrder.getRefundFlowNo());
                            recDocDB.setRefundTime(LocalDateTime.now());
                            recDocDB.setPayAbcNo(orderInfoVo.getPayOrderNo() + "_" + num);
                            recDocDB.setRefundMoney(0L);
                            recDocDB.setRefundState(RecDoc.ERefundState.REFUND_FAIL.val());
                            //recDocDB.setPayTime(LocalDateTime.now());
                            //recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                            //recDocDB.setRecType(orderInfoVo.getPayMethod());
                            this.recDocMapper.updateById(recDocDB);
                        }
                    }


                }
            }
        }
    }

    @Override
    public List<RecDoc> getRecDocVoByOrderId(Long orderId) {
        AssertExt.hasId(orderId, "订单id为空");
        return this.recDocMapper.selectList(new QueryWrapper<RecDoc>().eq("ORDER_ID", orderId).orderByDesc("create_time"));
    }

    @Override
    public IPage<TVDates> getTVGoodsOff(Page page) {
        return this.orderInfoMapper.getTVGoodsOff(page);
    }

    @Override
    public IPage<TVDates> getTVToDaySales(Page page) {
        return this.orderInfoMapper.getTVToDaySales(page);
    }

    @Override
    public TVDates getTVToDaySalesTotal() {
        TVDates tvDates = this.orderInfoMapper.getTVToDaySalesTotal();
        if (null != tvDates) {
            tvDates.setGoodRemainingTotal(this.orderInfoMapper.getTVToGoodsTotal());
            tvDates.setGoodTotal(this.orderInfoMapper.getTVGoodsTotal());

        } else {
            tvDates = new TVDates();
            tvDates.setGoodRemainingTotal(this.orderInfoMapper.getTVToGoodsTotal());
            tvDates.setGoodTotal(this.orderInfoMapper.getTVGoodsTotal());
        }
        return tvDates;
    }

    @Override
    public IPage<TVDates> getTVGiftGoodsOff(Page page) {
        return this.orderInfoMapper.getTVGiftGoodsOff(page);
    }

    @Override
    public IPage<OrderInfoVo> getOrderInfoInterceptList(Page page, OrderInfoVo orderInfoVo) {
        AssertExt.notNull(orderInfoVo.getInterceptStatus(), "状态为空");
        return this.orderInfoMapper.getOrderInfoInterceptList(page, orderInfoVo);
    }

    @Override
    public void updateOrderInfoVoIntercept(Long id, String remark, String interceptStatus) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(interceptStatus, "状态为空");
        OrderInfo orderInfo = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfo, "无效订单id[%s]", id);
        if (orderInfo.getOrderState().equals(OrderInfo.EOrderState.TO_ERP.val())) {
            orderInfo.setOrderState(OrderInfo.EOrderState.CANCEL.val());
        }
        orderInfo.setInterceptRemark(remark);
        orderInfo.setInterceptStatus(interceptStatus);
        orderInfo.setInterceptTime(LocalDateTime.now());
        this.orderInfoMapper.updateById(orderInfo);
    }

    @Override
    public IPage<OrderInfoVo> getAdminErpOrderInfoInterceptList(Page page, OrderInfoVo orderInfoVo) {
        return this.orderInfoMapper.getAdminErpOrderInfoInterceptList(page, orderInfoVo);
    }

    @Override
    public Integer get24MemberOrderInfo(Long memberId) {
        return this.orderInfoMapper.get24MemberOrderInfo(memberId);
    }

    @Transactional
    @Override
    public void applyRefund(Long orderId, String remark) {
        AssertExt.hasId(orderId, "id为空");
        AssertExt.notNull(remark, "备注为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(orderId);
        AssertExt.notNull(orderInfoDB, "无效订单id[%s]", orderId);

        AssertExt.isFalse(orderInfoDB.getPayMethod().equals(OrderInfo.EPayMethod.ON_LINE.val()), "只有在线支付才能申请退款");

        AssertExt.isTrue(orderInfoDB.getCreateTime().plusDays(10L).isAfter(LocalDateTime.now()), "订单已超过可申请退款/退货时间");

        AssertExt.isTrue(orderInfoDB.getRefundState().equals(OrderInfo.ERefundState.NO_REFUND.val()), "订单已申请退货/退款");

        RefundReturn refundReturnVo = new RefundReturn();
        refundReturnVo.setRefundState(RefundReturn.ERefundState.PENDING.val());
        if (orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.TO_ERP.val())) {
            refundReturnVo.setRefundState(RefundReturn.ERefundState.DEAL_WITH.val());
        }

        refundReturnVo.setApplyType(RefundReturn.EApplyType.REFUND.val());
        refundReturnVo.setOrderNo(orderInfoDB.getOrderNo());
        refundReturnVo.setErpCustomerId(orderInfoDB.getErpCustomerId());
        refundReturnVo.setApplyNo(this.refundReturnWorker.genNo());
        refundReturnVo.setMemberName(orderInfoDB.getMemberName());

        refundReturnVo.setCreateTime(LocalDateTime.now());
        this.refundReturnMapper.insert(refundReturnVo);

        orderInfoDB.setOrderState(OrderInfo.EOrderState.APPLY_REFUND.val());
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("用户申请退款");


        List<OrderGoods> orderGoodsList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderInfoDB.getId()));
        RecDoc recDoc = new RecDoc();
        recDoc.setEntryId(1L);
        recDoc.setMemberId(orderInfoDB.getMemberId());
        recDoc.setOrderId(orderInfoDB.getId());
        recDoc.setStatus(RecDoc.EStatus.DONE_PAY.val());
        recDoc.setRecMethod(RecDoc.ERecMethod.REFUND.val());
        recDoc.setRecType(orderInfoDB.getPayMethod());
        recDoc.setTotal(orderInfoDB.getGoodsAmount());
        recDoc.setDtlLines(orderGoodsList.size());
        recDoc.setPremoney(orderInfoDB.getGoodsAmount());
        recDoc.setRemark(orderInfoDB.getRemark());
        recDoc.setCreateTime(LocalDateTime.now());
        recDoc.setRefundState(RecDoc.ERefundState.NO_REFUND.val());
        recDoc.setRecDocNo(this.recDocReturnWorker.genNo());
        this.recDocMapper.insert(recDoc);

        List<OrderGoods> orderGoodsVoList = this.orderGoodsMapper.selectList(new QueryWrapper<OrderGoods>().eq("order_id", orderId));
        orderGoodsVoList.forEach(orderGoodsVo -> {
            RefundDetail refundDetail = new RefundDetail();
            refundDetail.setGoodsDtlId(orderGoodsVo.getGoodsDtlId());
            refundDetail.setGoodsId(orderGoodsVo.getGoodsId());
            refundDetail.setOrderGoodsId(orderGoodsVo.getId());
            refundDetail.setGoodsName(orderGoodsVo.getGoodsName());
            refundDetail.setGodosImage(orderGoodsVo.getGoodsImage());
            refundDetail.setGoodsNum(orderGoodsVo.getGoodsNum());
            refundDetail.setLotNo(orderGoodsVo.getErpLotNo());
            refundDetail.setPayAmount(orderGoodsVo.getGoodsPayPrice());
            refundDetail.setRefundAmount(orderGoodsVo.getAmountPay());
            refundDetail.setPdAmount(orderGoodsVo.getGoodsPayPrice());
            refundDetail.setRefundState(refundReturnVo.getRefundState());
            refundDetail.setRefundId(refundReturnVo.getId());
            refundDetail.setOrderId(orderInfoDB.getId());
            //refundDetail.setGoodsSource(orderGoodsVo.getGoodsSource());
            refundDetail.setErpStorageId(orderGoodsVo.getErpStorageId());
            //refundDetail.setSrcErpOrderDtlId(orderGoodsVo.getSrcErpOrderDtlId());
            //refundDetail.setSrcErpOrderId(orderGoodsVo.getSrcErpOrderId());
            refundDetail.setCreateTime(LocalDateTime.now());
            this.refundDetailMapper.insert(refundDetail);

            RecDtl recDtl = new RecDtl();
            recDtl.setGoodsId(orderGoodsVo.getErpGoodsId());
            recDtl.setGoodsQty(orderGoodsVo.getGoodsNum());
            recDtl.setUntPrice(orderGoodsVo.getGoodsPayPrice());
            recDtl.setTotalLine(orderGoodsVo.getAmountPay());
            recDtl.setErpLeastsaleqty(orderGoodsVo.getErpLeastsaleqty());
            recDtl.setSaRecId(recDoc.getId());
            recDtl.setCreateTime(LocalDateTime.now());
            this.recDtlMapper.insert(recDtl);
        });

        if (orderInfoDB.getOrderState().equals(OrderInfo.EOrderState.TO_ERP.val())) {
            String num = "TK" + System.currentTimeMillis();
            PayOrderVo payOrderVo = this.payOrderService.refund(orderInfoDB.getPayOrderNo(), num, (int) (orderInfoDB.getActuallyMoney() * 100));
            orderInfoDB.setOrderState(OrderInfo.EOrderState.SUCCESS_REFUND.val());
            orderInfoDB.setRefundAmount((double) payOrderVo.getRefundFee() / 100);
            orderInfoDB.setRefundFlowNo(payOrderVo.getRefundFlowNo());
            orderLog.setLogMsg("用户申请退款--退款单号：" + orderInfoDB.getPayOrderNo() + "_" + num + "退款流水号：" + payOrderVo.getRefundFlowNo());

            recDoc.setPayRefundNo(orderInfoDB.getPayOrderNo() + "_" + num);
            recDoc.setRefundTime(LocalDateTime.now());
            recDoc.setRefundMoney((double) payOrderVo.getRefundFee() / 100);
            recDoc.setRefundState(RecDoc.ERefundState.REFUND_SUCCESS.val());
            this.recDocMapper.updateById(recDoc);

            refundReturnVo.setRefundState(RefundReturn.ERefundState.DONE.val());
            refundReturnVo.setFinnshedTime(LocalDateTime.now());
            this.refundReturnMapper.updateById(refundReturnVo);
        }

        orderInfoDB.setRefundState(OrderInfo.ERefundState.ALL_REFUND.val());
        this.orderInfoMapper.updateById(orderInfoDB);


        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        orderLog.setDoneTime(LocalDateTime.now());
        this.orderLogMapper.insert(orderLog);
    }

    @Transactional
    @Override
    public void addManuallyIntercept(ManuallyIntercept manuallyIntercept) {
        AssertExt.notNull(manuallyIntercept.getB2bOrderNo(), "b2b订单号为空");
        AssertExt.notNull(manuallyIntercept.getErpOrderNo(), "Erp订单号为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectOne(new QueryWrapper<OrderInfo>().eq("order_no", manuallyIntercept.getB2bOrderNo()));
        AssertExt.notNull(orderInfoDB, "无效订单号[%s]", manuallyIntercept.getB2bOrderNo());

        String[] erpOrder = manuallyIntercept.getErpOrderNo().split(",");
        for (String str : erpOrder) {
            manuallyIntercept.setErpOrderNo(str);
            manuallyIntercept.setStatus(ManuallyIntercept.EStatus.TO_INTERCEPT.val());
            manuallyIntercept.setCreateTime(LocalDateTime.now());
            manuallyIntercept.setDealWithTime(LocalDateTime.now());
            this.manuallyInterceptMapper.insert(manuallyIntercept);
        }
        orderInfoDB.setExpStatus(OrderInfo.EExpStatus.INTERCEPT_EXP.val());
        orderInfoDB.setInterceptStatus(OrderInfo.EInterceptStatus.TO_INTERCEPT.val());
        orderInfoDB.setInterceptTime(LocalDateTime.now());
        this.orderInfoMapper.updateById(orderInfoDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("手工拦截订单");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Override
    public IPage<ManuallyIntercept> getManuallyInterceptList(Page page, ManuallyIntercept manuallyIntercept) {
        return this.orderInfoMapper.getManuallyInterceptList(page, manuallyIntercept);
    }

    @Transactional
    @Override
    public void updateManuallyIntercept(ManuallyIntercept manuallyIntercept) {
        AssertExt.hasId(manuallyIntercept.getId(), "id为空");
        AssertExt.notNull(manuallyIntercept.getStatus(), "状态为空");
        manuallyIntercept.setDealWithTime(LocalDateTime.now());
        this.manuallyInterceptMapper.updateById(manuallyIntercept);

        OrderInfo orderInfoDB = this.orderInfoMapper.selectOne(new QueryWrapper<OrderInfo>().eq("order_no", manuallyIntercept.getB2bOrderNo()));
        AssertExt.notNull(orderInfoDB, "无效订单号[%s]", manuallyIntercept.getB2bOrderNo());

        orderInfoDB.setExpStatus(OrderInfo.EExpStatus.INTERCEPT_EXP.val());
        orderInfoDB.setInterceptStatus(manuallyIntercept.getStatus());
        orderInfoDB.setInterceptTime(LocalDateTime.now());
        this.orderInfoMapper.updateById(orderInfoDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("手工拦截订单");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Transactional
    @Override
    public void reFinanceOrder(Long id, Long financeTrue, String remark) {
        AssertExt.hasId(id, "id为空");
        AssertExt.hasId(financeTrue, "是否核销为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfoDB, "无效订单id[%s]", id);

        if (financeTrue == 1) {
            orderInfoDB.setFinanceTrue(financeTrue);
        } else {
            orderInfoDB.setFinanceTrue(0L);
        }
        orderInfoDB.setExpStatus(OrderInfo.EExpStatus.NORMAL.val());
        this.orderInfoMapper.updateById(orderInfoDB);

        //修改收款单状态
        List<RecDoc> recDocList = this.recDocMapper.selectList(new QueryWrapper<RecDoc>()
                .eq("ORDER_ID", orderInfoDB.getId()));
              /*  .ne("REC_METHOD", RecDoc.ERecMethod.REFUND_GOODS.val())
                .ne("REC_METHOD", RecDoc.ERecMethod.SHORT.val()));*/
        if (null != recDocList) {
            for (RecDoc recDocDB : recDocList) {
                recDocDB.setFinanceTrue(orderInfoDB.getFinanceTrue());
                recDocDB.setRemark(remark);
                recDocDB.setErpFinanceRemark("手动变更核销状态");
                recDocDB.setFinanceTime(LocalDateTime.now());
                recDocDB.setStatus(RecDoc.EStatus.DONE_PAY.val());
                //recDocDB.setRecType(RecDoc.ERecType.ON_LINE.val());
                this.recDocMapper.updateById(recDocDB);
            }
        }

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("订单手动核销,备注：" + remark);
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Transactional
    @Override
    public void sureSenTTime(Long id) {
        AssertExt.hasId(id, "id为空");
        OrderInfo orderInfoDB = this.orderInfoMapper.selectById(id);
        AssertExt.notNull(orderInfoDB, "无效订单id[%s]", id);

        orderInfoDB.setSentTime(LocalDateTime.now());
        this.orderInfoMapper.updateById(orderInfoDB);

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateTime(LocalDateTime.now());
        orderLog.setLogMsg("订单确认送货");
        orderLog.setOrderId(orderInfoDB.getId());
        orderLog.setOrderStatus(orderInfoDB.getOrderState());
        orderLog.setToErpNum("v1");
        this.orderLogMapper.insert(orderLog);
    }

    @Override
    public OrderInfoVo getOrderInfoDate() {
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        orderInfoVo.setExpOrder(this.orderInfoMapper.selectCount(new QueryWrapper<OrderInfo>().ne("EXP_STATUS", OrderInfo.EExpStatus.NORMAL.val())));
        orderInfoVo.setCancelOrder(this.orderInfoMapper.selectCount(new QueryWrapper<OrderInfo>().eq("ORDER_STATE", OrderInfo.EOrderState.CANCEL.val())));
        orderInfoVo.setInterceptOrder(this.orderInfoMapper.selectCount(new QueryWrapper<OrderInfo>().eq("INTERCEPT_STATUS", OrderInfo.EInterceptStatus.TO_INTERCEPT.val())));
        orderInfoVo.setRefundOrder(this.orderInfoMapper.selectCount(new QueryWrapper<OrderInfo>().ne("REFUND_STATE", OrderInfo.ERefundState.NO_REFUND.val())));
        orderInfoVo.setToDeliveryOrder(this.orderInfoMapper.selectCount(new QueryWrapper<OrderInfo>().eq("ORDER_STATE", OrderInfo.EOrderState.TO_DELIVERY.val())));
        return orderInfoVo;
    }

    @Override
    public String getOrderInfoFPInfo(Long orderId) {
        AssertExt.hasId(orderId, "订单id为空");
        OrderInfo orderInfo = this.orderInfoMapper.selectById(orderId);

        AssertExt.notNull(orderInfo, "无效id[%s]", orderId);
        try {
            FapiaoOrderVo rs = this.fapiaoService.getKpInfo(orderId);
            String erpOrderIds = StringUtils.join(rs.getErpOrderIdList().toArray(), ",");

            if (orderInfo.getFpStatus().equals(OrderInfo.EFpStatus.OFF.val())) {
                orderInfo.setFpStatus(OrderInfo.EFpStatus.ON.val());
                this.orderInfoMapper.updateById(orderInfo);
                OrderLog orderLog = new OrderLog();
                orderLog.setCreateTime(LocalDateTime.now());
                orderLog.setLogMsg("订单开发票");
                orderLog.setOrderId(orderInfo.getId());
                orderLog.setOrderStatus(orderInfo.getOrderState());
                orderLog.setDoneTime(LocalDateTime.now());
                orderLog.setToErpNum("v1");
                this.orderLogMapper.insert(orderLog);
            }

            return this.invoiceService.ztissue(erpOrderIds, rs.getTaxNo(), rs.getUsername(), rs.getPassword());
        } catch (RuntimeException e) {
            orderInfo.setFpStatus(OrderInfo.EFpStatus.OFF.val());
            this.orderInfoMapper.updateById(orderInfo);
            OrderLog orderLog = new OrderLog();
            orderLog.setCreateTime(LocalDateTime.now());
            orderLog.setLogMsg("订单开发票失败；错误信息：" + e.getMessage());
            orderLog.setOrderId(orderInfo.getId());
            orderLog.setOrderStatus(orderInfo.getOrderState());
            orderLog.setDoneTime(LocalDateTime.now());
            orderLog.setToErpNum("v1");
            this.orderLogMapper.insert(orderLog);
            return e.getMessage();
        }

    }

    @Override
    public void toDayExpAmountOrder() {
        Integer orderGoodsAmount = this.orderInfoMapper.getOrderGoodsAmountToday();
        if (orderGoodsAmount > 0) {
            this.loginService.sendSmsCode("17665073990", "金额异常", "172.16.20.195");
            this.loginService.sendSmsCode("13630035367", "金额异常", "172.16.20.195");
            this.loginService.sendSmsCode("13702832295", "金额异常", "172.16.20.195");
        }
        Integer orderInfoAmount = this.orderInfoMapper.getOrderInfoAmountToday();
        if (orderInfoAmount > 0) {
            this.loginService.sendSmsCode("17665073990", "金额异常", "172.16.20.195");
            this.loginService.sendSmsCode("13630035367", "金额异常", "172.16.20.195");
            this.loginService.sendSmsCode("13702832295", "金额异常", "172.16.20.195");
        }
    }

    @Override
    public FapiaoAddressVo getErpKpAddressInfo(Long orderId) {
        AssertExt.hasId(orderId, "订单id为空");
        return this.fapiaoService.getKpAddressInfo(orderId);
    }

    @Override
    public IPage<OrderGoodsVo> getOrderGoodsBy9(Page page, OrderGoodsVo orderGoods) {
        return this.orderGoodsMapper.getOrderGoodsBy9(page, orderGoods);
    }

    @Override
    public IPage<OrderInfoVo> getOrderInfoVoByTranslinename(Page page, OrderInfoVo orderInfoVo) {
        IPage<OrderInfoVo> orderInfoVoIPage = this.orderInfoMapper.getOrderInfoVoByTranslinename(page, orderInfoVo);
       /* orderInfoVoIPage.getRecords().forEach(orderInfoVo1 -> {
            List<OrderInfoVo> orderInfoVoList=this.orderInfoMapper.getMyOrderInfoAll(orderInfoVo1.getMemberId(), OrderInfo.EOrderState.TO_DELIVERY.val());
            orderInfoVoList.forEach(orderInfoVo2 -> {
               // orderInfoVo2.setOrderGoodsDeliveryList(this.orderGoodsMapper.orderGoodsDeliveryList(orderInfoVo2.getId()));
                orderInfoVo2.setOrderGoodsList(this.orderGoodsMapper.getOrderGoodsVoInfo(orderInfoVo2.getId()));
                orderInfoVo2.setErpOrderInfoList(this.orderInfoMapper.getErpOrderId(orderInfoVo2.getOrderNo()));
            });
            orderInfoVo1.setOrderInfoVoList(orderInfoVoList);
        });*/
        return orderInfoVoIPage;
    }

    @Override
    public List<OrderInfoVo> getOrderInfoVoByTranslinenameList(Long memberId) {
        AssertExt.hasId(memberId, "客户id为空");
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("erp_user_id", memberId));
        List<OrderInfoVo> orderInfoVoList = new ArrayList<>();
        if (null != member) {
            orderInfoVoList = this.orderInfoMapper.getMyOrderInfoAll(member.getId(), OrderInfo.EOrderState.TO_DELIVERY.val());
            orderInfoVoList.forEach(orderInfoVo2 -> {
                // orderInfoVo2.setOrderGoodsDeliveryList(this.orderGoodsMapper.orderGoodsDeliveryList(orderInfoVo2.getId()));
                orderInfoVo2.setOrderGoodsList(this.orderGoodsMapper.getOrderGoodsVoInfo(orderInfoVo2.getId()));
                orderInfoVo2.setErpOrderInfoList(this.orderInfoMapper.getErpOrderId(orderInfoVo2.getOrderNo()));
            });
        }


        return orderInfoVoList;
    }

    @Override
    public List<ErpOrderInfo> getErpOrderInfoByMemberId(ErpOrderInfo erpOrderInfo) {
        AssertExt.hasId(erpOrderInfo.getErpUserId(), "客户id为空");
        if(ObjectUtil.isNotNull(erpOrderInfo.getTranposname())){
            erpOrderInfo.setTranslinenames(erpOrderInfo.getTranposname().split(","));
        }
        List<Long> longList = new ArrayList<>();
        List<Long> transDocBySalesId = null;
        longList.add(0L);
        if (ObjectUtil.isNotNull(erpOrderInfo.getTransDocId())) {
            CustomTransVo customTransVo = this.transControlService.get(erpOrderInfo.getErpUserId(), erpOrderInfo.getTransDocId());
            if (null != customTransVo) {
                if (customTransVo.getSalesIdList().size() > 0) {
                    longList = customTransVo.getSalesIdList();
                    transDocBySalesId=customTransVo.getSalesIdList();
                }else {
                    return new ArrayList<>();
                }

            }
        }else{
            /**
             * peiqy 修改，前台扫描的是客户id
             */
            CustomTransVo customTransVo = this.transControlService.getByCustomId(erpOrderInfo.getErpUserId());
            if (null != customTransVo) {
                if (customTransVo.getSalesIdList().size() > 0) {
                    longList = customTransVo.getSalesIdList();
                }else {
                    return new ArrayList<>();
                }
            }

        }
        erpOrderInfo.setTransDocBySalesId(transDocBySalesId);
        erpOrderInfo.setLongList(longList);
        return this.orderInfoMapper.getErpOrderInfoByMemberId(erpOrderInfo);
    }

}
