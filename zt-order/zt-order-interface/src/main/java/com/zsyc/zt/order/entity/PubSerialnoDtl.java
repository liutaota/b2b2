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
@TableName("PUB_SERIALNO_DTL")
public class PubSerialnoDtl extends Model<PubSerialnoDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("SERIALNOID")
    private Long serialnoid;

    @TableId("SERIALNODTLID")
    private Long serialnodtlid;

    @TableField("SERIALNOYEAR")
    private Integer serialnoyear;

    @TableField("SERIALNOMONTH")
    private Integer serialnomonth;

    @TableField("SERIALNODAY")
    private Integer serialnoday;

    @TableField("CURRENTVALUE")
    private Long currentvalue;


    @Override
    protected Serializable pkVal() {
        return this.serialnodtlid;
    }

}
