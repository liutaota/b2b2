package com.zsyc.zt.order.vo;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author peiqy
 */
@Data
@Accessors(chain = true)
public class OrderResultDtlVo implements Serializable {

    public OrderResultDtlVo(String orderId,String orderDtlId, Long goodsId, Double num) {
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
