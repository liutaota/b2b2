package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-25
 */
@Data

@Accessors(chain = true)
@TableName("GPCS_PLACEPOINT")
@ApiModel(value="GpcsPlacepoint对象", description="")
@KeySequence(value = "GPCS_PLACEPOINT_SEQ")
public class GpcsPlacepoint {

    private static final long serialVersionUID = 1L;

    @TableId("PLACEPOINTID")
    private Long placepointid;

    @TableField("PLACEPOINTOPCODE")
    private String placepointopcode;

    @TableField("PLACEPOINTNO")
    private String placepointno;

    @TableField("PLACEPOINTNAME")
    private String placepointname;

    @TableField("PLACEPOINTPINYIN")
    private String placepointpinyin;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("STHOUSEID")
    private Long sthouseid;

    @TableField("TEL")
    private String tel;

    @TableField("ONES")
    private Integer ones;

    @TableField("BUSINESSTIME")
    private Double businesstime;

    @TableField("ADDRESS")
    private String address;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("AREADOCID")
    private Long areadocid;

    @TableField("RISERATE")
    private Double riserate;

    @TableField("RESA_MONEY_PRECISION")
    private Integer resaMoneyPrecision;

    @TableField("LOT_MANAGER_MODE")
    private Integer lotManagerMode;

    @TableField("LOT_MODIFY")
    private Integer lotModify;

    @TableField("MAXSALESQTY")
    private Long maxsalesqty;

    @TableField("UPDATEPOS")
    private Integer updatepos;

    @TableField("INPUTMETHOD")
    private Integer inputmethod;

    @TableField("SALERFLAG")
    private Integer salerflag;

    @TableField("WORKINGTYPE")
    private Integer workingtype;

    @TableField("CTRLLOWPRICE")
    private Double ctrllowprice;

    @TableField("DISPAYGATHMONEY")
    private Integer dispaygathmoney;

    @TableField("RETAILCENTERID")
    private Long retailcenterid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("PRESSTOCKFLAG")
    private Integer presstockflag;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("REQSPLITFLAG")
    private Integer reqsplitflag;

    @TableField("ISCOMM")
    private Integer iscomm;

    @TableField("REQCENTERST")
    private Integer reqcenterst;

    @TableField("REQQTYUPLIMIT")
    private Integer reqqtyuplimit;

    @TableField("MANUALRATELIMIT")
    private Double manualratelimit;

    @TableField("MINIATURETAXPAYERFLAG")
    private Integer miniaturetaxpayerflag;

    @TableField("INCOMETAXRATE")
    private Double incometaxrate;

    @TableField("OUTPUTTAXRATE")
    private Double outputtaxrate;

    @TableField("PLACEPOINTTYPE")
    private Integer placepointtype;

    @TableField("BATCH_MANAGER_MODE")
    private Integer batchManagerMode;

    @TableField("INSAUTOUP")
    private Integer insautoup;

    @TableField("OTCRESTRICT")
    private Integer otcrestrict;

    @TableField("DEFAULTDOCTER")
    private String defaultdocter;

    @TableField("DEFAULTHOSPITAL")
    private String defaulthospital;

    @TableField("MAXEPHQTY")
    private Double maxephqty;

    @TableField("EXAMPRICEID")
    private Long exampriceid;

    @TableField("PRELOTMANAGERMODE")
    private Integer prelotmanagermode;

    @TableField("PEIFANGMANID")
    private Long peifangmanid;

    @TableField("SHENFANGMANID")
    private Long shenfangmanid;

    @TableField("TRANSLINEID")
    private Long translineid;

    @TableField("SADAYS_PARAM")
    private Integer sadaysParam;

    @TableField("TOPNUM_PARAM")
    private Integer topnumParam;

    @TableField("CATEGORYID")
    private Long categoryid;

    @TableField("BULKCHECKMANID")
    private Long bulkcheckmanid;

    @TableField("DELIVERYDAY")
    private String deliveryday;

    @TableField("TOTALAREA")
    private Double totalarea;

    @TableField("LIVINGAREA")
    private Double livingarea;

    @TableField("RECIPEREUSABLEFLAG")
    private Integer recipereusableflag;

    @TableField("MANUALLYADD")
    private Integer manuallyadd;

    @TableField("ISAPPROVAL")
    private Integer isapproval;

    @TableField("JFDKXS")
    private Double jfdkxs;

    @TableField("ISCONFIRM")
    private Integer isconfirm;

    @TableField("INSIDERCHANGELOW")
    private Double insiderchangelow;

    @TableField("OFFICESIDS")
    private String officesids;

    @TableField("OFFICESNAMES")
    private String officesnames;

    @TableField("CLASSTEMPLETID")
    private Long classtempletid;

    @TableField("ISZFB")
    private Integer iszfb;

    @TableField("ZFB_PAY_PUBKEY")
    private String zfbPayPubkey;

    @TableField("ZFB_PRIVATEKEY")
    private String zfbPrivatekey;

    @TableField("ZFB_APPID")
    private String zfbAppid;

    @TableField("ISWEIXIN")
    private Integer isweixin;

    @TableField("WX_CUSTOMERNO")
    private String wxCustomerno;

    @TableField("ISENDOFDAY")
    private Integer isendofday;

    @TableField("ISSAME")
    private Integer issame;

    @TableField("AVGMATHED")
    private Integer avgmathed;

    @TableField("AVGMATHEDPOINT")
    private Integer avgmathedpoint;

    @TableField("UPDOWNUPDATE")
    private Integer updownupdate;

    @TableField("UPDOWNGOODS")
    private Integer updowngoods;

    @TableField("UPDOWNAVGMATHED")
    private Integer updownavgmathed;

    @TableField("UPDOWNMATHEDPOINT")
    private Integer updownmathedpoint;

    @TableField("DEFAULTUPDAYS")
    private Double defaultupdays;

    @TableField("DEFAULTDOWNDAYS")
    private Double defaultdowndays;

    @TableField("AVGCALCULATEMATHED")
    private Integer avgcalculatemathed;

    @TableField("D1DAYS")
    private Double d1days;

    @TableField("D2DAYS")
    private Double d2days;

    @TableField("D3DAYS")
    private Double d3days;

    @TableField("D4DAYS")
    private Double d4days;

    @TableField("D1PROPORTION")
    private Double d1proportion;

    @TableField("D2PROPORTION")
    private Double d2proportion;

    @TableField("D3PROPORTION")
    private Double d3proportion;

    @TableField("D4PROPORTION")
    private Double d4proportion;

    @TableField("REMOVENOSALE")
    private Integer removenosale;

    @TableField("REMOVEPLACE")
    private Integer removeplace;

    @TableField("REMOVENOACCEPT")
    private Integer removenoaccept;

    @TableField("REMOVEINST")
    private Integer removeinst;

    @TableField("REMOVEOUTST")
    private Integer removeoutst;

    @TableField("REQMATHED")
    private Integer reqmathed;

    @TableField("AUTOREQMATHED")
    private Integer autoreqmathed;

    @TableField("AUTOREQDATE")
    private String autoreqdate;

    @TableField("AUTOREQUSESTATUS")
    private Integer autorequsestatus;

    @TableField("CLACDATEN")
    private Long clacdaten;

    @TableField("CALCMETHOD")
    private Integer calcmethod;

    @TableField("ZX_OTCFLAG0")
    private Integer zxOtcflag0;

    @TableField("ZX_OTCFLAG1")
    private Integer zxOtcflag1;

    @TableField("ZX_OTCFLAG2")
    private Integer zxOtcflag2;

    @TableField("ZX_OTCFLAG3")
    private Integer zxOtcflag3;


}
