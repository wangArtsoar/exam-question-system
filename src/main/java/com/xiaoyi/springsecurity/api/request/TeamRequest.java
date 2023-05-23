package com.xiaoyi.springsecurity.api.request;

import com.xiaoyi.springsecurity.domain.user.entity.Grade;
import com.xiaoyi.springsecurity.domain.user.entity.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description TeamRequest
 * @date 2023/5/19 13:23
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {
	private String name;
	private Specialty specialty;
	private Grade grade;
	private String headTeacher;
	private String headTeacherEmail;
	private List<CourseRequest> courses;
}
