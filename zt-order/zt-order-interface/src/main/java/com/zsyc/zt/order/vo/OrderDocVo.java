package com.zsyc.zt.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderDocVo implements Serializable {

    String orderNo;


    List<OrderDetailVo> detailList;
}
