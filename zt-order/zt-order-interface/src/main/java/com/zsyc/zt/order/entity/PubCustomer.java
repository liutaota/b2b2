package com.zsyc.zt.order.entity;

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
@TableName("PUB_CUSTOMER")
public class PubCustomer extends Model<PubCustomer> {

    private static final long serialVersionUID = 1L;

    @TableId("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMOPCODE")
    private String customopcode;

    @TableField("CUSTOMNO")
    private String customno;

    @TableField("CUSTOMPINYIN")
    private String custompinyin;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CITYID")
    private Long cityid;

    @TableField("COUNTRYID")
    private Long countryid;

    @TableField("CUSTOMERTYPE")
    private Integer customertype;

    @TableField("REGISTADD")
    private String registadd;

    @TableField("ADDRESS")
    private String address;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("ZONE")
    private String zone;

    @TableField("LEGALPERSON")
    private String legalperson;

    @TableField("GSPCATEGORYID")
    private Long gspcategoryid;

    @TableField("MEDICODE")
    private String medicode;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("FMID")
    private Long fmid;

    @TableField("INVDEMAND")
    private Integer invdemand;

    @TableField("INVMETHOD")
    private Integer invmethod;

    @TableField("INVMONTH")
    private Integer invmonth;

    @TableField("INVDAY")
    private Integer invday;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("MONOMERPHARMACY")
    private Integer monomerpharmacy;

    @TableField("ZX_ISFIX")
    private Integer zxIsfix;

    @TableField("ZX_ISPRINT")
    private Integer zxIsprint;

    @TableField("ZX_DZ_DATE")
    private Long zxDzDate;

    @TableField("ZX_PH_ORDER1")
    private String zxPhOrder1;

    @TableField("ZX_KPMANID")
    private Long zxKpmanid;

    @TableField("ZX_KPMAN")
    private String zxKpman;

    @TableField("ZX_ACCOUNTID")
    private Long zxAccountid;

    @TableField("ZX_ACCOUNT")
    private String zxAccount;

    @TableField("ZX_QY_SALERID")
    private Long zxQySalerid;

    @TableField("ZX_QY_SALER")
    private String zxQySaler;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("ZX_QY")
    private String zxQy;

    @TableField("ZX_PH_ORDER")
    private Integer zxPhOrder;

    @TableField("ZX_OTCFLAG0")
    private Integer zxOtcflag0;

    @TableField("ZX_OTCFLAG1")
    private Integer zxOtcflag1;

    @TableField("ZX_OTCFLAG2")
    private Integer zxOtcflag2;

    @TableField("ZX_OTCFLAG3")
    private Integer zxOtcflag3;

    @TableField("XSTRANSLINEID")
    private Integer xstranslineid;

    @TableField("XSTRANSNAME")
    private String xstransname;

    @TableField("ZX_MEMO")
    private String zxMemo;

    @TableField("ZX_EMAIL")
    private String zxEmail;

    @TableField("ZX_WARNDAY")
    private Integer zxWarnday;

    @TableField("CUSTOMERTYPE_DOC")
    private Integer customertypeDoc;

    @TableField("ZX_PHONE")
    private Long zxPhone;


    @Override
    protected Serializable pkVal() {
        return this.customid;
    }

}
