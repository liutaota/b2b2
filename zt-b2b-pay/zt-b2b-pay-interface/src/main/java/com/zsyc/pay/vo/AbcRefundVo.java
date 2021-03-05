package com.zsyc.pay.vo;

import com.zsyc.framework.base.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 农行退款
 * Created by lcs on 2020/12/9.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AbcRefundVo extends BaseBean {
    /**
     * 支付订单编号
     */
    private String orderNo;
    /**
     * 退款交易编号
     */
    private String newOrderNo;
    /**
     * 退款金额(单位：分)
     */
    private Integer refundAmount;
    /**
     * 附言
     */
    private String remark;
}
