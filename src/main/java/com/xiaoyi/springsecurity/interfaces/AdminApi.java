package com.xiaoyi.springsecurity.interfaces;

import com.xiaoyi.springsecurity.api.request.RegisterRequest;
import com.xiaoyi.springsecurity.api.request.TeamRequest;
import com.xiaoyi.springsecurity.api.response.UserResponse;
import com.xiaoyi.springsecurity.domain.user.entity.Role;
import com.xiaoyi.springsecurity.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("addAdmin")
	@Operation(summary = "创建新管理员")
	@PreAuthorize("hasAuthority('admin:create')")
	public ResponseEntity<UserResponse> addAdmin(@RequestBody RegisterRequest request) {
		request.setRole(Role.ADMIN);
		return ResponseEntity.ok(userService.save(request));
	}

	@PostMapping("/createTeam")
	@PreAuthorize("hasAuthority('admin:create')")
	@Operation(summary = "创建班级")
	public ResponseEntity<String> createTeam(@RequestBody TeamRequest request) {
		userService.createTeam(request);
		return ResponseEntity.ok(request.getName() + "创建成功");
	}

}
