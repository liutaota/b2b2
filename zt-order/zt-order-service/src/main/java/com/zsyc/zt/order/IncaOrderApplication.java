package com.zsyc.zt.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 */
@SpringBootApplication
@EnableAsync
@EnableDubbo
public class IncaOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncaOrderApplication.class, args);
    }

}
