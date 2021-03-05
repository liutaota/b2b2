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
 * ERP开票商品
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BWKP_GOODS")
public class BwkpGoods extends Model<BwkpGoods> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODS_CODE")
    private String goodsCode;

    @TableField("GOODS_NAME")
    private String goodsName;

    @TableField("PY_CHAR")
    private String pyChar;

    @TableField("TAX_KIND_CODE")
    private String taxKindCode;

    @TableField("TAX_KIND_NAME")
    private String taxKindName;

    @TableField("PRODUCT_NO")
    private String productNo;

    @TableField("SPEC_NAME")
    private String specName;

    @TableField("SALE_UNIT")
    private String saleUnit;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("NON_TAX_PRICE")
    private BigDecimal nonTaxPrice;

    @TableField("TAX_FLAG")
    private Integer taxFlag;

    @TableField("TAX_RATE")
    private BigDecimal taxRate;

    @TableField("DUTY_FREE")
    private Integer dutyFree;

    @TableField("COUPON_FLAG")
    private Integer couponFlag;

    @TableField("COUPON_POLICY")
    private String couponPolicy;

    @TableField("STATUS")
    private Integer status;

    @TableField("MODIFY_TIME")
    private BigDecimal modifyTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
