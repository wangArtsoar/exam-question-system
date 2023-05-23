package com.xiaoyi.springsecurity.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description CourseRequest
 * @date 2023/5/22 21:14
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {
	private String name;
	private String teacher;
	private String teacherEmail;
}
