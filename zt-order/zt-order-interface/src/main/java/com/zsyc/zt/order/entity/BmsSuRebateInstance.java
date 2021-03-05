package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("BMS_SU_REBATE_INSTANCE")
public class BmsSuRebateInstance extends Model<BmsSuRebateInstance> {

    private static final long serialVersionUID = 1L;

    @TableId("INSTANCEID")
    private Long instanceid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("REBATEDOCID")
    private Long rebatedocid;

    @TableField("CALCFREQUENCY")
    private Long calcfrequency;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("FINALCALCMETHOD")
    private Integer finalcalcmethod;

    @TableField("FINALCALCDATE")
    private LocalDateTime finalcalcdate;

    @TableField("FINALCALCMANID")
    private Long finalcalcmanid;

    @TableField("AUTOCALCDATE")
    private LocalDateTime autocalcdate;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CALCINFO")
    private String calcinfo;


    @Override
    protected Serializable pkVal() {
        return this.instanceid;
    }

}
