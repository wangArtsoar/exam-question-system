package com.xiaoyi.springsecurity.domain.question_bank.serivce;

import com.xiaoyi.springsecurity.api.request.QuestionRequest;
import com.xiaoyi.springsecurity.api.response.QuestionResponse;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Option;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Question;
import com.xiaoyi.springsecurity.domain.question_bank.repo.OptionRepo;
import com.xiaoyi.springsecurity.domain.question_bank.repo.QuestionRepo;
import com.xiaoyi.springsecurity.infrastructure.common.Utils;
import com.xiaoyi.springsecurity.infrastructure.exception.CreateFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

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
	private final PlatformTransactionManager manager;

	@Override
	public List<QuestionResponse> saveQuestion(List<QuestionRequest> requests) {
		// 事务管理
		TransactionStatus status = manager.getTransaction(new DefaultTransactionDefinition());
		try {
			// 创建question
			List<Question> questions = createQues(requests);
			// 保存Options
			/*List<Option> options = new ArrayList<>();
			Map<Integer, List<Option>> map = new HashMap<>();
			for (int i = 0; i < requests.size(); i++) {
				Question question = questions.get(i);
				QuestionRequest request = requests.get(i);
				if (!Objects.equals(request.getType(), SHORT_ANSWER)) {
					var list = request.getOptions().stream().peek(option -> option.setQuestion(question)).toList();
					options.addAll(list);
					map.put(question.getId(), list);
				}
			}
			// 添加选项
			optionRepo.saveAll(options);*/
			manager.commit(status);
			return toQuestionResponse(questions);
		} catch (Exception e) {
			manager.rollback(status);
			throw new CreateFailedException("添加失败" + e);
		}
	}

	private List<QuestionResponse> toQuestionResponse(List<Question> questions) {
		return questions.stream()
						.map(question -> {
							QuestionResponse questionResponse = new QuestionResponse();
							BeanUtils.copyProperties(question, questionResponse);
							List<Option> options = optionRepo.findByQuestionId(question.getId());
							questionResponse.setOptions(Utils.toOptionResponseList(options));
							return questionResponse;
						}).toList();
	}

	private List<Question> createQues(List<QuestionRequest> requests) {
		var list = requests.stream().map(request -> Question.builder()
										.topic(request.getTopic())
										.answer(request.getAnswer())
										.answerExplain(request.getAnswerExplain())
										.score(request.getScore())
										.type(request.getType())
										.difficulty(request.getDifficulty())
										.options(request.getOptions())
										.build())
						.toList();
		return questionRepo.saveAll(list);
	}

	@Override
	public List<QuestionResponse> findAllQuestion() {
		return toQuestionResponse(questionRepo.findAll());
	}
}
