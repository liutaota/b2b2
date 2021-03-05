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
@TableName("BMS_SK_DEF")
public class BmsSkDef extends Model<BmsSkDef> {

    private static final long serialVersionUID = 1L;

    @TableId("STOCKID")
    private Long stockid;

    @TableField("STOCKNO")
    private String stockno;

    @TableField("STOCKNAME")
    private String stockname;

    @TableField("COSTMETHOD")
    private Integer costmethod;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CALCORDER")
    private Integer calcorder;

    @TableField("COSTFLUCOE")
    private BigDecimal costflucoe;

    @TableField("ADJUSTFLAG")
    private Integer adjustflag;

    @TableField("FLUEFLAG")
    private Integer flueflag;

    @TableField("MAXUSEMM")
    private Long maxusemm;

    @TableField("MINUSEMM")
    private Long minusemm;


    @Override
    protected Serializable pkVal() {
        return this.stockid;
    }

}
