package com.xiaoyi.springsecurity.api.request;

import com.xiaoyi.springsecurity.domain.user.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description RegisterRequest
 * @date 2023/5/11 14:45
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	@Schema(description = "用户昵称")
	private String username;
	@Schema(description = "密码")
	private String password;
	@Schema(description = "用户邮箱")
	private String email;
	@Schema(description = "用户角色")
	private Role role;
}
