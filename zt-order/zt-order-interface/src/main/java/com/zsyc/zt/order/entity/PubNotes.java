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
@TableName("PUB_NOTES")
public class PubNotes extends Model<PubNotes> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("NOTESNO")
    private String notesno;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("ACCDATE")
    private String accdate;

    @TableField("MONEY")
    private BigDecimal money;

    @TableField("BANKID")
    private Long bankid;

    @TableField("DEPT")
    private String dept;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("COMEDATE")
    private LocalDateTime comedate;

    @TableField("ENDORSEID")
    private Long endorseid;

    @TableField("ENDORSEDATE")
    private LocalDateTime endorsedate;

    @TableField("DISCOUNTDATE")
    private LocalDateTime discountdate;

    @TableField("DISCOUNTEMPID")
    private Long discountempid;

    @TableField("CASHEMPID")
    private Long cashempid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("NOTESTYPE")
    private Integer notestype;

    @TableField("CASHDATE")
    private LocalDateTime cashdate;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
