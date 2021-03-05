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
@TableName("BMS_SU_REBATE_DTL")
public class BmsSuRebateDtl extends Model<BmsSuRebateDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("REBATEDTLID")
    private Long rebatedtlid;

    @TableField("REBATEDOCID")
    private Long rebatedocid;

    @TableField("DTLTYPE")
    private Integer dtltype;

    @TableField("JAVACLASSNAME")
    private String javaclassname;

    @TableField("JAVANAME")
    private String javaname;

    @TableField("PARAMJSTRING")
    private String paramjstring;

    @TableField("DESCRIBE")
    private String describe;


    @Override
    protected Serializable pkVal() {
        return this.rebatedtlid;
    }

}
