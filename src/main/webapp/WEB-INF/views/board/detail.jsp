<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form>
		<div class="form-group">
			<label for="title"><b>제목</b></label> <input type="text" class="form-control" id="title" value="${board.title}" readonly>
		</div>
		<div class="form-group">
			<label for="username"><b>작성자</b></label> <input type="text" class="form-control" id="username" value="${board.user.username}" readonly>
		</div>
		<hr>
		<div class="form-group">
			<label for="content"><b>내용</b></label> <div style="height: 200%;">${board.content}</div>
		</div>
		<hr>
	</form>
	<br>
	<div align="center">
		<button id="btn-list" class="btn btn-outline-dark" onclick="history.back()">게시글 목록</button>
		<c:if test="${board.user.username eq principal.user.username}">
			<a href="/board/${board.id}/updateForm" class="btn btn-dark">게시글 수정</a>
			<button id="btn-delete" class="btn btn-danger">게시글 삭제</button>
		</c:if>
	</div>
	<div>
		<input type="hidden" id="id" name="id" value="${board.id}">
		<input type="hidden" id="userId" name="userId" value="${principal.user.id}">
	</div>
	<br>
	<div class="card">
		<div class="card-header"><b>댓글 목록</b></div>
		<c:choose>
			<c:when test="${empty board.reply}">
				<ul class="list-group" id="reply_box">
					<li class="list-group-item d-flex justify-content-center" id="reply">
						<div>작성된 댓글이 없습니다. 새로운 댓글을 남겨주세요.</div>
					</li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="list-group" id="reply_box">
					<c:forEach var="reply" items="${board.reply}">
						<li class="list-group-item d-flex justify-content-between align-items-center" id="reply_${reply.id}">
							<div class="d-flex">
								<div><b>${reply.user.username}&nbsp;&nbsp;&nbsp;</b></div>
								<div>${reply.content}</div>
							</div>
							<div class="d-flex">
								<div class="font-italic" style="font-size: 14px;"><fmt:formatDate value="${reply.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<c:if test="${reply.user.username eq principal.user.username}">
									&nbsp;&nbsp;&nbsp;
									<button class="badge" style="border: none;">수정</button>&nbsp;
									<button class="badge badge-danger" onclick="index.replyDelete(${board.id}, ${reply.id})" style="border: none;">삭제</button>
								</c:if>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
		
	</div>
	<br>
	<div class="card">
		<div class="card-body d-flex align-items-center"><b>${principal.user.username}&nbsp;&nbsp;&nbsp;</b><textarea class="form-control" id="comment" rows="1" style="resize: none;"></textarea></div>
		<div class="card-footer"><button class="btn btn-dark" id="btn-reply-save" style="float: right;">댓글 등록</button></div>
	</div>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>