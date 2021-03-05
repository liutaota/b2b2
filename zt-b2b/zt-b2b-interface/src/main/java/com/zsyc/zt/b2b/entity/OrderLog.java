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
 * 订单日志
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ORDER_LOG")
@ApiModel(value = "OrderLog对象", description = "订单日志")
@KeySequence(value = "SEQ_ORDER_LOG")
public class OrderLog extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    @TableField("ORDER_STATUS")
    private String orderStatus;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 文字描述
     */
    @ApiModelProperty(value = "文字描述")
    @TableField("LOG_MSG")
    private String logMsg;

    /**
     * 操作角色
     */
    @ApiModelProperty(value = "操作角色")
    @TableField("LOG_ROLE")
    private String logRole;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private LocalDateTime doneTime;

    /**
     * 下发版本（次数）
     */
    @ApiModelProperty(value = "下发版本（次数）")
    @TableField("TO_ERP_NUM")
    private String toErpNum;

    /**
     * @see #orderStatus
     */
    public enum EOrderStatus implements IEnum {
        CANCEL("已取消"),
        UNPAID("未付款"),
        TO_ERP("待下发"),
        SEND_ERP("已下发"),
        TO_SEND("拣货中"),
        TO_DELIVERY("待收货"),
        TO_RECEIVED("已收货"),
        DONE("完成"),
        APPLY_REFUND("申请退款"),
        SUCCESS_REFUND("退款成功"),
        ORDER_EXP("erp异常"),
        NO_ORDER("整单不出"),
        SHORT("短减"),
        CPFR("补货"),
        INTERCEPT("拦单异常"),
        PART_REFUND("部分退款"),
        ALL_REFUND("全部退款"),
        DEAL_WITH("处理中"),
        CATCH_EXP("未知异常"),
        INTERCEPT_EXP("拦截异常"),
        AMOUNT_EXP("金额异常"),
        ;
        private String desc;

        EOrderStatus(String desc) {
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
