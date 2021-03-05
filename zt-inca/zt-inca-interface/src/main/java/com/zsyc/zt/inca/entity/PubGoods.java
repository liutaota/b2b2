package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
@Data

@Accessors(chain = true)
@TableName("PUB_GOODS")
@ApiModel(value="PubGoods对象", description="")
@KeySequence(value = "PUB_GOODS_SEQ")
public class PubGoods extends BaseBean {

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
    private Double supplytaxrate;

    @TableField("SALESTAXRATE")
    private Double salestaxrate;

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
    private Double customstax;

    @TableField("MINREQGOODSQTY")
    private Double minreqgoodsqty;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("OTCFLAG")
    private Integer otcflag;

    @TableField("SECURITYFLAG")
    private Integer securityflag;

    @TableField("CHINESEFLAG")
    private Integer chineseflag;

    @TableField("MEDICINETYPE")
    private Long medicinetype;

    @TableField("IMPORTFLAG")
    private Integer importflag;

    @TableField("MEDICINEFLAG")
    private Long medicineflag;

    @TableField("CREDATE")
    private Date credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("NMRMSCLASSID")
    private Integer nmrmsclassid;

    @TableField("PRICE")
    private Double price;

    @TableField("BUSISCOPE")
    private Long busiscope;

    @TableField("BOXFLAG")
    private Integer boxflag;

    @TableField("INTEGERNX")
    private Double integernx;

    @TableField("FUNCTION")
    private String function;

    @TableField("MEDICINESORT")
    private Integer medicinesort;

    @TableField("LIMITEDSALEFLAG")
    private Integer limitedsaleflag;

    @TableField("LEASTSALEQTY")
    private Double leastsaleqty;

    @TableField("ENTRYSET")
    private String entryset;

    @TableField("FINANCENO2")
    private String financeno2;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("ECODEFLAG")
    private Integer ecodeflag;

    @TableField("ZHL")
    private Double zhl;

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

    @TableField("QLOGID")
    private Long qlogid;

    @TableField("PKID")
    private Long pkid;

    @TableField("DIRECTION")
    private Integer direction;

    @TableField("TRANFLAG")
    private Long tranflag;

    @TableField("TRANDATE")
    private Date trandate;

    @TableField("EXPORTDATE")
    private Date exportdate;

    @TableField("IMPORTDATE")
    private Date importdate;

    @TableField("SYS_MODIFYDATE")
    private Date sysModifydate;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("NEWGOODSID")
    private Long newgoodsid;

    @TableField("VARIETYID")
    private Long varietyid;

    @TableField("ALONEPACKFLAG")
    private Integer alonepackflag;

    @TableField("MEDICINETYPENAME")
    private String medicinetypename;

    @TableField("BUSISCOPENAME")
    private String busiscopename;

    @TableField("KEYSPHYCONSERVEDAYS")
    private Integer keysphyconservedays;

    @TableField("SPECIALDRUG")
    private Integer specialdrug;

    @TableField("COLDFLAG")
    private Integer coldflag;

    @TableField("TEMPERATUREDOWN")
    private Double temperaturedown;

    @TableField("TEMPERATUREUP")
    private Double temperatureup;

    @TableField("SPECIALCTRL")
    private Integer specialctrl;

    @TableField("TRANSPORTTIME")
    private Double transporttime;

    @TableField("TREATMENT")
    private String treatment;

    @TableField("DOSAGE")
    private String dosage;

    @TableField("PERMITNO")
    private String permitno;

    @TableField("TOWMSDATE")
    private Date towmsdate;

    @TableField("RETAILNOLOT")
    private Integer retailnolot;

    @TableField("USEGOODSTIME")
    private String usegoodstime;

    @TableField("DIAGNOSTICINFO")
    private String diagnosticinfo;

    @TableField("BRANDID")
    private Long brandid;

    @TableField("AGAINCHKFLAG")
    private Integer againchkflag;

    @TableField("LOTFLAG")
    private Integer lotflag;

    @TableField("FACTORYFLAG")
    private Integer factoryflag;

    @TableField("PRODAREAFLAG")
    private Integer prodareaflag;

    @TableField("SPECIALCONTROL")
    private Integer specialcontrol;

    @TableField("TOZJBDATE")
    private Date tozjbdate;

    @TableField("ZX_LIMITDAYS")
    private Long zxLimitdays;

