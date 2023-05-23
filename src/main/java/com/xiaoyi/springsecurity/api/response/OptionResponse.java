package com.xiaoyi.springsecurity.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description OptionResponse 选项
 * @date 2023/5/19 18:26
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {
	private String content;
	private boolean isTure;
}
