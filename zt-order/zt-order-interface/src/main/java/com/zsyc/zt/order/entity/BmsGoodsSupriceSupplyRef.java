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
@TableName("BMS_GOODS_SUPRICE_SUPPLY_REF")
public class BmsGoodsSupriceSupplyRef extends Model<BmsGoodsSupriceSupplyRef> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYID")
    private Long entryid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("LASTSUDTLID")
    private Long lastsudtlid;

    @TableField("LASTPRICE")
    private BigDecimal lastprice;

    @TableField("LOWESTSUDTLID")
    private Long lowestsudtlid;

    @TableField("LOWESTPRICE")
    private BigDecimal lowestprice;

    @TableField("HIGHESTSUDTLID")
    private Long highestsudtlid;

    @TableField("HIGHESTPRICE")
    private BigDecimal highestprice;


    @Override
    protected Serializable pkVal() {
        return this.entryid;
    }

}
