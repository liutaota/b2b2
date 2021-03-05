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
 * 转盘抽奖详情
 * </p>
 *
 * @author MP
 * @since 2020-12-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("LOTTERY_DIAL_DETAIL")
@ApiModel(value="LotteryDialDetail对象", description="转盘抽奖详情")
@KeySequence(value = "SEQ_LOTTERY_DIAL_DETAIL")
public class LotteryDialDetail extends BaseBean {

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
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    @TableField("MEMBER_NAME")
    private String memberName;

    /**
     * 奖项名称
     */
    @ApiModelProperty(value = "奖项名称")
    @TableField("RATE_NAME")
    private String rateName;

    /**
     * 奖项类型 0未中奖，1积分，2实物
     */
    @ApiModelProperty(value = "奖项类型")
    @TableField("RATE_TYPE")
    private String rateType;

    /**
     * 奖品信息
     */
    @ApiModelProperty(value = "奖品信息")
    @TableField("PRIZE_INFO")
    private String prizeInfo;

    /**
     * 派奖状态
     */
    @ApiModelProperty(value = "派奖状态")
    @TableField("PRIZE_STATE")
    private String prizeState;

    /**
     * 派奖时间
     */
    @ApiModelProperty(value = "派奖时间")
    @TableField("PRIZE_TIME")
    private LocalDateTime prizeTime;

    /**
     * 发奖人
     */
    @ApiModelProperty(value = "发奖人")
    @TableField("PRIZE_MEMBER")
    private Long prizeMember;

    /**
     * 派奖备注
     */
    @ApiModelProperty(value = "派奖备注")
    @TableField("PRIZE_REMARK")
    private String prizeRemark;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "数量")
    @TableField("PRIZE_NUM")
    private Integer prizeNum;

    /**
     * @see #prizeState 状态
     */
    public enum EPrizeState implements IEnum {
        UN_PRIZE("未派奖"),
        TO_PRIZE("已派奖");
        private String desc;

        EPrizeState(String desc) {
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
