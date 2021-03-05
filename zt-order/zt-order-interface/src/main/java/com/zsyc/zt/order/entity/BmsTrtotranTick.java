package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_TRTOTRAN_TICK")
public class BmsTrtotranTick extends Model<BmsTrtotranTick> {

    private static final long serialVersionUID = 1L;

    @TableField("ROADDTLID")
    private Long roaddtlid;

    @TableField("TRID")
    private Long trid;

    @TableField("TRDTLID")
    private Long trdtlid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
