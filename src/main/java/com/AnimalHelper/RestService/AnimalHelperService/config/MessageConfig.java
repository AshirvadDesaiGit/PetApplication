package com.AnimalHelper.RestService.AnimalHelperService.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * This configuration class creates a message source
 * This is to be used for displaying error messages
 **/
@Configuration
public class MessageConfig {

    /*@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }*/

    /**
     * This method is not needed in case we enable the property spring.messages.basename in application.properties
     * @return
     */

    /*
     * @Bean // used for internationalization reading messages public public
     * ResourceBundleMessageSource bundleMessageSource() { System.out.
     * println("ResourceBundleMessageSource intalilized !!!!!!!!!!!!!!!!!!!!!!!!!!!!"
     * ); ResourceBundleMessageSource messageSource = new
     * ResourceBundleMessageSource(); messageSource.setBasename("messages"); //
     * messageSource.setDefaultEncoding("UTF-8"); return messageSource; }
     */
}
