package com.xiaoyi.springsecurity.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 王艺翔
 * @description AnswerDetailsRequest
 * @date 2023/5/24 14:40
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDetailsRequest {
	@Schema(description = "试卷ID")
	private Integer examId;
	@Schema(description = "回答问题map")
	private Map<Integer, String> answerQuestionMap;
	@Schema(description = "答题时间")
	private Long answerTime;
}
