package com.zsyc.zt.b2b.service;

import com.zsyc.framework.util.AliyunHttp;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.config.AliyunSmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcs on 2020/7/25.
 */
@Service
@Transactional
@Slf4j
public class AliyunService {
	@Autowired
	private AliyunSmsProperties aliyunSmsProperties;

	private AliyunHttp aliyunSmsHttp;

	@Lazy
	@PostConstruct
	public void initPublicParams() {
		this.aliyunSmsHttp = new AliyunHttp();
		this.aliyunSmsHttp.setPublicParams(new AliyunHttp.PublicParams()
				.setHost("https://dysmsapi.aliyuncs.com")
				.setVersion("2017-05-25")
				.setAccessKeyId(this.aliyunSmsProperties.getAccessKeyId())
				.setAccessKeySecret(this.aliyunSmsProperties.getAccessKeySecret()));
	}

	/**
	 * 阿里云短信验证码发送
	 * @param telephone
	 * @param code
	 * @return
	 */
	public Map sendSms(String telephone, String code) {
		AssertExt.notBlank(telephone, "无效telephone");
		AssertExt.notBlank(code, "无效code");
		Map<String, String> params = new HashMap<>();
		params.put("PhoneNumbers", telephone);
		params.put("SignName", this.aliyunSmsProperties.getSignName().name());
		params.put("TemplateCode", this.aliyunSmsProperties.getTemplateCode());
		params.put("Action", "SendSms");
		params.put("TemplateParam", String.format("{\"code\":\"%s\"}", code));
		return this.aliyunSmsHttp.get(params);
	}
}

