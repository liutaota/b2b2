package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PUB_GOODS_PRICE")
@ApiModel(value="PubGoodsPrice对象", description="")
@KeySequence(value = "PUB_GOODS_PRICE_SEQ")
public class PubGoodsPrice extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICE")
    private Double price;

    @TableField("DISCOUNT")
    private Double discount;

    @TableField("REFRENCEPRICEID")
    private Long refrencepriceid;

    @TableField("REFRENCEPRICE")
    private Double refrenceprice;

    @TableId("GDSPRICEID")
    private Long gdspriceid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYID")
    private Integer entryid;


}
