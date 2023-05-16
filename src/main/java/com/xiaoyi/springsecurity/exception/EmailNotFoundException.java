package com.xiaoyi.springsecurity.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 王艺翔
 * @description EmailNotFoundException
 * @date 2023/5/15 17:29
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RequiredArgsConstructor
public class EmailNotFoundException extends RuntimeException {

	@Getter
	private final String message;
}
