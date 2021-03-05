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
@TableName("PUB_APP_SA_SALES_DAY")
public class PubAppSaSalesDay extends Model<PubAppSaSalesDay> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SATYPEID")
    private Long satypeid;

    @TableField("TRADECOUNT")
    private Long tradecount;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("MONEY")
    private BigDecimal money;

    @TableField("BATCHGROSS")
    private BigDecimal batchgross;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
