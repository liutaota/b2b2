package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_DEF")
@ApiModel(value="BmsStDef对象", description="")
@KeySequence(value = "BMS_ST_DEF_SEQ")
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
    private Date sysModifydate;

    @TableField("TOWMSDATE")
    private Date towmsdate;

    @TableField("ZX_CHECK_IN_SORT")
    private Long zxCheckInSort;

    @TableField("ZX_CHECK_OUT_SORT")
    private Long zxCheckOutSort;

    @TableField("ZX_MV_STORAGEID")
    private Long zxMvStorageid;

    @TableField("ZX_UNQUALIFIED_STORAGEID")
    private Long zxUnqualifiedStorageid;


}
