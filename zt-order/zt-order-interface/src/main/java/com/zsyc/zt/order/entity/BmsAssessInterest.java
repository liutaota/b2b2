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
@TableName("BMS_ASSESS_INTEREST")
public class BmsAssessInterest extends Model<BmsAssessInterest> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("USEMM")
    private Long usemm;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("AWARDMONEY")
    private BigDecimal awardmoney;

    @TableField("PUNISHMONEY")
    private BigDecimal punishmoney;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESID")
    private Long salesid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
