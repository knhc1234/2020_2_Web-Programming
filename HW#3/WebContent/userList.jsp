<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<html>
<head>
<meta charset="utf-8">
<title>유저 목록</title>
</head>
<body>
<%
	String path = application.getRealPath("/file/userData.txt");	// 현재프로젝트위치/file/userData.txt를 path로 함
	File file = new File(path);	// 파일 객체 생성
	BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));	// 입력 버퍼 생성
	String userID = (String)session.getAttribute("ID");	// 사용자 유저의 ID(userID에 로그인 할 때 입력한 "ID"값을 세션에서 가져와 저장)
	String userName = null;	// 파일에서 읽은 사용자 유저의 이름정보를 저장하기 위한 변수
	String userTel = null;	// 파일에서 읽은 사용자 유저의 전화번호정보를 저장하기 위한 변수
	String userMail = null;	// 파일에서 읽은 사용자 유저의 email정보를 저장하기 위한 변수
	String ID = null;	// 파일에서 읽은 유저의 ID정보를 저장하기 위한 변수
	String Name = null;	// 파일에서 읽은 유저의 이름정보를 저장하기 위한 변수
	String Tel = null;	// 파일에서 읽은 유저의 전화번호정보를 저장하기 위한 변수
	String Mail = null;	// 파일에서 읽은 유저의 email정보를 저장하기 위한 변수
	
	int i=0;			// 0: userID, 2: userName, 3: userTel, 4: userMail (1:비밀번호이지만 여기선 다루지 않음)
	String line = "";	// 파일에서 읽어올 정보를 저장함(한줄 단위)
	try{
		while((line = bufReader.readLine())!=null) {	// 파일 끝까지(null이 나올 때까지) 계속 한 줄씩 읽어서 line에 저장
			if(i == 0) {	// ID정보를 읽은 경우
				ID = line;
			}
			else if(i == 2) {	// 이름정보를 읽은 경우
				Name = line;
			}
			else if(i == 3) {	// 전화번호정보를 읽은 경우
				Tel = line;
			}
			else if(i == 4){	// email정보를 읽은 경우
				Mail = line;	
			}
			i++;	// 매 줄을 읽을 때마다 i를 1씩 증가
			i = i % 5;	// i값이 0~4를 유지(0: userID, 2: userName, 3: userTel, 4: userMail)
			if(i == 0) {	// 한 회원의 정보를 모두 읽은 경우
				if(ID.equals(userID)) {	// 사용자 ID와 읽은 정보의 회원의 ID가 같다면(사용자에 대한 정보라면)
					userName = Name;	// 해당 정보를 사용자 유저의 정보를 저장하기 위한 변수로 옮기고
					userTel = Tel;
					userMail = Mail;
				}
				// 그 회원의 정보를 출력함 (&nbsp는 빈칸을 나타냄)
				String s = "ID:&nbsp;" + ID + "&nbsp;&nbsp;&nbsp;&nbsp;이름:&nbsp;" + Name + "&nbsp;&nbsp;&nbsp;&nbsp;전화번호:&nbsp;" + Tel + "&nbsp;&nbsp;&nbsp;&nbsp;이메일:&nbsp;" + Mail + "<br>";
				out.println(s);
			}
		}
		// 모든 회원의 정보를 출력한 후 사용자의 정보도 출력함(회원의 정보를 출력할 때도 있긴 함)
		String s = "사용자&nbsp;ID:&nbsp;" + userID + "&nbsp;&nbsp;&nbsp;&nbsp;사용자&nbsp;이름:&nbsp;" + userName + "&nbsp;&nbsp;&nbsp;&nbsp;사용자&nbsp;전화번호:&nbsp;" + userTel + "&nbsp;&nbsp;&nbsp;&nbsp;사용자&nbsp;이메일:&nbsp;" + userMail + "<br>";
		out.println(s);
		bufReader.close();	// 입력 버퍼를 끝냄
	 } catch(FileNotFoundException e) {	// 파일을 찾지 못한 경우(userData.txt)
		out.println("Error:유저 데이터에 접근할 수가 없습니다.");	
	 } catch(IOException e) {		
		out.println("Error");
	 }	
%>
	<a href="Main.jsp">메인화면</a><br>	<!-- 메인화면으로 이동하는 하이퍼링크 생성 -->
</body>
</html>