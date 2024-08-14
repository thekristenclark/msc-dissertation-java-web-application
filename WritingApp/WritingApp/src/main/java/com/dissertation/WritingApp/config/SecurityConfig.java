package com.dissertation.WritingApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.dissertation.WritingApp.domain.User;
//import com.dissertation.WritingApp.service.MongoAuthUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
//    private final MongoAuthUserDetailService mongoAuthUserDetailService;


    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
 //       this.mongoAuthUserDetailService = mongoAuthUserDetailService;

    }

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(Customizer.withDefaults())
//            .httpBasic(Customizer.withDefaults())
//            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll())
//            .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return http.build();
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	
//    @Autowired
//    private CustomUserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	/*	http.csrf(csrf -> csrf.disable()) */
    	http
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/", "/home").permitAll()
			.requestMatchers("/editor").authenticated()
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
			.loginPage("/login")
			.defaultSuccessUrl("/editor",true)
			.permitAll()
		)
		.logout((logout) -> logout.permitAll()
		)
    	.sessionManagement(sessionManagement -> 
    		sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		);
	return http.build();
    }  
    
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User
//			 	.setUsername("user")
//				.setPassword("password");
//
//		return new InMemoryUserDetailsManager(user);
//	}
    
//	@Bean
//	public UserDetailsService userDetailsService2() {
//		
//		return mongoAuthUserDetailService;
//	}


}
