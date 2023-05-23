package com.xiaoyi.springsecurity.api.response;

import com.xiaoyi.springsecurity.domain.question_bank.entity.Difficulty;
import com.xiaoyi.springsecurity.domain.question_bank.entity.QuestionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description QuestionResponse
 * @date 2023/5/16 18:16
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
	@Schema(description = "题目")
	private String topic;
	@Schema(description = "答案")
	private String answer;
	@Schema(description = "答案解释")
	private String answerExplain;
	@Schema(description = "类型")
	private QuestionType type;
	@Schema(description = "分数")
	private Double score;
	@Schema(description = "难度")
	private Difficulty difficulty;
	@Schema(description = "选项")
	private List<OptionResponse> options;
}
