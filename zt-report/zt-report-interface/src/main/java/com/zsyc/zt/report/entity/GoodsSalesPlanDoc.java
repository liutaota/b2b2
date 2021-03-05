package com.zsyc.zt.report.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@TableName("GOODS_SALES_PLAN_DOC")
@ApiModel(value="GoodsSalesPlan对象", description="商品销售计划")
@KeySequence(value = "GOODS_SALES_PLAN_DOC_SEQ")
public class GoodsSalesPlanDoc extends BaseBean {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("PLAN_ID")
    private Long planId;

    /**
     * 计划名称
     */
    @ApiModelProperty(value = "计划名称")
    @TableField("PLAN_NAME")
    private String planName;

    /**
     * 指标销量
     */
    @ApiModelProperty(value = "指标销量")
    @TableField("PLAN_NUM")
    private Double planNum;

    /**
     * 统计使用的基本单位
     */
    @ApiModelProperty(value = "统计使用的基本单位")
    @TableField("STATISTICS_UNITS")
    private String statisticsUnits;

    /**
     * 计划开始日期
     */
    @ApiModelProperty(value = "计划开始日期")
    @TableField("PLAN_START_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate planStartDate;

    /**
     * 计划结束日期
     */
    @ApiModelProperty(value = "计划结束日期")
    @TableField("PLAN_END_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate planEndDate;

    /**
     * 使用状态:1:临时，2:正式
     */
    @ApiModelProperty(value = "使用状态:1:临时，2:正式")
    @TableField("USE_STATUS")
    private Integer useStatus;

    /**
     * 操作员ID
     */
    @ApiModelProperty(value = "操作员ID")
    @TableField("INPUT_ID")
    private Long inputId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 确认人员ID
     */
    @ApiModelProperty(value = "确认人员ID")
    @TableField("CONFIRM_ID")
    private Long confirmId;

    /**
     * 确认日期
     */
    @ApiModelProperty(value = "确认日期")
    @TableField("CONFIRM_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime confirmDate;

    /**
     * 回退人员ID
     */
    @ApiModelProperty(value = "回退人员ID")
    @TableField("UN_CONFIRM_ID")
    private Long unConfirmId;

    /**
     * 回退日期
     */
    @ApiModelProperty(value = "回退日期")
    @TableField("UN_CONFIRM_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime unConfirmDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 是否删除：1-删除，2-显示
     */
    @ApiModelProperty(value = "是否删除：1-删除，2-显示")
    @TableField("IS_DELETE")
    private Integer isDelete;

    /**
     * 操作员
     */
    @ApiModelProperty(value = "操作员")
    @TableField("INPUT_NAME")
    private String inputName;

    /**
     * 确认人员
     */
    @ApiModelProperty(value = "确认人员")
    @TableField("CONFIRM_NAME")
    private String confirmName;

    /**
     * 回退人员
     */
    @ApiModelProperty(value = "回退人员")
    @TableField("UN_CONFIRM_NAME")
    private String unConfirmName;
}
