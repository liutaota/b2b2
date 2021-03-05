package com.zsyc.test;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.zsyc.framework.util.EnumScan;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.SocketUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Created by lcs on 2019-01-02.
 */
@Slf4j
public class TT {

	@Test
	public void testChart(){
		Assert.isTrue(Pattern.matches("^[\\d\\w\\-]{2,4}$", "18996"));
	}

	@Test
	public void testRandomStringUtils(){
		System.out.println("--------------------------");
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.random(10, false, true));
		System.out.println(RandomStringUtils.random(10, true, false));
		System.out.println(RandomStringUtils.random(10, true, true));

		System.out.println("--------------------------");
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.random(10, false, true));
		System.out.println(RandomStringUtils.random(10, true, false));
		System.out.println(RandomStringUtils.random(10, true, true));

		System.out.println("--------------------------");
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.random(10, false, true));
		System.out.println(RandomStringUtils.random(10, true, false));
		System.out.println(RandomStringUtils.random(10, true, true));
	}

	@Test
	public void testLocalDate(){
		System.out.println(LocalDateTime.now());
	}

	@Test
	public void testEnum() throws IOException {
	}

	@Test
	public void testDirectory(){
		File file = new File("Z://");
		for (String s : file.list()) {
			System.out.println(s);
		}
	}

}
