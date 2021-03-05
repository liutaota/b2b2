package com.zsyc.zt.report;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by lcs on 2019-01-04.
 */
@SpringBootApplication(scanBasePackages = "com.zsyc")
@EnableDubbo
@EnableTransactionManagement
@EnableMethodCache(basePackages = "com.zsyc")
@EnableScheduling
@EnableAsync
public class ZSYCZTReportServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZSYCZTReportServicesApplication.class, args);
    }
}