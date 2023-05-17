package com.xiaoyi.springsecurity.domain.question_bank.serivce;

import com.xiaoyi.springsecurity.api.request.QuestionRequest;
import com.xiaoyi.springsecurity.api.response.QuestionResponse;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Option;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Question;
import com.xiaoyi.springsecurity.domain.question_bank.repo.OptionRepo;
import com.xiaoyi.springsecurity.domain.question_bank.repo.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.xiaoyi.springsecurity.infrastructure.GlobalEnum.SHORT_ANSWER;

/**
 * @author 王艺翔
 * @description QuestionServiceImpl
 * @date 2023/5/16 18:12
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private final QuestionRepo questionRepo;
	private final OptionRepo optionRepo;
	private final PlatformTransactionManager transactionManager;

	@Override
	public QuestionResponse saveQuestion(QuestionRequest request) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		var question = Question.builder()
						.answer(request.getAnswer())
						.topic(request.getTopic())
						.answerExplain(request.getAnswerExplain())
						.score(request.getScore())
						.type(request.getType()).build();
		try {
			QuestionResponse questionResponse = new QuestionResponse();
			BeanUtils.copyProperties(questionRepo.save(question), questionResponse);
			if (Objects.equals(request.getType(), SHORT_ANSWER.getData())) {
				transactionManager.commit(transactionStatus);
				return questionResponse;
			}
			List<Option> options = optionRepo.saveAll(
							request.getOptions()
											.stream()
											.peek(option -> option.setQuestionId(question.getId()))
											.collect(Collectors.toList()));
			questionResponse.setOptions(options);
			transactionManager.commit(transactionStatus);
			return questionResponse;
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
		}
		return null;
	}

	@Override
	public List<QuestionResponse> findAllQuestion() {
		return questionRepo.findAll()
						.stream()
						.map(question -> {
							QuestionResponse questionResponse = new QuestionResponse();
							BeanUtils.copyProperties(question, questionResponse);
							questionResponse.setOptions(optionRepo.findByQuestionId(question.getId()));
							return questionResponse;
						}).toList();
	}
}
