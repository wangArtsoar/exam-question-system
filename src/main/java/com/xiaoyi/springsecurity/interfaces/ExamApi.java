package com.xiaoyi.springsecurity.interfaces;

import com.xiaoyi.springsecurity.api.request.ExamRequest;
import com.xiaoyi.springsecurity.api.response.ExamResponse;
import com.xiaoyi.springsecurity.domain.examination.service.ExaminationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王艺翔
 * @description ExamApi
 * @date 2023/5/18 17:03
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RestController
@RequestMapping("api/exam")
@RequiredArgsConstructor
@Tag(name = "试卷接口")
@PreAuthorize("hasRole('TEACHER')")
public class ExamApi {

	private final ExaminationService examinationService;

	@PostMapping("save")
	@Operation(summary = "创建试卷")
	@PreAuthorize("hasAuthority('teacher:create')")
	public ResponseEntity<ExamResponse> createExam(@RequestBody ExamRequest request) {
		return ResponseEntity.ok(examinationService.saveExam(request));
	}
}
