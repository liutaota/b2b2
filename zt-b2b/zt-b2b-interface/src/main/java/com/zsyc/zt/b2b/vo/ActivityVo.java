package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.Activity;
import com.zsyc.zt.b2b.entity.ActivityGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ActivityGiftVo对象", description="活动")
public class ActivityVo extends Activity {

    /**
     * 策略Id
     */
    @ApiModelProperty(value = "策略Id")
    private Long asId;

    /**
     * 商品策略，1-任选商品数量，2-任选商品策略数量，3-任选商品金额，4-任选商品策略金额
     */
    @ApiModelProperty(value = "商品策略，1-任选商品数量，2-任选商品策略数量，3-任选商品金额，4-任选商品策略金额")
    private Integer goodsStrategy;

    /**
     * 满足数量
     */
    @ApiModelProperty(value = "满足数量")
    private Long meetQuantity;

    /**
     * 商品上限0为无上限，默认为0
     */
    @ApiModelProperty(value = "商品上限0为无上限，默认为0")
    private Long topLimit;

    /**
     * 满足金额
     */
    @ApiModelProperty(value = "满足金额")
    private Long amountSatisfied;

    /**
     * 满足数量
     */
    @ApiModelProperty(value = "满足数量")
    private Long conditionNum;

    @ApiModelProperty(value = "策略满足金额")
    private Long conditionPrice;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    private Long discount;

    @ApiModelProperty(value = "策略名字")
    private String strategyName;

    @ApiModelProperty(value = "商品上限0为无上限，默认为0，策略表的")
    private Integer asTopLimit;

    @ApiModelProperty(value = "赠送数量")
    private Integer giftNum;

    @ApiModelProperty(value = "减少金额")
    private Long reducedAmount;

    @ApiModelProperty(value = "返现金")
    private Long cash;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    @ApiModelProperty(value = "赠品策略")
    private String giftStrategy;

    @ApiModelProperty(value = "赠品信息")
    List<ActivityGiftVo> activityGiftVoList;

    @ApiModelProperty(value = "策略数据")
    private List<ActivityGoods> activityGoodsList;

    /**
     * 商品活动价格
     */
    @ApiModelProperty(value = "商品活动价格")
    @TableField("GOODS_PRICE")
    private Long goodsPrice;

    /**
     * 促销活动ID（限时折扣ID/优惠套装ID）与goods_type搭配使用
     */
    @ApiModelProperty(value = "促销活动ID（限时折扣ID/优惠套装ID）与goods_type搭配使用")
    private Long activityId;
}
