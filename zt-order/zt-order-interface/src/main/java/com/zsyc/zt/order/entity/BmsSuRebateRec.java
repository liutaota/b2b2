package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SU_REBATE_REC")
public class BmsSuRebateRec extends Model<BmsSuRebateRec> {

    private static final long serialVersionUID = 1L;

    @TableId("RECID")
    private Long recid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("TYPE")
    private Integer type;

    @TableField("REBATEDOCID")
    private Long rebatedocid;

    @TableField("INSTANCEID")
    private Long instanceid;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("CERTID")
    private Long certid;

    @TableField("SUCONSTATUS")
    private Integer suconstatus;

    @TableField("SUCONID")
    private Long suconid;

    @TableField("SUSETDOCID")
    private Long susetdocid;

    @TableField("SUSETSTATUS")
    private Integer susetstatus;

    @TableField("SUDOCSTATUS")
    private Integer sudocstatus;

    @TableField("SUDOCID")
    private Long sudocid;

    @TableField("SETTORECID")
    private Long settorecid;

    @TableField("CREATETYPE")
    private Integer createtype;

    @TableField("MEMO")
    private String memo;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("AGENTMANID")
    private Long agentmanid;

    @TableField("CONFIGCLASS")
    private Integer configclass;

    @TableField("CLUES")
    private Integer clues;

    @TableField("FREQUENCY")
    private Integer frequency;

    @TableField("FORM")
    private Integer form;


    @Override
    protected Serializable pkVal() {
        return this.recid;
    }

}
