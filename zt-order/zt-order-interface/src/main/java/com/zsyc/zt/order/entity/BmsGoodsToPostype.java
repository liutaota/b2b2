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
@TableName("BMS_GOODS_TO_POSTYPE")
public class BmsGoodsToPostype extends Model<BmsGoodsToPostype> {

    private static final long serialVersionUID = 1L;

    @TableId("DEFID")
    private Long defid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("POSTYPEID")
    private Long postypeid;

    @TableField("REFGOODSUSEQTY")
    private BigDecimal refgoodsuseqty;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("USINGRATE")
    private BigDecimal usingrate;


    @Override
    protected Serializable pkVal() {
        return this.defid;
    }

}
