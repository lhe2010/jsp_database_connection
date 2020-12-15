<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	String name = request.getParameter("name");

	// 데이터베이스를 연결하기 위한 객체
	Connection conn = null;
	// 쿼리문을 실행하기 위한 객체
	PreparedStatement pstmt = null;
	
	try {
		
		// DB연결 정보 > "jdbc://mysql://연결DB서버주소:포트번호/DB명/시간동기화"
		String jdbcUrl = "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
		// DB 연결계정
		String dbId = "root";
		// DB 연결 비밀번호
		String dbPass = "1234";
		
		// forName 생성
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 데이터베이스 연동
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		// 선처리문 쿼리 작성 (SQL injection 에 대비하기 위함)
		String sql = "insert into member values (?,?,?,now())";
		
		// 쿼리문 완성
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id); // mysql은 숫자 1부터 시작 
		pstmt.setString(2, passwd);
		pstmt.setString(3, name);
		
		// 쿼리문 실행
		pstmt.executeUpdate(); 	// insert, update, delete문 실행메서드 : .executeUpdate();
								// select문 실행메서드 : .executeQuery();
	} catch (Exception e){
		e.printStackTrace();
	} finally {
		// DB 연동 종료
		pstmt.close();
		conn.close();
		
	}
	
%>

	<a href="00_main.jsp">메인으로 돌아가기</a>
</body>
</html>