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
@TableName("BMS_ST_REM_QTY")
public class BmsStRemQty extends Model<BmsStRemQty> {

    private static final long serialVersionUID = 1L;

    @TableId("USEMM")
    private Long usemm;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("REMGOODSQTY")
    private BigDecimal remgoodsqty;


    @Override
    protected Serializable pkVal() {
        return this.usemm;
    }

}
