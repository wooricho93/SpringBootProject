let index = {
	init: function() {
		$("#btn-join").on("click", () => { // this를 바인딩하기 위해 사용
			this.save();
		});
		
		/*$("#btn-login").on("click", () => { // this를 바인딩하기 위해 사용
			this.login();
		});*/
		
		$("#btn-update").on("click", () => { // this를 바인딩하기 위해 사용
			this.update();
		});
	},
	
	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		console.log(data);
		
		/* ajax의 기본 호출: 비동기 호출 */
		$.ajax({
			type: "POST",
			url: "/auth/join",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=UTF-8", // body 데이터의 타입(MIME)
			dataType: "json" // 응답 데이터(기본값: 문자열)가 json 형식일 경우 javascript 오브젝트로 변경
		}).done(function(response) { // 요청에 대한 응답의 결과가 정상일 경우 동작
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) { // 요청에 대한 응답의 결과가 실패할 경우 동작
			alert("이미 존재하는 아이디입니다.");
		}); // ajax 통신을 이용해서 3개의 parameter 데이터를 json으로 변경 후 insert 요청
	},
	
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
	
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		console.log(data);
		
		/* ajax의 기본 호출: 비동기 호출 */
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=UTF-8", // body 데이터의 타입(MIME)
			dataType: "json" // 응답 데이터(기본값: 문자열)가 json 형식일 경우 javascript 오브젝트로 변경
		}).done(function(response) { // 요청에 대한 응답의 결과가 정상일 경우 동작
			alert("회원 정보 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) { // 요청에 대한 응답의 결과가 실패할 경우 동작
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 parameter 데이터를 json으로 변경 후 insert 요청
	},
	
	deleteUser: function(userId) {
		$.ajax({
			type: "DELETE",
			url: `/user/${userId}`,
			dataType: "json"
		}).done(function(response) {
			alert("회원 탈퇴가 정상적으로 처리되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();