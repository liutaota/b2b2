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
@TableName("BMS_ASSESS_CALCDEF")
public class BmsAssessCalcdef extends Model<BmsAssessCalcdef> {

    private static final long serialVersionUID = 1L;

    @TableId("DEFID")
    private Long defid;

    @TableField("SETDAYS")
    private Long setdays;

    @TableField("AWARDRATE")
    private BigDecimal awardrate;

    @TableField("PUNISHRATE")
    private BigDecimal punishrate;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.defid;
    }

}
