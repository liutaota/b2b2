package com.zsyc.test;

import com.zsyc.zt.fs.service.AliyunFileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.ByteArrayInputStream;

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
public class AliyunFileServiceTest {
	@Autowired
	private AliyunFileService aliyunFileService;

	@Test
	public void testUpload() {
//		this.b2bFileService.write("test/shit/asdf",new ByteArrayInputStream(new byte[]{1}));
		this.aliyunFileService.write("1",new ByteArrayInputStream(new byte[10]));
		this.aliyunFileService.write("test/shit",new ByteArrayInputStream(new byte[100]));
//		log.info("{}", this.b2bFileService.read("test/shit/asdf"));
	}

	@Test
	public void test() {
//		this.b2bFileService.write("test/shit/asdf",new ByteArrayInputStream(new byte[]{1}));
		this.aliyunFileService.exist("test/shit2/12e");
//		log.info("{}", this.b2bFileService.read("test/shit/asdf"));
	}
}
