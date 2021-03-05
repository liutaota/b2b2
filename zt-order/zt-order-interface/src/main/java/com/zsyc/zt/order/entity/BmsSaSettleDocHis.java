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
@TableName("BMS_SA_SETTLE_DOC_HIS")
public class BmsSaSettleDocHis extends Model<BmsSaSettleDocHis> {

    private static final long serialVersionUID = 1L;

    @TableField("SASETTLEID")
    private Long sasettleid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("SA_MODE")
    private Integer saMode;

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

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("CERTID")
    private Long certid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("ASSESSDATE")
    private LocalDateTime assessdate;

    @TableField("INFOID")
    private Long infoid;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("SETTYPE")
    private Integer settype;

    @TableField("SACERTID")
    private Long sacertid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("RECEIPTMANID")
    private Long receiptmanid;

    @TableField("RECEIPTDATE")
    private LocalDateTime receiptdate;

    @TableField("RECEIPTSTATUS")
    private Long receiptstatus;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
