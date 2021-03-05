package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("PUB_ZTLIN_WWW")
public class PubZtlinWww extends Model<PubZtlinWww> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("PACKSIAE_B")
    private Long packsiaeB;

    @TableField("PACKSIZE_M")
    private Long packsizeM;

    @TableField("AGENT")
    private String agent;

    @TableField("PRICE_NEW")
    private BigDecimal priceNew;

    @TableField("PRICE_M")
    private BigDecimal priceM;

    @TableField("PRICE_B")
    private BigDecimal priceB;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
