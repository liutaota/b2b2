package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_RG_DTL")
@ApiModel(value="BmsStRgDtl对象", description="")
@KeySequence(value = "SEQ_BMS_ST_RG_DTL")
public class BmsStRgDtl extends Model<BmsStRgDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("RGDTLID")
    private Long rgdtlid;

    @TableField("RGID")
    private Long rgid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("WHOLEQTY")
    private Double wholeqty;

    @TableField("SCATTERQTY")
    private Double scatterqty;

    @TableField("UNWHOLEQTY")
    private Double unwholeqty;

    @TableField("UNSCATTERQTY")
    private Double unscatterqty;

    @TableField("UNINTRUSTWHOLEQTY")
    private Double unintrustwholeqty;

    @TableField("UNINTRUSTSCATTERQTY")
    private Double unintrustscatterqty;

    @TableField("UNREFUSEWHOLEQTY")
    private Double unrefusewholeqty;

    @TableField("UNREFUSESCATTERQTY")
    private Double unrefusescatterqty;

    @TableField("REGOODSQTY")
    private Double regoodsqty;

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
    private Double starttemperature;

    @TableField("REACHTEMPERATURE")
    private Double reachtemperature;

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
    private Double randomnum;

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
    private Double eligibgoodsqty;

    @TableField("UNELIGIBGOODSQTY")
    private Double uneligibgoodsqty;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("UNINTRUSTQTY")
    private Double unintrustqty;

    @TableField("UNREFUSEQTY")
    private Double unrefuseqty;

    @TableField("RETINYID")
    private Long retinyid;

    @TableField("RGGOODSQTY")
    private Double rggoodsqty;

    @TableField("TARGETSOURCEID")
    private Long targetsourceid;

    @TableField("TARGETGOODSQTY")
    private Double targetgoodsqty;

    @TableField("INVDATE")
    private LocalDateTime invdate;

    @TableField("INVNO")
    private String invno;

    @TableField("INVCODE")
    private String invcode;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("REWHOLEQTY")
    private Double rewholeqty;

    @TableField("RESCATTERQTY")
    private Double rescatterqty;

    @TableField("REUNWHOLEQTY")
    private Double reunwholeqty;

    @TableField("REUNSCATTERQTY")
    private Double reunscatterqty;

    @TableField("REUNINTRUSTWHOLEQTY")
    private Double reunintrustwholeqty;

    @TableField("REUNINTRUSTSCATTERQTY")
    private Double reunintrustscatterqty;

    @TableField("REUNREFUSEWHOLEQTY")
    private Double reunrefusewholeqty;

    @TableField("REUNREFUSESCATTERQTY")
    private Double reunrefusescatterqty;

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


}
