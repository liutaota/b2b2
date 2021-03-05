package com.zsyc.zt.b2b.service;

/**
 * Created by lcs on 2019-06-13.
 */
public  interface OauthService {
	/**
	 * 强制一微信用户退出登录
	 */
	void kickOutWechatMember(String openId);

	/**
	 * 强制一b2b用户退出登录
	 */
	void kickOutB2BMember(String telephone);

	/**
	 * 强制一后台用户退出登录
	 */
	void kickOutUser(String userId);

}
