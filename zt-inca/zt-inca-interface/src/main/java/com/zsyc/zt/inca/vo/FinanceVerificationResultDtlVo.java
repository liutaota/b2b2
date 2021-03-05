package com.zsyc.zt.inca.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author peiqy
 */
@Data
@Accessors(chain = true)
public class FinanceVerificationResultDtlVo {

    public FinanceVerificationResultDtlVo(String orderId, String orderDtlId, Long goodsId, Double num) {
        this.orderId = orderId;
        this.orderDtlId = orderDtlId;
        this.goodsId = goodsId;
        this.num = num;
    }

    private String orderDtlId;
    private String orderId;
    private Long goodsId;

    private Double num;

}
