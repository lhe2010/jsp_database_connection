<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<body>
	
	<h2>Modify </h2>
	<form name="joinForm" action="06_updatePro.jsp" method="post" >
		<fieldset>
			<p><label for="id">id </label><input type="text" id="id" name="id"  ></p>
			<p><label for="pwd">Password </label><input type="password" id="passwd"  name="passwd" autofocus></p>
			<p><label for="name">Name </label><input type="text" id="name"  name="name" ></p>
			<button type="submit" >modify</button>
		</fieldset>
	</form> 
</body>
</html>