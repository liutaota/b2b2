package com.zsyc.test;

import com.zsyc.zt.fs.service.AliyunFileService;
import com.zsyc.zt.fs.service.MinioFileService;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
public class MinioFileServiceTest {
	@Autowired
	private MinioFileService minioFileService;

	@Test
	public void testUpload() throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
//		this.b2bFileService.write("test/shit/asdf",new ByteArrayInputStream(new byte[]{1}));
		System.out.println(this.minioFileService.write("test/shit/asdf",new ByteArrayInputStream(new byte[110])));;
		this.minioFileService.write("test/shit/asdf1",new ByteArrayInputStream(new byte[110]));
//		log.info("{}", this.b2bFileService.read("test/shit/asdf"));
	}

	@Test
	public void test() throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
//		this.b2bFileService.write("test/shit/asdf",new ByteArrayInputStream(new byte[]{1}));
		this.minioFileService.exist("test/shit2/12e");
//		log.info("{}", this.b2bFileService.read("test/shit/asdf"));
	}
}
