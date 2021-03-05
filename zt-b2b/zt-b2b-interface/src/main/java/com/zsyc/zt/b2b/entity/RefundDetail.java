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
 * 退单详情
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("REFUND_DETAIL")
@ApiModel(value="RefundDetail对象", description="退单详情")
@KeySequence(value = "SEQ_REFUND_DETAIL")
public class RefundDetail extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 退货退款单id
     */
    @ApiModelProperty(value = "退货退款单id")
    @TableField("REFUND_ID")
    private Long refundId;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    @TableField("LOT_NO")
    private String lotNo;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @TableField("REFUND_AMOUNT")
    private double refundAmount;

    /**
     * 在线退款金额
     */
    @ApiModelProperty(value = "在线退款金额")
    @TableField("PAY_AMOUNT")
    private double payAmount;

    /**
     * 账期退款金额
     */
    @ApiModelProperty(value = "账期退款金额")
    @TableField("PD_AMOUNT")
    private double pdAmount;

    /**
     * 退款支付代码
     */
    @ApiModelProperty(value = "退款支付代码")
    @TableField("REFUND_CODE")
    private String refundCode;

    /**
     * 退款状态:1为处理中,2为已完成,默认为1
     */
    @ApiModelProperty(value = "退款状态:1为处理中,2为已完成,默认为1")
    @TableField("REFUND_STATE")
    private String refundState;

    /**
     * 退款完成时间
     */
    @ApiModelProperty(value = "退款完成时间")
    @TableField("REFUND_FINNSHED_TIME")
    private LocalDateTime refundFinnshedTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 订单商品ID,全部退款是0
     */
    @ApiModelProperty(value = "订单商品ID")
    @TableField("ORDER_GOODS_ID")
    private Long orderGoodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    @TableField("GOODS_NUM")
    private double goodsNum;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GODOS_IMAGE")
    private String godosImage;

    @ApiModelProperty("销售总单ID")
    private Long srcErpOrderId;

    @ApiModelProperty("销售细单ID")
    private Long srcErpOrderDtlId;

    @ApiModelProperty("单据类型ID")
    private Long goodsSource;

    /**
     * 保管帐id
     */
    @ApiModelProperty(value = "保管帐id")
    @TableField("ERP_STORAGE_ID")
    private Integer erpStorageId;

    /**
     * 订单商品类型:1默认2限时折扣商品4组合套装
     */
    @ApiModelProperty(value = "订单商品类型:1默认2限时折扣商品4组合套装")
    @TableField("ORDER_GOODS_TYPE")
    private String orderGoodsType;


    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    @TableField("LOT_ID")
    private Long lotId;

    /**
     * 批次ID
     */
    @ApiModelProperty(value = "批次ID")
    @TableField("BATCH_ID")
    private Long  batchId ;

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
     *单价
     */
    @ApiModelProperty(value = "单价")
    @TableField("GOODS_PRICE")
    private double goodsPrice;

    /**
     * 商品实际成交价
     */
    @ApiModelProperty(value = "商品优惠平均成交价")
    @TableField("GOODS_PAY_PRICE")
    private double goodsPayPrice;

    /**
     * 货品细单id
     */
    @ApiModelProperty(value = "货品细单id")
    @TableField("GOODS_DTL_ID")
    private Long goodsDtlId;

    /**
     * @see #refundState
     */
    public enum ERefundState implements IEnum {
        PENDING("待审核"),
        DEAL_WITH("处理中"),
        DONE("已完成"),
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
     * @see #orderGoodsType
     */
    public enum EOrderGoodsType implements IEnum {
        DEFAULTS("默认"),
        DISCOUNT("限时折扣商品"),
        SUIT("组合套装"),
        ;
        private String desc;

        EOrderGoodsType(String desc) {
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
