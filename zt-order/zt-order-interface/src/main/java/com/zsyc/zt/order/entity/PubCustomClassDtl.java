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
@TableName("PUB_CUSTOM_CLASS_DTL")
public class PubCustomClassDtl extends Model<PubCustomClassDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("CLASSTYPEID")
    private Long classtypeid;

    @TableField("CLASSID")
    private Long classid;

    @TableId("CUSTOMID")
    private Long customid;


    @Override
    protected Serializable pkVal() {
        return this.customid;
    }

}
