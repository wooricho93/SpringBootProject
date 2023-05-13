package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

/* Bean 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 */
@Configuration
@EnableWebSecurity // Security 필터 적용 -> 활성화된 스프링 시큐리티의 현재 파일에서 설정
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한 및 인증을 사전 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean // IoC 적용
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// crsf 토큰 비활성화
		http.csrf().disable().authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/loginForm")
				   .loginProcessingUrl("/auth/login") // 해당 주소로 로그인 요청이 들어오면 스프링 시큐리티가 가로채서 대신 로그인 작업 수행
				   .defaultSuccessUrl("/");
	}
}
