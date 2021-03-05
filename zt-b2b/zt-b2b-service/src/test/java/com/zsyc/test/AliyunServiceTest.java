package com.zsyc.test;

import com.zsyc.zt.b2b.service.AliyunService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by lcs on 2020/7/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
		Config.class,
		AnnotationConfigContextLoader.class,
})
@Slf4j
public class AliyunServiceTest {
	@Autowired
	private AliyunService aliyunService;

	@Test
	public void testSendSms() {
		log.info("{}", this.aliyunService.sendSms("13434848804", "10086"));
	}

	@Test
	public void testSendSms2() {
		// mvn -am -pl zt-b2b/zt-b2b-service test -Dtest=com.zsyc.test.AliyunServiceTest#testSendSms2 -DfailIfNoTests=false -Dzsyc.aliyun.sms.signName=广东中天药业
		log.info("{}", this.aliyunService.sendSms("13434848804", "10085"));
	}
}
