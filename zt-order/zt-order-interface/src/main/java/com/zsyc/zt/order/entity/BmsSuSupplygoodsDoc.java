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
@TableName("BMS_SU_SUPPLYGOODS_DOC")
public class BmsSuSupplygoodsDoc extends Model<BmsSuSupplygoodsDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SUPPLYGOODSDOCID")
    private Long supplygoodsdocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("LASTSUDATE")
    private LocalDateTime lastsudate;

    @TableField("LASTPRICE")
    private BigDecimal lastprice;

    @TableField("HIGHESTPRICE")
    private BigDecimal highestprice;

    @TableField("LOWESTPRICE")
    private BigDecimal lowestprice;

    @TableField("SUTIMES")
    private Long sutimes;

    @TableField("SUGOODSQTYS")
    private BigDecimal sugoodsqtys;

    @TableField("SUMONEYS")
    private BigDecimal sumoneys;

    @TableField("SUBACKTIMES")
    private Long subacktimes;

    @TableField("SUBACKGOODSQTYS")
    private BigDecimal subackgoodsqtys;

    @TableField("SUBACKMONEYS")
    private BigDecimal subackmoneys;

    @TableField("RULE")
    private Integer rule;

    @TableField("VALIDITY")
    private Long validity;

    @TableField("NUM")
    private Long num;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("LASTSUPPLYID")
    private Long lastsupplyid;

    @TableField("LASTAGENTID")
    private Long lastagentid;

    @TableField("LASTCONTACTID")
    private Long lastcontactid;

    @TableField("LASTSUDOCID")
    private Long lastsudocid;

    @TableField("LASTSUQTY")
    private BigDecimal lastsuqty;

    @TableField("DTLLINES")
    private Long dtllines;


    @Override
    protected Serializable pkVal() {
        return this.supplygoodsdocid;
    }

}
