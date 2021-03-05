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
@TableName("BMS_MV_DOC")
public class BmsMvDoc extends Model<BmsMvDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("MOVEID")
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

    @TableField("ZX_CMDTYPE")
    private Integer zxCmdtype;

    @TableField("ZX_INOUTFLAG")
    private Integer zxInoutflag;

    @TableField("ZX_OUTINFLAG")
    private Integer zxOutinflag;

    /**
     * ????
     */
    @TableField("APPROVESTATUS")
    private Integer approvestatus;

    /**
     * ???
     */
    @TableField("APPROVEMANNAME")
    private String approvemanname;

    /**
     * ???ID
     */
    @TableField("APPROVEMANID")
    private Long approvemanid;

    /**
     * ????
     */
    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;

    /**
     * ????
     */
    @TableField("APPROVEMEMO")
    private String approvememo;


    @Override
    protected Serializable pkVal() {
        return this.moveid;
    }

}
