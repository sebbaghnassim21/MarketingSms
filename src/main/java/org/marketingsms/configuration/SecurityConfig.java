package org.marketingsms.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired	
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {			 
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());;
		
	}	
	
	  
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/js/**", "/css/**").permitAll();
		 http.authorizeRequests()
			.antMatchers("/index","/accompt","/template","/shopmag","/listemag")
			.access("hasRole('ROLE_USER')")		
			.anyRequest().permitAll()
			
			
		
			.and()
			  .formLogin().loginPage("/login")
			  .usernameParameter("username").passwordParameter("password").failureHandler(new UsernameInURLAuthenticationFailureHandler())
			.and()
			  .logout().logoutSuccessUrl("/login?logout")	
			  .and()
			 .exceptionHandling().accessDeniedPage("/403")
			.and()
			  .csrf();
	}
	
	@Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
    	return new BCryptPasswordEncoder();
    }
	
	
}
