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
@TableName("BMS_FORMULAMANAGE_DTL")
public class BmsFormulamanageDtl extends Model<BmsFormulamanageDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("FORMULADTLID")
    private Long formuladtlid;

    @TableField("FORMULADOCID")
    private Long formuladocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("USEUNITQTY")
    private BigDecimal useunitqty;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.formuladtlid;
    }

}
