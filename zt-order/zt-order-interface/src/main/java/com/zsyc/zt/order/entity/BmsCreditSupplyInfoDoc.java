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
@TableName("BMS_CREDIT_SUPPLY_INFO_DOC")
public class BmsCreditSupplyInfoDoc extends Model<BmsCreditSupplyInfoDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SUPPLY_ST_ID")
    private Long supplyStId;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("APPROVE_STATUS")
    private Integer approveStatus;

    @TableField("APPROVE_MEMO")
    private String approveMemo;

    @TableField("APPROVE_DATE")
    private LocalDateTime approveDate;

    @TableField("APPROVE_MANID")
    private Long approveManid;

    @TableField("APPROVE_SALERID")
    private Long approveSalerid;


    @Override
    protected Serializable pkVal() {
        return this.supplyStId;
    }

}
