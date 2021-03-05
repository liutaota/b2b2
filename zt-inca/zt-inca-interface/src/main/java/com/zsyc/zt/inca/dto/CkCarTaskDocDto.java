package com.zsyc.zt.inca.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 欧朝森
 */
@ApiModel("车辆任务总单表-前端")
@Data
@EqualsAndHashCode(callSuper = false)
public class CkCarTaskDocDto implements Serializable {

    /**
     * 出车任务单ID
     */
    @ApiModelProperty(value="出车任务单ID")
    private Long taskId;

    /**
     * 出车调度单ID
     */
    @ApiModelProperty(value="出车调度单ID")
    private Long scheduleId;

    /**
     * 录入人ID
     */
    @ApiModelProperty(value="录入人ID")
    private Long inputmanId;

    /**
     * 录入日期
     */
    @ApiModelProperty(value="录入日期")
    private LocalDateTime credate;

    /**
     * 确认人ID
     */
    @ApiModelProperty(value="确认人ID")
    private Long confirmmanId;

    /**
     * 确认日期
     */
    @ApiModelProperty(value="确认日期")
    private LocalDateTime confirmDate;

    /**
     * 回退确认人ID
     */
    @ApiModelProperty(value="回退确认人ID")
    private Long unconfirmId;

    /**
     * 回退确认日期
     */
    @ApiModelProperty(value="回退确认日期")
    private LocalDateTime unconfirmDate;

    /**
     * 状态(1临时，2正式)
     */
    @ApiModelProperty(value="状态(1临时，2正式)")
    private Integer usestatus;

    /**
     * 任务执行日期
     */
    @ApiModelProperty(value="任务执行日期")
    private LocalDate taskDate;

    /**
     * 销售单条数
     */
    @ApiModelProperty(value="销售单条数")
    private Integer dtlLines;

    /**
     * 细单数据集合
     */
    @ApiModelProperty(value="细单数据集合")
    private List<CkCarTaskDtlDto> ckCarTaskDtlList;

}
