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
@TableName("BMS_SA_APPROVE_MEMO")
public class BmsSaApproveMemo extends Model<BmsSaApproveMemo> {

    private static final long serialVersionUID = 1L;

    @TableId("APPROVEID")
    private Long approveid;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SAVEMEMO")
    private String savememo;

    @TableField("APPROVEMEMO")
    private String approvememo;


    @Override
    protected Serializable pkVal() {
        return this.approveid;
    }

}
