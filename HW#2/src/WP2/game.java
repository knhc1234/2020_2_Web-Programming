package WP2_2019253025;

import java.util.Scanner;
public class game {
	
	public static void main(String[] args) {
		char [][] map = new char [10][31];			// 맵정보를 저장하기 위한 배열
		Scanner scan = new Scanner(System.in);		// System.in에 대한 Scanner객체를 만듦
		int mapChoice = (int)(Math.random()*3 + 1);	// map1, map2, map3중 플레이할 맵을 랜덤으로 고르기 위한 변수
		switch(mapChoice) {	// 정해진 맵중 랜덤으로 생성(map배열에 정보를 넣음)
		case 1: map1(map);
			break;
		case 2: map2(map);
			break;
		case 3: map3(map);
			break;
		}

		int clear = 1;	// clear == 0 게임 클리어, 1: 진행중
		int endCode = 1;	// 플레이어 사망 혹은 게임 클리어시 게임을 다시 시작: 1, 그대로 종료 : 0
		Player player = new Player(8,1);	// 시작 좌표(세 맵의 시작좌표는 모두 같음) player를 생성(체력, 현재 위치, 죽인 보스의 수 등의 정보를 가지고 있음)
		while(true) {	// 게임이 끝날때 까지 반복
			printMap(player, map);	// 맵을 출력
			movePlayer(scan, player, map);	// 플레이어를 이동
			clear = checkEvent(scan, player, map);	// 만약 해당 칸에서 이벤트(보스 또는 보물상자 발견)가 발생한다면 해당 이벤트 진행
			if(clear == 0) {	// clear가 0이라면 네 보스를 모두 처리하고 보물 상자를 얻은 경우(게임 클리어)
				endCode = finishAndRestart(scan, map, player, mapChoice);	// T(보물)을 만나면 게임 승리 안내를 출력하고 게임 다시 시작 여부를 물어봄
			}
			else {	// clear가 1 즉 게임이 진행중인데 플레이어가 죽은 경우(게임 오버)
				if(player.curhp <= 0) {	// 플레이어의 체력이 모두 소진될 경우 게임 패배 안내를 출력하고 게임을 다시 시작할지 물어봄
					System.out.println("안내> 플레이어가 사망하였습니다. 게임을 종료합니다.");	
					endCode = finishAndRestart(scan, map, player, mapChoice);	
				}
			}
			if(endCode == 0) // 게임을 승리했든 중간에 패배하였든 다시 시작할지 물어볼 때 종료(n)를 선택한 경우 게임을 종료함
				break;
		}
		scan.close();	
	}
	
