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
 * 优惠券客户集合
 * </p>
 *
 * @author MP
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("COUPON_MEMBER")
@ApiModel(value="CouponMember对象", description="优惠券客户集合")
@KeySequence(value = "SEQ_COUPON_MEMBER")
public class CouponMember extends BaseBean {

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
     * 客户集合ID
     */
    @ApiModelProperty(value = "客户集合ID")
    @TableField("CUSTOM_SET_ID")
    private Long customSetId;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 添加者id
     */
    @ApiModelProperty(value = "添加者id")
    @TableField("ADMIN_ID")
    private Long adminId;

    /**
     * 客户集合类型
     * 10-ALL 全部可见
     * 20-VISIBLE 部分可见
     * 30- UN_VISIBLE 部分不可见
     */
    @ApiModelProperty(value = "客户集合类型 10-ALL 全部可见 20-VISIBLE 部分可见 30- UN_VISIBLE 部分不可见")
    @TableField("VISIBLE_TYPE")
    private String visibleType;


    /**
     * @see #visibleType
     */
    public enum EVisibleType implements IEnum {
        ALL("全部可见"),
        VISIBLE("部分可见"),
        UN_VISIBLE("部分不可见"),
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
