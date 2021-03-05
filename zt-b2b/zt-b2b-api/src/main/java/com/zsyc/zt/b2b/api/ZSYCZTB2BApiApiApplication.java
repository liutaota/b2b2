package com.zsyc.zt.b2b.api;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * Created by lcs on 2019-01-09.
 */
@SpringBootApplication(scanBasePackages = {"com.zsyc"})
@EnableMethodCache(basePackages = "com.zsyc")
public class ZSYCZTB2BApiApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZSYCZTB2BApiApiApplication.class, args);
    }
    public final static String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
//        properties.setProperty("kaptcha.border", "yes");
//        properties.setProperty("kaptcha.border.color", "105,179,90");
//        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "110");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
//        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
//        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
