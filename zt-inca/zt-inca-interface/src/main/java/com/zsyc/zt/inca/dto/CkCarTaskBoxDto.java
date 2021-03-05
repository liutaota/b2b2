package com.zsyc.zt.inca.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel("任务销售单装箱表-前端")
@Data
@EqualsAndHashCode(callSuper = false)
public class CkCarTaskBoxDto implements Serializable {
    /**
     * 装箱记录id
     */
    @ApiModelProperty(value="装箱记录id")
    private Long taskBoxId;

    /**
     * 任务id
     */
    @ApiModelProperty(value="任务id")
    private Long taskId;

    /**
     * 任务细单id
     */
    @ApiModelProperty(value="任务细单id")
    private Long taskDtlId;

    /**
     * 销售总单集合
     */
    @ApiModelProperty(value="销售总单集合")
    private String salesIdList;

    /**
     * 箱号
     */
    @ApiModelProperty(value="箱号")
    private String packId;

    /**
     * 创建日期
     */
    @ApiModelProperty(value="创建日期")
    private LocalDateTime createDate;


}
