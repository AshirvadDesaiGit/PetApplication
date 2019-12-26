package com.AnimalHelper.RestService.AnimalHelperService;



import java.util.Locale;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class AnimalHelperServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(AnimalHelperServiceApplication.class, args);
		//optional
		/*System.out.println("printing all the bean names");
		for(String bean:applicationContext.getBeanDefinitionNames())
		{
			
			System.out.println(bean);
			
		}*/
	}

}
