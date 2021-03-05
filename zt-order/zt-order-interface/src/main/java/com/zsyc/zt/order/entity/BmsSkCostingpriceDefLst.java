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
@TableName("BMS_SK_COSTINGPRICE_DEF_LST")
public class BmsSkCostingpriceDefLst extends Model<BmsSkCostingpriceDefLst> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("STOCKID")
    private Long stockid;

    @TableField("COSTPRICE")
    private BigDecimal costprice;

    @TableId("SEQID")
    private Long seqid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
