package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PUB_VEHICLE_DEF")
public class PubVehicleDef extends Model<PubVehicleDef> {

    private static final long serialVersionUID = 1L;

    @TableId("VEHICLEID")
    private Long vehicleid;

    @TableField("VEHICLENUM")
    private String vehiclenum;

    @TableField("VEHICLEMODEL")
    private String vehiclemodel;

    @TableField("DRIVERID")
    private Long driverid;

    @TableField("LOADCAPACITY")
    private BigDecimal loadcapacity;

    @TableField("VEHICLESIZE")
    private BigDecimal vehiclesize;

    @TableField("MEMO")
    private String memo;

    @TableField("PRELOADNUM")
    private BigDecimal preloadnum;

    @TableField("ANNUALSURVEYDATE")
    private LocalDateTime annualsurveydate;


    @Override
    protected Serializable pkVal() {
        return this.vehicleid;
    }

}
