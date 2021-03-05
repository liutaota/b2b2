package com.zsyc.zt.fs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2020/7/25.
 */
@Configuration
@ConfigurationProperties("zsyc.aliyun.oss")
@Data
public class AliyunOssProperties {

	/**
	 * accessKeyId
	 */
	private String accessKeyId = "LTAI4GJ12GtvfzZyLjKpx2yN";

	/**
	 * accessKeySecret
	 */
	private String accessKeySecret = "S5B2hcXni2qjRzahvdwEVcZjdPrMem";

	/**
	 * endpoint
	 * oss-cn-shenzhen.aliyuncs.com
	 */
	private String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";

	/**
	 * bucketName
	 * gdztyy-b2b.oss-cn-shenzhen.aliyuncs.com
	 */
	private String bucketName = "gdztyy-b2b";

}
