package com.zsyc.zt.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单
 * @author  peiqy
 */
@Data
public class OrderInfoDocVo implements Serializable {

    private static final long serialVersionUID = 4951336634117202760L;


    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单类型  1  普通单  2是补货单
     */
    private String orderType;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 送货地址
     */
    private Long targetPosId;

    /**
     * 仓房地址 对应 送过地址 通过b2b_store_id去取
     */
    private Long storeId;


    /**
     * 订单来源  1  web  2是 小程序
     */
    private Integer orderFrom;




    /**
     * 单据日期
     */
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 客户ID
     */
    private Long customId;

    /**
     * 客户ID
     */
    private Long b2bCustomId;


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
     * 订单应付金额 = amountTotal-amountDiscount
     */
    private Double amountPay;


    /**
     * 订单快递金额
     */
    private Double amountDelivery;

    /**
     * 备注
     */
    private String remark;


    private String version;


    private List<OrderInfoDtlVo> orderInfoDtlList;
}
