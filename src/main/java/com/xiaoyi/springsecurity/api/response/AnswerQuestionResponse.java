package com.xiaoyi.springsecurity.api.response;

import com.xiaoyi.springsecurity.domain.question_bank.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description AnswerQuestionResponse 回答问题（考题）
 * @date 2023/5/19 18:23
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerQuestionResponse {
	private String topic;
	private QuestionType type;
	private Double score;
	private List<OptionResponse> optionResponses;
}
