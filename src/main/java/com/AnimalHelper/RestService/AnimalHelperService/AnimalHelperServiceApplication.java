package com.AnimalHelper.RestService.AnimalHelperService;



import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class AnimalHelperServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalHelperServiceApplication.class, args);
	}

	@Bean //used for internationalization
	public LocaleResolver localeResolver() {
		SessionLocaleResolver  localeResolver=new SessionLocaleResolver();
		System.out.println("LocaleResolver  !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	/**
	 * This method is not needed in case we enable the property spring.messages.basename in application.properties
	 * @return
	 */
	
	@Bean // used for internationalization reading messages public
	public ResourceBundleMessageSource bundleMessageSource() {
		System.out.println("ResourceBundleMessageSource intalilized !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		// messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	
	 
}
