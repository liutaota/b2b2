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
@TableName("PUB_AGENT_PRICE")
public class PubAgentPrice extends Model<PubAgentPrice> {

    private static final long serialVersionUID = 1L;

    @TableId("AGENTID")
    private Long agentid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("DISCOUNT")
    private BigDecimal discount;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;


    @Override
    protected Serializable pkVal() {
        return this.agentid;
    }

}
