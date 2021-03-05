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
@TableName("BMS_ST_ADJUST_DTL")
public class BmsStAdjustDtl extends Model<BmsStAdjustDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("STADJUSTDTLID")
    private Long stadjustdtlid;

    @TableField("STADJUSTID")
    private Long stadjustid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("ORIGOODSDTLID")
    private Long origoodsdtlid;

    @TableField("ORIBATCHID")
    private Long oribatchid;

    @TableField("ORILOTID")
    private Long orilotid;

    @TableField("ORIPOSID")
    private Long oriposid;

    @TableField("ORIGOODSSTATUSID")
    private Long origoodsstatusid;

    @TableField("NEWGOODSDTLID")
    private Long newgoodsdtlid;

    @TableField("NEWBATCHID")
    private Long newbatchid;

    @TableField("NEWLOTID")
    private Long newlotid;

    @TableField("NEWPOSID")
    private Long newposid;

    @TableField("NEWGOODSSTATUSID")
    private Long newgoodsstatusid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("TIMEQTY")
    private BigDecimal timeqty;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("ZX_SENDWMSFLAG")
    private Integer zxSendwmsflag;

    @TableField("ZX_SENDWMSDATE")
    private LocalDateTime zxSendwmsdate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;


    @Override
    protected Serializable pkVal() {
        return this.stadjustdtlid;
    }

}
