package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券日志
 * </p>
 *
 * @author MP
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("COUPON_LOG")
@ApiModel(value = "CouponLog对象", description = "优惠券日志")
@KeySequence(value = "SEQ_COUPON_LOG")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponLog extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 优惠券ID
     */
    @ApiModelProperty(value = "优惠券ID")
    @TableField("COUPON_ID")
    private Long couponId;

    /**
     * 优惠券码
     */
    @ApiModelProperty(value = "优惠券码")
    @TableField("COUPON_CODE")
    private String couponCode;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 原订单金额
     */
    @ApiModelProperty(value = "原订单金额")
    @TableField("ORDER_AMOUNT")
    private double orderAmount;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    @TableField("COUPON_AMOUNT")
    private double couponAmount;

    /**
     * 抵扣优惠后的订单金额
     */
    @ApiModelProperty(value = "抵扣优惠后的订单金额")
    @TableField("ORDER_FINAL_AMOUNT")
    private double orderFinalAmount;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 日志状态，默认0，支付回调成功后为1,4,赠送优惠券日志
     */
    @ApiModelProperty(value = "日志状态，默认0，支付回调成功后为1,4,赠送优惠券日志")
    @TableField("STATUS")
    private Integer status;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 赠送数量
     */
    @ApiModelProperty(value = "赠送数量")
    @TableField("GIFT_NUM")
    private Long giftNum;
}
