package com.zsyc.zt.order.event;

import cn.hutool.core.util.ObjectUtil;
import com.zsyc.zt.b2b.service.GoodsService;
import com.zsyc.zt.order.api.service.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderPushEventListener implements ApplicationListener<OrderPushEvent> {

    @Reference
    private GoodsService goodsService;

    @Resource
    OrderService orderService;

    @Override
    @Async
    public void onApplicationEvent(OrderPushEvent event) {
        if(ObjectUtil.isNull(event.getOrderInfoDocVo())){
            return;
        }
        //orderService.genOrder(event.getOrderInfoDocVo());
        /**
         * 使用版本2 ，推送到报货平台，使用报货平台的逻辑生产订单
         */
        orderService.genOrderV2(event.getOrderInfoDocVo());
    }
}
