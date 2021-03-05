package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 可抽奖次数
 * </p>
 *
 * @author MP
 * @since 2020-12-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("LOTTER_DAIL_QUALIFICATIONS")
@ApiModel(value = "LotterDailQualifications对象", description = "可抽奖次数")
@KeySequence(value = "SEQ_LOTTER_DAIL_QUALIFICATIONS")
public class LotterDailQualifications extends BaseBean {

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
    @TableField("LOT_ID")
    private Long lotId;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 抽奖活动类型
     */
    @ApiModelProperty(value = "抽奖活动类型")
    @TableField("LOT_TYPE")
    private String lotType;

    /**
     * 可抽奖次数
     */
    @ApiModelProperty(value = "可抽奖次数")
    @TableField("LOT_NUM")
    private Integer lotNum;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 抽奖状态
     */
    @ApiModelProperty(value = "抽奖状态")
    @TableField("STATUS")
    private String status;


    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    @ApiModelProperty(value = "抽奖结束时间")
    @TableField("LOT_END_TIME")
    private LocalDateTime lotEndTime;

    /**
     * @see #status 状态
     */
    public enum EStatus implements IEnum {
        TMP("临时存放抽奖次数"),
        ORDER("下单赠送抽奖次数");
        private String desc;

        EStatus(String desc) {
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
