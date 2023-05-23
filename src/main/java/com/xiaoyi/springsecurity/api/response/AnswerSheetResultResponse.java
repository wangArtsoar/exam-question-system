package com.xiaoyi.springsecurity.api.response;

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
public class AnswerSheetResultResponse {
	private String username;
	private String examinationName;
	private Long compactTime;
	private Long limitTime;
	private Double totalScore;
}
