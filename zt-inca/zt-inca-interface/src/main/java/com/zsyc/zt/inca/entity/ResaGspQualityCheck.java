package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("RESA_GSP_QUALITY_CHECK")
@ApiModel(value="ResaGspQualityCheck对象", description="")
@KeySequence(value = "RESA_GSP_QUALITY_CHECK_SEQ")
public class ResaGspQualityCheck extends Model<ResaGspQualityCheck> {

    private static final long serialVersionUID = 1L;

    @TableId("CHECKID")
    private Long checkid;

    @TableField("CHECKDOCNO")
    private String checkdocno;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("POSID")
    private Long posid;

    @TableField("CERTIFIEDFLAG")
    private Integer certifiedflag;

    @TableField("CHECKCONCLUSION")
    private Integer checkconclusion;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("REFUSEFLAG")
    private Integer refuseflag;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("MEMO")
    private String memo;

    @TableField("ARRIVEDATE")
    private LocalDateTime arrivedate;

    @TableField("FACEQUALITY")
    private Integer facequality;

    @TableField("PACKQUALITY")
    private Integer packquality;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("TRANSPORTID")
    private Long transportid;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("STARTPLACE")
    private String startplace;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("REACHDATE")
    private LocalDateTime reachdate;

    @TableField("STARTTEMPERATURE")
    private Double starttemperature;

    @TableField("REACHTEMPERATURE")
    private Double reachtemperature;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("GOODSDETAILID")
    private Long goodsdetailid;


}
