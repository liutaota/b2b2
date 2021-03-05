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
@TableName("BMS_SU_DTL_HIS")
public class BmsSuDtlHis extends Model<BmsSuDtlHis> {

    private static final long serialVersionUID = 1L;

    @TableField("SUDOCID")
    private Long sudocid;

    @TableField("SUDOCDTLID")
    private Long sudocdtlid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("TAXRATE")
    private BigDecimal taxrate;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("ORGQTY")
    private BigDecimal orgqty;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("COSTINGMONEY")
    private BigDecimal costingmoney;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("PLNPAYDATE")
    private LocalDateTime plnpaydate;

    @TableField("BACKWHYID")
    private Integer backwhyid;

    @TableField("BACKPROID")
    private Integer backproid;

    @TableField("WHOLESALEPRICE")
    private BigDecimal wholesaleprice;

    @TableField("RESALEPRICE")
    private BigDecimal resaleprice;

    @TableField("MEMO")
    private String memo;

    @TableField("SETTLEFLAG")
    private Integer settleflag;

    @TableField("RECST")
    private Integer recst;

    @TableField("RECSTDATE")
    private LocalDateTime recstdate;

    @TableField("SETTLEDQTY")
    private BigDecimal settledqty;

    @TableField("SETTLEDMONEY")
    private BigDecimal settledmoney;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDTIME")
    private LocalDateTime invalidtime;

    @TableField("ORGDOCNO")
    private String orgdocno;

    @TableField("CORRECTFLAG")
    private Integer correctflag;

    @TableField("LASTPRICE")
    private BigDecimal lastprice;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("SKCERTID")
    private Long skcertid;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("GENTIMELYREBATE")
    private Integer gentimelyrebate;

    @TableField("LOWESTPRICE")
    private BigDecimal lowestprice;

    @TableField("SUPPLYLASTPRICE")
    private BigDecimal supplylastprice;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("CONSIGNERID")
    private Long consignerid;

    @TableField("CONPRICE")
    private BigDecimal conprice;

    @TableField("LIMITID")
    private Long limitid;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("HASPRESENDFLAG")
    private Integer haspresendflag;

    @TableField("PRESENDINFO")
    private String presendinfo;

    @TableField("LIMITCUSTOMSETID")
    private Long limitcustomsetid;

    @TableField("OLDSUDTLID")
    private Long oldsudtlid;

    @TableField("SALESDTLID")
    private Long salesdtlid;

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

    @TableField("TOTAL_LINE_NAT")
    private BigDecimal totalLineNat;

    @TableField("COSTINGMONEY_NAT")
    private BigDecimal costingmoneyNat;

    @TableField("TOTALPAYMONEY")
    private BigDecimal totalpaymoney;

    @TableField("TOTALPOSTMONEY")
    private BigDecimal totalpostmoney;

    @TableField("BANNEDCUSTOMERID")
    private Long bannedcustomerid;

    @TableField("BANNEDCUSTOMERSETID")
    private Long bannedcustomersetid;

    @TableField("OLDISSETTLE")
    private Integer oldissettle;

    @TableField("OLDISPAY")
    private Integer oldispay;

    @TableField("ADJUSTPRICEDOCID")
    private BigDecimal adjustpricedocid;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
