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
@TableName("BMS_TR_FETCH_DTL")
public class BmsTrFetchDtl extends Model<BmsTrFetchDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("FETCHID")
    private Long fetchid;

    @TableId("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("SALEDTLID")
    private Long saledtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("BATCHFLAG")
    private Integer batchflag;

    @TableField("BATCHPRICE")
    private BigDecimal batchprice;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("WMSPRINTDATE")
    private LocalDateTime wmsprintdate;

    @TableField("DRIVERGETBACKDATE")
    private LocalDateTime drivergetbackdate;

    @TableField("DRIVERGETBACKDEMO")
    private String drivergetbackdemo;

    @TableField("BACKWHYID")
    private Long backwhyid;

    @TableField("BACKWAYID")
    private Integer backwayid;

    @TableField("INQTY")
    private BigDecimal inqty;

    @TableField("REFUSEQTY")
    private BigDecimal refuseqty;

    @TableField("INTRUSTQTY")
    private BigDecimal intrustqty;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("COLDEQUIP")
    private Integer coldequip;

    @TableField("RECEIVEQTY")
    private BigDecimal receiveqty;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("ACCQTY")
    private BigDecimal accqty;

    @TableField("UNQUALIFIEDQTY")
    private BigDecimal unqualifiedqty;

    @TableField("REUSESTATUS")
    private Integer reusestatus;

    @TableField("RGUSESTATUS")
    private Integer rgusestatus;

    @TableField("ZX_SALESDOCID")
    private Long zxSalesdocid;

    @TableField("ZX_SALECREDATE")
    private LocalDateTime zxSalecredate;

    @TableField("ZX_TAXRATE")
    private BigDecimal zxTaxrate;


    @Override
    protected Serializable pkVal() {
        return this.fetchdtlid;
    }

}
