package com.xiaoyi.springsecurity.domain.user.service;

import com.xiaoyi.springsecurity.api.request.RegisterRequest;
import com.xiaoyi.springsecurity.api.request.TeamRequest;
import com.xiaoyi.springsecurity.api.response.TeamResponse;
import com.xiaoyi.springsecurity.api.response.UserResponse;

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

	UserResponse save(RegisterRequest request);

	String applyDelUser(String email, String password);

	String joinTeam(Integer teamId);

	void createTeam(TeamRequest request);

	TeamResponse getTeamById(Integer teamId);
}
