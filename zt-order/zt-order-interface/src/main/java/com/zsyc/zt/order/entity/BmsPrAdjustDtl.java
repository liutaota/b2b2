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
@TableName("BMS_PR_ADJUST_DTL")
public class BmsPrAdjustDtl extends Model<BmsPrAdjustDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("ADJUSTPRICEDTLID")
    private Long adjustpricedtlid;

    @TableField("ADJUSTPRICEDOCID")
    private Long adjustpricedocid;

    @TableField("SALESDTLID")
    private Long salesdtlid;

    @TableField("CREATEDATE")
    private LocalDateTime createdate;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("OLDBATCHID")
    private Long oldbatchid;

    @TableField("OLDSAPRICE")
    private BigDecimal oldsaprice;

    @TableField("OLDSAQTY")
    private BigDecimal oldsaqty;

    @TableField("ADJUSTSQTY")
    private BigDecimal adjustsqty;

    @TableField("DTLADJUSTPRICE")
    private BigDecimal dtladjustprice;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SABACKQTY")
    private BigDecimal sabackqty;

    @TableField("LOTID")
    private Long lotid;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.adjustpricedtlid;
    }

}
