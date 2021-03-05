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
@TableName("BMS_UP_TASK_DOC")
public class BmsUpTaskDoc extends Model<BmsUpTaskDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("TASKID")
    private Long taskid;

    @TableField("STHOUSEID")
    private Long sthouseid;

    @TableField("RGID")
    private Long rgid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("PRINTCOUNT")
    private Integer printcount;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return this.taskid;
    }

}
