package com.zsyc.webapp.oauth;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lcs on 2019-01-19.
 */
public class BaseAccountHelper {

    private static OAuth2Authentication getOAuth2Authentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }

        if (SecurityContextHolder.getContext()
                .getAuthentication() == null) {
            return null;
        }
        if (!(SecurityContextHolder.getContext()
                .getAuthentication() instanceof OAuth2Authentication)) {
            return null;
        }
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        return auth2Authentication;
    }

    private static ZSYCUserDetails getUserDetail() {
        return (ZSYCUserDetails) getOAuth2Authentication().getUserAuthentication().getDetails();
    }

    public ZSYCUserDetails userDetail() {
        return getUserDetail();
    }

    /**
     * 获取客户端类型
     * @return
     */
    public String getClientId(){
        //TODO 这个值与 ${zsyc.oauth2.clients.xxx.client-id} 的值一致
        return getOAuth2Authentication().getOAuth2Request().getClientId();
    }
    /**
	 * 获取IP
     * @param request
	 * @return
	 */
    public  String getIP(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }
}
