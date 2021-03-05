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
@TableName("BMS_PICK_AREAS_DTL")
public class BmsPickAreasDtl extends Model<BmsPickAreasDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("PICKAREASDTLID")
    private Long pickareasdtlid;

    @TableField("PICKAREASID")
    private Long pickareasid;

    @TableField("STHOUSEID")
    private Long sthouseid;


    @Override
    protected Serializable pkVal() {
        return this.pickareasdtlid;
    }

}
