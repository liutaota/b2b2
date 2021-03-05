package com.zsyc.zt.order.exception;

import lombok.Data;

/**
 * Created by lcs on 2019-04-28.
 */
@Data
public class IncaApiRuntimeException extends RuntimeException {
	private String code;

	public IncaApiRuntimeException(){
		super();
	}

	public IncaApiRuntimeException(String message){
		super(message);
	}
	public IncaApiRuntimeException(String message, String code){
		super(message);
		this.code = code;
	}

	public IncaApiRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncaApiRuntimeException(String message, String code, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	protected IncaApiRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	protected IncaApiRuntimeException(String message, String code, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}
}
