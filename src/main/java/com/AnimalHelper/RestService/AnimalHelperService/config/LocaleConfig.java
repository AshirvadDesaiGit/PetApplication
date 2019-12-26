package com.AnimalHelper.RestService.AnimalHelperService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean //used for internationalization
    public LocaleResolver localeResolver() {
        //SessionLocaleResolver  localeResolver=new SessionLocaleResolver();
        AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
}
