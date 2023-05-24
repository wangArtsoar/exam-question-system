package com.xiaoyi.springsecurity.domain.examination.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 王艺翔
 * @description ComplateLevel
 * @date 2023/5/19 18:20
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RequiredArgsConstructor
public enum CompleteLevel {
	EXCELLENT(90),
	GOOD(75),
	PASS(60),
	FAIL(0);

	@Getter
	private final double number;
}
