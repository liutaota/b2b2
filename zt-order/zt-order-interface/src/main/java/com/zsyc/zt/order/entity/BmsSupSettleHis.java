package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_SUP_SETTLE_HIS")
public class BmsSupSettleHis extends Model<BmsSupSettleHis> {

    private static final long serialVersionUID = 1L;

    @TableField("SUDOCDTLID")
    private Long sudocdtlid;

    @TableField("SUSETDTLID")
    private Long susetdtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("TOTAL_LINE_NAT")
    private BigDecimal totalLineNat;

    @TableField("COSTINGMONEY_NAT")
    private BigDecimal costingmoneyNat;

    @TableField("COSTINGMONEY")
    private BigDecimal costingmoney;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
