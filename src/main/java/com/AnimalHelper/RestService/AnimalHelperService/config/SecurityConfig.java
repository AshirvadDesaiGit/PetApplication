package com.AnimalHelper.RestService.AnimalHelperService.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	/* this class overrides the default spring security config */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser(User.withUsername("ashirvad").password("ashirvad").roles("ADMIN"))
		.withUser(User.withUsername("jairam").password("jairam").roles("ADMIN"));
		
		/*
		 * InMemory Config
		 * auth.inMemoryAuthentication().withUser("ashirvad").password("ashirvad").roles
		 * ("ADMIN") .and().withUser("jairam").password("jairam").roles("ADMIN")
		 * .and().withUser("tony").password("tony").roles("ADMIN");
		 */
		
	}
	
  @Bean
  public PasswordEncoder setPasswordEncoder() {
	return NoOpPasswordEncoder.getInstance();
	  
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	 http.authorizeRequests().antMatchers("/swagger-ui/*").hasRole("ADMIN")
	 .antMatchers("/pet/**").hasAnyRole("ADMIN","USER")
	 .antMatchers("/user/**").hasAnyRole("ADMIN","USER");
	 //.and().formLogin();
  }
}
