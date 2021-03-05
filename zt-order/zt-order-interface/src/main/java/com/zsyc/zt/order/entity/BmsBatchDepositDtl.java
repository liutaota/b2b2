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
@TableName("BMS_BATCH_DEPOSIT_DTL")
public class BmsBatchDepositDtl extends Model<BmsBatchDepositDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("DEPOSITDTLID")
    private Long depositdtlid;

    @TableField("DEPOSITDOCID")
    private Long depositdocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("DEPOSITRATE")
    private BigDecimal depositrate;

    @TableField("DEPOSIT")
    private BigDecimal deposit;


    @Override
    protected Serializable pkVal() {
        return this.depositdtlid;
    }

}
