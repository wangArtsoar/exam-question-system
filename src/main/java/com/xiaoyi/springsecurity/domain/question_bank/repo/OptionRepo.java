package com.xiaoyi.springsecurity.domain.question_bank.repo;

import com.xiaoyi.springsecurity.domain.question_bank.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 王艺翔
 * @description OptionRepo
 * @date 2023/5/16 17:57
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public interface OptionRepo extends JpaRepository<Option, Integer> {
	List<Option> findByQuestionId(Integer questionId);
}
