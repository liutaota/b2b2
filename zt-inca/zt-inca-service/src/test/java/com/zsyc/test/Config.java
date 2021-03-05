package com.zsyc.test;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by lcs on 2018-12-30.
 */
@ComponentScan({"com.zsyc","com.alicp"})
@EnableMethodCache(basePackages = "com.zsyc")
public class Config {
}
