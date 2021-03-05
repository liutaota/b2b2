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
 * ERP开票商品明细
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BWKP_KPMX")
public class BwkpKpmx extends Model<BwkpKpmx> {

    private static final long serialVersionUID = 1L;

    @TableField("KPMX_ID")
    private BigDecimal kpmxId;

    @TableField("KP_ID")
    private BigDecimal kpId;

    @TableField("BILL_CODE")
    private String billCode;

    @TableField("LINE_NO")
    private Integer lineNo;

    @TableField("LINE_KIND")
    private Integer lineKind;

    @TableField("GOODS_CODE")
    private String goodsCode;

    @TableField("GOODS_NAME")
    private String goodsName;

    @TableField("TAX_KIND_CODE")
    private String taxKindCode;

    @TableField("TAX_ITEM")
    private String taxItem;

    @TableField("PRODUCT_NO")
    private String productNo;

    @TableField("SPEC_NAME")
    private String specName;

    @TableField("SALE_UNIT")
    private String saleUnit;

    @TableField("QTY")
    private BigDecimal qty;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("NON_TAX_PRICE")
    private BigDecimal nonTaxPrice;

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

    @TableField("DISCOUNT_TOTAL_AMOUNT")
    private BigDecimal discountTotalAmount;

    @TableField("TAX_FLAG")
    private Integer taxFlag;

    @TableField("DUTY_FREE")
    private Integer dutyFree;

    @TableField("COUPON_FLAG")
    private Integer couponFlag;

    @TableField("COUPON_POLICY")
    private String couponPolicy;

    @TableField("OUT_ID")
    private String outId;

    @TableField("MODIFIER")
    private BigDecimal modifier;

    @TableField("MODIFY_TIME")
    private BigDecimal modifyTime;

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
