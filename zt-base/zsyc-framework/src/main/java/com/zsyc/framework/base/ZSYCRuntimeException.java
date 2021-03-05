package com.zsyc.framework.base;

import lombok.Data;

/**
 * Created by lcs on 2019-04-28.
 */
@Data
public class ZSYCRuntimeException extends RuntimeException {
	private String code;

	public ZSYCRuntimeException(){
		super();
	}

	public ZSYCRuntimeException(String message){
		super(message);
	}
	public ZSYCRuntimeException(String message,String code){
		super(message);
		this.code = code;
	}

	public ZSYCRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZSYCRuntimeException(String message, String code, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	protected ZSYCRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	protected ZSYCRuntimeException(String message, String code, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}
}
