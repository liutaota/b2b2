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
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_TRANSCONTROL_DTL")
@ApiModel(value="BmsTranscontrolDtl对象", description="")
@KeySequence(value = "SEQ_BMS_TRANSCONTROL_DTL")
public class BmsTranscontrolDtl extends Model<BmsTranscontrolDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("ROADDTLID")
    private Long roaddtlid;

    @TableField("ROADID")
    private Long roadid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("TRANPOSID")
    private Long tranposid;

    @TableField("TRANSORTNO")
    private Long transortno;

    @TableField("TOTALPACKQTY")
    private Long totalpackqty;

    @TableField("DTLCOST")
    private Double dtlcost;

    @TableField("RECEIPTMAN")
    private String receiptman;

    @TableField("RECEIPTDATE")
    private LocalDateTime receiptdate;

    @TableField("RETURNFLAG")
    private Integer returnflag;

    @TableField("TRID")
    private Long trid;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("SOURCETYPE")
    private Integer sourcetype;

    @TableField("MEMO")
    private String memo;

    @TableField("RECEMONEY")
    private Double recemoney;

    @TableField("TRDTLID")
    private Long trdtlid;

    @TableField("SALESID")
    private Long salesid;

    @TableField("BOXQTY")
    private Double boxqty;

    @TableField("SPEDRUGFLAG")
    private Integer spedrugflag;

    @TableField("STARTTEMPERATURE")
    private Double starttemperature;

    @TableField("REACHTEMPERATURE")
    private Double reachtemperature;

    @TableField("COLDSTORAGEFLAG")
    private Integer coldstorageflag;

    @TableField("ZX_SARECTYPE")
    private Integer zxSarectype;

    @TableField("ZX_UNDELIFLAG")
    private Integer zxUndeliflag;

    @TableField("UNDELIFLAG")
    private Integer undeliflag;

    @TableField("SWIFTNUMBER")
    private String swiftnumber;

    @TableField("ZX_ERRORMSG")
    private String zxErrormsg;

    @TableField("ZX_APPPAYFLAG")
    private Integer zxApppayflag;


}
