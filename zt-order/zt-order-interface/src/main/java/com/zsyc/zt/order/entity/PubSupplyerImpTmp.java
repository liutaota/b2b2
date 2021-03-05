package com.zsyc.zt.order.entity;

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
@TableName("PUB_SUPPLYER_IMP_TMP")
public class PubSupplyerImpTmp extends Model<PubSupplyerImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYOPCODE")
    private String supplyopcode;

    @TableField("SUPPLYNO")
    private String supplyno;

    @TableField("SUPPLYPINYIN")
    private String supplypinyin;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("IMPORTFLAG")
    private Integer importflag;

    @TableField("CITYID")
    private Long cityid;

    @TableField("COUNTRYID")
    private Long countryid;

    @TableField("CITY")
    private String city;

    @TableField("COUNTRY")
    private String country;

    @TableField("ADDRESS")
    private String address;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("REGISTADD")
    private String registadd;

    @TableField("DOCID")
    private Long docid;

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

    @TableField("GSPFLAG")
    private String gspflag;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}
