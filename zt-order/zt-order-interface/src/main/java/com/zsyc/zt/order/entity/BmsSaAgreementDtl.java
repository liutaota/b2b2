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
@TableName("BMS_SA_AGREEMENT_DTL")
public class BmsSaAgreementDtl extends Model<BmsSaAgreementDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("AGRCONID")
    private Long agrconid;

    @TableId("AGRCONDTLID")
    private Long agrcondtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSQTY1")
    private BigDecimal goodsqty1;

    @TableField("GOODSUNIT1")
    private String goodsunit1;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("EXECCOUNT")
    private Integer execcount;

    @TableField("FINDATE")
    private LocalDateTime findate;

    @TableField("WHOLESALEPRICE")
    private BigDecimal wholesaleprice;

    @TableField("RESALEPRICE")
    private BigDecimal resaleprice;

    @TableField("DISCOUNT")
    private BigDecimal discount;

    @TableField("ACCSTQTY")
    private BigDecimal accstqty;

    @TableField("ACCSTBAKQTY")
    private BigDecimal accstbakqty;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("STRICTEXECFLAG")
    private Integer strictexecflag;

    @TableField("TOTALOUTQTY")
    private BigDecimal totaloutqty;

    @TableField("TOTALOUTBACKQTY")
    private BigDecimal totaloutbackqty;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("WTOFLG")
    private Integer wtoflg;

    @TableField("NEEDTRACFLG")
    private Integer needtracflg;

    @TableField("INNORDQTY")
    private BigDecimal innordqty;

    @TableField("NEEDTRACKFLG")
    private Integer needtrackflg;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("PRODUSESTATUS")
    private Integer produsestatus;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("DEADLINE")
    private LocalDateTime deadline;

    @TableField("PRODINDATE")
    private LocalDateTime prodindate;

    @TableField("INVPRICE")
    private BigDecimal invprice;


    @Override
    protected Serializable pkVal() {
        return this.agrcondtlid;
    }

}
