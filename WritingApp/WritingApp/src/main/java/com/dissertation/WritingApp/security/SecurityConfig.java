package com.dissertation.WritingApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Autowired
	 CustomUserDetailsService customUserDetailsService;

	 @Bean
	 public static PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	 }	
	
    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable()
        .authorizeHttpRequests()
        	.requestMatchers("/writeSimply").permitAll()
        	.requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**").permitAll() // Allow static resources
        	.requestMatchers("/left-sidebar", "/right-sidebar", "/no-sidebar").permitAll()
        	.requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**").permitAll() // Allow static resources
            .requestMatchers("/register").permitAll()
            .requestMatchers("/email-confirmation").permitAll() // Whitelist /confirm-email endpoint
            .requestMatchers("/home").authenticated()
			.requestMatchers("/editor").authenticated()	// requires auth to access editor page
            .requestMatchers("/editor-new").authenticated()
            .requestMatchers("/editor/**").authenticated() // Ensure that other endpoints are protected
        .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/home", true)
            .permitAll()
        .and()
        .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/writeSimply")
            .permitAll();
//    	.sessionManagement(sessionManagement -> 
//    		sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		);
	return http.build();
    }  

	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	 }
}
