package com.zsyc.zt.inca.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 财务核销
 * @author  peiqy
 */
@Data
public class FinanceVerificationDocVo implements Serializable {

    private static final long serialVersionUID = 4951336634117202760L;


    /**
     * 核销类型  1，线上支付核销  2  现结核销  3 月结核销   4，退单核销
     */
    private Integer   verificationType;
    /**
     * 订单ID   如果是线下订单，传收款总单ID
     */
    private Long orderId;

    /**
     * 订单编号   如果是线下订单，传收款总单No
     */
    private String orderNo;


    /**
     * 源订单ID 退货单是原单号，非退货单和orderId一样      如果是线下订单，传收款总单ID
     */
    private Long srcOrderId;

    /**
     * 源订单编号  源订单ID 退货单是原单号，非退货单和orderNo一样   如果是线下订单，传收款总单No
     */
    private String srcOrderNo;



    /**
     * 单据日期  如果是线下订单，传线下收款时间
     */
    private LocalDateTime createDate;


    /**
     *  付款日期
     */
    private LocalDateTime payDate;


    /**
     *  核销日期  ，传核销调用时间
     */
    private LocalDateTime verificationDate;


    /**
     * 收款单号
     */
    private  String recDocNo;


    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    private String payFlowNo;


    /**
     * 传默认的   44147,  现金支付 传 0
     */
    private  Long bankId;

    /**
     * 支付方式   线上支付 对应 erp  线上支付   CASH  ON_LINE
     */
    private  String payType;

    /**
     * 客户ID
     */
    private Long customId;


    /**
     * 订单类型  1  普通单  2是补货单  3 退货单
     */
    private Integer orderType;

    /**
     * 结算方式
     */
    //private Integer settleMethod;
    private String settleMethod;

    /**
     * 制单人ID  1，在线支付，传1   2 到付情况传递，传app收款人erpid   3 月结，传1
     */
    private Long inputManId;

    /**
     * 确认人ID  ,自动的话，就传1，如果是手工点核销，就是，核销的操作人的erp用户ID,如果是走线下，就是核单人的后台对应  ID
     */
    private Long confirmManId;

    /**
     * 独立单元ID
     */
    private Integer entryId;


    /**
     * 订单总金额
     */
    private Double amountTotal;

    /**
     * 订单优惠金额
     */
    private Double amountDiscount;

    /**
     * 订单应付金额
     */
    private double amountPay;


    /**
     * 核销金额
     */
    private double amountVerification;


    /**
     * 差异金额
     */
    private double amountDiscrepancy;


    /**
     * 备注
     */
    private String remark;


    private String version;


    private List<FinanceVerificationDtlVo> financeVerificationDtlVoList;


}
