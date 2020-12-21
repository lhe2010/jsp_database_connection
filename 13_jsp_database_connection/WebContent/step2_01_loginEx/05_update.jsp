<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<body>
	<%
		String id = (String)session.getAttribute("id");
	
	%>
	<h2>Modify '<%=id %>'</h2>
	<form action="06_updatePro.jsp" method="post" >
		<fieldset>
			<p><label for="id">id : </label><input type="text" id="id" name="id" value="<%=id %>" readonly ></p>
			<p><label for="passwd">Password : </label><input type="password" id="passwd"  name="passwd"></p>
			<p><label for="name">Name : </label><input type="text" id="name"  name="name" ></p>
			<button type="submit" >Modify</button>
		</fieldset>
	</form> 
</body>
</html>