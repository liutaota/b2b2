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
@TableName("BMS_GOODS_STORE_RULE_DTL")
public class BmsGoodsStoreRuleDtl extends Model<BmsGoodsStoreRuleDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("RULEID")
    private Long ruleid;

    @TableId("RULEDTLID")
    private Long ruledtlid;

    @TableField("STHOUSEID")
    private Long sthouseid;

    @TableField("PRIORITY")
    private Long priority;


    @Override
    protected Serializable pkVal() {
        return this.ruledtlid;
    }

}
