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
@TableName("PUB_CUSTOMER_IMP_TMP")
public class PubCustomerImpTmp extends Model<PubCustomerImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("CUSTOMID")
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

    @TableField("DEFAULTPRICEID")
    private Long defaultpriceid;

    @TableField("CREDITFLAG")
    private Integer creditflag;

    @TableField("CREDIT")
    private BigDecimal credit;

    @TableField("CREDITDAYSFLAG")
    private Integer creditdaysflag;

    @TableField("CREDITDAYS")
    private Long creditdays;

    @TableField("LOWPRICEFLAG")
    private Integer lowpriceflag;

    @TableField("TRANPRIORITY")
    private String tranpriority;

    @TableField("DELIVERMETHOD")
    private String delivermethod;

    @TableField("DEFAULTTRANMETHODID")
    private Integer defaulttranmethodid;

    @TableField("DEFAULTTRANMETH")
    private String defaulttranmeth;

    @TableField("DELIVERMETHODID")
    private Long delivermethodid;

    @TableField("DEFAULTINVOICETYPEID")
    private Integer defaultinvoicetypeid;

    @TableField("DEFAULTINVOICETYPE")
    private String defaultinvoicetype;

    @TableField("RECMONEY")
    private BigDecimal recmoney;

    @TableField("RECDATE")
    private LocalDateTime recdate;

    @TableField("CITY")
    private String city;

    @TableField("COUNTRY")
    private String country;

    @TableField("CITYID")
    private Long cityid;

    @TableField("COUNTRYID")
    private Long countryid;

    @TableField("CUSTOMERTYPE")
    private String customertype;

    @TableField("CUSTOMERTYPEID")
    private Long customertypeid;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("DEFAULTPRICE")
    private String defaultprice;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("ZXCOLNUM1")
    private String zxcolnum1;

    @TableField("ZXCOLNUM2")
    private String zxcolnum2;

    @TableField("ZXCOLNUM3")
    private String zxcolnum3;

    @TableField("ZXCOLNUM4")
    private String zxcolnum4;

    @TableField("ZXCOLNUM5")
    private String zxcolnum5;

    @TableField("ZXCOLNUM6")
    private String zxcolnum6;

    @TableField("ZXCOLNUM7")
    private String zxcolnum7;

    @TableField("ZXCOLNUM8")
    private String zxcolnum8;

    @TableField("ZXCOLNUM9")
    private String zxcolnum9;

    @TableField("ZXCOLNUM10")
    private String zxcolnum10;

    @TableField("DOCID")
    private Long docid;

    @TableField("PAYWAY")
    private String payway;

    @TableField("PAYWAYID")
    private Integer paywayid;

    @TableField("REGISTADD")
    private String registadd;

    @TableField("ADDRESS")
    private String address;

    @TableField("GSPFLAG")
    private String gspflag;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}
