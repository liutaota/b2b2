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
@TableName("BMS_FORBID_SALES_CUSTOM")
public class BmsForbidSalesCustom extends Model<BmsForbidSalesCustom> {

    private static final long serialVersionUID = 1L;

    @TableField("FORBIDID")
    private Long forbidid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableId("CUSTOMINFOID")
    private Long custominfoid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.custominfoid;
    }

}
