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
@TableName("PUB_IMP_SA_REC")
public class PubImpSaRec extends Model<PubImpSaRec> {

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

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("SALERNAME")
    private String salername;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("SETTLETYPENAME")
    private String settletypename;

    @TableField("TOTAL")
    private String total;

    @TableField("BANKDOCNO")
    private String bankdocno;

    @TableField("BANKNAME")
    private String bankname;

    @TableField("FMNAME")
    private String fmname;

    @TableField("CONFIRMDATE")
    private String confirmdate;

    @TableField("CONFIRMMANNAME")
    private String confirmmanname;

    @TableField("INPUTMANNAME")
    private String inputmanname;

    @TableField("DOCMEMO")
    private String docmemo;

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

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("SETTLETYPEID")
    private Long settletypeid;

    @TableField("BANKID")
    private Long bankid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("CONTACTMAN")
    private String contactman;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
