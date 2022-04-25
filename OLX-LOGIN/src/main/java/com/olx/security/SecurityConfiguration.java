package com.olx.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{ //Used for authentication
		
		auth.userDetailsService(userDetailsService);
	}
	
	@Override 
	public void configure(HttpSecurity http) throws Exception{ //Used for Authorization
		http.csrf().disable()
		.authorizeRequests()	
		.antMatchers("/olx/login/user").hasAnyRole("USER", "ADMIN")
		.antMatchers("/olx/login/user/all").hasRole("ADMIN")
		.antMatchers("/olx/login/user/{id}").hasRole("ADMIN")
		.antMatchers("/olx/login/user/logout").hasAnyRole("USER", "ADMIN")		 
		.antMatchers("/olx/login/user/authenticate").permitAll()
		.and()
		.httpBasic()
		.and()
		.formLogin();
	}
	 
	
	//Encoding authorized users' password
	@Bean
	public PasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean 
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
