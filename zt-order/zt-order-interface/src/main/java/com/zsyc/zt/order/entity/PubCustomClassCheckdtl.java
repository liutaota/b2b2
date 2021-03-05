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
@TableName("PUB_CUSTOM_CLASS_CHECKDTL")
public class PubCustomClassCheckdtl extends Model<PubCustomClassCheckdtl> {

    private static final long serialVersionUID = 1L;

    @TableId("CHEDTLID")
    private Long chedtlid;

    @TableField("CHEID")
    private Long cheid;

    @TableField("CLASSCON")
    private String classcon;

    @TableField("CONEXPLAIN")
    private String conexplain;


    @Override
    protected Serializable pkVal() {
        return this.chedtlid;
    }

}
