package com.zsyc.zt.b2b.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "BannedVo对象", description = "禁销限销筛选参数")
public class BannedVo implements Serializable {

    /**
     * 禁销限销筛选参数
     * 禁销限销列表
     * 客户集合总单
     * 客户集合细单
     * 货品集合总单
     * 货品集合细单
     */
    @ApiModelProperty(value = "总单ID")
    private Long forbidid;

    @ApiModelProperty(value = "客户集合ID")
    private Long customsetid;

    @ApiModelProperty(value = "客户集合名称")
    private String customsetname;

    @ApiModelProperty("客户集合状态")
    private Long customsetusestatus;

    @ApiModelProperty(value = "客户ID")
    private Long customid;

    @ApiModelProperty(value = "客户名称")
    private String customname;

    @ApiModelProperty(value = "客户分类编码")
    private String customno;

    @ApiModelProperty(value = "客户细单ID")
    private Long setdlid;

    @ApiModelProperty(value = "货品ID")
    private Long goodsid;

    @ApiModelProperty(value = "货品集合ID")
    private Long goodssetid;

    @ApiModelProperty(value = "货品集合细单ID")
    private Long setenumdtlid;

    @ApiModelProperty(value = "货品集合名称")
    private String goodssetname;

    @ApiModelProperty("货品集合状态")
    private Long goodssetusestatus;

    @ApiModelProperty(value = "通用名")
    private String goodsname;

    @ApiModelProperty(value = "基本单位")
    private String goodsunit;

    @ApiModelProperty(value = "规格")
    private String goodstype;

    @ApiModelProperty(value = "厂商")
    private String factoryname;

    @ApiModelProperty(value = "质量状态")
    private Long gspusestatus;

    @ApiModelProperty(value = "禁销类型:1-禁销，2-限销")
    private Long forbidflag;

    @ApiModelProperty(value = "使用状态")
    private Long usestatus;

    @ApiModelProperty(value = "建立日期/录入日期")
    private LocalDateTime credate;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "建立人ID/录入人ID")
    private Long inputmanid;

    @ApiModelProperty(value = "建立人/录入人")
    private String inputmaname;

    @ApiModelProperty(value = "独立单元ID")
    private Long entryid;

    @ApiModelProperty(value = "独立单元")
    private String entryname;

    @ApiModelProperty(value = "委托人ID")
    private Long agentid;

    @ApiModelProperty(value = "联系人ID")
    private Long contactid;

    @ApiModelProperty(value = "集合ID-通用客户/货品")
    private Long setid;

    @ApiModelProperty(value = "集合名称-通用客户/货品")
    private String setname;

    @ApiModelProperty(value = "起始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
}
