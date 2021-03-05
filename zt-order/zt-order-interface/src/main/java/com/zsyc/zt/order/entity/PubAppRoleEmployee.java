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
@TableName("PUB_APP_ROLE_EMPLOYEE")
public class PubAppRoleEmployee extends Model<PubAppRoleEmployee> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("GROUPID")
    private Long groupid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
