package com.zsyc.zt.b2b.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

/**
 * AES加密解密字符串工具类
 * 概述：AES高级加密标准，是对称密钥加密中最流行的算法之一；
 *       工作模式包括：ECB、CBC、CTR、OFB、CFB；
 * 使用范围：该工具类仅支持CBC模式下的：
 *              填充：PKCS7PADDING
 *              数据块：128位
 *              密码（key）：32字节长度（例如：12345678901234567890123456789012）
 *              偏移量（iv）：16字节长度（例如：1234567890123456）
 *              输出：hex
 *              字符集：UTF-8
 * 使用方式：String encrypt = AESCBCUtil.encrypt("wy");
 *           String decrypt = AESCBCUtil.decrypt(encrypt);
 * 验证方式：http://tool.chacuo.net/cryptaes（在线AES加密解密）
 */
@Slf4j
public class AESCBCUtil {
        static {
            //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
            Security.addProvider(new BouncyCastleProvider());
        }

        /**
         * AES解密
         *
         * @param data           //密文，被加密的数据
         * @param key            //秘钥
         * @param iv             //偏移量
         * @param encodingFormat //解密后的结果需要进行的编码
         * @return
         * @throws Exception
         */
        public static String decrypt(String data, String key, String iv, String encodingFormat)  {

            //被加密的数据
            byte[] dataByte = Base64.decodeBase64(data);
            //加密秘钥
            byte[] keyByte = Base64.decodeBase64(key);
            //偏移量
            byte[] ivByte = Base64.decodeBase64(iv);
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
                AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
                parameters.init(new IvParameterSpec(ivByte));
                cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
                byte[] resultByte = cipher.doFinal(dataByte);
                if (null != resultByte && resultByte.length > 0) {
                    String result = new String(resultByte, encodingFormat);
                    return result;
                }
                return null;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidParameterSpecException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
                e.printStackTrace();
                log.error("decrypt", e);
            }
            return null;
        }



//
//
//
//    public static void main(String[] args) {
//        /*EnumSet<SourceEnum> set=EnumSet.allOf(SourceEnum.class);
//        for(SourceEnum e:set){
//            log.debug("enum值： {},{} ",e.toString(),e.getCode());
//        }*/
//
//        String aesKey = "36c82834-3fe4-4305-b6e0-39d52e5113d4";
//        String password = "123456";
//        String resPass = new String(encryptAES(password, aesKey));
////        System.out.println(resPass);
//
//        String srcPass = new String(decryptAES(encryptAES(password, aesKey), aesKey));
////        System.out.println(srcPass);
//    }



    /**
     *
     * AES加密
     *
     * @param content   需要加密的内容
     * @param key  加密密钥
     * @return
     */
    public static byte[] encryptAES(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     * @param content  待解密内容
     * @param key 解密密钥
     * @return
     */
    public static byte[] decryptAES(byte[] content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
