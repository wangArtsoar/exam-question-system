package com.xiaoyi.springsecurity.infrastructure.common;

import com.xiaoyi.springsecurity.api.response.OptionResponse;
import com.xiaoyi.springsecurity.domain.question_bank.entity.Option;
import com.xiaoyi.springsecurity.infrastructure.exception.JoinException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王艺翔
 * @description Utils
 * @date 2023/5/23 14:42
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public class Utils {

	public static List<OptionResponse> toOptionResponseList(List<Option> options) {
		return options
						.stream()
						.map(option -> OptionResponse.builder()
										.content(option.getContent())
										.isTure(option.isTrue())
										.build())
						.collect(Collectors.toList());
	}

	public static String getJwt(String authHeader) {
		final String jwt;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new JoinException("加入失败");
		}
		// 获取email
		return authHeader.substring(7);
	}
}
