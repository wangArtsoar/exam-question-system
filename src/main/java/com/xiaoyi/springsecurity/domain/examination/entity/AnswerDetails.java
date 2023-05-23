package com.xiaoyi.springsecurity.domain.examination.entity;

import com.xiaoyi.springsecurity.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王艺翔
 * @description AnswerSheetDetails
 * @date 2023/5/18 11:24
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_answerDetails")
public class AnswerDetails {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private User user;
	private Long compactTime; // 答题时间
	@Enumerated(EnumType.STRING)
	private CompleteLevel level;
	@OneToMany
	@JoinColumn(name = "answer_details_id",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	private List<Respond> responds;
}
