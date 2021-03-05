package com.zsyc.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.entity.OrderGoods;
import com.zsyc.zt.b2b.entity.OrderInfo;
import com.zsyc.zt.b2b.mapper.OrderGoodsMapper;
import com.zsyc.zt.b2b.mapper.OrderInfoMapper;
import com.zsyc.zt.b2b.service.OrderInfoService;
import com.zsyc.zt.b2b.service.RefundReturnService;
import com.zsyc.zt.b2b.vo.CouponVo;
import com.zsyc.zt.b2b.vo.OrderGoodsVo;
import com.zsyc.zt.b2b.vo.OrderInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/18 15:55
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class OrderInfoServiceTest {

    @Reference
    private OrderInfoService orderInfoService;

    @Reference
    private RefundReturnService refundReturnService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Test
    public void test1() {
        System.out.println(1111);

        //this.orderInfoService.reErpOrder(672L);

        this.orderInfoService.minutesToErpOrder();

        //this.orderInfoService.getCartToActivity(934L,0L);
    }

    @Test
    public void updateOrderState() {
        log.info("updateOrderState");
        this.orderInfoService.updateOrderState();

    }

    @Test
    public void sureOrderToDelivery() {
        log.info("sureOrderToDelivery");
        this.orderInfoService.sureOrderToDelivery(763L);

    }

    @Test
    public void dealWithApplyRefundOrder() {
        log.info("dealWithApplyRefundOrder");
        this.refundReturnService.dealWithApplyRefundOrder();

    }

    @Test
    public void getTwoBuyGoodsInfoVo() {
        log.info("getTwoBuyGoodsInfoVo");
        List<OrderGoodsVo> orderGoodsList = this.orderInfoService.getTwoBuyGoodsInfoVo(2650L, 81L,null);

        log.info("{}", orderGoodsList);
    }

    @Test
    public void getByCartCoupon1() {
        log.info("getByCartCoupon1");

        List<CouponVo> couponVos = this.orderInfoService.getByCartCoupon1(81L);

        log.info("{}", couponVos);
    }


    @Test
    public void getCartUseCoupon() {
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        orderInfoVo.setCouponIds("21");
        orderInfoVo.setMemberId(81L);
        orderInfoVo.setId(1L);
        this.orderInfoService.getCartUseCoupon(orderInfoVo);

    }
}
