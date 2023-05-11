package com.xiaoyi.springsecurity.auth;

import com.xiaoyi.springsecurity.user.Role;
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
	private String username;
	private String password;
	private String email;
	private Role role;
}
