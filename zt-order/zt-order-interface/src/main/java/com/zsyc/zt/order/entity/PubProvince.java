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
@TableName("PUB_PROVINCE")
public class PubProvince extends Model<PubProvince> {

    private static final long serialVersionUID = 1L;

    @TableId("PROVINCEID")
    private Long provinceid;

    @TableField("PROVINCENAME")
    private String provincename;

    @TableField("PROVINCEPINYIN")
    private String provincepinyin;

    @TableField("PROVINCEOPCODE")
    private String provinceopcode;


    @Override
    protected Serializable pkVal() {
        return this.provinceid;
    }

}
