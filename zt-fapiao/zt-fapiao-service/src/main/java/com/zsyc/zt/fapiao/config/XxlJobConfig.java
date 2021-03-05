package com.zsyc.zt.fapiao.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.zsyc.framework.util.AssertExt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2020/8/14.
 */
@Configuration
@ConfigurationProperties("zsyc.xxl-job")
@Data
@Slf4j
public class XxlJobConfig {

	/**
	 * enable 是否启用
	 */
	private Boolean enable;

	/**
	 * adminAddresses
	 */
	private String adminAddresses;

	/**
	 * appname
	 */
	private String appName;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * port
	 */
	private Integer port;

	/**
	 * logpath
	 */
	private String logpath;

	private String accessToken;


	@Bean
	public XxlJobExecutor xxlJobExecutor() {
		log.info("XxlJobExecutor enable : {}", this.enable);
		if(!this.enable) return null;

		XxlJobExecutor xxlJobExecutor = new XxlJobSpringExecutor();
		AssertExt.notNull(port, "port is null");
		AssertExt.isTrue(port > 0, "port is not ok");
		xxlJobExecutor.setIp(ip);
		xxlJobExecutor.setPort(port);
		xxlJobExecutor.setAppname(appName);
		xxlJobExecutor.setAdminAddresses(adminAddresses);
		xxlJobExecutor.setLogPath(logpath);
		/**
		 * 加入accessToken
		 */
		xxlJobExecutor.setAccessToken(accessToken);
		return xxlJobExecutor;
	}
}
