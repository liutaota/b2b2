package com.zsyc.zt.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redisson")
@Setter
@Slf4j
public class
RedissonConfig {

    private String address;
    private String password;
    private Integer database;

    @Bean
    public RedissonClient getRedisson() {
        log.info("初始化分布式锁");
        Config config = new Config();
        //单机模式  依次设置redis地址和密码
        config.useSingleServer().
                setAddress(address).
                setDatabase(database)
                .setPassword(password);
        return Redisson.create(config);
    }
}
