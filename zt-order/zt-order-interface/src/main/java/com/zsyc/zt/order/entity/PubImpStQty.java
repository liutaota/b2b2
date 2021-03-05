package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("PUB_IMP_ST_QTY")
public class PubImpStQty extends Model<PubImpStQty> {

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

    @TableField("STORAGENAME")
    private String storagename;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("PACKNAME")
    private String packname;

    @TableField("PACKSIZE")
    private String packsize;

    @TableField("GOODSQTY")
    private String goodsqty;

    @TableField("LOTNO")
    private String lotno;

    @TableField("KILLLOTNO")
    private String killlotno;

    @TableField("PRODDATE")
    private String proddate;

    @TableField("INVALIDDATE")
    private String invaliddate;

    @TableField("KILLDATE")
    private String killdate;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("REGISTDOCNO")
    private String registdocno;

    @TableField("LOTFACTORYNAME")
    private String lotfactoryname;

    @TableField("PRINTSET")
    private String printset;

    @TableField("POSNO")
    private String posno;

    @TableField("GOODSSTATUS")
    private String goodsstatus;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("SUPPLYERNAME")
    private String supplyername;

    @TableField("UNITPRICE")
    private String unitprice;

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

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("SUDATE")
    private String sudate;

    @TableField("LOTFLAG")
    private String lotflag;

    @TableField("FACTORYFLAG")
    private String factoryflag;

    @TableField("PRODAREAFLAG")
    private String prodareaflag;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
