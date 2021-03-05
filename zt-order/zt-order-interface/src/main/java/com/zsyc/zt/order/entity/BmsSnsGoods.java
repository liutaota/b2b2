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
@TableName("BMS_SNS_GOODS")
public class BmsSnsGoods extends Model<BmsSnsGoods> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("SUPAGENTID")
    private Long supagentid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
