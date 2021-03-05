package com.zsyc.test;

import com.zsyc.zt.inca.service.OrderBackService;
import com.zsyc.zt.inca.vo.OrderBackInfoDocVo;
import com.zsyc.zt.inca.vo.OrderBackInfoDtlVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/11 18:10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
/*@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})*/
@Slf4j
public class OrderBackServiceTest {
    @Autowired
    private OrderBackService orderBackService;
    /**
     * 生成配送退单
     * @return  liutao
     */
    @Test
    public void test() throws ParseException {
        OrderBackInfoDocVo orderBackInfoDocVo =new OrderBackInfoDocVo();
        long l1 = 284;
        orderBackInfoDocVo.setCustomId(l1);
        orderBackInfoDocVo.setOrderNo("15958080820");
        orderBackInfoDocVo.setOrderInfoDtlList(new ArrayList<OrderBackInfoDtlVo>(){{
            OrderBackInfoDtlVo orderBackInfoDtlVo=new OrderBackInfoDtlVo();
            long l2 =3065;
            orderBackInfoDtlVo.setGoodsId(l2);
            orderBackInfoDtlVo.setNum(1.0);
            orderBackInfoDtlVo.setPrice(12.0);
            orderBackInfoDtlVo.setAmount(13.0);
            orderBackInfoDtlVo.setOrderDtlId("15958080");
            orderBackInfoDtlVo.setLotId((long)296296);
            orderBackInfoDtlVo.setBatchId((long)4406587);
            orderBackInfoDtlVo.setStorageId(1);
            this.add(orderBackInfoDtlVo);
            //	OrderBackInfoDtlVo orderBackInfoDtlVo1=new OrderBackInfoDtlVo();
			/*long l3 =14752;
			orderBackInfoDtlVo1.setGoodsId(l3);
			orderBackInfoDtlVo1.setNum(50.0);
			orderBackInfoDtlVo1.setPrice(12.0);
			orderBackInfoDtlVo1.setAmount(13.0);
			orderBackInfoDtlVo1.setOrderDtlId("159580801111");
			orderBackInfoDtlVo1.setLotId((long)290934);
			orderBackInfoDtlVo1.setBatchId((long)4374946);
			orderBackInfoDtlVo1.setStorageId(732);
			this.add(orderBackInfoDtlVo1);

			OrderBackInfoDtlVo orderBackInfoDtlVo2=new OrderBackInfoDtlVo();
			long l4 = 5690;
			orderBackInfoDtlVo2.setGoodsId(l4);
			orderBackInfoDtlVo2.setNum(2.0);
			orderBackInfoDtlVo2.setPrice(10.0);
			orderBackInfoDtlVo2.setAmount(13.0);
			orderBackInfoDtlVo2.setOrderDtlId("159580808384123");
			orderBackInfoDtlVo2.setLotId((long)266711);
			orderBackInfoDtlVo2.setBatchId((long)3305269);
			orderBackInfoDtlVo2.setStorageId(1);
			this.add(orderBackInfoDtlVo2);*/

			/*OrderBackInfoDtlVo orderBackInfoDtlVo2=new OrderBackInfoDtlVo();
			long l4 =47396;
			orderBackInfoDtlVo2.setGoodsId(l4);
			orderBackInfoDtlVo2.setNum(4.0);
			orderBackInfoDtlVo2.setPrice(10.0);
			orderBackInfoDtlVo2.setAmount(15.0);
			orderBackInfoDtlVo2.setOrderDtlId("159580808384124");
			orderBackInfoDtlVo2.setLotId((long)311195);
			orderBackInfoDtlVo2.setBatchId((long)4564066);
			this.add(orderBackInfoDtlVo2);*/
        }});
        orderBackService.genBackOrderPs(orderBackInfoDocVo);
    }


    /**
     * 生成销售退单
     * @returnt  liutao
     */
    @Test
    public void testgenBackOrderSa() throws ParseException {
        OrderBackInfoDocVo orderBackInfoDocVo =new OrderBackInfoDocVo();
        long l1 = 3051;
        orderBackInfoDocVo.setCustomId(l1);
        orderBackInfoDocVo.setOrderNo("18682416470");
        orderBackInfoDocVo.setApproveManId((long)14163);
        orderBackInfoDocVo.setAmountTotal(28.0);
        orderBackInfoDocVo.setStorerId((long)7368);
        orderBackInfoDocVo.setOrderInfoDtlList(new ArrayList<OrderBackInfoDtlVo>(){{
            OrderBackInfoDtlVo orderBackInfoDtlVo=new OrderBackInfoDtlVo();
            long l2 =1333;
            orderBackInfoDtlVo.setSrcErpOrderDtlId((long)5435818);
            orderBackInfoDtlVo.setSrcErpOrderId((long)730566);
            orderBackInfoDtlVo.setGoodsId(l2);
            orderBackInfoDtlVo.setNum(4.0);
            orderBackInfoDtlVo.setPrice(7.0);
            orderBackInfoDtlVo.setPriceDiscount(12.0);
            orderBackInfoDtlVo.setAmount(28.0);
            orderBackInfoDtlVo.setOrderDtlId("1595808011");
            orderBackInfoDtlVo.setLotId((long)317277);
            orderBackInfoDtlVo.setBatchId((long)4584800);
            orderBackInfoDtlVo.setStorageId(1);
            orderBackInfoDtlVo.setReason(2);
            this.add(orderBackInfoDtlVo);
            OrderBackInfoDtlVo orderBackInfoDtlVo1=new OrderBackInfoDtlVo();
			long l3 =488;
            orderBackInfoDtlVo1.setSrcErpOrderDtlId((long)5435821);
            orderBackInfoDtlVo1.setSrcErpOrderId((long)730566);
			orderBackInfoDtlVo1.setGoodsId(l3);
			orderBackInfoDtlVo1.setNum(50.0);
			orderBackInfoDtlVo1.setPrice(12.0);
            orderBackInfoDtlVo1.setPriceDiscount(12.0);
			orderBackInfoDtlVo1.setAmount(13.0);
			orderBackInfoDtlVo1.setOrderDtlId("159580801111");
			orderBackInfoDtlVo1.setLotId((long)314550);
			orderBackInfoDtlVo1.setBatchId((long)4510141);
			orderBackInfoDtlVo1.setStorageId(732);
            orderBackInfoDtlVo1.setReason(2);
			this.add(orderBackInfoDtlVo1);



            /*OrderBackInfoDtlVo orderBackInfoDtlVo2=new OrderBackInfoDtlVo();
            long l4 =488;
            orderBackInfoDtlVo2.setSrcErpOrderDtlId((long)5435821);
            orderBackInfoDtlVo2.setSrcErpOrderId((long)730566);
            orderBackInfoDtlVo2.setGoodsId(l3);
            orderBackInfoDtlVo2.setNum(50.0);
            orderBackInfoDtlVo2.setPrice(12.0);
            orderBackInfoDtlVo2.setPriceDiscount(12.0);
            orderBackInfoDtlVo2.setAmount(13.0);
            orderBackInfoDtlVo2.setOrderDtlId("159580801111");
            orderBackInfoDtlVo2.setLotId((long)314550);
            orderBackInfoDtlVo2.setBatchId((long)4510141);
            orderBackInfoDtlVo2.setStorageId(5);
            orderBackInfoDtlVo2.setReason(2);
            this.add(orderBackInfoDtlVo2);*/
        }});
        orderBackService.genBackOrderSa(orderBackInfoDocVo);
    }

}
