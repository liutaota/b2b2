package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CK_CAR_TASK_BOX")
@KeySequence("CK_CAR_TASK_BOX_SEQ")
public class CkCarTaskBox extends Model<CkCarTaskBox> {

    private static final long serialVersionUID = 1L;

    /**
     * 装箱记录id
     */
    @ApiModelProperty(value="装箱记录id")
    @TableId("TASK_BOX_ID")
    private Long taskBoxId;

    /**
     * 任务id
     */
    @ApiModelProperty(value="任务id")
    @TableField("TASK_ID")
    private Long taskId;

    /**
     * 任务细单id
     */
    @ApiModelProperty(value="任务细单id")
    @TableField("TASK_DTL_ID")
    private Long taskDtlId;

    /**
     * 销售总单id
     */
    @ApiModelProperty(value="销售总单id")
    @TableField("SALES_ID_LIST")
    private String salesIdList;


    /**
     * 箱号
     */
    @ApiModelProperty(value="箱号")
    @TableField("PACK_ID")
    private String packId;

    /**
     * 创建日期
     */
    @ApiModelProperty(value="创建日期")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @Override
    protected Serializable pkVal() {
        return this.taskBoxId;
    }

}
