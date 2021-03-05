package com.zsyc.zt.inca.vo;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/1 11:01
 */
@Data
public class OrderCallbackVo {
    List<String> orderIds;
    /**
     * 2 出库完成
     */
    private Integer status;

    private Date time;


    public static OrderCallbackVo newInstance(List<String> orderIds,Integer status){
        OrderCallbackVo orderCallbackVo = new OrderCallbackVo();

        orderCallbackVo.setOrderIds(orderIds);
        orderCallbackVo.setStatus(status);
        orderCallbackVo.setTime(DateTime.now().toJdkDate());
        return orderCallbackVo;
    }
}
