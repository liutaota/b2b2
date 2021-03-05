package com.zsyc.zt.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.gdztyy")
public class IncaxWxApplication {
    public static void main(String[] args) {

        SpringApplication.run(IncaxWxApplication.class, args);

    }
}
