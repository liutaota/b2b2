package com.zsyc.zt.order.api.controller;

import com.zsyc.zt.order.api.service.OrderService;
import com.zsyc.zt.order.config.security.ApiUserDetails;
import com.zsyc.zt.order.event.OrderPushEvent;
import com.zsyc.zt.order.vo.OrderDocVo;
import com.zsyc.zt.order.vo.OrderInfoDocVo;
import com.zsyc.zt.order.vo.OrderResultDocVo;
import com.zsyc.zt.order.vo.OrderStatusVo;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    OrderService orderService;


    @RequestMapping("/push")
    @PreAuthorize("isAuthenticated()")
    public OrderResultDocVo pushOrder(@RequestBody OrderInfoDocVo orderInfoDocVo) {
        ApiUserDetails userDetails = (ApiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        orderInfoDocVo.setCustomId(userDetails.getCustomId());
        orderInfoDocVo.setB2bCustomId(userDetails.getB2bCustomId());
        orderInfoDocVo.setEntryId(1);

        applicationEventPublisher.publishEvent(new OrderPushEvent(orderInfoDocVo,"inca-api"));

        return new OrderResultDocVo(orderInfoDocVo.getOrderNo(),orderInfoDocVo.getCustomId(),"订单已经下发!,状态请稍候获取！！！");
    }

    /**
     * 1 没有订单  2  还未出库  3 已经出库
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("/status")
    @PreAuthorize("isAuthenticated()")
    public OrderStatusVo getOrderStatus(@RequestParam("order_no") String orderNo) {
        OrderStatusVo orderStatusVo = new OrderStatusVo();
        orderStatusVo.setOrderNo(orderNo);
        orderStatusVo.setStatus(orderService.getOrderStatus(orderNo));
        return orderStatusVo;
    }

    /**
     * 1 没有订单  2  还未出库  3 已经出库
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("/details")
    @PreAuthorize("isAuthenticated()")
    public OrderDocVo getOrderDetails(@RequestParam("order_no") String orderNo) {
        OrderDocVo orderDocVo = new OrderDocVo();
        orderDocVo.setOrderNo(orderNo);
        orderDocVo.setDetailList(orderService.selectOrderDetails(orderNo));
        return orderDocVo;
    }

}
