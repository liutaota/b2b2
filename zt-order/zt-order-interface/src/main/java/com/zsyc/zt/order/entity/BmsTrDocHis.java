package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_TR_DOC_HIS")
public class BmsTrDocHis extends Model<BmsTrDocHis> {

    private static final long serialVersionUID = 1L;

    @TableField("TRID")
    private Long trid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("TRANFLAG")
    private Integer tranflag;

    @TableField("TRNO")
    private String trno;

    @TableField("FROMCOMPANYID")
    private Long fromcompanyid;

    @TableField("TOCOMPANYID")
    private Long tocompanyid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("TRANPRIORITY")
    private Integer tranpriority;

    @TableField("URGENTFLAG")
    private Integer urgentflag;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("TARGETPOSID")
    private Long targetposid;

    @TableField("SELFTRANTYPE")
    private Integer selftrantype;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
