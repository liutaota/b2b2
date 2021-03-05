package com.zsyc.test;

import com.zsyc.zt.inca.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/11 18:10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
/*@Configuration
@EnableAutoConfiguration*/
/*@ComponentScan(basePackages = {"com.alicp.jetcache", "com.alicp.jetcache.anno","com"})
@EnableMethodCache(basePackages = {"com", "com.alicp.jetcache.anno.inittestbeans"})*/
@Slf4j
@ActiveProfiles("test")
public class GoodsServiceTest {

    @Resource
    GoodsService goodsService;



    @Test
    public void teststatus(){


        System.out.println(goodsService.getDetail(94513L, 1 ));
        System.out.println(goodsService.validBusinessScope(94513L, 1,new ArrayList<Long>(){{
            this.add(4229L);
        }}));

    }

    @Test
    public void testScope(){


        System.out.println(goodsService.getDetail(94513L, 1 ));
        System.out.println(goodsService.validBusinessScope(102L, 1,new ArrayList<Long>(){{
            this.add(100663L);
        }}));

    }
}
