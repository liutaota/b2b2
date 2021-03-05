package com.zsyc.zt.inca.job;

import com.zsyc.zt.inca.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/1 8:39
 */
@Slf4j
public class OrderStatusCallbackJob extends QuartzJobBean {

    @Resource
    OrderService orderService;

//    @Resource
//    RabbitTemplate rabbitTemplate;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

//        log.info("订单状态回写报货平台---开始------");
//
//        /**
//         * 查询所有订单状态
//         */
//        List<String> orderIds = orderService.selectOrderNotCallback(11, 1, 1);
//        if(ObjectUtil.isNotEmpty(orderIds)){
//            log.info("需要回写的订单:"+JSONObject.toJSONString(orderIds));
//            OrderCallbackVo orderCallbackVo = OrderCallbackVo.newInstance(orderIds,2);
//
//            rabbitTemplate.convertAndSend(Constant.RABBIT_MQ.EXCHANGE_DIRECT, Constant.RABBIT_MQ.ROUTING_KEY_DEMO, JSONObject.toJSONString(orderCallbackVo));
//            orderService.updateCallbackStatus(orderIds, 2);
//        }
//
//
//        log.info("订单状态回写报货平台---结束------");
    }
}
