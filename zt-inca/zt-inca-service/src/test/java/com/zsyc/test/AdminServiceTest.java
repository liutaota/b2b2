package com.zsyc.test;

import com.zsyc.zt.inca.service.OrderBackService;
import com.zsyc.zt.inca.service.OrderService;
import com.zsyc.zt.inca.vo.OrderBackInfoDocVo;
import com.zsyc.zt.inca.vo.OrderBackInfoDtlVo;
import com.zsyc.zt.inca.vo.OrderInfoDocVo;
import com.zsyc.zt.inca.vo.OrderInfoDtlVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by lcs on 2020/7/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
		Config.class,
		AnnotationConfigContextLoader.class,
})
@Slf4j
public class AdminServiceTest {

	@Autowired
	private OrderService orderService;




	@Test
	public void testGenOrder() {
		OrderInfoDocVo orderInfoDocVo = new OrderInfoDocVo();
		orderInfoDocVo.setOrderNo("10001");
		orderInfoDocVo.setEntryId(1);
		orderInfoDocVo.setCustomId(43192L);

		orderInfoDocVo.setOrderInfoDtlList(new ArrayList<OrderInfoDtlVo>(){{
			OrderInfoDtlVo orderInfoDtlVo1 = new OrderInfoDtlVo();
			orderInfoDtlVo1.setPrice(1.0);
			orderInfoDtlVo1.setAmount(10.0);
			orderInfoDtlVo1.setGoodsId(72794L);
			orderInfoDtlVo1.setStorageId(732);
			orderInfoDtlVo1.setNum(10.0);
			orderInfoDtlVo1.setPriceId(105);
			orderInfoDtlVo1.setOrderNo("10001");
			orderInfoDtlVo1.setOrderDtlId("10001-1");

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
		System.out.println(orderService.genOrder(orderInfoDocVo));;
	}




}




