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
 * 优惠券
 * </p>
 *
 * @author MP
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("COUPON")
@ApiModel(value = "Coupon对象", description = "优惠券")
@KeySequence(value = "SEQ_COUPON")
public class Coupon extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 客户集合类型
     * 10-ALL 全部可见
     * 20-VISIBLE 部分可见
     * 30- UN_VISIBLE 部分不可见
     */
    @ApiModelProperty(value = "客户集合类型 10-ALL 全部可见 20-VISIBLE 部分可见 30- UN_VISIBLE 部分不可见")
    @TableField("CUSTOM_SET_TYPE")
    private String customSetType;

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    @TableField("COUPON_NAME")
    private String couponName;

    /**
     * 商品集合
     * 1-ALL 全部
     * 2-PART 部分商品集合
     */
    @ApiModelProperty(value = "商品集合 1-ALL 全部 2-PART 部分可用商品集合 3-UN_VISIBLE 部分不可用商品集合")
    @TableField("GOODS_SET_TYPE")
    private String goodsSetType;

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 满足金额，0为不限满足金额
     */
    @ApiModelProperty(value = "满足金额，0为不限满足金额")
    @TableField("FULL_AMOUNT")
    private double fullAmount;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    @TableField("REDUCE_AMOUNT")
    private double reduceAmount;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    @TableField("DISCOUNT")
    private double discount;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @TableField("CREATE_USER_ID")
    private Long createUserId;

    /**
     * 总发放数量
     */
    @ApiModelProperty(value = "总发放数量")
    @TableField("COUPON_ACCEPT")
    private Long couponAccept;

    /**
     * 剩余数量
     */
    @ApiModelProperty(value = "剩余数量")
    @TableField("REMAIN_NUM")
    private Long remainNum;

    /**
     * 优惠券已使用数量
     */
    @ApiModelProperty(value = "优惠券已使用数量")
    @TableField("USED_AMOUNT")
    private Long usedAmount;

    /**
     * 用户限领数量
     */
    @ApiModelProperty(value = "用户限领数量")
    @TableField("LIMIT_NUM")
    private Long limitNum;

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
    private LocalDateTime useStartTime;

    /**
     * 可使用结束时间
     */
    @ApiModelProperty(value = "可使用结束时间")
    @TableField("USE_END_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime useEndTime;

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

    /**
     * 是否删除，0为否，1为是，默认0
     */
    @ApiModelProperty(value = "是否删除，0为否，1为是，默认0")
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
     * @see #customSetType
     */
    public enum ECustomSetType implements IEnum {
        ALL("全部可见"),
        VISIBLE("部分可见"),
        UN_VISIBLE("部分不可见"),
        ;
        private String desc;

        ECustomSetType(String desc) {
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
     * @see #goodsSetType
     */
    public enum EGoodsSetType implements IEnum {
        ALL("全部"),
        PART("部分可用商品集合"),
        UN_VISIBLE("部分不可用商品集合"),
        ;
        private String desc;

        EGoodsSetType(String desc) {
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
     * @see #type
     */
    public enum EType implements IEnum {
        FULL_REDUCTION_TICKET("满减券"),
        FULL_PRESENT_TICKET("折扣券"),
        CASH_TICKET("现金券");
        private String desc;

        EType(String desc) {
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
