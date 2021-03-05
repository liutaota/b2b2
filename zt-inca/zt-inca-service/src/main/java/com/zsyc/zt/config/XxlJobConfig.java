package com.zsyc.zt.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.zsyc.framework.util.AssertExt;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2020/8/14.
 */
@Configuration
@ConfigurationProperties("zsyc.xxl-job")
@Data
public class XxlJobConfig {

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


	@Bean
	public XxlJobExecutor xxlJobExecutor() {
		XxlJobExecutor xxlJobExecutor = new XxlJobSpringExecutor();
		AssertExt.notNull(port, "port is null");
		AssertExt.isTrue(port > 0, "port is not ok");
		xxlJobExecutor.setIp(ip);
		xxlJobExecutor.setPort(port);
		xxlJobExecutor.setAppname(appName);
		xxlJobExecutor.setAdminAddresses(adminAddresses);
		xxlJobExecutor.setLogPath(logpath);
		return xxlJobExecutor;
	}
}
