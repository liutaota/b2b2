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
@TableName("BMS_ENTRY_PRALTER_DTL")
public class BmsEntryPralterDtl extends Model<BmsEntryPralterDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("ALTERDTLID")
    private Long alterdtlid;

    @TableField("ALTERDOCID")
    private Long alterdocid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("FROMPRICE")
    private BigDecimal fromprice;

    @TableField("TOPRICE")
    private BigDecimal toprice;

    @TableField("ALTERREASON")
    private String alterreason;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;


    @Override
    protected Serializable pkVal() {
        return this.alterdtlid;
    }

}
