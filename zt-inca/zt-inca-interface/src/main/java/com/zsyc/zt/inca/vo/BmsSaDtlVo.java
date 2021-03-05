package com.zsyc.zt.inca.vo;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value = "销售发货单细单返回")
@Data
public class BmsSaDtlVo implements Serializable {
    @ApiModelProperty(name = "销售细单ID")
    private Long salesdtlid;

    @ApiModelProperty(name = "销售总单ID")
    private Long salesid;

    @ApiModelProperty(name = "打印号")
    private String printno;

    @ApiModelProperty(name = "打印列号")
    private Integer printline;

    @ApiModelProperty(name = "货品ID")
    private Long goodsid;

    @ApiModelProperty(name = "货品明细ID")
    private Long goodsdtlid;

    @ApiModelProperty (name = "税率")
    private BigDecimal taxrate;

    @ApiModelProperty(name = "基本单位数量")
    private BigDecimal goodsqty;

    @ApiModelProperty(name = "单价")
    private BigDecimal unitprice;

    //    @ApiModelProperty(name = "")
    private BigDecimal totalLine;

    @ApiModelProperty(name = "使用单位")

    private String goodsuseunit;

    @ApiModelProperty(name = "使用单位数量")
    private BigDecimal goodsuseqty;

    @ApiModelProperty(name = "折扣")
    private BigDecimal discount;

    @ApiModelProperty(name = "价格类型ID")
    private Long priceid;

    @ApiModelProperty(name = "超底价标志")
    private Integer lowerflag;

    @ApiModelProperty(name = "参考价格类型ID")
    private Long timepriceid;

    @ApiModelProperty(name = "参考价格")
    private BigDecimal timeprice;

    @ApiModelProperty(name = "保管账ID")
    private Long storageid;

    @ApiModelProperty(name = "累计发票数量")
    private BigDecimal settleqty;

    @ApiModelProperty(name = "累计发票金额")
    private BigDecimal settlemoney;

    @ApiModelProperty(name = "开票标志")
    private Integer settleflag;

    @ApiModelProperty(name = "批发价")
    private BigDecimal wholesaleprice;

    @ApiModelProperty(name = "零售价")
    private BigDecimal resaleprice;

    @ApiModelProperty(name = "备注")
    private String dtlmemo;

    @ApiModelProperty(name = "允许退货标志")
    private Integer allowbackflag;

    @ApiModelProperty(name = "冲调标志")
    private Integer correctflag;

    @ApiModelProperty(name = "存货传票ID")
    private Long certid;

    @ApiModelProperty(name = "退货原因")
    private Integer backwhyid;

    @ApiModelProperty(name = "无税金额")
    private BigDecimal notaxmoney;

    @ApiModelProperty(name = "上次销价类型ID")
    private Long lastpriceid;

    @ApiModelProperty(name = "上次销价")
    private BigDecimal lastunitprice;

    @ApiModelProperty(name = "批次ID")
    private Long batchid;

    @ApiModelProperty(name = "批号ID")
    private Long lotid;

    @ApiModelProperty(name = "货位ID")
    private Long posid;

    @ApiModelProperty(name = "货品状态")
    private Long goodsstatusid;

    @ApiModelProperty(name = "记保管账标志")
    private Integer stioflag;