	public static int finishAndRestart(Scanner scan, char map[][], Player player, int mapChoice) {	// 게임을 다시 시작할지 물어보는 함수
		String restart;	// 게임을 재시작 할지 말지를 입력받는 변수
		int endCode = 1;// 메인에 리턴하기 위한 변수(1: 게임 재시작(진행중), 0: 게임 종료)
		System.out.println("안내> 게임을 재시작 하시겠습니까?(y or n)");
		System.out.print("플레이어>");
		while(true) {
			try {
			restart = scan.next();	// 게임을 재시작 할지 말지 입력 받음
			if(restart.equals("y") == true) {	// y를 입력받은 경우
				switch(mapChoice) {	// 현재 진행중인 맵에서 게임을 다시 실행하기 위해 배열의 데이터를 초기화해줌(보스 처치시 사라진 일부 데이터 복구)
				case 1: map1(map);
					break;
				case 2: map2(map);
					break;
				case 3: map3(map);
					break;
				}
				player.regame(8, 1); // 플레이어의 위치 초기화(시작 위치로 이동)
				break; // while문을 빠져나감
			}
			else if(restart.equals("n") == true) {	// n를 입력 받은 경우	
				System.out.println("안내> 게임을 종료합니다. 즐겨주셔서 감사합니다.");	// 종료 문구 출력
				endCode = 0;	// endCode를 0으로 만들어 main에서 게임을 종료하도록 만들어줌
				break;	// while문을 빠져나감
			}
			else {	// y또는 n을 입력한 경우가 아닌 경우
				throw new Exception();	// 예외 처리
			}
			}catch(Exception e) {	// y또는 n을 입력한 경우가 아닌 경우
				System.out.println("안내> 잘못된 입력입니다.");	// 잘못된 입력이라 출력후 while문을 통해 다시 입력 받음
			}
		}
		return endCode;	// endCode를 리턴해줌(1: 게임 재시작(진행중), 0: 게임 종료)
	}
	public static void map1(char [][] map) {	// 맵1을 생성(map배열을 채움)
		for(int i=0; i < 10; i++) {	
			for(int j=0; j< 31; j++) {
				if(i == 0 || i == 9) {		// 위아래 끝의 벽 만들기
					if(j==0 || j==30) {
						map[i][j] = 'o';
					}
					else {
						map[i][j] = '-';
					}
				}
				else {
					if(j == 0 || j == 30) {	// 좌우 끝의 벽 만들기
						map[i][j] = '|';
					}
					// 내부 만들기
					else if(i == 1) {
						map[i][j] = '`';
					}
					else if(i == 2 && (j==1||j==2||j==3||j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==21||j==22||j==23||j==24))
						map[i][j] = '`';
					else if(i == 3 && (j==1||j==2||j==3||j==5||j==6||j==7||j==8||j==9||j==10||j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==26||j==27||j==28))
						map[i][j] = '`';
					else if(i == 4 && (j==1||j==2||j==3||j==5||j==6||j==7||j==8||j==9||j==10||j==18||j==19||j==20||j==22||j==23||j==24||j==25||j==26||j==27||j==28))
						map[i][j] = '`';
					else if(i == 5 && (j==1||j==2||j==3||j==5||j==6||j==7||j==8||j==9||j==10||j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==26||j==27||j==28))
						map[i][j] = '`';
					else if(i == 6 && (j==1||j==7||j==8||j==9||j==10||j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==26||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 7 && (j==1||j==3||j==4||j==5||j==7||j==8||j==9||j==10||j==12||j==13||j==14||j==15||j==16||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 8 && (j==3||j==4||j==5||j==8||j==9||j==10||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==25||j==26||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 2 && j == 4)
						map[i][j] = '1';
					else if(i == 4 && j == 11)
						map[i][j] = '2';
					else if(i == 7 && j == 25)
						map[i][j] = '3';
					else if(i == 2 && j == 25)
						map[i][j] = '4';
					else if(i == 8 && j == 1)
						map[i][j] = 's';
					else if(i == 5 && j == 29)
						map[i][j] = 'T';
					else
						map[i][j] = ' ';
				}
			}
		}
	}
	
	public static void map2(char [][] map) {	//맵2를 생성(map배열을 채움)
		for(int i=0; i < 10; i++) {	
			for(int j=0; j< 31; j++) {
				if(i == 0 || i == 9) {		// 위아래 끝의 벽 만들기
					if(j==0 || j==30) {	
						map[i][j] = 'o';
					}
					else {
						map[i][j] = '-';
					}
				}
				else {
					if(j == 0 || j == 30) {	// 좌우 끝의 벽 만들기
						map[i][j] = '|';
					}
					// 내부 만들기
					else if(i == 1 && (j==1||j==2||j==3||j==4||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==26||j==27||j==28||j==29)) {
						map[i][j] = '`';
					}
					else if(i == 2 && (j==1||j==2||j==3||j==4||j==6||j==7||j==8||j==9||j==10||j==11||j==12||j==14||j==15||j==16||j==18||j==19||j==20||j==21||j==22||j==23||j==24))
						map[i][j] = '`';
					else if(i == 3 && (j==1||j==2||j==3||j==4||j==6||j==7||j==8||j==9||j==10||j==11||j==12||j==14||j==15||j==16||j==18||j==19||j==20||j==26||j==27||j==28))
						map[i][j] = '`';
					else if(i == 4 && (j==1||j==2||j==3||j==4||j==12||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==25||j==26||j==27||j==28))
						map[i][j] = '`';
					else if(i == 5 && (j==1||j==2||j==3||j==4||j==5||j==6||j==7||j==8||j==9||j==10||j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==21||j==22||j==23||j==24))
						map[i][j] = '`';
					else if(i == 6 && (j==1||j==2||j==3||j==4||j==5||j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==26||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 7 && (j==1||j==2||j==3||j==4||j==5||j==7||j==8||j==9||j==10||j==12||j==13||j==14||j==15||j==16||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 8 && (j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==25||j==26||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 5 && j == 11)
						map[i][j] = '1';
					else if(i == 1 && j == 5)
						map[i][j] = '2';
					else if(i == 7 && j == 25)
						map[i][j] = '3';
					else if(i == 2 && j == 25)
						map[i][j] = '4';
					else if(i == 8 && j == 1)
						map[i][j] = 's';
					else if(i == 3 && j == 21)
						map[i][j] = 'T';
					else
						map[i][j] = ' ';
				}
			}
		}
	}
	public static void map3(char [][] map) {	//맵3를 생성(map배열을 채움)
		for(int i=0; i < 10; i++) {	
			for(int j=0; j< 31; j++) {
				if(i == 0 || i == 9) {		// 위아래 끝의 벽 만들기
					if(j==0 || j==30) {
						map[i][j] = 'o';
					}
					else {
						map[i][j] = '-';
					}
				}
				else {
					if(j == 0 || j == 30) {	// 좌우 끝의 벽 만들기
						map[i][j] = '|';
					}
					// 내부 만들기
					else if(i == 1 && (j==21||j==22||j==23||j==24||j==25||j==26||j==27||j==28||j==29)) {
						map[i][j] = '`';
					}
					else if(i == 2 && (j==1||j==2||j==4||j==5||j==7||j==8||j==9||j==10||j==11||j==13||j==14||j==15||j==16||j==17||j==18||j==19||j==21||j==22||j==23||j==24))
						map[i][j] = '`';
					else if(i == 3 && (j==1||j==2||j==4||j==5||j==13||j==14||j==15||j==16||j==17||j==18||j==19||j==26||j==27||j==28))
						map[i][j] = '`';
					else if(i == 4 && (j==4||j==5||j==6||j==7||j==8||j==9||j==10||j==11||j==12||j==13||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==25||j==26||j==27||j==28))
						map[i][j] = '`';
					else if(i == 5 && (j==1||j==2||j==3||j==4||j==5||j==6||j==7||j==8||j==9||j==10||j==11||j==12||j==13||j==14||j==15||j==16||j==18||j==19||j==20||j==21||j==22||j==23||j==24))
						map[i][j] = '`';
					else if(i == 6 && (j==1||j==2||j==3||j==4||j==5||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==26||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 7 && (j==1||j==2||j==3||j==4||j==5||j==7||j==8||j==9||j==10||j==12||j==13||j==14||j==15||j==16||j==26||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 8 && (j==12||j==13||j==14||j==15||j==16||j==17||j==18||j==19||j==20||j==21||j==22||j==23||j==24||j==25||j==26||j==27||j==28||j==29))
						map[i][j] = '`';
					else if(i == 6 && j == 11)
						map[i][j] = '1';
					else if(i == 5 && j == 25)
						map[i][j] = '2';
					else if(i == 1 && j == 20)
						map[i][j] = '3';
					else if(i == 1 && j == 6)
						map[i][j] = '4';
					else if(i == 8 && j == 1)
						map[i][j] = 's';
					else if(i == 4 && j == 1)
						map[i][j] = 'T';
					else
						map[i][j] = ' ';
				}
			}
		}
	}
	public static void printMap(Player player, char[][] map) {	// 현재 상황을 출력
		System.out.println("게임 화면                        정보");
		for(int i=0; i<10; i++) {
			for(int j=0; j<31; j++) {
				if(i == player.x && j == player.y) {	// 만약 플레이어의 현재 위치에 해당하는 map배열의 값이 s,1,2,3,4,T,또는 공백인 경우 p를 출력하도록 함(플레이어의 현재 위치 출력)
					if(map[player.x][player.y] == 's')
						System.out.print('p');
					else if(map[player.x][player.y] == '1')
						System.out.print('p');
					else if(map[player.x][player.y] == '2')
						System.out.print('p');
					else if(map[player.x][player.y] == '3')
						System.out.print('p');
					else if(map[player.x][player.y] == '4')
						System.out.print('p');
					else if(map[player.x][player.y] == 'T')
						System.out.print('p');
					else if(map[player.x][player.y] == ' ')
						System.out.print('p');
				}
				else
					System.out.print(map[i][j]);	// 그 외의 경우 맵 출력
			}
			switch(i) {
			case 0: System.out.println("	o------------------------o");	// 맵 정보에 대한 내용 출력
				break;
			case 1: System.out.println("	|			 |");
				break;
			case 2: System.out.println("	| `: 벽			 |");
				break;
			case 3: System.out.println("	| o, -, |: 게임 경계	 |");
				break;
			case 4: System.out.println("	| s: 시작 위치		 |");
				break;
			case 5: System.out.println("	| p: 플레이어 위치		 |");
				break;
			case 6: System.out.println("	| 1, 2, 3, 4: 보스 위치	 |");
				break;
			case 7: System.out.println("	| T: 보물 위치		 |");
				break;
			case 8: System.out.println("	|			 |");
				break;
			case 9: System.out.println("	o------------------------o");
				break;
			}
		}
	}
	
	public static void movePlayer(Scanner scan, Player player, char[][] map) {	// 플레이어를 이동하는 함수(이동 가능한 방향을 나타내기도 함)
		int [] flag = new int [4];	// flag[0] = 동쪽, flag[1] = 서쪽, flag[2] = 남쪽, flag[3] = 북쪽	0인경우: 해당 방향 이동 불가능, 1인경우:해당 방향 이동 가능
		int whileFlag = 1;	// 이동 가능한 방향을 입력 받았을 경우 0이되어 while문을 빠져나갈 수 있게 함
		String order = new String();	// 이동 할 방향을 입력받을 변수
		if(map[player.x][player.y+1]=='|' || map[player.x][player.y+1]=='`')	// 동쪽으로 이동할 시 벽이 있는 경우 flag가 0이됨(이동 불가) 이동 가능한 경우 flag는 1이됨(이동 가능)
			flag[0] = 0;
		else
			flag[0] = 1;	
		if(map[player.x][player.y-1]=='|' || map[player.x][player.y-1]=='`')	// 서쪽으로 이동할 시 벽이 있는 경우 flag가 0이됨(이동 불가) 이동 가능한 경우 flag는 1이됨(이동 가능)
			flag[1] = 0;
		else
			flag[1] = 1;
		if(map[player.x+1][player.y]=='-' || map[player.x+1][player.y]=='`')	// 남쪽으로 이동할 시 벽이 있는 경우 flag가 0이됨(이동 불가) 이동 가능한 경우 flag는 1이됨(이동 가능)
			flag[2] = 0;
		else
			flag[2] = 1;
		if(map[player.x-1][player.y]=='-' || map[player.x-1][player.y]=='`')	// 북쪽으로 이동할 시 벽이 있는 경우 flag가 0이됨(이동 불가) 이동 가능한 경우 flag는 1이됨(이동 가능)
			flag[3] = 0;
		else
			flag[3] = 1;
		
		int flagSum = flag[0] + flag[1] + flag[2] + flag[3];	// flag의 합을 계산(출력시 ,를 넣을지에 대한 부분에서 이용됨 ex) flag[0] = 1일때 flagSum-1이 0이라면 이동 가능한 방향은 동쪽뿐 따라서 ,를 안쓰고 (동)으로 표시해 주면 됨)
		
		System.out.print("안내> 이동할 방향을 입력하세요.  (");
		
		if(flag[0] == 1 && flagSum-1 == 0)	// 동쪽으로 이동가능하고 flagSum-1이 0이라면 동쪽으로만 이동 가능
			System.out.println("동)");	// 이와같이 출력
		else if(flag[0] == 1 && flagSum-1 != 0) {	// 동쪽으로 이동가능하고 flagSum-1이 0이 아니라면 동쪽외의 다른 방향도 이동 가능
			System.out.print("동,");		// 이와 같이 출력
			flagSum -= 1;	// flagSum에 1을 빼줌
		}
		if(flag[1] == 1 && flagSum-1 == 0)	// 서쪽으로 이동가능하고 flagSum-1이 0이라면 남은 방향(동쪽 제외)중 서쪽으로만 이동 가능
			System.out.println("서)");	// 이와같이 출력
		else if(flag[1] == 1 && flagSum-1 != 0) {	// 서쪽으로 이동가능하고 flagSum-1이 0이 아니라면 남은 방향(동쪽 제외)중 서쪽외의 다른 방향도 이동 가능
			System.out.print("서,");		// 이와 같이 출력
			flagSum -= 1;	// flagSum에 1을 빼줌
		}
		if(flag[2] == 1 && flagSum-1 == 0)	// 남쪽으로 이동가능하고 flagSum-1이 0이라면 남은 방향(남쪽 ,북쪽)중 남쪽으로만 이동 가능
			System.out.println("남)");	// 이와같이 출력
		else if(flag[2] == 1 && flagSum-1 != 0)		// 남쪽으로 이동가능하고 flagSum-1이 0이 아니라면 북쪽으로도 이동 가능
			System.out.print("남,");		// 이와같이 출력
		if(flag[3] == 1) // 북쪽으로 이동이 가능하다면
			System.out.println("북)");	// 이와같이 출력(북쪽은 가장 마지막에 다루기 때문에 북쪽으로 이동 가능하다면 ,를 쓸 필요 없이 바로 ')'로 닫아주면 됨)
		/*-----------------------------------*/	// 그림으로 이동 가능한 방향 시각화
		if(flag[3] == 1)
			System.out.println(" |");
		else
			System.out.println("");
		if(flag[1] == 1)
			System.out.print("-");
		else
			System.out.print(" ");
		System.out.print("p");
		if(flag[0] == 1) 
			System.out.println("-");
		else
			System.out.println("");
		if(flag[2] == 1)
			System.out.println(" |");
		else
			System.out.println("");
		/*------------------------------------*/
		while(whileFlag == 1) {	// 이동 가능한 방향을 제대로 입력 받기 전까지 계속 해서 반복
			System.out.print("플레이어> ");
			try {
				order = scan.next();	// 이동 방향을 입력 받음
				System.out.println("");
				if(order.equals("동") == false && order.equals("서") == false &&order.equals("남") == false &&order.equals("북") == false )	// 동,서,남,북 이외의 이상한 입력을 한 경우
					throw new Exception();	// 예외처리해줌
				else {
					if(order.equals("동")) {	// 동을 입력한 경우
						if(flag[0]==1) {	// 동쪽으로 이동이 가능하다면 플레이어의 y값을 1증가시켜주고(동쪽으로 이동) whileFlag를 0으로 만들어 while문에서 빠져나감
							player.y += 1;
							whileFlag = 0;
						}
						else	// 동쪽으로 이동이 불가능하다면 잘못된 입력이라 표현해줌(다시 입력 받음)
							System.out.println("안내> 잘못된 명령어입니다. 다시 입력하세요.");
					}
					else if(order.equals("서")) {	// 서를 입력한 경우
						if(flag[1]==1) {	// 서쪽으로 이동이 가능하다면 플레이어의 y값을 1감소시켜주고(서쪽으로 이동) whileFlag를 0으로 만들어 while문에서 빠져나감
							player.y -= 1;
							whileFlag = 0;
						}
						else	// 서쪽으로 이동이 불가능하다면 잘못된 입력이라 표현해줌(다시 입력 받음)
							System.out.println("안내> 잘못된 명령어입니다. 다시 입력하세요.");
					}
					else if(order.equals("남")) {	// 남을 입력한 경우
						if(flag[2]==1) {	// 남쪽으로 이동이 가능하다면 플레이어의 x값을 1증가시켜주고(남쪽으로 이동) whileFlag를 0으로 만들어 while문에서 빠져나감
							player.x += 1;
							whileFlag = 0;
						}
						else	// 남쪽으로 이동이 불가능하다면 잘못된 입력이라 표현해줌(다시 입력 받음)
							System.out.println("안내> 잘못된 명령어입니다. 다시 입력하세요.");
					}
					else if(order.equals("북")) {	// 북을 입력한 경우
						if(flag[3]==1) {	// 북쪽으로 이동이 가능하다면 플레이어의 x값을 1감소시켜주고(북쪽으로 이동) whileFlag를 0으로 만들어 while문에서 빠져나감
							player.x -= 1;
							whileFlag = 0;
						}
						else	// 북쪽으로 이동이 불가능하다면 잘못된 입력이라 표현해줌(다시 입력 받음)
							System.out.println("안내> 잘못된 명령어입니다. 다시 입력하세요.");
					}
				}
			}catch(Exception x) {	// 예외 처리 부분(동,서,남,북 외의 이상한 입력을 받은 경우) 잘못된 입력이라 표현해줌(다시 입력 받음)
				System.out.println("안내> 잘못된 명령어입니다. 다시 입력하세요.");
			}
		}
	}
	
	public static int checkEvent(Scanner scan, Player player, char [][] map) {	// 이벤트를 확인하고 있다면(보스or보물상자) 진행하는 함수
		int clear = 1;	// 게임이 진행중인지 끝난지를 체크해주기 위한 변수(0:clear, 1:진행중(player.curhp = 0이면 게임 실패))
		if(map[player.x][player.y] == '1') {	// 플레이어의 위치가 1번 보스가 있는 곳이라면
			printMap(player, map);	// 해당 위치를 출력해줌(도달했다고)
			Boss(1, scan, player);	// 1번 보스와의 대결을 시작
			if(player.curhp > 0) {	// 보스 클리어시 해당 보스가 있던 자리를 공백으로 바꿈
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == '2') {	// 플레이어의 위치가 2번 보스가 있는 곳이라면
			printMap(player, map);	// 해당 위치를 출력해줌(도달했다고)
			Boss(2, scan, player);	// 2번 보스와의 대결을 시작
			if(player.curhp > 0) {	// 보스 클리어시 해당 보스가 있던 자리를 공백으로 바꿈
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == '3') {	// 플레이어의 위치가 3번 보스가 있는 곳이라면
			printMap(player, map);	// 해당 위치를 출력해줌(도달했다고)
			Boss(3, scan, player);	// 3번 보스와의 대결을 시작
			if(player.curhp > 0) {	// 보스 클리어시 해당 보스가 있던 자리를 공백으로 바꿈
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == '4') {	// 플레이어의 위치가 4번 보스가 있는 곳이라면
			printMap(player, map);	// 해당 위치를 출력해줌(도달했다고)
			Boss(4, scan, player);	// 4번 보스와의 대결을 시작
			if(player.curhp > 0) {	// 보스 클리어시 해당 보스가 있던 자리를 공백으로 바꿈
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == 'T') {	// 플레이어의 위치가 T(보물상자)가 있는 곳이라면
			printMap(player, map);	// 해당 위치를 출력해줌(도달했다고)
			if(player.killBossCount == 4) {	// 네 보스를 모두 이긴 경우 게임 승리
				System.out.println("축하합니다. 스테이지를 클리어하셨습니다!");
				clear = 0;	// clear을 0으로 만들어 main에서 재시작 할지를 선택하게 함
			}
			else {	// 만약 네 보스를 이긴 것이 아닌 경우 아직 모든 보스를 쓰러트리지 못했다고 출력(게임을 계속 진행)
				System.out.println("안내> 아직 모든 보스를 쓰러트리지 못하였습니다.");
			}
		}
		return clear;	// clear를 리턴해줌(보물상자를 찾아 게임을 클리어한 경우 0을 리턴 이외의 경우 1 리턴)
	}
	
	public static void Boss(int BossNum, Scanner scan, Player player) {	// 보스를 생성하고 대결하는 함수
		BossMonster boss = new BossMonster(BossNum);	// 보스 번호에 따른 능력치를 가진 보스를 만듦
		
		System.out.println("안내> 보스 출현.");		// 안내 문구
		System.out.println("안내> 보스와 블랙잭을 진행합니다.");
		System.out.println("안내> 보스의 체력을 모두 소진시켜 물리치세요.");
		System.out.println("");
		while(true) {	// 게임 진행
			System.out.println("안내> 플레이어 (" + player.curhp + "/" + player.maxhp + ") / 보스 (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");	// 플레이어와 보스의 체력 표시
			BlackJack(scan, boss, player);	// 블랙잭을 함(둘 중 한명의 피가 0이하가 될 때까지 반복
			System.out.println("");
			System.out.println("");
			
			if(boss.bossCurhp <= 0) {	// 보스의 체력이 0이하라면 보스를 물리친 것
				player.ClearBoss();	// 보스를 죽인 수를 1 증가시킴
				System.out.println("안내> 보스를 물리쳤습니다.");	
				break;	// while문을 빠져나옴
			}
			if(player.curhp <= 0) {	// 플레이어의 체력이 0이하라면 플레이어의 패배
				break;	// while문을 빠져나옴 플레이어 사망시 메세지는 main에 있음
			}
		}
	}
	
	public static void BlackJack(Scanner scan, BossMonster boss, Player player) {	// 블랙잭을 진행하는 함수
		String [][] card = new String [4][13];	// card의 이름을 저장하기 위한 변수 card[0][?]에 해당하는 것은 스페이드, 1: 클로버, 2:하트, 3:다이아 card[?][0]에 해당하는 것은 A, 1: 2, ...(index의 번호 + 1에 해당하는 것이 카드에 해당하는 것)
		int [][] cardOwner = new int [4][13];	// 0: 뽑히지 않음, 1: 뽑힘(카드가 중복되어 뽑히는 것을 막기 위함)
		String cardShape;	// 카드의 모양을 다루기 위한 변수(숫자와 합하여 card배열의 알맞은 위치에 저장하기 위함)
		String number;		// 카드의 숫자를 다루기 위한 변수(모양과 합하여 card배열의 알맞은 위치에 저장하기 위함)
		int bossDrawCount = 0;	// 보스가 뽑은 카드의 수
		int playerDrawCount = 0;	// 플레이어가 뽑은 카드의 수
		int [][] bossDrawCardIndex = new int [11][2];	// 보스가 뽑은 카드를 순서대로 나열하기 위한 정보 저장용 (최대 1,1,1,1,2,2,2,2,3,3,3까지 가능)
		int [][] playerDrawCardIndex = new int [11][2];	// 플레이어가 뽑은 카드를 순서대로 나열하기 위한 정보 저장용 (최대 1,1,1,1,2,2,2,2,3,3,3까지 가능)
		String order = "x";	// 'h'도 's'도 아닌 어떤 값(아무 의미 없음) (히트(h)와 스탠드(s) 명령어를 입력받기 위한 변수)
		int playerSum = 0;	// 플레이어의 총 카드의 합
		int bossSum = 0;	// 보스의 총 카드의 합
		
		for(int i = 0; i < 4; i++) {	
			if(i == 0) {	// i = 0이면 카드의 모양은 스페이드
				cardShape = "s";
			}
			else if(i == 1) {	// i = 1이면 카드의 모양은 클로버
				cardShape = "c";
			}
			else if(i == 2) {	// i = 2이면 카드의 모양은 하트
				cardShape = "h";
			}
			else  {
				cardShape = "d";	// i = 3이면 카드의 모양은 다이아몬드
			}
			for(int j=0; j<13; j++) {	
				if (j == 0) {	// j = 0이면 A카드
					number = "A";
				}
				else if(j == 10) {	// j = 10이면 J카드
					number = "J";
				}
				else if(j == 11) {	// j = 11이면 Q카드
					number = "Q";
				}
				else if(j == 12) {	// j = 12이면 K카드
					number = "K";
				}
				else	// 이외의 경우 (인덱스(j) + 1)에 해당하는 숫자 카드
					number = Integer.toString(j+1);
				card[i][j] = cardShape + number;	// 카드 배열에 카드의 모양+숫자에 해당하는 값으로 저장함(실제 트럼프 카드와 같이 저장)
				cardOwner[i][j] = 0;	// 해당 카드의 주인을 0(아직 없음, 뽑히지 않음)이라고 해둠
			}
		}
		// 카드를 2장 먼저 뽑고 -> (보여주고 -> 계산하고(승패가 나뉘었는지도 확인) -> 히트스탠드 선택 -> (히트시)카드 뽑기 -> 괄호의 맨앞으로 반복) 와 같은 형식으로 진행함 
		bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);	// 보스가 카드 2장을 뽑음
		bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);
		
		playerDrawCount = DrawCard(playerDrawCardIndex, playerDrawCount, cardOwner);	// 플레이어가 카드 2장을 뽑음
		playerDrawCount = DrawCard(playerDrawCardIndex, playerDrawCount, cardOwner);
		
		while(true) {	// 게임이 종료될때까지 진행(블랙잭 혹은 버스트 혹은 스탠드 후 둘의 비교 등이 진행될때까지 진행)
			if(order.equals("s")==false) {	// 스탠드를 하지 않은 상태(히트또는 처음 2장을 뽑은 직후 상황에 해당)
				int flag = 0;	// h 또는 s를 제대로 입력 받도록 만들기 위한 변수
				ShowCard(1, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// 카드를 보여줌(situation1의 경우 보스의 마지막(두번쨰) 카드는 ?(비공개))
				if(playerSum == 0) {	// 맨 처음 2장을 받았을 때 합 계산하기(A가 있다면 A의 값을 결정하여 합계산)
					playerSum = PlayerCalculate(scan, 1, playerDrawCardIndex, playerDrawCount, playerSum);	
				}
				else {	// hit한 경우에 합 계산(A가 있다면 A의 값을 결정하여 합계산)
					playerSum = PlayerCalculate(scan, 2, playerDrawCardIndex, playerDrawCount, playerSum);
				}
				
				if(playerSum > 21) {	// 플레이어의 합이 21보다 큰 경우 버스트로 패배
					ShowCard(2, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// 보스의 카드 공개
					bossSum = BossCalculate(1, bossDrawCardIndex, bossDrawCount, bossSum);	// 보스의 합 계산
					if(bossSum == 21) {
						System.out.println("안내> 보스 : " + bossSum + " (블랙잭) | 플레이어 :" + playerSum + " (버스트)");	// 둘의 점수와 보스의 블랙잭 및 플레이어의 버스트로 패배함을 공개
						player.curhp -= 2*boss.bossAtk;	// 플레이어의 체력을 보스의 공격력*2만큼 줄어들게됨
					}
					else {
						System.out.println("안내> 보스 : " + bossSum + "| 플레이어 :" + playerSum + " (버스트)");	// 둘의 점수와 버스트로 패배함을 공개
						player.curhp -= boss.bossAtk;	// 플레이어의 체력을 보스의 공격력만큼 줄어들게됨
					}
					System.out.println("보스 " + boss.bossNum + "> 플레이어 패배 | 플레이어 (" + + player.curhp + "/" + player.maxhp + ") / 보스 (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");	// 플레이어의 패배 및 현재 체력상황을 출력
					break;	 // 한 게임이 종료되었으므로 while문을 빠져나감(Boss함수에서 둘 중 한명의 체력이 0이하가 될 때까지 블랙잭을 계속해서 반복하게 함)
				}
				
				if(playerSum == 21) {	// 플레이어가 블랙잭인 경우 무조건 승리
					ShowCard(2, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// 보스의 카드 공개
					bossSum = BossCalculate(1, bossDrawCardIndex, bossDrawCount, bossSum);	// 보스의 합 계산
					if(bossSum == 21)	// 보스도 블랙잭인 경우의 문구 출력(그래도 플레이어가 승리함 - 규칙)
						System.out.println("안내> 보스 : " + bossSum + " (블랙잭) | 플레이어 :" + playerSum + " (블랙잭)");
					else 				// 그 외의 경우의 문구 출력
						System.out.println("안내> 보스 : " + bossSum + " | 플레이어 :" + playerSum + " (블랙잭)");
					boss.bossCurhp -= 2;	// 보스에게 2배의 데미지를 줌
					System.out.println("보스 " + boss.bossNum + "> 플레이어 승리 | 플레이어 (" + + player.curhp + "/" + player.maxhp + ") / 보스 (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");	// 플레이어의 승리 및 현재 체력상황을 출력
					break;	// 한 게임이 종료되었으므로 while문을 빠져나감(Boss함수에서 둘 중 한명의 체력이 0이하가 될 때까지 블랙잭을 계속해서 반복하게 함)
				}
				
				while(flag == 0) {	// h또는 s를 제대로 입력할 때까지 반복
					System.out.println("안내> 히트(h)? 스탠드(s)?");
					System.out.print("플레이어> ");
					try {
						order = scan.next();	// 입력을 받음
						if(order.equals("h")) {	// 히트를 하는 경우
							flag = 1;	// flag를 1로 만들어 while문을 빠져나감
							playerDrawCount = DrawCard(playerDrawCardIndex, playerDrawCount, cardOwner);	// 한장을 뽑음(히트함)
						}
						else if(order.equals("s")) {	// 스탠드를 하는 경우
							flag = 1;	// flag를 1로 만들어 while문을 빠져나감 (바깥 while문의 스탠드에 해당하는 상태로 이동하게 됨)
						}
						else {	// 그 외의 잘못된 입력인 경우 flag가 1이 아니므로 다시 입력 받음
							throw new Exception();
						}
					}catch(Exception e) {	// 잘못된 입력임을 출력하고 다시 입력 받음
						System.out.println("안내> 잘못된 입력입니다.");
					}
				}
			}
			else {	// 스탠드를 한 상태 if(order.equals("s")==true)	
				ShowCard(2, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// 보스의 패 공개
				
				if(bossSum == 0) {	// 맨 처음 2장을 받았을 때 합 계산하기(A가 있다면 A의 값을 결정하여 합계산)
					bossSum = BossCalculate(1, bossDrawCardIndex, bossDrawCount, bossSum);	
				}
				else {	// hit한 경우에 합 계산(A가 있다면 A의 값을 결정하여 합계산)
					bossSum = BossCalculate(2, bossDrawCardIndex, bossDrawCount, bossSum);
				}
				
				if(bossSum > 21) {	// 보스의 합이 21보다 큰 경우 경우 버스트로 플레이어 승리(플레이어가 버스트거나 블랙잭인 경우는 hit에 해당하는 if문에서 자동으로 판정됨)
					System.out.println("안내> 보스 : " + bossSum + " (버스트) | 플레이어 :" + playerSum);
					boss.bossCurhp -= 1;	// 보스에게 1의 데미지를 줌
					System.out.println("보스 " + boss.bossNum + "> 플레이어 승리 | 플레이어 (" + + player.curhp + "/" + player.maxhp + ") / 보스 (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
					break;	// 게임 1번 종료
				}
				
				if(bossSum > playerSum) {	// bossSum > playerSum인 경우(그리고 bossSum <=21인 경우)
					if(bossSum == 21) {	// bossSum이 21이면 블랙잭으로 2배의 데미지를 줌 (플레이어가 블랙잭인 경우는 위에서 자동으로 판별 되었음)
						System.out.println("안내> 보스 : " + bossSum + " (블랙잭) | 플레이어 :" + playerSum);
						player.curhp -= boss.bossAtk*2;	// 플레이어에게 2배의 데미지를 줌
					}
					else {	// 그 외의 경우 일반 데미지
						System.out.println("안내> 보스 : " + bossSum + " | 플레이어 :" + playerSum);
						player.curhp -= boss.bossAtk;	// 플레이어에게 일반 데미지를 줌
					}	// 플레이어의 패배 문구 출력
					System.out.println("보스 " + boss.bossNum + "> 플레이어 패배 | 플레이어 (" + + player.curhp + "/" + player.maxhp + ") / 보스 (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
					break;	// 게임 1번 종료
				}
				else if(bossSum == playerSum) {	//(bossSum = playerSum인 경우 16보다 작거나 같으면 히트, 17이상이면 스탠드함(조건)
					if(bossSum <= 16) {	// 히트
						bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);
					}
					else {	// 17이상이면 스탠드(푸쉬에 해당하는 부분임)
						System.out.println("안내> 보스 : " + bossSum + "| 플레이어 :" + playerSum);
						System.out.println("보스 " + boss.bossNum + "> 무승부 | 플레이어 (" + + player.curhp + "/" + player.maxhp + ") / 보스 (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
						break;	// 게임 1번 종료
					}
				}
				else {	// bossSum < playerSum
					if(bossSum <= 16) {	// 히트
						bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);
					}
					else {	// 스탠드(플레이어의 승리)
						System.out.println("안내> 보스 : " + bossSum + "| 플레이어 :" + playerSum);
						boss.bossCurhp -= 1;	// 보스에게 1의 데미지를 입힘
						System.out.println("보스 " + boss.bossNum + "> 플레이어 승리 | 플레이어 (" + + player.curhp + "/" + player.maxhp + ") / 보스 (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
						break;	// 게임 1번 종료
					}
				}
			}
		}	
	}	
	
	public static int PlayerCalculate(Scanner scan, int situation, int playerDrawCardIndex[][], int playerDrawCount, int sum) {	// 플레이어가 뽑은 카드의 숫자의 합을 계산하는 함수
		int getNumber = 0;	// A가 나왔을 때 1 또는 11의 숫자값을 입력 받는 변수
		int flag = 0;	// A가 나왔을 때 1 또는 11의 숫자를 입력하도록 만들기 위한 변수
		if(situation == 1) {	// 맨 처음 두 카드를 뽑은 직후 합을 계산 할 때
			for(int i=0; i< playerDrawCount; i++) {		// playerDrawCount = 2임
				if(playerDrawCardIndex[i][1] == 0) {	// A카드인 경우
					while(flag == 0) {
						System.out.print("A의 값을 정해주세요(1 or 11):");	// 1 또는 11을 입력 받음
						getNumber = scan.nextInt();	
						try {
							if(getNumber == 1) {	// 1을 고른 경우
								sum += 1;			// 숫자의 합에 1을 더해주고
								flag = 1;			// while문을 빠져나옴
							}
							else if(getNumber == 11) {	// 11을 고른경우
								sum += 11;				// 숫자의 합에 11을 더해주고
								flag = 1;				// while문을 빠져나옴
							}
							else {	// 이 외의 경우 예외처리
								throw new Exception();
							}
						}catch(Exception e) {	// 1또는 11만 입력 받도록 잘못된 입력이라 출력하고 다시 입력 받음
							System.out.println("안내> 잘못된 입력입니다.");
						}
					}
				}
				else if (playerDrawCardIndex[i][1] > 9) {	// J,Q,K의 경우 숫자의 합을 계산 할때 10으로 처리
					sum += 10;
				}
				else {
					sum += playerDrawCardIndex[i][1] + 1;	// 이 외의 경우 playerDrawCardIndex(플레이어가 지금까지 뽑은 카드에 해당하는 인덱스)에 저장되어있는 값을 참고하여 해당하는 숫자를 이용하여 계산 ex) 맨처음 뽑은 카드가 d2라면 card[3][1]에 해당함 -> playerDrawCardIndex[0][0] = 3 ,[0][1] = 1이됨 (숫자의 경우 실제론 인덱스 값에 1을 더한 값이 진짜 값이 됨)
				}
			}
		}
		else {	// hit로 카드를 더 뽑을 때
			if(playerDrawCardIndex[playerDrawCount-1][1] == 0) {	// A카드인 경우
				while(flag == 0) {	
					System.out.print("A의 값을 정해주세요(1 or 11):");	// 1 또는 11을 입력 받음
					getNumber = scan.nextInt();
					try {
						if(getNumber == 1) {	// 1을 고른 경우
							sum += 1;			// 숫자의 합에 1을 더해주고
							flag = 1;			// while문을 빠져나옴
						}
						else if(getNumber == 11) {	// 11을 고른경우
							sum += 11;				// 숫자의 합에 11을 더해주고
							flag = 1;				// while문을 빠져나옴
						}
						else {	// 이 외의 경우 예외처리
							throw new Exception();
						}
					}catch(Exception e) {	// 1또는 11만 입력 받도록 잘못된 입력이라 출력하고 다시 입력 받음
						System.out.println("안내> 잘못된 입력입니다.");
					}
				}
			}
			else if (playerDrawCardIndex[playerDrawCount-1][1] > 9) {	// J,Q,K의 경우 숫자의 합을 계산 할때 10으로 처리
				sum += 10;
			}
			else {
				sum += playerDrawCardIndex[playerDrawCount-1][1] + 1;	// 이 외의 경우 playerDrawCardIndex(플레이어가 지금까지 뽑은 카드에 해당하는 인덱스)에 저장되어있는 값을 참고하여 해당하는 숫자를 이용하여 계산 ex) 맨처음 뽑은 카드가 d2라면 card[3][1]에 해당함 -> playerDrawCardIndex[0][0] = 3 ,[0][1] = 1이됨 (숫자의 경우 실제론 인덱스 값에 1을 더한 값이 진짜 값이 됨)
			}
		}
		return sum;	// 계산한 합을 리턴 해줌
	}
	
	public static int BossCalculate(int situation, int bossDrawCardIndex[][], int bossDrawCount, int sum) {	// 보스가 뽑은 카드의 숫자의 합을 계산하는 함수
		if(situation == 1) {	// 맨 처음 두 카드를 뽑은 직후 합을 계산 할 때
			for(int i=0; i< bossDrawCount; i++) {	// bossDrawCount = 2임
				if(bossDrawCardIndex[i][1] == 0) {	// A카드인 경우
					if(sum + 11 > 21) 	// 11로 골랐을 때 버스트가 된다면 1로 고름
						sum += 1;
					else				// 버스트가 되지 않는 다면 11로 고름(조건) 
						sum += 11;
				}
				else if (bossDrawCardIndex[i][1] > 9) {	// J,Q,K의 경우 숫자의 합을 계산 할때 10으로 처리
					sum += 10;
				}
				else {
					sum += bossDrawCardIndex[i][1] + 1;	// 이 외의 경우 bossDrawCardIndex(보스가 지금까지 뽑은 카드에 해당하는 인덱스)에 저장되어있는 값을 참고하여 해당하는 숫자를 이용하여 계산 ex) 맨처음 뽑은 카드가 d2라면 card[3][1]에 해당함 -> bossDrawCardIndex[0][0] = 3 ,[0][1] = 1이됨 (숫자의 경우 실제론 인덱스 값에 1을 더한 값이 진짜 값이 됨) 
				}
			}
		}
		else {		// hit로 카드를 더 뽑을 때
			if(bossDrawCardIndex[bossDrawCount-1][1] == 0) {	// A카드인 경우
				if(sum + 11 > 21) 	// 11로 골랐을 때 버스트가 된다면 1로 고름
					sum += 1;
				else 				// 버스트가 되지 않는 다면 11로 고름(조건) 
					sum += 11;		
			}
			else if (bossDrawCardIndex[bossDrawCount-1][1] > 9) {	// J,Q,K의 경우 숫자의 합을 계산 할때 10으로 처리
				sum += 10;
			}
			else {
				sum += bossDrawCardIndex[bossDrawCount-1][1] + 1;	// 이 외의 경우 bossDrawCardIndex(보스가 지금까지 뽑은 카드에 해당하는 인덱스)에 저장되어있는 값을 참고하여 해당하는 숫자를 이용하여 계산 ex) 맨처음 뽑은 카드가 d2라면 card[3][1]에 해당함 -> bossDrawCardIndex[0][0] = 3 ,[0][1] = 1이됨 (숫자의 경우 실제론 인덱스 값에 1을 더한 값이 진짜 값이 됨) 
			}
		}
		return sum;	// 계산한 합을 리턴 해줌
	}
	
	public static int DrawCard(int gamerDrawCardIndex[][], int gamerDrawCount, int cardOwner[][]) {	// 카드를 뽑는 함수(히트 또는 첫 2장의 카드를 뽑을 시 이용)
		int randNumber;	// 랜덤한 카드를 뽑기위한 변수
		while(true) {	// cardOwner가 0인(뽑히지 않은) 카드를 뽑을 때까지 반복(중복을 없애줌)
			randNumber = (int)(Math.random()*52);	// randNumber : 0 ~ 51
			if(cardOwner[randNumber/13][randNumber%13] == 0) {	// cardOwner가 0인 경우 
				cardOwner[randNumber/13][randNumber%13] = 1;	// 해당 카드가 뽑혔다고 처리해주고 1로 만듦
				gamerDrawCardIndex[gamerDrawCount][0] = randNumber/13;	// 해당 카드를 뽑은 사람의 카드 인덱스를 다루는 변수에 뽑은 카드의 인덱스를 저장
				gamerDrawCardIndex[gamerDrawCount][1] = randNumber%13;	// 해당 카드를 뽑은 사람의 카드 인덱스를 다루는 변수에 뽑은 카드의 인덱스를 저장
				gamerDrawCount++;	// 해당 카드를 뽑은 사람의 뽑은 카드 수를 1증가 시킴
				break;	// while문을 빠져나옴(중복이 없으므로)
			}
		}
		return gamerDrawCount;	// 해당 카드를 뽑은 사람의 뽑은 카드 수를 리턴해줌
	}
	
	public static void ShowCard(int situation, int bossDrawCardIndex[][], int playerDrawCardIndex[][], String card[][], int bossDrawCount, int playerDrawCount) {	// 뽑힌 카드의 상황을 알려줌(situation: 1인 경우 보스의 마지막(두번째) 카드는 ?로 가려져 있음, 2인 경우 모든 뽑힌 카드 정보 오픈)
		if(situation == 1) {	// boss의 패 미공개
			System.out.print("안내> 보스 : ");
			for(int i = 0; i < bossDrawCount-1; i++) {	// 마지막 카드는 보여주지 않음
				System.out.print(card[bossDrawCardIndex[i][0]][bossDrawCardIndex[i][1]]);	// 보스의 카드 정보 출력
				if(i + 1 < bossDrawCount) {	// 이번 카드가 마지막 카드가 아니라면 / 출력
					System.out.print("/");
				}
			}
			System.out.print("? | 플레이어 : ");	// 마지막 카드는 ?로 출력
			for(int i = 0; i < playerDrawCount; i++) {	
				System.out.print(card[playerDrawCardIndex[i][0]][playerDrawCardIndex[i][1]]);	// 플레이어의 모든 카드 정보 공개
				if(i + 1 < playerDrawCount) { // 이번 카드가 마지막 카드가 아니라면 / 출력
					System.out.print("/");
				}
			}
			System.out.println("");
		}
		if(situation == 2) {	// boss의 패 공개
			System.out.print("안내> 보스 : ");
			for(int i = 0; i < bossDrawCount; i++) {
				System.out.print(card[bossDrawCardIndex[i][0]][bossDrawCardIndex[i][1]]);	// 보스의 모든 카드 정보 출력
				if(i + 1 < bossDrawCount) {	// 이번 카드가 마지막 카드가 아니라면 / 출력
					System.out.print("/");
				}
			}
			System.out.print(" | 플레이어 : ");
			for(int i = 0; i < playerDrawCount; i++) {
				System.out.print(card[playerDrawCardIndex[i][0]][playerDrawCardIndex[i][1]]);	// 플레이어의 모든 카드 정보 공개
				if(i + 1 < playerDrawCount) { // 이번 카드가 마지막 카드가 아니라면 / 출력
					System.out.print("/");
				}
			}
			System.out.println("");
		}
	}
}

class Player {	// 플레이어 좌표 및 체력 정보, 보스를 처치한 수를 다룸
	int x;	// 맵에서의 위치를 다루는 변수
	int y;	// 맵에서의 위치를 다루는 변수
	int curhp;	// 현재 체력을 다루는 변수
	int maxhp;	// 최대 체력을 다루는 변수
	int killBossCount;	// 보스를 이긴 수
	
	public Player(int x, int y) {	// 플레이어를 만들 때 위치 정보를 받아 선언 함
		this.x = x;
		this.y = y;
		this.curhp = 5;
		this.maxhp = 5;
		this.killBossCount = 0;
	}
	
	public void ClearBoss() {	// 보스를 이긴 경우 체력을 2증가시키고 killBossCount를 1증가시킴 
		this.curhp += 2;
		this.maxhp += 2;
		this.killBossCount += 1;
	}
	
	public void regame(int x, int y) {	// 게임을 다시할 경우 시작 지점의 x,y좌표로 이동하고 체력과 killBossCount를 시작 시 값으로 초기화 함
		this.x = x;
		this.y = y;
		this.curhp = 5;
		this.maxhp = 5;
		this.killBossCount = 0;
	}
}

class BossMonster{	// 보스 몬스터의 숫자(1번 보스, 2번 보스, 3번 보스, 4번 보스), 해당 보스의 체력, 해당 보스의 공격력을 다룸
	int bossNum;	// 보스의 번호
	int bossMaxhp;	// 보스의 최대 체력
	int bossCurhp;	// 보스의 현재 체력
	int bossAtk;	// 보스의 공격력
	
	public BossMonster(int bossNum) {	// 보스를 만들 때 보스 몬스터의 숫자를 이용하여 선언
		this.bossNum = bossNum;	
		this.bossMaxhp = 4 + bossNum;
		this.bossCurhp = bossMaxhp;	
		this.bossAtk = bossNum;
	}
}