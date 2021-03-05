package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_SUM_QTY")
@ApiModel(value="BmsStSumQty对象", description="")
//@KeySequence(value = "BMS_ST_SUM_QTY_SEQ")
public class BmsStSumQty extends Model<BmsStSumQty> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSID")
    private Long goodsid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("USEMM")
    private Long usemm;

    @TableField("SUPPLYQTY")
    private Double supplyqty;

    @TableField("SUPPLYBAKQTY")
    private Double supplybakqty;

    @TableField("SALESQTY")
    private Double salesqty;

    @TableField("SALESBAKQTY")
    private Double salesbakqty;

    @TableField("MOVEINQTY")
    private Double moveinqty;

    @TableField("MOVEOUTQTY")
    private Double moveoutqty;

    @TableField("OVERFLOWQTY")
    private Double overflowqty;

    @TableField("LOSEQTY")
    private Double loseqty;

    @TableField("MATERIALOUT")
    private Double materialout;

    @TableField("PRODIN")
    private Double prodin;

    @TableField("OTHERINQTY")
    private Double otherinqty;

    @TableField("OTHEROUTQTY")
    private Double otheroutqty;

    @TableField("ERAINQTY")
    private Double erainqty;

    @TableField("ERAOUTQTY")
    private Double eraoutqty;

    @TableField("PRESENTINQTY")
    private Double presentinqty;

    @TableField("PRESENTOUTQTY")
    private Double presentoutqty;

    @TableField("FITINQTY")
    private Double fitinqty;

    @TableField("FITOUTQTY")
    private Double fitoutqty;

    @TableField("DEBITOUT")
    private Double debitout;

    @TableField("LENDEROUT")
    private Double lenderout;

    @TableField("DEBITIN")
    private Double debitin;

    @TableField("LENDERIN")
    private Double lenderin;

    @TableField("MATERIALOUTBACK")
    private Double materialoutback;

    @TableField("PDINBACK")
    private Double pdinback;

    @TableField("DIAGNOISOUTQTY")
    private Double diagnoisoutqty;


}
