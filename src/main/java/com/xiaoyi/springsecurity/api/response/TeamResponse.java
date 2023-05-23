package com.xiaoyi.springsecurity.api.response;

import java.util.List;

/**
 * @author 王艺翔
 * @description TeamResponse 班级
 * @date 2023/5/19 13:24
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public class TeamResponse {
	private String name;
	private String specialty;
	private String grade;
	private String headTeacher;
	private List<UserResponse> students;
	private List<CourseResponse> courses;
}
