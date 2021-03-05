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
@TableName("BMS_SK_IO_DOC")
public class BmsSkIoDoc extends Model<BmsSkIoDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("CERTID")
    private Long certid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("DEBITQTY")
    private BigDecimal debitqty;

    @TableField("DEBITMONEY")
    private BigDecimal debitmoney;

    @TableField("CREDITQTY")
    private BigDecimal creditqty;

    @TableField("CREDITMONEY")
    private BigDecimal creditmoney;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("SOURCETABLE")
    private String sourcetable;

    @TableField("BUSIDATE")
    private LocalDateTime busidate;

    @TableField("SUMMARY")
    private String summary;

    @TableField("NEEDCALC")
    private Integer needcalc;

    @TableField("COSTPRICE")
    private BigDecimal costprice;

    @TableField("TRANSACTIONID")
    private Long transactionid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("INOUTID")
    private Long inoutid;

    @TableField("SUADJFLAG")
    private Integer suadjflag;

    @TableField("STOCKID")
    private Long stockid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("COMPANYNAME")
    private String companyname;

    @TableField("LOGICMM")
    private Long logicmm;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("COSTOBJID")
    private Long costobjid;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
