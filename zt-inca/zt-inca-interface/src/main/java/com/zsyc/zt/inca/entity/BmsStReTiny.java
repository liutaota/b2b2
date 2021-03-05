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
 * @since 2020-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_RE_TINY")
@ApiModel(value="BmsStReTiny对象", description="")
@KeySequence(value = "BMS_ST_RE_TINY_SEQ")

public class BmsStReTiny extends Model<BmsStReTiny> {

    private static final long serialVersionUID = 1L;

    @TableId("RETINYID")
    private Long retinyid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY_LINE")
    private Double goodsqtyLine;

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

    @TableField("UNQUALTFIEDMEMO")
    private String unqualtfiedmemo;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("UNINTRUSTQTY")
    private Double unintrustqty;

    @TableField("UNREFUSEQTY")
    private Double unrefuseqty;

    @TableField("REDTLID")
    private Long redtlid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("ZX_GOODSSTATUSID")
    private Long zxGoodsstatusid;


}
