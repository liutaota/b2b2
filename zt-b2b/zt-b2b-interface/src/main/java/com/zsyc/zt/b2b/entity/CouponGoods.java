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
 * 优惠券可用商品
 * </p>
 *
 * @author MP
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("COUPON_GOODS")
@ApiModel(value="CouponGoods对象", description="优惠券可用商品")
@KeySequence(value = "SEQ_COUPON_GOODS")
public class CouponGoods extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 优惠券ID
     */
    @ApiModelProperty(value = "优惠券ID")
    @TableField("COUPON_ID")
    private Long couponId;

    /**
     * 商品集合ID
     */
    @ApiModelProperty(value = "商品集合ID")
    @TableField("GOODS_SET_ID")
    private Long goodsSetId;

    /**
     * 是否可用，0为否，1为是
     */
    @ApiModelProperty(value = "是否可用，0为否，1为是")
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
     * 商品集合
     * 1-ALL 全部
     * 2-PART 部分商品集合
     * 3-UN_VISIBLE 部分不可见商品集合
     */
    @ApiModelProperty(value = "商品集合 1-ALL 全部 2-PART 部分可用商品集合 3-UN_VISIBLE 部分不可用商品集合")
    @TableField("VISIBLE_TYPE")
    private String visibleType;

    /**
     * @see #visibleType
     */
    public enum EVisibleType implements IEnum {
        ALL("全部"),
        PART("部分可用商品集合"),
        UN_VISIBLE("部分不可用商品集合"),
        ;
        private String desc;

        EVisibleType(String desc) {
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
