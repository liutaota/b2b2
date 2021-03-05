package com.zsyc.test;

import com.zsyc.framework.util.EnumScan;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.vo.WebPageInfoVo;
import com.zsyc.zt.b2b.vo.WebPageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
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
		EnumScan.scanAllEnum("com.zsyc", IEnum.class);
	}

	@Test
	public void testGsonNum() {
		WebPageInfoVo.PageSet pageSet = new WebPageInfoVo.PageSet();
		pageSet.setFloorId(Long.valueOf("1230"));
		WebPageVo webPageVo = new WebPageVo();
		webPageVo.setPageSetList(Collections.singletonList(pageSet));
		log.info("{}", webPageVo.getMetaData());

	}

	@Test
	public void testEncode(){

		log.info("{}", UriUtils.encode("asdf东奔西走asdfa   afafaf", "utf-8"));
	}
}
