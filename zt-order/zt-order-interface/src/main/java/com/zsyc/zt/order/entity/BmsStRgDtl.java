package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_ST_RG_DTL")
public class BmsStRgDtl extends Model<BmsStRgDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("RGDTLID")
    private Long rgdtlid;

    @TableField("RGID")
    private Long rgid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("WHOLEQTY")
    private BigDecimal wholeqty;

    @TableField("SCATTERQTY")
    private BigDecimal scatterqty;

    @TableField("UNWHOLEQTY")
    private BigDecimal unwholeqty;

    @TableField("UNSCATTERQTY")
    private BigDecimal unscatterqty;

    @TableField("UNINTRUSTWHOLEQTY")
    private BigDecimal unintrustwholeqty;

    @TableField("UNINTRUSTSCATTERQTY")
    private BigDecimal unintrustscatterqty;

    @TableField("UNREFUSEWHOLEQTY")
    private BigDecimal unrefusewholeqty;

    @TableField("UNREFUSESCATTERQTY")
    private BigDecimal unrefusescatterqty;

    @TableField("REGOODSQTY")
    private BigDecimal regoodsqty;

    @TableField("LOTID")
    private Long lotid;

    @TableField("PACKQUALITY")
    private Integer packquality;

    @TableField("FACEQUALITY")
    private Integer facequality;

    @TableField("TRANSPORTID")
    private Long transportid;

    @TableField("TRANMETHOD")
    private Long tranmethod;

    @TableField("STARTTEMPERATURE")
    private BigDecimal starttemperature;

    @TableField("REACHTEMPERATURE")
    private BigDecimal reachtemperature;

    @TableField("TEMPERATURESTATUS")
    private Integer temperaturestatus;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("REACHDATE")
    private LocalDateTime reachdate;

    @TableField("COLDEQUIP")
    private Integer coldequip;

    @TableField("UNQUALIFIEDMEMO")
    private String unqualifiedmemo;

    @TableField("REGISTEFLAG")
    private Integer registeflag;

    @TableField("PASSGATEFLAG")
    private Integer passgateflag;

    @TableField("IMPORTREPFLAG")
    private Integer importrepflag;

    @TableField("RANDOMNUM")
    private BigDecimal randomnum;

    @TableField("RECHECKSTATUS")
    private Integer recheckstatus;

    @TableField("RECHECKMANID")
    private Long recheckmanid;

    @TableField("RECHECKDATE")
    private LocalDateTime recheckdate;

    @TableField("ASKCHKFLAG")
    private Integer askchkflag;

    @TableField("IFCHECKFLAG")
    private Integer ifcheckflag;

    @TableField("ASKCHECKID")
    private Long askcheckid;

    @TableField("CHECKDOCID")
    private Long checkdocid;

    @TableField("QUALITYCHECKMANID")
    private Long qualitycheckmanid;

    @TableField("QUALITYDATE")
    private LocalDateTime qualitydate;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("ELIGIBGOODSQTY")
    private BigDecimal eligibgoodsqty;

    @TableField("UNELIGIBGOODSQTY")
    private BigDecimal uneligibgoodsqty;

    @TableField("UNQUALIFIEDQTY")
    private BigDecimal unqualifiedqty;

    @TableField("UNINTRUSTQTY")
    private BigDecimal unintrustqty;

    @TableField("UNREFUSEQTY")
    private BigDecimal unrefuseqty;

    @TableField("RETINYID")
    private Long retinyid;

    @TableField("RGGOODSQTY")
    private BigDecimal rggoodsqty;

    @TableField("TARGETSOURCEID")
    private Long targetsourceid;

    @TableField("TARGETGOODSQTY")
    private BigDecimal targetgoodsqty;

    @TableField("INVDATE")
    private LocalDateTime invdate;

    @TableField("INVNO")
    private String invno;

    @TableField("INVCODE")
    private String invcode;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("REWHOLEQTY")
    private BigDecimal rewholeqty;

    @TableField("RESCATTERQTY")
    private BigDecimal rescatterqty;

    @TableField("REUNWHOLEQTY")
    private BigDecimal reunwholeqty;

    @TableField("REUNSCATTERQTY")
    private BigDecimal reunscatterqty;

    @TableField("REUNINTRUSTWHOLEQTY")
    private BigDecimal reunintrustwholeqty;

    @TableField("REUNINTRUSTSCATTERQTY")
    private BigDecimal reunintrustscatterqty;

    @TableField("REUNREFUSEWHOLEQTY")
    private BigDecimal reunrefusewholeqty;

    @TableField("REUNREFUSESCATTERQTY")
    private BigDecimal reunrefusescatterqty;

    @TableField("POSID")
    private Long posid;

    @TableField("ZX_GOODSSTATUSID")
    private Long zxGoodsstatusid;

    @TableField("ZX_SENDWMSFLAG")
    private Integer zxSendwmsflag;

    @TableField("ZX_SENDWMSDATE")
    private LocalDateTime zxSendwmsdate;

    @TableField("ZX_WMSBACKDATE")
    private LocalDateTime zxWmsbackdate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
