<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
<title>login page</title>
</head>
<body>
	<form method="post" action="./loginAction.jsp">	<!-- 로그인 버튼을 누를 시 loginAction.jsp로 이동하여 동작함. post형식이라 url에 나타나지 않음 -->
		<table>
			<tr>
				<td width=50 align="right">ID</td>	
				<td><input type="text" name="ID"></td> <!-- text타입의 "ID"값을 입력받아 전달 함 -->
			</tr>
			<tr>
				<td align="right">PW</td>
				<td><input type="password" name="PW"></td>	<!-- password타입(입력 시 안보임)의 "PW"값을 입력받아 전달 함 -->
				<td><input type="submit" value="로그인"> </td>	<!-- 로그인 버튼을 누를시 위에서 입력한 "ID"값과 "PW"값을 loginAction.jsp에 전달함 -->	
			</tr>
		</table>
	</form>
</body>
</html>