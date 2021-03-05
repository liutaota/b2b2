package com.zsyc.zt.report.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
@ApiModel(value="GoodsSalesPlanDtl对象", description="商品销售计划商品")
public class GoodsSalesPlanDtlVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品销售计划细单ID
     */
    @ApiModelProperty(value = "商品销售计划细单ID")
    private Long planDtlId;

    /**
     * 计划ID
     */
    @ApiModelProperty(value = "计划ID")
    private Long planId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品通用名
     */
    @ApiModelProperty(value = "商品通用名")
    private String goodsName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String goodsType;

    /**
     * 基本单位
     */
    @ApiModelProperty(value = "基本单位")
    private String goodsUnit;

    /**
     * 药品类型
     */
    @ApiModelProperty(value = "药品类型")
    private String medicineTypeName;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long factoryId;

    /**
     * 生产厂商名称
     */
    @ApiModelProperty(value = "生产厂商名称")
    private String factoryName;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String prodArea;

//    /**
//     * 是否删除：1-删除，2-显示
//     */
//    @ApiModelProperty(value = "是否删除：1-删除，2-显示")
//    @JsonIgnore
//    private Integer isDelete;

    /**
     * 操作员ID
     */
    @ApiModelProperty(value = "操作员ID")
    @JsonIgnore
    private Long operatorId;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationDate;

    /**
     * 操作员
     */
    @ApiModelProperty(value = "操作员")
    private String operator;

}
