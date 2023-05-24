package com.xiaoyi.springsecurity.domain.examination.service;

import com.xiaoyi.springsecurity.api.request.AnswerDetailsRequest;
import com.xiaoyi.springsecurity.api.request.ExamRequest;
import com.xiaoyi.springsecurity.api.response.*;
import com.xiaoyi.springsecurity.domain.examination.entity.CompleteLevel;
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
import java.util.Map;
import java.util.stream.Collectors;

import static com.xiaoyi.springsecurity.domain.examination.entity.CompleteLevel.*;

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
														.questionId(question.getId())
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

	@Override
	public AnswerDetailResponse checkPaper(AnswerDetailsRequest request) {
		String jwt = Utils.getJwt(this.request.getHeader("Authorization"));
		User user = userRepo.findByEmail(jwtUtils.extractUserEmail(jwt)).orElseThrow();
		Examination examination = examinationRepo.findById(request.getExamId()).orElseThrow();
		Map<Integer, String> answerQuestionMap = request.getAnswerQuestionMap();
		List<Question> questions = examination.getQuestions();
		Double totalScore = getTotalScore(questions, 0.0);
		List<QuestionResponse> questionResponses = toExamResponse(examination).getQuestions();
		// 对比答案
		double answerScore = 0;
		for (Question question : questions) {
			String answer = answerQuestionMap.get(question.getId());
			if (answer.equals(question.getAnswer())) answerScore += question.getScore();
		}
		// 判断答卷程度
		CompleteLevel completeLevel;
		if (answerScore >= EXCELLENT.getNumber()) {
			completeLevel = EXCELLENT;
		} else if (answerScore >= GOOD.getNumber()) {
			completeLevel = GOOD;
		} else if (answerScore >= PASS.getNumber()) {
			completeLevel = PASS;
		} else {
			completeLevel = FAIL;
		}
		return AnswerDetailResponse.builder()
						.answerDate(new Date(new Date().getTime() - request.getAnswerTime()))
						.answerTime(request.getAnswerTime())
						.level(completeLevel)
						.username(user.getName())
						.totalScore(totalScore)
						.respondResponses(questionResponses.stream()
										.map(questionResponse -> RespondResponse.builder()
														.topic(questionResponse.getTopic())
														.score(questionResponse.getScore())
														.answer(questionResponse.getAnswer())
														.answerExplain(questionResponse.getAnswerExplain())
														.respond(answerQuestionMap.get(questionResponse.getQuestionId()))
														.build())
										.collect(Collectors.toList()))
						.build();
	}

	private Double getTotalScore(List<Question> questions, double i) {
		for (Question question : questions) i += question.getScore();
		return i;
	}

	private AnswerExamResponse toAnswerExamResponse(Examination examination, User user) {
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
						.totalScore(getTotalScore(examination.getQuestions(), 0.0))
						.name(examination.getName()).build();
	}
}
