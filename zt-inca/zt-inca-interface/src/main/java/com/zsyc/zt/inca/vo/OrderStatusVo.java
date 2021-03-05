package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  peiqy
 */
@Data
public class OrderStatusVo implements Serializable {

    private Long b2bOrderId;
    /**
     * 1 订单不存在  2 订单在拣货中 3 订单已经出库
     */
    private Integer status;


}
