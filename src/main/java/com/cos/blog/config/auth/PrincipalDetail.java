package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

/* Spring Security가 로그인 요청을 가로채서 로그인 진행 후 완료가 되면 UserDetails 타입의 객체를 Spring Security 고유 세션저장소에 저장 */
@Getter
public class PrincipalDetail implements UserDetails {
	private User user; // composition
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	/* 계정 만료 여부 확인 (true: 만료 X) */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/* 계정 잠금 여부 확인 (true: 해제) */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/* 비밀번호 만료 여부 확인 (true: 만료 X) */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* 계정 활성화 여부 확인 (true: 활성화) */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	/* 계정의 권환 목록 반환 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_" + user.getRole();
//			}
//		});
		
		/* 람다식 활용 */
		collectors.add(() -> {return "ROLE_" + user.getRole();});
		
		return collectors;
	}
}
