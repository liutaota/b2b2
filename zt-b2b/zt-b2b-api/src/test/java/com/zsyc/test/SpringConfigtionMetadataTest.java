package com.zsyc.test;

import com.zsyc.framework.util.SpringConfigureMetadataToDocs;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by lcs on 2020/8/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
		Config.class,
		AnnotationConfigContextLoader.class,
})
@Slf4j
public class SpringConfigtionMetadataTest {

	@Test
	public void testRead() throws IOException {
		log.info("\n{}", SpringConfigureMetadataToDocs.toMD(Arrays.asList("zsyc.oauth2.clients")));
	}
}
