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
@TableName("BMS_GOODS_SORT")
public class BmsGoodsSort extends Model<BmsGoodsSort> {

    private static final long serialVersionUID = 1L;

    @TableId("SORTID")
    private Long sortid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("SAQTY")
    private BigDecimal saqty;

    @TableField("SAMONEY")
    private BigDecimal samoney;

    @TableField("BATCHCOST")
    private BigDecimal batchcost;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("SACONTRIREFGRADE")
    private Integer sacontrirefgrade;

    @TableField("SACONTRIGRADE")
    private Integer sacontrigrade;

    @TableField("GROSSCONTRIREFGRADE")
    private Integer grosscontrirefgrade;

    @TableField("GROSSCONTRIGRADE")
    private Integer grosscontrigrade;

    @TableField("GROSSREFGRADE")
    private Integer grossrefgrade;

    @TableField("GROSSGRADE")
    private Integer grossgrade;

    @TableField("BUSITYPE")
    private Integer busitype;

    @TableField("STATICDATE")
    private LocalDateTime staticdate;

    @TableField("STATICMANID")
    private Long staticmanid;


    @Override
    protected Serializable pkVal() {
        return this.sortid;
    }

}
