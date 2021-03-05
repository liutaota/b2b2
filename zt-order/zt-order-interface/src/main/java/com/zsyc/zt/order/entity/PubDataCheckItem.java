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
@TableName("PUB_DATA_CHECK_ITEM")
public class PubDataCheckItem extends Model<PubDataCheckItem> {

    private static final long serialVersionUID = 1L;

    @TableId("CHECKITEMID")
    private Long checkitemid;

    @TableField("CHECKCATEGORYID")
    private Long checkcategoryid;

    @TableField("CHECKITEMNAME")
    private String checkitemname;

    @TableField("CONDITION")
    private String condition;


    @Override
    protected Serializable pkVal() {
        return this.checkitemid;
    }

}
