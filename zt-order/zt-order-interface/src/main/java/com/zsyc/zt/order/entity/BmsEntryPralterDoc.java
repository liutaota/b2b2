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
@TableName("BMS_ENTRY_PRALTER_DOC")
public class BmsEntryPralterDoc extends Model<BmsEntryPralterDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("ALTERDOCID")
    private Long alterdocid;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("ALTERTYPE")
    private Integer altertype;

    @TableField("PLANEXEDATE")
    private LocalDateTime planexedate;

    @TableField("MEMO")
    private String memo;

    @TableField("APPROVEFLAG")
    private Integer approveflag;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;

    @TableField("EXECFLAG")
    private Integer execflag;

    @TableField("EXECDATE")
    private LocalDateTime execdate;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("AUTOEXECFLAG")
    private Integer autoexecflag;

    @TableField("IMPORTID")
    private Long importid;


    @Override
    protected Serializable pkVal() {
        return this.alterdocid;
    }

}
