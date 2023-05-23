package com.xiaoyi.springsecurity.infrastructure.config;

import com.xiaoyi.springsecurity.domain.auth.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.xiaoyi.springsecurity.domain.user.entity.Permission.*;
import static com.xiaoyi.springsecurity.domain.user.entity.Role.ADMIN;
import static org.springframework.http.HttpMethod.*;

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
	private final LogoutService logoutService;

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
						.requestMatchers("/api/admin/**").hasAnyRole(ADMIN.name())
						.requestMatchers(GET, "/api/admin/**").hasAuthority(ADMIN_READ.name())
						.requestMatchers(PUT, "/api/admin/**").hasAuthority(ADMIN_UPDATE.name())
						.requestMatchers(DELETE, "/api/admin/**").hasAuthority(ADMIN_DELETE.name())
						.requestMatchers(POST, "/api/admin/**").hasAuthority(ADMIN_CREATE.name())
//						.requestMatchers("/api/question/**").hasAnyRole(TEACHER.name())
//						.requestMatchers(GET, "/api/question/**").hasAuthority(TEACHER_READ.name())
//						.requestMatchers(PUT, "/api/question/**").hasAuthority(TEACHER_UPDATE.name())
//						.requestMatchers(DELETE, "/api/question/**").hasAuthority(TEACHER_DELETE.name())
//						.requestMatchers(POST, "/api/question/**").hasAuthority(TEACHER_CREATE.name())
						.anyRequest()
						.authenticated()
						.and()
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.authenticationProvider(authenticationProvider)
						.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
						.logout()
						.logoutUrl("/api/auth/logout")
						.addLogoutHandler(logoutService)
						.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
		return http.build();
	}
}
