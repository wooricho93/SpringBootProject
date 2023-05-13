package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	/* Spring Security가 로그인 요청을 가로챌 때 password는 자동으로 처리하지만, username의 경우에는 개발자가 스스로 처리(DB에 존재하는지 확인) */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(() -> {
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
		});
		return new PrincipalDetail(principal); // Security 세션에 유저 정보 저장
	}
	
}
