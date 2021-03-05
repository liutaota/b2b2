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
@TableName("BMS_SU_REBATE_INSTANCE_REF")
public class BmsSuRebateInstanceRef extends Model<BmsSuRebateInstanceRef> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("INSTANCEID")
    private Long instanceid;

    @TableField("DOCTYPE")
    private Integer doctype;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("BATCHID")
    private Long batchid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
