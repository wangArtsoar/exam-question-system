package com.xiaoyi.springsecurity.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description RespondResponse 回答题目答案
 * @date 2023/5/19 18:14
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondResponse {
	@Schema(description = "题目")
	private String topic;
	@Schema(description = "分数")
	private Double score;
	@Schema(description = "回答")
	private String respond;
	@Schema(description = "答案解释")
	private String answerExplain;
	@Schema(description = "答案")
	private String answer;
}
