package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单快照表
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ORDER_GOODS")
@ApiModel(value = "OrderGoods对象", description = "订单快照表")
@KeySequence(value = "SEQ_ORDER_GOODS")
public class OrderGoods extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @TableField("GOODS_NUM")
    private double goodsNum;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

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
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格")
    @TableField("GOODS_PRICE")
    private double goodsPrice;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GOODS_IMAGE")
    private String goodsImage;

    /**
     * 商品实际成交价
     */
    @ApiModelProperty(value = "商品优惠平均成交价")
    @TableField("GOODS_PAY_PRICE")
    private double goodsPayPrice;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 1默认2限时折扣商品3组合套装4赠品5加价购活动商品6加价购换购商品
     */
    @ApiModelProperty(value = "1默认2限时折扣商品3组合套装4赠品5加价购活动商品6加价购换购商品")
    @TableField("GOOD_TYPE")
    private Integer goodType;

    /**
     * 促销活动ID（限时折扣ID/优惠套装ID）与goods_type搭配使用
     */
    @ApiModelProperty(value = "促销活动ID（限时折扣ID/优惠套装ID）与goods_type搭配使用")
    @TableField("ACTIVITY_ID")
    private Long activityId;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    @TableField("GC_ID")
    private Long gcId;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    @TableField("GOODS_SPEC")
    private String goodsSpec;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    @TableField("ERP_LOT_NO")
    private String erpLotNo;

    /**
     * 保管帐id
     */
    @ApiModelProperty(value = "保管帐id")
    @TableField("ERP_STORAGE_ID")
    private Integer erpStorageId;

    /**
     * erp实际发货数量
     */
    @ApiModelProperty(value = "erp实际发货数量")
    @TableField("SELL_NUM")
    private double sellNum;

    /**
     * 已退数量
     */
    @ApiModelProperty(value = "已退数量")
    @TableField("REFUND_NUM")
    private double refundNum;

    /**
     * erp最小销售数量
     */
    @ApiModelProperty(value = "erp最小销售数量")
    @TableField("ERP_LEASTSALEQTY")
    private double erpLeastsaleqty;

    /**
     * erp基本单位
     */
    @ApiModelProperty(value = "erp基本单位")
    @TableField("ERP_GOODS_UNIT")
    private String erpGoodsUnit;

    /**
     * erp商品id
     */
    @ApiModelProperty(value = "erp商品id")
    @TableField("ERP_GOODS_ID")
    private Long  erpGoodsId;

    /**
     * 价格类型id
     */
    @ApiModelProperty(value = "价格类型id")
    @TableField("PRICE_ID")
    private Integer priceId;

    /**
     * 小计
     */
    @ApiModelProperty(value = "小计")
    @TableField("AMOUNT_NUM")
    private double amountNum;

    /**
     * 优惠后小计
     */
    @ApiModelProperty(value = "优惠后小计")
    @TableField("AMOUNT_PAY")
    private double  amountPay;

    @ApiModelProperty(value = "批号id")
    private Long lotId;


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
    private Long giftId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;


    /**
     * 商品积分
     */
    @ApiModelProperty(value = "商品积分")
    @TableField("GOODS_SCORE")
    private Long goodsScore;

    @ApiModelProperty(value = "限购活动id")
    @TableField("PURCHASE_ID")
    private Long purchaseId;

    @ApiModelProperty(value = "奖品id")
    @TableField("PRIZE_ID")
    private Long prizeId;

    /**
     * 门店id
     */
    @ApiModelProperty(value = "门店id")
    @TableField("STORE_ID")
    private Long storeId;


    /**
     * 货品细单id
     */
    @ApiModelProperty(value = "货品细单id")
    @TableField("GOODS_DTL_ID")
    private Long goodsDtlId;

    @ApiModelProperty(value = "门店细单id")
    @TableField("SRC_ORDER_DTL_ID")
    private Long srcOrderDtlId;
}
