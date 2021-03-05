package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value  = "路单总单查询条件")
@Data
public class BmsTranscontrolDocDto implements Serializable {


    @ApiModelProperty(name  = "路单总单ID")
    private Long roadid;

    @ApiModelProperty(name  = "车辆ID")
    private Long vehicleid;

    @ApiModelProperty(name  = "驾驶员ID")
    private Long driverid;

    @ApiModelProperty(name  = "送货日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime delidate;

    @ApiModelProperty(name  = "使用状态")
    private Integer usestatus;

    @ApiModelProperty(name  = "送货人ID")
    private Long delimanid;

    @ApiModelProperty(name  = "总件数")
    private BigDecimal sumquantity;

    @ApiModelProperty(name  = "总金额")
    private BigDecimal sumcost;

    @ApiModelProperty(name  = "细单条数")
    private Long dtlcount;

    @ApiModelProperty(name  = "录入人ID")
    private Long inputmanid;

    @ApiModelProperty(name  = "录入日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime credate;

    @ApiModelProperty(name  = "确认人ID")
    private Long confirmmanid;

    @ApiModelProperty(name  = "确认日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime confirmdate;

    @ApiModelProperty(name  = "备注")
    private String memo;

    @ApiModelProperty(name  = "打印标志")
    private Integer printflag;

    @ApiModelProperty(name  = "打印人ID")
    private Long printmanid;

    @ApiModelProperty(name  = "打印日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime printdate;

    //    @ApiModelProperty(name  = "")
    private Integer gspunconfirmflag;

    @ApiModelProperty(name  = "含冷藏货品标志")
    private Long coldstorageflag;

    @ApiModelProperty(name  = "运输单位ID")
    private Long transportid;

    @ApiModelProperty(name  = "")
    private Long transporttype;

    @ApiModelProperty(name  = "运输单位类型")
    private Integer tranmethod;

    @ApiModelProperty(name  = "启运时间")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime startdate;

    @ApiModelProperty(name  = "外运驾驶员")
    private String externaldriver;

    @ApiModelProperty(name  = "外运送货员")
    private String externaldeliman;

    //    @ApiModelProperty(name  = "")
    private Integer filecount;

    @ApiModelProperty(name  = "启运温度")
    private BigDecimal starttemperature;

    //    @ApiModelProperty(name  = "")
    private Integer zxSarectype;

    @ApiModelProperty(name  = "快递单位")
    private Integer zxDeliveryunit;

    @ApiModelProperty(name  = "快递日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime zxDeliverydate;

    @ApiModelProperty(name  = "快递单号")
    private String zxDeliverynum;

    @ApiModelProperty(name  = "是否出车")
    private Integer dispatchflag;

    @ApiModelProperty(name  = "打印时是否打印首页")
    private Integer zxPrinthomeflag;

    @ApiModelProperty(name  = "打印时是否打印交接凭证")
    private Integer zxPrinttranscertiflag;

    @ApiModelProperty(name  = "打印时是否打印出车表")
    private Integer zxPrintccbflag;



}
