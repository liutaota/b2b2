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
@TableName("BMS_CREDIT_BILL_DOC_BACK")
public class BmsCreditBillDocBack extends Model<BmsCreditBillDocBack> {

    private static final long serialVersionUID = 1L;

    @TableField("BILLID")
    private Long billid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERDEPTID")
    private Long salerdeptid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("OWEMONEY")
    private BigDecimal owemoney;

    @TableField("OWEDATE")
    private LocalDateTime owedate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
