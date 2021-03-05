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
@TableName("BMS_ST_ERA_DTL")
public class BmsStEraDtl extends Model<BmsStEraDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("ERADTLID")
    private Long eradtlid;

    @TableField("ERAID")
    private Long eraid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("PRINGFLAG")
    private Integer pringflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("ERAREASON")
    private Integer erareason;

    @TableField("ERAOUTWAY")
    private Integer eraoutway;

    @TableField("TOTALERAOUTQTY")
    private BigDecimal totaleraoutqty;

    @TableField("FINISHFLAG")
    private Integer finishflag;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("ERADTLINID")
    private Long eradtlinid;

    @TableField("GOODSSTATUSID")
    private Integer goodsstatusid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("SENDFLAG")
    private Integer sendflag;

    @TableField("WMSSTATUS")
    private Integer wmsstatus;

    @TableField("RECST")
    private Integer recst;

    @TableField("RECSTDATE")
    private LocalDateTime recstdate;


    @Override
    protected Serializable pkVal() {
        return this.eradtlid;
    }

}
