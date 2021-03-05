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
 * 发票回传主表
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BWFP_FPXX")
public class BwfpFpxx extends Model<BwfpFpxx> {

    private static final long serialVersionUID = 1L;

    @TableField("FP_ID")
    private BigDecimal fpId;

    @TableField("INVOICE_KIND_CODE")
    private String invoiceKindCode;

    @TableField("INVOICE_CODE")
    private String invoiceCode;

    @TableField("INVOICE_NO")
    private String invoiceNo;

    @TableField("INVOICE_TYPE")
    private Integer invoiceType;

    @TableField("INVOICE_DATE")
    private Long invoiceDate;

    @TableField("KP_ID")
    private BigDecimal kpId;

    @TableField("REQ_SERIAL_NO")
    private String reqSerialNo;

    @TableField("BILL_CODE")
    private String billCode;

    @TableField("BILL_DATE")
    private BigDecimal billDate;

    @TableField("BILL_SOURCE")
    private Integer billSource;

    @TableField("BATCH_NO")
    private String batchNo;

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

    @TableField("ZONE_CODE")
    private String zoneCode;

    @TableField("SELLER_TAX_CODE")
    private String sellerTaxCode;

    @TableField("SELLER_NAME")
    private String sellerName;

    @TableField("SELLER_ADDRESS_TEL")
    private String sellerAddressTel;

    @TableField("SELLER_BANK_ACCOUNT")
    private String sellerBankAccount;

    @TableField("TERMINAL_CODE")
    private String terminalCode;

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

    @TableField("BUYER_MOBILE")
    private String buyerMobile;

    @TableField("BUYER_EMAIL")
    private String buyerEmail;

    @TableField("DRAWER")
    private String drawer;

    @TableField("PAYEE")
    private String payee;

    @TableField("CHECKER")
    private String checker;

    @TableField("INVOICE_REMARK")
    private String invoiceRemark;

    @TableField("RED_INFO_NO")
    private String redInfoNo;

    @TableField("ORIGINAL_INVOICE_CODE")
    private String originalInvoiceCode;

    @TableField("ORIGINAL_INVOICE_NO")
    private String originalInvoiceNo;

    @TableField("REPLACE_INVOICE_CODE")
    private String replaceInvoiceCode;

    @TableField("REPLACE_INVOICE_NO")
    private String replaceInvoiceNo;

    @TableField("CANCEL_DATE")
    private String cancelDate;

    @TableField("CANCEL_PERSON")
    private String cancelPerson;

    @TableField("STATUS")
    private Integer status;

    @TableField("CREATE_TIME")
    private BigDecimal createTime;

    @TableField("MODIFIER")
    private BigDecimal modifier;

    @TableField("MODIFY_TIME")
    private Long modifyTime;

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

    @TableField("ORIGINAL_BILL_CODE")
    private String originalBillCode;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
