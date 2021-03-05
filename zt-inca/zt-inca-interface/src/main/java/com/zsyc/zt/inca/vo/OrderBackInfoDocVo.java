package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author  peiqy
 */
@Data
public class OrderBackInfoDocVo implements Serializable {

    private static final long serialVersionUID = 4951336634117202760L;


    /**
     * 退单订单编号
     */
    private String orderNo;

    /**
     * 退单订单ID
     */
    private Long orderId;


    /**
     * 源订单编号
     */
    private String srcOrderNo;

    /**
     * 源订单编号
     */
    private Long srcOrderId;


    /**
     * 业务日期  退货日期
     */
    private LocalDateTime createDate;

    /**
     * 客户ID
     */
    private Long customId;

    /**
     * 审核人ID
     */
    private Long approveManId;

    /**
     * 独立单元ID
     */
    private Integer entryId;

    /**
     * 仓库Id
     */
    private Long storerId;
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
     * 订单快递金额
     */
    private Double amountDelivery;

    /**
     * 备注
     */
    private String remark;




    private String version;

    /**
     * 保管账ID 用作分离不同的申请
     */
    private Integer storagetId;


    private List<OrderBackInfoDtlVo> orderInfoDtlList;


}
