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
@TableName("BMS_ASSESS_DATE_DETAIL")
public class BmsAssessDateDetail extends Model<BmsAssessDateDetail> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CUSTOMSETID")
    private Long customsetid;

    @TableField("GOODSSETID")
    private Long goodssetid;

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

    @TableField("SABKRATE")
    private BigDecimal sabkrate;

    @TableField("SABKMONEY")
    private BigDecimal sabkmoney;

    @TableField("SUMMONEY")
    private BigDecimal summoney;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
