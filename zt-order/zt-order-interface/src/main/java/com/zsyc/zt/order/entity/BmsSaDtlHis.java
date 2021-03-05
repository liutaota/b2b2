package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BMS_SA_DTL_HIS")
public class BmsSaDtlHis extends Model<BmsSaDtlHis> {

    private static final long serialVersionUID = 1L;

    @TableField("SALESDTLID")
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
    private BigDecimal taxrate;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("DISCOUNT")
    private BigDecimal discount;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("LOWERFLAG")
    private Integer lowerflag;

    @TableField("TIMEPRICEID")
    private Long timepriceid;

    @TableField("TIMEPRICE")
    private BigDecimal timeprice;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("SETTLEQTY")
    private BigDecimal settleqty;

    @TableField("SETTLEMONEY")
    private BigDecimal settlemoney;

    @TableField("SETTLEFLAG")
    private Integer settleflag;

    @TableField("WHOLESALEPRICE")
    private BigDecimal wholesaleprice;

    @TableField("RESALEPRICE")
    private BigDecimal resaleprice;

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
    private BigDecimal notaxmoney;

    @TableField("LASTPRICEID")
    private Long lastpriceid;

    @TableField("LASTUNITPRICE")
    private BigDecimal lastunitprice;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("STIOFLAG")
    private Integer stioflag;

    @TableField("STIODATE")
    private LocalDateTime stiodate;

    @TableField("LOTGROSS")
    private BigDecimal lotgross;

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
    private BigDecimal oldunitprice;

    @TableField("REDCORRECTFLAG")
    private Integer redcorrectflag;

    @TableField("REDSALESID")
    private Long redsalesid;

    @TableField("BATCHFLAG")
    private Integer batchflag;

    @TableField("BATCHPRICE")
    private BigDecimal batchprice;

    @TableField("LASTDATE")
    private LocalDateTime lastdate;

    @TableField("OLDDISCOUNT")
    private BigDecimal olddiscount;

    @TableField("EXAMPROFIT")
    private BigDecimal examprofit;

    @TableField("EXAMPRICE")
    private BigDecimal examprice;

    @TableField("PACKQTY")
    private BigDecimal packqty;

    @TableField("LOTGROSSRATE")
    private BigDecimal lotgrossrate;

    @TableField("LASTGOODSQTY")
    private BigDecimal lastgoodsqty;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("OLDSADATE")
    private LocalDateTime oldsadate;

    @TableField("OLDSAQTY")
    private BigDecimal oldsaqty;

    @TableField("BIDSETID")
    private Long bidsetid;

    @TableField("USEPACKSIZE")
    private BigDecimal usepacksize;

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
    private BigDecimal totalrecmoney;

    @TableField("NOSETTLEMONEY")
    private BigDecimal nosettlemoney;

    @TableField("NOSETTLESQTY")
    private BigDecimal nosettlesqty;

    @TableField("ADJUSTPRICEDTLID")
    private BigDecimal adjustpricedtlid;

    @TableField("IMPDTLID")
    private Long impdtlid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
