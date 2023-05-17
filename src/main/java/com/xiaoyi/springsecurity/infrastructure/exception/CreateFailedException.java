package com.xiaoyi.springsecurity.infrastructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 王艺翔
 * @description CreateFailedException
 * @date 2023/5/17 19:33
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RequiredArgsConstructor
public class CreateFailedException extends RuntimeException {
	@Getter
	private final String message;
}
