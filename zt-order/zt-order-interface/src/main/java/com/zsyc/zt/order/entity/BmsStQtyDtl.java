package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_ST_QTY_DTL")
public class BmsStQtyDtl extends Model<BmsStQtyDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("CUTID")
    private Long cutid;

    @TableId("CUTDTLID")
    private Long cutdtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("REALQTY")
    private BigDecimal realqty;

    @TableField("CUTFLAG")
    private Integer cutflag;

    @TableField("CUTMANID")
    private Long cutmanid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ISCONFIRM")
    private Integer isconfirm;


    @Override
    protected Serializable pkVal() {
        return this.cutdtlid;
    }

}
