<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<%
	request.setCharacterEncoding("UTF-8");	// 한글 인코딩 용
	String ID = null;	// 파일에서 읽어올 ID값 저장용 변수
	String PW = null;	// 파일에서 읽어올 PW값 저장용 변수
	String Name = null;	// 파일에서 읽어올 Name값 저장용 변수
	String Tel = null;	// 파일에서 읽어올 Tel값 저장용 변수
	String Mail = null;	// 파일에서 읽어올 Mail값 저장용 변수
	String path = application.getRealPath("/file/userData.txt");	// 현재프로젝트위치/file/userData.txt를 path로 함
	File file = new File(path);	// 파일 객체 생성
	int i = 0;		// 0: ID, 1: PW, 2: Name, 3: Tel;, 4: Mail
	
	if(request.getParameter("ID")!=null) {	// signUp.jsp에서 입력한 "ID"에 해당하는 값이 null이 아니라면
		ID = request.getParameter("ID");	// ID변수에 signUp.jsp에서 입력한 "ID"에 해당하는 값을 저장
	}
	if(request.getParameter("PW")!=null) {	// signUp.jsp에서 입력한 "PW"에 해당하는 값이 null이 아니라면
		PW = request.getParameter("PW");	// PW변수에 signUp.jsp에서 입력한 "PW"에 해당하는 값을 저장
	}
	if(request.getParameter("Name")!=null) {	// signUp.jsp에서 입력한 "Name"에 해당하는 값이 null이 아니라면
		Name = request.getParameter("Name");	// Name변수에 signUp.jsp에서 입력한 "Name"에 해당하는 값을 저장
	}
	if(request.getParameter("Tel")!=null) {		// signUp.jsp에서 입력한 "Tel"에 해당하는 값이 null이 아니라면
		Tel = request.getParameter("Tel");		// Tel변수에 signUp.jsp에서 입력한 "Tel"에 해당하는 값을 저장
	}
	if(request.getParameter("Mail")!=null) {	// signUp.jsp에서 입력한 "Mail"에 해당하는 값이 null이 아니라면
		Mail = request.getParameter("Mail");	// Mail변수에 signUp.jsp에서 입력한 "Mail"에 해당하는 값을 저장
	}
	
	if(ID == null || PW == null || Name == null || Tel == null || Mail == null) {	// ID, PW, Name, Tel, Mail중 입력되지 않은 값이 하나라도 있다면
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 되지 않은 칸이 있습니다. 모든 칸을 입력해주세요.');");	// 입력이 되지 않은 칸이 있습니다. 모든 칸을 입력해주세요.라는 문구를 가진스크립트를 출력하고
		script.println("history.back()");	// signUp.jsp로 돌아감(회원가입 화면으로 복귀)
		script.println("</script>");
		script.close();
		return;
	}
	else {
		String[] list = { ID, PW, Name, Tel, Mail};	// 입력 받은 데이터들을 묶어 list형식으로 만듦
		try{
			FileOutputStream output = new FileOutputStream(file,true);		// OutputStream 생성(이미 존재하는 경우 뒤에 이어서 작성)
			OutputStreamWriter writer = new OutputStreamWriter(output,"UTF-8");	// UTF-8 문자 집합의 인코딩을 사용해 output을 바이트스트림으로 변환 객체를 생성
			BufferedWriter bufWriter = new BufferedWriter(writer);	// 출력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));	// 입력 버퍼 생성
			String line = "";	// 파일에서 읽은 내용을 저장할 변수
			while((line = bufReader.readLine())!=null) {	// 파일 끝까지(null이 나올 때까지) 계속 한 줄씩 읽어서 line에 저장
				if(ID.equals(line)==true && i == 0) {	// 아이디 중복인 경우
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('이미 존재하는 아이디입니다.');");	// 이미 존재하는 아이디입니다.라는 문구를 가진 스크립트를 출력하고
					script.println("history.back()");	// signUp.jsp로 돌아감(회원가입 화면으로 복귀)
					script.println("</script>");
					script.close();
					return;
				}
				i++;		// if문안에 들어갔다면 break로 빠져나가므로 if문안에 들어가지 않은 경우(일치하는 ID가 없어서 계속 찾는 경우) 매 줄마다 i값을 1 더해줌
				i = i % 5;	// i값이 0~4를 유지(0: ID, 1: PW, 2: Name, 3: Tel, 4: Mail)
			}
			bufReader.close();	// 입력 버퍼를 끝냄

			for(String s: list) {	// list에 저장된 ID, PW, Name, Tel, Mail값을 userData.txt에 저장(입력)함(한 내용을 입력하고 다음 줄에 입력하는 방식)
				bufWriter.write(s);
				bufWriter.write("\r\n");
			}
			bufWriter.close();	// 출력 버퍼를 끝냄
			writer.close();		// 변환 객체 제거
			output.close();		// OutputStream 제거
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입에 성공하였습니다!');");		// 회원가입에 성공하였습니다!라는 문구를 가진 스크립트를 출력하고
			script.println("location.href='index.jsp'");	// 초기 화면인 index.jsp로 이동시킴(로그인 전이므로)
			script.println("</script>");	
			script.close();		
			return;
		} catch(IOException e) {
			out.println("error!");
		} 
	}
	
%>