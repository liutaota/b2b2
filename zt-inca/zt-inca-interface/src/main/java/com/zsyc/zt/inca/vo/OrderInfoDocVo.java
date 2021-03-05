package com.zsyc.zt.inca.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
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
     * 支付订单号
     */
    private String payOrderNo;

    /**
     * 支付流水号
     */
    private String payFlowNo;

    /**
     * 订单类型  1  普通单  2是补货单  3 退货单
     */
    private String orderType;

    /**
     * 支付类型
     */
    private String payType;



    /**
     * 支付代码
     */
    private String payCode;

    /**
     * 支付代码 对应的中文
     */
    private String payCodeCn;




    /**
     * 送货地址
     */
    private Long targetPosId;


    /**
     * 订单来源  1WEB2mobile  3 后台下单  4 接口下单
     */
    private Integer orderFrom;




    /**
     * 单据日期
     */
    private LocalDateTime createDate;

    /**
     * 客户ID
     */
    private Long customId;

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
     * 收货人ID
     */
    private Long receiptManId;


    /**
     * 订单快递金额
     */
    private Double amountDelivery;

    /**
     * 备注
     */
    private String remark;


    private String version;


    private Long storeId;

    private List<OrderInfoDtlVo> orderInfoDtlList;

    @ApiModelProperty(value = "门店订单id")
    private Long srcOrderId;

    @ApiModelProperty(value = "门店订单号")
    private String srcOrderNo;

    @ApiModelProperty(value = "门店订单时间")
    private LocalDateTime srcOrderTime;
}
