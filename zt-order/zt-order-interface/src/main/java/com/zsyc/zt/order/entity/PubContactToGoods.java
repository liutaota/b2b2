package com.zsyc.zt.order.entity;

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
@TableName("PUB_CONTACT_TO_GOODS")
public class PubContactToGoods extends Model<PubContactToGoods> {

    private static final long serialVersionUID = 1L;

    @TableId("CONTACTGOODSID")
    private Long contactgoodsid;

    @TableField("CONTACTPANYID")
    private Long contactpanyid;

    @TableField("GOODSID")
    private Long goodsid;


    @Override
    protected Serializable pkVal() {
        return this.contactgoodsid;
    }

}
