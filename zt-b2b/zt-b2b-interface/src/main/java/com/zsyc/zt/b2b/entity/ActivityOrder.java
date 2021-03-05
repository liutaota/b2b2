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
 * 活动订单表
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACTIVITY_ORDER")
@ApiModel(value="ActivityOrder对象", description="活动订单表")
@KeySequence(value = "SEQ_ACTIVITY_ORDER")
public class ActivityOrder extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID")
    @TableField("ACTIVITY_ID")
    private Long activityId;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;

    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 策略id
     */
    @ApiModelProperty(value = "策略id")
    @TableField("AS_ID")
    private Long asId;
}
