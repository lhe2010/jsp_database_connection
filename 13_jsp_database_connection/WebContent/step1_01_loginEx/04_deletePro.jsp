<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
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
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
		// DB 연결계정
		String dbId = "root";
		// DB 연결 비밀번호
		String dbPass = "1234";
		
		// forName 생성
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 데이터베이스 연동
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		String sql = "select id, passwd from member where id=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,id);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()){ // rs.next() : 반환된 결과물이 있으면
// 			System.out.println("있는 아이디네");
			String returnId = rs.getString("id");
			String returnPasswd = rs.getString("passwd");
			
			// 파라메타로 넘어온 ID/Passwd와 DB에 있는 ID/passwd 비교
			if(id.equals(returnId) && passwd.equals(returnPasswd)){
				sql = "delete from member where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				
			} else { // 비밀번호가 틀린 경우
				
			}
			
		} else {	// 아이디가 없는 경우 
			System.out.println("없는 아이디네");
			
		}
		
		
	} catch (Exception e){
		e.printStackTrace();
	} finally {
		conn.close();
		pstmt.close();
		rs.close();
	}
%>

</body>
</html>