package com.zsyc.webapp.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by lcs on 2018/11/17.
 */
public class StanderResponseReturnHandler extends RequestResponseBodyMethodProcessor {

	private List<String> ignorePaths;
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	public StanderResponseReturnHandler(List<HttpMessageConverter<?>> converters,List<String> ingorePath) {
		super(converters);
		if(ingorePath == null){
			this.ignorePaths = Collections.emptyList();
		}else {
			this.ignorePaths = ingorePath;
		}
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if(request == null){
			super.handleReturnValue(returnValue,returnType,mavContainer,webRequest);
			return;
		}

		for( String pattern :  this.ignorePaths){
			if(this.antPathMatcher.match(pattern, request.getServletPath())){
				super.handleReturnValue(returnValue,returnType,mavContainer,webRequest);
				return;
			}
		}

		super.handleReturnValue(ResponseJson.builder().errorCode("0").bizContent(returnValue).build(),returnType,mavContainer,webRequest);
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		if(returnType.getContainingClass().getName().startsWith("springfox")){
			return false;
		}
		return super.supportsReturnType(returnType);
	}
}
