package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("PUB_SERIALNO")
public class PubSerialno extends Model<PubSerialno> {

    private static final long serialVersionUID = 1L;

    @TableId("SERIALNOID")
    private Long serialnoid;

    @TableField("SERIALNONAME")
    private String serialnoname;

    @TableField("CURRENTVALUE")
    private Long currentvalue;

    @TableField("TEMPLATE")
    private String template;

    @TableField("RESETTYPE")
    private Integer resettype;


    @Override
    protected Serializable pkVal() {
        return this.serialnoid;
    }

}
