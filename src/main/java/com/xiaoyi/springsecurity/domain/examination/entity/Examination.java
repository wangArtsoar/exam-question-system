package com.xiaoyi.springsecurity.domain.examination.entity;

import com.xiaoyi.springsecurity.domain.question_bank.entity.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 王艺翔
 * @description examination
 * @date 2023/5/18 11:18
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_examination")
public class Examination {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@Column(columnDefinition = "TEXT")
	private String description;
	private Long limitedTime; // 1000 * 60 * 60 * 1.2 = 120min
	private String author;
	private Date createTime;
	private Double difficulty;
	@OneToMany
	@JoinColumn(name = "examinationId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private List<Question> questions;
}
