package com.zsyc.test;

import com.zsyc.framework.util.LongConflate;
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

	@Test
	public void testCode() {
		System.out.println(LongConflate.encode(123L));
		System.out.println(LongConflate.encode(1456789023232323232L));
		System.out.println(LongConflate.decode(LongConflate.encode(1345678902L)));
		System.out.println(LongConflate.decode("54VX5656WLZDNRH"));
		System.out.println(LongConflate.decode("E4VX5656WLZDNRHAL"));
	}

}
