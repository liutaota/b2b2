package com.zsyc.zt.order.entity;

import java.math.BigDecimal;

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
@TableName("BMS_TR_PICK_DOC")
@KeySequence("BMS_TR_PICK_DOC_SEQ")
public class BmsTrPickDoc extends Model<BmsTrPickDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PICKDOCID")
    private Long pickdocid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTCOUNT")
    private Integer printcount;

    @TableField("TOTALPACKQTY")
    private Long totalpackqty;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("FINISHDATE")
    private LocalDateTime finishdate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("MEMO")
    private String memo;

    @TableField("PICKMANID")
    private Long pickmanid;

    @TableField("BOXMANID")
    private Long boxmanid;

    @TableField("DESKID")
    private Long deskid;

    @TableField("WAVENO")
    private String waveno;

    @TableField("DRAWSTATUS")
    private Integer drawstatus;

    @TableField("PICKDATE")
    private LocalDateTime pickdate;

    @TableField("BOXQTY")
    private BigDecimal boxqty;

    @TableField("FINISHMANID")
    private Long finishmanid;

    @TableField("FINISH2MANID")
    private Long finish2manid;

    @TableField("TRANSLINEID")
    private String translineid;

    @TableField("PICKAREASID")
    private Long pickareasid;

    @TableField("CONTAINERID")
    private Long containerid;

    @TableField("TRID")
    private Long trid;

    @TableField("ZX_COMPANYID")
    private Long zxCompanyid;

    @TableField("ZX_COMPANYNAME")
    private String zxCompanyname;

    @TableField("ZX_ISTOTAL")
    private Integer zxIstotal;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("ZX_SEAT")
    private String zxSeat;


    @Override
    protected Serializable pkVal() {
        return this.pickdocid;
    }

}
