package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付交易
 * </p>
 *
 * @author MP
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PAY_TRADE")
@ApiModel(value="PayTrade对象", description="支付交易")
@KeySequence(value = "SEQ_PAY_TRADE")
public class PayTrade extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 支付订单ID
     */
    @ApiModelProperty(value = "支付订单ID")
    @TableField("PAY_ORDER_ID")
    private Long payOrderId;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号")
    @TableField("PAY_FLOW_NO")
    private String payFlowNo;

    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    @TableField("MERCHANT_ID")
    private String merchantId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 请求报文
     */
    @ApiModelProperty(value = "请求报文")
    @TableField("REQUEST")
    private String request;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @TableField("REFUN_FEE")
    private Long refunFee;

    /**
     * 退款单号
     */
    @ApiModelProperty(value = "退款单号")
    @TableField("REFUND_NO")
    private String refundNo;

    /**
     * 响应报文
     */
    @ApiModelProperty(value = "响应报文")
    @TableField("RESPONSE")
    private Clob response;

    /**
     * 第三退款单号
     */
    @ApiModelProperty(value = "第三退款单号")
    @TableField("OUT_REFUND_NO")
    private String outRefundNo;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;


}
