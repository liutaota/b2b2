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
@TableName("BMS_ZTLIN_SK")
public class BmsZtlinSk extends Model<BmsZtlinSk> {

    private static final long serialVersionUID = 1L;

    @TableId("TAB_ID")
    private Long tabId;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERNAME")
    private String salername;

    @TableField("TOTAL")
    private BigDecimal total;


    @Override
    protected Serializable pkVal() {
        return this.tabId;
    }

}
