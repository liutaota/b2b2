package com.zsyc.framework.base;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ZSYCOAuthException extends RuntimeException{
    private String code;
    private Map<String, String> otherInfo ;

    public ZSYCOAuthException(String message, String code){
        super(message);
        this.code = code;
		this.otherInfo = new HashMap<>();
    }

	/**
	 * 添加拓展错误信息
	 * @param key
	 * @param val
	 * @return
	 */
    public ZSYCOAuthException add(String key,String val){
    	if(this.otherInfo == null){
			this.otherInfo = new HashMap<>();
		}
		this.otherInfo.put(key, val);
		return this;
	}
}
