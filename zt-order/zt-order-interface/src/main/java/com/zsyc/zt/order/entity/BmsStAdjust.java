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
@TableName("BMS_ST_ADJUST")
public class BmsStAdjust extends Model<BmsStAdjust> {

    private static final long serialVersionUID = 1L;

    @TableId("STADJUSTID")
    private Long stadjustid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("STADJREASON")
    private Integer stadjreason;

    @TableField("MEMO")
    private String memo;

    @TableField("STADJUSTTYPE")
    private Integer stadjusttype;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("AUTOMOVE")
    private Integer automove;

    @TableField("MOVEFLAG")
    private Integer moveflag;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ADJUSTMANID")
    private Long adjustmanid;

    @TableField("ADJUSTDATE")
    private LocalDateTime adjustdate;

    @TableField("GOTFLAG")
    private Integer gotflag;


    @Override
    protected Serializable pkVal() {
        return this.stadjustid;
    }

}
