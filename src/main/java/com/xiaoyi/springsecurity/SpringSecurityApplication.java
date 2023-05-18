package com.xiaoyi.springsecurity;

import com.xiaoyi.springsecurity.api.request.RegisterRequest;
import com.xiaoyi.springsecurity.domain.auth.AuthenticationService;
import com.xiaoyi.springsecurity.domain.user.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service) {
		return args -> {
			var admin = RegisterRequest.builder()
							.username("xiaoyi").password("password").email("xiaoyi_wyx@163.com").role(Role.ADMIN).build();
			var teacher = RegisterRequest.builder()
							.username("xiaoyi").password("password").email("xiaoyi_wyx@icloud.com").role(Role.TEACHER).build();
			var student = RegisterRequest.builder()
							.username("xiaoyi").password("password").email("xiaoyi_wyx@outlook.com").role(Role.STUDENT).build();
			System.out.println(service.register(admin));
			System.out.println(service.register(teacher));
			System.out.println(service.register(student));
		};
	}
	// learn from https://github.com/ali-bouali/spring-boot-3-jwt-security.git
}
