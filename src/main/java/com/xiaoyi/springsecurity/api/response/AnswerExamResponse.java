package com.xiaoyi.springsecurity.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description AnswerExamResponse 考卷
 * @date 2023/5/19 18:27
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerExamResponse {
	@Schema(description = "答卷名称")
	private String name;
	@Schema(description = "限时")
	private Long limitTime;
	@Schema(description = "答题人")
	private String username;
	@Schema(description = "总分")
	private Double totalScore;
	@Schema(description = "题目回答合集")
	private List<AnswerQuestionResponse> answerQuestionResponses;
}
