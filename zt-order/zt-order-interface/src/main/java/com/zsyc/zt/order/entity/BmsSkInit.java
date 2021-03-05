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
@TableName("BMS_SK_INIT")
public class BmsSkInit extends Model<BmsSkInit> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("MONEY")
    private BigDecimal money;

    @TableField("COSTPRICE")
    private BigDecimal costprice;

    @TableField("STOCKID")
    private Long stockid;

    @TableField("CALCFLAG")
    private Integer calcflag;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("INOUTFLAG")
    private Long inoutflag;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CREFLAG")
    private Integer creflag;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("TMPID")
    private Long tmpid;

    @TableField("SKCERTID")
    private Long skcertid;

    @TableField("INITID")
    private Long initid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
