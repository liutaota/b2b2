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
@TableName("BMS_SA_SETTLES_RELATION")
public class BmsSaSettlesRelation extends Model<BmsSaSettlesRelation> {

    private static final long serialVersionUID = 1L;

    @TableField("OLDSASETTLEDTLID")
    private Long oldsasettledtlid;

    @TableField("TOSASETTLEDTLID")
    private Long tosasettledtlid;

    @TableId("SEQID")
    private Long seqid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
