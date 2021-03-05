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
@TableName("BMS_SU_PROTOCAL_DTL")
public class BmsSuProtocalDtl extends Model<BmsSuProtocalDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("PROTOCALDTLID")
    private Long protocaldtlid;

    @TableField("PROTOCALDOCID")
    private Long protocaldocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("DTLGOODSQTY")
    private BigDecimal dtlgoodsqty;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("UNITMAXSUQTY")
    private BigDecimal unitmaxsuqty;

    @TableField("ACCGOODSQTY")
    private BigDecimal accgoodsqty;

    @TableField("ACCMONEY")
    private BigDecimal accmoney;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("PAYLIMIT")
    private String paylimit;


    @Override
    protected Serializable pkVal() {
        return this.protocaldtlid;
    }

}
