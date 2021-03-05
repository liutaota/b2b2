package com.zsyc.zt.b2b.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

@Data
@Accessors(chain = true)
@ApiModel(value = "AdminInfoVo对象", description = "待办/统计")
public class AdminInfoVo implements Serializable {
    /**
     * 今日待办信息
     */
    @ApiModelProperty(value = "新增企业待审核")
    private Integer enterpriseNum;

    @ApiModelProperty(value = "售后待审核")
    private Integer aftermarketNum;

    @ApiModelProperty(value = "订单待下发")
    private Integer orderStayIssueNum;

    @ApiModelProperty(value = "订单待发货")
    private Integer orderStayShipmentsNum;

    /**
     * 金额统计
     */
    @ApiModelProperty(value = "下单金额")
    private Double orderAmount;

    @ApiModelProperty(value = "付款金额")
    private Double payAmount;

    @ApiModelProperty(value = "已出库金额")
    private Double sentAmount;

    @ApiModelProperty(value = "退货金额")
    private Double refundAmount;

    /**
     * 数量统计
     */
    @ApiModelProperty(value = "下单订单数量")
    private Integer orderNum;

    @ApiModelProperty(value = "下单用户数量")
    private Long memberNum;

    @ApiModelProperty(value = "已出库用户数量")
    private Integer sentMemberNum;
}
