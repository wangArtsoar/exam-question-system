package com.xiaoyi.springsecurity.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author 王艺翔
 * @description JwtAuthConfig
 * @date 2023/5/10 20:06
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github <a href="https://github.com/Tom-Collection">...</a>"
 */
@Component
@RequiredArgsConstructor
public class JwtAuthConfig extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
					@NonNull HttpServletRequest request,
					@NonNull HttpServletResponse response,
					@NonNull FilterChain filterChain) throws ServletException, IOException {
		// 放行
		if (request.getServletPath().contains("/api/auth/")) {
			filterChain.doFilter(request, response);
			return;
		}
		// 获取头信息
		final String header = request.getHeader("Authorization");
		// 判断头信息是否符合Token
		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		// 获取jwt
		final String jwt = header.substring(7);
		// 提取userEmail
		final String userEmail = jwtUtils.extractUserEmail(jwt);
		// 获取当前用户的认证信息(如果用户尚未登录，则没有认证信息)
		SecurityContext auth = SecurityContextHolder.getContext();
		// 如果email不为空并且认证信息为空
		if (userEmail != null && auth.getAuthentication() == null) {
			// 根据用户邮箱获取用户信息
			UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
			// 检查token
			if (jwtUtils.isTokenValid(jwt, userDetails)) {
				// 创建一个新的UsernamePasswordAuthenticationToken
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
								new UsernamePasswordAuthenticationToken(
												userDetails,
												null,
												userDetails.getAuthorities()
								);
				// 将其设置为当前安全上下文中的身份验证
				usernamePasswordAuthenticationToken.setDetails(
								new WebAuthenticationDetailsSource().buildDetails(request)
				);
				auth.setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
