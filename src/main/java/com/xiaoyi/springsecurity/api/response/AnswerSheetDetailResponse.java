package com.xiaoyi.springsecurity.api.response;

import com.xiaoyi.springsecurity.domain.examination.entity.CompleteLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 王艺翔
 * @description AnswerSheetDetailResponse 答卷详细
 * @date 2023/5/19 18:12
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSheetDetailResponse {
	private String username;
	private Double totalScore;
	private Date answerDate;
	private CompleteLevel level;
	private List<RespondResponse> respondResponses;
}
