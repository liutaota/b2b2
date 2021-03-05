package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_CREDIT_ADJUST_DTL")
public class BmsCreditAdjustDtl extends Model<BmsCreditAdjustDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("CREDIT_ADJUST_DTL_ID")
    private Long creditAdjustDtlId;

    @TableField("CREDIT_ADJUST_DOC_ID")
    private Long creditAdjustDocId;

    @TableField("ADJUSTITEM")
    private Integer adjustitem;

    @TableField("CUSTOMFLAG")
    private Integer customflag;

    @TableField("SALERDEPTFLAG")
    private Integer salerdeptflag;

    @TableField("SALERFLAG")
    private Integer salerflag;

    @TableField("AGENTFLAG")
    private Integer agentflag;

    @TableField("CONTACTFLAG")
    private Integer contactflag;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SALERDEPTID")
    private Long salerdeptid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("CREDIT")
    private BigDecimal credit;

    @TableField("DAYS")
    private Long days;

    @TableField("ORIGINAL_CREDIT")
    private BigDecimal originalCredit;

    @TableField("ORIGINAL_DAYS")
    private Long originalDays;

    @TableField("DTLMEMO")
    private String dtlmemo;


    @Override
    protected Serializable pkVal() {
        return this.creditAdjustDtlId;
    }

}
