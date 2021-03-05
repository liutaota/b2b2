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
 * 退货退款单
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("REFUND_RETURN")
@ApiModel(value = "RefundReturn对象", description = "退货退款单")
@KeySequence(value = "SEQ_REFUND_RETURN")
public class RefundReturn extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 申请编号
     */
    @ApiModelProperty(value = "申请编号")
    @TableField("APPLY_NO")
    private String applyNo;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 买家会员名
     */
    @ApiModelProperty(value = "买家会员名")
    @TableField("MEMBER_NAME")
    private String memberName;

    /**
     * 商品id，0为全部退款
     */
    @ApiModelProperty(value = "商品id，0为全部退款")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 订单商品ID,全部退款是0
     */
    @ApiModelProperty(value = "订单商品ID,全部退款是0")
    @TableField("ORDER_GOODS_ID")
    private Long orderGoodsId;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @TableField("REFUND_AMOUNT")
    private double refundAmount;

    /**
     * 申请类型:1为退款,2为退货,默认为1
     */
    @ApiModelProperty(value = "申请类型:1为退款,2为退货,默认为1")
    @TableField("APPLY_TYPE")
    private String applyType;

    /**
     * 申请状态:1为处理中,2为待管理员处理,3为已完成,默认为1
     */
    @ApiModelProperty(value = "申请状态:1为处理中,2为待管理员处理,3为已完成,默认为1")
    @TableField("REFUND_STATE")
    private String refundState;

    /**
     * 退货类型:1为不用退货,2为需要退货,默认为1
     */
    @ApiModelProperty(value = "退货类型:1为不用退货,2为需要退货,默认为1")
    @TableField("RETURN_TYPE")
    private String returnType;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 管理 员id
     */
    @ApiModelProperty(value = "管理 员id")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 管理内容审核时间
     */
    @ApiModelProperty(value = "管理内容审核时间")
    @TableField("USER_TIME")
    private LocalDateTime userTime;

    /**
     * 原因id，0为其他
     */
    @ApiModelProperty(value = "原因id，0为其他")
    @TableField("REASON_ID")
    private Long reasonId;

    /**
     * 原因内容
     */
    @ApiModelProperty(value = "原因内容 ")
    @TableField("REASON_INFO")
    private String reasonInfo;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    @TableField("PIC_INFO")
    private String picInfo;

    /**
     * 申请原因
     */
    @ApiModelProperty(value = "申请原因")
    @TableField("MEMBER_MESSAGE")
    private String memberMessage;

    /**
     * 管理员备注
     */
    @ApiModelProperty(value = "管理员备注")
    @TableField("USER_MESSAGE")
    private String userMessage;

    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    @TableField("SHIP_TIME")
    private String shipTime;

    /**
     * 收货延迟时间
     */
    @ApiModelProperty(value = "收货延迟时间")
    @TableField("DELAY_TIME")
    private LocalDateTime delayTime;

    /**
     * 收货时间
     */
    @ApiModelProperty(value = "收货时间")
    @TableField("RECEIVE_TIME")
    private LocalDateTime receiveTime;

    /**
     * 收货备注
     */
    @ApiModelProperty(value = "收货备注")
    @TableField("RECEIVE_MESSAGE")
    private String receiveMessage;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @TableField("FINNSHED_TIME")
    private LocalDateTime finnshedTime;

    /**
     * erp客户id
     */
    @ApiModelProperty(value = "erp客户id")
    @TableField("ERP_CUSTOMER_ID")
    private Long erpCustomerId;

    /**
     * 退货单异常信息
     */
    @ApiModelProperty(value = "退货单异常信息")
    @TableField("REFUND_MESSAGE")
    private String refundMessage;

    @ApiModelProperty(value = "重新下发操作人")
    @TableField("RE_ERP_USER_ID")
    private Long reErpUserId;

    @ApiModelProperty(value = "重新下发备注")
    @TableField("RE_ERP_REMARK")
    private String reErpRemark;

    @ApiModelProperty(value = "确认收货人")
    @TableField("RECEIVE_USER_ID")
    private Long receiveUserId ;

    /**
     * @see #applyType
     */
    public enum EApplyType implements IEnum {
        REFUND("退款"),
        REFUND_GOODS("退货"),
        ;
        private String desc;

        EApplyType(String desc) {
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
     * @see #refundState 申请状态:1为处理中,2为待管理员处理,3为已完成,默认为1
     */
    public enum ERefundState implements IEnum {
        PENDING("待审核"),
        DEAL_WITH("处理中"),
        TO_SEND("待发货"),
        TO_DELIVERY("待收货"),
        UN_DELIVERY("待验收"),
        DELIVERY_DONE("已收货"),
        DONE("已完成"),
        FAIL("不通过"),
        ORDER_EXP("erp异常"),
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
     * @see #returnType  退货类型:1为不用退货,2为需要退货,默认为1
     */
    public enum EReturnType implements IEnum {
        NO_GOODS("不用退货"),
        NEED_GOODS("需要退货"),
        ;
        private String desc;

        EReturnType(String desc) {
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
