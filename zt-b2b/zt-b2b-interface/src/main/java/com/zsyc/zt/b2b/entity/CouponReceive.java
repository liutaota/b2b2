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
 * 用户优惠券
 * </p>
 *
 * @author MP
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("COUPON_RECEIVE")
@ApiModel(value = "CouponReceive对象", description = "用户优惠券")
@KeySequence(value = "SEQ_COUPON_RECEIVE")
public class CouponReceive extends BaseBean {

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
     * 状态，0-UNUSED 未使用 1-USE 已使用 2-TO_RECEIVE 待领取 3-OVERDUE 已过期
     */
    @ApiModelProperty(value = "状态，0-UNUSED 未使用 1-USE 已使用 2-TO_RECEIVE 待领取 3-OVERDUE 已过期")
    @TableField("STATUS")
    private String status;

    /**
     * 使用开始时间
     */
    @ApiModelProperty(value = "使用开始时间")
    @TableField("USE_START_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime useStartTime;

    /**
     * 使用结束时间
     */
    @ApiModelProperty(value = "使用结束时间")
    @TableField("USE_END_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime useEndTime;

    /**
     * 是否删除，0否，1是
     */
    @ApiModelProperty(value = "是否删除，0否，1是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 来源:ADMIN 后台赠 ORDER 下单赠->促销活动赠
     */
    @ApiModelProperty(value = "来源:ADMIN 后台赠 ORDER 下单赠->促销活动赠")
    @TableField("SOURCE")
    private String source;

    /**
     * 领取时间
     */
    @ApiModelProperty(value = "领取时间")
    @TableField("TO_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toTime;

    /**
     * @see #status
     */
    public enum EStatus implements IEnum {
        UNUSED("未使用"),
        USE("已使用"),
        TO_RECEIVE("待领取"),
        OVERDUE("已过期"),
        DEL("废弃")
        ;
        private String desc;

        EStatus(String desc) {
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
     * @see #source
     */
    public enum ESource implements IEnum {
        ADMIN("后台赠"),
        ORDER("下单赠");
        private String desc;

        ESource(String desc) {
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
}
