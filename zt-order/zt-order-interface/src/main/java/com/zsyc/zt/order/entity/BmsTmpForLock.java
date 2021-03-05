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
@TableName("BMS_TMP_FOR_LOCK")
public class BmsTmpForLock extends Model<BmsTmpForLock> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    @TableField("VALUE")
    private String value;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
