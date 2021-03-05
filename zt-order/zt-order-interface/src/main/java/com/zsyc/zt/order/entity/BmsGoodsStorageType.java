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
@TableName("BMS_GOODS_STORAGE_TYPE")
public class BmsGoodsStorageType extends Model<BmsGoodsStorageType> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSSTORTYEID")
    private Long goodsstortyeid;

    @TableField("STORTYPEOPCODE")
    private String stortypeopcode;

    @TableField("GOODSSTORTYE")
    private String goodsstortye;


    @Override
    protected Serializable pkVal() {
        return this.goodsstortyeid;
    }

}
