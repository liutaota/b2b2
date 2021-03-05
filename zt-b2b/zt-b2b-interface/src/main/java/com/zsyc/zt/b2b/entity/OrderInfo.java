package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单信息
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ORDER_INFO")
@ApiModel(value = "OrderInfo对象", description = "订单信息")
@KeySequence(value = "SEQ_ORDER_INFO")
public class OrderInfo extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    @TableField("ORDER_TYPE")
    private String orderType;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 总额
     */
    @ApiModelProperty(value = "总额")
    @TableField("ORDER_AMOUNT")
    private double orderAmount;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    @TableField("GOODS_AMOUNT")
    private double goodsAmount;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    @TableField("ACTUALLY_MONEY")
    private double actuallyMoney;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @TableField("PAY_TIME")
    private LocalDateTime payTime;

    /**
     * 支付方式
     * @see EPayMethod
     */
    @ApiModelProperty(value = "支付方式 ")
    @TableField("PAY_METHOD")
    private String payMethod;

    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    @TableField("PAY_FLOW_NO")
    private String payFlowNo;

    /**
     * 退款流水号
     */
    @ApiModelProperty(value = "退款流水号")
    @TableField("REFUND_FLOW_NO")
    private String refundFlowNo;

    /**
     * 物流号
     */
    @ApiModelProperty(value = "物流号")
    @TableField("LOGISTICS")
    private String logistics;

    /**
     * 地址详情
     */
    @ApiModelProperty(value = "地址详情")
    @TableField("ADDRESS_DETAIL")
    private String addressDetail;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @TableField("REFUND_AMOUNT")
    private double refundAmount;

    /**
     * 退货物流号
     */
    @ApiModelProperty(value = "退货物流号")
    @TableField("RETURN_LOGISTICS")
    private String returnLogistics;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    @TableField("MEMBER_NAME")
    private String memberName;

    /**
     * 客户电子邮箱
     */
    @ApiModelProperty(value = "客户电子邮箱")
    @TableField("MEMBER_EMAIL")
    private String memberEmail;

    /**
     * 客户手机
     */
    @ApiModelProperty(value = "客户手机")
    @TableField("MEMBER_PHONE")
    private String memberPhone;

    /**
     * 订单完成时间
     */
    @ApiModelProperty(value = "订单完成时间")
    @TableField("FINNSHED_TIME")
    private LocalDateTime finnshedTime;

    /**
     * 评价状态 0未评价，1已评价，2已过期未评价
     */
    @ApiModelProperty(value = "评价状态 0未评价，1已评价，2已过期未评价")
    @TableField("EVALUATION_STATE")
    private String evaluationState;

    /**
     * 追加评价状态 0未评价，1已评价，2已过期未评价
     */
    @ApiModelProperty(value = "追加评价状态 0未评价，1已评价，2已过期未评价")
    @TableField("EVALUATION_AGAIN_STATE")
    private String evaluationAgainState;

    /**
     * 订单状态：0(已取消)10(默认):未付款;20:已付款;30:已发货;40:已收货;
     */
    @ApiModelProperty(value = "订单状态：0(已取消)10(默认):未付款;20:已付款;30:已发货;40:已收货;")
    @TableField("ORDER_STATE")
    private String orderState;

    /**
     * 退款状态:0是无退款,1是部分退款,2是全部退款
     */
    @ApiModelProperty(value = "退款状态:0是无退款,1是部分退款,2是全部退款")
    @TableField("REFUND_STATE")
    private String refundState;

    /**
     * 冗余，锁定状态:0是正常,大于0是锁定,默认是0
     */
    @ApiModelProperty(value = "冗余，锁定状态:0是正常,大于0是锁定,默认是0")
    @TableField("LOCK_STATE")
    private String lockState;

    /**
     * 删除状态0未删除1放入回收站2彻底删除
     */
    @ApiModelProperty(value = "删除状态0未删除1放入回收站2彻底删除")
    @TableField("DELETE_STATE")
    private Integer deleteState;

    /**
     * 延迟时间,默认为0
     */
    @ApiModelProperty(value = "延迟时间,默认为0")
    @TableField("DELAY_TIME")
    private LocalDateTime delayTime;

    /**
     * 1WEB2mobile  3 后台下单  4 接口下单
     */
    @ApiModelProperty(value = "1WEB2mobile")
    @TableField("ORDER_FROM")
    private Integer orderFrom;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    @TableField("RPT_AMOUNT")
    private double rptAmount;

    /**
     * 同步ERP销售主表id
     */
    @ApiModelProperty(value = "同步ERP销售主表id")
    @TableField("ERP_ORDER_ID")
    private Long erpOrderId;

    /**
     * ERP结算方式
     */
    @ApiModelProperty(value = "ERP结算方式")
    @TableField("SETTLE_TYPE_ID")
    private Long settleTypeId;

    /**
     * 发票类型
     */
    @ApiModelProperty(value = "发票类型")
    @TableField("INV_TYPE")
    private String invType;

    /**
     * 发票要求
     */
    @ApiModelProperty(value = "发票要求")
    @TableField("INV_DEMAND")
    private String invDemand;

    /**
     * 欠款金额
     */
    @ApiModelProperty(value = "欠款金额")
    @TableField("REC_MONEY")
    private double recMoney;

    /**
     * 可用信用天数
     */
    @ApiModelProperty(value = "可用信用天数")
    @TableField("CREDIT_DAYS")
    private Long creditDays;

    /**
     * 线序
     */
    @ApiModelProperty(value = "线序")
    @TableField("ZX_PH_ORDER")
    private Long zxPhOrder;

    /**
     * 运输路线
     */
    @ApiModelProperty(value = "运输路线")
    @TableField("TRANSLINENAME")
    private String translinename;

    /**
     * erp客户id
     */
    @ApiModelProperty(value = "erp客户id")
    @TableField("ERP_CUSTOMER_ID")
    private Long erpCustomerId;

    @ApiModelProperty(value = "送货地址id")
    @TableField("TARGET_POS_ID")
    private Long targetPosId;

    /**
     * 异常状态
     */
    @ApiModelProperty(value = "异常状态")
    @TableField("EXP_STATUS")
    private String expStatus;

    /**
     * 异常备注
     */
    @ApiModelProperty(value = "异常备注")
    @TableField("EXP_REMARK")
    private String expRemark;

    /**
     * 0否，1是
     */
    @ApiModelProperty(value = "是否全场折扣")
    @TableField("ALL_DISCOUNT")
    private Integer allDiscount;

    /**
     * 优惠券id
     */
    @ApiModelProperty(value = "优惠券id")
    @TableField("COUPON_ID")
    private Long couponId;


    /**
     * 多张优惠券
     */
    @ApiModelProperty(value = "多张优惠券")
    @TableField("COUPON_IDS")
    private String couponIds;


    /**
     * 使用积分
     */
    @ApiModelProperty(value = "使用积分")
    @TableField("USE_SCORE")
    private Long useScore;

    /**
     * 赠送积分
     */
    @ApiModelProperty(value = "赠送积分")
    @TableField("SEND_SCORE")
    private Long sendScore;

    @ApiModelProperty(value = "处理备注")
    @TableField("USER_REMARK")
    private String userRemark;

    @ApiModelProperty(value = "是否已处理")
    @TableField("IS_TRUE")
    private String isTrue;

    @ApiModelProperty(value = "支付订单号")
    @TableField("PAY_ORDER_NO")
    private String payOrderNo;

    @ApiModelProperty(value = "erp短减金额")
    @TableField("ERP_AMOUNT")
    private double erpAmount;

    @ApiModelProperty(value = "ERP发货单金额")
    @TableField("ERP_ORDER_AMOUNT")
    private double erpOrderAmount;

    /**
     * 1,是，0否
     */
    @ApiModelProperty(value = "核销是否通过")
    @TableField("FINANCE_TRUE")
    private Long financeTrue;

    @ApiModelProperty(value = "后台下单操作人")
    @TableField("ADMIN_USER_ID")
    private Long adminUserId;

    @ApiModelProperty(value = "拦截状态")
    @TableField("INTERCEPT_STATUS")
    private String interceptStatus;

    @ApiModelProperty(value = "拦截时间")
    @TableField("INTERCEPT_TIME")
    private LocalDateTime interceptTime;

    @ApiModelProperty(value = "拦截备注")
    @TableField("INTERCEPT_REMARK")
    private String interceptRemark;

    @ApiModelProperty(value = "送货时间")
    @TableField("SENT_TIME")
    private LocalDateTime sentTime;

    @ApiModelProperty(value = "收款人id")
    @TableField("SENT_USER_ID")
    private Long sentUserId;

    @ApiModelProperty(value = "整单不出生成人")
    @TableField("NO_ORDER_USER_ID")
    private Long noOrderUserId;

    @ApiModelProperty(value = "生成整单不出备注")
    @TableField("NO_ORDER_REMARK")
    private String noOrderRemark;


    @ApiModelProperty(value = "开发票状态")
    @TableField("FP_STATUS")
    private String fpStatus;

    @ApiModelProperty(value = "支付交易码")
    @TableField("PAY_TYPE")
    private String payType;

    @ApiModelProperty(value = "支付交易码说明")
    @TableField("PAY_TYPE_DOC")
    private String payTypeDoc;

    @ApiModelProperty(value = "随货备注")
    @TableField("GOODS_REMARK")
    private String  goodsRemark;

    @ApiModelProperty(value = "门店订单id,对应store_id")
    @TableField("SRC_ORDER_ID")
    private Long srcOrderId;

    @ApiModelProperty(value = "门店订单号")
    @TableField("SRC_ORDER_NO")
    private String srcOrderNo;

    @ApiModelProperty(value = "门店订单时间")
    @TableField("SRC_ORDER_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime srcOrderTime;



    @ApiModelProperty(value = "门店ID")
    @TableField("STORE_ID")
    private Long storeId;

    /**
     * @see #fpStatus
     */
    public enum EFpStatus implements IEnum {
        ON("已开"),
        OFF("未开"),
        ;
        private String desc;

        EFpStatus(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }


    /**
     * @see #interceptStatus
     */
    public enum EInterceptStatus implements IEnum {
        NORMAL("正常"),
        TO_INTERCEPT("待拦截"),
        INTERCEPT_SUCCESS("拦截成功"),
        INTERCEPT_FAIL("拦截失败"),
        ;
        private String desc;

        EInterceptStatus(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * @see #expStatus
     */
    public enum EExpStatus implements IEnum {
        NORMAL("正常"),
        ORDER_EXP("erp异常"),
        NO_ORDER("整单不出"),
        SHORT("短减"),
        OUT_TIME("超时"),
        CATCH_EXP("未知异常"),
        DEAL_WITH("待处理"),
        INTERCEPT_EXP("拦截异常"),
        AMOUNT_EXP("金额异常"),
        FINANCE_EXP("核销异常"),
        ;
        private String desc;

        EExpStatus(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }


    /**
     * @see #orderType
     */
    public enum EOrderType implements IEnum {
        COMMON("普通订单"),
        CPFR("补货订单"),
        ;
        private String desc;

        EOrderType(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * @see #evaluationState
     */
    public enum EEvaluationState implements IEnum {
        UN_EVALUATION("未评价"),
        EVALUATION("已评价"),
        EVALUATION_time("已过期未评价"),
        ;
        private String desc;

        EEvaluationState(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * @see #evaluationAgainState
     */
    public enum EEvaluationAgainState implements IEnum {
        UN_EVALUATION("未评价"),
        EVALUATION("已评价"),
        EVALUATION_time("已过期未评价"),
        ;
        private String desc;

        EEvaluationAgainState(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * @see #orderState
     */
    public enum EOrderState implements IEnum {
        CANCEL("已取消"),
        UNPAID("未付款"),
        TO_ERP("待下发"),
        INTERCEPT("待拦截"),
        SEND_ERP("已下发"),
        TO_SEND("拣货中"),
        TO_DELIVERY("待收货"),
        TO_RECEIVED("已收货"),
        DONE("完成"),
        IN_REFUND("退款中"),
        APPLY_REFUND("申请退款"),
        SUCCESS_REFUND("退款成功"),
        ERP_TO_RECEIVED("erp已收货"),
        ;
        private String desc;

        EOrderState(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * @see #refundState
     */
    public enum ERefundState implements IEnum {
        NO_REFUND("无退款"),
        PART_REFUND("部分退款"),
        ALL_REFUND("全部退款"),
        ;
        private String desc;

        ERefundState(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * @see #lockState
     */
    public enum ELockState implements IEnum {
        NORMAL("正常"),
        LOCK("锁定"),
        ;
        private String desc;

        ELockState(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }


    /**
     * @see #payMethod
     */
    public enum EPayMethod implements IEnum {
        CASH("货到付款"),
        MONTH("月结"),
        ON_LINE("在线支付"),
        WX_LINE("微信支付"),
        ZFB_LINE("支付宝支付"),
        ABC_LINE("农行支付"),
        ;
        private String desc;

        EPayMethod(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }


    /**
     * @see #payTypeDoc
     */
    public enum EPayTypeDoc implements IEnum {
        EP063("普通类-网络渠道-直接支付银行端-对公户一代K"),
        EP064("普通类-网络渠道-直接支付银行端-对公户二代K"),
        EP065("其它类-网络渠道-跨行支付(第三方)对公"),
        EP066("其它类-移动渠道-跨行支付(第三方)对公"),
        EP067("普通类-网络渠道-授权支付-三方协议接口-借记卡K码"),
        EP068("普通类-移动渠道-直接支付银行端-借记卡通用K"),
        EP069("普通类-移动渠道-直接支付银行端-贷记卡通用K"),
        EP070("普通类-支付终端-直接支付-借记卡"),
        EP071("普通类-支付终端-直接支付-贷记卡"),
        EP072("普通类-移动渠道-直接支付银行端-借记卡软token"),
        EP073("非金类-网络渠道-快捷支付（银行端）-借记卡K宝/K令"),
        EP074("非金类-网络渠道-快捷支付（银行端）-借记卡K码"),
        EP075("非金类-移动渠道-快捷支付（银行端）-借记卡K宝/K令"),
        EP076("非金类-移动渠道-快捷支付（银行端）-借记卡K码"),
        EP077("非金类-快捷支付（商户端）-借记卡"),
        EP078("非金类-快捷支付网银-借记卡"),
        EP079("非金类-快捷支付掌银-借记卡"),
        EP080("非金类-快捷支付网站-借记卡"),
        EP081("非金类-快捷支付柜面-借记卡"),
        EP082("非金类-网络渠道-快捷支付（银行端）-贷记卡K宝/K令"),
        EP083("非金类-网络渠道-快捷支付（银行端）-贷记卡K码"),
        EP084("非金类-移动渠道-快捷支付（银行端）-贷记卡K宝/K令"),
        EP085("非金类-移动渠道-快捷支付（银行端）-贷记卡K码"),
        EP086("非金类-快捷支付（商户端）-贷记卡"),
        EP087("非金类-快捷支付网银-贷记卡"),
        EP088("非金类-快捷支付掌银-贷记卡"),
        EP089("非金类-快捷支付网站-贷记卡"),
        EP090("非金类-快捷支付柜面-贷记卡"),
        EP135("普通类-网络渠道-直接支付银行端-借记卡软token"),
        EP136("普通类-网络渠道-直接支付银行端-贷记卡软token"),
        EP137("普通类-移动渠道-直接支付银行端-贷记卡软token"),
        EP138("普通类-网络渠道-微信支付"),
        EP139("普通类-移动渠道-微信支付"),
        EP140("普通类-线下渠道-微信支付"),
        EP141("普通类-网络渠道-银联新无卡快捷-贷记卡"),
        EP142("普通类-移动渠道-银联新无卡快捷-借记卡"),
        EP143("普通类-移动渠道-银联新无卡快捷-贷记卡"),
        EP148("普通类-网络渠道-直接支付商户端-微信扫码支付"),
        EP149("普通类-网络渠道-直接支付银行端-微信一码多付"),
        EP150("普通类-移动渠道-微信APP支付"),
        EP151("普通类-移动渠道-微信公众号支付"),
        EP152("普通类-线下渠道-微信扫码支付"),
        EP153("普通类-线下渠道-微信刷卡支付"),
        EP154("普通类-线下渠道-微信一码多付（借记卡）"),
        EP155("普通类-网络渠道-直接支付商户端-支付宝扫码支付"),
        EP156("普通类-网络渠道-直接支付银行端-支付宝一码多付"),
        EP157("普通类-移动渠道-支付宝APP支付"),
        EP158("普通类-移动渠道-支付宝WAP支付"),
        EP159("普通类-线下渠道-支付宝扫码支付"),
        EP160("普通类-线下渠道-支付宝刷卡支付"),
        EP161("普通类-线下渠道-支付宝一码多付（借记卡）"),
        EP164("非金类-快捷支付-直接支付-借记卡"),
        EP165("非金类-快捷支付-直接支付-贷记卡"),
        EP169("普通类-移动渠道-微信钱包缴费"),
        EP170("普通类-移动渠道-百度缴费"),
        EP171("普通类-网络渠道-银联代收"),
        EP172("普通类-网络渠道-银联代付"),
        EP173("普通类-移动渠道-银联代收"),
        EP174("普通类-移动渠道-银联代付"),
        EP175("普通类-线下渠道-微信一码多付（贷记卡）"),
        EP176("普通类-线下渠道-支付宝一码多付（贷记卡）"),
        ;
        private String desc;

        EPayTypeDoc(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }
}
