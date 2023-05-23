package com.xiaoyi.springsecurity.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王艺翔
 * @description Couse
 * @date 2023/5/18 23:38
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_course")
public class Course {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@OneToOne
	private User teacher;
}
