<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<h2>회원정보 수정</h2>
	<h3>아이디와 패스워드가 인증되면 이름을 수정시킵니다. </h3>
	<form method="post" action="06_updatePro.jsp">
		아이디 : <input type="text" name="id" id="id"><br>
		패스워드 : <input type="password" name="passwd" id="passwd"><br>
		이름 : <input type="text" id="name" name="name"><br>
		<input type="submit" value="입력완료">
	</form>
</body>
</html>