    @TableField("TKRSFLAG")
    private Integer tkrsflag;

    @TableField("FAKEGOODSID")
    private Long fakegoodsid;

    @TableField("ZX_LIMITQTY")
    private Double zxLimitqty;

    @TableField("ZX_INVALIDDAYS")
    private Long zxInvaliddays;

    @TableField("TOFSYJDATE")
    private Date tofsyjdate;

    @TableField("ZX_MINSALEQTY")
    private Double zxMinsaleqty;

    @TableField("YJ_GOODSTYPE")
    private String yjGoodstype;

    @TableField("WMSCW")
    private String wmscw;

    @TableField("PRODUCTGROUP")
    private Integer productgroup;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("FLCODE")
    private String flcode;

    @TableField("JCNAME")
    private String jcname;

    @TableField("PROLICENSE")
    private String prolicense;

    @TableField("FLGOODS")
    private String flgoods;

    @TableField("LXNAMEID")
    private Long lxnameid;

    @TableField("ZX_SPLIT_TYPE")
    private Integer zxSplitType;

    @TableField("ZX_TYPECODE")
    private String zxTypecode;

    @TableField("ZX_KPOPCODE")
    private String zxKpopcode;

    @TableField("ZX_YPCODE")
    private String zxYpcode;

    @TableField("ZX_BZ_TYPE")
    private String zxBzType;

    @TableField("ZX_LEVEL")
    private Integer zxLevel;

    @TableField("ZX_DLTYPE")
    private Integer zxDltype;

    @TableField("ZX_GOODS_TYPE")
    private Integer zxGoodsType;

    @TableField("ZX_UNITCONVERSIONRATIO")
    private Double zxUnitconversionratio;

    @TableField("ZX_ISRAW")
    private Integer zxIsraw;

    @TableField("ZX_OPCODE")
    private String zxOpcode;

    @TableField("ZX_HPTYPEID")
    private Integer zxHptypeid;

    @TableField("ZX_HPTYPENAME")
    private String zxHptypename;

    /**
     * 用来作缺货登记专用
     */
    @ApiModelProperty(value = "用来作缺货登记专用")
    @TableField("ZX_MEMO")
    private String zxMemo;

    /**
     * 用来作缺货登记专用
     */
    @ApiModelProperty(value = "用来作缺货登记专用")
    @TableField("ZX_TAG")
    private Integer zxTag;

    /**
     * 预警天数引用
     */
    @ApiModelProperty(value = "预警天数引用")
    @TableField("ZX_DAY")
    private Integer zxDay;

    /**
     * 用来作缺货登记专用
     */
    @ApiModelProperty(value = "用来作缺货登记专用")
    @TableField("ZX_REDATE")
    private Date zxRedate;

    /**
     * 缺货原因选项标志用
     */
    @ApiModelProperty(value = "缺货原因选项标志用")
    @TableField("ZX_LACKFLAG")
    private Integer zxLackflag;

    /**
     * 厂家反利维护栏位
     */
    @ApiModelProperty(value = "厂家反利维护栏位")
    @TableField("ZX_REBATE")
    private Integer zxRebate;

    @TableField("ZX_GOODS_ID")
    private Long zxGoodsId;

    @TableField("YJC_SHOP_FLAG")
    private Integer yjcShopFlag;

    @TableField("B2B_SHOP_FLAG")
    private Integer b2bShopFlag;

    @TableField("ZX_CON_SA")
    private Integer zxConSa;

    @TableField("CLASSN_ROW")
    private Long classnRow;

    @TableField("GOODSID_GPS")
    private String goodsidGps;

    @TableField("CLASSN_ROW_2")
    private Long classnRow2;

    @TableField("CLASSNAME_1")
    private String classname1;

    @TableField("CLASSN_ROW_3")
    private Long classnRow3;

    @TableField("CLASSNAME_3")
    private String classname3;

    @TableField("CLASSNAME_2")
    private String classname2;

    @TableField("CLASSN_ROW_1")
    private Long classnRow1;

    /**
     * 京东价钱浮动比例
     */
    @ApiModelProperty(value = "京东价钱浮动比例")
    @TableField("JD_PRICE_FLOAT")
    private Double jdPriceFloat;

    /**
     * 商品需拆包标志传到WMS
     */
    @ApiModelProperty(value = "商品需拆包标志传到WMS")
    @TableField("ZX_DL_PACK")
    private String zxDlPack;


}
