package com.xiaoyi.springsecurity.api.response;

import com.xiaoyi.springsecurity.domain.examination.entity.CompleteLevel;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class AnswerDetailResponse {
	@Schema(description = "答题人")
	private String username;
	@Schema(description = "总分")
	private Double totalScore;
	@Schema(description = "答题时长")
	private Long answerTime;
	@Schema(description = "答题时间")
	private Date answerDate;
	@Schema(description = "回答程度")
	private CompleteLevel level;
	@Schema(description = "回答合集")
	private List<RespondResponse> respondResponses;
}
