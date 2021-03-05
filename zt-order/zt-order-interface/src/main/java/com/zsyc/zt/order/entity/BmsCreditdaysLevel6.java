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
@TableName("BMS_CREDITDAYS_LEVEL_6")
public class BmsCreditdaysLevel6 extends Model<BmsCreditdaysLevel6> {

    private static final long serialVersionUID = 1L;

    @TableId("CUSTOMID")
    private Long customid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("DAYS")
    private Long days;

    @TableField("EXEC_ORDER_ID")
    private Long execOrderId;

    @TableField("OWEDATE")
    private LocalDateTime owedate;


    @Override
    protected Serializable pkVal() {
        return this.customid;
    }

}
