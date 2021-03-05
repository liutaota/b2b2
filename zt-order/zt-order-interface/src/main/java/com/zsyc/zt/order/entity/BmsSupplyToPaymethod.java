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
@TableName("BMS_SUPPLY_TO_PAYMETHOD")
public class BmsSupplyToPaymethod extends Model<BmsSupplyToPaymethod> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("PAYLIMIT")
    private String paylimit;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
