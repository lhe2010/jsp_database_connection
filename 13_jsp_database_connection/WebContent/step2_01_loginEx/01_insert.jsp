<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
</head>
<body>

	<h2>Join Form</h2>
	<form name="joinForm" action="02_insertPro.jsp" method="post" >
		<fieldset>
			<p><label for="id">id : </label><input type="text" id="id" name="id" autofocus></p>
			<p><label for="pwd">Password : </label><input type="password" id="pwd"  name="pwd" ></p>
			<p><label for="confirmPwd">Confirm Password : </label><input type="password" id="confirmPwd" name="confirmPwd"></p>
			<p><label  for="inputDefault">Name : </label><input type="text" id="name" name="name"></p>
			<button type="submit">join</button>
			<button type="reset">reset</button>
		</fieldset>
	</form> 
	  
</body>
</html>