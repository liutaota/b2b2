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
@TableName("PUB_BID_PRICE_DTL")
public class PubBidPriceDtl extends Model<PubBidPriceDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("BIDDTLID")
    private Long biddtlid;

    @TableField("BIDID")
    private Long bidid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICE")
    private BigDecimal price;


    @Override
    protected Serializable pkVal() {
        return this.biddtlid;
    }

}
