package com.zsyc.pay.service;

import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.vo.PayWithdrawVo;
import org.apache.commons.lang3.NotImplementedException;

/**
 * 内部回调
 * Created by lcs on 2019-04-08.
 */
public interface InnerCallbackService {
    /**
     * 支付成功回调
     *
     * @param payOrder
     */
    void payCallback(PayOrder payOrder);


    /**
     * 退款成功回调
     *
     * @param payOrder
     */
    @Deprecated
    void refundCallback(PayOrder payOrder);

    /**
     * 提现成功回调
     *
     * @param withdrawVo
     */
    @Deprecated
    default void withDrawCallback(PayWithdrawVo withdrawVo,boolean isSuccess) {
        throw new NotImplementedException("");
    }
}
