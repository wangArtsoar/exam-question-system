package com.xiaoyi.springsecurity.api;

import com.xiaoyi.springsecurity.response.UserResponse;
import com.xiaoyi.springsecurity.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王艺翔
 * @description AdminApi
 * @date 2023/5/14 20:43
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "管理员接口")
public class AdminApi {

	private final UserService userService;

	@GetMapping("findAllUser")
	@Operation(summary = "查询所有成员")
	@PreAuthorize("hasAuthority('admin:read')")
	public ResponseEntity<List<UserResponse>> findAllUser() {
		return ResponseEntity.ok(userService.findAllUser());
	}
}
