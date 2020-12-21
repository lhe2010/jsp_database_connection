<%@page import="step2_00_loginEx.MemberDAO"%>
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
		
		boolean isValidMember = MemberDAO.getInstance().memberCheck(id, passwd);
		
		// 로그인 DAO가 필요할듯!
		if(isValidMember) {
			session.setAttribute("id", id);			// 세션에 등록한후 메인페이지로 이동
			session.setMaxInactiveInterval(60*10); // 연결상태 유지 10번으로 생성(기본값30분) // 60*60*24*7 
			response.sendRedirect("00_main.jsp");
		} else {
			%>
			<script>
				alert("check your Id and Password");
				history.go(-1);
			</script>
	
	<%		
		}
	%>

</body>
</html>