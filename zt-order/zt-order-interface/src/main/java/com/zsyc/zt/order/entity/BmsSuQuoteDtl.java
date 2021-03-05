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
@TableName("BMS_SU_QUOTE_DTL")
public class BmsSuQuoteDtl extends Model<BmsSuQuoteDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("QUOTEDTLID")
    private Long quotedtlid;

    @TableField("QUOTEDOCID")
    private Long quotedocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private Long goodsqty;

    @TableField("PLANDATE")
    private LocalDateTime plandate;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("ARRIVEDATE")
    private LocalDateTime arrivedate;

    @TableField("ISTICKETFLAG")
    private Integer isticketflag;

    @TableField("CREDITMONEY")
    private BigDecimal creditmoney;

    @TableField("CREDITDAY")
    private Integer creditday;

    @TableField("REBATEPOLICY")
    private String rebatepolicy;

    @TableField("QUOTEDATE")
    private LocalDateTime quotedate;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("SUPPLYERID")
    private Long supplyerid;


    @Override
    protected Serializable pkVal() {
        return this.quotedtlid;
    }

}
