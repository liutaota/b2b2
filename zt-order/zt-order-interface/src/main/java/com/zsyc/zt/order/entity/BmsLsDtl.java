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
@TableName("BMS_LS_DTL")
public class BmsLsDtl extends Model<BmsLsDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("LSID")
    private Long lsid;

    @TableId("LSDTLID")
    private Long lsdtlid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("LSREASON")
    private Integer lsreason;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("CALCFLAG")
    private Integer calcflag;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("COSTINGMONEY")
    private BigDecimal costingmoney;

    @TableField("STIFLAG")
    private Integer stiflag;

    @TableField("STIDATE")
    private LocalDateTime stidate;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("SKCERTID")
    private Long skcertid;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDTIME")
    private LocalDateTime invalidtime;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ZXTAXPRICE")
    private BigDecimal zxtaxprice;

    @TableField("ZXTAXMONEY")
    private BigDecimal zxtaxmoney;

    @TableField("UNQUALIFIEDMETHOD")
    private Integer unqualifiedmethod;

    @TableField("ISDESTROY")
    private Integer isdestroy;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;


    @Override
    protected Serializable pkVal() {
        return this.lsdtlid;
    }

}
