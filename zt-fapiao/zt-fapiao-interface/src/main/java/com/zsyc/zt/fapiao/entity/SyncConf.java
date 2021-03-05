package com.zsyc.zt.fapiao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYNC_CONF")
public class SyncConf extends Model<SyncConf> {

    private static final long serialVersionUID = 1L;

    @TableField("PARAM_NAME")
    private String paramName;

    @TableField("PARAM_VALUE")
    private String paramValue;

    @TableField("PARAM_DESC")
    private String paramDesc;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
