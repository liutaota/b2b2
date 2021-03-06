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
@TableName("BMS_CLOUD_EMPLOYEE")
public class BmsCloudEmployee extends Model<BmsCloudEmployee> {

    private static final long serialVersionUID = 1L;

    @TableId("TELEPHONE")
    private Long telephone;

    @TableField("EMPLOYEENAME")
    private String employeename;

    @TableField("EMPLOYEEOPCODE")
    private String employeeopcode;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("SERVICEID")
    private Long serviceid;

    @TableField("ERPEMPLOYEEID")
    private Long erpemployeeid;

    @TableField("PASSWORD")
    private String password;

    @TableField("MD5COUNT")
    private Long md5count;

    @TableField("ISMANAGER")
    private Integer ismanager;


    @Override
    protected Serializable pkVal() {
        return this.telephone;
    }

}
