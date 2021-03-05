package com.zsyc.zt.b2b.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2020-03-21.
 */
@Configuration
@ConfigurationProperties("zsyc.aliyun.sms")
@Data
public class AliyunSmsProperties {

	/**
	 * accessKeyId
	 */
	private String accessKeyId = "LTAI4GJ12GtvfzZyLjKpx2yN";

	/**
	 * accessKeySecret
	 */
	private String accessKeySecret = "S5B2hcXni2qjRzahvdwEVcZjdPrMem";

	/**
	 * templateCode
	 */
	private String templateCode = "SMS_176555154";

	/**
	 * signName
	 * 可选
	 * 1. 广东中天药业
	 * 2. 广东中天医药
	 */
	private SmsSignName signName = SmsSignName.广东中天医药;

	public enum SmsSignName {
		广东中天药业, 广东中天医药,
	}

}
