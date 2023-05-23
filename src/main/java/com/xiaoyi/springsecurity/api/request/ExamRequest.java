package com.xiaoyi.springsecurity.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description ExamRequest
 * @date 2023/5/18 22:45
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequest {
	@Schema(description = "试卷名称")
	private String name;
	@Schema(description = "试卷简介")
	private String description;
	@Schema(description = "限时")
	private Long limitedTime; // 1000 * 60 * 60 * 1.2 = 120min
	@Schema(description = "作者")
	private String author;
	@Schema(description = "题目合集")
	private List<QuestionRequest> questions;
}
