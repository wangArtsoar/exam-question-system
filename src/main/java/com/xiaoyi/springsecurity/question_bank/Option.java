package com.xiaoyi.springsecurity.question_bank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description Options
 * @date 2023/5/16 13:46
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_option")
public class Option {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer questionId;
	private String content;
}
