package com.zsyc.zt.b2b.api.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.service.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcs on 2019-01-19.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Value("${spring.application.name:api-docs}")
	private String app;

	@Bean
	public Docket apiDocument() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder().title(this.app).build())
//				.ignoredParameterTypes(ApiIgnore.class)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.build()
				.globalOperationParameters(setHeaderToken());
	}

	private List<Parameter> setHeaderToken() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("authorization").description("authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());
		return pars;
	}
}


