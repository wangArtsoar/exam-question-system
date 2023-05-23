package com.xiaoyi.springsecurity.domain.user.service;

import com.xiaoyi.springsecurity.api.request.CourseRequest;
import com.xiaoyi.springsecurity.api.request.RegisterRequest;
import com.xiaoyi.springsecurity.api.request.TeamRequest;
import com.xiaoyi.springsecurity.api.response.TeamResponse;
import com.xiaoyi.springsecurity.api.response.UserResponse;
import com.xiaoyi.springsecurity.domain.user.entity.Course;
import com.xiaoyi.springsecurity.domain.user.entity.Team;
import com.xiaoyi.springsecurity.domain.user.entity.User;
import com.xiaoyi.springsecurity.domain.user.repo.CourseRepo;
import com.xiaoyi.springsecurity.domain.user.repo.TeamRepo;
import com.xiaoyi.springsecurity.domain.user.repo.UserRepo;
import com.xiaoyi.springsecurity.infrastructure.config.JwtUtils;
import com.xiaoyi.springsecurity.infrastructure.exception.CreateFailedException;
import com.xiaoyi.springsecurity.infrastructure.exception.EmailAlreadyExistedException;
import com.xiaoyi.springsecurity.infrastructure.exception.EmailNotFoundException;
import com.xiaoyi.springsecurity.infrastructure.exception.JoinException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王艺翔
 * @description UserServiceImpl
 * @date 2023/5/11 20:27
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/Tom-Collection"
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final JwtUtils jwtUtils;
	private final UserRepo userRepo;
	private final CourseRepo courseRepo;
	private final TeamRepo teamRepo;
	private final PasswordEncoder passwordEncoder;
	private final RedisTemplate<String, Object> redisTemplate;
	private final AuthenticationManager manager;
	private final PlatformTransactionManager transactionManager;


	@Override
	public UserResponse findByUserEmail(String email) {
		User user = userRepo.findByEmail(email)
						.orElseThrow(() -> new EmailNotFoundException("Email not found: " + email));
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}

	@Override
	public List<UserResponse> findAllUser() {
		return userRepo.findAll().stream().map(
						user -> UserResponse.builder().name(user.getName()).email(user.getEmail())
										.role(user.getRole()).build()).toList();
	}

	@Override
	public UserResponse save(RegisterRequest request) {
		if (userRepo.findByEmail(request.getEmail()).isPresent()) {
			throw new EmailAlreadyExistedException("the email has existed");
		}
		User save = userRepo.save(User.builder()
						.name(request.getUsername())
						.email(request.getEmail())
						.pwd(passwordEncoder.encode(request.getPassword()))
						.role(request.getRole()).build());
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(save, userResponse);
		return userResponse;
	}

	@Override
	public String applyDelUser(String email, String password) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		if (Boolean.TRUE.equals(redisTemplate.hasKey(email))) {
			return "申请成功";
		}
		return "申请失败";
	}

	@Override
	public String joinTeam(Integer teamId, HttpServletRequest request) {
		// 获取jwt token
		String authHeader = request.getHeader("Authentication");
		final String jwt;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new JoinException("加入失败");
		}
		// 获取email
		jwt = authHeader.substring(7);
		String email = jwtUtils.extractUserEmail(jwt);
		// 查询team（班级）
		Team team = teamRepo.findById(teamId).orElseThrow();
		// 获取user
		User user = userRepo.findByEmail(email).orElseThrow();
		List<User> students = team.getStudents();
		if (students.get(user.getId()) != null) {
			return "您已加入该班级";
		}
		students.set(user.getId(), user);
		teamRepo.save(team);
		return "加入成功";
	}

	@Override
	public void createTeam(TeamRequest request) {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			List<Course> courseList = toCourseList(request.getCourses());
			courseRepo.saveAll(courseList);
			User user = userRepo.findByEmail(request.getHeadTeacherEmail()).orElseThrow();
			var team = Team.builder()
							.name(request.getName())
							.specialty(request.getSpecialty())
							.grade(request.getGrade())
							.headTeacher(user).build();
			teamRepo.save(team);
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new CreateFailedException("添加失败" + e);
		}
	}

	@Override
	public TeamResponse getTeamById(Integer teamId) {
		Team team = teamRepo.findById(teamId).orElseThrow();
		TeamResponse teamResponse = new TeamResponse();
		BeanUtils.copyProperties(team, teamResponse);
		return teamResponse;
	}

	private List<Course> toCourseList(List<CourseRequest> courses) {
		List<Course> courseList = new ArrayList<>();
		courses.forEach(courseRequest -> {
			String email = courseRequest.getTeacherEmail();
			userRepo.findByEmail(email).ifPresent(
							user -> courseList.add(Course.builder().name(courseRequest.getName()).teacher(user).build()));
		});
		return courseList;
	}

}
