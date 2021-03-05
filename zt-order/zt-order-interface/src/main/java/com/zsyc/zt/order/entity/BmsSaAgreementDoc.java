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
@TableName("BMS_SA_AGREEMENT_DOC")
public class BmsSaAgreementDoc extends Model<BmsSaAgreementDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("AGRCONID")
    private Long agrconid;

    @TableField("AGRCONNO")
    private String agrconno;

    @TableField("CONTYPE")
    private Integer contype;

    @TableField("CUSTOMERID")
    private Long customerid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("SIGNDATE")
    private LocalDateTime signdate;

    @TableField("SIGNADDRESS")
    private String signaddress;

    @TableField("SIGNMANID")
    private Long signmanid;

    @TableField("VALIDBEGINDATE")
    private LocalDateTime validbegindate;

    @TableField("VALIDENDDATE")
    private LocalDateTime validenddate;

    @TableField("SUMMARY")
    private String summary;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERDEPTID")
    private Long salerdeptid;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("FMID")
    private Long fmid;

    @TableField("CREATEMODE")
    private Integer createmode;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("WTOFLG")
    private Integer wtoflg;

    @TableField("NEEDTRACFLG")
    private Integer needtracflg;

    @TableField("INEXPFLAG")
    private Integer inexpflag;

    @TableField("CUSTOMREQUEST")
    private String customrequest;

    @TableField("CUSTOMORDERNO")
    private String customorderno;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.agrconid;
    }

}
