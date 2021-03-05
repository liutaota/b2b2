package com.zsyc.zt.b2b.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * dubbo自定义版本配置
 * Created by lcs on 2020/8/11.
 */
@Configuration
@ConfigurationProperties("zsyc.dubbo")
@Data
public class DubboCustomerVersionConfig {

	/**
	 * dubbo自定义配置
	 */
	private final Map<String, DubboVersion> configs = new HashMap<>();

	@Data
	public static class DubboVersion{
		/**
		 * dubbo版本
		 */
		private String version;
	}
}
