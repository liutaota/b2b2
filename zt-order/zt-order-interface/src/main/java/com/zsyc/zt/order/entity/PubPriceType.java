package com.zsyc.zt.order.entity;

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
@TableName("PUB_PRICE_TYPE")
public class PubPriceType extends Model<PubPriceType> {

    private static final long serialVersionUID = 1L;

    @TableId("PRICEID")
    private Long priceid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("PRICENAME")
    private String pricename;

    @TableField("WHOLERESALEFLAG")
    private Integer wholeresaleflag;

    @TableField("CONTROLFLAG")
    private Integer controlflag;

    @TableField("WORKFLAG")
    private Integer workflag;

    @TableField("PUBLISHFLAG")
    private Integer publishflag;

    @TableField("MODIFYFLAG")
    private Integer modifyflag;

    @TableField("DISCOUNTFLAG")
    private Integer discountflag;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.priceid;
    }

}
