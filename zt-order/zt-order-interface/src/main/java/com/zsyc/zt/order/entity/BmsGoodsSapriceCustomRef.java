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
@TableName("BMS_GOODS_SAPRICE_CUSTOM_REF")
public class BmsGoodsSapriceCustomRef extends Model<BmsGoodsSapriceCustomRef> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYID")
    private Long entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("LASTSADTLID")
    private Long lastsadtlid;

    @TableField("LASTSAPRICE")
    private BigDecimal lastsaprice;

    @TableField("COMEFROM")
    private Integer comefrom;


    @Override
    protected Serializable pkVal() {
        return this.entryid;
    }

}
