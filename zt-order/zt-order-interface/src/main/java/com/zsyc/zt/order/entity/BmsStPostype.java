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
@TableName("BMS_ST_POSTYPE")
public class BmsStPostype extends Model<BmsStPostype> {

    private static final long serialVersionUID = 1L;

    @TableId("POSTYPEID")
    private Long postypeid;

    @TableField("POSTYPENAME")
    private String postypename;

    @TableField("GOODSUNITFLAG")
    private Integer goodsunitflag;

    @TableField("POSTLONG")
    private BigDecimal postlong;

    @TableField("POSTWIDE")
    private BigDecimal postwide;

    @TableField("POSTHIGH")
    private BigDecimal posthigh;

    @TableField("POSTWEIGHT")
    private BigDecimal postweight;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("GOODSUNITVOL")
    private BigDecimal goodsunitvol;


    @Override
    protected Serializable pkVal() {
        return this.postypeid;
    }

}
