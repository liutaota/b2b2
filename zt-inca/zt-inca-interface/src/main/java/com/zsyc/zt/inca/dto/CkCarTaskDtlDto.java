package com.zsyc.zt.inca.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class CkCarTaskDtlDto implements Serializable {
    /**
     * 任务细单ID
     */
    @ApiModelProperty(value="任务细单ID")
    @TableId("TASK_DTL_ID")
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
     * 销售总单ID
     */
    @ApiModelProperty(value="销售总单ID")
    @TableField("SALES_ID")
    private Long salesId;

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
    @ApiModelProperty(value="运输线路序号")
    @TableField("TRANSORTNO")
    private Long tranSortNo;

    /**
     * 任务日期
     */
    @ApiModelProperty(value="任务日期")
    @TableField("TASK_DATE")
    private LocalDate taskDate;

//    /**
//     * 是否选择客户
//     */
//    @ApiModelProperty(value="是否选择客户")
//    private Boolean  choseCustom;
//
//    /**
//     * 客户列表
//     */
//    @ApiModelProperty(value="客户列表")
//    private Set<NwResIVo> customSet;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    @TableField("USESTATUS")
    private Integer usestatus;

    /**
     * 货品装修数据集合
     */
    @ApiModelProperty(value="货品装修数据集合")
    private List<CkCarTaskBoxDto> ckCarTaskBoxList;

    /**
     * 箱号
     */
    @ApiModelProperty(value="箱号")
    private String packId;

}
