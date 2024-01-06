<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");	// 한글 인코딩 용
	String ID = null;	// login.jsp에서 입력한 "ID"에 해당하는 값을 저장할 변수
	String PW = null;	// login.jsp에서 입력한 "PW"에 해당하는 값을 저장할 변수
	String path1 = application.getRealPath("/file/userData.txt");	// 현재프로젝트위치/file/userData.txt를 path로 함
	File file = new File(path1);
	int Case = 0;	// 상황 정리용 변수 0: userData.txt에 일치하는 ID가 없음(회원가입 하지 않은 아이디), 1: userData.txt에 적힌 ID와 PW값과 일치하여 로그인 실행, 2: userData.txt에 적힌 ID는 있지만 PW값이 입력받은 값과 틀림
	int i=0;	// 정보를 다루기위한 변수 (0: ID, 1: PW, 2: Name, 3: Tel, 4: Mail)
	
	if(request.getParameter("ID")!=null) {	// login.jsp에서 입력한 "ID"에 해당하는 값이 null이 아니라면
		ID = request.getParameter("ID");	// ID변수에 login.jsp에서 입력한 "ID"에 해당하는 값을 저장
	}
	if(request.getParameter("PW")!=null) {	// login.jsp에서 입력한 "PW"에 해당하는 값이 null이 아니라면
		PW = request.getParameter("PW");	// PW변수에 login.jsp에서 입력한 "PW"에 해당하는 값을 저장
	}
	
	if(ID == null || PW == null) {	// ID 또는 PW가 null이라면
		PrintWriter script = response.getWriter();
		script.println("<script>");	
		script.println("alert('입력이 되지 않은 칸이 있습니다. 모든 칸을 입력해주세요.');");	// 입력이 되지 않은 칸이 있습니다. 모든 칸을 입력해주세요.라는 문구를 가진스크립트를 출력하고
		script.println("history.back()");	// login.jsp로 돌아감(로그인 화면으로 복귀)
		script.println("</script>");	
		script.close();
		return;
	}
	else {	// ID와 PW 모두 입력된 경우
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8")); // 입력 버퍼 생성
			String line = "";	// 파일에서 읽은 내용을 저장할 변수
			while((line = bufReader.readLine())!=null) {	// 파일 끝까지(null이 나올 때까지) 계속 한 줄씩 읽어서 line에 저장
				if(ID.equals(line)==true && i == 0) {	// line에 저장한 내용이 ID와 일치하고 i=0이라면(ID에 해당하는 값이 맞는 경우)
					line = bufReader.readLine();	// 그 다음줄을 읽어서 line에 저장하고
					if(PW.equals(line)==true) {	// 그 다음줄(PW에 해당하는 내용)이 입력받은 PW값과 같으면(아이디와 비밀번호 모두 일치-> 로그인)
						Case = 1;	// Case값을 1로 만듦(로그인)
						session.setAttribute("ID", ID);	// 현재 ID값을 session에 저장(로그인 되어있는 상황을 나타내기 위해서) 
						break;
					}
					else {	// 그 다음줄(PW에 해당하는 내용)이 입력받은 PW값과 다르면 (아이디는 같지만 비밀번호가 틀림)
						Case = 2;	// Case값을 2로 만듦(잘못된 비밀번호)
						break;
					}
				}
				i++;	// if문안에 들어갔다면 break로 빠져나가므로 if문안에 들어가지 않은 경우(일치하는 ID를 못찾아서 계속 찾는 경우) 매 줄마다 i값을 1 더해줌
				i = i % 5;	// i값이 0~4를 유지(0: ID, 1: PW, 2: Name, 3: Tel, 4: Mail)
			}
			bufReader.close();	// 입력 버퍼를 끝냄
		} catch(FileNotFoundException e){	// 파일을 찾지 못한 경우(userData.txt)
			out.println("Error:유저 데이터에 접근할 수가 없습니다.");	
		} catch(IOException e){		
			out.println("error");
		}
	}
	if(Case == 1) {	// 로그인하는 경우
		String path2 = application.getRealPath("/file/userLogin.txt");	// 현재프로젝트위치/file/userLogin.txt를 path로 함
		File loginFile = new File(path2);	// 파일 객체 생성
		FileOutputStream output = new FileOutputStream(loginFile,true);	// OutputStream 생성(이미 존재하는 경우 뒤에 이어서 작성)
		OutputStreamWriter writer = new OutputStreamWriter(output,"UTF-8");	// UTF-8 문자 집합의 인코딩을 사용해 output을 바이트스트림으로 변환 객체를 생성
		BufferedWriter loginFileWriter = new BufferedWriter(writer);	// 출력 버퍼 생성
		Calendar time = Calendar.getInstance();	// Calendar클래스에서 getInstance() 메소드를 사용하여 현재 날짜와 시간의 객체를 가져오기 위함
		int year = time.get(Calendar.YEAR);		// 현재 시간의 '년'에 해당하는 값을 year에 저장
		int month = time.get(Calendar.MONTH)+1;	// 현재 시간의 '월'에 해당하는 값을 month에 저장(Calendar에 0~11로 저장되어있기 때문에 1을 더함)
		int date = time.get(Calendar.DATE);		// 현재 시간의 '일'에 해당하는 값을 date에 저장
		int hour = time.get(Calendar.HOUR); 	// 현재 시간의 '시'에 해당하는 값을 hour에 저장
		int minute = time.get(Calendar.MINUTE);	// 현재 시간의 '분'에 해당하는 값을 minute에 저장 
		int second = time.get(Calendar.SECOND);	// 현재 시간의 '초'에 해당하는 값을 second에 저장
		loginFileWriter.write(ID + "\r\n" + year + "\r\n" + month + "\r\n" + date + "\r\n" + hour + "\r\n" + minute + "\r\n" + second + "\r\n");	// 로그인 한 사용자의 ID와 로그인 시간을 userLogin.txt에 저장
		loginFileWriter.close();			// 출력 버퍼를 끝냄
		writer.close();						// 변환 객체 제거
		output.close();						// OutputStream 제거
		pageContext.forward("Main.jsp");	// 메인 페이지로 이동
	}
	else if(Case == 2) {	// 잘못된 비밀번호 입력시
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('잘못된 비밀번호를 입력하였습니다.');");	// 잘못된 비밀번호를 입력하였습니다.라는 문구를 가진 스크립트를 출력하고
		script.println("history.back()");	// login.jsp로 돌아감(로그인 화면으로 복귀)
		script.println("</script>");
		script.close();
		return;
	}
	else {	// Case=0 : 일치하는 아이디가 없는 경우
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('올바른 아이디인지 확인해주세요.');");		// 올바른 아이디인지 확인해주세요.라는 문구를 가진 스크립트를 출력하고
		script.println("history.back()");	// login.jsp로 돌아감(로그인 화면으로 복귀)
		script.println("</script>");
		script.close();
		return;
	}
	
%>