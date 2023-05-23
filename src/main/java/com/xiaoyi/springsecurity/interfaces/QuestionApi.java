package com.xiaoyi.springsecurity.interfaces;

import com.xiaoyi.springsecurity.api.request.QuestionRequest;
import com.xiaoyi.springsecurity.api.response.QuestionResponse;
import com.xiaoyi.springsecurity.domain.question_bank.serivce.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王艺翔
 * @description QuestionApi
 * @date 2023/5/16 18:13
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/question")
@Tag(name = "问题接口")
public class QuestionApi {

	private final QuestionService questionService;

	@PostMapping("create")
	@Operation(summary = "创建问题")
	@PreAuthorize("hasAuthority('teacher:create')")
	public ResponseEntity<List<QuestionResponse>> saveQuestion(@RequestBody List<QuestionRequest> requests) {
		return ResponseEntity.ok(questionService.saveQuestion(requests));
	}

	@GetMapping("find")
	@Operation(summary = "查询问题")
	public ResponseEntity<List<QuestionResponse>> findAllQuestion() {
		return ResponseEntity.ok(questionService.findAllQuestion());
	}
}
