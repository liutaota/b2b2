package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value   = "货品主档信息维护查询条件")
@Data
public class PubGoodsDto implements Serializable {

    @ApiModelProperty(name  = "货品ID")
    private Long goodsid;

    @ApiModelProperty(name  = "操作码")
    private String opcode;

    @ApiModelProperty(name  = "拼音")
    private String goodspinyin;

    @ApiModelProperty(name  = "通用名")
    private String goodsname;

    @ApiModelProperty(name  = "商品名")
    private String currencyname;

    @ApiModelProperty(name  = "英文名称")
    private String goodsengname;

    //    @ApiModelProperty(name  = "")
    private String goodsinvname;

    @ApiModelProperty(name  = "简称")
    private String goodsshortname;

    @ApiModelProperty(name  = "学名")
    private String goodsformalname;

    //    @ApiModelProperty(name  = "")
    private String goodsformalpy;

    @ApiModelProperty(name  = "")
    private String goodsno;

    @ApiModelProperty(name  = "分类编码")
    private String standardno;

    @ApiModelProperty(name  = "条形码")
    private String barcode;

    @ApiModelProperty(name  = "规格")
    private String goodstype;

    @ApiModelProperty(name  = "基本单位")
    private String goodsunit;

    @ApiModelProperty(name  = "首营审批档案号")
    private String firstapprovedocno;

    @ApiModelProperty(name  = "批准文号")
    private String approvedocno;

    @ApiModelProperty(name  = "注册证号")
    private String registdocno;

    @ApiModelProperty(name  = "质量标准")
    private String standardtype;

    @ApiModelProperty(name  = "生产厂商ID")
    private Long factoryid;

    @ApiModelProperty(name  = "有效期")
    private Integer validperiod;

    @ApiModelProperty(name  = "厂家负责期")
    private Integer respperiod;

    @ApiModelProperty(name  = "期间单位")
    private String periodunit;

    @ApiModelProperty(name  = "商标")
    private String trademark;

    @ApiModelProperty(name  = "产地")
    private String prodarea;

    @ApiModelProperty(name  = "进项税")
    private BigDecimal supplytaxrate;

    @ApiModelProperty(name  = "销项税")
    private BigDecimal salestaxrate;

    //    @ApiModelProperty(name  = "")
    private Integer fixpricetype;

    @ApiModelProperty(name  = "使用属性")
    private Integer accflag;

    //    @ApiModelProperty(name  = "")
    private Integer defaultagtflag;

    @ApiModelProperty(name  = "储存条件")
    private Integer storagecondition;

    @ApiModelProperty(name  = "运输条件")
    private Integer transcondition;

    //    @ApiModelProperty(name  = "")
    private Integer combinflag;

    //    @ApiModelProperty(name  = "")
    private BigDecimal customstax;

    //    @ApiModelProperty(name  = "")
    private BigDecimal minreqgoodsqty;

    //    @ApiModelProperty(name  = "")
    private String financeno;

    @ApiModelProperty(name  = "OTC标志")
    private Integer otcflag;

    @ApiModelProperty(name  = "社保药标志")
    private Integer securityflag;

    @ApiModelProperty(name  = "中成药标志")
    private Integer chineseflag;

    @ApiModelProperty(name  = "剂型")
    private Long medicinetype;

    @ApiModelProperty(name  = "进口药品标志")
    private Integer importflag;

    //    @ApiModelProperty(name  = "")
    private Long medicineflag;

