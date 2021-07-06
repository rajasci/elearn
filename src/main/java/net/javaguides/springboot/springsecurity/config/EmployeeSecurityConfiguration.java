package net.javaguides.springboot.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(3)
public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			
			.antMatcher("/employee/**")
			.authorizeRequests()
			.antMatchers(
					"/**",
	                "/registration**",
	                "/js/**",
	                "/css/**",
	                "/images/**",
	                "/index/**",
	                "/img/**",
	                "/webjars/**",
	                "/admin/login",
	                "/employee/login",
	                "/admin/admin",
	                "/trainer/login",
	                "/employee/registration",
	                "/employee/registration",
	                "/index").permitAll()
			.antMatchers("/user/**").access("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN') or hasRole('ROLE_TRAINER')")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/employee/login")
				.defaultSuccessUrl("/employee/index", true)
			.permitAll()
			.and().logout().logoutUrl("/employee/logout").logoutSuccessUrl("/employee/login");
		http.csrf().disable();

	}
}	