package com.zsyc.zt.inca.vo.others;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value =  "车辆任务客户订单收款信息实体类")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarTaskBmsDocVo implements Serializable {

    /**
     * 销售总单ID
     */
    @ApiModelProperty(value="销售总单ID")
    private Long salesId;

    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID")
    private Long customId;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称")
    private String customName;

    /**
     * 货品箱号
     */
    @ApiModelProperty(value="货品箱号")
    private String packIdList;

    /**
     * 总金额
     */
    @ApiModelProperty(value="总金额")
    private BigDecimal dtlCost;

    /**
     * 线下金额
     */
    @ApiModelProperty(value="线下金额")
    private BigDecimal xxDtlCost;

    /**
     * 京东已付金额
     */
    @ApiModelProperty(value="京东已付金额")
    private BigDecimal jdCost;

    /**
     * b2b线上已付金额
     */
    @ApiModelProperty(value="b2b线上已付金额")
    private BigDecimal b2bDtlCost;

    /**
     * 未收货标志
     */
    @ApiModelProperty(value="未收货标志")
    private BigDecimal zxUnDeliFlag;

//    /**
//     * 已付款金额
//     */
//    @ApiModelProperty(value="已付款金额")
//    private BigDecimal accountPaid;
//
//    /**
//     * 未付款金额
//     */
//    @ApiModelProperty(value="未付款金额")
//    private BigDecimal unPaid;


}
