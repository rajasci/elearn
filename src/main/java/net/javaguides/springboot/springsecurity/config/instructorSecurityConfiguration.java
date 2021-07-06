package net.javaguides.springboot.springsecurity.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class instructorSecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/trainer/**")
			 
			.authorizeRequests()
			
			.antMatchers(
					"/**",
	                "/registration**",
	                "/js/**",
	                "/index/**",
	                "/css/**",
	                "/images/**",
	                "/img/**",
	                "/webjars/**",
	                "/admin/login",
	                "/employee/login",
	                "/admin/admin",
	                "/trainer/login",
	                "/trainer/logg",
	                "/trainer/css/**",
	                "/trainer/images/**",
	                "/trainer/registration",
	                "/trainer/css/**",
	                "/trainer/images/**",
	                "/index").permitAll()
			
			.antMatchers("/trainer/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_TRAINER')")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/trainer/login")
				.defaultSuccessUrl("/trainer/index", true)
			.permitAll()
			.and().logout().logoutUrl("/trainer/logout").logoutSuccessUrl("/trainer/login");
		http.csrf().disable();

	}
}	