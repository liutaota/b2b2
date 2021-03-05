package com.zsyc.test;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.zsyc.zt.inca.service.OrderService;
import com.zsyc.zt.inca.vo.OrderInfoDocVo;
import com.zsyc.zt.inca.vo.OrderInfoDtlVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/11 18:10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan({"com.zsyc","com.alicp.jetcache.autoconfigure"})
@EnableMethodCache(basePackages = "com.zsyc")
@SpringBootConfiguration
/*@Configuration
@EnableAutoConfiguration*/
/*@ComponentScan(basePackages = {"com.alicp.jetcache", "com.alicp.jetcache.anno","com"})
@EnableMethodCache(basePackages = {"com", "com.alicp.jetcache.anno.inittestbeans"})*/
public class OrderServiceTest {

    @Resource
    OrderService orderService;

    /**
     * 订单收货
     */
    @Test
    public void receiptOrder(){
        OrderInfoDocVo orderInfoDocVo = new OrderInfoDocVo();
        orderInfoDocVo.setOrderNo("ZT_493560624589045760");
        orderInfoDocVo.setOrderId(584L);
        orderInfoDocVo.setOrderType("COMMON");
        orderInfoDocVo.setEntryId(3);//独立单元
        orderInfoDocVo.setCustomId(240L);//客户id
        orderInfoDocVo.setReceiptManId(21618L);//收货人id
        orderInfoDocVo.setCreateDate(LocalDateTime.now());
        orderInfoDocVo.setAmountTotal(122.34);
        orderInfoDocVo.setAmountPay(122.34);
        orderInfoDocVo.setAmountDiscount(0.0);

        orderInfoDocVo.setOrderInfoDtlList(new ArrayList<OrderInfoDtlVo>(){{
            OrderInfoDtlVo orderInfoDtlVo1 = new OrderInfoDtlVo();
            orderInfoDtlVo1.setPrice(1.0);

            orderInfoDtlVo1.setPriceDiscount(1.0);

            orderInfoDtlVo1.setAmount(10.0);
            orderInfoDtlVo1.setGoodsId(3027L);
            orderInfoDtlVo1.setStorageId(1);
            orderInfoDtlVo1.setNum(1.0);
            orderInfoDtlVo1.setPriceId(105);
            orderInfoDtlVo1.setOrderNo("ZT_493103174425509888");
            orderInfoDtlVo1.setOrderId(584L);
            orderInfoDtlVo1.setOrderDtlId("3l");
            orderInfoDtlVo1.setSrcErpOrderId(276057l);
            orderInfoDtlVo1.setSrcErpOrderDtlId(2239811l);

            this.add(orderInfoDtlVo1);
        }
        });
        System.out.println(orderService.receiptOrder(orderInfoDocVo));
    }

    @Test
    public void testGenOrder() {
        OrderInfoDocVo orderInfoDocVo = new OrderInfoDocVo();
        orderInfoDocVo.setOrderNo("ZT_494502057663791101");
        orderInfoDocVo.setOrderId(683L);
        orderInfoDocVo.setOrderType("COMMON");
        orderInfoDocVo.setEntryId(1);
        orderInfoDocVo.setCustomId(240L);
        orderInfoDocVo.setEntryId(1);
        orderInfoDocVo.setCreateDate(LocalDateTime.now());
        orderInfoDocVo.setAmountTotal(122.34);
        orderInfoDocVo.setAmountPay(122.34);
        orderInfoDocVo.setAmountDiscount(0.0);

        orderInfoDocVo.setOrderInfoDtlList(new ArrayList<OrderInfoDtlVo>(){{
            OrderInfoDtlVo orderInfoDtlVo1 = new OrderInfoDtlVo();
            orderInfoDtlVo1.setPrice(1.0);

            orderInfoDtlVo1.setPriceDiscount(1.0);

            orderInfoDtlVo1.setAmount(10.0);
            orderInfoDtlVo1.setGoodsId(752L);
            orderInfoDtlVo1.setStorageId(1);
            orderInfoDtlVo1.setNum(2.0);
            orderInfoDtlVo1.setPriceId(105);
            orderInfoDtlVo1.setOrderNo("ZT_493103174425509888");
            orderInfoDtlVo1.setOrderId(584L);
            orderInfoDtlVo1.setOrderDtlId("3l");

			/*OrderInfoDtlVo orderInfoDtlVo2 = new OrderInfoDtlVo();
			orderInfoDtlVo2.setPrice(1.0);
			orderInfoDtlVo2.setAmount(10.0);
			orderInfoDtlVo2.setGoodsId(34283L);
			orderInfoDtlVo2.setNum(10.0);
			orderInfoDtlVo2.setOrderId("10001");
			orderInfoDtlVo2.setOrderDtlId("10001-2");

			OrderInfoDtlVo orderInfoDtlVo3 = new OrderInfoDtlVo();
			orderInfoDtlVo3.setPrice(1.0);
			orderInfoDtlVo3.setAmount(10.0);
			orderInfoDtlVo3.setGoodsId(4229L);
			orderInfoDtlVo3.setNum(10.0);
			orderInfoDtlVo3.setOrderId("10001");
			orderInfoDtlVo3.setOrderDtlId("10001-3");

			OrderInfoDtlVo orderInfoDtlVo4 = new OrderInfoDtlVo();
			orderInfoDtlVo4.setPrice(1.0);
			orderInfoDtlVo4.setAmount(10.0);
			orderInfoDtlVo4.setGoodsId(628L);
			orderInfoDtlVo4.setNum(10.0);
			orderInfoDtlVo4.setOrderId("10001");
			orderInfoDtlVo4.setOrderDtlId("10001-4");*/
            this.add(orderInfoDtlVo1);
			/*this.add(orderInfoDtlVo2);
			this.add(orderInfoDtlVo3);
			this.add(orderInfoDtlVo4);*/
        }
        });
        System.out.println(orderService.genOrder(orderInfoDocVo));
    }

    @Test
    public void testConfirmGiftOrder(){
        orderService.confirmGiftOrder("1597203975805374",1,2,1,new ArrayList<Integer>(){{
            this.add(11);
        }});

    }

    @Test
    public void testConfirmSaOrder(){
        orderService.confirmSaOrder("ZT_492761642421977088",1,2,1,new ArrayList<Integer>(){{
            this.add(11);
        }});

    }

    @Test
    public void testConfirmPsOrder(){
        orderService.confirmPsOrder("ZT_494468824238653440",1,1,2,new ArrayList<Integer>(){{
            this.add(11);
        }});

    }
    @Test
    public void testOrder(){
        DecimalFormat decimalFormat=new DecimalFormat("#.000000");
        Double d1=0.0002;
        Double d2=2.0;
       // System.out.print( decimalFormat.format(d1-d2));
        if(d2.compareTo(0.0)>0){
            System.out.print("ture");
        }
        System.out.print("false");
    }

    @Test
    public void teststatus(){
        System.out.println(orderService.selectOrderStatusList(new ArrayList<Long>(){
            {
                this.add(41595L);
                this.add(3066L);
            }
        }));
    }

    @Test
    public void test5(){
        System.out.println(orderService.selectOrderStatusList(new ArrayList<Long>() {
            {
                this.add(288L);
                this.add(250L);
                this.add(109L);
            }
        }));

    }
}
