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
@TableName("BMS_ST_FIX_SYS_MODEL")
public class BmsStFixSysModel extends Model<BmsStFixSysModel> {

    private static final long serialVersionUID = 1L;

    @TableId("MODELID")
    private Integer modelid;

    @TableField("FIXMODELNAME")
    private String fixmodelname;

    @TableField("SELECTBITMAP")
    private String selectbitmap;

    @TableField("SELECTCLAUSE")
    private String selectclause;

    @TableField("HAVINGCLAUSE")
    private String havingclause;

    @TableField("GROUPBYCLAUSE")
    private String groupbyclause;

    @TableField("ORDERBYCLAUSE")
    private String orderbyclause;

    @TableField("MEMO")
    private String memo;

    @TableField("QUONDAMID")
    private Long quondamid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("QUBUFFID")
    private Long qubuffid;


    @Override
    protected Serializable pkVal() {
        return this.modelid;
    }

}
