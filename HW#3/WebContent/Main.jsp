<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
<title>메인 화면</title>
</head>
<body>
<%
	String userID = (String)session.getAttribute("ID");	// 로그인 되어있는 ID(userID에 로그인 할 때 입력한 "ID"값을 세션에서 가져와 저장)
	out.println("ID:&nbsp;" + userID);	// ID: "userID"형태로 출력(로그인 한 계정을 확인할 수 있는 용도)
%>
	<a href="index.jsp">로그아웃</a><br>			<!-- 로그아웃하여 index.jsp 화면으로 이동하는 하이퍼링크 생성 + \n(<br>)처리-->
	<a href="userList.jsp">회원 목록</a><br>		<!-- 회원 목록으로 이동하는 하이퍼링크 생성 + \n(<br>)처리-->
	<a href="loginRecord.jsp">로그인 기록</a>		<!-- 로그인 기록으로 이동하는 하이퍼링크 생성-->
</body>
</html>