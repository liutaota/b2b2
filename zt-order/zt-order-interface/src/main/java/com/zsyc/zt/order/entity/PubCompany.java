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
@TableName("PUB_COMPANY")
public class PubCompany extends Model<PubCompany> {

    private static final long serialVersionUID = 1L;

    @TableId("COMPANYID")
    private Long companyid;

    @TableField("COMPANYOPCODE")
    private String companyopcode;

    @TableField("COMPANYPINYIN")
    private String companypinyin;

    @TableField("COMPANYNAME")
    private String companyname;

    @TableField("PARENTCOMPANYID")
    private Long parentcompanyid;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("SELFFLAG")
    private Integer selfflag;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("REFERENCEDCOUNT")
    private Integer referencedcount;

    @TableField("COMPANYMEMO")
    private String companymemo;

    @TableField("SUPPLYERFLAG")
    private Integer supplyerflag;

    @TableField("CUSTOMFLAG")
    private Integer customflag;

    @TableField("FACTFLAG")
    private Integer factflag;

    @TableField("TRANSPORTFLAG")
    private Integer transportflag;

    @TableField("DEPTSUPPLY")
    private Integer deptsupply;

    @TableField("DEPTSALESFLAG")
    private Integer deptsalesflag;

    @TableField("DEPTSTORERFLAG")
    private Integer deptstorerflag;

    @TableField("BANKFLAG")
    private Integer bankflag;

    @TableField("MANAGERAGE")
    private String managerage;

    @TableField("QUDEPTFLAG")
    private Integer qudeptflag;

    @TableField("ISOTCFLAG")
    private Integer isotcflag;

    @TableField("ISDISTRIBUTORFLAG")
    private Integer isdistributorflag;

    @TableField("ISHOSPOTALFLAG")
    private Integer ishospotalflag;

    @TableField("ISOTHERFLAG")
    private Integer isotherflag;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("PRODUCERFLAG")
    private Integer producerflag;

    @TableField("COMPANYNO")
    private String companyno;

    @TableField("COMPANYSHORTNAME")
    private String companyshortname;

    @TableField("COMPANYTYPE")
    private Integer companytype;

    @TableField("LEGALPERSON")
    private String legalperson;

    @TableField("ZONE")
    private String zone;

    @TableField("COMPANYCREDATE")
    private LocalDateTime companycredate;

    @TableField("PUBFLAG")
    private Integer pubflag;

    @TableField("DEPTPRODUCERFLAG")
    private Integer deptproducerflag;

    @TableField("COPYDATA")
    private Integer copydata;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("MEMBERID")
    private Long memberid;

    @TableField("QUONDAMID")
    private Long quondamid;

    @TableField("LEADERID")
    private Long leaderid;

    @TableField("COUNTRYID")
    private Long countryid;

    @TableField("ISCLINIQUE")
    private Integer isclinique;

    @TableField("ISOTCLINIQUE")
    private Integer isotclinique;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("MEDICODE")
    private String medicode;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;


    @Override
    protected Serializable pkVal() {
        return this.companyid;
    }

}
