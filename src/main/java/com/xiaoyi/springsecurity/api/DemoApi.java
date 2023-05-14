package com.xiaoyi.springsecurity.api;

import com.xiaoyi.springsecurity.user.UserResponse;
import com.xiaoyi.springsecurity.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王艺翔
 * @description DemoApi
 * @date 2023/5/9 19:42
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "demo")
public class DemoApi {

	private final UserService userService;

	@GetMapping("/findByUserEmail")
	public ResponseEntity<UserResponse> getUserByUsername(String email) {
		return ResponseEntity.ok(userService.findByUserEmail(email));
	}
}