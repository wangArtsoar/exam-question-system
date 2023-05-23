package com.xiaoyi.springsecurity.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "选项内容")
	private String content;
	@Schema(description = "是否正确")
	private boolean isTure;
}
