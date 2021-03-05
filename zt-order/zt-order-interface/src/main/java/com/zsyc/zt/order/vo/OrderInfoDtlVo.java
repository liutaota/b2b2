package com.zsyc.zt.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peiqy
 */
@Data
public class OrderInfoDtlVo implements Serializable {
    private static final long serialVersionUID = -3703845614247283819L;

    /**
     * 订单编号细单
     */
    private String orderDtlId;


    /**
     * 源订单ID
     */
    private String srcOrderId;

    /**
     * 源订单细单ID
     */
    private String srcOrderDtlId;


    /**
     * 对应ERP源销售总单
     */
    private Long srcErpOrderId;


    /**
     * 对应ERP源销售细单
     */
    private Long srcErpOrderDtlId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 获评ID
     */
    private Long goodsId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 数量
     */
    private Double num;

    /**
     * 单价-----平台单价
     */
    private Double price;


    /**
     * 折价-----
     */
    private Double priceDiscount;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 保管账ID
     */
    private Integer storageId;

    /**
     * 批号
     */
    private String lotNo;


    /**
     * 批号ID
     */
    private Long lotId;

    /**
     * 批次ID
     */
    private Long  batchId ;



    /**
     * 价格类型
     */
    private Integer priceId;



    /**
     * 1 默认   7  特价商品
     */
/*    private Integer goodsType;*/


    /**
     * 商品 来源   3 普通商品  18 赠品
     */
    private Integer goodsSource;


}
