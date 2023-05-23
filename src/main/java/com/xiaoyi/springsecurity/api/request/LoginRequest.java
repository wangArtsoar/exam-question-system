package com.xiaoyi.springsecurity.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description AuthenticationRequest
 * @date 2023/5/11 14:42
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	@Schema(description = "用户邮箱")
	private String email;
	@Schema(description = "密码")
	private String password;
}
