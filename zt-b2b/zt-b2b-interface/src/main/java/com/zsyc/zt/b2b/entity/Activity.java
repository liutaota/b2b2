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
 * 活动主表
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACTIVITY")
@ApiModel(value = "Activity对象", description = "活动主表")
@KeySequence(value = "SEQ_ACTIVITY")
public class Activity extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 促销内容
     */
    @ApiModelProperty(value = "促销内容")
    @TableField("CONTENT")
    private String content;

    /**
     * 活动简称
     */
    @ApiModelProperty(value = "活动简称")
    @TableField("ABBREVIATION")
    private String abbreviation;

    /**
     * 客户集合类型 10，全部可见，20，部分可见,30,不可见
     */
    @ApiModelProperty(value = "客户集合类型 10，全部可见，20，部分可见，30,不可见")
    @TableField("CUSTOM_SET_TYPE")
    private Integer customSetType;

    /**
     * 活动策略  10全场折扣，20商品折扣，30满减，40满赠，50赠优惠券，60限时秒杀&限时特价，70全场优惠券,80品牌特价，90单品赠，100限购，110抽奖,120限购数量
     */
    @ApiModelProperty(value = "活动策略  10全场折扣，20商品折扣，30满减，40满赠，50赠优惠券，60限时秒杀&限时特价，70全场优惠券,80品牌特价")
    @TableField("ACTIVITY_STRATEGY")
    private Integer activityStrategy;

    /**
     * 预热时间
     */
    @ApiModelProperty(value = "预热时间")
    @TableField("WARM_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime warmTime;

    /**
     * 有效开始时段 存时分秒 10:00:00
     */
    @ApiModelProperty(value = "有效开始时段")
    @TableField("START_EFFECTIVE_TIME")
    private String startEffectiveTime;

    /**
     * 有效结束时段 存时分秒 12:00:00
     */
    @ApiModelProperty(value = "有效结束时段")
    @TableField("END_EFFECTIVE_TIME")
    private String endEffectiveTime;

    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间")
    @TableField("START_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间")
    @TableField("END_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 同一客户次数限制
     */
    @ApiModelProperty(value = "同一客户次数限制")
    @TableField("TIMES")
    private Integer times;

    /**
     * 是否叠加使用,0否，1是，默认0
     */
    @ApiModelProperty(value = "是否叠加使用,0否，1是，默认0")
    @TableField("IS_SUPERPOSITION")
    private Integer isSuperposition;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 星期1-7,0每天
     */
    @ApiModelProperty(value = "星期1-7，0每天")
    @TableField("WEEK")
    private Integer week;

    /**
     * 限制策略，0全场一次，1每天，2周，3月，4年
     */
    @ApiModelProperty(value = "限制策略，0全场一次，1每天，2周，3月，4年")
    @TableField("TIMES_STRATEGY")
    private Integer timesStrategy;

    /**
     * 是否启用,0否，1是
     */
    @ApiModelProperty(value = "是否启用，0否，1是")
    @TableField("IS_USE")
    private Integer isUse;

    /**
     * 采购员ID
     */
    @ApiModelProperty(value = "采购员ID")
    @TableField("ERP_PURCHASER_ID")
    private Long erpPurchaserId;

    @ApiModelProperty(value = "抽奖结束时间")
    @TableField("LOT_END_TIME")
    private LocalDateTime lotEndTime;

}
