package com.zsyc.zt.b2b.service;

import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.vo.ScanInfoVo;

/**
 * Created by lcs on 2019-05-12.
 */
public interface LoginService {

    /**
     * 发送验证
     * 1. 不能频繁发
     * 2. 将 code 存到redis，并返回
     *
     * @param telephone
     * @return
     */
    String sendSmsCode(String telephone, String logType, String ip);

    /**
     * 生成图片验证码
     *
     * @param codeKey
     * @return
     */
    String genCaptchaCode(String codeKey);

    /**
     * 检验验图片证码
     *
     * @param telephone
     * @param code
     */
    void validateCaptchaCode(String telephone, String code);

    /**
     * 检验验证码
     *
     * @param telephone
     * @return
     */
    void validateSmsCode(String telephone, String code);

    /**
     * pcWeb客户登录
     *
     * @param telephone
     * @param password
     * @return
     */
    Member memberLogin(String telephone, String password, String code, String ip);

    /**
     * 手机号授权登录
     *
     * @param code
     * @return
     */
    Member telephoneAuth(String telephone, String code, String loginCode, String password);

    /**
     * 客户可买商品
     */
    void addCustomerCanBuyGoods();

    /**
     * 客户注册
     *
     * @param telephone
     * @param code
     * @param password
     * @param rePassword
     */
    Member register(String telephone, String code, String password, String rePassword, String ip);

    /**
     * 生成二维码code
     *
     * @return
     */
    String getScanCode();

    /**
     * 确认扫码登录
     *
     * @param scanCode
     * @param loginCode
     * @return
     */
    void scanCodeConfirmAuth(String scanCode, String loginCode);

    /**
     * 获取扫码信息
     *
     * @param scanCode
     * @return
     */
    ScanInfoVo getScanInfoByScanCode(String scanCode);

    /**
     * 微信授权登录
     *
     * @param code
     * @param scanCode
     * @return
     */
    Member wechatAuth(String code, String scanCode);

    /**
     * 微信小程序授权获取手机号登录
     *
     * @param encryptedData
     * @param iv
     * @param loginCode
     * @return
     */
    Member wechatTelephoneAuth(String encryptedData, String iv, String loginCode);

    /**
     * 手机号授权登录
     *
     * @param code
     * @return
     */
    Member telephoneAppAuth(String telephone, String code, String password);

    /**
     * 登录白名单判断
     * @param telephone
     * @return
     */
    boolean loginList(String telephone);
}
