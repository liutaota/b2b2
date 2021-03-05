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
@TableName("BMS_ST_BOX")
public class BmsStBox extends Model<BmsStBox> {

    private static final long serialVersionUID = 1L;

    @TableId("BOXID")
    private Long boxid;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("BOXNO")
    private Long boxno;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;


    @Override
    protected Serializable pkVal() {
        return this.boxid;
    }

}
