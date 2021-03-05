package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_STORER_POS")
@ApiModel(value="BmsStorerPos对象", description="")
@KeySequence(value = "BMS_STORER_POS_SEQ")
public class BmsStorerPos extends Model<BmsStIoDtl> {

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
    private Double existspercent;

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


}
