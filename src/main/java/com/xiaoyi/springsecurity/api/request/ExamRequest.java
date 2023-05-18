package com.xiaoyi.springsecurity.api.request;

import com.xiaoyi.springsecurity.domain.question_bank.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description ExamRequest
 * @date 2023/5/18 22:45
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequest {
	private String name;
	private String description;
	private Long limitedTime; // 1000 * 60 * 60 * 1.2 = 120min
	private String author;
	private List<Question> questions;
}
