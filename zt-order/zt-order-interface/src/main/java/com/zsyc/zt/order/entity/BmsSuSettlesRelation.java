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
@TableName("BMS_SU_SETTLES_RELATION")
public class BmsSuSettlesRelation extends Model<BmsSuSettlesRelation> {

    private static final long serialVersionUID = 1L;

    @TableField("OLDSUSETTLEDTLID")
    private Long oldsusettledtlid;

    @TableField("TOSUSETTLEDTLID")
    private Long tosusettledtlid;

    @TableId("SEQID")
    private Long seqid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
