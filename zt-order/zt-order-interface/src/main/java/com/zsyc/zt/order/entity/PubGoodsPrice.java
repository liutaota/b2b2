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
@TableName("PUB_GOODS_PRICE")
public class PubGoodsPrice extends Model<PubGoodsPrice> {

    private static final long serialVersionUID = 1L;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("DISCOUNT")
    private BigDecimal discount;

    @TableField("REFRENCEPRICEID")
    private Long refrencepriceid;

    @TableField("REFRENCEPRICE")
    private BigDecimal refrenceprice;

    @TableId("GDSPRICEID")
    private Long gdspriceid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYID")
    private Integer entryid;


    @Override
    protected Serializable pkVal() {
        return this.gdspriceid;
    }

}
