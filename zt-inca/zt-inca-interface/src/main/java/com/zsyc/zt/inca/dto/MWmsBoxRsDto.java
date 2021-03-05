package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value =   "")
@Data
public class MWmsBoxRsDto implements Serializable {

    @ApiModelProperty(name  = "")
    private String sourceid;

    @ApiModelProperty(name  = "")
    private String sourceerpid;

    @ApiModelProperty(name  = "")
    private Integer cmdtype;

    @ApiModelProperty(name  = "")
    private String scustomerid;

    @ApiModelProperty(name  = "")
    private String customername;

    @ApiModelProperty(name  = "")
    private Long cmdsetid;

    @ApiModelProperty(name  = "")
    private String boxno;

    @ApiModelProperty(name  = "")
    private Integer goodsunitflag;

    @ApiModelProperty(name  = "")
    private Long packdocid;

    @ApiModelProperty(name  = "")
    private Integer writebackflag;

    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime writedate;


}
