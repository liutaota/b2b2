package com.zsyc.zt.b2b.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2019-04-25.
 */
@Configuration
@ConfigurationProperties("zsyc.wechat")
@Data
public class WechatConfig {

	public final static String WECHAT_API_HOST = "https://api.weixin.qq.com";
	/**
	 *  中天
	 */
	@NestedConfigurationProperty
	private WechatConfigProperties zhongTian;

}
