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
@TableName("BMS_ZTLIN_SU")
public class BmsZtlinSu extends Model<BmsZtlinSu> {

    private static final long serialVersionUID = 1L;

    @TableId("TAB_ID")
    private Long tabId;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("SUPPLYER")
    private String supplyer;

    @TableField("DOCTYPE")
    private String doctype;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("PAYMONEY")
    private BigDecimal paymoney;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return this.tabId;
    }

}
