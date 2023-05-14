package com.xiaoyi.springsecurity.user;

import com.xiaoyi.springsecurity.response.UserResponse;

import java.util.List;

/**
 * @author 王艺翔
 * @description UserService
 * @date 2023/5/11 20:27
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public interface UserService {
	UserResponse findByUserEmail(String email);

	List<UserResponse> findAllUser();
}
