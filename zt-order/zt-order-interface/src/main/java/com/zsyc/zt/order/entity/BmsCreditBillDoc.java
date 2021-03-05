package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("BMS_CREDIT_BILL_DOC")
public class BmsCreditBillDoc extends Model<BmsCreditBillDoc> {

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
    private Double owemoney;

    @TableField("OWEDATE")
    private LocalDateTime owedate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
