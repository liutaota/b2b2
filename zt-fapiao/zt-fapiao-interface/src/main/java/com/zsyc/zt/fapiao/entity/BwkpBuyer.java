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
 * ERP开票购方
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BWKP_BUYER")
public class BwkpBuyer extends Model<BwkpBuyer> {

    private static final long serialVersionUID = 1L;

    @TableField("BUYER_CODE")
    private String buyerCode;

    @TableField("BUYER_TAX_CODE")
    private String buyerTaxCode;

    @TableField("BUYER_NAME")
    private String buyerName;

    @TableField("PY_CHAR")
    private String pyChar;

    @TableField("TAX_ADDRESS_TEL")
    private String taxAddressTel;

    @TableField("BANK_NAME_ACCOUNT")
    private String bankNameAccount;

    @TableField("MOBILE")
    private String mobile;

    @TableField("EMAIL")
    private String email;

    @TableField("STATUS")
    private Integer status;

    @TableField("MODIFY_TIME")
    private BigDecimal modifyTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