    @ApiModelProperty(name = "记保管账日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime stiodate;

    @ApiModelProperty(name = "批次毛利")
    private BigDecimal lotgross;

    @ApiModelProperty(name = "打印标志")
    private Integer printflag;

    @ApiModelProperty(name = "打印人ID")
    private Long printmanid;

    @ApiModelProperty(name = "打印日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime printdate;

    @ApiModelProperty(name = "需要打印质检标志")
    private Integer reqprintquflag;

    @ApiModelProperty(name = "已经打印质检标志")
    private Integer hasprintquflag;

    @ApiModelProperty(name = "原单价")
    private BigDecimal oldunitprice;

    //    @ApiModelProperty(name = "")
    private Integer redcorrectflag;

    //    @ApiModelProperty(name = "")
    private Long redsalesid;

    //    @ApiModelProperty(name = "")
    private Integer batchflag;

    @ApiModelProperty(name = "批次价")
    private BigDecimal batchprice;

    @ApiModelProperty(name = "上次销售日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime lastdate;

    @ApiModelProperty(name = "原折扣")
    private BigDecimal olddiscount;

    @ApiModelProperty(name = "考核毛利")
    private BigDecimal examprofit;

    @ApiModelProperty(name = "考核价")
    private BigDecimal examprice;

    @ApiModelProperty(name = "件数")
    private BigDecimal packqty;

    @ApiModelProperty(name = "批次毛利率")
    private BigDecimal lotgrossrate;

    @ApiModelProperty(name = "上次销售数量")
    private BigDecimal lastgoodsqty;

    //    @ApiModelProperty(name = "")
    private Long placesupplydtlstid;

    //    @ApiModelProperty(name = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime oldsadate;

    //    @ApiModelProperty(name = "")
    private BigDecimal oldsaqty;

    @ApiModelProperty(name = "中标组ID")
    private Long bidsetid;

    @ApiModelProperty(name = "使用单位大小")
    private BigDecimal usepacksize;

    //    @ApiModelProperty(name = "")
    private Integer genfacsinglerebflag;

    //    @ApiModelProperty(name = "")
    private Integer gensupsinglerebflag;

    //    @ApiModelProperty(name = "")
    private Integer genfactotalrebflag;

    //    @ApiModelProperty(name = "")
    private Integer gensuptotalrebflag;

    //    @ApiModelProperty(name = "")
    private Long genfrozxsabkdtlid;

    //    @ApiModelProperty(name = "")
    private Long genfrozxsadtlid;

    //    @ApiModelProperty(name = "")
    private Long oristorageid;

    @ApiModelProperty(name = "")
    private Integer limitedsaleflag;

    @ApiModelProperty(name = "累计收款金额")
    private BigDecimal totalrecmoney;

    //    @ApiModelProperty(name = "")
    private BigDecimal nosettlemoney;

    //    @ApiModelProperty(name = "")
    private BigDecimal nosettlesqty;

    //    @ApiModelProperty(name = "")
    private BigDecimal adjustpricedtlid;

    //    @ApiModelProperty(name = "")
    private Long impdtlid;

    @ApiModelProperty(name = "发票号码")
    private String invno;

    @ApiModelProperty(name = "下传物流标志")
    private Integer sendwmsflag;

    @ApiModelProperty(name = "下传物流日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime sendwmsdate;

    @ApiModelProperty(name = "物流反馈日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime wmsbackdate;

    //    @ApiModelProperty(name = "")
    private Long printcountbill;

    //    @ApiModelProperty(name = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime printdatebill;

    //    @ApiModelProperty(name = "")
    private Long printmanidbill;

    //    @ApiModelProperty(name = "")
    private Long printcountinv;

    //    @ApiModelProperty(name = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime printdateinv;

    //    @ApiModelProperty(name = "")
    private Long printmanidinv;

    @ApiModelProperty(name = "促销信息")
    private String zxCxinfo;

    @ApiModelProperty(name = "效期月数")
    private Long zxDiffmonths;

    @ApiModelProperty(name = "近效期提醒月数")
    private Long zxRemindmonths;

    @ApiModelProperty(name = "拆单分类")
    private Integer zxSplitType;

    //    @ApiModelProperty(name = "")
    private BigDecimal zxDzje;

    //    @ApiModelProperty(name = "")
    private Integer zxDzbz;

    //    @ApiModelProperty(name = "")
    private Integer zxSjzt;

    @ApiModelProperty(name = "补差价")
    private BigDecimal zxDifprice;

    @ApiModelProperty(name = "赠品关联ID")
    private Long zxReleteid;

    @ApiModelProperty(name = "平台赠品关联ID")
    private Long zxBhreleteid;

    //    @ApiModelProperty(name = "")
    private BigDecimal jdSourceNum;

    @ApiModelProperty(name = "(京东)订单号")
    private Long jdOrderId;

    //    @ApiModelProperty(name = "")
    private Long jdOrderDtlId;

    //    @ApiModelProperty(name = "")
    private BigDecimal jdTotalMoney;

    //    @ApiModelProperty(name = "")
    private BigDecimal jdDiscount;

    //    @ApiModelProperty(name = "")
    private BigDecimal jdPayMoney;

    /**
     * 京东优惠单价
     */
    @ApiModelProperty(name = "京东优惠单价")
    private BigDecimal jdUnitPrice;

    /**
     * 京东单项金额
     */
    @ApiModelProperty(name = "京东单项金额" )
    private BigDecimal jdMoney;

    /**
     * 京东订单类型  1，销售单  2  配送单转移过来
     */
    @ApiModelProperty(name = "京东订单类型  1，销售单  2  配送单转移过来")
    private Integer jdOrderType;

    //    @ApiModelProperty(name = "")
    private String zxKpMsg;

    /**
     * 1初始值  2 已经回写
     */
    @ApiModelProperty(name = "1初始值  2 已经回写")
    private Integer zxKpFlag;

    //    @ApiModelProperty(name = "")
    private Integer jdPayType;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bOrderId;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bOrderDtlId;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bAmountTotal;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bAmountDiscount;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bAmountPay;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bAmountDelivery;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bNum;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bPrice;

    //    @ApiModelProperty(name = "")
    private BigDecimal b2bPriceDiscount;

    //    @ApiModelProperty(name = "")
    private String b2bOrderType;

    //    @ApiModelProperty(name = "")
    private String b2bOrderNo;



}
