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
@TableName("PUB_GOODS_TMP")
public class PubGoodsTmp extends Model<PubGoodsTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSID")
    private Long goodsid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("GOODSPINYIN")
    private String goodspinyin;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSENGNAME")
    private String goodsengname;

    @TableField("GOODSINVNAME")
    private String goodsinvname;

    @TableField("GOODSSHORTNAME")
    private String goodsshortname;

    @TableField("GOODSFORMALNAME")
    private String goodsformalname;

    @TableField("GOODSFORMALPY")
    private String goodsformalpy;

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

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("VALIDPERIOD")
    private Integer validperiod;

    @TableField("RESPPERIOD")
    private Integer respperiod;

    @TableField("PERIODUNIT")
    private String periodunit;

    @TableField("TRADEMARK")
    private String trademark;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("SALESTAXRATE")
    private BigDecimal salestaxrate;

    @TableField("FIXPRICETYPE")
    private Integer fixpricetype;

    @TableField("ACCFLAG")
    private Integer accflag;

    @TableField("DEFAULTAGTFLAG")
    private Integer defaultagtflag;

    @TableField("STORAGECONDITION")
    private Integer storagecondition;

    @TableField("TRANSCONDITION")
    private Integer transcondition;

    @TableField("COMBINFLAG")
    private Integer combinflag;

    @TableField("CUSTOMSTAX")
    private BigDecimal customstax;

    @TableField("MINREQGOODSQTY")
    private BigDecimal minreqgoodsqty;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("OTCFLAG")
    private Integer otcflag;

    @TableField("GMPFLAG")
    private Integer gmpflag;

    @TableField("PATENTFLAG")
    private Integer patentflag;

    @TableField("SECURITYFLAG")
    private Integer securityflag;

    @TableField("CHINESEFLAG")
    private Integer chineseflag;

    @TableField("MEDICINETYPE")
    private Long medicinetype;

    @TableField("IMPORTFLAG")
    private Integer importflag;

    @TableField("DRUGFLAG")
    private Integer drugflag;

    @TableField("POISONFLAG")
    private Integer poisonflag;

    @TableField("BIOMEDFLAG")
    private Integer biomedflag;

    @TableField("BACTERINFLAG")
    private Integer bacterinflag;

    @TableField("COMMONFLAG")
    private Integer commonflag;

    @TableField("MEDICINEFLAG")
    private Long medicineflag;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("NMRMSCLASSID")
    private Integer nmrmsclassid;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("BUSISCOPE")
    private Long busiscope;

    @TableField("BOXFLAG")
    private Integer boxflag;

    @TableField("INTEGERNX")
    private BigDecimal integernx;

    @TableField("FUNCTION")
    private String function;

    @TableField("FAMILYPLANFLAG")
    private Integer familyplanflag;

    @TableField("AGAINCHKFLAG")
    private Integer againchkflag;

    @TableField("KEYCONSERVEFLAG")
    private Integer keyconserveflag;

    @TableField("MEDICINESORT")
    private Integer medicinesort;

    @TableField("TOINVDAYSWARN")
    private Integer toinvdayswarn;

    @TableField("LIMITEDSALEFLAG")
    private Integer limitedsaleflag;

    @TableField("LEASTSALEQTY")
    private BigDecimal leastsaleqty;

    @TableField("ENTRYSET")
    private String entryset;

    @TableField("FINANCENO2")
    private String financeno2;

    @TableField("EPHEDRINE")
    private Integer ephedrine;

    @TableField("ISEGGPEPTIDE")
    private Integer iseggpeptide;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("ECODEFLAG")
    private Integer ecodeflag;

    @TableField("ZHL")
    private BigDecimal zhl;

    @TableField("GOODSGROUPCODE")
    private String goodsgroupcode;

    @TableField("MPGID")
    private Long mpgid;

    @TableField("DRUGTABOO")
    private String drugtaboo;

    @TableField("GOODSPROPERTIES")
    private String goodsproperties;

    @TableField("GSPCATEGORYID")
    private Long gspcategoryid;

    @TableField("UNEDITEDFLAG")
    private Integer uneditedflag;

    @TableField("QLOGID")
    private Long qlogid;

    @TableField("PKID")
    private Long pkid;

    @TableField("DIRECTION")
    private Integer direction;

    @TableField("TRANFLAG")
    private Long tranflag;

    @TableField("TRANDATE")
    private LocalDateTime trandate;

    @TableField("EXPORTDATE")
    private LocalDateTime exportdate;

    @TableField("IMPORTDATE")
    private LocalDateTime importdate;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("TRANSPORTTIME")
    private BigDecimal transporttime;


    @Override
    protected Serializable pkVal() {
        return this.goodsid;
    }

}
