package com.c.config;


import java.io.IOException;
import java.nio.channels.UnsupportedAddressTypeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.c.Services.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {
	@Autowired
	private SecurityCustomUserDetailService userDetailservice;
	
	@Autowired
	private OAuthenticationSuccessHandler Ohandler;
	
   @Bean
   public DaoAuthenticationProvider autenticationprovider() {
	   DaoAuthenticationProvider daoAuthenticationProvider = new 
			   DaoAuthenticationProvider();
	   
	   daoAuthenticationProvider.setUserDetailsService(userDetailservice);
	   daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	   
	   
	return daoAuthenticationProvider;
   }
	
   @Bean
   public PasswordEncoder passwordEncoder() {
	  
	return new BCryptPasswordEncoder();
   }
   
   @Bean
   public SecurityFilterChain securityfilterchain(HttpSecurity security) throws Exception {
	   
	    security.authorizeHttpRequests(auth->{
	    	auth.requestMatchers("/user/**").authenticated();
	    	auth.anyRequest().permitAll();
	    });
	    security.formLogin(formlogin ->{
	    	formlogin.loginPage("/login");
	    	formlogin.loginProcessingUrl("/authenticated");
	    	formlogin.successForwardUrl("/user/profile");
			/* formlogin.failureForwardUrl("/login?error=true"); */
	    	formlogin.usernameParameter("email");
	    	formlogin.passwordParameter("password");
	    	
	    	
			/*
			 * formlogin.failureHandler(new AuthenticationFailureHandler() {
			 * 
			 * @Override public void onAuthenticationFailure(HttpServletRequest request,
			 * HttpServletResponse response, AuthenticationException exception) throws
			 * IOException, ServletException { throw new UnsupportedAddressTypeException();
			 * 
			 * } }); formlogin.successHandler(new AuthenticationSuccessHandler() {
			 * 
			 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
			 * HttpServletResponse response, Authentication authentication) throws
			 * IOException, ServletException { // TODO Auto-generated method stub
			 * 
			 * } });;
			 */
	   
	    });
	     security.csrf(AbstractHttpConfigurer::disable);
	    security.logout(logout->{
	    	logout.logoutUrl("/do-logout");
	    	logout.logoutSuccessUrl("/login?logout=true");
	    });
	    
	    //oauth congif
	    
	    security.oauth2Login(oauth->{
	    	oauth.loginPage("/login");
	    	oauth.successHandler(Ohandler);
	    	});	   
	   
	   return security.build();
	   
   }
	
	
}
