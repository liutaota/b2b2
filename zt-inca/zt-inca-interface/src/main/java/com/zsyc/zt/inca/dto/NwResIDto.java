package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("仓库物流 -前端")
@Data
@EqualsAndHashCode(callSuper = false)
public class NwResIDto implements Serializable {


    @ApiModelProperty(value = "")
    private String wmsItemId;

    @ApiModelProperty(value = "")
    private String erpId;

    @ApiModelProperty(value = "")
    private String erpTypeId;

    @ApiModelProperty(value = "")
    private String itemId;

    @ApiModelProperty(value = "")
    private String invId;

    @ApiModelProperty(value = "")
    private String batCode;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime prodAt;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime exprAt;

    @ApiModelProperty(value = "")
    private Long qty;

    @ApiModelProperty(value = "")
    private BigDecimal qty1;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime movedAt;

    @ApiModelProperty(value = "")
    private Long checkedQty;

    @ApiModelProperty(value = "")
    private BigDecimal checkedQty1;

    @ApiModelProperty(value = "")
    private String movedBy;

    @ApiModelProperty(value = "")
    private String movedByName;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime checkedAt;

    @ApiModelProperty(value = "")
    private String checkedBy;

    @ApiModelProperty(value = "")
    private String checkedByName;

    @ApiModelProperty(value = "")
    private String checkedBy2;

    @ApiModelProperty(value = "")
    private String checkedByName2;

    @ApiModelProperty(value = "")
    private Integer isFcl;

    @ApiModelProperty(value = "")
    private String seatId;

    @ApiModelProperty(value = "")
    private String movedBy2;

    @ApiModelProperty(value = "")
    private String movedByName2;

    @ApiModelProperty(value = "")
    private String fdaCode;

    @ApiModelProperty(value = "")
    private String sid;

    @ApiModelProperty(value = "")
    private String packId;

    @ApiModelProperty(value = "")
    private String cancelledBy;

    @ApiModelProperty(value = "")
    private Integer zxIsload;



}
