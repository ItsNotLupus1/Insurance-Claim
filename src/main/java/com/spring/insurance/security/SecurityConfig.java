package com.spring.insurance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    
//    @Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
//		auth.setUserDetailsService(userDetailsService);
//		auth.setPasswordEncoder(passwordEncoder());
//		return auth;
//	}

    @Override
    @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Override 
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf()
        		.disable()
        		.formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .failureUrl("/login?error=true")
                    .permitAll()
                    .defaultSuccessUrl("/home", true)
                .and()
                .csrf()
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/home").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET, "/profile").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET, "/policyForm").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET, "/claim").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET, "/hospForm").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.GET, "/health").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET, "/getFile").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET, "/dependents").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers("/css/").permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/login").permitAll()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID");

    }
}
