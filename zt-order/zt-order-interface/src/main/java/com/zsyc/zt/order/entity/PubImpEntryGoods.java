package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("PUB_IMP_ENTRY_GOODS")
public class PubImpEntryGoods extends Model<PubImpEntryGoods> {

    private static final long serialVersionUID = 1L;

    @TableField("DOCID")
    private Long docid;

    @TableId("DTLID")
    private Long dtlid;

    @TableField("IMPSTATUS")
    private Integer impstatus;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("NEWID")
    private Long newid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("GOODSINVNAME")
    private String goodsinvname;

    @TableField("SUPPLYTAXRATE")
    private String supplytaxrate;

    @TableField("SALESTAXRATE")
    private String salestaxrate;

    @TableField("LEASTSALEQTY")
    private String leastsaleqty;

    @TableField("SUPPLYERNAME")
    private String supplyername;

    @TableField("FIRSTSUDATE")
    private String firstsudate;

    @TableField("FIRSTAPPROVEDATE")
    private String firstapprovedate;

    @TableField("ZXCOLUMN01")
    private String zxcolumn01;

    @TableField("ZXCOLUMN02")
    private String zxcolumn02;

    @TableField("ZXCOLUMN03")
    private String zxcolumn03;

    @TableField("ZXCOLUMN04")
    private String zxcolumn04;

    @TableField("ZXCOLUMN05")
    private String zxcolumn05;

    @TableField("ZXCOLUMN06")
    private String zxcolumn06;

    @TableField("ZXCOLUMN07")
    private String zxcolumn07;

    @TableField("ZXCOLUMN08")
    private String zxcolumn08;

    @TableField("ZXCOLUMN09")
    private String zxcolumn09;

    @TableField("ZXCOLUMN10")
    private String zxcolumn10;

    @TableField("ZXCOLUMN11")
    private String zxcolumn11;

    @TableField("ZXCOLUMN12")
    private String zxcolumn12;

    @TableField("ZXCOLUMN13")
    private String zxcolumn13;

    @TableField("ZXCOLUMN14")
    private String zxcolumn14;

    @TableField("ZXCOLUMN15")
    private String zxcolumn15;

    @TableField("ZXCOLUMN16")
    private String zxcolumn16;

    @TableField("ZXCOLUMN17")
    private String zxcolumn17;

    @TableField("ZXCOLUMN18")
    private String zxcolumn18;

    @TableField("ZXCOLUMN19")
    private String zxcolumn19;

    @TableField("ZXCOLUMN20")
    private String zxcolumn20;

    @TableField("USEFLAG")
    private String useflag;

    @TableField("PURBEFOR")
    private String purbefor;

    @TableField("PROBEFOR")
    private String probefor;

    @TableField("SUMBEFOR")
    private String sumbefor;

    @TableField("LEVELNUM")
    private String levelnum;

    @TableField("PLANSTRA")
    private String planstra;

    @TableField("AVERRAGETYPE")
    private String averragetype;

    @TableField("AVERRAGEINT")
    private String averrageint;

    @TableField("PURCHASENUM")
    private String purchasenum;

    @TableField("PRODUCENUM")
    private String producenum;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("DOUBLERG")
    private String doublerg;

    @TableField("DOUBLEPICK")
    private String doublepick;

    @TableField("DOUBLECHECK")
    private String doublecheck;

    @TableField("BANCASH")
    private String bancash;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
