package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/*总单和调度细单关系表*/
@TableName("BMS_TR_BACK_INSERT")
@Data
@KeySequence("Bms_tr_back_seq")
public class BmsTrBackInsert extends Model<BmsTrBackInsert> {
    private static final long serialVersionUID = 1L;
    @TableId(value = "seqid",type = IdType.INPUT)
    private Long seqid;
    @TableField(value = "credate", fill = FieldFill.UPDATE,update = "SYSDATE")
    private Date credate;
    @TableField("type")
    private int type;
    @TableField("trdtlid")
    private Long trdtlid;
    @TableField("comefrom")
    private int comefrom;
    @TableField("sourceid")
    private Long sourceid;
    private Long trid;
}
