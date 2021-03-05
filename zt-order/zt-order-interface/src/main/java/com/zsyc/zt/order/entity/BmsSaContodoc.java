package com.zsyc.zt.order.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
@TableName("BMS_SA_CONTODOC")
@KeySequence("BMS_SA_CONTODOC_SEQ")
public class BmsSaContodoc extends Model<BmsSaContodoc> {

    private static final long serialVersionUID = 1L;

    @TableId("CONDTLID")
    private Long condtlid;

    @TableField("SALESDTLID")
    private Long salesdtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;


    @Override
    protected Serializable pkVal() {
        return this.condtlid;
    }

}
