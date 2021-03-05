package com.zsyc.zt.report.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GOODS_SALES_REPORT")
@ApiModel(value="GoodsSalesReport对象", description="商品销售报告")
@KeySequence(value = "GOODS_SALES_REPORT_SEQ")
public class GoodsSalesReport extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("REPORT_ID")
    private Long reportId;

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
     * 统计类型：1按计划，2按月份
     */
    @ApiModelProperty(value = "统计类型：1按计划，2按月份")
    @TableField("EXTRACT_TYPE")
    private Integer extractType;

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
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")
    @TableField("SUM_GOODS_QTY")
    private Double sumGoodsQty;

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

    /**
     * 药品类型
     */
    @ApiModelProperty(value = "药品类型")
    @TableField("MEDICINE_TYPE_NAME")
    private String medicineTypeName;

    /**
     * 统计范围：yyyy/mm,yyyy/mm - yyyy/mm
     */
    @ApiModelProperty(value = "统计范围：yyyy/mm,yyyy/mm - yyyy/mm")
    @TableField("STATISTICS_SCOPE")
    private String statisticsScope;

    @TableField("CONVERT_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime convertDate;

    @TableField("CREATE_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 药品类型
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
}
