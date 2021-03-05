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
@TableName("BMS_ST_DEF")
public class BmsStDef extends Model<BmsStDef> {

    private static final long serialVersionUID = 1L;

    @TableId("STORAGEID")
    private Long storageid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("STORAGENO")
    private String storageno;

    @TableField("STORAGENAME")
    private String storagename;

    @TableField("GOODSDTLFLAG")
    private Integer goodsdtlflag;

    @TableField("BATCHFLAG")
    private Integer batchflag;

    @TableField("LOTFLAG")
    private Integer lotflag;

    @TableField("POSFLAG")
    private Integer posflag;

    @TableField("PHYSTOREID")
    private Long phystoreid;

    @TableField("MEMO")
    private String memo;

    @TableField("PROCFLAG")
    private Integer procflag;

    @TableField("AUTOCRTFLAG")
    private Integer autocrtflag;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("EMPID")
    private Long empid;

    @TableField("STORAGETYPE")
    private Integer storagetype;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("ZX_CHECK_IN_SORT")
    private Long zxCheckInSort;

    @TableField("ZX_CHECK_OUT_SORT")
    private Long zxCheckOutSort;

    @TableField("ZX_MV_STORAGEID")
    private Long zxMvStorageid;

    @TableField("ZX_UNQUALIFIED_STORAGEID")
    private Long zxUnqualifiedStorageid;


    @Override
    protected Serializable pkVal() {
        return this.storageid;
    }

}
