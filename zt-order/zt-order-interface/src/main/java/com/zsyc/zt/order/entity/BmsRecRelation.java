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
@TableName("BMS_REC_RELATION")
public class BmsRecRelation extends Model<BmsRecRelation> {

    private static final long serialVersionUID = 1L;

    @TableId("OLDRECDTLID")
    private Long oldrecdtlid;

    @TableField("TORECDTLID")
    private Long torecdtlid;


    @Override
    protected Serializable pkVal() {
        return this.oldrecdtlid;
    }

}
