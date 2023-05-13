package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

/* 스프링에서 컴포넌트 스캔을 통해 Bean에 등록(IoC) */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public void join(User user) {
		String rawPass = user.getPassword();
		String encPass = passwordEncoder.encode(rawPass);
		user.setPassword(encPass);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
//	@Transactional(readOnly = true) // select 시 트랜잭션 시작 ~ 서비스 종료 시 트랜잭션 종료(정합성 유지를 위해)
//	public User login(User user) {
//		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
//	}
	
	@Transactional(readOnly = true)
	public User findUser(String username) {
		User user = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return user;
	}
	
	@Transactional
	public void update(User user) {
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 정보를 조회하는 데 실패했습니다.");
		});
		
		/* Validation 체크 */
		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPass = user.getPassword();
			String encPass = passwordEncoder.encode(rawPass);
			persistance.setPassword(encPass);
		}
		
		persistance.setEmail(user.getEmail());
	}
	
	@Transactional
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}
}
