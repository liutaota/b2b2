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
@TableName("BMS_SA_CON_GOODSDTL")
public class BmsSaConGoodsdtl extends Model<BmsSaConGoodsdtl> {

    private static final long serialVersionUID = 1L;

    @TableId("CONGOODSDTLID")
    private Long congoodsdtlid;

    @TableField("CONDTLID")
    private Long condtlid;

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

    @TableField("DTLGOODSQTY")
    private BigDecimal dtlgoodsqty;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("EXECFLAG")
    private Integer execflag;

    @TableField("SALESDTLID")
    private Long salesdtlid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;


    @Override
    protected Serializable pkVal() {
        return this.congoodsdtlid;
    }

}
