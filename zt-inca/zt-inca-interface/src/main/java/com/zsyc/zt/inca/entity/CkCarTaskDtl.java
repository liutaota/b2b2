package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CK_CAR_TASK_DTL")
@KeySequence("CK_CAR_TASK_DTL_SEQ")
public class CkCarTaskDtl extends Model<CkCarTaskDtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务细单ID
     */
    @ApiModelProperty(value="任务细单ID")
    @TableId(value = "TASK_DTL_ID")
    private Long taskDtlId;

    /**
     * 任务总单ID
     */
    @ApiModelProperty(value="任务总单ID")
    @TableField("TASK_ID")
    private Long taskId;

    /**
     * 出车调度单ID
     */
    @ApiModelProperty(value="出车调度单ID")
    @TableField("SCHEDULE_ID")
    private Long scheduleId;


    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID")
    @TableField("CUSTOM_ID")
    private Long customId;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称")
    @TableField("CUSTOM_NAME")
    private String customName;

    /**
     * 运输线路序号
     */
    @ApiModelProperty(value="客户名称")
    @TableField("TRANSORTNO")
    private Long tranSortNo;

    /**
     * 任务日期
     */
    @ApiModelProperty(value="任务日期")
    @TableField("TASK_DATE")
    private LocalDate taskDate;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    @TableField("USESTATUS")
    private Integer usestatus;



    @Override
    protected Serializable pkVal() {
        return this.taskDtlId;
    }

}
