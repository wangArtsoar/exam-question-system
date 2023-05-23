package com.xiaoyi.springsecurity.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 王艺翔
 * @description ExamResponse 试卷
 * @date 2023/5/18 22:45
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponse {
	@Schema(description = "名称")
	private String name;
	@Schema(description = "简介")
	private String description;
	@Schema(description = "限时")
	private Long limitedTime; // 1000 * 60 * 60 * 1.2 = 120min
	@Schema(description = "作者")
	private String author;
	@Schema(description = "创建时间")
	private Date createTime;
	@Schema(description = "题目合集")
	private List<QuestionResponse> questions;
}
