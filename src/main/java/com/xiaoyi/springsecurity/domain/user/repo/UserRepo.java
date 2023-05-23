package com.xiaoyi.springsecurity.domain.user.repo;

import com.xiaoyi.springsecurity.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author 王艺翔
 * @description UserRepo
 * @date 2023/5/10 17:54
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
