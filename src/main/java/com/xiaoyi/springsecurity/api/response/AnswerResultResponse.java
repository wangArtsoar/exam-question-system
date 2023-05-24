package com.xiaoyi.springsecurity.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description AnswerSheetResponse 答卷结果
 * @date 2023/5/19 18:05
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResultResponse {
	@Schema(description = "答题人")
	private String username;
	@Schema(description = "试卷名称")
	private String examinationName;
	@Schema(description = "完成时长")
	private Long compactTime;
	@Schema(description = "限时")
	private Long limitTime;
	@Schema(description = "试卷总分")
	private Double totalScore;
	@Schema(description = "成绩")
	private Double answerScore;
}
