package com.xiaoyi.springsecurity.domain.question_bank.serivce;

import com.xiaoyi.springsecurity.api.request.QuestionRequest;
import com.xiaoyi.springsecurity.api.response.QuestionResponse;

import java.util.List;

/**
 * @author 王艺翔
 * @description QuestionService
 * @date 2023/5/16 18:12
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public interface QuestionService {
	List<QuestionResponse> saveQuestion(List<QuestionRequest> requests);

	List<QuestionResponse> findAllQuestion();
}
