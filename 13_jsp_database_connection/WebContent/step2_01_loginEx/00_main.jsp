<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--	
	# 실습을 위한 데이터베이스 구성
	
	CREATE DATABASE LOGIN_EX;
	
	USE LOGIN_EX;
	
	CREATE TABLE MEMBER(
		ID VARCHAR(20),
	    PASSWD VARCHAR(20),
	    NAME VARCHAR(20),
		JOINDATE DATETIME
	); 
 --%>
 
<html>

<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>

      	<h1>JUST DO IT!</h1>
      	<p><a href="01_insert.jsp">join!</a></p>
      	<p><a href="07_login.jsp">login!</a></p>		         
    	<p><a href="05_update.jsp?" >modify!</a></p>
      	<p><a href="03_delete.jsp?" >delete!</a></p>
      	<a href="09_logout.jsp">logout!</a>
	 
</body>
</html>

