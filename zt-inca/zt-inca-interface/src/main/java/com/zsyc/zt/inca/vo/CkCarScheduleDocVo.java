package com.zsyc.zt.inca.vo;

import io.swagger.annotations.ApiModel;
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
 * @since 2020-12-02
 */
@ApiModel(value =  "出车调度")
@Data
@EqualsAndHashCode(callSuper = false)
public class CkCarScheduleDocVo implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "调度ID")
    private Long scheduleId;

    @ApiModelProperty(value ="司机ID")
    private Long driverId;

    @ApiModelProperty(value ="司机名称")
    private String driverName;

    @ApiModelProperty(value="线路ID")
    private Long translineId;

    @ApiModelProperty(value="路线")
    private String translineName;

    @ApiModelProperty(value="车辆ID")
    private Long vehicleId;

    @ApiModelProperty(value="车牌号码")
    private String vehicleNum;

    @ApiModelProperty(value="车牌型号")
    private String vehicleModel;

    @ApiModelProperty(value="状态，1，临时，2 正式")
    private Integer usestatus;

    @ApiModelProperty(value="调度日期")
    private LocalDate scheduleDate;

}
