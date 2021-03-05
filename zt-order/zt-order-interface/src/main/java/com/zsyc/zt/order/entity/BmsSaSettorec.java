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
@TableName("BMS_SA_SETTOREC")
public class BmsSaSettorec extends Model<BmsSaSettorec> {

    private static final long serialVersionUID = 1L;

    @TableId("SASETTLEDTLID")
    private Long sasettledtlid;

    @TableField("SARECDTLID")
    private Long sarecdtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;


    @Override
    protected Serializable pkVal() {
        return this.sasettledtlid;
    }

}
