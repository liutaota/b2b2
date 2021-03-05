package com.zsyc.test;

import com.zsyc.pay.service.PayOrderService;
import com.zsyc.pay.vo.PayOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by lcs on 2020/10/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class PayServiceTest {
    @Autowired
    private PayOrderService<PayOrderVo> payOrderService;

    @Test
    public void testPayOrder(){
        PayOrderVo payOrder = new PayOrderVo();
        payOrder.setDataSource("test");
        payOrder.setOrderNo("zs_" + System.currentTimeMillis());
        payOrder.setBody("test abs");
        payOrder.setTotalFee(100);
        payOrder.setSpbillCreateIp("10.0.8.6");

        this.payOrderService.payOrder(payOrder);
    }
}
