package com.xiaoyi.springsecurity.domain.question_bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
@Builder
@Table(name = "_question")
public class Question {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(columnDefinition = "TEXT")
	private String topic;
	@Column(columnDefinition = "TEXT")
	private String answer;
	@Column(columnDefinition = "TEXT")
	private String answerExplain;
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	@Enumerated(EnumType.ORDINAL)
	private Difficulty difficulty;
	private Double score;
	@OneToMany
	@JoinColumn(name = "questionId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private List<Option> options;
}
