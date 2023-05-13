package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/* 사용자의 요청에 응답(Html) -> @Controller */

/* 사용자의 요청에 응답(Data) */
@RestController
public class HttpControllerTest {
	// 인터넷 브라우저 요청은 무조건 get만 가능
	@GetMapping("/http/get")
	public String getTest(Member member) {
		return "get 요청: " + member.getId() + ", " + member.getUserName() + ", " + member.getPassword() + ", " + member.getEmail();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member member) {
		return "post 요청: " + member.getId() + ", " + member.getUserName() + ", " + member.getPassword() + ", " + member.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member member) {
		return "put 요청: " + member.getId() + ", " + member.getUserName() + ", " + member.getPassword() + ", " + member.getEmail() ;
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
