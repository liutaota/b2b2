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
@TableName("BMS_SK_SUM")
public class BmsSkSum extends Model<BmsSkSum> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("USEMM")
    private Long usemm;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("SUMDEBITQTY")
    private BigDecimal sumdebitqty;

    @TableField("SUMDEBITMONEY")
    private BigDecimal sumdebitmoney;

    @TableField("SUMCREDITQTY")
    private BigDecimal sumcreditqty;

    @TableField("SUMCREDITMONEY")
    private BigDecimal sumcreditmoney;

    @TableField("STOCKID")
    private Long stockid;

    @TableField("LOGICMM")
    private Long logicmm;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("INITQTY")
    private BigDecimal initqty;

    @TableField("INITMONEY")
    private BigDecimal initmoney;

    @TableField("SASETQTY")
    private BigDecimal sasetqty;

    @TableField("SASETMONEY")
    private BigDecimal sasetmoney;

    @TableField("SUQTY")
    private BigDecimal suqty;

    @TableField("SUMONEY")
    private BigDecimal sumoney;

    @TableField("MVINQTY")
    private BigDecimal mvinqty;

    @TableField("MVINMONEY")
    private BigDecimal mvinmoney;

    @TableField("MVOUTQTY")
    private BigDecimal mvoutqty;

    @TableField("MVOUTMONEY")
    private BigDecimal mvoutmoney;

    @TableField("LSQTY")
    private BigDecimal lsqty;

    @TableField("LSMONEY")
    private BigDecimal lsmoney;

    @TableField("OFQTY")
    private BigDecimal ofqty;

    @TableField("OFMONEY")
    private BigDecimal ofmoney;

    @TableField("GRESAQTY")
    private BigDecimal gresaqty;

    @TableField("GRESAMONEY")
    private BigDecimal gresamoney;

    @TableField("ADJCOSTQTY")
    private BigDecimal adjcostqty;

    @TableField("ADJCOSTMONEY")
    private BigDecimal adjcostmoney;

    @TableField("PRINQTY")
    private BigDecimal prinqty;

    @TableField("PRINMONEY")
    private BigDecimal prinmoney;

    @TableField("MATERIALOUTQTY")
    private BigDecimal materialoutqty;

    @TableField("MATERIALOUTMONEY")
    private BigDecimal materialoutmoney;

    @TableField("OTHERQTY")
    private BigDecimal otherqty;

    @TableField("OTHERMONEY")
    private BigDecimal othermoney;

    @TableField("RECIQTY")
    private BigDecimal reciqty;

    @TableField("RECIMONEY")
    private BigDecimal recimoney;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
