package com.zsyc.zt.b2b.api.controller;

import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.service.PayOrderService;
import com.zsyc.pay.vo.PayOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lcs on 2020/11/12.
 */
@RestController
@RequestMapping("pay/abc")
@Slf4j
public class AbcPayController {
    @Reference
    private PayOrderService payOrderService;

    @RequestMapping("pay-notify")
    public String payNotify(@RequestParam("MSG") String msg) {
        log.info("pay notify data {}", msg);
        this.payOrderService.payCallback(msg);
        return "success";
    }

    @RequestMapping("refund-notify")
    public String refundNotify(@RequestBody String xmlData) {
        log.info("refund notify data {}", xmlData);
        this.payOrderService.refundCallback(xmlData);
        return "success";
    }

    @RequestMapping("test")
    public PayOrder test() {
        PayOrderVo payOrder = new PayOrderVo();
        payOrder.setDataSource("test");
        payOrder.setOrderNo("zs_" + System.currentTimeMillis());
        payOrder.setBody("test abs");
        payOrder.setTotalFee(1);
        payOrder.setSpbillCreateIp("10.0.8.6");
        payOrder.setDataSource("1");
        return this.payOrderService.payOrder(payOrder);
    }
}
