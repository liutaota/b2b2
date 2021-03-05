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
@TableName("BMS_SABK_STRG_TMP")
public class BmsSabkStrgTmp extends Model<BmsSabkStrgTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("SALESID")
    private Long salesid;

    @TableField("RGID")
    private Long rgid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
