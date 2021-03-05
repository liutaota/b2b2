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
@TableName("PUB_GOODS_UNIT")
public class PubGoodsUnit extends Model<PubGoodsUnit> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("BASEFLAG")
    private Integer baseflag;

    @TableField("SUBGOODSUNIT")
    private String subgoodsunit;

    @TableField("SUBGOODSQTY")
    private BigDecimal subgoodsqty;

    @TableField("BASEUNITQTY")
    private BigDecimal baseunitqty;

    @TableField("GOODSUNITWEIGHT")
    private BigDecimal goodsunitweight;

    @TableField("GOODSUNITVOL")
    private BigDecimal goodsunitvol;

    @TableField("GOODSDTLFLAG")
    private Integer goodsdtlflag;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableId("SEQID")
    private Long seqid;

    @TableField("COLORSTRING")
    private String colorstring;

    @TableField("GOODSLENGTH")
    private BigDecimal goodslength;

    @TableField("GOODSWIDTH")
    private BigDecimal goodswidth;

    @TableField("GOODSHEIGHT")
    private BigDecimal goodsheight;

    @TableField("GOODSUNITID")
    private Long goodsunitid;

    @TableField("SUBGOODSUNITID")
    private Long subgoodsunitid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
