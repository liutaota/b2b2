package com.zsyc.webapp.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lcs on 2019-01-13.
 */
@Component("corsFilter")
public class CorsFilter extends OncePerRequestFilter implements Ordered {
	@Value("${zsyc.oauth2.request.cors.origin:*}")
	private String origin;
	@Value("${zsyc.oauth2.request.cors.headers:authorization}")
	private String heads;

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		httpServletResponse.setHeader("Access-Control-Allow-Origin", this.origin);
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", this.heads);

		if(RequestMethod.OPTIONS.name().equals(httpServletRequest.getMethod())){
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
