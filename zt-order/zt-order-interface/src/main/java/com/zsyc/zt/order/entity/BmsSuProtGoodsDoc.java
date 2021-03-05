package com.zsyc.zt.order.entity;

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
@TableName("BMS_SU_PROT_GOODS_DOC")
public class BmsSuProtGoodsDoc extends Model<BmsSuProtGoodsDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PROTDOCID")
    private Long protdocid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("AGENTMANID")
    private Long agentmanid;

    @TableField("CLUES")
    private Integer clues;

    @TableField("TYPE")
    private Integer type;

    @TableField("FREQUENCY")
    private Integer frequency;

    @TableField("FORM")
    private Integer form;

    @TableField("LIMITCUSTOMERSETID")
    private Long limitcustomersetid;

    @TableField("CONFIGCLASS")
    private Integer configclass;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.protdocid;
    }

}
