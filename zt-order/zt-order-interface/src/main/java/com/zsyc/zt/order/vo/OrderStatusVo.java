package com.zsyc.zt.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderStatusVo implements Serializable {
    private String orderNo;
    private Integer status;
}
