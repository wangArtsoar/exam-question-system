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
 * @description Question
 * @date 2023/5/16 13:38
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_question")
public class Question {
	@Id
	@GeneratedValue
	private Integer id;
	private String topic;
	private String answer;
	private String answer_explain;
	private Integer type;
	private Double score;
}
