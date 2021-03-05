package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
@Data

@Accessors(chain = true)
@TableName("BMS_SA_DTL")
@ApiModel(value="BmsSaDtl对象", description="")
@KeySequence(value = "BMS_SA_DTL_SEQ")
public class BmsSaDtl {

    private static final long serialVersionUID = 1L;

    @TableId("SALESDTLID")
    private Long salesdtlid;

    @TableField("SALESID")
    private Long salesid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("TAXRATE")
    private Double taxrate;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("UNITPRICE")
    private Double unitprice;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private Double goodsuseqty;

    @TableField("DISCOUNT")
    private Double discount;

    @TableField("PRICEID")
    private Integer priceid;

    @TableField("LOWERFLAG")
    private Integer lowerflag;

    @TableField("TIMEPRICEID")
    private Long timepriceid;

    @TableField("TIMEPRICE")
    private Double timeprice;

    @TableField("STORAGEID")
    private Integer storageid;

    @TableField("SETTLEQTY")
    private Double settleqty;

    @TableField("SETTLEMONEY")
    private Double settlemoney;

    @TableField("SETTLEFLAG")
    private Integer settleflag;

    @TableField("WHOLESALEPRICE")
    private Double wholesaleprice;

    @TableField("RESALEPRICE")
    private Double resaleprice;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("ALLOWBACKFLAG")
    private Integer allowbackflag;

    @TableField("CORRECTFLAG")
    private Integer correctflag;

    @TableField("CERTID")
    private Long certid;

    @TableField("BACKWHYID")
    private Integer backwhyid;

    @TableField("NOTAXMONEY")
    private Double notaxmoney;

    @TableField("LASTPRICEID")
    private Long lastpriceid;

    @TableField("LASTUNITPRICE")
    private Double lastunitprice;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Integer goodsstatusid;

    @TableField("STIOFLAG")
    private Integer stioflag;

    @TableField("STIODATE")
    private LocalDateTime stiodate;

    @TableField("LOTGROSS")
    private Double lotgross;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("REQPRINTQUFLAG")
    private Integer reqprintquflag;

    @TableField("HASPRINTQUFLAG")
    private Integer hasprintquflag;

    @TableField("OLDUNITPRICE")
    private Double oldunitprice;

    @TableField("REDCORRECTFLAG")
    private Integer redcorrectflag;

    @TableField("REDSALESID")
    private Long redsalesid;

    @TableField("BATCHFLAG")
    private Integer batchflag;

    @TableField("BATCHPRICE")
    private Double batchprice;

    @TableField("LASTDATE")
    private LocalDateTime lastdate;

    @TableField("OLDDISCOUNT")
    private Double olddiscount;

    @TableField("EXAMPROFIT")
    private Double examprofit;

    @TableField("EXAMPRICE")
    private Double examprice;

    @TableField("PACKQTY")
    private Double packqty;

    @TableField("LOTGROSSRATE")
    private Double lotgrossrate;

    @TableField("LASTGOODSQTY")
    private Double lastgoodsqty;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("OLDSADATE")
    private LocalDateTime oldsadate;

    @TableField("OLDSAQTY")
    private Double oldsaqty;

    @TableField("BIDSETID")
    private Long bidsetid;

    @TableField("USEPACKSIZE")
    private Double usepacksize;

    @TableField("GENFACSINGLEREBFLAG")
    private Integer genfacsinglerebflag;

    @TableField("GENSUPSINGLEREBFLAG")
    private Integer gensupsinglerebflag;

    @TableField("GENFACTOTALREBFLAG")
    private Integer genfactotalrebflag;

    @TableField("GENSUPTOTALREBFLAG")
    private Integer gensuptotalrebflag;

    @TableField("GENFROZXSABKDTLID")
    private Long genfrozxsabkdtlid;

    @TableField("GENFROZXSADTLID")
    private Long genfrozxsadtlid;

    @TableField("ORISTORAGEID")
    private Long oristorageid;

    @TableField("LIMITEDSALEFLAG")
    private Integer limitedsaleflag;

    @TableField("TOTALRECMONEY")
    private Double totalrecmoney;

