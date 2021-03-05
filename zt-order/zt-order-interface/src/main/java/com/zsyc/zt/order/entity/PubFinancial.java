package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
@TableName("PUB_FINANCIAL")
public class PubFinancial extends Model<PubFinancial> {

    private static final long serialVersionUID = 1L;

    @TableField("CUSTOMERID")
    private String customerid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("TOMONTH")
    private String tomonth;

    @TableField("CODEE")
    private String codee;

    @TableField("HANDLETOTAL")
    private BigDecimal handletotal;

    @TableField("RECEIVABLE")
    private BigDecimal receivable;

    @TableField("BACKACCOUNT")
    private BigDecimal backaccount;

    @TableField("INPUTMANID")
    private BigDecimal inputmanid;

    @TableId("INPUTID")
    private BigDecimal inputid;

    @TableField("MEMO")
    private String memo;

    @TableField("CLASSIFY")
    private String classify;


    @Override
    protected Serializable pkVal() {
        return this.inputid;
    }

}
