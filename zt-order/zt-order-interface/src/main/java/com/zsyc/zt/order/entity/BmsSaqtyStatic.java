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
@TableName("BMS_SAQTY_STATIC")
public class BmsSaqtyStatic extends Model<BmsSaqtyStatic> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYID")
    private Long entryid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BUSITYPE")
    private Integer busitype;

    @TableField("LSTMONTYQTY")
    private BigDecimal lstmontyqty;

    @TableField("BEFORELSTMMQTY")
    private BigDecimal beforelstmmqty;

    @TableField("LSTYEARREFQTY")
    private BigDecimal lstyearrefqty;

    @TableField("STATICDATE")
    private LocalDateTime staticdate;


    @Override
    protected Serializable pkVal() {
        return this.entryid;
    }

}
