package com.zsyc.zt.inca.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.zsyc.zt.b2b.service.OrderInfoService;
import com.zsyc.zt.inca.service.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/17 9:11
 */
@Component
public class OrderStatusCallbackXxlJob {
    @Resource
    OrderService orderService;
//
//    @Resource
//    RabbitTemplate rabbitTemplate;

    @Reference
    private OrderInfoService orderInfoService;

    /**
     * 订单状态回写
     */
    @XxlJob("orderStatusCallback")
    public ReturnT<String> orderStatusCallback(String param) throws Exception {

       /* XxlJobLogger.log("执行京东订单数据同步到英克--手工--任务开始");

        *//**
         * 查询所有订单状态
         *//*
        List<String> orderIds = orderService.selectOrderNotCallback(11, 1, 1);
        if (ObjectUtil.isNotEmpty(orderIds)) {
            XxlJobLogger.log("需要回写的订单:" + JSONObject.toJSONString(orderIds));
            OrderCallbackVo orderCallbackVo = OrderCallbackVo.newInstance(orderIds, 2);
           // this.orderInfoService.updateOrderState(orderIds);
            //rabbitTemplate.convertAndSend(Constant.RABBIT_MQ.EXCHANGE_DIRECT, Constant.RABBIT_MQ.ROUTING_KEY_ORDER_STATUS_CALLBACK, JSONObject.toJSONString(orderCallbackVo));
            orderService.updateCallbackStatus(orderIds, 2);
        }
        XxlJobLogger.log("执行京东订单数据同步到英克--手工--任务结束");*/
        return ReturnT.SUCCESS;
    }
}
