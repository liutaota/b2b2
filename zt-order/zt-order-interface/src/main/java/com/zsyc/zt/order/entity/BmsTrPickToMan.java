package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_TR_PICK_TO_MAN")
public class BmsTrPickToMan extends Model<BmsTrPickToMan> {

    private static final long serialVersionUID = 1L;

    @TableId("PICKDOCID")
    private Long pickdocid;

    @TableField("PICKMANID")
    private Long pickmanid;

    @TableField("PACKQTY")
    private Integer packqty;

    @TableField("WEIGHT")
    private BigDecimal weight;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.pickdocid;
    }

}
