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
@TableName("BMS_MV_DOC_HIS")
public class BmsMvDocHis extends Model<BmsMvDocHis> {

    private static final long serialVersionUID = 1L;

    @TableField("MOVEID")
    private Long moveid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("FROMSTORAGEID")
    private Long fromstorageid;

    @TableField("FROMCOMPANYID")
    private Long fromcompanyid;

    @TableField("TOSTORAGEID")
    private Long tostorageid;

    @TableField("TOCOMPANYID")
    private Long tocompanyid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("MVDIR")
    private Integer mvdir;

    @TableField("DELIVERMETHOD")
    private Integer delivermethod;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("MIDPOSID")
    private Long midposid;

    @TableField("TARGETPOSID")
    private Long targetposid;

    @TableField("URGENTFLAG")
    private Integer urgentflag;

    @TableField("TRANPRIORITY")
    private Integer tranpriority;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("PLACEMOVEFLAG")
    private Integer placemoveflag;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("INOUTREASON")
    private Integer inoutreason;

    @TableField("PLACESUPPLYID")
    private Long placesupplyid;

    @TableField("MOREMFLAG")
    private Integer moremflag;

    @TableField("MOTYPE")
    private Integer motype;

    @TableField("PROJECTID")
    private Long projectid;

    @TableField("DEALMANID")
    private Long dealmanid;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
