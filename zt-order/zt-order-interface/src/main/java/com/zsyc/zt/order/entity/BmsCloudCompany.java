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
@TableName("BMS_CLOUD_COMPANY")
public class BmsCloudCompany extends Model<BmsCloudCompany> {

    private static final long serialVersionUID = 1L;

    @TableId("COMPANYID")
    private Long companyid;

    @TableField("COMPANYNAME")
    private String companyname;

    @TableField("COMPANYOPCODE")
    private String companyopcode;

    @TableField("MEMO")
    private String memo;

    @TableField("LICENSEUSER")
    private Long licenseuser;


    @Override
    protected Serializable pkVal() {
        return this.companyid;
    }

}
