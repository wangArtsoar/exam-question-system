package com.xiaoyi.springsecurity.domain.examination.service;

import com.xiaoyi.springsecurity.api.request.ExamRequest;
import com.xiaoyi.springsecurity.api.response.*;
import com.xiaoyi.springsecurity.domain.examination.entity.Examination;
import com.xiaoyi.springsecurity.domain.examination.repo.ExaminationRepo;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Question;
import com.xiaoyi.springsecurity.domain.question_bank.repo.OptionRepo;
import com.xiaoyi.springsecurity.domain.question_bank.serivce.QuestionService;
import com.xiaoyi.springsecurity.domain.user.entity.User;
import com.xiaoyi.springsecurity.domain.user.repo.UserRepo;
import com.xiaoyi.springsecurity.infrastructure.common.Utils;
import com.xiaoyi.springsecurity.infrastructure.config.JwtUtils;
import com.xiaoyi.springsecurity.infrastructure.exception.CreateFailedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private final OptionRepo optionRepo;
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
			// 转换ExamResponse
			ExamResponse examResponse = new ExamResponse();
			BeanUtils.copyProperties(examination, examResponse);
			manager.commit(status);
			return examResponse;
		} catch (Exception e) {
			manager.rollback(status);
			throw new CreateFailedException("添加失败" + e);
		}
	}

	@Override
	public ExamResponse findExamById(Integer id) {
		return null;
	}

	@Override
	public Page<ExamResponse> findExamList(Pageable pageable) {
		return examinationRepo.findAll(pageable).map(examination -> ExamResponse.builder().build());
	}

	@Override
	public AnswerExamResponse startAnswerById(Integer id) {
		// 获取user
		String jwt = Utils.getJwt(request.getHeader("Authentication"));
		User user = userRepo.findByEmail(jwtUtils.extractUserEmail(jwt)).orElseThrow();
		// 查询exam
		Examination examination = examinationRepo.findById(id).orElseThrow();
		// 设置值
		Double totalScore = null;
		List<AnswerQuestionResponse> list = new ArrayList<>();
		for (Question question : examination.getQuestions()) {
			List<OptionResponse> optionResponses = Utils.toOptionResponseList(
							optionRepo.findByQuestionId(question.getId()));
			list.add(AnswerQuestionResponse.builder()
							.score(question.getScore())
							.topic(question.getTopic())
							.type(question.getType())
							.optionResponses(optionResponses)
							.build());
			totalScore += question.getScore();
		}
		return AnswerExamResponse.builder()
						.answerQuestionResponses(list)
						.limitTime(examination.getLimitedTime())
						.username(user.getName())
						.totalScore(totalScore)
						.name(examination.getName())
						.build();
	}
}
