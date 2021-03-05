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
@TableName("BMS_SU_REBATE_SETTOREC")
public class BmsSuRebateSettorec extends Model<BmsSuRebateSettorec> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("RECID")
    private Long recid;

    @TableField("SETID")
    private Long setid;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
