package com.zsyc.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lcs on 2018/11/16.
 */
@Configuration
public class WebappConfig {
	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	@Value("${zsyc.api.ignore-path:}")
	String[] ignorePaths;

	@PostConstruct
	void init(){
		List<HandlerMethodReturnValueHandler> returnValueHandlers = new LinkedList<>();
		returnValueHandlers.add(new StanderResponseReturnHandler(this.requestMappingHandlerAdapter.getMessageConverters(), Arrays.asList(ignorePaths)));
		returnValueHandlers.addAll(requestMappingHandlerAdapter.getReturnValueHandlers());
		requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
	}
}
