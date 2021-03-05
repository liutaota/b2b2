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
@TableName("BMS_DISCTYPE_DISCOUNT_DEF")
public class BmsDisctypeDiscountDef extends Model<BmsDisctypeDiscountDef> {

    private static final long serialVersionUID = 1L;

    @TableId("DISCTYPEDISCDEFID")
    private Long disctypediscdefid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("DISTYPEDOCID")
    private Long distypedocid;

    @TableField("DISCOUNT")
    private BigDecimal discount;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INVALIDENDDATE")
    private LocalDateTime invalidenddate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.disctypediscdefid;
    }

}
