package com.xiaoyi.springsecurity.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 王艺翔
 * @description EmailAlreadyExistedException
 * @date 2023/5/15 14:43
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */

@RequiredArgsConstructor
public class EmailAlreadyExistedException extends RuntimeException {

	@Getter
	private final String message;
}
