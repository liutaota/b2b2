package com.zsyc.zt.inca.job;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.zsyc.zt.inca.service.OrderBackService;
import com.zsyc.zt.inca.vo.OrderCallbackVo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * 配送订单退货 -- 状态返回
 * @author liutao
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/1 8:39
 */
@Slf4j
public class OrderPSBackStatusCallbackJob extends QuartzJobBean {


    



//    @Resource
//    RabbitTemplate rabbitTemplate;
    @Resource
    OrderBackService orderBackService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("配送订单退货回写报货平台---开始------");
        /**
         * 查询所有订单状态 liutao
         */
        List<String> b2bOrderIds=orderBackService.selectOrderPSNotCallback(11,4,1);
        if(ObjectUtil.isNotEmpty(b2bOrderIds)){
            log.info("需要回写的订单:"+JSONObject.toJSONString(b2bOrderIds));
            OrderCallbackVo orderCallbackVo = OrderCallbackVo.newInstance(b2bOrderIds,2);
            //FIXME remove MQ
            // rabbitTemplate.convertAndSend(Constant.RABBIT_MQ.EXCHANGE_DIRECT, Constant.RABBIT_MQ.ROUTING_KEY_ORDER_STATUS_CALLBACK, JSONObject.toJSONString(orderCallbackVo));
            orderBackService.updatePSCallbackStatus(b2bOrderIds, 2);
        }
        log.info("配送订单退货回写报货平台---结束------");
    }
}
