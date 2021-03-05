package com.zsyc.pay.service;

import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.vo.PayOrderVo;
import com.zsyc.pay.vo.PayWithdrawVo;

/**
 * Created by lcs on 2019-04-03.
 */
public interface PayOrderService<T extends PayOrder> {
    /**
     * 支付下单
     * 有 openid 进行 PC 支付；
     * 没有 openid 进行 微信 支付
     *
     * @param payOrder
     * @return
     */
    T payOrder(T payOrder);

    /**
     * 支付结果通知
     *
     * @param requestBody
     * @return
     */
    PayOrder payCallback(String requestBody);

    /**
     * 退款结果通知
     *
     * @param requestBody
     * @return
     */
    @Deprecated
    PayOrder refundCallback(String requestBody);


    /**
     * 获取支付订单
     *
     * @param orderNo
     * @return
     */
    @Deprecated
    PayOrder queryOrder(String orderNo);

    /**
     * 支付退款
     *
     * @param orderNo
     * @return
     */
    PayOrderVo refund(String orderNo, String ref, Integer refundFree);

    /**
     * 提现
     */
    PayWithdrawVo widthDraw(PayWithdrawVo payWithdrawVo);

}
