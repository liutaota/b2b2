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
@TableName("BMS_SK_COSTPRICE_INIT")
public class BmsSkCostpriceInit extends Model<BmsSkCostpriceInit> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("STOCKID")
    private Long stockid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("COSTPRICE")
    private BigDecimal costprice;

    @TableField("INPUTMANID")
    private Long inputmanid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
