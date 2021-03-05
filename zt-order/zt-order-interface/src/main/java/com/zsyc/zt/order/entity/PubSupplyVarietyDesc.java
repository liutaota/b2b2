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
@TableName("PUB_SUPPLY_VARIETY_DESC")
public class PubSupplyVarietyDesc extends Model<PubSupplyVarietyDesc> {

    private static final long serialVersionUID = 1L;

    @TableField("VARIETYID")
    private Long varietyid;

    @TableId("DESCID")
    private Long descid;

    @TableField("DESCSEQ")
    private Long descseq;

    @TableField("DESCR")
    private String descr;


    @Override
    protected Serializable pkVal() {
        return this.descid;
    }

}
