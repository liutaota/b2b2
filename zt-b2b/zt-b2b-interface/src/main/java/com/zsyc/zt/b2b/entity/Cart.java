package com.zsyc.zt.b2b.entity;

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

import java.time.LocalDateTime;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("CART")
@ApiModel(value = "Cart对象", description = "购物车")
@KeySequence(value = "SEQ_CART")
public class Cart extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * ERP商品表pub_goods
     */
    @ApiModelProperty(value = "ERP商品表pub_goods")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 加入购物车时的商品价格
     */
    @ApiModelProperty(value = "加入购物车时的商品价格")
    @TableField("GOODS_PRICE")
    private double goodsPrice;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    @TableField("GOODS_NUM")
    private Integer goodsNum;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GOODS_IMAGE")
    private String goodsImage;

    /**
     * 组合套装id
     */
    @ApiModelProperty(value = "组合套装id")
    @TableField("BL_ID")
    private Long blId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    @TableField("GC_ID")
    private Long gcId;

    /**
     * 价格类型id
     */
    @ApiModelProperty(value = "价格类型id")
    @TableField("PRICE_ID")
    private Long priceId;


    @ApiModelProperty(value = "保管帐ID")
    private Long storageId;

    /*@ApiModelProperty(value = "商品定位， X为新品")
    private String  goodsidGps;*/

    @ApiModelProperty(value = "是否赠品标志")
    private Long accflag;

    @ApiModelProperty(value = "批号")
    private String lotNo;

    @ApiModelProperty(value = "批号id")
    private Long lotId;

    /**
     * 1默认2限时折扣商品3组合套装4赠品5加价购活动商品6加价购换购商品
     */
    @ApiModelProperty(value = "1默认2限时折扣商品3组合套装4赠品5加价购活动商品6加价购换购商品")
    @TableField("GOOD_TYPE")
    private Integer goodType;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    @TableField("ACTIVITY_ID")
    private Long activityId;

    /**
     * 活动策略ID
     */
    @ApiModelProperty(value = "活动策略ID")
    @TableField("AS_ID")
    private Long asId;

    /**
     * 赠品id
     */
    @ApiModelProperty(value = "赠品id")
    @TableField("GIFT_ID")
    private String giftId;

    /**
     * 是否选中商品 2否，1是
     */
    @ApiModelProperty(value = "是否选中商品,0否，1是")
    @TableField("PITCH_ON")
    private Long pitchOn;

    /**
     * 门店id
     */
    @ApiModelProperty(value = "门店id")
    @TableField("STORE_ID")
    private Long storeId;

    @ApiModelProperty(value = "门店细单id")
    @TableField("SRC_ORDER_DTL_ID")
    private Long srcOrderDtlId;
}
