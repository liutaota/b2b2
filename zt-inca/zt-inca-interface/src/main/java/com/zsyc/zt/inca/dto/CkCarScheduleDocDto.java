package com.zsyc.zt.inca.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-12-02
 */
@ApiModel("调度单-前端")
@Data
@EqualsAndHashCode(callSuper = false)
public class CkCarScheduleDocDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="调度单ID")
    private Long scheduleId;

    @ApiModelProperty(value="司机ID")
    private Long driverId;

    @ApiModelProperty(value="线路ID")
    private Long translineId;

    @ApiModelProperty(value="车辆ID")
    private Long vehicleId;

    @ApiModelProperty(value="状态，1，临时，2 正式")
    private BigDecimal usestatus;

    @ApiModelProperty(value="调度日期")
    private LocalDateTime scheduleDate;

    @ApiModelProperty(value="车牌号码")
    private String vehiclenum;

    @ApiModelProperty(value="司机名字")
    private String employeename;

    @ApiModelProperty(value="路线")
    private String translinename;

    @ApiModelProperty(value="路线、车牌、司机")
    private String option;
}
