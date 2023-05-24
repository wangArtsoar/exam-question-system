package com.xiaoyi.springsecurity.interfaces;

import com.xiaoyi.springsecurity.api.request.AnswerDetailsRequest;
import com.xiaoyi.springsecurity.api.request.ExamRequest;
import com.xiaoyi.springsecurity.api.response.AnswerDetailResponse;
import com.xiaoyi.springsecurity.api.response.AnswerExamResponse;
import com.xiaoyi.springsecurity.api.response.ExamResponse;
import com.xiaoyi.springsecurity.domain.examination.service.ExaminationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王艺翔
 * @description ExamApi
 * @date 2023/5/18 17:03
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github <a href="https://github.com/Tom-Collection">...</a>"
 */
@RestController
@RequestMapping("api/exam")
@RequiredArgsConstructor
@Tag(name = "试卷接口")
public class ExamApi {

	private final ExaminationService examinationService;

	@PostMapping("save")
	@Operation(summary = "创建试卷")
	@PreAuthorize("hasAuthority('teacher:create')")
	public ResponseEntity<ExamResponse> createExam(@RequestBody ExamRequest request) {
		return ResponseEntity.ok(examinationService.saveExam(request));
	}

	@PutMapping("findById{id}")
	@Operation(summary = "根据id查询试卷")
	public ResponseEntity<ExamResponse> findExamById(@PathVariable Integer id) {
		return ResponseEntity.ok(examinationService.findExamById(id));
	}

	@GetMapping("findList")
	@Operation(summary = "试卷分页")
	public Page<ExamResponse> findExamList(
					@RequestParam(value = "page", defaultValue = "0") int page,
					@RequestParam(value = "size", defaultValue = "10") int size) {
		return examinationService.findExamList(PageRequest.of(page, size));
	}

	@PutMapping("answerById{id}")
	@Operation(summary = "答卷本")
	public ResponseEntity<AnswerExamResponse> queryAnswerExam(@PathVariable Integer id) {
		return ResponseEntity.ok(examinationService.startAnswerById(id));
	}

	@PostMapping("checkPaper")
	@Operation(summary = "查卷")
	public ResponseEntity<AnswerDetailResponse> checkPaper(@RequestBody AnswerDetailsRequest request) {
		return ResponseEntity.ok(examinationService.checkPaper(request));
	}
}
