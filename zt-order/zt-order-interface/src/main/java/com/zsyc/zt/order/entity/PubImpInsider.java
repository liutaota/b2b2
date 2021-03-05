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
@TableName("PUB_IMP_INSIDER")
public class PubImpInsider extends Model<PubImpInsider> {

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

    @TableField("CARDTYPENAME")
    private String cardtypename;

    @TableField("INSIDERCARDNO")
    private String insidercardno;

    @TableField("INSIDERNAME")
    private String insidername;

    @TableField("OPCODE")
    private String opcode;

    @TableField("RSACUSTOMERNO")
    private String rsacustomerno;

    @TableField("SEX")
    private String sex;

    @TableField("BIRTHDATE")
    private String birthdate;

    @TableField("LUNARBIRTHDAY")
    private String lunarbirthday;

    @TableField("IDCARD")
    private String idcard;

    @TableField("BLOODTYPENAME")
    private String bloodtypename;

    @TableField("MOBILE")
    private String mobile;

    @TableField("HOMEPHONE")
    private String homephone;

    @TableField("OFFICEPHONE")
    private String officephone;

    @TableField("EMAILADDRESS")
    private String emailaddress;

    @TableField("MAILADDRESS")
    private String mailaddress;

    @TableField("ZIPCODE")
    private String zipcode;

    @TableField("PLACEPOINTNAME")
    private String placepointname;

    @TableField("CREDATE")
    private String credate;

    @TableField("INVALIDDATE")
    private String invaliddate;

    @TableField("DEALMANNAME")
    private String dealmanname;

    @TableField("CITYNAME")
    private String cityname;

    @TableField("COUNTRYNAME")
    private String countryname;

    @TableField("INITINTEGRAL")
    private String initintegral;

    @TableField("INITADDINTEGRAL")
    private String initaddintegral;

    @TableField("INITMONEY")
    private String initmoney;

    @TableField("LASTCONSUMDATE")
    private String lastconsumdate;

    @TableField("ELDERNAME")
    private String eldername;

    @TableField("CHLLDNAME")
    private String chlldname;

    @TableField("CARDIOPATHY")
    private String cardiopathy;

    @TableField("HYPERTENSION")
    private String hypertension;

    @TableField("HIGHBLOOD")
    private String highblood;

    @TableField("BONEILL")
    private String boneill;

    @TableField("LIVERILL")
    private String liverill;

    @TableField("STOMACTHICILL")
    private String stomacthicill;

    @TableField("BLOODILL")
    private String bloodill;

    @TableField("DIABETES")
    private String diabetes;

    @TableField("NIAOILL")
    private String niaoill;

    @TableField("ASPIRATORYILL")
    private String aspiratoryill;

    @TableField("WOMENFOLKILL")
    private String womenfolkill;

    @TableField("OTHERILL")
    private String otherill;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUSNAME")
    private String usestatusname;

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

    @TableField("INSCARDTYPEID")
    private Long inscardtypeid;

    @TableField("BLOODTYPE")
    private Long bloodtype;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("DEALMANID")
    private Long dealmanid;

    @TableField("CITYID")
    private Long cityid;

    @TableField("COUNTRYID")
    private Long countryid;

    @TableField("ELDER")
    private Long elder;

    @TableField("CHLLD")
    private Long chlld;

    @TableField("USESTATUS")
    private Long usestatus;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
