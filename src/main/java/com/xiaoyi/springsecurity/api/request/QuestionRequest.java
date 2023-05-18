package com.xiaoyi.springsecurity.api.request;

import com.xiaoyi.springsecurity.domain.question_bank.entity.Option;
import com.xiaoyi.springsecurity.domain.question_bank.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description QuestionRequest
 * @date 2023/5/16 18:16
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
	//	题目
	private String topic;
	//	答案
	private String answer;
	//	答案解释
	private String answerExplain;
	//	类型（0-简答题，1-单选题，2-多选题，3-判断题）
	private QuestionType type;
	//	分数
	private Double score;
	//	选项
	private List<Option> options;
}
