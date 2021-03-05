package com.zsyc.zt.report.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
@Data
@ApiModel(value="GoodsSalesPlan对象", description="商品销售计划")
@ExcelTarget("goodsSalesReportVo")
public class GoodsSalesPlanDocVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Excel(name = "计划ID",width = 10)
    private Long planId;

    /**
     * 计划名称
     */
    @ApiModelProperty(value = "计划名称")
    @Excel(name = "计划名称",width = 30)
    private String planName;

    /**
     * 计划开始日期
     */
    @ApiModelProperty(value = "计划开始日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始日期",width = 18,format = "yyyy-MM-dd")
    private LocalDate planStartDate;

    /**
     * 计划结束日期
     */
    @ApiModelProperty(value = "计划结束日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束日期",width = 18,format = "yyyy-MM-dd")
    private LocalDate planEndDate;

    /**
     * 使用状态:1:临时，2:正式
     */
    @ApiModelProperty(value = "使用状态:1:临时，2:正式")
    @Excel(name = "使用状态",width = 10,replace = {"临时_1","正式_2"})
    private Integer useStatus;

    /**
     * 统计使用的基本单位
     */
    @ApiModelProperty(value = "统计使用的基本单位")
    @Excel(name = "基本单位", width = 10)
    private String statisticsUnits;

    /**
     * 指标销量
     */
    @ApiModelProperty(value = "指标销量")
    @Excel(name = "指标销量", width = 16, numFormat = "#0.0000")
    private Double planNum;

    /**
     * 已完成销量
     */
    @ApiModelProperty(value = "已完成销量")
    @Excel(name = "已完成销量", width = 16, numFormat = "#0.0000")
    private Double completeGoodsQty;

    /**
     * 待完成销量
     */
    @ApiModelProperty(value = "待完成销量")
    @Excel(name = "待完成销量", width = 16, numFormat = "#0.0000")
    private Double notCompleteGoodsQty;

    /**
     * 完成率
     */
    @ApiModelProperty(value = "完成率")
    @Excel(name = "完成率", width = 20)
    private String completePercent;

    /**
     * 待完成率
     */
    @ApiModelProperty(value = "待完成率")
    @Excel(name = "待完成率", width = 20)
    private String notCompletePercent;

    /**
     * 录入人员
     */
    @ApiModelProperty(value = "录入人员")
    @Excel(name = "录入人员", width = 15)
    private String inputName;

    /**
     * 录入人员ID
     */
    @ApiModelProperty(value = "录入人员ID")
    @Excel(name = "录入人员ID", width = 10)
    private Long inputId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 25, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 确认人员
     */
    @ApiModelProperty(value = "确认人员")
    @Excel(name = "确认人员", width = 15)
    private String confirmName;

    /**
     * 确认人员ID
     */
    @ApiModelProperty(value = "确认人员ID")
    @Excel(name = "确认人员ID", width = 10)
    private Long confirmId;

    /**
     * 确认日期
     */
    @ApiModelProperty(value = "确认日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "确认日期", width = 25, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime confirmDate;

    /**
     * 回退人员
     */
    @ApiModelProperty(value = "回退人员")
    //@Excel(name = "回退人员", width = 15)
    private String unConfirmName;

    /**
     * 回退人员ID
     */
    @ApiModelProperty(value = "回退人员ID")
    //@Excel(name = "回退人员ID", width = 15)
    private Long unConfirmId;

    /**
     * 回退日期
     */
    @ApiModelProperty(value = "回退日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    //@Excel(name = "回退日期", width = 28, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime unConfirmDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注", width = 40)
    private String remark;

    /**
     * 是否删除：1-删除，2-显示
     */
    @ApiModelProperty(value = "是否删除：1-删除，2-显示")
    @JsonIgnore
    private Integer isDelete;

    @ApiModelProperty(value = "开始查询日期" , name = "startDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束查询日期" , name = "endDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 销售计划的商品集合
     */
    @ApiModelProperty(value = "销售计划的商品集合",name = "goodsSalesPlanDtlVoList")
    List<GoodsSalesPlanDtlVo> goodsSalesPlanDtlVoList;

}
