<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${board.id}">
		<div class="form-group">
			<label for="title"><b>제목</b></label> <input type="text" class="form-control" id="title" value="${board.title}">
		</div>
		<div class="form-group">
			<label for="username"><b>작성자</b></label> <input type="text" class="form-control" id="username" value="${board.user.username}" readonly>
		</div>
		<div class="form-group">
			<label for="content"><b>내용</b></label> <textarea class="form-control summernote" rows="5" id="content" style="resize: none;">${board.content}</textarea>
		</div>
	</form>
	<button id="btn-update" class="btn btn-dark">게시글 수정</button>
</div>
<script>
$('.summernote').summernote({
	tabsize: 2,
	height: 300
});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>