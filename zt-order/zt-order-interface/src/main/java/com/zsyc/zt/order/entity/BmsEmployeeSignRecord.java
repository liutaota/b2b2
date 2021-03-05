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
@TableName("BMS_EMPLOYEE_SIGN_RECORD")
public class BmsEmployeeSignRecord extends Model<BmsEmployeeSignRecord> {

    private static final long serialVersionUID = 1L;

    @TableId("RECORDID")
    private Long recordid;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("EMPLOYEENAME")
    private String employeename;

    @TableField("EMPLOYEETEL")
    private String employeetel;

    @TableField("SIGNOUTTIME")
    private LocalDateTime signouttime;

    @TableField("SIGNINTIME")
    private LocalDateTime signintime;


    @Override
    protected Serializable pkVal() {
        return this.recordid;
    }

}
