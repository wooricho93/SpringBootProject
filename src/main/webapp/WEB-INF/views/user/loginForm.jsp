<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form action="/auth/login" method="post">
		<div class="form-group">
			<label for="username">아이디</label> <input type="text" class="form-control" id="username" name="username">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password" class="form-control" id="password" name="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>
		<button id="btn-login" class="btn btn-dark">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=d84bf8027dbf037d037493b3bc838c8b&redirect_uri=http://localhost:8002/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png"></a>
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>