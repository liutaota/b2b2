package com.zsyc.zt.order.entity;

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
@TableName("PUB_GOODS_HIS")
public class PubGoodsHis extends Model<PubGoodsHis> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("ZX_SPLIT_TYPE")
    private Integer zxSplitType;

    @TableField("GOODSTYPE")
    private String goodstype;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
