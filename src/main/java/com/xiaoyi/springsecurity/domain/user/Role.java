package com.xiaoyi.springsecurity.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.xiaoyi.springsecurity.domain.user.Permission.*;

/**
 * @author 王艺翔
 * @description Role
 * @date 2023/5/10 17:41
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RequiredArgsConstructor
public enum Role {

	STUDENT(Collections.emptySet()),
	TEACHER(
					Set.of(TEACHER_READ,
									TEACHER_CREATE,
									TEACHER_UPDATE,
									TEACHER_DELETE)
	),
	ADMIN(
					Set.of(ADMIN_READ,
									ADMIN_CREATE,
									ADMIN_UPDATE,
									ADMIN_DELETE,
									TEACHER_READ,
									TEACHER_CREATE,
									TEACHER_UPDATE,
									TEACHER_DELETE)
	);
	@Getter
	private final Set<Permission> permissions;

	public List<SimpleGrantedAuthority> getAuthorities() {
		var authorities = getPermissions()
						.stream()
						.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
						.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return authorities;
	}
}
