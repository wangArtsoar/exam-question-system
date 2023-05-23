package com.xiaoyi.springsecurity.domain.examination.repo;

import com.xiaoyi.springsecurity.domain.examination.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author 王艺翔
 * @description ExaminationRepo
 * @date 2023/5/18 11:51
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
public interface ExaminationRepo extends
				PagingAndSortingRepository<Examination, Integer>,
				JpaRepository<Examination, Integer> {
}
