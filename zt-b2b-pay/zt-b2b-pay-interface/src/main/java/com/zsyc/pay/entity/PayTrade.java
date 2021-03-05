package com.zsyc.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zsyc.framework.base.BaseBean;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单交易流水
 * </p>
 *
 * @author MP
 * @since 2019-04-07
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayTrade extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支付订单ID
     */
    private Long payOrderId;

    /**
     * 支付订单号
     */
    private String payFlowNo;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 请求报文
     */
    private String request;

    /**
     * 退款金额
     */
    private Integer refunFee;

    /**
     * 退款单号
     */
    private String refundNo;

    /**
     * 响应报文
     */
    private String response;

    /**
     * 第三退款单号
     */
    private String outRefundNo;

    /**
     * 备注
     */
    private String remark;


}
