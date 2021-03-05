package com.zsyc.zt.b2b.oauth;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lcs on 2019-08-01.
 */
@Api
@RestController
public class IndexController {

	@Value("${spring.application.name}")
	private String app;

	@GetMapping({"index", "", "/"})
	public Object index(){
		return String.format("%s IS RUNNING", app).toUpperCase();
	}
}
