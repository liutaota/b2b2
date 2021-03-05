package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("PUB_APP_CENTER_QUARTER")
public class PubAppCenterQuarter extends Model<PubAppCenterQuarter> {

    private static final long serialVersionUID = 1L;

    @TableField("SEQID")
    private Long seqid;

    @TableField("RETAILCENTERID")
    private Long retailcenterid;

    @TableField("MONEY")
    private BigDecimal money;

    @TableField("GROSS")
    private BigDecimal gross;

    @TableField("STOCKMONEY")
    private BigDecimal stockmoney;

    @TableField("CUSTOMERQTY")
    private Long customerqty;

    @TableField("REQQTY")
    private BigDecimal reqqty;

    @TableField("SUPQTY")
    private BigDecimal supqty;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
