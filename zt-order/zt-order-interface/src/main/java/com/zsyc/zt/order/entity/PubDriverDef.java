package com.zsyc.zt.order.entity;

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
@TableName("PUB_DRIVER_DEF")
public class PubDriverDef extends Model<PubDriverDef> {

    private static final long serialVersionUID = 1L;

    @TableId("DRIVERID")
    private Long driverid;

    @TableField("LICENSENO")
    private String licenseno;

    @TableField("LICENSETYPE")
    private String licensetype;

    @TableField("VALIDDATE")
    private LocalDateTime validdate;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.driverid;
    }

}
