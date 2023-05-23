package com.xiaoyi.springsecurity.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description TeamResponse 班级
 * @date 2023/5/19 13:24
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamResponse {
	@Schema(description = "班级名称")
	private String name;
	@Schema(description = "专业")
	private String specialty;
	@Schema(description = "年级")
	private String grade;
	@Schema(description = "班主任/导员")
	private String headTeacher;
	@Schema(description = "学生")
	private List<UserResponse> students;
	@Schema(description = "课程")
	private List<CourseResponse> courses;
}
