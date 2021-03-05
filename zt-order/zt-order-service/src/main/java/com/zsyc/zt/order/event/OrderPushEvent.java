package com.zsyc.zt.order.event;

import com.zsyc.zt.order.vo.OrderInfoDocVo;
import org.springframework.context.ApplicationEvent;

public class OrderPushEvent extends ApplicationEvent {
    OrderInfoDocVo orderInfoDocVo;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    private OrderPushEvent(Object source) {
        super(source);
    }


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public OrderPushEvent(OrderInfoDocVo orderInfoDocVo, Object source) {
        super(source);
        this.orderInfoDocVo = orderInfoDocVo;
    }

    public OrderInfoDocVo getOrderInfoDocVo() {
        return orderInfoDocVo;
    }
}
