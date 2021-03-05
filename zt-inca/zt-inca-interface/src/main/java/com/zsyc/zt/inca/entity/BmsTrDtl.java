package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
@TableName("BMS_TR_DTL")
@KeySequence("BMS_TR_DTL_SEQ")
public class BmsTrDtl extends Model<BmsTrDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("TRID")
    private Long trid;

    @TableId("TRDTLID")
    private Long trdtlid;

    @TableField("PREPARESTATUS")
    private Integer preparestatus;

    @TableField("CONFIGMANID")
    private Long configmanid;

    @TableField("CONFIGTIME")
    private LocalDateTime configtime;

    @TableField("SIGNFLOWFLAG")
    private Integer signflowflag;

    @TableField("CONSIGNMENTDATE")
    private LocalDateTime consignmentdate;

    @TableField("CONSIGNMENTMANID")
    private Long consignmentmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("SIGNFORMMANNAME")
    private String signformmanname;

    @TableField("SIGNFLOWTIME")
    private LocalDateTime signflowtime;

    @TableField("ALLOWTIME")
    private LocalDateTime allowtime;

    @TableField("SIGNFORMMAN")
    private Long signformman;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("ALLOWMANID")
    private Long allowmanid;

    @TableField("STORERID")
    private Integer storerid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("PICKBACKID")
    private Long pickbackid;

    @TableField("PICKBACKDATE")
    private LocalDateTime pickbackdate;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("WAVENO")
    private String waveno;

    @TableField("GSPUNCONFIRMFLAG")
    private Integer gspunconfirmflag;


    @Override
    protected Serializable pkVal() {
        return this.trdtlid;
    }

}
