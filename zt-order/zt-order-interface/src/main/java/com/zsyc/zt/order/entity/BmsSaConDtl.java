package com.zsyc.zt.order.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
@TableName("BMS_SA_CON_DTL")
@KeySequence("BMS_SA_CON_DTL_SEQ")
public class BmsSaConDtl extends Model<BmsSaConDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("CONID")
    private Long conid;

    @TableId("CONDTLID")
    private Long condtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("WHOLESALEPRICE")
    private BigDecimal wholesaleprice;

    @TableField("RESALEPRICE")
    private BigDecimal resaleprice;

    @TableField("DISCOUNT")
    private BigDecimal discount;

    @TableField("TIMEPRICEID")
    private Long timepriceid;

    @TableField("TIMEPRICE")
    private BigDecimal timeprice;

    @TableField("ACCSTQTY")
    private BigDecimal accstqty;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDTIME")
    private LocalDateTime invalidtime;

    @TableField("SPECIALLOTFLAG")
    private Integer speciallotflag;

    @TableField("LOTMEMO")
    private String lotmemo;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("LOWERFLAG")
    private Integer lowerflag;

    @TableField("SAPLACEQTY")
    private BigDecimal saplaceqty;

    @TableField("CUSTOMLASTPRICE")
    private BigDecimal customlastprice;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("USEPACKSIZE")
    private BigDecimal usepacksize;

    @TableField("REQPRINTQUFLAG")
    private Integer reqprintquflag;

    @TableField("AGREEMENTFLAG")
    private Integer agreementflag;

    @TableField("AGRCONDTLID")
    private Long agrcondtlid;

    @TableField("OLDUNITPRICE")
    private BigDecimal oldunitprice;

    @TableField("OOSREC_FLAG")
    private Integer oosrecFlag;

    @TableField("ZX_CMDID")
    private Long zxCmdid;

    @TableField("SALESDTLID")
    private Long salesdtlid;


    @Override
    protected Serializable pkVal() {
        return this.condtlid;
    }

}
