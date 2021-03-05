package com.zsyc.zt.inca.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ApiModel("车辆任务细单表 -前端")
@Data
@EqualsAndHashCode(callSuper = false)
public class CkCarTaskDtlVo implements Serializable {
    /**
     * 任务细单ID
     */
    @ApiModelProperty(value="任务细单ID")
    private Long taskDtlId;

    /**
     * 任务总单ID
     */
    @ApiModelProperty(value="任务总单ID")
    private Long taskId;

    /**
     * 出车调度单ID
     */
    @ApiModelProperty(value="出车调度单ID")
    private Long scheduleId;

    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID")
    private Long customId;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称")
    private String customName;

    /**
     * 运输线路序号
     */
    @ApiModelProperty(value="运输线路序号")
    private Long tranSortNo;

    /**
     * 任务日期
     */
    @ApiModelProperty(value="任务日期")
    private String salesList;

    /**
     * 任务日期
     */
    @ApiModelProperty(value="任务日期")
    private LocalDate taskDate;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    private Integer usestatus;

    /**
     * 货品箱集合
     */
    @ApiModelProperty(value="货品箱集合")
    private List<CkCarTaskBoxVo> ckCarTaskBoxList;

}
