package com.xiaoyi.springsecurity.domain.examination.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description AnswerDetail
 * @date 2023/5/18 11:29
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_respond")
public class Respond {
	@Id
	@GeneratedValue
	private Integer id;
	private String respond;
	private Integer questionId;
}
