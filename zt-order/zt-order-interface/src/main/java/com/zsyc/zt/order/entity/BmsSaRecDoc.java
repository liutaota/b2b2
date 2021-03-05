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
@TableName("BMS_SA_REC_DOC")
public class BmsSaRecDoc extends Model<BmsSaRecDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SARECID")
    private Long sarecid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("MEMO")
    private String memo;

    @TableField("RECTYPEID")
    private Integer rectypeid;

    @TableField("RECMETHOD")
    private Integer recmethod;

    @TableField("BANKBILLNO")
    private String bankbillno;

    @TableField("CERTID")
    private Long certid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PREMONEY")
    private BigDecimal premoney;

    @TableField("RECEXPMONEY")
    private BigDecimal recexpmoney;

    @TableField("RECEXPTYPE")
    private Integer recexptype;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("PRESALERID")
    private Long presalerid;

    @TableField("PRESADEPTID")
    private Long presadeptid;

    @TableField("BANKID")
    private Long bankid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("OLDPRERECID")
    private Long oldprerecid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("DRAFTDATE")
    private LocalDateTime draftdate;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("FINISHFLAG")
    private Integer finishflag;

    @TableField("NOTESID")
    private Long notesid;

    @TableField("RECMANID")
    private Long recmanid;

    @TableField("NOTESNO")
    private String notesno;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("B2BCONID")
    private Long b2bconid;

    @TableField("ORDERID")
    private String orderid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ROADDTLID")
    private Long roaddtlid;

    @TableField("SWIFTNUMBER")
    private String swiftnumber;

    @TableField("PAYTYPE")
    private String paytype;

    @TableField("ZX_INITSATUS")
    private Integer zxInitsatus;


    @Override
    protected Serializable pkVal() {
        return this.sarecid;
    }

}
