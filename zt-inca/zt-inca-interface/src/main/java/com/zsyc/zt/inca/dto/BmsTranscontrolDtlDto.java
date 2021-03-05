package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value =  "路单细单查询条件")
@Data
public class BmsTranscontrolDtlDto implements Serializable {

    @ApiModelProperty(name  = "路单细单ID")
    private Long roaddtlid;

    @ApiModelProperty(name  = "路单总单ID")
    private Long roadid;

    @ApiModelProperty(name  = "单位ID")
    private Long companyid;

    @ApiModelProperty(name  = "运输地点ID")
    private Long tranposid;

    //    @ApiModelProperty(name  = "")
    private Long transortno;

    @ApiModelProperty(name  = "整件数")
    private Long totalpackqty;

    @ApiModelProperty(name  = "金额")
    private BigDecimal dtlcost;

    @ApiModelProperty(name  = "签收人")
    private String receiptman;

    @ApiModelProperty(name  = "签收日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime receiptdate;

    @ApiModelProperty(name  = "回执标记")
    private Integer returnflag;

    @ApiModelProperty(name  = "调度总单ID")
    private Long trid;

    @ApiModelProperty(name  = "单据来源ID")
    private Long sourceid;

    @ApiModelProperty(name  = "单据来源类型")
    private Integer sourcetype;

    @ApiModelProperty(name  = "备注")
    private String memo;

    @ApiModelProperty(name  = "收款额")
    private BigDecimal recemoney;

    //    @ApiModelProperty(name  = "")
    private Long trdtlid;

    //    @ApiModelProperty(name  = "")
    private Long salesid;

    @ApiModelProperty(name  = "装箱数")
    private BigDecimal boxqty;

    @ApiModelProperty(name  = "含特殊药品复方制剂")
    private Integer spedrugflag;

    @ApiModelProperty(name  = "启运温度")
    private BigDecimal starttemperature;

    //    @ApiModelProperty(name  = "")
    private BigDecimal reachtemperature;

    @ApiModelProperty(name  = "含冷藏货品标志")
    private Integer coldstorageflag;

    //    @ApiModelProperty(name  = "")
    private Integer zxSarectype;

    @ApiModelProperty(name  = "未送货标志")
    private Integer zxUndeliflag;

    //    @ApiModelProperty(name  = "")
    private Integer undeliflag;

    //    @ApiModelProperty(name  = "")
    private String swiftnumber;

    //    @ApiModelProperty(name  = "")
    private String zxErrormsg;

    //    @ApiModelProperty(name  = "")
    private Integer zxApppayflag;





}
