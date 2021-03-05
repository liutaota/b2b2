package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_SU_CON_DOC")
public class BmsSuConDoc extends Model<BmsSuConDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SUCONID")
    private Long suconid;

    @TableField("SUCONNO")
    private String suconno;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("CONTRACTTYPE")
    private Integer contracttype;

    @TableField("IMPORTFLAG")
    private Integer importflag;

    @TableField("SIGNDATE")
    private LocalDateTime signdate;

    @TableField("SIGNADDRESS")
    private String signaddress;

    @TableField("PAPERNO")
    private String paperno;

    @TableField("SIGNMAN")
    private String signman;

    @TableField("VALIDBEGDATE")
    private LocalDateTime validbegdate;

    @TableField("VALIDENDDATE")
    private LocalDateTime validenddate;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("PREPAY")
    private BigDecimal prepay;

    @TableField("OTHERMONEY")
    private BigDecimal othermoney;

    @TableField("AGENTFLAG")
    private Integer agentflag;

    @TableField("CLAUSE")
    private String clause;

    @TableField("SWITCHMODE")
    private Integer switchmode;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("DESTPOSID")
    private Long destposid;

    @TableField("TRANCOSTRATE")
    private BigDecimal trancostrate;

    @TableField("PICKADDRESS")
    private String pickaddress;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("MEMO")
    private String memo;

    @TableField("FINDATE")
    private LocalDateTime findate;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("PREPAYACC")
    private BigDecimal prepayacc;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SOURCETYPE")
    private Integer sourcetype;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("ADDRESS")
    private String address;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("SUCONNATURE")
    private Integer suconnature;

    @TableField("RECORDNO")
    private Long recordno;

    @TableField("FILEGROUPID")
    private Long filegroupid;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;

    @TableField("STORERID")
    private Long storerid;

    @TableField("PREPRINTCOUNT")
    private Long preprintcount;

    @TableField("PREPRINTMANID")
    private Long preprintmanid;

    @TableField("PREPRINTDATE")
    private LocalDateTime preprintdate;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ZX_PREPAYFLAG")
    private Integer zxPrepayflag;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("ZX_SIGNMANID")
    private Long zxSignmanid;

    @TableField("ZX_SIGNMANNAME")
    private String zxSignmanname;

    @TableField("ZX_SIGNDATE")
    private LocalDateTime zxSigndate;


    @Override
    protected Serializable pkVal() {
        return this.suconid;
    }

}
