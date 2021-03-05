package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 对接订单数据细单
 * </p>
 *
 * @author MP
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("API_ORDER_DTL")
@ApiModel(value="ApiOrderDtl对象", description="对接订单数据细单")
@KeySequence(value = "API_ORDER_DTL_SEQ")
public class ApiOrderDtl extends Model<ApiOrderDtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    @TableField("ORDER_DTL_ID")
    private String orderDtlId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    @TableField("LOT_NO")
    private String lotNo;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    @TableField("PRICE")
    private Double price;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @TableField("NUM")
    private Double num;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @TableField("AMOUNT")
    private Double amount;

    @TableField("SRC_ORDER_DTL_ID")
    private String srcOrderDtlId;

    @TableField("SRCORDERID")
    private String srcorderid;

    /**
     * 保管账ID
     */
    @ApiModelProperty(value = "保管账ID")
    @TableField("STORAGE_ID")
    private Integer storageId;

    /**
     * 总单ID
     */
    @ApiModelProperty(value = "总单ID")
    @TableField("API_ORDER_ID")
    private Long apiOrderId;

}
