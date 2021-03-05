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
@TableName("BMS_ACC_DOC")
public class BmsAccDoc extends Model<BmsAccDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("CERTID")
    private Long certid;

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

    @TableField("DEBITQTY")
    private BigDecimal debitqty;

    @TableField("DEBITMONEY")
    private BigDecimal debitmoney;

    @TableField("CREDITQTY")
    private BigDecimal creditqty;

    @TableField("CREDITMONEY")
    private BigDecimal creditmoney;

    @TableField("SOURCETABLE")
    private String sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("BUSIDATE")
    private LocalDateTime busidate;

    @TableField("SUMMARY")
    private String summary;

    @TableField("TRANSACTIONID")
    private Long transactionid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
