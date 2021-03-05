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
@TableName("BMS_SU_REBATE_DOC")
public class BmsSuRebateDoc extends Model<BmsSuRebateDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("REBATEDOCID")
    private Long rebatedocid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("DOCTYPE")
    private Integer doctype;

    @TableField("DOCDATETYPE")
    private Integer docdatetype;

    @TableField("CAFORMULA")
    private Integer caformula;

    @TableField("ROUNDMETHOD")
    private Integer roundmethod;

    @TableField("INTPRECISION")
    private Integer intprecision;

    @TableField("FREQUENCY")
    private Integer frequency;

    @TableField("FORM")
    private Integer form;

    @TableField("REBATEMEMO")
    private String rebatememo;

    @TableField("SIGNMANID")
    private Long signmanid;

    @TableField("PROTOCALDOCID")
    private Long protocaldocid;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("NOTEXAMPLEQTY")
    private Long notexampleqty;

    @TableField("YESEXAMPLEQTY")
    private Long yesexampleqty;

    @TableField("REALMONEY")
    private BigDecimal realmoney;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;


    @Override
    protected Serializable pkVal() {
        return this.rebatedocid;
    }

}
