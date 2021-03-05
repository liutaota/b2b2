package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.CouponReceive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CouponReceiveVo对象", description = "客户优惠券信息")
public class CouponReceiveVo extends CouponReceive {
    @ApiModelProperty(value = "过滤优惠券状态状态，0-UNUSED 未使用 1-USE 已使用 2-TO_RECEIVE 待领取 3-OVERDUE 已过期")
    private String status;

    @ApiModelProperty(value = "客户id")
    private Long memberId;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    /**
     * 优惠券状态
     * NORMAL 正常
     * ABOUB_OF_OVERDUEOverdue 即将过期
     * OVERDUE 已过期
     */
    @ApiModelProperty(value = "优惠券状态 NORMAL 正常 ABOUB_OF_OVERDUEOverdue 即将过期 OVERDUE 已过期")
    private String state;

    /**
     * @see #state
     */
    public enum EState implements IEnum {
        NORMAL("正常"),
        ABOUB_OF_OVERDUEOverdue("即将过期"),
        OVERDUE("已过期");
        private String desc;

        EState(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    @TableField("COUPON_NAME")
    private String couponName;

    /**
     * 优惠券类型
     * 1-FULL_REDUCTION_TICKET 满减券
     * 2-FULL_PRESENT_TICKET 折扣券
     * 3-CASH_TICKET 现金券
     */
    @ApiModelProperty(value = "优惠券类型 1-FULL_REDUCTION_TICKET 满减券 2-FULL_PRESENT_TICKET 折扣券 3-CASH_TICKET 现金券")
    @TableField("TYPE")
    private String type;

    /**
     * 优惠券说明
     */
    @ApiModelProperty(value = "优惠券说明")
    @TableField("EXPLAIN")
    private String explain;

    /**
     * 满足金额，0为不限满足金额
     */
    @ApiModelProperty(value = "满足金额，0为不限满足金额")
    @TableField("FULL_AMOUNT")
    private Long fullAmount;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    @TableField("REDUCE_AMOUNT")
    private Long reduceAmount;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    @TableField("DISCOUNT")
    private Long discount;

    /**
     * 有效使用天数
     */
    @ApiModelProperty(value = "有效使用天数")
    @TableField("USE_DAY")
    private Long useDay;

    /**
     * 发放开始时间
     */
    @ApiModelProperty(value = "发放开始时间")
    @TableField("RECEIVE_START_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveStartTime;

    /**
     * 可领取结束时间
     */
    @ApiModelProperty(value = "可领取结束时间")
    @TableField("RECEIVE_END_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveEndTime;

    /**
     * 可使用开始时间
     */
    @ApiModelProperty(value = "可使用开始时间")
    @TableField("USE_START_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cUseStartTime;

    /**
     * 可使用结束时间
     */
    @ApiModelProperty(value = "可使用结束时间")
    @TableField("USE_END_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cUseEndTime;

    /**
     * 是否可发放，0为否，1为是，默认1
     */
    @ApiModelProperty(value = "是否可发放，0为否，1为是，默认1")
    @TableField("IS_RECEIVE")
    private Integer isReceive;

    /**
     * 是否可用，0为否，1为是，默认1
     */
    @ApiModelProperty(value = "是否可用，0为否，1为是，默认1")
    @TableField("IS_USE")
    private Integer isUse;

    @ApiModelProperty(value = "用户手机号")
    private String telephone;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * erp用户id
     */
    @ApiModelProperty(value = "erp用户id")
    private Long erpUserId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
