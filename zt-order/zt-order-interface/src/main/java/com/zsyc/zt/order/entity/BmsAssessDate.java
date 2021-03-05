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
@TableName("BMS_ASSESS_DATE")
public class BmsAssessDate extends Model<BmsAssessDate> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SAMONEY")
    private BigDecimal samoney;

    @TableField("SABONUS")
    private BigDecimal sabonus;

    @TableField("SAPROMONEY")
    private BigDecimal sapromoney;

    @TableField("SAPROBONUS")
    private BigDecimal saprobonus;

    @TableField("RECMONEY")
    private BigDecimal recmoney;

    @TableField("RECBONUS")
    private BigDecimal recbonus;

    @TableField("RECPROMONEY")
    private BigDecimal recpromoney;

    @TableField("RECPROBONUS")
    private BigDecimal recprobonus;

    @TableField("USEMM")
    private Long usemm;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("BONUSMONEY")
    private BigDecimal bonusmoney;

    @TableField("AWARDMONEY")
    private BigDecimal awardmoney;

    @TableField("PUNISHMONEY")
    private BigDecimal punishmoney;

    @TableField("SABKRATE")
    private BigDecimal sabkrate;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
