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
@TableName("BMS_GROUP_SA_DOC")
public class BmsGroupSaDoc extends Model<BmsGroupSaDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SALESID")
    private Long salesid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

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

    @TableField("CONFIRMANID")
    private Long confirmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("SATYPEID")
    private Integer satypeid;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("STSETID")
    private Long stsetid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

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

    @TableField("ASSESSDATE")
    private LocalDateTime assessdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("RECEIVEFLAG")
    private Integer receiveflag;

    @TableField("RECEIVEMANID")
    private Long receivemanid;

    @TableField("RECEIVEDATE")
    private LocalDateTime receivedate;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SACERTID")
    private Long sacertid;

    @TableField("RGID")
    private Long rgid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("TOEDISTATUS")
    private Integer toedistatus;

    @TableField("INVALIDSTATUS")
    private Integer invalidstatus;

    @TableField("SETDAYS")
    private Long setdays;

    @TableField("AWARDRATE")
    private BigDecimal awardrate;

    @TableField("PUNISHRATE")
    private BigDecimal punishrate;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;

    @TableField("APENTRYID")
    private Long apentryid;

    @TableField("PLANINVDATE")
    private LocalDateTime planinvdate;

    @TableField("INVDEMAND")
    private Integer invdemand;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("TWOINVFLAG")
    private Integer twoinvflag;

    @TableField("TWOINVMETHOD")
    private Integer twoinvmethod;


    @Override
    protected Serializable pkVal() {
        return this.salesid;
    }

}
