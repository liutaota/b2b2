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
@TableName("BMS_CREDIT_LEVEL_2")
public class BmsCreditLevel2 extends Model<BmsCreditLevel2> {

    private static final long serialVersionUID = 1L;

    @TableId("SALERID")
    private Long salerid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CREDIT")
    private BigDecimal credit;

    @TableField("EXEC_ORDER_ID")
    private Long execOrderId;

    @TableField("OWEMONEY")
    private BigDecimal owemoney;


    @Override
    protected Serializable pkVal() {
        return this.salerid;
    }

}
