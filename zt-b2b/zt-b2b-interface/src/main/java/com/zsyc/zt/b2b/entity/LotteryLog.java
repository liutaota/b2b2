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
 * 抽奖日志表
 * </p>
 *
 * @author MP
 * @since 2020-12-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("LOTTERY_LOG")
@ApiModel(value = "LotteryLog对象", description = "抽奖日志表")
@KeySequence(value = "SEQ_LOTTERY_LOG")
public class LotteryLog extends BaseBean {

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
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    @TableField("LOT_ID")
    private Long lotId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

}
