package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(value =   "报表参数")
public class ReportDto implements Serializable {

    /**
     *
     * 时间范围
     * startTIme
     * endTime
     */

    @ApiModelProperty(name  = "开始时间",  example = "2020-11-11 11:11:11")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime startTime;

    @ApiModelProperty(name  = "结束时间",  example = "2020-12-11 11:11:11")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime endTime;

    //类别id集合
    private Long[] classIds;





}
