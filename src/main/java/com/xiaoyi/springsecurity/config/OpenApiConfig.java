package com.xiaoyi.springsecurity.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * @author 王艺翔
 * @description SpringDocConfig
 * @date 2023/5/9 21:24
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */

@OpenAPIDefinition(
				// 定义安全需求
				security = {
								@SecurityRequirement(
												name = "Authentication"
								)
				}
)
// 定义安全方案
@SecurityScheme(
				// 定义类型为HTTP
				type = SecuritySchemeType.HTTP,
				// 名称
				name = "Authentication",
				// 简介
				description = "Authentication token",
				// 前缀
				scheme = "bearer",
				// 承载格式
				bearerFormat = "JWT",
				// 定义位置
				in = SecuritySchemeIn.HEADER
)

public class OpenApiConfig {
}
