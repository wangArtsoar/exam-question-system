package com.xiaoyi.springsecurity.domain.examination.service;

import com.xiaoyi.springsecurity.api.request.ExamRequest;
import com.xiaoyi.springsecurity.api.response.ExamResponse;

/**
 * @author 王艺翔
 * @description ExaminationService
 * @date 2023/5/18 17:02
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public interface ExaminationService {
	ExamResponse saveExam(ExamRequest request);
}
