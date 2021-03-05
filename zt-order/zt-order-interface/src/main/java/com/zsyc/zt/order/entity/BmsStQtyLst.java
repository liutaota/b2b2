package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
@TableName("BMS_ST_QTY_LST")
public class BmsStQtyLst extends Model<BmsStQtyLst> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSID")
    private Long goodsid;

    @TableField("GOODSDETAILID")
    private Long goodsdetailid;

    @TableField("STORAGEID")
    private Long storageid;

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



    //实际库存
    @TableField(exist = false)
    private Double qtyFact;

    @TableField(exist = false)
    private Date proddate;

    @TableField(exist = false)
    private Date invaliddate;

    @Override
    protected Serializable pkVal() {
        return this.goodsid;
    }

}
