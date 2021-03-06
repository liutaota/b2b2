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
@TableName("PUB_IMP_SU_CON")
public class PubImpSuCon extends Model<PubImpSuCon> {

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

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("CREDATE")
    private String credate;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("CONTRACTTYPENAME")
    private String contracttypename;

    @TableField("SUCONNATURENAME")
    private String suconnaturename;

    @TableField("SUCONNO")
    private String suconno;

    @TableField("PAPERNO")
    private String paperno;

    @TableField("RECORDNO")
    private String recordno;

    @TableField("VALIDBEGDATE")
    private String validbegdate;

    @TableField("VALIDENDDATE")
    private String validenddate;

    @TableField("PREPAY")
    private String prepay;

    @TableField("OTHERMONEY")
    private String othermoney;

    @TableField("SIGNDATE")
    private String signdate;

    @TableField("SIGNMAN")
    private String signman;

    @TableField("SIGNADDRESS")
    private String signaddress;

    @TableField("SETTLETYPENAME")
    private String settletypename;

    @TableField("SWITCHMODENAME")
    private String switchmodename;

    @TableField("TRANMETHODNAME")
    private String tranmethodname;

    @TableField("TRANCOSTRATE")
    private String trancostrate;

    @TableField("PICKADDRESS")
    private String pickaddress;

    @TableField("CLAUSE")
    private String clause;

    @TableField("FMNAME")
    private String fmname;

    @TableField("INPUTMANNAME")
    private String inputmanname;

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

    @TableField("SUPPLYERNAME")
    private String supplyername;

    @TableField("GOODSQTY")
    private String goodsqty;

    @TableField("UNITPRICE")
    private String unitprice;

    @TableField("TAXRATE")
    private String taxrate;

    @TableField("TOTAL_LINE")
    private String totalLine;

    @TableField("PAYMETHODNAME")
    private String paymethodname;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("HASPRESENDFLAG")
    private String haspresendflag;

    @TableField("PRESENDINFO")
    private String presendinfo;

    @TableField("DTLMEMO")
    private String dtlmemo;

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

    @TableField("STORERNAME")
    private String storername;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("IMPORTFLAG")
    private Long importflag;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CONTRACTTYPE")
    private Long contracttype;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("PAYMETHOD")
    private Long paymethod;

    @TableField("SUCONNATURE")
    private Long suconnature;

    @TableField("TRANMETHOD")
    private Long tranmethod;

    @TableField("SETTLETYPEID")
    private Long settletypeid;

    @TableField("SWITCHMODE")
    private Long switchmode;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("CONTACTMAN")
    private String contactman;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("STORAGEID")
    private Long storageid;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
