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
 * 活动赠品
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACTIVITY_GIFT")
@ApiModel(value="ActivityGift对象", description="活动赠品")
@KeySequence(value = "SEQ_ACTIVITY_GIFT")
public class ActivityGift extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

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
     * 赠品价格，0为免费，100为1分钱，erp设置
     */
    @ApiModelProperty(value = "赠品价格，0为免费，100为1分钱，erp设置")
    @TableField("GOODS_PRICE")
    private double goodsPrice;

    /**
     * 保管账id（=5为近效商品）
     */
    @ApiModelProperty(value = "保管账id（=5为近效商品）")
    @TableField("STORAGE_ID")
    private Long storageId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    @TableField("LOTNO")
    private String lotno;

    /**
     * ERP商品属性（=5为赠品）(先不存)
     */
    @ApiModelProperty(value = "ERP商品属性（=5为赠品）(先不存)")
    @TableField("ERP_ACCFLAG")
    private Long erpAccflag;

    /**
     * 赠送倍数,默认1
     */
    @ApiModelProperty(value = "赠送倍数,默认1")
    @TableField("GIFT_MULTIPLE")
    private Long giftMultiple;

    /**
     * 赠送数量，0为不限，>0为固定值不可选
     */
    @ApiModelProperty(value = "赠送数量，0为不限，>0为固定值不可选")
    @TableField("GIFT_NUM")
    private double giftNum;

    /**
     * 是否启用,0否，1是
     */
    @ApiModelProperty(value = "是否启用，0否，1是")
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
    private String userId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 批号id
     */
    @ApiModelProperty(value = "批号id")
    @TableField("LOT_ID")
    private Long lotId;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    @TableField("ACTIVITY_ID")
    private Long activityId;
}
