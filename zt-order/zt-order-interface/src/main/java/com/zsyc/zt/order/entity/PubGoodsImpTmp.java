package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("PUB_GOODS_IMP_TMP")
public class PubGoodsImpTmp extends Model<PubGoodsImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("GOODSPINYIN")
    private String goodspinyin;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSNO")
    private String goodsno;

    @TableField("STANDARDNO")
    private String standardno;

    @TableField("BARCODE")
    private String barcode;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("FIRSTAPPROVEDOCNO")
    private String firstapprovedocno;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("REGISTDOCNO")
    private String registdocno;

    @TableField("STANDARDTYPE")
    private String standardtype;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("VALIDPERIOD")
    private String validperiod;

    @TableField("RESPPERIOD")
    private String respperiod;

    @TableField("PERIODUNIT")
    private String periodunit;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("SALESTAXRATE")
    private BigDecimal salestaxrate;

    @TableField("FIXPRICETYPE")
    private String fixpricetype;

    @TableField("STORAGECONDITION")
    private String storagecondition;

    @TableField("TRANSCONDITION")
    private String transcondition;

    @TableField("COMBINFLAG")
    private String combinflag;

    @TableField("MINREQGOODSQTY")
    private BigDecimal minreqgoodsqty;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("OTCFLAG")
    private String otcflag;

    @TableField("GMPFLAG")
    private String gmpflag;

    @TableField("PATENTFLAG")
    private String patentflag;

    @TableField("SECURITYFLAG")
    private String securityflag;

    @TableField("CHINESEFLAG")
    private String chineseflag;

    @TableField("MEDICINETYPE")
    private String medicinetype;

    @TableField("IMPORTFLAG")
    private String importflag;

    @TableField("DRUGFLAG")
    private String drugflag;

    @TableField("POISONFLAG")
    private String poisonflag;

    @TableField("BIOMEDFLAG")
    private String biomedflag;

    @TableField("BACTERINFLAG")
    private String bacterinflag;

    @TableField("COMMONFLAG")
    private String commonflag;

    @TableField("MEDICINEFLAG")
    private String medicineflag;

    @TableField("BUSISCOPE")
    private String busiscope;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("SUBGOODSUNIT1")
    private String subgoodsunit1;

    @TableField("SUBGOODSQTY1")
    private BigDecimal subgoodsqty1;

    @TableField("BASEUNITQTY1")
    private BigDecimal baseunitqty1;

    @TableField("UNIT2WEIGHT")
    private BigDecimal unit2weight;

    @TableField("UNIT2VOL")
    private BigDecimal unit2vol;

    @TableField("SUBGOODSUNIT2")
    private String subgoodsunit2;

    @TableField("SUBGOODSQTY2")
    private BigDecimal subgoodsqty2;

    @TableField("BASEUNITQTY2")
    private BigDecimal baseunitqty2;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("FIXPRICETYPEID")
    private Long fixpricetypeid;

    @TableField("MEDICINETYPEID")
    private Long medicinetypeid;

    @TableField("BUSISCOPEID")
    private Long busiscopeid;

    @TableField("STORAGECONDITIONID")
    private Long storageconditionid;

    @TableField("TRANSCONDITIONID")
    private Long transconditionid;

    @TableField("DOCID")
    private Long docid;

    @TableField("UNITISDTL")
    private Integer unitisdtl;

    @TableField("UNITQTY")
    private BigDecimal unitqty;

    @TableField("UNITWEIGHT")
    private BigDecimal unitweight;

    @TableField("UNITVOL")
    private BigDecimal unitvol;

    @TableField("UNIT1")
    private String unit1;

    @TableField("UNIT1ISDTL")
    private Integer unit1isdtl;

    @TableField("UNIT1WEIGHT")
    private BigDecimal unit1weight;

    @TableField("UNIT1VOL")
    private BigDecimal unit1vol;

    @TableField("UNIT2")
    private String unit2;

    @TableField("UNIT2ISDTL")
    private Integer unit2isdtl;

    @TableField("ACCFLAG")
    private String accflag;

    @TableField("DEFAULTAGTFLAG")
    private String defaultagtflag;

    @TableField("ACCFLAGID")
    private Long accflagid;

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

    @TableField("DEFAULTAGTFLAGID")
    private Integer defaultagtflagid;

    @TableField("VARIETYNAME")
    private String varietyname;

    @TableField("VARIETYDESCNAME")
    private String varietydescname;

    @TableField("VARIETYID")
    private Long varietyid;

    @TableField("VARIETYDESCID")
    private Long varietydescid;

    @TableField("GSPFLAG")
    private String gspflag;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}
