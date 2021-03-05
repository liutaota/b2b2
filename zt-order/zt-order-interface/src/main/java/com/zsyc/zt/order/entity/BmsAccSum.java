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
@TableName("BMS_ACC_SUM")
public class BmsAccSum extends Model<BmsAccSum> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("USEMM")
    private Long usemm;

    @TableField("ACCID")
    private Long accid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("SUMDEBITQTY")
    private BigDecimal sumdebitqty;

    @TableField("SUMDEBITMONEY")
    private BigDecimal sumdebitmoney;

    @TableField("SUMCREDITQTY")
    private BigDecimal sumcreditqty;

    @TableField("SUMCREDITMONEY")
    private BigDecimal sumcreditmoney;

    @TableField("DEBITTAXMONEY")
    private BigDecimal debittaxmoney;

    @TableField("CREDEBITTAXMONEY")
    private BigDecimal credebittaxmoney;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
