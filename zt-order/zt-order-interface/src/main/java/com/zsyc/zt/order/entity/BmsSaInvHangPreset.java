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
@TableName("BMS_SA_INV_HANG_PRESET")
public class BmsSaInvHangPreset extends Model<BmsSaInvHangPreset> {

    private static final long serialVersionUID = 1L;

    @TableId("INVSETID")
    private Long invsetid;

    @TableField("INVRULEID")
    private String invruleid;

    @TableField("INVRULENAME")
    private String invrulename;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("SPECIALLIMITMONEY")
    private BigDecimal speciallimitmoney;

    @TableField("NORMALLIMITMONEY")
    private BigDecimal normallimitmoney;


    @Override
    protected Serializable pkVal() {
        return this.invsetid;
    }

}
