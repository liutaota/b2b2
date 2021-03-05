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
 * 商品评价
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("EVALUATE_GOODS")
@ApiModel(value="EvaluateGoods对象", description="商品评价")
@KeySequence(value = "SEQ_EVALUATE_GOODS")
public class EvaluateGoods extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 订单表id
     */
    @ApiModelProperty(value = "订单表id")
    @TableField("EVAL_ORDER_ID")
    private Long evalOrderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @TableField("EVAL_ORDER_NO")
    private String evalOrderNo;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("EVAL_GOODS_ID")
    private Long evalGoodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("EVAL_GOODS_NAME")
    private String evalGoodsName;

    /**
     * 1-5分
     */
    @ApiModelProperty(value = "1-5分")
    @TableField("EVAL_SCORES")
    private Integer evalScores;

    /**
     * 信誉评价内容
     */
    @ApiModelProperty(value = "信誉评价内容")
    @TableField("EVAL_CONTENT")
    private String evalContent;

    /**
     * 是否匿名，0否，1是，默认0
     */
    @ApiModelProperty(value = "是否匿名，0否，1是，默认0")
    @TableField("EVAL_IS_ANONYMOUS")
    private Integer evalIsAnonymous;

    /**
     * 创建时间/评价时间
     */
    @ApiModelProperty(value = "创建时间/评价时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 评价人id
     */
    @ApiModelProperty(value = "评价人id")
    @TableField("EVAL_MEMBER_ID")
    private Long evalMemberId;

    /**
     * 评价人名称
     */
    @ApiModelProperty(value = "评价人名称")
    @TableField("EVAL_MEMBER_NAME")
    private String evalMemberName;

    /**
     * 解释内容
     */
    @ApiModelProperty(value = "解释内容")
    @TableField("EVAL_EXPLAIN")
    private String evalExplain;

    /**
     * 评价图片
     */
    @ApiModelProperty(value = "评价图片")
    @TableField("EVAL_IMAGE")
    private String evalImage;

    /**
     * 追加评价内容
     */
    @ApiModelProperty(value = "追加评价内容")
    @TableField("EVAL_CONTENT_AGAIN")
    private String evalContentAgain;

    /**
     * 追加评价时间
     */
    @ApiModelProperty(value = "追加评价时间")
    @TableField("EVAL_ADDTIME_AGAIN")
    private LocalDateTime evalAddtimeAgain;

    /**
     * 追加评价图片
     */
    @ApiModelProperty(value = "追加评价图片")
    @TableField("EVAL_IMAGE_AGAIN")
    private String evalImageAgain;

    /**
     * 追加评价解释
     */
    @ApiModelProperty(value = "追加评价解释")
    @TableField("EVAL_EXPLAIN_AGAIN")
    private String evalExplainAgain;


}
