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
@TableName("BMS_TRANSCONTROL_DTL")
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
    private BigDecimal dtlcost;

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
    private BigDecimal recemoney;

    @TableField("TRDTLID")
    private Long trdtlid;

    @TableField("SALESID")
    private Long salesid;

    @TableField("BOXQTY")
    private BigDecimal boxqty;

    @TableField("SPEDRUGFLAG")
    private Integer spedrugflag;

    @TableField("STARTTEMPERATURE")
    private BigDecimal starttemperature;

    @TableField("REACHTEMPERATURE")
    private BigDecimal reachtemperature;

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


    @Override
    protected Serializable pkVal() {
        return this.roaddtlid;
    }

}
