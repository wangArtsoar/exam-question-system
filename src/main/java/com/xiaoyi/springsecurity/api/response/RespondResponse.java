package com.xiaoyi.springsecurity.api.response;

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
	private String topic;
	private Double score;
	private String respond;
	private String answerExplain;
	private String answer;
}
