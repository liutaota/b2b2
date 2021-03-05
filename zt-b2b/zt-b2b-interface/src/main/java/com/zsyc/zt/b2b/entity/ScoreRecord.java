package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户积分记录
 * </p>
 *
 * @author MP
 * @since 2020-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("SCORE_RECORD")
@ApiModel(value="ScoreRecord对象", description="用户积分记录")
@KeySequence(value = "SEQ_SCORE_RECORD")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecord extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId("ID")
    private Long id;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @TableField("CONTENT")
    private String content;

    /**
     * 增或减积分
     */
    @ApiModelProperty(value = "增或减积分")
    @TableField("CHAN_SCORE")
    private Long chanScore;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 来源类型：1-订单 2-新用户 3-后台减少
     */
    @ApiModelProperty(value = "来源类型：1-订单 2-新用户 3-后台减少")
    @TableField("FROM_TYPE")
    private String fromType;

    /**
     * 来源id
     */
    @ApiModelProperty(value = "来源id")
    @TableField("FROM_ID")
    private Long fromId;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 原本积分
     */
    @ApiModelProperty(value = "原本积分")
    @TableField("ORIGN_SCORE")
    private Long orignScore;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;


}
