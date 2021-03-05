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
@TableName("BMS_SA_CON_DOC_HIS")
public class BmsSaConDocHis extends Model<BmsSaConDocHis> {

    private static final long serialVersionUID = 1L;

    @TableField("CONID")
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

    @TableField("SA_MODE")
    private Integer saMode;

    @TableField("FIX_MODE")
    private Integer fixMode;

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
    private BigDecimal total;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("MEMO")
    private String memo;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREDITFLAG")
    private Integer creditflag;

    @TableField("CREDIT")
    private BigDecimal credit;

    @TableField("RECMONEY")
    private BigDecimal recmoney;

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

    @TableField("RECEIPTMANID")
    private Long receiptmanid;

    @TableField("RECEIPTDATE")
    private LocalDateTime receiptdate;

    @TableField("RECEIPTSTATUS")
    private Long receiptstatus;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
