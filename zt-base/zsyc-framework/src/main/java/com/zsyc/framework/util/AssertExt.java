package com.zsyc.framework.util;

import com.zsyc.framework.base.ZSYCRuntimeException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Created by lcs on 2018-12-29.
 */
public class AssertExt {

	/**
	 * @param constants
	 * @param constant
	 * @param message
	 * @param args
	 * @param <T>
	 */
	public static <T> void hasConstant(T[] constants, T constant, String message, Object... args) {
		isTrue(ArrayUtils.contains(constants, constant), message, args);
	}

	/**
	 * 是否为有效ID
	 *
	 * @param id
	 * @param message
	 */
	public static void hasId(Long id, String message) {
		isTrue(id != null && id > 0, message);
	}

	/**
	 * 判断值是否为false
	 *
	 * @param expression
	 * @param message
	 */
	public static void isFalse(boolean expression, String message, Object... args) {
		isTrue(!expression, message, args);
	}

	/**
	 * 判断值是否为true
	 *
	 * @param expression
	 * @param message
	 * @param args
	 */
	public static void isTrue(boolean expression, String message, Object... args) {
		if (!expression) {
			throw new ZSYCRuntimeException(String.format(message, args));
		}
	}

	/**
	 * 判断是否有ID
	 *
	 * @param id
	 * @param message
	 * @param args
	 */
	public static void hasId(Long id, String message, Object... args) {
		isTrue(id != null && id > 0, message, args);
	}

	/**
	 * 非null
	 *
	 * @param object
	 * @param message
	 * @param args
	 */
	public static void notNull(Object object, String message, Object... args) {
		isTrue(object != null, message, args);
	}

	/**
	 * collection not empty
	 *
	 * @param collection
	 * @param message
	 * @param args
	 */
	public static void notEmpty(Collection<?> collection, String message, Object... args) {
		isTrue(!CollectionUtils.isEmpty(collection), message, args);
	}

	/**
	 * array not empty
	 *
	 * @param array
	 * @param message
	 * @param args
	 */
	public static void notEmpty(Object[] array, String message, Object... args) {
		isTrue(!ArrayUtils.isEmpty(array), message, args);
	}

	/**
	 * string not blank
	 *
	 * @param str
	 * @param message
	 * @param args
	 */
	public static void notBlank(String str, String message, Object... args) {
		isTrue(!StringUtils.isEmpty(str), message, args);
	}

	/**
	 * 判断是否为有效的枚举类型
	 *
	 * @param EnumClass
	 * @param name
	 * @param message
	 * @param args
	 */
	public static void checkEnum(Class<? extends Enum> EnumClass, String name, String message, Object... args) {
		try {
			Enum.valueOf(EnumClass, name);
		} catch (Exception e) {
			AssertExt.isTrue(false, message, args);
		}
	}

	/**
	 * 正则判断
	 *
	 * @param regex   *         The expression to be compiled
	 * @param content
	 * @param message
	 * @param args
	 */
	public static void matches(String regex, CharSequence content, String message, Object... args) {
		AssertExt.isTrue(Pattern.matches(regex, content), message, args);
	}

	/**
	 * 直接触发 exception
	 * @param message
	 * @param args
	 */
	public static void fail(String message, Object... args) {
		AssertExt.isTrue(false, message, args);
	}

}
