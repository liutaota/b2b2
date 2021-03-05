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
@TableName("PUB_GOODS_TYPE_IMP")
public class PubGoodsTypeImp extends Model<PubGoodsTypeImp> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("ZX_GOODS_TYPE")
    private Integer zxGoodsType;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
