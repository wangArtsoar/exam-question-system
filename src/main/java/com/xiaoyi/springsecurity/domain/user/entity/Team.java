package com.xiaoyi.springsecurity.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description Team
 * @date 2023/5/18 22:56
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_team")
public class Team {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	@Enumerated(EnumType.STRING)
	private Grade grade;
	@OneToMany
	@JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private List<Course> courses;
	@OneToMany
	@JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private List<User> students;
	@OneToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private User headTeacher;
}
