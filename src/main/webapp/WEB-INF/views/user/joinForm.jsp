<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">아이디</label> <input type="text" class="form-control" placeholder="아이디를 입력해 주세요" id="username">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password" class="form-control" placeholder="비밀번호를 입력해 주세요" id="password">
		</div>
		<div class="form-group">
			<label for="email">이메일</label> <input type="email" class="form-control" placeholder="이메일을 입력해 주세요" id="email">
		</div>
	</form>
	<button id="btn-join" class="btn btn-dark">가입하기</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>