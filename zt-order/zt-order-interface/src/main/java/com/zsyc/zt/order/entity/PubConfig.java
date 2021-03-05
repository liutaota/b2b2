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
@TableName("PUB_CONFIG")
public class PubConfig extends Model<PubConfig> {

    private static final long serialVersionUID = 1L;

    @TableField("PKID")
    private Long pkid;

    @TableId("CONFIGKEY")
    private String configkey;

    @TableField("CONFIGVALUE")
    private String configvalue;


    @Override
    protected Serializable pkVal() {
        return this.configkey;
    }

}
