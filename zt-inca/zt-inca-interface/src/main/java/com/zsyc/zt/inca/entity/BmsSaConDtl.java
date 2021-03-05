package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@KeySequence(value = "BMS_SA_CON_DTL_SEQ")
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
    private Double goodsqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private Double goodsuseqty;

    @TableField("UNITPRICE")
    private Double unitprice;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("WHOLESALEPRICE")
    private Double wholesaleprice;

    @TableField("RESALEPRICE")
    private Double resaleprice;

    @TableField("DISCOUNT")
    private Double discount;

    @TableField("TIMEPRICEID")
    private Long timepriceid;

    @TableField("TIMEPRICE")
    private Double timeprice;

    @TableField("ACCSTQTY")
    private Double accstqty;

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
    private Double saplaceqty;

    @TableField("CUSTOMLASTPRICE")
    private Double customlastprice;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("USEPACKSIZE")
    private Double usepacksize;

    @TableField("REQPRINTQUFLAG")
    private Integer reqprintquflag;

    @TableField("AGREEMENTFLAG")
    private Integer agreementflag;

    @TableField("AGRCONDTLID")
    private Long agrcondtlid;

    @TableField("OLDUNITPRICE")
    private Double oldunitprice;

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
