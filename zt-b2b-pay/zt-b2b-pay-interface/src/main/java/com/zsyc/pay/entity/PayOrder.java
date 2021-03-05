package com.zsyc.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zsyc.framework.base.BaseMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 支付订单
 * </p>
 *
 * @author MP
 * @since 2019-04-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayOrder extends BaseMeta {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 商户key(appid)
     */
    private String merchantKey;

    /**
     * 业务订单号
     */
    private String orderNo;

    /**
     * 支付订单号
     */
    private String payFlowNo;

    /**
     * 币种
     */
    private String feeType;

    /**
     * 金额
     */
    private Integer totalFee;

    /**
     * 退款金额
     */
    private Integer refundFee;

    /**
     * 交易起始时间
     */
    private LocalDateTime timeStart;

    /**
     * 交易过期时间
     */
    private LocalDateTime timeExpire;

    /**
     * 交易完成时间
     */
    private LocalDateTime timeEnd;

    /**
     * 状态
     */
    private String status;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 商品描述
     */
    private String body;


    public enum EOrderStatus {
        PENDING("待支付"),
        SUCCESS("支付成功"),
        FAIL("支付失败"),
        REFUND("退款"),
        REFUND_FAIL("退款失败"),
        REFUND_SUCCESS("退款成功"),

        PARTIAL_REFUND("部分退款"),
        PARTIAL_REFUND_FAIL("部分退款失败"),
        PARTIAL_REFUND_SUCCESS("部分退款成功"),

        CANCEL("取消支付"),
        ;
        private String desc;


        EOrderStatus(String desc) {
            this.desc = desc;
        }

        public String desc() {
            return this.desc;
        }

        public String val() {
            return name();
        }
    }
}
