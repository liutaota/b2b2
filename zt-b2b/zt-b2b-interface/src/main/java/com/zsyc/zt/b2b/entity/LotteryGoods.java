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
 * 抽奖商品
 * </p>
 *
 * @author MP
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("LOTTERY_GOODS")
@ApiModel(value="LotteryGoods对象", description="抽奖商品")
@KeySequence(value = "SEQ_LOTTERY_GOODS")
public class LotteryGoods extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 抽奖数量
     */
    @ApiModelProperty(value = "抽奖数量")
    @TableField("LOT_NUM")
    private Long lotNum;

    /**
     * 抽奖总数量
     */
    @ApiModelProperty(value = "抽奖总数量")
    @TableField("LOT_TOTAL_NUM")
    private Long lotTotalNum;

    /**
     * 状态，上下架
     */
    @ApiModelProperty(value = "状态，上下架")
    @TableField("STATUS")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * @see #status
     */
    public enum EStatus implements IEnum {
        OFF("不启用"),
        ON("启用"),
        ;
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
