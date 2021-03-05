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
@TableName("BMS_SU_REBATE_REMAIN")
public class BmsSuRebateRemain extends Model<BmsSuRebateRemain> {

    private static final long serialVersionUID = 1L;

    @TableId("REMAINID")
    private Long remainid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("REMAIN")
    private BigDecimal remain;

    @TableField("USEMM")
    private Long usemm;


    @Override
    protected Serializable pkVal() {
        return this.remainid;
    }

}
