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
@TableName("BMS_ACC_REM")
public class BmsAccRem extends Model<BmsAccRem> {

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

    @TableField("REMDEBITQTY")
    private BigDecimal remdebitqty;

    @TableField("REMDEBITMONEY")
    private BigDecimal remdebitmoney;

    @TableField("REMCREDITQTY")
    private BigDecimal remcreditqty;

    @TableField("REMCREDITMONEY")
    private BigDecimal remcreditmoney;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("AGENTID")
    private Long agentid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
