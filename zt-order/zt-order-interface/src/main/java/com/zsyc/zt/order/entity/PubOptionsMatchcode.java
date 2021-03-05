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
@TableName("PUB_OPTIONS_MATCHCODE")
public class PubOptionsMatchcode extends Model<PubOptionsMatchcode> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    @TableField("NAME")
    private String name;

    @TableField("TABLECOLUMN")
    private String tablecolumn;

    @TableField("VALUE")
    private Long value;

    @TableField("OPTIONNAME")
    private String optionname;

    @TableField("WMSVALUE")
    private Long wmsvalue;

    @TableField("WMSNAME")
    private String wmsname;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
