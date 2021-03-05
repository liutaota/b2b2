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
 * 每日报表
 * </p>
 *
 * @author MP
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("DAY_BILL")
@ApiModel(value="DayBill对象", description="每日报表")
@KeySequence(value = "SEQ_DAY_BILL")
public class DayBill extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 报表编号
     */
    @ApiModelProperty(value = "报表编号")
    @TableField("BILL_NO")
    private String billNo;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @TableField("START_TIME")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @TableField("END_TIME")
    private LocalDateTime endTime;

    /**
     * 订单总额
     */
    @ApiModelProperty(value = "订单总额")
    @TableField("ORDER_AMOUNT")
    private double orderAmount;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    @TableField("PAYABLE_AMOUNT")
    private double payableAmount;

    /**
     * 现结金额
     */
    @ApiModelProperty(value = "现结金额")
    @TableField("CASE_AMOUNT")
    private double caseAmount;

    /**
     * 月结金额
     */
    @ApiModelProperty(value = "月结金额")
    @TableField("MONTHLY_AMOUNT")
    private double monthlyAmount;

    /**
     * 在线支付金额
     */
    @ApiModelProperty(value = "在线支付金额")
    @TableField("ONLINE_AMOUNT")
    private double onlineAmount;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @TableField("REFUND_AMOUNT")
    private double refundAmount;

    /**
     * 短减金额
     */
    @ApiModelProperty(value = "短减金额")
    @TableField("REDUCTION_AMOUNT")
    private Long reductionAmount;

    /**
     * 促销金额
     */
    @ApiModelProperty(value = "促销金额")
    @TableField("PROMOTION_AMOUNT")
    private double promotionAmount;

    /**
     * 订单数
     */
    @ApiModelProperty(value = "订单数")
    @TableField("ORDER_NUM")
    private Long orderNum;

    /**
     * 退单数
     */
    @ApiModelProperty(value = "退单数")
    @TableField("RETURN_ORDER_NUM")
    private Long returnOrderNum;

    /**
     * 异常订单数
     */
    @ApiModelProperty(value = "异常订单数")
    @TableField("EXCEPTION_ORDER_NUM")
    private Long exceptionOrderNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 订单日期
     */
    @ApiModelProperty(value = "订单日期")
    @TableField("BILL_DATE")
    private String billDate;


}
