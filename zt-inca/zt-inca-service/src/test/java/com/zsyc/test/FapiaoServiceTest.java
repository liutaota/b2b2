package com.zsyc.test;



import com.zsyc.zt.inca.service.FapiaoService;
import com.zsyc.zt.inca.vo.FapiaoOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class FapiaoServiceTest {

    @Autowired
    public FapiaoService fapiaoService;

    @Test
    public void test1(){
        FapiaoOrderVo rs = fapiaoService.getKpInfo(112L);
    }


    @Test
    public void test2(){
        List<Long> data = new ArrayList<>();
        data.add(112L);
        data.add(113L);
        data.add(114L);
        fapiaoService.getKpInfo(data);
    }

    @Test
    public void test3(){
        System.out.println(fapiaoService.getKpAddressInfo(112L));;
    }




    @Test
    public void test4(){
        List<Long> data = new ArrayList<>();
        data.add(112L);
        data.add(113L);
        data.add(114L);
        fapiaoService.getKpAddressInfo(data);
    }






}
