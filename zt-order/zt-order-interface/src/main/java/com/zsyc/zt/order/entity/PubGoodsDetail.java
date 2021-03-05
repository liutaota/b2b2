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
@TableName("PUB_GOODS_DETAIL")
public class PubGoodsDetail extends Model<PubGoodsDetail> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableId("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("PACKNAME")
    private String packname;

    @TableField("PACKSIZE")
    private BigDecimal packsize;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("COLORSTRING")
    private String colorstring;

    @TableField("MODEL")
    private String model;

    @TableField("GOODSLENGTH")
    private BigDecimal goodslength;

    @TableField("GOODSWIDTH")
    private BigDecimal goodswidth;

    @TableField("GOODSHEIGHT")
    private BigDecimal goodsheight;

    @TableField("GOODSUNITWEIGHT")
    private BigDecimal goodsunitweight;

    @TableField("GOODSUNITVOL")
    private BigDecimal goodsunitvol;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;


    @Override
    protected Serializable pkVal() {
        return this.goodsdtlid;
    }

}
