package net.javaguides.springboot.springsecurity.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class adminSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			
			.antMatcher("/admin/**")
			.authorizeRequests()
			.antMatchers(
					"/**",
	                "/registration**",
	                "/js/**",
	                "/css/**",
	                "/images/**",
	                "/img/**",
	                "/index/**",
	                "/webjars/**",
	                "/admin/login",
	                "/employee/login",
	                "/admin/admin",
	                "/trainer/login",
	                "/admin/registration",
	                "/index").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/admin/login")
				.defaultSuccessUrl("/admin/index", true)
			.permitAll()
			.and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
		http.csrf().disable();

	}
}	