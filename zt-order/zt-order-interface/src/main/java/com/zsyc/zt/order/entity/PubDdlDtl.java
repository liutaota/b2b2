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
@TableName("PUB_DDL_DTL")
public class PubDdlDtl extends Model<PubDdlDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("SYSID")
    private Long sysid;

    @TableId("SYSDTLID")
    private Long sysdtlid;

    @TableField("DDLID")
    private Long ddlid;

    @TableField("DDLNAME")
    private String ddlname;


    @Override
    protected Serializable pkVal() {
        return this.sysdtlid;
    }

}
