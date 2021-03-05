package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("FAVORITES")
@ApiModel(value="Favorites对象", description="收藏表")
@KeySequence(value = "SEQ_FAVORITES")
public class Favorites extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 会员名称
     */
    @ApiModelProperty(value = "会员名称")
    @TableField("MEMBER_NAME")
    private String memberName;

    /**
     * 收藏对像id，如商品id
     */
    @ApiModelProperty(value = "收藏对像id，如商品id")
    @TableField("FAV_ID")
    private Long favId;

    /**
     * 类型：goods为商品
     */
    @ApiModelProperty(value = "类型：goods为商品")
    @TableField("FAV_TYPE")
    private String favType;

    /**
     * 收藏时间
     */
    @ApiModelProperty(value = "收藏时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GOODS_IMAGE")
    private String goodsImage;

    /**
     * 商品分类id
     */
    @ApiModelProperty(value = "商品分类id")
    @TableField("GC_ID")
    private Long gcId;

    /**
     * 商品收藏时价格
     */
    @ApiModelProperty(value = "商品收藏时价格")
    @TableField("LOG_PRICE")
    private double logPrice;

    /**
     * 收藏备注
     */
    @ApiModelProperty(value = "收藏备注")
    @TableField("LOG_MSG")
    private String logMsg;

    /**
     * 价格类型id
     */
    @ApiModelProperty(value = "价格类型id")
    @TableField("PRICE_ID")
    private Long priceId;


    @ApiModelProperty(value = "保管帐ID")
    private Integer  storageId;

    @ApiModelProperty(value = "是否赠品标志")
    private Long accflag;

    @ApiModelProperty(value = "批号")
    private String lotNo;

    @ApiModelProperty(value = "批号id")
    private Long lotId;

}
