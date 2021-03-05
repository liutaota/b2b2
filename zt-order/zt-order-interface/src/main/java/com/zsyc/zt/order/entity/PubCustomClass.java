package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("PUB_CUSTOM_CLASS")
public class PubCustomClass extends Model<PubCustomClass> {

    private static final long serialVersionUID = 1L;

    @TableId("CLASSID")
    private Long classid;

    @TableField("CLASSTYPEID")
    private Long classtypeid;

    @TableField("CLASSNO")
    private String classno;

    @TableField("CLASSNAME")
    private String classname;

    @TableField("LEAFFLAG")
    private Integer leafflag;

    @TableField("PARENTCLASSID")
    private Long parentclassid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return this.classid;
    }

}
