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
@TableName("BMS_PRES_OUT_DOC_HIS")
public class BmsPresOutDocHis extends Model<BmsPresOutDocHis> {

    private static final long serialVersionUID = 1L;

    @TableField("PRESOUTID")
    private Long presoutid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("POLICYTYPE")
    private Integer policytype;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("POLICYID")
    private Long policyid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("PLACESUPPLYID")
    private Long placesupplyid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
