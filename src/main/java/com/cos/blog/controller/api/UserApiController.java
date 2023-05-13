package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/join")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("save 호출");
		userService.join(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value() , 1); // Java object를 json으로 변환하여 리턴(Jackson)
	}
	
	/* 과거의 로그인 방식 */
//	@PostMapping("/api/user/login")
//	public ResponseDTO<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("login 호출");
//		User principal = userService.login(user); // principal = 접근주체
//		
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDTO<Integer>(HttpStatus.OK.value() , 1);
//	}
	
	@PutMapping("/user")
	public ResponseDTO<Integer> update(@RequestBody User user) {
		userService.update(user);
		
		/* 세션 등록 */
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDTO<Integer>(HttpStatus.OK.value() , 1);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseDTO<Integer> userDelete(@PathVariable int userId) {
		userService.deleteUser(userId);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
}
