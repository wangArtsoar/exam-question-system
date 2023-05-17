package com.xiaoyi.springsecurity.interfaces;

import com.xiaoyi.springsecurity.api.request.LoginRequest;
import com.xiaoyi.springsecurity.api.request.RegisterRequest;
import com.xiaoyi.springsecurity.api.response.AuthenticationResponse;
import com.xiaoyi.springsecurity.domain.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "验证接口")
public class AuthenticationApi {

	private final AuthenticationService authenticationService;

	/**
	 * 注册
	 *
	 * @param request 参数
	 * @return token
	 */
	@PostMapping("/register")
	@Operation(summary = "注册")
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
	@Operation(summary = "登录")
	public ResponseEntity<AuthenticationResponse> Login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authenticationService.login(request));
	}
}
