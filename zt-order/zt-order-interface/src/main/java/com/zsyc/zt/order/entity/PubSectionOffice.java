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
@TableName("PUB_SECTION_OFFICE")
public class PubSectionOffice extends Model<PubSectionOffice> {

    private static final long serialVersionUID = 1L;

    @TableId("OFFICEID")
    private Long officeid;

    @TableField("OFFICEOPCODE")
    private String officeopcode;

    @TableField("OFFICEPINYIN")
    private String officepinyin;

    @TableField("OFFICENAME")
    private String officename;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.officeid;
    }

}
