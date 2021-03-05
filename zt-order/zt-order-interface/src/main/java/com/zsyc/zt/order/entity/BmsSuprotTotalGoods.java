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
@TableName("BMS_SUPROT_TOTAL_GOODS")
public class BmsSuprotTotalGoods extends Model<BmsSuprotTotalGoods> {

    private static final long serialVersionUID = 1L;

    @TableId("PROTDTLID")
    private Long protdtlid;

    @TableField("PROTID")
    private Long protid;

    @TableField("GOODSID")
    private Long goodsid;


    @Override
    protected Serializable pkVal() {
        return this.protdtlid;
    }

}
