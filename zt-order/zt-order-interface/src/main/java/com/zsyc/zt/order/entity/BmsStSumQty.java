package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_ST_SUM_QTY")
public class BmsStSumQty extends Model<BmsStSumQty> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSID")
    private Long goodsid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("USEMM")
    private Long usemm;

    @TableField("SUPPLYQTY")
    private BigDecimal supplyqty;

    @TableField("SUPPLYBAKQTY")
    private BigDecimal supplybakqty;

    @TableField("SALESQTY")
    private BigDecimal salesqty;

    @TableField("SALESBAKQTY")
    private BigDecimal salesbakqty;

    @TableField("MOVEINQTY")
    private BigDecimal moveinqty;

    @TableField("MOVEOUTQTY")
    private BigDecimal moveoutqty;

    @TableField("OVERFLOWQTY")
    private BigDecimal overflowqty;

    @TableField("LOSEQTY")
    private BigDecimal loseqty;

    @TableField("MATERIALOUT")
    private BigDecimal materialout;

    @TableField("PRODIN")
    private BigDecimal prodin;

    @TableField("OTHERINQTY")
    private BigDecimal otherinqty;

    @TableField("OTHEROUTQTY")
    private BigDecimal otheroutqty;

    @TableField("ERAINQTY")
    private BigDecimal erainqty;

    @TableField("ERAOUTQTY")
    private BigDecimal eraoutqty;

    @TableField("PRESENTINQTY")
    private BigDecimal presentinqty;

    @TableField("PRESENTOUTQTY")
    private BigDecimal presentoutqty;

    @TableField("FITINQTY")
    private BigDecimal fitinqty;

    @TableField("FITOUTQTY")
    private BigDecimal fitoutqty;

    @TableField("DEBITOUT")
    private BigDecimal debitout;

    @TableField("LENDEROUT")
    private BigDecimal lenderout;

    @TableField("DEBITIN")
    private BigDecimal debitin;

    @TableField("LENDERIN")
    private BigDecimal lenderin;

    @TableField("MATERIALOUTBACK")
    private BigDecimal materialoutback;

    @TableField("PDINBACK")
    private BigDecimal pdinback;

    @TableField("DIAGNOISOUTQTY")
    private BigDecimal diagnoisoutqty;


    @Override
    protected Serializable pkVal() {
        return this.goodsid;
    }

}
