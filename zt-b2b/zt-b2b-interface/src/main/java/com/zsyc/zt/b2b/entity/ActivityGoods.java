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
 * 活动商品
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACTIVITY_GOODS")
@ApiModel(value="ActivityGoods对象", description="活动商品")
@KeySequence(value = "SEQ_ACTIVITY_GOODS")
public class ActivityGoods extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

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
     * erp商品id
     */
    @ApiModelProperty(value = "erp商品id")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 策略满足数量
     */
    @ApiModelProperty(value = "策略满足数量")
    @TableField("CONDITION_NUM")
    private double conditionNum;

    /**
     * 商品上限0为无上限，默认为0
     */
    @ApiModelProperty(value = "商品上限0为无上限，默认为0")
    @TableField("TOP_LIMIT")
    private Long topLimit;

    /**
     * 策略满足金额
     */
    @ApiModelProperty(value = "策略满足金额")
    @TableField("CONDITION_PRICE")
    private double conditionPrice;

    /**
     * 商品活动价格
     */
    @ApiModelProperty(value = "商品活动价格")
    @TableField("GOODS_PRICE")
    private double goodsPrice;

    /**
     * 是否启用,0否，1是
     */
    @ApiModelProperty(value = "是否启用,0否，1是")
    @TableField("IS_USE")
    private Integer isUse;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

}
