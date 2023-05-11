package com.xiaoyi.springsecurity.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王艺翔
 * @description SpringDocConfig
 * @date 2023/5/9 21:24
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Configuration
public class SpringDocConfig {
		@Bean
		public OpenAPI mallTinyOpenAPI() {
			return new OpenAPI()
					.info(new Info().title("Demo API")
							.description("SpringDoc API")
							.version("v1.0.0")
							.license(new License().name("Apache 2.0").url("https://github.com/Tom-Collection/spring" +
									"-security")))
					.externalDocs(new ExternalDocumentation()
							.description("Spring Boot 3 + Spring Security 6 + JWT"));
		}
}
