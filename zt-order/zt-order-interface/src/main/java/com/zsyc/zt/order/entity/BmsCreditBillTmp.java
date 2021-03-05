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
@TableName("BMS_CREDIT_BILL_TMP")
public class BmsCreditBillTmp extends Model<BmsCreditBillTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("SALERID")
    private Long salerid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableId("SOURCETABLE")
    private String sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("BILLDATE")
    private LocalDateTime billdate;

    @TableField("BILLMONEY")
    private BigDecimal billmoney;


    @Override
    protected Serializable pkVal() {
        return this.sourcetable;
    }

}
