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
@TableName("BMS_ZW_CHECK_SQL")
public class BmsZwCheckSql extends Model<BmsZwCheckSql> {

    private static final long serialVersionUID = 1L;

    @TableId("CHECKED")
    private Long checked;

    @TableField("CHECKTYPE")
    private Integer checktype;

    @TableField("SQLTYPE")
    private Integer sqltype;

    @TableField("CELLAREA")
    private String cellarea;

    @TableField("SQLMEMO")
    private String sqlmemo;

    @TableField("TYPEID")
    private Long typeid;


    @Override
    protected Serializable pkVal() {
        return this.checked;
    }

}
