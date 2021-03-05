package com.zsyc.webapp.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcs on 2019-01-13.
 */
@ConfigurationProperties(prefix = "zsyc.oauth2")
@ConditionalOnClass(BaseClientDetails.class)
@Component
@Data
public class OauthClientProperties {

	/**
	 * 所有客户端配置
	 */
	private final Map<String, BaseClientDetails> clients = new HashMap<>();

	/**
	 * 存储 token 前缀
	 */
	private String storeRedisKey = "spring_oauth:";

	/**
	 * 请求
 	 */
	private Request request;

	@Data
	public static class Request {
		/**
		 * 请求白名单
		 */
		private String whiteList;
		/**
		 * 跨域
		 */
		private RequestOrs cors;
	}

	@Data
	public static class RequestOrs{
		/**
		 * 请求来源
		 */
		private String origin;
		/**
		 * 请求头
		 */
		private String headers;
	}
}
