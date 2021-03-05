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
@TableName("PUB_ROLE_CUSTOM_CLASSTYPE")
public class PubRoleCustomClasstype extends Model<PubRoleCustomClasstype> {

    private static final long serialVersionUID = 1L;

    @TableId("SCRID")
    private Long scrid;

    @TableField("ROLEID")
    private Long roleid;

    @TableField("CLASSTYPEID")
    private Long classtypeid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("USESTATUS")
    private Integer usestatus;


    @Override
    protected Serializable pkVal() {
        return this.scrid;
    }

}
