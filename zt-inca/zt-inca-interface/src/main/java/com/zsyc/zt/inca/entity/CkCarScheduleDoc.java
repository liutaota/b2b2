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
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CK_CAR_SCHEDULE_DOC")
@KeySequence("CK_CAR_SCHEDULE_DOC_SEQ")
public class CkCarScheduleDoc extends Model<CkCarScheduleDoc> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="调度记录id")
    @TableId("SCHEDULE_ID")
    private Long scheduleId;

    @ApiModelProperty(value="司机id")
    @TableField("DRIVER_ID")
    private Long driverId;

    @ApiModelProperty(value="线路id")
    @TableField("TRANSLINE_ID")
    private Long translineId;

    @ApiModelProperty(value="车辆id")
    @TableField("VEHICLE_ID")
    private Long vehicleId;

    @ApiModelProperty(value="录入人id")
    @TableField("INPUTMAN_ID")
    private Long inputmanId;

    @ApiModelProperty(value="录入日期")
    @TableField("CREDATE")
    private LocalDateTime credate;

    @ApiModelProperty(value="确认人ID")
    @TableField("CONFIRMMAN_ID")
    private Long confirmmanId;

    @ApiModelProperty(value="确认日期")
    @TableField("CONFIRM_DATE")
    private LocalDateTime confirmDate;

    @ApiModelProperty(value="状态：1、临时 2、正式")
    @TableField("USESTATUS")
    private BigDecimal usestatus;

    @ApiModelProperty(value="调度日期")
    @TableField("SCHEDULE_DATE")
    private LocalDateTime scheduleDate;

    @ApiModelProperty(value="回退确认人id")
    @TableField("UNCONFIRM_ID")
    private Long unconfirmId;

    @ApiModelProperty(value="回退确认日期")
    @TableField("UNCONFIRM_DATE")
    private LocalDateTime unconfirmDate;

    @Override
    protected Serializable pkVal() {
        return this.scheduleId;
    }

}
