package com.zsyc.zt.report.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GOODS_SALES_PLAN_DTL")
@ApiModel(value="GoodsSalesPlanDtl对象", description="商品销售计划商品")
@KeySequence(value = "GOODS_SALES_PLAN_DTL_SEQ")
public class GoodsSalesPlanDtl extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 商品销售计划细单ID
     */
    @ApiModelProperty(value = "商品销售计划细单ID")
    @TableId("PLAN_DTL_ID")
    private Long planDtlId;

    /**
     * 计划ID
     */
    @ApiModelProperty(value = "计划ID")
    @TableField("PLAN_ID")
    private Long planId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 商品通用名
     */
    @ApiModelProperty(value = "商品通用名")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    @TableField("GOODS_TYPE")
    private String goodsType;

    /**
     * 基本单位
     */
    @ApiModelProperty(value = "基本单位")
    @TableField("GOODS_UNIT")
    private String goodsUnit;

    /**
     * 药品类型
     */
    @ApiModelProperty(value = "药品类型")
    @TableField("MEDICINE_TYPE_NAME")
    private String medicineTypeName;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    @TableField("FACTORY_ID")
    private Long factoryId;

    /**
     * 生产厂商名称
     */
    @ApiModelProperty(value = "生产厂商名称")
    @TableField("FACTORY_NAME")
    private String factoryName;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    @TableField("PROD_AREA")
    private String prodArea;

//    /**
//     * 是否删除：1-删除，2-显示
//     */
//    @ApiModelProperty(value = "是否删除：1-删除，2-显示")
//    @TableField("IS_DELETE")
//    private Integer isDelete;

    /**
     * 操作员ID
     */
    @ApiModelProperty(value = "操作员ID")
    @TableField("OPERATOR_ID")
    private Long operatorId;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    @TableField("OPERATION_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationDate;

    /**
     * 操作员
     */
    @ApiModelProperty(value = "操作员")
    @TableField("OPERATOR")
    private String operator;

}
