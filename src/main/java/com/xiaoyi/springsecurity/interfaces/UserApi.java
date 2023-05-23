package com.xiaoyi.springsecurity.interfaces;

import com.xiaoyi.springsecurity.api.response.TeamResponse;
import com.xiaoyi.springsecurity.api.response.UserResponse;
import com.xiaoyi.springsecurity.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王艺翔
 * @description DemoApi
 * @date 2023/5/9 19:42
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RestController
@RequestMapping("/api/user")
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

	@PutMapping("/joinTeam{teamId}")
	@Operation(summary = "加入班级")
	public ResponseEntity<String> joinTeam(@PathVariable Integer teamId, HttpServletRequest request) {
		return ResponseEntity.ok(userService.joinTeam(teamId, request));
	}

	@GetMapping("/getTeamById")
	@Operation(summary = "查询班级")
	public ResponseEntity<TeamResponse> getTeamById(Integer teamId) {
		return ResponseEntity.ok(userService.getTeamById(teamId));
	}
}