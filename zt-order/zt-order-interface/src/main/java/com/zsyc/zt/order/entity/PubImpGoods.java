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
@TableName("PUB_IMP_GOODS")
public class PubImpGoods extends Model<PubImpGoods> {

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

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("OPCODE")
    private String opcode;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("FACTORYOPCODE")
    private String factoryopcode;

    @TableField("FACTORYPINYIN")
    private String factorypinyin;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("FACTORYNO")
    private String factoryno;

    @TableField("FACTORYMEMO")
    private String factorymemo;

    @TableField("BARCODE")
    private String barcode;

    @TableField("STANDARDNO")
    private String standardno;

    @TableField("TRADEMARK")
    private String trademark;

    @TableField("GOODSSHORTNAME")
    private String goodsshortname;

    @TableField("GOODSENGNAME")
    private String goodsengname;

    @TableField("GOOODSFORMALNAME")
    private String gooodsformalname;

    @TableField("GOODSPINYIN")
    private String goodspinyin;

    @TableField("ALONEPACKFLAG")
    private String alonepackflag;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("UNITWEIGHT")
    private String unitweight;

    @TableField("UNITLENGTH")
    private String unitlength;

    @TableField("UNITWIDTH")
    private String unitwidth;

    @TableField("UNITHEIGHT")
    private String unitheight;

    @TableField("UNITVOL")
    private String unitvol;

    @TableField("MIDPACKNAME")
    private String midpackname;

    @TableField("MIDPACKSIZE")
    private String midpacksize;

    @TableField("MIDPACKWEIGHT")
    private String midpackweight;

    @TableField("MIDPACKLENGTH")
    private String midpacklength;

    @TableField("MIDPACKWIDTH")
    private String midpackwidth;

    @TableField("MIDPACKHEIGHT")
    private String midpackheight;

    @TableField("MIDPACKVOL")
    private String midpackvol;

    @TableField("PACKNAME")
    private String packname;

    @TableField("PACKSIZE")
    private String packsize;

    @TableField("PACKWEIGHT")
    private String packweight;

    @TableField("PACKLENGTH")
    private String packlength;

    @TableField("PACKWIDTH")
    private String packwidth;

    @TableField("PACKHEIGHT")
    private String packheight;

    @TableField("PACKVOL")
    private String packvol;

    @TableField("STORAGECONDITIONNAME")
    private String storageconditionname;

    @TableField("TRANSCONDITIONNAME")
    private String transconditionname;

    @TableField("VALIDPERIOD")
    private String validperiod;

    @TableField("PERIODUNIT")
    private String periodunit;

    @TableField("RESPPERIOD")
    private String respperiod;

    @TableField("SUPPLYTAXRATE")
    private String supplytaxrate;

    @TableField("SALESTAXRATE")
    private String salestaxrate;

    @TableField("ACCFLAGNAME")
    private String accflagname;

    @TableField("FUNCTION")
    private String function;

    @TableField("GSPFLAG")
    private String gspflag;

    @TableField("GSPCATEGORYNAME")
    private String gspcategoryname;

    @TableField("STANDARDTYPE")
    private String standardtype;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("REGISTDOCNO")
    private String registdocno;

    @TableField("MEDICINETYPENAME")
    private String medicinetypename;

    @TableField("BUSISCOPENAME")
    private String busiscopename;

    @TableField("MEDICINESORTNAME")
    private String medicinesortname;

    @TableField("OTCFLAGNAME")
    private String otcflagname;

    @TableField("SECURITYFLAGNAME")
    private String securityflagname;

    @TableField("SPECIALDRUGNAME")
    private String specialdrugname;

    @TableField("SPECIALCTRLNAME")
    private String specialctrlname;

    @TableField("IMPORTFLAG")
    private String importflag;

    @TableField("ECODEFLAG")
    private String ecodeflag;

    @TableField("GOODSNO")
    private String goodsno;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private String credate;

    @TableField("INPUTMANNAME")
    private String inputmanname;

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

    @TableField("USESTATUSNAME")
    private String usestatusname;

    @TableField("COLDFLAG")
    private String coldflag;

    @TableField("TEMPERATUREDOWN")
    private String temperaturedown;

    @TableField("TEMPERATUREUP")
    private String temperatureup;

    @TableField("TRANSPORTTIME")
    private String transporttime;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("STORAGECONDITION")
    private Integer storagecondition;

    @TableField("TRANSCONDITION")
    private Integer transcondition;

    @TableField("ACCFLAG")
    private Integer accflag;

    @TableField("MEDICINETYPE")
    private Long medicinetype;

    @TableField("BUSISCOPE")
    private Long busiscope;

    @TableField("MEDICINESORT")
    private Integer medicinesort;

    @TableField("OTCFLAG")
    private Integer otcflag;

    @TableField("SECURITYFLAG")
    private Integer securityflag;

    @TableField("SPECIALDRUG")
    private Integer specialdrug;

    @TableField("SPECIALCTRL")
    private Integer specialctrl;

    @TableField("GSPCATEGORYID")
    private Long gspcategoryid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("GOODSUNITID")
    private Long goodsunitid;

    @TableField("MIDPACKID")
    private Long midpackid;

    @TableField("PACKID")
    private Long packid;

    @TableField("RETAILNOLOT")
    private String retailnolot;

    @TableField("AGAINCHKFLAG")
    private String againchkflag;

    @TableField("LOTFLAG")
    private String lotflag;

    @TableField("FACTORYFLAG")
    private String factoryflag;

    @TableField("PRODAREAFLAG")
    private String prodareaflag;

    @TableField("TREATMENTVARIETY")
    private Integer treatmentvariety;

    @TableField("USEDAYS")
    private Integer usedays;

    @TableField("USETABOO")
    private String usetaboo;

    @TableField("FOCUSDRUGS")
    private Integer focusdrugs;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
