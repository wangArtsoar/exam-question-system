package com.xiaoyi.springsecurity.infrastructure.common;

import com.xiaoyi.springsecurity.api.request.QuestionRequest;
import com.xiaoyi.springsecurity.api.response.OptionResponse;
import com.xiaoyi.springsecurity.api.response.QuestionResponse;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Option;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Question;
import com.xiaoyi.springsecurity.infrastructure.exception.JoinException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description Utils
 * @date 2023/5/23 14:42
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public class Utils {

	public static List<OptionResponse> toOptionResponseList(List<Option> options) {
		return options
						.stream()
						.map(option -> OptionResponse.builder()
										.content(option.getContent())
										.isTrue(option.isTrue())
										.build())
						.collect(Collectors.toList());
	}

	public static String getJwt(String authHeader) {
		final String jwt;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new JoinException("加入失败");
		}
		// 获取email
		return authHeader.substring(7);
	}

	public static <T> List<Question> createQuestionList(List<T> tList) {
		return tList.stream().map(t -> {
			Question question;
			if (t instanceof QuestionRequest) question = requestToQuestion((QuestionRequest) t);
			else question = responseToQuestion((QuestionResponse) t);
			return question;
		}).toList();
	}

	private static Question requestToQuestion(QuestionRequest request) {
		return Question.builder()
						.topic(request.getTopic())
						.answer(request.getAnswer())
						.answerExplain(request.getAnswerExplain())
						.score(request.getScore())
						.type(request.getType())
						.difficulty(request.getDifficulty())
						.build();
	}

	private static Question responseToQuestion(QuestionResponse response) {
		return Question.builder()
						.id(response.getQuestionId())
						.topic(response.getTopic())
						.answer(response.getAnswer())
						.answerExplain(response.getAnswerExplain())
						.score(response.getScore())
						.type(response.getType())
						.difficulty(response.getDifficulty())
						.build();
	}
}
