package com.xiaoyi.springsecurity.api;

import com.xiaoyi.springsecurity.response.UserResponse;
import com.xiaoyi.springsecurity.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
@Tag(name = "用户接口")
public class UserApi {

	private final UserService userService;

	@PutMapping("/findUser{email}")
	@Operation(summary = "根据email查询用户")
	public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String email) {
		return ResponseEntity.ok(userService.findByUserEmail(email));
	}

	@PutMapping("/applyDel{email}{password}")
	@Operation(summary = "申请删除用户")
	public ResponseEntity<String> applyDelUser(@PathVariable String email, @PathVariable String password) {
		return ResponseEntity.ok(userService.applyDelUser(email, password));
	}
}