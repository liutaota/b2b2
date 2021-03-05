package com.zsyc.zt.b2b.api;


import com.zsyc.webapp.oauth.BaseAccountHelper;
import org.springframework.stereotype.Component;

/**
 * Created by lcs on 2019-01-19.
 */
@Component
public class AccountHelper extends BaseAccountHelper {

	/**
	 * 获取后台用户ID
	 *
	 * @return
	 */
	public Long getUserId() {
		return userDetail().getAccountId();
	}

	/**
	 * 获取后台用户名
	 *
	 * @return
	 */
	public String getUserName() {
		return userDetail().getUsername();
	}

//    /**
//     * 获取会员
//     *
//     * @return
//     */
//    public User getMember() {
//        ZSYCUserDetails userDetails = getUserDetail();
//        AssertExt.notNull(userDetails, "无效用户detail信息");
//        User member = new User();
//        member.setUserId(userDetails.getAccountId());
//        member.setOpenId(userDetails.getUsername());
//        return member;
//    }
	/**
	 * 获取用户的原始openid
	 * @return
	 */
	public String getOriginOpenid() {
		return userDetail().getData("originOpenid");
	}

}
