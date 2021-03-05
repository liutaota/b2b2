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
@TableName("BMS_STORER_POS")
public class BmsStorerPos extends Model<BmsStorerPos> {

    private static final long serialVersionUID = 1L;

    @TableId("POSID")
    private Long posid;

    @TableField("POSNO")
    private String posno;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("STHOUSEID")
    private Long sthouseid;

    @TableField("MASTERID")
    private Long masterid;

    @TableField("MEMO")
    private String memo;

    @TableField("XPOINT")
    private Long xpoint;

    @TableField("YPOINT")
    private Long ypoint;

    @TableField("GOODSNUM")
    private Long goodsnum;

    @TableField("LOTNUM")
    private Long lotnum;

    @TableField("EXISTSPERCENT")
    private BigDecimal existspercent;

    @TableField("EXISTSGOODSNUM")
    private Long existsgoodsnum;

    @TableField("EXISTSLOTSNUM")
    private Long existslotsnum;

    @TableField("ZPOINT")
    private Long zpoint;

    @TableField("WPOINT")
    private Long wpoint;

    @TableField("POSTYPEID")
    private Long postypeid;

    @TableField("SORTNO")
    private Long sortno;

    @TableField("GOODSUNITFLAG")
    private Long goodsunitflag;


    @Override
    protected Serializable pkVal() {
        return this.posid;
    }

}
