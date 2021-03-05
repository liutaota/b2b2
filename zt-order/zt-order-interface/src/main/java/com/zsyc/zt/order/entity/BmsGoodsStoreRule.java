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
@TableName("BMS_GOODS_STORE_RULE")
public class BmsGoodsStoreRule extends Model<BmsGoodsStoreRule> {

    private static final long serialVersionUID = 1L;

    @TableId("RULEID")
    private Long ruleid;

    @TableField("CLASS_IDSET")
    private String classIdset;

    @TableField("PICKRATE_IDSET")
    private String pickrateIdset;

    @TableField("GOODSSTATUS_IDSET")
    private String goodsstatusIdset;

    @TableField("STORELEVEL")
    private Integer storelevel;

    @TableField("CLASS_SETS")
    private String classSets;

    @TableField("PICKRATE_SETS")
    private String pickrateSets;

    @TableField("GOODSSTATUS_SETS")
    private String goodsstatusSets;

    @TableField("STORERID")
    private Long storerid;


    @Override
    protected Serializable pkVal() {
        return this.ruleid;
    }

}
