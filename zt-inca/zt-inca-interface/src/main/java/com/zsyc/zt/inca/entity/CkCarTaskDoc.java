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
import java.time.LocalDateTime;

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
@TableName("CK_CAR_TASK_DOC")
@KeySequence("CK_CAR_TASK_DOC_SEQ")
public class CkCarTaskDoc extends Model<CkCarTaskDoc> {

    private static final long serialVersionUID = 1L;

    /**
     * 出车任务单ID
     */
    @TableId("TASK_ID")
    @ApiModelProperty(value="出车任务单ID")
    private Long taskId;

    /**
     * 出车调度单ID
     */
    @ApiModelProperty(value="出车调度单ID")
    @TableField("SCHEDULE_ID")
    private Long scheduleId;

    /**
     * 录入人ID
     */
    @ApiModelProperty(value="录入人ID")
    @TableField("INPUTMAN_ID")
    private Long inputmanId;

    /**
     * 录入日期
     */
    @ApiModelProperty(value="录入日期")
    @TableField("CREDATE")
    private LocalDateTime credate;

    /**
     * 确认人ID
     */
    @ApiModelProperty(value="确认人ID")
    @TableField("CONFIRMMAN_ID")
    private Long confirmmanId;

    /**
     * 确认日期
     */
    @ApiModelProperty(value="确认日期")
    @TableField("CONFIRM_DATE")
    private LocalDateTime confirmDate;

    /**
     * 回退确认人ID
     */
    @ApiModelProperty(value="回退确认人ID")
    @TableField("UNCONFIRM_ID")
    private Long unconfirmId;

    /**
     * 回退确认日期
     */
    @ApiModelProperty(value="回退确认日期")
    @TableField("UNCONFIRM_DATE")
    private LocalDateTime unconfirmDate;

    /**
     * 状态(1临时，2正式)
     */
    @ApiModelProperty(value="状态(1临时，2正式)")
    @TableField("USESTATUS")
    private Integer usestatus;

    /**
     * 任务执行日期
     */
    @ApiModelProperty(value="任务执行日期")
    @TableField("TASK_DATE")
    private LocalDate taskDate;

    /**
     * 销售单条数
     */
    @ApiModelProperty(value="销售单条数")
    @TableField("DTL_LINES")
    private Integer dtlLines;

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }

}
