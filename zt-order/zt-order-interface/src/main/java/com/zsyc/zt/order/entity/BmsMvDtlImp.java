package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_MV_DTL_IMP")
public class BmsMvDtlImp extends Model<BmsMvDtlImp> {

    private static final long serialVersionUID = 1L;

    @TableField("MOVEDTLID")
    private Long movedtlid;

    @TableField("MOVEID")
    private Long moveid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("COSTINGMONEY")
    private BigDecimal costingmoney;

    @TableField("STOFLAG")
    private Integer stoflag;

    @TableField("COSTCALCFLAG")
    private Integer costcalcflag;

    @TableField("STIFLAG")
    private Integer stiflag;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INSENDWMSFLAG")
    private Integer insendwmsflag;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
