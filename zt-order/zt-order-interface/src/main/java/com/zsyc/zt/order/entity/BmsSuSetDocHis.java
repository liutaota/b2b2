package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SU_SET_DOC_HIS")
public class BmsSuSetDocHis extends Model<BmsSuSetDocHis> {

    private static final long serialVersionUID = 1L;

    @TableField("SUSETDOCID")
    private Long susetdocid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("TOTAL_IN")
    private BigDecimal totalIn;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("MEMO")
    private String memo;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("SETTLEMODE")
    private Integer settlemode;

    @TableField("CERTID")
    private Long certid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("SETTYPE")
    private Integer settype;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("COSTTYPE")
    private Integer costtype;

    @TableField("TAXRATEDOC")
    private BigDecimal taxratedoc;

    @TableField("MAKEUPTYPE")
    private Long makeuptype;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
