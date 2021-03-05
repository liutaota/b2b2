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
 * 收款总单
 * </p>
 *
 * @author MP
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("REC_DOC")
@ApiModel(value = "RecDoc对象", description = "收款总单")
@KeySequence(value = "SEQ_REC_DOC")
public class RecDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 独立单元id
     */
    @ApiModelProperty(value = "独立单元id")
    @TableField("ENTRY_ID")
    private Long entryId;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 收款金额
     */
    @ApiModelProperty(value = "收款金额")
    @TableField("TOTAL")
    private double total;

    /**
     * 收款类别
     */
    @ApiModelProperty(value = "收款类别")
    @TableField("REC_METHOD")
    private String recMethod;

    /**
     * 结算方式：在线支付，现金
     */
    @ApiModelProperty(value = "结算方式：在线支付，现金")
    @TableField("REC_TYPE")
    private String recType;

    /**
     * 细单条数
     */
    @ApiModelProperty(value = "细单条数")
    @TableField("DTL_LINES")
    private Integer dtlLines;

    /**
     * 细单金额
     */
    @ApiModelProperty(value = "细单金额")
    @TableField("PREMONEY")
    private double premoney;

    /**
     * 状态（已取消，待结算，已结算）
     */
    @ApiModelProperty(value = "状态（已取消，待结算，已结算）")
    @TableField("STATUS")
    private String status;

    /**
     * 收款人id（app端用）
     */
    @ApiModelProperty(value = "收款人id（app端用）")
    @TableField("REC_USER_ID")
    private Long recUserId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @TableField("PAY_TIME")
    private LocalDateTime payTime;

    /**
     * 确定时间
     */
    @ApiModelProperty(value = "确定时间")
    @TableField("CONFIRM_DATE")
    private LocalDateTime confirmDate;

    /**
     * 确定人
     */
    @ApiModelProperty(value = "确定人")
    @TableField("CONFIRM_USER_ID")
    private Long confirmUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    @ApiModelProperty(value = "是否生成账单")
    @TableField("IS_TRUE")
    private Long isTrue;

    /**
     * 1,已核销，0未核销,2核销中，3核销失败
     */
    @ApiModelProperty(value = "核销是否通过")
    @TableField("FINANCE_TRUE")
    private Long financeTrue;

    @ApiModelProperty(value = "erp核销备注")
    @TableField("ERP_FINANCE_REMARK")
    private String erpFinanceRemark;

    @ApiModelProperty(value = "退款金额")
    @TableField("REFUND_MONEY")
    private double refundMoney;

    @ApiModelProperty(value = "退款单id")
    @TableField("REFUND_ID")
    private Long refundId;

    @ApiModelProperty(value = "退款操作人")
    @TableField("REFUND_USER_ID")
    private Long refundUserId;

    @ApiModelProperty(value = "核销操作人")
    @TableField("FINANCE_USER_ID")
    private Long financeUserId;

    @ApiModelProperty(value = "退款备注")
    @TableField("REFUND_REMARK")
    private String refundRemark;

    @ApiModelProperty(value = "退款时间")
    @TableField("REFUND_TIME")
    private LocalDateTime refundTime;

    @ApiModelProperty(value = "核销时间")
    @TableField("FINANCE_TIME")
    private LocalDateTime financeTime;

    @ApiModelProperty(value = "单号")
    @TableField("REC_DOC_NO")
    private String recDocNo;

    @ApiModelProperty(value = "退款单号")
    @TableField("PAY_ABC_NO")
    private String payAbcNo;

    @ApiModelProperty(value = "退款流水号")
    @TableField("PAY_REFUND_NO")
    private String payRefundNo;

    @ApiModelProperty(value = "农行日志")
    @TableField("ABC_MESSAGE")
    private String abcMessage;

    @ApiModelProperty(value = "退款状态")
    @TableField("REFUND_STATE")
    private String refundState;

    @ApiModelProperty(value = "确定备注")
    @TableField("CONFIRM_REMARK")
    private String confirmRemark;

    /**
     * @see # refundState
     */
    public enum ERefundState implements IEnum {
        NO_REFUND("未退款"),
        REFUND_SUCCESS("退款成功"),
        REFUND_FAIL("退款失败"),
        ;
        private String desc;

        ERefundState(String desc) {
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

    /**
     * @see # status
     */
    public enum EStatus implements IEnum {
        CANCEL("已取消"),
        TO_PAY("待结算"),
        DONE_PAY("已结算"),
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


    /**
     * @see #recMethod
     */
    public enum ERecMethod implements IEnum {
        PAY_ORDER("下单"),
        SHORT("短减"),
        NO_ORDER("整单不出"),
        REFUND("退款"),
        REFUND_GOODS("退货"),
        INTERCEPT("拦截退款"),
        ;
        private String desc;

        ERecMethod(String desc) {
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

    /**
     * @see #recType
     */
    public enum ERecType implements IEnum {
        ON_LINE("在线支付"),
        CASH("现金"),
        MONTH("月结"),
        OFF_LINE("线下支付"),
        ;
        private String desc;

        ERecType(String desc) {
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
