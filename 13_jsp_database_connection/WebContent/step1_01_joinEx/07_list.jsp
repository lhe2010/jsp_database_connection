<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
</head>
<body>
	<%	
		request.setCharacterEncoding("utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		
		try { 
			String jdbcUrl 	= "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
			String dbId 	= "root";
			String dbPass 	= "1234";
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
			
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String rId 		= "";
			String rPasswd 	= "";
			String rName 	= "";
			String rDate 	= "";
	%>
			<h2>회원 리스트</h2>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>PASSWORD</th>
					<th>NAME</th>
					<th>JOIN DATE</th>
				</tr>
	<%
			while(rs.next()){
				
				rId = rs.getString("id");
				rPasswd = rs.getString("passwd");
				rName = rs.getString("name");
				rDate = sdf.format(rs.getTimestamp("joindate")); // *****
	%>
				<tr align="center">
					<td><%=rId %></td>
					<td><%=rPasswd %></td>
					<td><%=rName %></td>
					<td><%=rDate %></td>
				</tr>
	<%
				System.out.printf("id:%s/pwd:%s/name:%s/date:%s\n", rId, rPasswd, rName, rDate);
			}
	%>
			</table>
	<%
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
	%>
</body>
</html>