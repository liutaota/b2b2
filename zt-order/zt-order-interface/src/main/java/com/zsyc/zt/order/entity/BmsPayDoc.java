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
@TableName("BMS_PAY_DOC")
public class BmsPayDoc extends Model<BmsPayDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PAYID")
    private Long payid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("BANKDOCNO")
    private String bankdocno;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("TOTAL_OUT")
    private BigDecimal totalOut;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("PAYTYPE")
    private Integer paytype;

    @TableField("MEMO")
    private String memo;

    @TableField("BANKID")
    private Long bankid;

    @TableField("ACCNO")
    private String accno;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("CERTID")
    private Long certid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PAYEXMONEY")
    private BigDecimal payexmoney;

    @TableField("PAYEXTYPE")
    private Integer payextype;

    @TableField("CONID")
    private Long conid;

    @TableField("TOTAL_LINES")
    private BigDecimal totalLines;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("OLDPREPAYID")
    private Long oldprepayid;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("PAYEXTAX")
    private BigDecimal payextax;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("NOTESID")
    private Long notesid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("REMITTANCEID")
    private Long remittanceid;

    @TableField("APPLYCOST")
    private BigDecimal applycost;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("REQPRINTCOUNT")
    private Long reqprintcount;

    @TableField("REQPRINTMANID")
    private Long reqprintmanid;

    @TableField("REQPRINTDATE")
    private LocalDateTime reqprintdate;

    @TableField("PREPRINTCOUNT")
    private Long preprintcount;

    @TableField("PREPRINTMANID")
    private Long preprintmanid;

    @TableField("PREPRINTDATE")
    private LocalDateTime preprintdate;

    @TableField("ZX_BANKNAME")
    private String zxBankname;

    @TableField("ZX_ACCNO")
    private String zxAccno;

    @TableField("ZX_INITSATUS")
    private Integer zxInitsatus;


    @Override
    protected Serializable pkVal() {
        return this.payid;
    }

}
