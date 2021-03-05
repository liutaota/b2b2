package com.zsyc.zt.fapiao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 发票回传信息中的货物明细
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BWFP_FPMX")
public class BwfpFpmx extends Model<BwfpFpmx> {

    private static final long serialVersionUID = 1L;

    @TableField("FPMX_ID")
    private BigDecimal fpmxId;

    @TableField("FP_ID")
    private BigDecimal fpId;

    @TableField("BILL_CODE")
    private String billCode;

    @TableField("LINE_NO")
    private Integer lineNo;

    @TableField("LINE_TYPE")
    private Integer lineType;

    @TableField("TAX_KIND_CODE")
    private String taxKindCode;

    @TableField("SPSM")
    private String spsm;

    @TableField("ZXBM")
    private String zxbm;

    @TableField("GOODS_CODE")
    private String goodsCode;

    @TableField("GOODS_NAME")
    private String goodsName;

    @TableField("SPEC_NAME")
    private String specName;

    @TableField("SALE_UNIT")
    private String saleUnit;

    @TableField("QTY")
    private BigDecimal qty;

    @TableField("TAX_PRICE")
    private BigDecimal taxPrice;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("TAX_RATE")
    private BigDecimal taxRate;

    @TableField("AMOUNT")
    private BigDecimal amount;

    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @TableField("DISCOUNT_AMOUNT")
    private BigDecimal discountAmount;

    @TableField("DISCOUNT_TAX_AMOUNT")
    private BigDecimal discountTaxAmount;

    @TableField("ZERO_RATE")
    private Integer zeroRate;

    @TableField("COUPON_FLAG")
    private Integer couponFlag;

    @TableField("COUPON_NAME")
    private String couponName;

    @TableField("STATUS")
    private Integer status;

    @TableField("MODIFIER")
    private BigDecimal modifier;

    @TableField("MODIFY_TIME")
    private BigDecimal modifyTime;

    @TableField("OUT_ID")
    private String outId;

    @TableField("EXPAND1")
    private String expand1;

    @TableField("EXPAND2")
    private String expand2;

    @TableField("EXPAND3")
    private String expand3;

    @TableField("EXPAND4")
    private String expand4;

    @TableField("EXPAND5")
    private String expand5;

    @TableField("EXPAND6")
    private String expand6;

    @TableField("EXPAND7")
    private String expand7;

    @TableField("EXPAND8")
    private String expand8;

    @TableField("EXPAND9")
    private String expand9;

    @TableField("EXPAND10")
    private String expand10;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
