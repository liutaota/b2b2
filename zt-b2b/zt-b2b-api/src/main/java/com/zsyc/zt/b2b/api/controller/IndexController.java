package com.zsyc.zt.b2b.api.controller;


import com.zsyc.framework.util.EnumScan;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by lcs on 2019-01-13.
 */
@Api
@RestController
@Slf4j
public class IndexController {

	@Value("${spring.application.name}")
	private String app;
	private static Properties gitInfo = new Properties();

	@GetMapping({"index", "", "/"})
	public Object index(){
		gitInfo.setProperty("APP", app);
		return gitInfo;
//		return String.format("%s IS RUNNING", app).toUpperCase();
	}

    @ApiOperation("获取系统所有枚举数据")
    @GetMapping({"api/enumData"})
    public Object enumData() throws IOException {
        return EnumScan.scanAllEnum("com.zsyc.zt.b2b", IEnum.class);
    }

    static {
		String profile = "classpath:git.properties";
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(profile);
			propertiesFactoryBean.setLocations(resources);
			propertiesFactoryBean.setSingleton(false);
			gitInfo = propertiesFactoryBean.getObject();
		} catch (IOException e) {
			log.error("git.properties", e);
		}
	}
}