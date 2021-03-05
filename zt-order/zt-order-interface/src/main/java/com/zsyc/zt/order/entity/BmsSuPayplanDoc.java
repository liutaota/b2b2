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
@TableName("BMS_SU_PAYPLAN_DOC")
public class BmsSuPayplanDoc extends Model<BmsSuPayplanDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PAYPLANID")
    private Long payplanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("EXECUTEMANID")
    private Long executemanid;

    @TableField("EXECUTEDATE")
    private LocalDateTime executedate;

    @TableField("MEMO")
    private String memo;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("THISPAY")
    private BigDecimal thispay;

    @TableField("TOTAL_EXEC")
    private BigDecimal totalExec;

    @TableField("FMID")
    private Long fmid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("SUNOSETTLE")
    private BigDecimal sunosettle;

    @TableField("BACKNOSETTLE")
    private BigDecimal backnosettle;

    @TableField("SETTLENOPAY")
    private BigDecimal settlenopay;

    @TableField("PRE_TOTAL_LINE")
    private BigDecimal preTotalLine;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("SUPPLYNAME")
    private String supplyname;


    @Override
    protected Serializable pkVal() {
        return this.payplanid;
    }

}
