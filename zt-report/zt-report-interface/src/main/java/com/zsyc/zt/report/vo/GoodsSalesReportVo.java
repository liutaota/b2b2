package com.zsyc.zt.report.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
@Data
@ApiModel(value="GoodsSalesReport对象", description="商品销售报告")
@ExcelTarget("goodsSalesReportVo")
public class GoodsSalesReportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Excel(name = "销售商品报表ID",width = 15)
    private Long reportId;

    /**
     * 计划ID
     */
    @ApiModelProperty(value = "计划ID")
    @Excel(name = "计划ID",width = 15)
    private Long planId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    @Excel(name = "商品ID",width = 15)
    private Long goodsId;

    /**
     * 商品通用名
     */
    @ApiModelProperty(value = "商品通用名")
    @Excel(name = "商品通用名",width = 20)
    private String goodsName;

    /**
     * 统计类型：1按计划，2按月份
     */
    @ApiModelProperty(value = "统计类型：1按计划，2按月份")
    @Excel(name = "统计类型",width = 15,replace = {"计划_1","月份_2"})
    private Integer extractType;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    @Excel(name = "规格",width = 15)
    private String goodsType;

    /**
     * 基本单位
     */
    @ApiModelProperty(value = "基本单位")
    @Excel(name = "计划ID",width = 15)
    private String goodsUnit;

    /**
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")
    @Excel(name = "销售数量",width = 22)
    private Double sumGoodsQty;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    @Excel(name = "生产厂商ID",width = 15)
    private Long factoryId;

    /**
     * 生产厂商名称
     */
    @ApiModelProperty(value = "生产厂商名称")
    @Excel(name = "生产厂商名称",width = 30)
    private String factoryName;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    @Excel(name = "产地",width = 15)
    private String prodArea;

    /**
     * 药品类型
     */
    @ApiModelProperty(value = "药品类型")
    @Excel(name = "药品类型",width = 20)
    private String medicineTypeName;

    /**
     * 统计范围：yyyy/mm,yyyy/mm - yyyy/mm
     */
    @ApiModelProperty(value = "统计范围：yyyy,yyyy/mm,yyyy/mm - yyyy/mm")
    @Excel(name = "统计范围",width = 18)
    private String statisticsScope;

    @ApiModelProperty(value = "统计范围：yyyy/mm,yyyy/mm - yyyy/mm")
    @JsonIgnore
    private LocalDateTime convertDate;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间",width = 26)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "开始查询日期" , name = "startDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束查询日期" , name = "endDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Excel(name = "计划ID",width = 40)
    private String remark;
}
