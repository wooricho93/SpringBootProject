<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="username">아이디</label> <input type="text" class="form-control" id="username" value="${principal.user.username}" readonly>
		</div>
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password" class="form-control" id="password" >
			</div>
		</c:if>
		<div class="form-group">
			<label for="email">이메일</label> <input type="email" class="form-control" id="email" value="${principal.user.email}">
		</div>
	</form>
	<button id="btn-update" class="btn btn-dark">수정하기</button>
	<button id="btn-delete" class="btn btn-danger" onclick="index.deleteUser(${principal.user.id})">회원 탈퇴</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>