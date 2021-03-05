package com.zsyc.test;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esotericsoftware.minlog.Log;
import com.zsyc.zt.b2b.service.RefundReturnService;
import com.zsyc.zt.b2b.vo.RefundReturnVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;

/**
 * Created by lcs on 2020/8/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class AdminOrderTest {

    @Autowired
    private RefundReturnService refundReturnService;

    @Test
    public void getRefundReturnList() {
        RefundReturnVo refundReturnVo = new RefundReturnVo();
        this.refundReturnService.getRefundReturnList(new Page(1, 10), refundReturnVo);
    }


    @Test
    public void test() {
        Long price = 30456000L;
        log.info("123---{}", (double) price / 10000);

        double d = new BigDecimal((double) price / 100).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();

        log.info("d---{}", d);
        int i = (int) (d * 100);
        log.info("i----{}", i);

        long il = i / 100;
        log.info("il------{}", il);


        Long price1 = 6899L;
        log.info("123---{}", (double) price1 / 10000);

        double d1 = new BigDecimal((double) price1 / 100).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();

        log.info("d1---{}", d1);
        int i1 = (int) (d1 * 10000);
        log.info("i1----{}", i1);

        long il1 = i1 / 100;
        log.info("il1------{}", il1);


        Long price2 = 2250L;
        log.info("price2---{}", (double) price2 / 10000);

        double d2 = new BigDecimal((double) price2 / 100).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();

        log.info("d2---{}", d2);
        int i2 = (int) (d2 * 10000);
        log.info("i2----{}", i2);

        long il2 = i2 / 100;
        log.info("il2------{}", il2);
    }

    @Test
    public void test1() {
        Long price = 10L;
        if(price.toString().length()>2){
            Long newPrice1= Long.valueOf(price.toString().substring(0, price.toString().length() - 2));
            log.info("newPrice1---{}", newPrice1);
        }

        Long a = price % 100;
        log.info("a{}", a);
        if (a > 5) {
            int ai = (int) (price / 100);
            log.info("a111--{}", ai + 1);

        }

        Long price1 = 6899L;
        Long a1 = price1 % 100;
        log.info("a1{}", a1);

        if (a1 > 5) {
            int ai = (int) (price1 / 100);
            log.info("a1111--{}", ai + 1);
        }

        Long price2 = 2250L;

        Long a2 = price2 % 100;
        log.info("a2{}", a2);


        Long newPrice= Long.valueOf(price2.toString().substring(0, price2.toString().length() - 2));
        log.info("newPrice---{}", newPrice);

        if (a1 > 5) {
            int ai = (int) (price2 / 100);

            log.info("newPrice1111---{}", newPrice+1);
            log.info("a11111--{}", ai + 1);
        }
    }

}
