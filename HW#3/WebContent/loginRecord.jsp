<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 기록</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");	// 한글 인코딩 용
	String path = application.getRealPath("/file/userLogin.txt");	// 현재프로젝트위치/file/userLogin.txt를 path로 함
	File file = new File(path);	// 파일 객체 생성
	BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8")); // 입력 버퍼 생성
	String userID = (String)session.getAttribute("ID");	// 로그인 되어있는 ID(userID에 로그인 할 때 입력한 "ID"값을 세션에서 가져와 저장)
	String ID = null;		// 파일에서 읽어올 ID값 저장용 변수
	String year = null;		// 파일에서 읽어올 '년'에 해당하는 값 저장용 변수
	String month = null;	// 파일에서 읽어올 '월'에 해당하는 값 저장용 변수
	String date = null;		// 파일에서 읽어올 '일'에 해당하는 값 저장용 변수
	String hour = null;		// 파일에서 읽어올 '시'에 해당하는 값 저장용 변수
	String minute = null;	// 파일에서 읽어올 '분'에 해당하는 값 저장용 변수
	String second = null;	// 파일에서 읽어올 '초'에 해당하는 값 저장용 변수
	int i=0;			// 0: ID, 1: year, 2: month , 3: date, 4: hour, 5: minute, 6: second 
	String line = "";	// 파일에서 읽어올 정보를 저장할 변수(한줄 단위)

	try{
		while((line = bufReader.readLine())!=null) {	// 파일 끝까지(null이 나올 때까지) 계속 한 줄씩 읽어서 line에 저장
			if(i == 0) {	// ID정보를 읽은 경우
				ID = line;	
			}
			else if(i == 1) {	// '년'에 해당하는 정보를 읽은 경우
				year = line;
			}
			else if(i == 2) {	// '월'에 해당하는 정보를 읽은 경우
				month = line;
			}
			else if(i == 3) {	// '일'에 해당하는 정보를 읽은 경우
				date = line;
			}
			else if(i == 4){	// '시'에 해당하는 정보를 읽은 경우
				hour = line;	
			}
			else if(i == 5){	// '분'에 해당하는 정보를 읽은 경우
				minute = line;	
			}
			else if(i == 6){	// '초'에 해당하는 정보를 읽은 경우
				second = line;	
			}
			i++;		// i값을 1 증가 시킴(줄마다의 정보 구분용)
			i = i % 7;	//i값이 0~6을 유지(0: ID, 1: year, 2: month, 3: date, 4: hour, 5: minute, 6: second)
			if(i == 0) {	// 하나의 로그인 정보(ID와 로그인 시간)를 모두 읽은 경우 
				if(userID.equals(ID)){	// 만약 그 사람이 본인(로그인 되어있는 userID와 읽은 정보의 ID가 같은 경우)이라면 해당 로그인 정보를 출력함 (&nbsp는 빈칸을 나타냄)
					String s = "ID:&nbsp;" + ID + "&nbsp;" + year + "년&nbsp;" + month + "월&nbsp;" + date+ "일&nbsp;" + hour + "시&nbsp;" + minute + "분&nbsp;" + second + "초" + "<br>";
					out.println(s);
				}
			}	// 결국 저장된 로그인 정보들을 확인하여 사용자 ID와 로그인 일시를 출력하게 됨
		}
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