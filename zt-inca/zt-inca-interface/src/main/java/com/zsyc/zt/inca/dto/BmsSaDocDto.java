package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(description = "销售发货单查询条件")
@Data
public class BmsSaDocDto implements Serializable {


    @ApiModelProperty(name = "销售总单ID")
    private Long salesid;

    @ApiModelProperty(name  = "业务日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime credate;

    @ApiModelProperty(name  = "客户ID")
    private Long customid;

    @ApiModelProperty(name  = "客户")
    private String customname;

    @ApiModelProperty(name  = "发票类型")
    private Integer invtype;

    @ApiModelProperty(name  = "结算方式")
    private Integer settletypeid;

    @ApiModelProperty(name  = "业务员ID")
    private Long salerid;

    @ApiModelProperty(name  = "业务部门ID")
    private Long salesdeptid;

    @ApiModelProperty(name  = "发货方式")
    private Integer delivermethod;

    @ApiModelProperty(name  = "运输工具")
    private Integer tranmethod;

    @ApiModelProperty(name  = "送货地点ID")
    private Long targetposid;

    @ApiModelProperty(name  = "加急标志")
    private Integer urgentflag;

    @ApiModelProperty(name  = "运输等级")
    private Integer tranpriority;

    @ApiModelProperty(name  = "送达日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime targetdate;

    @ApiModelProperty(name  = "总金额")
    private BigDecimal total;

    @ApiModelProperty(name  = "确认人ID")
    private Long confirmanid;

    @ApiModelProperty(name  = "确认日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime confirmdate;

    @ApiModelProperty(name  = "录入人ID")
    private Long inputmanid;

    @ApiModelProperty(name  = "备注")
    private String memo;

    @ApiModelProperty(name  = "细单条数")
    private Integer dtlLines;

    @ApiModelProperty(name  = "销售单类型")
    private Integer satypeid;

    @ApiModelProperty(name  = "初始化标志")
    private Integer initflag;

    @ApiModelProperty(name  = "独立单元ID")
    private Long entryid;

    @ApiModelProperty(name  = "保管账集合")
    private Long stsetid;

    @ApiModelProperty(name  = "外币ID")
    private Long fmid;

    @ApiModelProperty(name  = "汇率")
    private BigDecimal exchange;

    //    @ApiModelProperty(name  = "")
    private Integer creditflag;

    @ApiModelProperty(name  = "可用信誉额度")
    private BigDecimal credit;

    @ApiModelProperty(name  = "欠款金额")
    private BigDecimal recmoney;

    //    @ApiModelProperty(name  = "")
    private Integer creditdaysflag;

    @ApiModelProperty(name  = "可用信用天数")
    private Long creditdays;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime recdate;

    @ApiModelProperty(name  = "是否控制最低限价")
    private Integer lowpriceflag;

    @ApiModelProperty(name  = "审批标志")
    private Integer approveflag;

    @ApiModelProperty(name  = "审批人ID")
    private Long approvemanid;

    @ApiModelProperty(name  = "审批日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime approvedate;

    @ApiModelProperty(name  = "审批意见")
    private String approvememo;

    @ApiModelProperty(name  = "考核日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime assessdate;

    @ApiModelProperty(name  = "使用状态")
    private Integer usestatus;

    @ApiModelProperty(name  = "销售回执标志")
    private Integer receiveflag;

    @ApiModelProperty(name  = "回执确认人ID")
    private Long receivemanid;

    @ApiModelProperty(name  = "回执确认日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime receivedate;

    @ApiModelProperty(name  = "打印次数")
    private Long printcount;

    @ApiModelProperty(name  = "打印人ID")
    private Long printmanid;

    @ApiModelProperty(name  = "打印日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime printdate;

    @ApiModelProperty(name  = "来源")
    private Integer comefrom;

    //    @ApiModelProperty(name  = "")
    private Long sacertid;

    //    @ApiModelProperty(name  = "")
    private Long rgid;

    @ApiModelProperty(name  = "委托人ID")
    private Long agentid;

    //    @ApiModelProperty(name  = "")
    private Integer toedistatus;

    //    @ApiModelProperty(name  = "")
    private Integer invalidstatus;

    @ApiModelProperty(name  = "回款天数")
    private Long setdays;

    @ApiModelProperty(name  = "奖息率")
    private BigDecimal awardrate;

    @ApiModelProperty(name  = "配送单ID")
    private BigDecimal punishrate;

    @ApiModelProperty(name  = "")
    private Long placesupplyid;

    @ApiModelProperty(name  = "无物流标志")
    private Integer nowmsflag;

    @ApiModelProperty(name  = "预计开票时间")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime planinvdate;

    @ApiModelProperty(name  = "发票要求")
    private Integer invdemand;

    @ApiModelProperty(name  = "联系人ID")
    private Long contactid;

    //    @ApiModelProperty(name  = "")
    private Integer discounttype;

    //    @ApiModelProperty(name  = "")
    private String transdocno;

    @ApiModelProperty(name  = "两票制管控标志")
    private Integer twoinvflag;

    @ApiModelProperty(name  = "两票制管控方式")
    private Integer twoinvmethod;

    @ApiModelProperty(name  = "拆分总单ID")
    private Long zxOldsalesid;

    @ApiModelProperty(name  = "报货平台订单标志")
    private Integer zxBhFlag;

    @ApiModelProperty(name  = "报货平台订单号")
    private String zxOrdernumber;

    @ApiModelProperty(name  = "备注2")
    private String zxMemo;

    //    @ApiModelProperty(name  = "")
    private String invoice;

    //    @ApiModelProperty(name  = "")
    private String invdate;

    //    @ApiModelProperty(name  = "")
    private Integer zxInitsatus;

    @ApiModelProperty(name  = "开票策略")
    private Integer invmethod;

    //    @ApiModelProperty(name  = "")
    private Long bak;

    @ApiModelProperty(name  = "WMS指令集ID")
    private String cmdsetid;

    @ApiModelProperty(name  = "整件数")
    private String zxdoccol1;

    @ApiModelProperty(name  = "散件数")
    private String zxdoccol2;

    @ApiModelProperty(name  = "箱单列表")
    private String zxdoccol3;

    //    @ApiModelProperty(name  = "")
    private String cmdsetid2;

    @ApiModelProperty(name  = "金税开票标志")
    private Integer zxInvstatus;

    @ApiModelProperty(name  = "发票日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime zxInvbackdate;

//    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime zxInvdate;

    @ApiModelProperty(name  = "报错信息")
    private String zxErrormemo;

    @ApiModelProperty(name  = "发票轨号")
    private String zxInvno;

//    @ApiModelProperty(name  = "")
    private String zxInvcode;

    //    @ApiModelProperty(name  = "")
    private String sourceOrderNum;

    //    @ApiModelProperty(name  = "")
    private String sourceDesc;

    /**
     * 1 未回写 2 已回写
     */
    @ApiModelProperty(name  = "1 未回写 2 已回写")
    private Integer jdWriteBackFlag;

    @ApiModelProperty(name  = "(京东)订单号")
    private String jdOrderId;

    //    @ApiModelProperty(name  = "")
    private String jdOrderDesc;

    //    @ApiModelProperty(name  = "")
    private BigDecimal jdTotalMoney;

    //    @ApiModelProperty(name  = "")
    private BigDecimal jdDiscount;

    //    @ApiModelProperty(name  = "")
    private BigDecimal jdPayMoney;

    /**
     * 京东支付方式 ：1、在线支付2、帐期支付3、线上支付4、月结支付5、线下支付6、货到付款
     */
    @ApiModelProperty(name  = "京东支付方式 ：1、在线支付2、帐期支付3、线上支付4、月结支付5、线下支付6、货到付款 ")
    private Integer jdPayType;

    /**
     * 京东订单类型  1，销售单  2  配送单转移过来
     */
    @ApiModelProperty(name  = "京东订单类型  1，销售单  2  配送单转移过来")
    private Integer jdOrderType;

    /**
     * 开票标记 1 开票  0 不开票
     */
    @ApiModelProperty(name  = "开票标记 1 开票  0 不开票")
    private Integer zxKpFlag;

    //    @ApiModelProperty(name  = "")
    private BigDecimal jdShopFlag;

    /**
     * 2 自动确认成功 3  不成功
     */
    @ApiModelProperty(name  = "2 自动确认成功 3不成功")
    private Integer autoConfirm;

    //    @ApiModelProperty(name  = "")
    private Integer b2bWriteBackFlag;

    //    @ApiModelProperty(name  = "")
    private BigDecimal b2bOrderId;

    //    @ApiModelProperty(name  = "")
    private BigDecimal b2bAmountTotal;

    //    @ApiModelProperty(name  = "")
    private BigDecimal b2bAmountDiscount;

    //    @ApiModelProperty(name  = "")
    private BigDecimal b2bAmountPay;

    //    @ApiModelProperty(name  = "")
    private BigDecimal b2bAmountDelivery;

    //    @ApiModelProperty(name  = "")
    private String b2bOrderType;

    //    @ApiModelProperty(name  = "")
    private String b2bPayType;

    //    @ApiModelProperty(name  = "")
    private String b2bOrderNo;

    //    @ApiModelProperty(name  = "")
    private BigDecimal b2bStoreId;




}
