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
@TableName("PUB_EMPLOYEE_ROLE")
public class PubEmployeeRole extends Model<PubEmployeeRole> {

    private static final long serialVersionUID = 1L;

    @TableId("EMPROLEID")
    private Long emproleid;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("ROLEID")
    private Long roleid;

    @TableField("EMPROLESORTNUM")
    private Integer emprolesortnum;


    @Override
    protected Serializable pkVal() {
        return this.emproleid;
    }

}
