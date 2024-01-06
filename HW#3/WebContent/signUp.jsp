<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("UTF-8");	// 한글 인코딩 용
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
<title>signUp page</title>
</head>
<body>
	<form method="post" action="./signUpAction.jsp">	<!-- 회원가입 버튼을 누를 시 signUpAction.jsp로 이동하여 동작함. post형식이라 url에 나타나지 않음 -->
		<table>
			<tr>
				<td width=100 align="right">ID:</td>
				<td><input type="text" name="ID"></td>	<!-- text타입의 "ID"값을 입력받아 전달 함 --> 
			</tr>
			<tr>
				<td align="right">PW:</td>
				<td><input type="password" name="PW"></td>	<!-- password타입(입력 시 안보임)의 "PW"값을 입력받아 전달 함 -->
			</tr>
			<tr>
				<td align="right">이름:</td>
				<td><input type="text" name="Name"></td> 	<!-- text타입의 "Name"값을 입력받아 전달 함 --> 
			</tr>
			<tr>
				<td align="right">전화번호:</td>
				<td><input type="tel" name="Tel"></td> 		<!-- tel타입의 "Tel"값을 입력받아 전달 함 --> 
			</tr>
			<tr>
				<td align="right">이메일:</td>
				<td><input type="email" name="Mail"></td>	<!-- email타입의 "Mail"값을 입력받아 전달 함 --> 
				<td><input type="submit" value="회원가입"></td>	<!-- 회원가입 버튼을 누를시 위에서 입력한 "ID"값, "PW"값, "Name"값, "Tel"값, "Mail"값을 signUpAction.jsp에 전달함 -->
			</tr>
		</table>
	</form>
</body>
</html>