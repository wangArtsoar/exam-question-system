package com.xiaoyi.springsecurity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description AuthenticationResponse
 * @date 2023/5/11 14:42
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	@JsonProperty("access-token")
	private String token;
	private String error;
}
