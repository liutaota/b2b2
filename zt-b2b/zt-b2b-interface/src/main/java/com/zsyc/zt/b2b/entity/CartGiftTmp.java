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
 * 购物车商品赠品临时表
 * </p>
 *
 * @author MP
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("CART_GIFT_TMP")
@ApiModel(value="CartGiftTmp对象", description="购物车商品赠品临时表")
@KeySequence(value = "SEQ_CART_GIFT_TMP")
public class CartGiftTmp extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * erp商品id
     */
    @ApiModelProperty(value = "erp商品id")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    @TableField("AC_ID")
    private Long acId;

    /**
     * 策略id
     */
    @ApiModelProperty(value = "策略id")
    @TableField("AS_ID")
    private Long asId;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    @TableField("GOODS_NUM")
    private Long goodsNum;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 可选数量
     */
    @ApiModelProperty(value = "可选数量")
    @TableField("NUM")
    private Long NUM;

}
