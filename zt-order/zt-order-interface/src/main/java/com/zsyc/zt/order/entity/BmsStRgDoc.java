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
@TableName("BMS_ST_RG_DOC")
public class BmsStRgDoc extends Model<BmsStRgDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("RGID")
    private Long rgid;

    @TableField("RGTYPE")
    private Integer rgtype;

    @TableField("FREIGHTBILLNO")
    private String freightbillno;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("UNQUALIFIEDQTY")
    private BigDecimal unqualifiedqty;

    @TableField("QUALIFIEDQTY")
    private BigDecimal qualifiedqty;

    @TableField("REQUALIFIEDQTY")
    private BigDecimal requalifiedqty;

    @TableField("REUNQUALIFIEDQTY")
    private BigDecimal reunqualifiedqty;

    @TableField("REGOODSQTY")
    private BigDecimal regoodsqty;

    @TableField("SALERID")
    private Long salerid;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("REINPUTMANID")
    private Long reinputmanid;

    @TableField("RECREDATE")
    private LocalDateTime recredate;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("PRESENTSTORAGEID")
    private Long presentstorageid;

    @TableField("ERASTORAGEID")
    private Long erastorageid;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("PLACERETURNDTLID")
    private Long placereturndtlid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("RGGOODSQTY")
    private BigDecimal rggoodsqty;

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

    @TableField("REDOCID")
    private Long redocid;

    @TableField("REDTLID")
    private Long redtlid;

    @TableField("RECHECKSTATUS")
    private Integer recheckstatus;

    @TableField("RECHECKMANID")
    private Long recheckmanid;

    @TableField("RECHECKDATE")
    private LocalDateTime recheckdate;

    @TableField("ZX_PRINTFLAG")
    private Integer zxPrintflag;

    @TableField("ZX_PRINTDATE")
    private LocalDateTime zxPrintdate;

    @TableField("ZX_PRINTMANID")
    private Long zxPrintmanid;

    @TableField("ZX_PRINTNUM")
    private Long zxPrintnum;


    @Override
    protected Serializable pkVal() {
        return this.rgid;
    }

}
