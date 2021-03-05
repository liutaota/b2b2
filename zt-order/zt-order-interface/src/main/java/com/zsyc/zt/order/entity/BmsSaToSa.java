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
@TableName("BMS_SA_TO_SA")
public class BmsSaToSa extends Model<BmsSaToSa> {

    private static final long serialVersionUID = 1L;

    @TableId("SADTLID1")
    private Long sadtlid1;

    @TableField("SADTLID2")
    private Long sadtlid2;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("IODTLID")
    private Long iodtlid;


    @Override
    protected Serializable pkVal() {
        return this.sadtlid1;
    }

}
