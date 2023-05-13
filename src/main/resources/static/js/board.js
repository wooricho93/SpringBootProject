let index = {
	init: function() {
		$("#btn-write").on("click", () => { // this를 바인딩하기 위해 사용
			this.save();
		});
		
		$("#btn-delete").on("click", () => { // this를 바인딩하기 위해 사용
			this.deleteById();
		});
		
		$("#btn-update").on("click", () => { // this를 바인딩하기 위해 사용
			this.update();
		});
		
		$("#btn-reply-save").on("click", () => { // this를 바인딩하기 위해 사용
			this.replySave();
		});
		
		/*$("#btn-login").on("click", () => { // this를 바인딩하기 위해 사용
			this.login();
		});*/
	},
	
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		console.log(data);
		
		/* ajax의 기본 호출: 비동기 호출 */
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=UTF-8", // body 데이터의 타입(MIME)
			dataType: "json" // 응답 데이터(기본값: 문자열)가 json 형식일 경우 javascript 오브젝트로 변경
		}).done(function(response) { // 요청에 대한 응답의 결과가 정상일 경우 동작
			alert("게시글 작성이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) { // 요청에 대한 응답의 결과가 실패할 경우 동작
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 parameter 데이터를 json으로 변경 후 insert 요청
	},
	
	deleteById: function() {
		let id = $("#id").val();
		
		/* ajax의 기본 호출: 비동기 호출 */
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json" // 응답 데이터(기본값: 문자열)가 json 형식일 경우 javascript 오브젝트로 변경
		}).done(function(response) { // 요청에 대한 응답의 결과가 정상일 경우 동작
			alert("게시글이 삭제되었습니다.");
			location.href = "/";
		}).fail(function(error) { // 요청에 대한 응답의 결과가 실패할 경우 동작
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 parameter 데이터를 json으로 변경 후 insert 요청
	},
	
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		console.log(data);
		
		/* ajax의 기본 호출: 비동기 호출 */
		$.ajax({
			type: "PATCH",
			url: "/api/board/" + id,
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=UTF-8", // body 데이터의 타입(MIME)
			dataType: "json" // 응답 데이터(기본값: 문자열)가 json 형식일 경우 javascript 오브젝트로 변경
		}).done(function(response) { // 요청에 대한 응답의 결과가 정상일 경우 동작
			alert("게시글이 수정되었습니다.");
			location.href = "/";
		}).fail(function(error) { // 요청에 대한 응답의 결과가 실패할 경우 동작
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 parameter 데이터를 json으로 변경 후 insert 요청
	},
	
	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#id").val(),
			content: $("#comment").val()
		};
		
		console.log(data);
		
		/* ajax의 기본 호출: 비동기 호출 */
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=UTF-8", // body 데이터의 타입(MIME)
			dataType: "json" // 응답 데이터(기본값: 문자열)가 json 형식일 경우 javascript 오브젝트로 변경
		}).done(function(response) { // 요청에 대한 응답의 결과가 정상일 경우 동작
			alert("댓글이 등록되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) { // 요청에 대한 응답의 결과가 실패할 경우 동작
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 parameter 데이터를 json으로 변경 후 insert 요청
	},
	
	replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(response) {
			alert("댓글이 삭제되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
	/*login: function() {
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val()
		};
		
		console.log(data);*/
		
		/* ajax의 기본 호출: 비동기 호출 */
		/*$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=UTF-8", // body 데이터의 타입(MIME)
			dataType: "json" // 응답 데이터(기본값: 문자열)가 json 형식일 경우 javascript 오브젝트로 변경
		}).done(function(response) { // 요청에 대한 응답의 결과가 정상일 경우 동작
			alert("정상적으로 로그인되었습니다.");
			location.href = "/";
		}).fail(function(error) { // 요청에 대한 응답의 결과가 실패할 경우 동작
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 parameter 데이터를 json으로 변경 후 insert 요청
	}*/
}

index.init();