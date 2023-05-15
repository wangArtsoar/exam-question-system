package com.xiaoyi.springsecurity.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * @author 王艺翔
 * @description GlobleExceptionHandle
 * @date 2023/5/15 14:41
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = EmailAlreadyExistedException.class)
	@ResponseBody
	public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistedException e) {
		return ResponseEntity.status(CONFLICT).body(e.getMessage());
	}
}