    @ApiModelProperty(name  = "创建日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime credate;

    @ApiModelProperty(name  = "建立人ID")
    private Long inputmanid;

    @ApiModelProperty(name  = "备注")
    private String memo;

    @ApiModelProperty(name  = "使用状态")
    private Integer usestatus;

    //    @ApiModelProperty(name  = "")
    private Integer nmrmsclassid;

    //    @ApiModelProperty(name  = "")
    private BigDecimal price;

    @ApiModelProperty(name  = "经营类别")
    private Long busiscope;

    //    @ApiModelProperty(name  = "")
    private Integer boxflag;

    //    @ApiModelProperty(name  = "")
    private BigDecimal integernx;

    @ApiModelProperty(name  = "产品说明")
    private String function;

    @ApiModelProperty(name  = "基药标志")
    private Integer medicinesort;

    //    @ApiModelProperty(name  = "")
    private Integer limitedsaleflag;

    //    @ApiModelProperty(name  = "")
    private BigDecimal leastsaleqty;

    //    @ApiModelProperty(name  = "")
    private String entryset;

    //    @ApiModelProperty(name  = "")
    private String financeno2;

    @ApiModelProperty(name  = "医药行业标志")
    private Integer gspflag;

    @ApiModelProperty(name  = "电子监管标志")
    private Integer ecodeflag;

    //    @ApiModelProperty(name  = "")
    private BigDecimal zhl;

    //    @ApiModelProperty(name  = "")
    private String goodsgroupcode;

    //    @ApiModelProperty(name  = "")
    private Long mpgid;

    //    @ApiModelProperty(name  = "")
    private String drugtaboo;

    //    @ApiModelProperty(name  = "")
    private String goodsproperties;

    @ApiModelProperty(name  = "货品证照分类")
    private Long gspcategoryid;

    //    @ApiModelProperty(name  = "")
    private Long qlogid;

    //    @ApiModelProperty(name  = "")
    private Long pkid;

    //    @ApiModelProperty(name  = "")
    private Integer direction;

    //    @ApiModelProperty(name  = "")
    private Long tranflag;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime trandate;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime exportdate;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime importdate;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime sysModifydate;

    @ApiModelProperty(name  = "期初货品")
    private Integer initflag;

    //    @ApiModelProperty(name  = "")
    private Long newgoodsid;

    //    @ApiModelProperty(name  = "")
    private Long varietyid;

    @ApiModelProperty(name  = "独立包装标志")
    private Integer alonepackflag;

    @ApiModelProperty(name  = "剂型")

    private String medicinetypename;

    @ApiModelProperty(name  = "经营类别")
    private String busiscopename;

    //    @ApiModelProperty(name  = "")
    private Integer keysphyconservedays;

    @ApiModelProperty(name  = "特殊药品")
    private Integer specialdrug;

    @ApiModelProperty(name  = "冷链品种标志")
    private Integer coldflag;

    @ApiModelProperty(name  = "存储温度下限")
    private BigDecimal temperaturedown;

    @ApiModelProperty(name  = "存储温度上限")
    private BigDecimal temperatureup;

    @ApiModelProperty(name  = "特管药品")
    private Integer specialctrl;

    @ApiModelProperty(name  = "运输时限")
    private BigDecimal transporttime;

    @ApiModelProperty(name  = "疗程说明")
    private String treatment;

    @ApiModelProperty(name  = "用法用量")
    private String dosage;

    @ApiModelProperty(name  = "生产许可证号")
    private String permitno;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime towmsdate;

    //    @ApiModelProperty(name  = "")
    private Integer retailnolot;

    @ApiModelProperty(name  = "用药时间")
    private String usegoodstime;

    @ApiModelProperty(name  = "诊断信息")
    private String diagnosticinfo;

    @ApiModelProperty(name  = "品牌ID")
    private Long brandid;

    //    @ApiModelProperty(name  = "")
    private Integer againchkflag;

    @ApiModelProperty(name  = "管理批号")
    private Integer lotflag;

    @ApiModelProperty(name  = "生产厂商在明细")
    private Integer factoryflag;

    @ApiModelProperty(name  = "产地在明细")
    private Integer prodareaflag;

    //    @ApiModelProperty(name  = "")
    private Integer specialcontrol;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime tozjbdate;

    //    @ApiModelProperty(name  = "")
    private Long zxLimitdays;

    //    @ApiModelProperty(name  = "")
    private Integer tkrsflag;

    //    @ApiModelProperty(name  = "")
    private Long fakegoodsid;

    //    @ApiModelProperty(name  = "")
    private BigDecimal zxLimitqty;

    //    @ApiModelProperty(name  = "")
    private Long zxInvaliddays;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime tofsyjdate;

    @ApiModelProperty(name  = "")
    private BigDecimal zxMinsaleqty;

    @ApiModelProperty(name  = "包装规格")
    private String yjGoodstype;

    //    @ApiModelProperty(name  = "")
    private String wmscw;

    //    @ApiModelProperty(name  = "")
    private Integer productgroup;

    //    @ApiModelProperty(name  = "")
    private Integer zxBhFlag;

    //    @ApiModelProperty(name  = "")
    private String flcode;

    //    @ApiModelProperty(name  = "")
    private String jcname;

    //    @ApiModelProperty(name  = "")
    private String prolicense;

    //    @ApiModelProperty(name  = "")
    private String flgoods;

    //    @ApiModelProperty(name  = "")
    private Long lxnameid;

    @ApiModelProperty(name  = "拆单分类")
    private Integer zxSplitType;

    @ApiModelProperty(name  = "开票分类编码")
    private String zxTypecode;

    @ApiModelProperty(name  = "开票简称")
    private String zxKpopcode;

    @ApiModelProperty(name  = "药品本位码")
    private String zxYpcode;

    //    @ApiModelProperty(name  = "")
    private String zxBzType;

    @ApiModelProperty(name  = "等级")
    private Integer zxLevel;

    @ApiModelProperty(name  = "代理性质")
    private Integer zxDltype;

    @ApiModelProperty(name  = "货品分类名")
    private Integer zxGoodsType;

    @ApiModelProperty(name  = "生产单位换算比例")
    private BigDecimal zxUnitconversionratio;

    @ApiModelProperty(name  = "原料/半成品")
    private Integer zxIsraw;

    @ApiModelProperty(name  = "商品名操作码")
    private String zxOpcode;

    //    @ApiModelProperty(name  = "")
    private Integer zxHptypeid;

    //    @ApiModelProperty(name  = "")
    private String zxHptypename;

    /**
     * 用来作缺货登记专用
     */
    @ApiModelProperty(name  = "用来作缺货登记专用")
    private String zxMemo;

    /**
     * 用来作缺货登记专用
     */
    @ApiModelProperty(name  = "用来作缺货登记专用")
    private Integer zxTag;

    /**
     * 预警天数引用
     */
    @ApiModelProperty(name  = "预警天数引用")
    private Integer zxDay;

    /**
     * 用来作缺货登记专用
     */
    @ApiModelProperty(name  = "用来作缺货登记专用")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime zxRedate;

    /**
     * 缺货原因选项标志用
     */
    @ApiModelProperty(name  = "缺货原因选项标志用")
    private Integer zxLackflag;

    /**
     * 厂家反利维护栏位
     */
    @ApiModelProperty(name  = "厂家反利维护栏位")
    private Integer zxRebate;

    //    @ApiModelProperty(name  = "")
    private Long zxGoodsId;

    @ApiModelProperty(name  = "是否上京东商城")
    private Integer yjcShopFlag;

    @ApiModelProperty(name  = "是否上B2B商城")
    private Integer b2bShopFlag;

    @ApiModelProperty(name  = "控销范围")
    private Integer zxConSa;

    @ApiModelProperty(name  = "")
    private String zxDecode;

    @ApiModelProperty(name  = "商品定位")
    private String goodsidGps;

    @ApiModelProperty(name  = "中类ID")
    private Long classnRow2;

    @ApiModelProperty(name  = "大类名称")
    private String classname1;

    @ApiModelProperty(name  = "小类ID")
    private Long classnRow3;

    @ApiModelProperty(name  = "小类名称")
    private String classname3;

    @ApiModelProperty(name  = "中类名称")
    private String classname2;

    //    @ApiModelProperty(name  = "")
    private Long classnRow1;

    /**
     * 京东价钱浮动比例
     */
    @ApiModelProperty(name  = "京东价钱浮动比例")
    private BigDecimal jdPriceFloat;

    /**
     * 商品需拆包标志传到WMS
     */
    @ApiModelProperty(name  = "商品需拆包标志传到WMS")
    private String zxDlPack;

    //    @ApiModelProperty(name  = "")
    private Long classnRow;


}
