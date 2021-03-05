package com.zsyc.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

/**
 * Created by lcs on 2019-01-02.
 */
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
}
