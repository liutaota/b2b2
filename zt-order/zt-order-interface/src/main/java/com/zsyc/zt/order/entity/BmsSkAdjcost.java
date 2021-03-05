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
@TableName("BMS_SK_ADJCOST")
public class BmsSkAdjcost extends Model<BmsSkAdjcost> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("STOCKID")
    private Long stockid;

    @TableField("CALCFLAG")
    private Integer calcflag;

    @TableField("COSTPRICE")
    private BigDecimal costprice;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREFLAG")
    private Integer creflag;

    @TableField("SKCERTID")
    private Long skcertid;

    @TableField("SKSEQID")
    private Long skseqid;

    @TableField("FINALPRICE")
    private BigDecimal finalprice;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