    @TableField("NOSETTLEMONEY")
    private Double nosettlemoney;

    @TableField("NOSETTLESQTY")
    private Double nosettlesqty;

    @TableField("ADJUSTPRICEDTLID")
    private Double adjustpricedtlid;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("INVNO")
    private String invno;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;

    @TableField("PRINTCOUNTBILL")
    private Long printcountbill;

    @TableField("PRINTDATEBILL")
    private LocalDateTime printdatebill;

    @TableField("PRINTMANIDBILL")
    private Long printmanidbill;

    @TableField("PRINTCOUNTINV")
    private Long printcountinv;

    @TableField("PRINTDATEINV")
    private LocalDateTime printdateinv;

    @TableField("PRINTMANIDINV")
    private Long printmanidinv;

    @TableField("ZX_CXINFO")
    private String zxCxinfo;

    @TableField("ZX_DIFFMONTHS")
    private Long zxDiffmonths;

    @TableField("ZX_REMINDMONTHS")
    private Long zxRemindmonths;

    @TableField("ZX_SPLIT_TYPE")
    private Integer zxSplitType;

    @TableField("ZX_DZJE")
    private Double zxDzje;

    @TableField("ZX_DZBZ")
    private Integer zxDzbz;

    @TableField("ZX_SJZT")
    private Integer zxSjzt;

    @TableField("ZX_DIFPRICE")
    private Double zxDifprice;

    @TableField("ZX_RELETEID")
    private Long zxReleteid;

    @TableField("ZX_BHRELETEID")
    private Long zxBhreleteid;

    @TableField("JD_SOURCE_NUM")
    private Double jdSourceNum;

    @TableField("JD_ORDER_ID")
    private Long jdOrderId;

    @TableField("JD_ORDER_DTL_ID")
    private Long jdOrderDtlId;

    @TableField("JD_TOTAL_MONEY")
    private Double jdTotalMoney;

    @TableField("JD_DISCOUNT")
    private Double jdDiscount;

    @TableField("JD_PAY_MONEY")
    private Double jdPayMoney;

    /**
     * 京东优惠单价
     */
    @ApiModelProperty(value = "京东优惠单价")
    @TableField("JD_UNIT_PRICE")
    private Double jdUnitPrice;

    /**
     * 京东单项金额
     */
    @ApiModelProperty(value = "京东单项金额")
    @TableField("JD_MONEY")
    private Double jdMoney;

    /**
     * 京东订单类型  1，销售单  2  配送单转移过来
     */
    @ApiModelProperty(value = "京东订单类型  1，销售单  2  配送单转移过来")
    @TableField("JD_ORDER_TYPE")
    private Integer jdOrderType;

    @TableField("ZX_KP_MSG")
    private String zxKpMsg;

    /**
     * 1初始值  2 已经回写
     */
    @ApiModelProperty(value = "1初始值  2 已经回写")
    @TableField("ZX_KP_FLAG")
    private Integer zxKpFlag;

    @TableField("JD_PAY_TYPE")
    private Integer jdPayType;

    @TableField("B2B_ORDER_ID")
    private Double b2bOrderId;

    @TableField("B2B_ORDER_DTL_ID")
    private Long b2bOrderDtlId;

    @TableField("B2B_AMOUNT_TOTAL")
    private Double b2bAmountTotal;

    @TableField("B2B_AMOUNT_DISCOUNT")
    private Double b2bAmountDiscount;

    @TableField("B2B_AMOUNT_PAY")
    private Double b2bAmountPay;

    @TableField("B2B_AMOUNT_DELIVERY")
    private Double b2bAmountDelivery;

    @TableField("B2B_NUM")
    private Double b2bNum;

    @TableField("B2B_PRICE")
    private Double b2bPrice;

    @TableField("B2B_PRICE_DISCOUNT")
    private Double b2bPriceDiscount;

    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;


    @TableField("API_ORDER_ID")
    private Long apiOrderId;

    @TableField("API_ORDER_DTL_ID")
    private Long apiOrderDtlId;

    @TableField("API_ORDER_NO")
    private String apiOrderNo;


}
