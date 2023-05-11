package com.xiaoyi.springsecurity.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王艺翔
 * @description AuthenticationApi
 * @date 2023/5/11 14:38
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationApi {

	private final AuthenticationService authenticationService;

	/**
	 * 注册
	 *
	 * @param request 参数
	 * @return token
	 */
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> Register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authenticationService.register(request));
	}

	/**
	 * 登录
	 *
	 * @param request 参数
	 * @return token
	 */
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> Login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authenticationService.login(request));
	}
}
