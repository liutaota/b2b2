package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_PRES_OUT_DTL_HIS")
public class BmsPresOutDtlHis extends Model<BmsPresOutDtlHis> {

    private static final long serialVersionUID = 1L;

    @TableField("PRESOUTID")
    private Long presoutid;

    @TableField("PRESOUTDTLID")
    private Long presoutdtlid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("RECST")
    private Integer recst;

    @TableField("RECSTDATE")
    private LocalDateTime recstdate;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("MEMO")
    private String memo;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("POLICYID")
    private Long policyid;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
