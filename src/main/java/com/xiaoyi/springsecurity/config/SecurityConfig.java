package com.xiaoyi.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 王艺翔
 * @description SecurityConfig
 * @date 2023/5/9 19:28
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthConfig jwtAuthFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
						.csrf().disable()
						.authorizeHttpRequests()
						.requestMatchers( // Swagger的资源路径需要允许访问
										"/api/auth/**",
										"/v2/api-docs",
										"/v3/api-docs",
										"/v3/api-docs/**",
										"/swagger-resources",
										"/swagger-resources/**",
										"/configuration/ui",
										"/configuration/security",
										"/swagger-ui/**",
										"/webjars/**",
										"/swagger-ui.html"
						)
						.permitAll()
						.anyRequest()
						.authenticated()
						.and()
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.authenticationProvider(authenticationProvider)
						.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
