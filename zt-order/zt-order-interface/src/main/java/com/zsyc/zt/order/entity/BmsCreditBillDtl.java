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
@TableName("BMS_CREDIT_BILL_DTL")
public class BmsCreditBillDtl extends Model<BmsCreditBillDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("BILLDTLID")
    private Long billdtlid;

    @TableField("BILLID")
    private Long billid;

    @TableField("BUSITYPE")
    private Integer busitype;

    @TableField("SOURCETABLE")
    private String sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("BUSICREDATE")
    private LocalDateTime busicredate;

    @TableField("BUSICONFIRMDATE")
    private LocalDateTime busiconfirmdate;

    @TableField("BUSIMONEY")
    private Double busimoney;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
