package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动策略
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACTIVITY_STRATEGY")
@ApiModel(value="ActivityStrategy对象", description="活动策略")
@KeySequence(value = "SEQ_ACTIVITY_STRATEGY")
public class ActivityStrategy extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    @TableField("ACTIVITY_ID")
    private Long activityId;

    /**
     * 策略名字
     */
    @ApiModelProperty(value = "策略名字")
    @TableField("STRATEGY_NAME")
    private String strategyName;

    /**
     * 商品策略，10-任选商品数量，20-任选商品策略数量，30-任选商品金额，40-任选商品策略金额
     */
    @ApiModelProperty(value = "商品策略，10-任选商品数量，20-任选商品策略数量，30-任选商品金额，40-任选商品策略金额")
    @TableField("GOODS_STRATEGY")
    private Integer goodsStrategy;

    /**
     * 满足数量
     */
    @ApiModelProperty(value = "满足数量")
    @TableField("MEET_QUANTITY")
    private double meetQuantity;

    /**
     * 商品上限0为无上限，默认为0
     */
    @ApiModelProperty(value = "商品上限0为无上限，默认为0")
    @TableField("TOP_LIMIT")
    private Long topLimit;

    /**
     * 满足金额
     */
    @ApiModelProperty(value = "满足金额")
    @TableField("AMOUNT_SATISFIED")
    private double amountSatisfied;

    /**
     * 赠送数量
     */
    @ApiModelProperty(value = "赠送数量")
    @TableField("GIFT_NUM")
    private double giftNum;

    /**
     * 减少金额
     */
    @ApiModelProperty(value = "减少金额")
    @TableField("REDUCED_AMOUNT")
    private double reducedAmount;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    @TableField("DISCOUNT")
    private double discount;

    /**
     * 返现金
     */
    @ApiModelProperty(value = "返现金")
    @TableField("CASH")
    private double cash;

    /**
     * 优惠券id
     */
    @ApiModelProperty(value = "优惠券id")
    @TableField("COUPON_ID")
    private Long couponId;

    /**
     * 赠品策略，10-固定赠品数量，30-策略赠品数量，40,不赠
     */
    @ApiModelProperty(value = "赠品策略，10-固定赠品数量，30-策略赠品数量，40,不赠")
    @TableField("GIFT_STRATEGY")
    private Integer giftStrategy;

    /**
     * 是否启用 0否，1是
     */
    @ApiModelProperty(value = "是否启用 0否，1是")
    @TableField("IS_USE")
    private Integer isUse;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Long sort;

    @ApiModelProperty(value = "抽奖次数")
    @TableField("LOT_NUM")
    private Integer lotNum;
}
