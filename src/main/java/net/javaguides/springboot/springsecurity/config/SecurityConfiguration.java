package net.javaguides.springboot.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.javaguides.springboot.springsecurity.service.EmployeeService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        
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
                "//login",
                "/trainer/registration",
                "/trainer/registration",
                "/employee/registration",
                "/index").permitAll()
            
            //.antMatchers("/admin/**").hasRole("USER")
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/instructor/**").hasRole("INSTRUCTOR")
            .antMatchers("/employee/**").hasRole("EMPLOYEE")
           //hasRole(Admin)
            .anyRequest().authenticated();
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .defaultSuccessUrl("/index", true)
//         
//            .permitAll()
//            .and()
//            .logout()
//            .invalidateHttpSession(true)
//            .clearAuthentication(true)
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//            .logoutSuccessUrl("/login?logout")
//            .permitAll();
    }
    
    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}