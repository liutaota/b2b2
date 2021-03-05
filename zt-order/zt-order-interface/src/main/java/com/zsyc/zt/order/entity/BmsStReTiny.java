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
@TableName("BMS_ST_RE_TINY")
public class BmsStReTiny extends Model<BmsStReTiny> {

    private static final long serialVersionUID = 1L;

    @TableId("RETINYID")
    private Long retinyid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY_LINE")
    private BigDecimal goodsqtyLine;

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

    @TableField("UNQUALTFIEDMEMO")
    private String unqualtfiedmemo;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNQUALIFIEDQTY")
    private BigDecimal unqualifiedqty;

    @TableField("UNINTRUSTQTY")
    private BigDecimal unintrustqty;

    @TableField("UNREFUSEQTY")
    private BigDecimal unrefuseqty;

    @TableField("REDTLID")
    private Long redtlid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("ZX_GOODSSTATUSID")
    private Long zxGoodsstatusid;


    @Override
    protected Serializable pkVal() {
        return this.retinyid;
    }

}
