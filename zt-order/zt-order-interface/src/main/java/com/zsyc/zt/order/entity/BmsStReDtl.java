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
@TableName("BMS_ST_RE_DTL")
public class BmsStReDtl extends Model<BmsStReDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("REDOCID")
    private Long redocid;

    @TableId("REDTLID")
    private Long redtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("OLDGOODSQTY")
    private BigDecimal oldgoodsqty;

    @TableField("CANGOODSQTY")
    private BigDecimal cangoodsqty;

    @TableField("QUALIFIEDQTY")
    private BigDecimal qualifiedqty;

    @TableField("UNQUALIFIEDQTY")
    private BigDecimal unqualifiedqty;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("HASPERSENDFLAG")
    private Integer haspersendflag;

    @TableField("PERSENDINFO")
    private String persendinfo;

    @TableField("SALERID")
    private Long salerid;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("PRESENTSTORAGEID")
    private Long presentstorageid;

    @TableField("ERASTORAGEID")
    private Long erastorageid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PACKFALG")
    private Integer packfalg;

    @TableField("UNPACKFALG")
    private Integer unpackfalg;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("PLACERETURNDTLID")
    private Long placereturndtlid;

    @TableField("GSPUNCONFIRMFLAG")
    private Integer gspunconfirmflag;

    @TableField("REALPRICE")
    private BigDecimal realprice;

    @TableField("REALMONEY")
    private BigDecimal realmoney;

    @TableField("TWOINVOICEINFLAG")
    private Integer twoinvoiceinflag;

    @TableField("SUCONID")
    private Long suconid;

    @TableField("FETCHID")
    private Long fetchid;

    @TableField("UNINVQTY")
    private BigDecimal uninvqty;

    @TableField("FILECOUNT")
    private Long filecount;

    @TableField("UPLOADQTY")
    private BigDecimal uploadqty;

    @TableField("INVQTY")
    private BigDecimal invqty;

    @TableField("REALQTY")
    private BigDecimal realqty;

    @TableField("ZX_RECMANID")
    private Long zxRecmanid;

    @TableField("ZX_RECDATE")
    private LocalDateTime zxRecdate;

    @TableField("BACKWHYID")
    private Long backwhyid;

    @TableField("JD_ORDER_ID")
    private BigDecimal jdOrderId;


    @Override
    protected Serializable pkVal() {
        return this.redtlid;
    }

}
