package com.xiaoyi.springsecurity.domain.auth;

import com.xiaoyi.springsecurity.api.request.LoginRequest;
import com.xiaoyi.springsecurity.api.request.RegisterRequest;
import com.xiaoyi.springsecurity.api.response.AuthenticationResponse;
import com.xiaoyi.springsecurity.domain.user.entity.User;
import com.xiaoyi.springsecurity.domain.user.repo.UserRepo;
import com.xiaoyi.springsecurity.infrastructure.config.JwtUtils;
import com.xiaoyi.springsecurity.infrastructure.exception.EmailAlreadyExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 王艺翔
 * @description AuthenticationService
 * @date 2023/5/11 17:25
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final JwtUtils jwtUtils;
	private final AuthenticationManager manager;
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final RedisTemplate<String, Object> redisTemplate;

	public AuthenticationResponse register(RegisterRequest request) {
		// 验证注册是否重复
		if (userRepo.findByEmail(request.getEmail()).isPresent())
			throw new EmailAlreadyExistedException("the email has existed");
		// 创建user
		var user = User
						.builder()
						.name(request.getUsername())
						.pwd(passwordEncoder.encode(request.getPassword()))
						.email(request.getEmail())
						.role(request.getRole())
						.build();
		// 写入数据库
		userRepo.save(user);
		// 生成token
		String token = jwtUtils.generateToken(user);
		// 保存token
		redisTemplate.opsForValue().append(user.getEmail(), token);
		return AuthenticationResponse.builder().token(token).build();
	}

	public AuthenticationResponse login(LoginRequest request) {
		//
		manager.authenticate(new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
		));
		// 验证数据库
		User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
		// 清空token by user_email
		redisTemplate.delete(user.getEmail());
		// 生成token
		String token = jwtUtils.generateToken(user);
		// 保存token
		redisTemplate.opsForValue().append(user.getEmail(), token);
		return AuthenticationResponse.builder().token(token).build();
	}
}
