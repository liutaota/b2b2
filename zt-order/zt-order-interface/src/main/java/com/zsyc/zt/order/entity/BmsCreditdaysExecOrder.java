package com.zsyc.zt.order.entity;

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
@TableName("BMS_CREDITDAYS_EXEC_ORDER")
public class BmsCreditdaysExecOrder extends Model<BmsCreditdaysExecOrder> {

    private static final long serialVersionUID = 1L;

    @TableId("ORDERID")
    private Long orderid;

    @TableField("ADJUST_DTL_ID")
    private Long adjustDtlId;

    @TableField("EXEC_DATE")
    private LocalDateTime execDate;

    @TableField("PARENT_ORDER_ID")
    private Long parentOrderId;


    @Override
    protected Serializable pkVal() {
        return this.orderid;
    }

}
