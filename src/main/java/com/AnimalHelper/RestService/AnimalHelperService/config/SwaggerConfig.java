package com.AnimalHelper.RestService.AnimalHelperService.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Set<String> PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json","application/xml"));
	private static Contact DEFAULT_CONTACT=new Contact("Ashirvad R Desai", "", "ashirvad@petShare.com");
	public static ApiInfo DEFAULT_API_INFO =new ApiInfo("Pet Application", "My test pet application", "1.0", "dont use wthout my written permission", DEFAULT_CONTACT,
			"licence by ashirvad", "http://pets/licence.com");

	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(PRODUCES_AND_CONSUMES).consumes(PRODUCES_AND_CONSUMES);
	}

}
