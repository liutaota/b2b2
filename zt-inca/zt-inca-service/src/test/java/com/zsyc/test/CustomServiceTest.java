package com.zsyc.test;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.zsyc.zt.inca.service.CustomService;
import com.zsyc.zt.inca.service.OrderService;
import com.zsyc.zt.inca.vo.OrderInfoDocVo;
import com.zsyc.zt.inca.vo.OrderInfoDtlVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

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
/*@SpringBootConfiguration
@ComponentScan({"com.zsyc","com.alicp.jetcache.autoconfigure"})
@EnableMethodCache(basePackages = "com.zsyc")*/
/*@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})*/
@Slf4j
@ActiveProfiles("prod")
public class CustomServiceTest {


    @Resource
    CustomService customService;



    @Test
    public void test1() {
   /*     System.out.println(customService.getCreditDay(240L, 1));
        customService.getById(2086L);
        customService.getById(2086L);
        customService.getById(2086L);*/
        System.out.println(customService.selectCanNotBuy(2086L,1));;

    }

    @Test
    public void test2() {
        System.out.println(customService.selectBusinessScopeByCustomId(2086L));;

    }

    @Test
    public void test3() {
        System.out.println(customService.selectCustomLicenseByCustomId(2086L));;

    }

    @Test
    public void test4() {
        System.out.println(customService.selectLicenseType(1));;

    }

    @Test
    public void test9(){
        System.out.println(customService.valid(14294L,1));
    }


    @Test
    public void test7(){
        System.out.println(customService.selectBusinessScopeByCustomIdAndLicenseId(14294L,1L));
    }

    @Test
    public void test5() {

        System.out.println("---------------");
        System.out.println(customService.selectCanNotBuy(44092L, 1));
        System.out.println("---------------");
        System.out.println(customService.selectCanBuy(44092L, 1));
/*
        System.out.println(customService.getByCustomId(284L,1));;*/

    }

    @Test
    public void test6() {

        System.out.println(customService.validEmployee(11232136L));


    }

}
