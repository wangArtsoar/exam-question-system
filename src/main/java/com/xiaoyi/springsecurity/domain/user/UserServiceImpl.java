package com.xiaoyi.springsecurity.domain.user;

import com.xiaoyi.springsecurity.api.request.RegisterRequest;
import com.xiaoyi.springsecurity.api.response.UserResponse;
import com.xiaoyi.springsecurity.infrastructure.exception.EmailAlreadyExistedException;
import com.xiaoyi.springsecurity.infrastructure.exception.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王艺翔
 * @description UserServiceImpl
 * @date 2023/5/11 20:27
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final RedisTemplate<String, Object> redisTemplate;
	private final AuthenticationManager manager;

	@Override
	public UserResponse findByUserEmail(String email) {
		User user = userRepo.findByEmail(email)
						.orElseThrow(() -> new EmailNotFoundException("Email not found: " + email));
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}

	@Override
	public List<UserResponse> findAllUser() {
		return userRepo.findAll().stream().map(
						user -> UserResponse.builder().name(user.getName()).email(user.getEmail())
										.role(user.getRole()).build()).toList();
	}

	@Override
	public UserResponse save(RegisterRequest request) {
		if (userRepo.findByEmail(request.getEmail()).isPresent()) {
			throw new EmailAlreadyExistedException("the email has existed");
		}
		User save = userRepo.save(User.builder()
						.name(request.getUsername())
						.email(request.getEmail())
						.pwd(passwordEncoder.encode(request.getPassword()))
						.role(request.getRole()).build());
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(save, userResponse);
		return userResponse;
	}

	@Override
	public String applyDelUser(String email, String password) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		if (Boolean.TRUE.equals(redisTemplate.hasKey(email))) {
			return "申请成功";
		}
		return "申请失败";
	}

}
