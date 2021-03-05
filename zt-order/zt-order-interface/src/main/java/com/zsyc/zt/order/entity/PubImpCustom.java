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
@TableName("PUB_IMP_CUSTOM")
public class PubImpCustom extends Model<PubImpCustom> {

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

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("CUSTOMOPCODE")
    private String customopcode;

    @TableField("CUSTOMPINYIN")
    private String custompinyin;

    @TableField("CUSTOMNO")
    private String customno;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("GSPFLAGNAME")
    private String gspflagname;

    @TableField("GSPCATEGORYNAME")
    private String gspcategoryname;

    @TableField("MEDICODE")
    private String medicode;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("ZONE")
    private String zone;

    @TableField("LEGALPERSON")
    private String legalperson;

    @TableField("CUSTOMERTYPENAME")
    private String customertypename;

    @TableField("CITYNAME")
    private String cityname;

    @TableField("COUNTRYNAME")
    private String countryname;

    @TableField("REGISTADD")
    private String registadd;

    @TableField("ADDRESS")
    private String address;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUSNAME")
    private String usestatusname;

    @TableField("CREDATE")
    private String credate;

    @TableField("INPUTMANNAME")
    private String inputmanname;

    @TableField("FMNAME")
    private String fmname;

    @TableField("INVCOMPANYNAME")
    private String invcompanyname;

    @TableField("INVTAXNO")
    private String invtaxno;

    @TableField("INVADDRESS")
    private String invaddress;

    @TableField("INVTELEPHONE")
    private String invtelephone;

    @TableField("INVBANKNAME")
    private String invbankname;

    @TableField("INVBANKNO")
    private String invbankno;

    @TableField("INVMEMO")
    private String invmemo;

    @TableField("TRANPOSNAME1")
    private String tranposname1;

    @TableField("TRANPOSOPCODE1")
    private String tranposopcode1;

    @TableField("TRANPOSORDER1")
    private String tranposorder1;

    @TableField("TRANPOSADDRESS1")
    private String tranposaddress1;

    @TableField("TRANPOSTELEPHONE1")
    private String tranpostelephone1;

    @TableField("TRANPOSFAX1")
    private String tranposfax1;

    @TableField("TRANPOSPOSTCODE1")
    private String tranpospostcode1;

    @TableField("TRANPOSCONTRACTMAN1")
    private String tranposcontractman1;

    @TableField("TRANPOSDISTANCE1")
    private String tranposdistance1;

    @TableField("TRANSLINENAME1")
    private String translinename1;

    @TableField("TRANSORTNO1")
    private String transortno1;

    @TableField("TRANPOSNAME2")
    private String tranposname2;

    @TableField("TRANPOSOPCODE2")
    private String tranposopcode2;

    @TableField("TRANPOSORDER2")
    private String tranposorder2;

    @TableField("TRANPOSADDRESS2")
    private String tranposaddress2;

    @TableField("TRANPOSTELEPHONE2")
    private String tranpostelephone2;

    @TableField("TRANPOSFAX2")
    private String tranposfax2;

    @TableField("TRANPOSPOSTCODE2")
    private String tranpospostcode2;

    @TableField("TRANPOSCONTRACTMAN2")
    private String tranposcontractman2;

    @TableField("TRANPOSDISTANCE2")
    private String tranposdistance2;

    @TableField("TRANSLINENAME2")
    private String translinename2;

    @TableField("TRANSORTNO2")
    private String transortno2;

    @TableField("TRANPOSNAME3")
    private String tranposname3;

    @TableField("TRANPOSOPCODE3")
    private String tranposopcode3;

    @TableField("TRANPOSORDER3")
    private String tranposorder3;

    @TableField("TRANPOSADDRESS3")
    private String tranposaddress3;

    @TableField("TRANPOSTELEPHONE3")
    private String tranpostelephone3;

    @TableField("TRANPOSFAX3")
    private String tranposfax3;

    @TableField("TRANPOSPOSTCODE3")
    private String tranpospostcode3;

    @TableField("TRANPOSCONTRACTMAN3")
    private String tranposcontractman3;

    @TableField("TRANPOSDISTANCE3")
    private String tranposdistance3;

    @TableField("TRANSLINENAME3")
    private String translinename3;

    @TableField("TRANSORTNO3")
    private String transortno3;

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

    @TableField("INVTYPENAME")
    private String invtypename;

    @TableField("CATEGORYID")
    private Long categoryid;

    @TableField("CITYID")
    private Long cityid;

    @TableField("COUNTRYID")
    private Long countryid;

    @TableField("CUSTOMERTYPE")
    private Integer customertype;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("TRANSLINEID1")
    private Long translineid1;

    @TableField("TRANSLINEID2")
    private Long translineid2;

    @TableField("TRANSLINEID3")
    private Long translineid3;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("FMID")
    private Long fmid;

    @TableField("INVDEMAND")
    private String invdemand;

    @TableField("INVMETHOD")
    private String invmethod;

    @TableField("INVMONTH")
    private String invmonth;

    @TableField("INVDAY")
    private String invday;

    @TableField("INVDEMANDID")
    private Integer invdemandid;

    @TableField("INVMETHODID")
    private Integer invmethodid;

    @TableField("INVACCNO")
    private String invaccno;

    @TableField("MONOMERPHARMACY")
    private String monomerpharmacy;

    @TableField("INVTYPE")
    private Integer invtype;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
