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
@TableName("BMS_CLOUD_SERVICE")
public class BmsCloudService extends Model<BmsCloudService> {

    private static final long serialVersionUID = 1L;

    @TableId("SERVICEID")
    private Long serviceid;

    @TableField("SERVICENAME")
    private String servicename;

    @TableField("SERVICEURL")
    private String serviceurl;

    @TableField("COMPANYID")
    private Long companyid;


    @Override
    protected Serializable pkVal() {
        return this.serviceid;
    }

}
