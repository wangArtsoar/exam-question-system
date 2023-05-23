package com.xiaoyi.springsecurity.api.response;

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
	private String name;
	private Long limitTime;
	private String username;
	private Double totalScore;
	private List<AnswerQuestionResponse> answerQuestionResponses;
}
