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
@TableName("BMS_PAY_DTL")
public class BmsPayDtl extends Model<BmsPayDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("PAYDTLID")
    private Long paydtlid;

    @TableField("PAYID")
    private Long payid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("CORRECTFLAG")
    private Integer correctflag;

    @TableField("CERTID")
    private Long certid;

    @TableField("CDATE")
    private LocalDateTime cdate;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INVNO")
    private String invno;

    @TableField("GENFACSINGLEREBFLAG")
    private Integer genfacsinglerebflag;

    @TableField("GENSUPSINGLEREBFLAG")
    private Integer gensupsinglerebflag;

    @TableField("GENFACTOTALREBFLAG")
    private Integer genfactotalrebflag;

    @TableField("GENSUPTOTALREBFLAG")
    private Integer gensuptotalrebflag;

    @TableField("TRANSDOCNO")
    private String transdocno;

    @TableField("PAYPLANDTLID")
    private Long payplandtlid;

    @TableField("ZX_PREPAYFLAG")
    private Integer zxPrepayflag;


    @Override
    protected Serializable pkVal() {
        return this.paydtlid;
    }

}
