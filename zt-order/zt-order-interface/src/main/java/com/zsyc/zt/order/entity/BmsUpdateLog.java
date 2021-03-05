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
@TableName("BMS_UPDATE_LOG")
public class BmsUpdateLog extends Model<BmsUpdateLog> {

    private static final long serialVersionUID = 1L;

    @TableField("LOGID")
    private Long logid;

    @TableField("PKID")
    private Long pkid;

    @TableField("TABLENAME")
    private String tablename;

    @TableField("UPDATEQTY")
    private BigDecimal updateqty;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("NOTAXMONEY")
    private BigDecimal notaxmoney;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
