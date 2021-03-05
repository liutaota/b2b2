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
@TableName("BMS_SU_PROT_TOTAL")
public class BmsSuProtTotal extends Model<BmsSuProtTotal> {

    private static final long serialVersionUID = 1L;

    @TableId("PROTID")
    private Long protid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("REBATERATE")
    private BigDecimal rebaterate;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MEMO")
    private String memo;

    @TableField("AGENTMANID")
    private Long agentmanid;

    @TableField("CLUES")
    private Integer clues;

    @TableField("FREQUENCY")
    private Integer frequency;

    @TableField("FORM")
    private Integer form;

    @TableField("TYPE")
    private Integer type;

    @TableField("LIMITCUSTOMERSETID")
    private Long limitcustomersetid;

    @TableField("GOODSLIMIT")
    private Integer goodslimit;

    @TableField("CONFIGCLASS")
    private Integer configclass;


    @Override
    protected Serializable pkVal() {
        return this.protid;
    }

}
