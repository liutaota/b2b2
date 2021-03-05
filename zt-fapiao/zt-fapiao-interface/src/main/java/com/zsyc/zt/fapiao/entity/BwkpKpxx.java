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
 * ERP开票信息主表
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BWKP_KPXX")
public class BwkpKpxx extends Model<BwkpKpxx> {

    private static final long serialVersionUID = 1L;

    @TableField("KP_ID")
    private BigDecimal kpId;

    @TableField("BILL_CODE")
    private String billCode;

    @TableField("BILL_DATE")
    private BigDecimal billDate;

    @TableField("BILL_SOURCE")
    private Integer billSource;

    @TableField("INVOICE_KIND_CODE")
    private String invoiceKindCode;

    @TableField("INVOICE_TYPE")
    private Integer invoiceType;

    @TableField("SELLER_TAX_CODE")
    private String sellerTaxCode;

    @TableField("SELLER_NAME")
    private String sellerName;

    @TableField("SELLER_ADDRESS_TEL")
    private String sellerAddressTel;

    @TableField("SELLER_BANK_ACCOUNT")
    private String sellerBankAccount;

    @TableField("SHOP_CODE")
    private String shopCode;

    @TableField("BUYER_TAX_CODE")
    private String buyerTaxCode;

    @TableField("BUYER_NAME")
    private String buyerName;

    @TableField("BUYER_ADDRESS_TEL")
    private String buyerAddressTel;

    @TableField("BUYER_BANK_ACCOUNT")
    private String buyerBankAccount;

    @TableField("BUYER_EMAIL")
    private String buyerEmail;

    @TableField("BUYER_MOBILE")
    private String buyerMobile;

    @TableField("TAXATION_MODE")
    private String taxationMode;

    @TableField("SPECIAL_FLAG")
    private String specialFlag;

    @TableField("INVOICE_FORMAT")
    private String invoiceFormat;

    @TableField("BATCH_NO")
    private String batchNo;

    @TableField("BATCH_COUNT")
    private Long batchCount;

    @TableField("BATCH_QTY")
    private BigDecimal batchQty;

    @TableField("BATCH_AMOUNT")
    private BigDecimal batchAmount;

    @TableField("MINUS_AMOUNT")
    private BigDecimal minusAmount;

    @TableField("AMOUNT")
    private BigDecimal amount;

    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @TableField("DISCOUNT_TOTAL_AMOUNT")
    private BigDecimal discountTotalAmount;

    @TableField("DRAWER")
    private String drawer;

    @TableField("PAYEE")
    private String payee;

    @TableField("CHECKER")
    private String checker;

    @TableField("INVOICE_REMARK")
    private String invoiceRemark;

    @TableField("BILL_CODE_OLD")
    private String billCodeOld;

    @TableField("ORIGINAL_INVOICE_CODE")
    private String originalInvoiceCode;

    @TableField("ORIGINAL_INVOICE_NO")
    private String originalInvoiceNo;

    @TableField("RED_INFO_NO")
    private String redInfoNo;

    @TableField("INVOICE_CODE")
    private String invoiceCode;

    @TableField("INVOICE_NO")
    private String invoiceNo;

    @TableField("STATUS")
    private Integer status;

    @TableField("CREATE_TIME")
    private BigDecimal createTime;

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
