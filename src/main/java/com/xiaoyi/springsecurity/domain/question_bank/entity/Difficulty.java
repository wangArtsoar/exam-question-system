package com.xiaoyi.springsecurity.domain.question_bank.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 王艺翔
 * @description Difficulty
 * @date 2023/5/20 15:10
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@RequiredArgsConstructor
public enum Difficulty {
	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5);
	@Getter
	private final Integer number;
}
