package com.zsyc.framework.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class LongConflate {
    /**
     * 自定义进制(0,1没有加入,容易与o,l混淆)，数组顺序可进行调整增加反推难度，A用来补位因此此数组不包含A，共31个字符。
     */
    private static final char[] BASE = new char[]{'H', 'V', 'E', '8', 'S', '2', 'D', 'Z', 'X', '9', 'C', '7', 'P', '5', 'I', 'K', '3', 'M', 'J', 'U', 'F', 'R', '4', 'W', 'Y', 'L', 'T', 'N', '6', 'B', 'G', 'Q'};
    /**
     * A补位字符，不能与自定义重复
     */
    private static final char SUFFIX_CHAR = 'A';
    /**
     * 进制长度
     */
    private static final int BIN_LEN = BASE.length;
    /**
     * 生成邀请链接最小长度
     */
    private static final int CODE_LEN = 6;

    /**
     * ID转换为邀请连接
     *
     * @param id
     * @return
     */
    public static String encode(Long id) {
        char[] buf = new char[BIN_LEN];
        int charPos = BIN_LEN;
        // 当id除以数组长度结果大于0，则进行取模操作，并以取模的值作为数组的坐标获得对应的字符
        while (id / BIN_LEN > 0) {
            int index = (int) (id % BIN_LEN);
            buf[--charPos] = BASE[index];
            id /= BIN_LEN;
        }
        buf[--charPos] = BASE[(int) (id % BIN_LEN)];
        // 将字符数组转化为字符串
        String result = new String(buf, charPos, BIN_LEN - charPos);
        // 长度不足指定长度则随机补全
        int len = result.length();
        if (len < CODE_LEN) {
            StringBuilder sb = new StringBuilder();
            sb.append(SUFFIX_CHAR);
            Random random = new Random();
            // 去除SUFFIX_CHAR本身占位之后需要补齐的位数
            for (int i = 0; i < CODE_LEN - len - 1; i++) {
                sb.append(BASE[random.nextInt(BIN_LEN)]);
            }
            result += sb.toString();
        }

		result = formatCode(result);
		return result;
    }

    /**
     * 邀请链接解析出ID
     * 基本操作思路恰好与idToCode反向操作。
     *
     * @param code
     * @return
     */
    public static Long decode(String code) {
		if (code.length() > 6) {
			code = checkCode(code);
		}
        char[] charArray = code.toCharArray();
        long result = 0L;
        for (int i = 0; i < charArray.length; i++) {
            int index = 0;
            for (int j = 0; j < BIN_LEN; j++) {
                if (charArray[i] == BASE[j]) {
                    index = j;
                    break;
                }
            }
            if (charArray[i] == SUFFIX_CHAR) {
                break;
            }
            if (i > 0) {
                result = result * BIN_LEN + index;
            } else {
                result = index;
            }
        }
        return result;
    }

    private static String checkCode(String code ){
    	String s = code.substring(2);
		AssertExt.isTrue(DigestUtils.md2Hex(s).toUpperCase().startsWith(code.substring(0, 2)), "非法code");
		return s;
	}
	private static String formatCode(String code ){
		return String.format("%s%s", DigestUtils.md2Hex(code).substring(0, 2).toUpperCase(), code);
	}

}
