package com.xiaoyi.springsecurity.api.request;

import com.xiaoyi.springsecurity.domain.user.entity.Grade;
import com.xiaoyi.springsecurity.domain.user.entity.Specialty;
import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "班级名称")
	private String name;
	@Schema(description = "专业")
	private Specialty specialty;
	@Schema(description = "年级")
	private Grade grade;
	@Schema(description = "班主任/导员")
	private String headTeacher;
	@Schema(description = "班主任/导员 邮箱")
	private String headTeacherEmail;
	@Schema(description = "课程合集")
	private List<CourseRequest> courses;
}
