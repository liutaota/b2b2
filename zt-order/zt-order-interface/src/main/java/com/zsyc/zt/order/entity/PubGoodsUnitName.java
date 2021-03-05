package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("PUB_GOODS_UNIT_NAME")
public class PubGoodsUnitName extends Model<PubGoodsUnitName> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSUNITID")
    private Long goodsunitid;

    @TableField("GOODSUNITNAME")
    private String goodsunitname;

    @TableField("GOODSUNITOPCODE")
    private String goodsunitopcode;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MODIFYLOG")
    private String modifylog;


    @Override
    protected Serializable pkVal() {
        return this.goodsunitid;
    }

}
