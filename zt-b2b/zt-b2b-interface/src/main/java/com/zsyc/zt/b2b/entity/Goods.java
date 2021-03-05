package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.sql.Clob;

import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GOODS")
@ApiModel(value = "Goods对象", description = "商品表")
@KeySequence(value = "SEQ_GOODS")
public class Goods extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 关联ERP货品表
     */
    @ApiModelProperty(value = "关联ERP货品表")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 厂家id
     */
    @ApiModelProperty(value = "厂家id")
    @TableField("FACTORY_ID")
    private Integer factoryId;

    /**
     * 是否有赠品
     */
    @ApiModelProperty(value = "是否有赠品")
    @TableField("HAVE_GIFT")
    private Integer haveGift;

    /**
     * 好评星级
     */
    @ApiModelProperty(value = "好评星级")
    @TableField("EVALUATION_GOOD_STAR")
    private Long evaluationGoodStar;

    /**
     * 评价数
     */
    @ApiModelProperty(value = "评价数")
    @TableField("EVALUATION_COUNT")
    private Integer evaluationCount;

    /**
     * 商品推荐，1是，0否，默认0
     */
    @ApiModelProperty(value = "商品推荐，1是，0否，默认0")
    @TableField("GOODS_COMMEND")
    private Integer goodsCommend;

    /**
     * 商品图片，分号隔开
     */
    @ApiModelProperty(value = "商品图片，分号隔开")
    @TableField("GOODS_IMG")
    private String goodsImg;

    /**
     * 收藏数
     */
    @ApiModelProperty(value = "收藏数")
    @TableField("GOODS_COLLECT")
    private Long goodsCollect;

    /**
     * 商品点击数量
     */
    @ApiModelProperty(value = "商品点击数量")
    @TableField("GOODS_CLICK")
    private Long goodsClick;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * ERP商品表类型=5为赠品
     */
    @ApiModelProperty(value = "ERP商品表类型=5为赠品")
    @TableField("ERP_ACC_FLAG")
    private Integer erpAccFlag;

    /**
     * 保管账
     */
    @ApiModelProperty(value = "保管账")
    @TableField("ERP_STORAGE_ID")
    private Long erpStorageId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    @TableField("ERP_LOT_NO")
    private String erpLotNo;

    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 货品图片数量
     */
    @ApiModelProperty(value = "货品图片数量")
    @TableField("IMG_NUM")
    private Integer imgNum;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @TableField("CREATE_USER_ID")
    private Long createUserId;

    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容")
    @TableField("ACTIVITY_CONTENT")
    private String activityContent;

    /**
     * 积分商品上下架：0为下架，1为上架
     */
    @ApiModelProperty(value = "积分商品上下架：0为下架，1为上架")
    @TableField("INTEGRAL_GOODS")
    private Long integralGoods;

    /**
     * 可兑换积分
     */
    @ApiModelProperty(value = "可兑换积分")
    @TableField("CONVERTIBLE_INTEGRAL")
    private Long convertibleIntegral;

    /**
     * 0否，1是
     */
    @ApiModelProperty(value = "是否参与抽奖")
    @TableField("IS_LOT")
    private Integer isLot;


    @ApiModelProperty(value = "抽奖数量")
    @TableField("LOT_NUM")
    private Integer lotNum;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
}
