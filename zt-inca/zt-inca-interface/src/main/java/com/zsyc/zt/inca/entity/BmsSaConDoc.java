package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("BMS_SA_CON_DOC")
@KeySequence("BMS_SA_CON_DOC_SEQ")
public class BmsSaConDoc extends Model<BmsSaConDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("CONID")
    private Long conid;

    @TableField("CONNO")
    private String conno;

    @TableField("CONTYPE")
    private Integer contype;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("SIGNDATE")
    private LocalDateTime signdate;

    @TableField("SIGNADDRESS")
    private String signaddress;

    @TableField("SIGNMANID")
    private Long signmanid;

    @TableField("VALIDBEGINDATE")
    private LocalDateTime validbegindate;

    @TableField("VALIDENDDATE")
    private LocalDateTime validenddate;

    @TableField("SUMMARY")
    private String summary;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("DELIVERMETHOD")
    private Integer delivermethod;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("TARGETPOSID")
    private Long targetposid;

    @TableField("URGENTFLAG")
    private Integer urgentflag;

    @TableField("TRANPRIORITY")
    private Integer tranpriority;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("TOTAL")
    private Double total;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("MEMO")
    private String memo;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private Double exchange;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREDITFLAG")
    private Integer creditflag;

    @TableField("CREDIT")
    private Double credit;

    @TableField("RECMONEY")
    private Double recmoney;

    @TableField("CREDITDAYSFLAG")
    private Integer creditdaysflag;

    @TableField("CREDITDAYS")
    private Long creditdays;

    @TableField("RECDATE")
    private LocalDateTime recdate;

    @TableField("LOWPRICEFLAG")
    private Integer lowpriceflag;

    @TableField("APPROVEFLAG")
    private Integer approveflag;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;

    @TableField("APPROVEMEMO")
    private String approvememo;

    @TableField("CONFIRMANID")
    private Long confirmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("ASSESSDATE")
    private LocalDateTime assessdate;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CNSCOMMITFLAG")
    private Integer cnscommitflag;

    @TableField("FINISHFLAG")
    private Integer finishflag;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;

    @TableField("PLANINVDATE")
    private LocalDateTime planinvdate;

    @TableField("INVDEMAND")
    private Integer invdemand;

    @TableField("PREDESPDATE")
    private LocalDateTime predespdate;

    @TableField("PAYFLAG")
    private Integer payflag;

    @TableField("WEBTOTALPRICE")
    private Double webtotalprice;

    @TableField("ACCOUNT")
    private String account;

    @TableField("PAYDATE")
    private LocalDateTime paydate;

    @TableField("WEBFLAG")
    private Integer webflag;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("TWOINVFLAG")
    private Integer twoinvflag;

    @TableField("TWOINVMETHOD")
    private Integer twoinvmethod;

    @TableField("SALESID")
    private Long salesid;


    @Override
    protected Serializable pkVal() {
        return this.conid;
    }

}
