package com.xiaoyi.springsecurity.domain.examination.service;

import com.xiaoyi.springsecurity.api.request.ExamRequest;
import com.xiaoyi.springsecurity.api.response.AnswerExamResponse;
import com.xiaoyi.springsecurity.api.response.AnswerQuestionResponse;
import com.xiaoyi.springsecurity.api.response.ExamResponse;
import com.xiaoyi.springsecurity.api.response.QuestionResponse;
import com.xiaoyi.springsecurity.domain.examination.entity.Examination;
import com.xiaoyi.springsecurity.domain.examination.repo.ExaminationRepo;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Question;
import com.xiaoyi.springsecurity.domain.question_bank.serivce.QuestionService;
import com.xiaoyi.springsecurity.domain.user.entity.User;
import com.xiaoyi.springsecurity.domain.user.repo.UserRepo;
import com.xiaoyi.springsecurity.infrastructure.common.Utils;
import com.xiaoyi.springsecurity.infrastructure.config.JwtUtils;
import com.xiaoyi.springsecurity.infrastructure.exception.CreateFailedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description ExaminationServiceImpl
 * @date 2023/5/18 17:02
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {

	private final JwtUtils jwtUtils;
	private final UserRepo userRepo;
	private final ExaminationRepo examinationRepo;
	private final QuestionService questionService;
	private final PlatformTransactionManager manager;
	private final HttpServletRequest request;


	@Override
	public ExamResponse saveExam(ExamRequest request) {
		TransactionStatus status = manager.getTransaction(new DefaultTransactionDefinition());
		try {
			// 保存问题
			List<QuestionResponse> questionResponses = questionService.saveQuestion(request.getQuestions());
			// 提取难度
			double sum = 0;
			for (QuestionResponse response : questionResponses) sum += response.getDifficulty().getNumber();
			// 保存试卷
			Examination examination = examinationRepo.save(Examination.builder()
							.name(request.getName())
							.description(request.getDescription())
							.author(request.getAuthor())
							.limitedTime(request.getLimitedTime())
							.createTime(new Date())
							.difficulty(sum / questionResponses.size())
							.build());
			// 转换为ExamResponse
			ExamResponse examResponse = toExamResponse(examination);
			manager.commit(status);
			return examResponse;
		} catch (Exception e) {
			manager.rollback(status);
			throw new CreateFailedException("添加失败" + e);
		}
	}

	private ExamResponse toExamResponse(Examination examination) {
		return ExamResponse.builder()
						.name(examination.getName())
						.limitedTime(examination.getLimitedTime())
						.questions(examination.getQuestions()
										.stream().map(question -> QuestionResponse.builder()
														.topic(question.getTopic())
														.score(question.getScore())
														.type(question.getType())
														.difficulty(question.getDifficulty())
														.answer(question.getAnswer())
														.answerExplain(question.getAnswerExplain())
														.options(Utils.toOptionResponseList(question.getOptions()))
														.build())
										.collect(Collectors.toList()))
						.author(examination.getAuthor())
						.description(examination.getDescription())
						.createTime(examination.getCreateTime())
						.build();
	}

	@Override
	public ExamResponse findExamById(Integer id) {
		return toExamResponse(examinationRepo.findById(id).orElseThrow());
	}

	@Override
	public Page<ExamResponse> findExamList(Pageable pageable) {
		return examinationRepo.findAll(pageable).map(this::toExamResponse);
	}

	@Override
	public AnswerExamResponse startAnswerById(Integer id) {
		String jwt = Utils.getJwt(request.getHeader("Authorization"));
		User user = userRepo.findByEmail(jwtUtils.extractUserEmail(jwt)).orElseThrow();
		Examination examination = examinationRepo.findById(id).orElseThrow();
		return toAnswerExamResponse(examination, user);
	}

	private AnswerExamResponse toAnswerExamResponse(Examination examination, User user) {
		Double totalScore = null;
		for (Question question : examination.getQuestions()) totalScore += question.getScore();
		return AnswerExamResponse.builder()
						.answerQuestionResponses(examination.getQuestions().stream()
										.map(question -> AnswerQuestionResponse.builder()
														.topic(question.getTopic())
														.score(question.getScore())
														.type(question.getType())
														.optionResponses(Utils.toOptionResponseList(question.getOptions()))
														.build())
										.collect(Collectors.toList()))
						.limitTime(examination.getLimitedTime())
						.username(user.getName())
						.totalScore(totalScore)
						.name(examination.getName()).build();
	}
}
