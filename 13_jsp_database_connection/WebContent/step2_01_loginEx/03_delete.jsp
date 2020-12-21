<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>leave</title>
</head>
<body>
	<%
		String id = (String)session.getAttribute("id");
	%>
	<h2>Delete Account '<%=id %>'</h2>
	<form action="04_deletePro.jsp" method="post" >
		<fieldset>
			<p><label for="id">id : </label><input type="text" value="<%=id %>" name="id" id="id" readonly></p>
			<p><label for="passwd">Password : </label><input type="password" id="passwd" name="passwd" ></p>
			<button type="submit">leave</button>
		</fieldset>
	</form>    
</body>
</html>