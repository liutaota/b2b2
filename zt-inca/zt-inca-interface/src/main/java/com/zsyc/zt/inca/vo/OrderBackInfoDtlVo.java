package com.zsyc.zt.inca.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @author peiqy
 */
@Data
public class OrderBackInfoDtlVo implements Serializable {

    private static final long serialVersionUID = -3703845614247283819L;

    /**
     * 退单订单编号细单
     */
    private String orderDtlId;

    /**
     * 退单订单ID
     */
    private String orderId;

    /**
     * 退单订单编号
     */
    private String orderNo;

    /**
     * 源订单ID
     */
    private String srcOrderId;

    /**
     * 源订单ID
     */
    private String srcOrderNo;

    /**
     * 源订单细单ID
     */
    private String srcOrderDtlId;


    /**
     * 对应ERP源销售总单(配送单）
     */
    private Long srcErpOrderId;


    /**
     * 对应ERP源销售细单(配送单）
     */
    private Long srcErpOrderDtlId;


    /**
     * 保管账 表示出库的保管账
     */
    private Integer storageId;

    /**
     * 获评ID
     */
    private Long goodsId;


    /**
     * 获评ID 细单
     */
    private Long goodsDtlId;


    /**
     * 批号ID
     */
    private Long lotId;

    /**
     * 批号
     */
    private String lotNo;

    /**
     * 批次ID 目前在配送单上面无效，和配送单的批次号不一样
     */
    private Long  batchId ;



    /**
     * 数量
     */
    private Double num;
    /**
     * 单价
     */
    private Double price;

    /**
     * 折价
     */
    private Double priceDiscount;
    /**
     * 金额
     */
    private Double amount;

    /**
     *退货原因   与erp对应
     */
    private Integer reason;

    /**
     * 是否赠品
     */
    private Boolean isGift;

    /**
     * 商品 来源   3 普通商品  18 赠品
     */
    private Integer goodsSource;



}
