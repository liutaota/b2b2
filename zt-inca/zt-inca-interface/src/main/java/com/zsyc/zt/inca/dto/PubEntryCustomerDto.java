package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value  = "客户运营参数查询条件")
@Data
public class PubEntryCustomerDto implements Serializable {

    @ApiModelProperty(name  = "客户运营参数实体id")
    private Long entrycustomerid;

    @ApiModelProperty(name  = "客户id")
    private Long customid;

    @ApiModelProperty(name  = "独立单元id")
    private Long entryid;

    @ApiModelProperty(name  = "质量状态")
    private Integer gspusestatus;

    @ApiModelProperty(name  = "首营通过日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime firstapprovedate;

    @ApiModelProperty(name  = "是否控制最低限价")
    private Integer lowpriceflag;

    @ApiModelProperty(name  = "结算方式")
    private Integer settletypeid;

    @ApiModelProperty(name  = "运输级别")
    private String tranpriority;

    @ApiModelProperty(name  = "运输工具")
    private Integer tranmethodid;

    @ApiModelProperty(name  = "送货待遇")
    private Integer delivermethod;

    @ApiModelProperty(name  = "发票类型")
    private Integer invtype;

    @ApiModelProperty(name  = "打印质检标志")
    private Integer reqprintquflag;

    @ApiModelProperty(name  = "财务编码")
    private String financeno;

    @ApiModelProperty(name  = "缺省价格类型ID")
    private Long priceid;

    @ApiModelProperty(name  = "数据来源")
    private Integer comefrom;

    @ApiModelProperty(name  = "期初数据")
    private Integer initflag;

    @ApiModelProperty(name  = "订阅规则细单ID")
    private Long subscribesetdtlid;

    @ApiModelProperty(name  = "销售状态")
    private Integer sausestatus;

    @ApiModelProperty(name  = "运营参数备注")
    private String entrymemo;

    @ApiModelProperty(name  = "最后修改时间")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime sysModifydate;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime towmsdate;

    @ApiModelProperty(name  = "单据委托人是否必填")
    private Integer ismustagent;

    @ApiModelProperty(name  = "单据联系人是否必填")
    private Integer ismustcontact;

    @ApiModelProperty(name  = "两票制管控标志")
    private Integer twoinvflag;

    @ApiModelProperty(name  = "两票制控制方式")
    @TableField("TWOINVMETHOD")
    private Integer twoinvmethod;

    @ApiModelProperty(name  = "是否波次发货")
    private Integer zxIsfix;

    @ApiModelProperty(name  = "打印单据")
    private Integer zxIsprint;

    @ApiModelProperty(name  = "对账日期")
    private Long zxDzDate;

    //    @ApiModelProperty(name  = "")
    private Integer zxBhFlag;

    /**
     * 是否启用阶梯价 0 否 1是
     */
    @ApiModelProperty(name  = "是否启用阶梯价")
    private Integer zxPlPriceFlag;



}
