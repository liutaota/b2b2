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
@TableName("BMS_ENTRY_GOODS_PRICE")
public class BmsEntryGoodsPrice extends Model<BmsEntryGoodsPrice> {

    private static final long serialVersionUID = 1L;

    @TableId("ENPRICEID")
    private Long enpriceid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("GRADE")
    private Integer grade;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("DISCOUNT")
    private BigDecimal discount;

    @TableField("REFRENCEPRICEID")
    private Long refrencepriceid;

    @TableField("REFRENCEPRICE")
    private BigDecimal refrenceprice;


    @Override
    protected Serializable pkVal() {
        return this.enpriceid;
    }

}
