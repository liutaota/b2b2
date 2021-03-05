package com.zsyc.webapp.config;

import com.zsyc.framework.base.ZSYCRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lcs on 2018/11/17.
 */

@ControllerAdvice
@Slf4j
public class ErrorController {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Object handleControllerException(RuntimeException e) {
		log.error("RuntimeException", e);
		return new ResponseEntity<>(ResponseJson.builder().errorCode("500").errorMessage(
				log.isDebugEnabled() ? e.getMessage() : "inner error"
		).build(), HttpStatus.OK);
	}

	@ExceptionHandler(ZSYCRuntimeException.class)
	@ResponseBody
	public Object handleZSYCRuntimeExceptionControllerException(ZSYCRuntimeException e) {
		log.error("ZSYCRuntimeException", e);
		String code = e.getCode();
		code = StringUtils.isBlank(code) ? "600" : code;
		return new ResponseEntity<>(ResponseJson.builder().errorCode(code).errorMessage(e.getMessage()).build(), HttpStatus.OK);
	}
}
