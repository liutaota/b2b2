package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *  财务核销
 * @author peiqy
 */
@Data
public class FinanceVerificationDtlVo implements Serializable {

    private static final long serialVersionUID = -3703845614247283819L;



    /**
     * 退单订单编号细单   如果是线下订单，传收款细单ID
     */
    private String orderDtlId;

    /**
     * 退单订单编号   如果是线下订单，传收款总单ID
     */
    private String orderId;

    /**
     * 源订单ID   如果是线下订单，传收款总单ID
     */
    private String srcOrderId;

    /**
     * 源订单细单ID    如果是线下订单，传收款细单ID
     */
    private String srcOrderDtlId;


    /**
     * 对应ERP源销售总单   如果是线下订单，传ERP总单ID
     */
    private Long srcErpOrderId;


    /**
     * 对应ERP源销售细单   如果是线下订单，0
     */
    private Long srcErpOrderDtlId;


    /**
     * --1是销售类，2是配送类,3是销配退类
     */
    private Integer sourceType;


    /**
     * 保管账ID   如果是线下订单，0
     */
    private Integer storageId;


    /**
     * 获评ID   如果是线下订单，0
     */
    private Long goodsId;


    /**
     * 核销数量   如果是线下订单，0
     */
    private Double num;

    /**
     * 核销金额   如果是线下订单，传总单金额
     */
    private Double amount;

    /**
     * 是否赠品
     */
    private Boolean isGift;



}
