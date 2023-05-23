package com.xiaoyi.springsecurity.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description CourseResponse
 * @date 2023/5/22 20:58
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {
	@Schema(description = "课程名称")
	private String name;
	@Schema(description = "科任老师")
	private String teacher;
}
