package WP2_2019253025;

import java.util.Scanner;
public class game {
	
	public static void main(String[] args) {
		char [][] map = new char [10][31];			// �������� �����ϱ� ���� �迭
		Scanner scan = new Scanner(System.in);		// System.in�� ���� Scanner��ü�� ����
		int mapChoice = (int)(Math.random()*3 + 1);	// map1, map2, map3�� �÷����� ���� �������� ���� ���� ����
		switch(mapChoice) {	// ������ ���� �������� ����(map�迭�� ������ ����)
		case 1: map1(map);
			break;
		case 2: map2(map);
			break;
		case 3: map3(map);
			break;
		}

		int clear = 1;	// clear == 0 ���� Ŭ����, 1: ������
		int endCode = 1;	// �÷��̾� ��� Ȥ�� ���� Ŭ����� ������ �ٽ� ����: 1, �״�� ���� : 0
		Player player = new Player(8,1);	// ���� ��ǥ(�� ���� ������ǥ�� ��� ����) player�� ����(ü��, ���� ��ġ, ���� ������ �� ���� ������ ������ ����)
		while(true) {	// ������ ������ ���� �ݺ�
			printMap(player, map);	// ���� ���
			movePlayer(scan, player, map);	// �÷��̾ �̵�
			clear = checkEvent(scan, player, map);	// ���� �ش� ĭ���� �̺�Ʈ(���� �Ǵ� �������� �߰�)�� �߻��Ѵٸ� �ش� �̺�Ʈ ����
			if(clear == 0) {	// clear�� 0�̶�� �� ������ ��� ó���ϰ� ���� ���ڸ� ���� ���(���� Ŭ����)
				endCode = finishAndRestart(scan, map, player, mapChoice);	// T(����)�� ������ ���� �¸� �ȳ��� ����ϰ� ���� �ٽ� ���� ���θ� ���
			}
			else {	// clear�� 1 �� ������ �������ε� �÷��̾ ���� ���(���� ����)
				if(player.curhp <= 0) {	// �÷��̾��� ü���� ��� ������ ��� ���� �й� �ȳ��� ����ϰ� ������ �ٽ� �������� ���
					System.out.println("�ȳ�> �÷��̾ ����Ͽ����ϴ�. ������ �����մϴ�.");	
					endCode = finishAndRestart(scan, map, player, mapChoice);	
				}
			}
			if(endCode == 0) // ������ �¸��ߵ� �߰��� �й��Ͽ��� �ٽ� �������� ��� �� ����(n)�� ������ ��� ������ ������
				break;
		}
		scan.close();	
	}
	
	public static int finishAndRestart(Scanner scan, char map[][], Player player, int mapChoice) {	// ������ �ٽ� �������� ����� �Լ�
		String restart;	// ������ ����� ���� ������ �Է¹޴� ����
		int endCode = 1;// ���ο� �����ϱ� ���� ����(1: ���� �����(������), 0: ���� ����)
		System.out.println("�ȳ�> ������ ����� �Ͻðڽ��ϱ�?(y or n)");
		System.out.print("�÷��̾�>");
		while(true) {
			try {
			restart = scan.next();	// ������ ����� ���� ���� �Է� ����
			if(restart.equals("y") == true) {	// y�� �Է¹��� ���
				switch(mapChoice) {	// ���� �������� �ʿ��� ������ �ٽ� �����ϱ� ���� �迭�� �����͸� �ʱ�ȭ����(���� óġ�� ����� �Ϻ� ������ ����)
				case 1: map1(map);
					break;
				case 2: map2(map);
					break;
				case 3: map3(map);
					break;
				}
				player.regame(8, 1); // �÷��̾��� ��ġ �ʱ�ȭ(���� ��ġ�� �̵�)
				break; // while���� ��������
			}
			else if(restart.equals("n") == true) {	// n�� �Է� ���� ���	
				System.out.println("�ȳ�> ������ �����մϴ�. ����ּż� �����մϴ�.");	// ���� ���� ���
				endCode = 0;	// endCode�� 0���� ����� main���� ������ �����ϵ��� �������
				break;	// while���� ��������
			}
			else {	// y�Ǵ� n�� �Է��� ��찡 �ƴ� ���
				throw new Exception();	// ���� ó��
			}
			}catch(Exception e) {	// y�Ǵ� n�� �Է��� ��찡 �ƴ� ���
				System.out.println("�ȳ�> �߸��� �Է��Դϴ�.");	// �߸��� �Է��̶� ����� while���� ���� �ٽ� �Է� ����
			}
		}
		return endCode;	// endCode�� ��������(1: ���� �����(������), 0: ���� ����)
	}
	public static void map1(char [][] map) {	// ��1�� ����(map�迭�� ä��)
		for(int i=0; i < 10; i++) {	
			for(int j=0; j< 31; j++) {
				if(i == 0 || i == 9) {		// ���Ʒ� ���� �� �����
					if(j==0 || j==30) {
						map[i][j] = 'o';
					}
					else {
						map[i][j] = '-';
					}
				}
				else {
					if(j == 0 || j == 30) {	// �¿� ���� �� �����
						map[i][j] = '|';
					}
					// ���� �����
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
	
	public static void map2(char [][] map) {	//��2�� ����(map�迭�� ä��)
		for(int i=0; i < 10; i++) {	
			for(int j=0; j< 31; j++) {
				if(i == 0 || i == 9) {		// ���Ʒ� ���� �� �����
					if(j==0 || j==30) {	
						map[i][j] = 'o';
					}
					else {
						map[i][j] = '-';
					}
				}
				else {
					if(j == 0 || j == 30) {	// �¿� ���� �� �����
						map[i][j] = '|';
					}
					// ���� �����
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
	public static void map3(char [][] map) {	//��3�� ����(map�迭�� ä��)
		for(int i=0; i < 10; i++) {	
			for(int j=0; j< 31; j++) {
				if(i == 0 || i == 9) {		// ���Ʒ� ���� �� �����
					if(j==0 || j==30) {
						map[i][j] = 'o';
					}
					else {
						map[i][j] = '-';
					}
				}
				else {
					if(j == 0 || j == 30) {	// �¿� ���� �� �����
						map[i][j] = '|';
					}
					// ���� �����
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
	public static void printMap(Player player, char[][] map) {	// ���� ��Ȳ�� ���
		System.out.println("���� ȭ��                        ����");
		for(int i=0; i<10; i++) {
			for(int j=0; j<31; j++) {
				if(i == player.x && j == player.y) {	// ���� �÷��̾��� ���� ��ġ�� �ش��ϴ� map�迭�� ���� s,1,2,3,4,T,�Ǵ� ������ ��� p�� ����ϵ��� ��(�÷��̾��� ���� ��ġ ���)
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
					System.out.print(map[i][j]);	// �� ���� ��� �� ���
			}
			switch(i) {
			case 0: System.out.println("	o------------------------o");	// �� ������ ���� ���� ���
				break;
			case 1: System.out.println("	|			 |");
				break;
			case 2: System.out.println("	| `: ��			 |");
				break;
			case 3: System.out.println("	| o, -, |: ���� ���	 |");
				break;
			case 4: System.out.println("	| s: ���� ��ġ		 |");
				break;
			case 5: System.out.println("	| p: �÷��̾� ��ġ		 |");
				break;
			case 6: System.out.println("	| 1, 2, 3, 4: ���� ��ġ	 |");
				break;
			case 7: System.out.println("	| T: ���� ��ġ		 |");
				break;
			case 8: System.out.println("	|			 |");
				break;
			case 9: System.out.println("	o------------------------o");
				break;
			}
		}
	}
	
	public static void movePlayer(Scanner scan, Player player, char[][] map) {	// �÷��̾ �̵��ϴ� �Լ�(�̵� ������ ������ ��Ÿ���⵵ ��)
		int [] flag = new int [4];	// flag[0] = ����, flag[1] = ����, flag[2] = ����, flag[3] = ����	0�ΰ��: �ش� ���� �̵� �Ұ���, 1�ΰ��:�ش� ���� �̵� ����
		int whileFlag = 1;	// �̵� ������ ������ �Է� �޾��� ��� 0�̵Ǿ� while���� �������� �� �ְ� ��
		String order = new String();	// �̵� �� ������ �Է¹��� ����
		if(map[player.x][player.y+1]=='|' || map[player.x][player.y+1]=='`')	// �������� �̵��� �� ���� �ִ� ��� flag�� 0�̵�(�̵� �Ұ�) �̵� ������ ��� flag�� 1�̵�(�̵� ����)
			flag[0] = 0;
		else
			flag[0] = 1;	
		if(map[player.x][player.y-1]=='|' || map[player.x][player.y-1]=='`')	// �������� �̵��� �� ���� �ִ� ��� flag�� 0�̵�(�̵� �Ұ�) �̵� ������ ��� flag�� 1�̵�(�̵� ����)
			flag[1] = 0;
		else
			flag[1] = 1;
		if(map[player.x+1][player.y]=='-' || map[player.x+1][player.y]=='`')	// �������� �̵��� �� ���� �ִ� ��� flag�� 0�̵�(�̵� �Ұ�) �̵� ������ ��� flag�� 1�̵�(�̵� ����)
			flag[2] = 0;
		else
			flag[2] = 1;
		if(map[player.x-1][player.y]=='-' || map[player.x-1][player.y]=='`')	// �������� �̵��� �� ���� �ִ� ��� flag�� 0�̵�(�̵� �Ұ�) �̵� ������ ��� flag�� 1�̵�(�̵� ����)
			flag[3] = 0;
		else
			flag[3] = 1;
		
		int flagSum = flag[0] + flag[1] + flag[2] + flag[3];	// flag�� ���� ���(��½� ,�� �������� ���� �κп��� �̿�� ex) flag[0] = 1�϶� flagSum-1�� 0�̶�� �̵� ������ ������ ���ʻ� ���� ,�� �Ⱦ��� (��)���� ǥ���� �ָ� ��)
		
		System.out.print("�ȳ�> �̵��� ������ �Է��ϼ���.  (");
		
		if(flag[0] == 1 && flagSum-1 == 0)	// �������� �̵������ϰ� flagSum-1�� 0�̶�� �������θ� �̵� ����
			System.out.println("��)");	// �̿Ͱ��� ���
		else if(flag[0] == 1 && flagSum-1 != 0) {	// �������� �̵������ϰ� flagSum-1�� 0�� �ƴ϶�� ���ʿ��� �ٸ� ���⵵ �̵� ����
			System.out.print("��,");		// �̿� ���� ���
			flagSum -= 1;	// flagSum�� 1�� ����
		}
		if(flag[1] == 1 && flagSum-1 == 0)	// �������� �̵������ϰ� flagSum-1�� 0�̶�� ���� ����(���� ����)�� �������θ� �̵� ����
			System.out.println("��)");	// �̿Ͱ��� ���
		else if(flag[1] == 1 && flagSum-1 != 0) {	// �������� �̵������ϰ� flagSum-1�� 0�� �ƴ϶�� ���� ����(���� ����)�� ���ʿ��� �ٸ� ���⵵ �̵� ����
			System.out.print("��,");		// �̿� ���� ���
			flagSum -= 1;	// flagSum�� 1�� ����
		}
		if(flag[2] == 1 && flagSum-1 == 0)	// �������� �̵������ϰ� flagSum-1�� 0�̶�� ���� ����(���� ,����)�� �������θ� �̵� ����
			System.out.println("��)");	// �̿Ͱ��� ���
		else if(flag[2] == 1 && flagSum-1 != 0)		// �������� �̵������ϰ� flagSum-1�� 0�� �ƴ϶�� �������ε� �̵� ����
			System.out.print("��,");		// �̿Ͱ��� ���
		if(flag[3] == 1) // �������� �̵��� �����ϴٸ�
			System.out.println("��)");	// �̿Ͱ��� ���(������ ���� �������� �ٷ�� ������ �������� �̵� �����ϴٸ� ,�� �� �ʿ� ���� �ٷ� ')'�� �ݾ��ָ� ��)
		/*-----------------------------------*/	// �׸����� �̵� ������ ���� �ð�ȭ
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
		while(whileFlag == 1) {	// �̵� ������ ������ ����� �Է� �ޱ� ������ ��� �ؼ� �ݺ�
			System.out.print("�÷��̾�> ");
			try {
				order = scan.next();	// �̵� ������ �Է� ����
				System.out.println("");
				if(order.equals("��") == false && order.equals("��") == false &&order.equals("��") == false &&order.equals("��") == false )	// ��,��,��,�� �̿��� �̻��� �Է��� �� ���
					throw new Exception();	// ����ó������
				else {
					if(order.equals("��")) {	// ���� �Է��� ���
						if(flag[0]==1) {	// �������� �̵��� �����ϴٸ� �÷��̾��� y���� 1���������ְ�(�������� �̵�) whileFlag�� 0���� ����� while������ ��������
							player.y += 1;
							whileFlag = 0;
						}
						else	// �������� �̵��� �Ұ����ϴٸ� �߸��� �Է��̶� ǥ������(�ٽ� �Է� ����)
							System.out.println("�ȳ�> �߸��� ��ɾ��Դϴ�. �ٽ� �Է��ϼ���.");
					}
					else if(order.equals("��")) {	// ���� �Է��� ���
						if(flag[1]==1) {	// �������� �̵��� �����ϴٸ� �÷��̾��� y���� 1���ҽ����ְ�(�������� �̵�) whileFlag�� 0���� ����� while������ ��������
							player.y -= 1;
							whileFlag = 0;
						}
						else	// �������� �̵��� �Ұ����ϴٸ� �߸��� �Է��̶� ǥ������(�ٽ� �Է� ����)
							System.out.println("�ȳ�> �߸��� ��ɾ��Դϴ�. �ٽ� �Է��ϼ���.");
					}
					else if(order.equals("��")) {	// ���� �Է��� ���
						if(flag[2]==1) {	// �������� �̵��� �����ϴٸ� �÷��̾��� x���� 1���������ְ�(�������� �̵�) whileFlag�� 0���� ����� while������ ��������
							player.x += 1;
							whileFlag = 0;
						}
						else	// �������� �̵��� �Ұ����ϴٸ� �߸��� �Է��̶� ǥ������(�ٽ� �Է� ����)
							System.out.println("�ȳ�> �߸��� ��ɾ��Դϴ�. �ٽ� �Է��ϼ���.");
					}
					else if(order.equals("��")) {	// ���� �Է��� ���
						if(flag[3]==1) {	// �������� �̵��� �����ϴٸ� �÷��̾��� x���� 1���ҽ����ְ�(�������� �̵�) whileFlag�� 0���� ����� while������ ��������
							player.x -= 1;
							whileFlag = 0;
						}
						else	// �������� �̵��� �Ұ����ϴٸ� �߸��� �Է��̶� ǥ������(�ٽ� �Է� ����)
							System.out.println("�ȳ�> �߸��� ��ɾ��Դϴ�. �ٽ� �Է��ϼ���.");
					}
				}
			}catch(Exception x) {	// ���� ó�� �κ�(��,��,��,�� ���� �̻��� �Է��� ���� ���) �߸��� �Է��̶� ǥ������(�ٽ� �Է� ����)
				System.out.println("�ȳ�> �߸��� ��ɾ��Դϴ�. �ٽ� �Է��ϼ���.");
			}
		}
	}
	
	public static int checkEvent(Scanner scan, Player player, char [][] map) {	// �̺�Ʈ�� Ȯ���ϰ� �ִٸ�(����or��������) �����ϴ� �Լ�
		int clear = 1;	// ������ ���������� �������� üũ���ֱ� ���� ����(0:clear, 1:������(player.curhp = 0�̸� ���� ����))
		if(map[player.x][player.y] == '1') {	// �÷��̾��� ��ġ�� 1�� ������ �ִ� ���̶��
			printMap(player, map);	// �ش� ��ġ�� �������(�����ߴٰ�)
			Boss(1, scan, player);	// 1�� �������� ����� ����
			if(player.curhp > 0) {	// ���� Ŭ����� �ش� ������ �ִ� �ڸ��� �������� �ٲ�
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == '2') {	// �÷��̾��� ��ġ�� 2�� ������ �ִ� ���̶��
			printMap(player, map);	// �ش� ��ġ�� �������(�����ߴٰ�)
			Boss(2, scan, player);	// 2�� �������� ����� ����
			if(player.curhp > 0) {	// ���� Ŭ����� �ش� ������ �ִ� �ڸ��� �������� �ٲ�
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == '3') {	// �÷��̾��� ��ġ�� 3�� ������ �ִ� ���̶��
			printMap(player, map);	// �ش� ��ġ�� �������(�����ߴٰ�)
			Boss(3, scan, player);	// 3�� �������� ����� ����
			if(player.curhp > 0) {	// ���� Ŭ����� �ش� ������ �ִ� �ڸ��� �������� �ٲ�
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == '4') {	// �÷��̾��� ��ġ�� 4�� ������ �ִ� ���̶��
			printMap(player, map);	// �ش� ��ġ�� �������(�����ߴٰ�)
			Boss(4, scan, player);	// 4�� �������� ����� ����
			if(player.curhp > 0) {	// ���� Ŭ����� �ش� ������ �ִ� �ڸ��� �������� �ٲ�
				map[player.x][player.y] = ' ';
			}
		}
		else if(map[player.x][player.y] == 'T') {	// �÷��̾��� ��ġ�� T(��������)�� �ִ� ���̶��
			printMap(player, map);	// �ش� ��ġ�� �������(�����ߴٰ�)
			if(player.killBossCount == 4) {	// �� ������ ��� �̱� ��� ���� �¸�
				System.out.println("�����մϴ�. ���������� Ŭ�����ϼ̽��ϴ�!");
				clear = 0;	// clear�� 0���� ����� main���� ����� ������ �����ϰ� ��
			}
			else {	// ���� �� ������ �̱� ���� �ƴ� ��� ���� ��� ������ ����Ʈ���� ���ߴٰ� ���(������ ��� ����)
				System.out.println("�ȳ�> ���� ��� ������ ����Ʈ���� ���Ͽ����ϴ�.");
			}
		}
		return clear;	// clear�� ��������(�������ڸ� ã�� ������ Ŭ������ ��� 0�� ���� �̿��� ��� 1 ����)
	}
	
	public static void Boss(int BossNum, Scanner scan, Player player) {	// ������ �����ϰ� ����ϴ� �Լ�
		BossMonster boss = new BossMonster(BossNum);	// ���� ��ȣ�� ���� �ɷ�ġ�� ���� ������ ����
		
		System.out.println("�ȳ�> ���� ����.");		// �ȳ� ����
		System.out.println("�ȳ�> ������ ������ �����մϴ�.");
		System.out.println("�ȳ�> ������ ü���� ��� �������� ����ġ����.");
		System.out.println("");
		while(true) {	// ���� ����
			System.out.println("�ȳ�> �÷��̾� (" + player.curhp + "/" + player.maxhp + ") / ���� (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");	// �÷��̾�� ������ ü�� ǥ��
			BlackJack(scan, boss, player);	// ������ ��(�� �� �Ѹ��� �ǰ� 0���ϰ� �� ������ �ݺ�
			System.out.println("");
			System.out.println("");
			
			if(boss.bossCurhp <= 0) {	// ������ ü���� 0���϶�� ������ ����ģ ��
				player.ClearBoss();	// ������ ���� ���� 1 ������Ŵ
				System.out.println("�ȳ�> ������ �����ƽ��ϴ�.");	
				break;	// while���� ��������
			}
			if(player.curhp <= 0) {	// �÷��̾��� ü���� 0���϶�� �÷��̾��� �й�
				break;	// while���� �������� �÷��̾� ����� �޼����� main�� ����
			}
		}
	}
	
	public static void BlackJack(Scanner scan, BossMonster boss, Player player) {	// ������ �����ϴ� �Լ�
		String [][] card = new String [4][13];	// card�� �̸��� �����ϱ� ���� ���� card[0][?]�� �ش��ϴ� ���� �����̵�, 1: Ŭ�ι�, 2:��Ʈ, 3:���̾� card[?][0]�� �ش��ϴ� ���� A, 1: 2, ...(index�� ��ȣ + 1�� �ش��ϴ� ���� ī�忡 �ش��ϴ� ��)
		int [][] cardOwner = new int [4][13];	// 0: ������ ����, 1: ����(ī�尡 �ߺ��Ǿ� ������ ���� ���� ����)
		String cardShape;	// ī���� ����� �ٷ�� ���� ����(���ڿ� ���Ͽ� card�迭�� �˸��� ��ġ�� �����ϱ� ����)
		String number;		// ī���� ���ڸ� �ٷ�� ���� ����(���� ���Ͽ� card�迭�� �˸��� ��ġ�� �����ϱ� ����)
		int bossDrawCount = 0;	// ������ ���� ī���� ��
		int playerDrawCount = 0;	// �÷��̾ ���� ī���� ��
		int [][] bossDrawCardIndex = new int [11][2];	// ������ ���� ī�带 ������� �����ϱ� ���� ���� ����� (�ִ� 1,1,1,1,2,2,2,2,3,3,3���� ����)
		int [][] playerDrawCardIndex = new int [11][2];	// �÷��̾ ���� ī�带 ������� �����ϱ� ���� ���� ����� (�ִ� 1,1,1,1,2,2,2,2,3,3,3���� ����)
		String order = "x";	// 'h'�� 's'�� �ƴ� � ��(�ƹ� �ǹ� ����) (��Ʈ(h)�� ���ĵ�(s) ��ɾ �Է¹ޱ� ���� ����)
		int playerSum = 0;	// �÷��̾��� �� ī���� ��
		int bossSum = 0;	// ������ �� ī���� ��
		
		for(int i = 0; i < 4; i++) {	
			if(i == 0) {	// i = 0�̸� ī���� ����� �����̵�
				cardShape = "s";
			}
			else if(i == 1) {	// i = 1�̸� ī���� ����� Ŭ�ι�
				cardShape = "c";
			}
			else if(i == 2) {	// i = 2�̸� ī���� ����� ��Ʈ
				cardShape = "h";
			}
			else  {
				cardShape = "d";	// i = 3�̸� ī���� ����� ���̾Ƹ��
			}
			for(int j=0; j<13; j++) {	
				if (j == 0) {	// j = 0�̸� Aī��
					number = "A";
				}
				else if(j == 10) {	// j = 10�̸� Jī��
					number = "J";
				}
				else if(j == 11) {	// j = 11�̸� Qī��
					number = "Q";
				}
				else if(j == 12) {	// j = 12�̸� Kī��
					number = "K";
				}
				else	// �̿��� ��� (�ε���(j) + 1)�� �ش��ϴ� ���� ī��
					number = Integer.toString(j+1);
				card[i][j] = cardShape + number;	// ī�� �迭�� ī���� ���+���ڿ� �ش��ϴ� ������ ������(���� Ʈ���� ī��� ���� ����)
				cardOwner[i][j] = 0;	// �ش� ī���� ������ 0(���� ����, ������ ����)�̶�� �ص�
			}
		}
		// ī�带 2�� ���� �̰� -> (�����ְ� -> ����ϰ�(���а� ������������ Ȯ��) -> ��Ʈ���ĵ� ���� -> (��Ʈ��)ī�� �̱� -> ��ȣ�� �Ǿ����� �ݺ�) �� ���� �������� ������ 
		bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);	// ������ ī�� 2���� ����
		bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);
		
		playerDrawCount = DrawCard(playerDrawCardIndex, playerDrawCount, cardOwner);	// �÷��̾ ī�� 2���� ����
		playerDrawCount = DrawCard(playerDrawCardIndex, playerDrawCount, cardOwner);
		
		while(true) {	// ������ ����ɶ����� ����(���� Ȥ�� ����Ʈ Ȥ�� ���ĵ� �� ���� �� ���� ����ɶ����� ����)
			if(order.equals("s")==false) {	// ���ĵ带 ���� ���� ����(��Ʈ�Ǵ� ó�� 2���� ���� ���� ��Ȳ�� �ش�)
				int flag = 0;	// h �Ǵ� s�� ����� �Է� �޵��� ����� ���� ����
				ShowCard(1, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// ī�带 ������(situation1�� ��� ������ ������(�ι���) ī��� ?(�����))
				if(playerSum == 0) {	// �� ó�� 2���� �޾��� �� �� ����ϱ�(A�� �ִٸ� A�� ���� �����Ͽ� �հ��)
					playerSum = PlayerCalculate(scan, 1, playerDrawCardIndex, playerDrawCount, playerSum);	
				}
				else {	// hit�� ��쿡 �� ���(A�� �ִٸ� A�� ���� �����Ͽ� �հ��)
					playerSum = PlayerCalculate(scan, 2, playerDrawCardIndex, playerDrawCount, playerSum);
				}
				
				if(playerSum > 21) {	// �÷��̾��� ���� 21���� ū ��� ����Ʈ�� �й�
					ShowCard(2, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// ������ ī�� ����
					bossSum = BossCalculate(1, bossDrawCardIndex, bossDrawCount, bossSum);	// ������ �� ���
					if(bossSum == 21) {
						System.out.println("�ȳ�> ���� : " + bossSum + " (����) | �÷��̾� :" + playerSum + " (����Ʈ)");	// ���� ������ ������ ���� �� �÷��̾��� ����Ʈ�� �й����� ����
						player.curhp -= 2*boss.bossAtk;	// �÷��̾��� ü���� ������ ���ݷ�*2��ŭ �پ��Ե�
					}
					else {
						System.out.println("�ȳ�> ���� : " + bossSum + "| �÷��̾� :" + playerSum + " (����Ʈ)");	// ���� ������ ����Ʈ�� �й����� ����
						player.curhp -= boss.bossAtk;	// �÷��̾��� ü���� ������ ���ݷ¸�ŭ �پ��Ե�
					}
					System.out.println("���� " + boss.bossNum + "> �÷��̾� �й� | �÷��̾� (" + + player.curhp + "/" + player.maxhp + ") / ���� (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");	// �÷��̾��� �й� �� ���� ü�»�Ȳ�� ���
					break;	 // �� ������ ����Ǿ����Ƿ� while���� ��������(Boss�Լ����� �� �� �Ѹ��� ü���� 0���ϰ� �� ������ ������ ����ؼ� �ݺ��ϰ� ��)
				}
				
				if(playerSum == 21) {	// �÷��̾ ������ ��� ������ �¸�
					ShowCard(2, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// ������ ī�� ����
					bossSum = BossCalculate(1, bossDrawCardIndex, bossDrawCount, bossSum);	// ������ �� ���
					if(bossSum == 21)	// ������ ������ ����� ���� ���(�׷��� �÷��̾ �¸��� - ��Ģ)
						System.out.println("�ȳ�> ���� : " + bossSum + " (����) | �÷��̾� :" + playerSum + " (����)");
					else 				// �� ���� ����� ���� ���
						System.out.println("�ȳ�> ���� : " + bossSum + " | �÷��̾� :" + playerSum + " (����)");
					boss.bossCurhp -= 2;	// �������� 2���� �������� ��
					System.out.println("���� " + boss.bossNum + "> �÷��̾� �¸� | �÷��̾� (" + + player.curhp + "/" + player.maxhp + ") / ���� (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");	// �÷��̾��� �¸� �� ���� ü�»�Ȳ�� ���
					break;	// �� ������ ����Ǿ����Ƿ� while���� ��������(Boss�Լ����� �� �� �Ѹ��� ü���� 0���ϰ� �� ������ ������ ����ؼ� �ݺ��ϰ� ��)
				}
				
				while(flag == 0) {	// h�Ǵ� s�� ����� �Է��� ������ �ݺ�
					System.out.println("�ȳ�> ��Ʈ(h)? ���ĵ�(s)?");
					System.out.print("�÷��̾�> ");
					try {
						order = scan.next();	// �Է��� ����
						if(order.equals("h")) {	// ��Ʈ�� �ϴ� ���
							flag = 1;	// flag�� 1�� ����� while���� ��������
							playerDrawCount = DrawCard(playerDrawCardIndex, playerDrawCount, cardOwner);	// ������ ����(��Ʈ��)
						}
						else if(order.equals("s")) {	// ���ĵ带 �ϴ� ���
							flag = 1;	// flag�� 1�� ����� while���� �������� (�ٱ� while���� ���ĵ忡 �ش��ϴ� ���·� �̵��ϰ� ��)
						}
						else {	// �� ���� �߸��� �Է��� ��� flag�� 1�� �ƴϹǷ� �ٽ� �Է� ����
							throw new Exception();
						}
					}catch(Exception e) {	// �߸��� �Է����� ����ϰ� �ٽ� �Է� ����
						System.out.println("�ȳ�> �߸��� �Է��Դϴ�.");
					}
				}
			}
			else {	// ���ĵ带 �� ���� if(order.equals("s")==true)	
				ShowCard(2, bossDrawCardIndex, playerDrawCardIndex, card, bossDrawCount, playerDrawCount);	// ������ �� ����
				
				if(bossSum == 0) {	// �� ó�� 2���� �޾��� �� �� ����ϱ�(A�� �ִٸ� A�� ���� �����Ͽ� �հ��)
					bossSum = BossCalculate(1, bossDrawCardIndex, bossDrawCount, bossSum);	
				}
				else {	// hit�� ��쿡 �� ���(A�� �ִٸ� A�� ���� �����Ͽ� �հ��)
					bossSum = BossCalculate(2, bossDrawCardIndex, bossDrawCount, bossSum);
				}
				
				if(bossSum > 21) {	// ������ ���� 21���� ū ��� ��� ����Ʈ�� �÷��̾� �¸�(�÷��̾ ����Ʈ�ų� ������ ���� hit�� �ش��ϴ� if������ �ڵ����� ������)
					System.out.println("�ȳ�> ���� : " + bossSum + " (����Ʈ) | �÷��̾� :" + playerSum);
					boss.bossCurhp -= 1;	// �������� 1�� �������� ��
					System.out.println("���� " + boss.bossNum + "> �÷��̾� �¸� | �÷��̾� (" + + player.curhp + "/" + player.maxhp + ") / ���� (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
					break;	// ���� 1�� ����
				}
				
				if(bossSum > playerSum) {	// bossSum > playerSum�� ���(�׸��� bossSum <=21�� ���)
					if(bossSum == 21) {	// bossSum�� 21�̸� �������� 2���� �������� �� (�÷��̾ ������ ���� ������ �ڵ����� �Ǻ� �Ǿ���)
						System.out.println("�ȳ�> ���� : " + bossSum + " (����) | �÷��̾� :" + playerSum);
						player.curhp -= boss.bossAtk*2;	// �÷��̾�� 2���� �������� ��
					}
					else {	// �� ���� ��� �Ϲ� ������
						System.out.println("�ȳ�> ���� : " + bossSum + " | �÷��̾� :" + playerSum);
						player.curhp -= boss.bossAtk;	// �÷��̾�� �Ϲ� �������� ��
					}	// �÷��̾��� �й� ���� ���
					System.out.println("���� " + boss.bossNum + "> �÷��̾� �й� | �÷��̾� (" + + player.curhp + "/" + player.maxhp + ") / ���� (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
					break;	// ���� 1�� ����
				}
				else if(bossSum == playerSum) {	//(bossSum = playerSum�� ��� 16���� �۰ų� ������ ��Ʈ, 17�̻��̸� ���ĵ���(����)
					if(bossSum <= 16) {	// ��Ʈ
						bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);
					}
					else {	// 17�̻��̸� ���ĵ�(Ǫ���� �ش��ϴ� �κ���)
						System.out.println("�ȳ�> ���� : " + bossSum + "| �÷��̾� :" + playerSum);
						System.out.println("���� " + boss.bossNum + "> ���º� | �÷��̾� (" + + player.curhp + "/" + player.maxhp + ") / ���� (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
						break;	// ���� 1�� ����
					}
				}
				else {	// bossSum < playerSum
					if(bossSum <= 16) {	// ��Ʈ
						bossDrawCount = DrawCard(bossDrawCardIndex, bossDrawCount, cardOwner);
					}
					else {	// ���ĵ�(�÷��̾��� �¸�)
						System.out.println("�ȳ�> ���� : " + bossSum + "| �÷��̾� :" + playerSum);
						boss.bossCurhp -= 1;	// �������� 1�� �������� ����
						System.out.println("���� " + boss.bossNum + "> �÷��̾� �¸� | �÷��̾� (" + + player.curhp + "/" + player.maxhp + ") / ���� (" + boss.bossCurhp + "/" + boss.bossMaxhp + ")");
						break;	// ���� 1�� ����
					}
				}
			}
		}	
	}	
	
	public static int PlayerCalculate(Scanner scan, int situation, int playerDrawCardIndex[][], int playerDrawCount, int sum) {	// �÷��̾ ���� ī���� ������ ���� ����ϴ� �Լ�
		int getNumber = 0;	// A�� ������ �� 1 �Ǵ� 11�� ���ڰ��� �Է� �޴� ����
		int flag = 0;	// A�� ������ �� 1 �Ǵ� 11�� ���ڸ� �Է��ϵ��� ����� ���� ����
		if(situation == 1) {	// �� ó�� �� ī�带 ���� ���� ���� ��� �� ��
			for(int i=0; i< playerDrawCount; i++) {		// playerDrawCount = 2��
				if(playerDrawCardIndex[i][1] == 0) {	// Aī���� ���
					while(flag == 0) {
						System.out.print("A�� ���� �����ּ���(1 or 11):");	// 1 �Ǵ� 11�� �Է� ����
						getNumber = scan.nextInt();	
						try {
							if(getNumber == 1) {	// 1�� �� ���
								sum += 1;			// ������ �տ� 1�� �����ְ�
								flag = 1;			// while���� ��������
							}
							else if(getNumber == 11) {	// 11�� �����
								sum += 11;				// ������ �տ� 11�� �����ְ�
								flag = 1;				// while���� ��������
							}
							else {	// �� ���� ��� ����ó��
								throw new Exception();
							}
						}catch(Exception e) {	// 1�Ǵ� 11�� �Է� �޵��� �߸��� �Է��̶� ����ϰ� �ٽ� �Է� ����
							System.out.println("�ȳ�> �߸��� �Է��Դϴ�.");
						}
					}
				}
				else if (playerDrawCardIndex[i][1] > 9) {	// J,Q,K�� ��� ������ ���� ��� �Ҷ� 10���� ó��
					sum += 10;
				}
				else {
					sum += playerDrawCardIndex[i][1] + 1;	// �� ���� ��� playerDrawCardIndex(�÷��̾ ���ݱ��� ���� ī�忡 �ش��ϴ� �ε���)�� ����Ǿ��ִ� ���� �����Ͽ� �ش��ϴ� ���ڸ� �̿��Ͽ� ��� ex) ��ó�� ���� ī�尡 d2��� card[3][1]�� �ش��� -> playerDrawCardIndex[0][0] = 3 ,[0][1] = 1�̵� (������ ��� ������ �ε��� ���� 1�� ���� ���� ��¥ ���� ��)
				}
			}
		}
		else {	// hit�� ī�带 �� ���� ��
			if(playerDrawCardIndex[playerDrawCount-1][1] == 0) {	// Aī���� ���
				while(flag == 0) {	
					System.out.print("A�� ���� �����ּ���(1 or 11):");	// 1 �Ǵ� 11�� �Է� ����
					getNumber = scan.nextInt();
					try {
						if(getNumber == 1) {	// 1�� �� ���
							sum += 1;			// ������ �տ� 1�� �����ְ�
							flag = 1;			// while���� ��������
						}
						else if(getNumber == 11) {	// 11�� �����
							sum += 11;				// ������ �տ� 11�� �����ְ�
							flag = 1;				// while���� ��������
						}
						else {	// �� ���� ��� ����ó��
							throw new Exception();
						}
					}catch(Exception e) {	// 1�Ǵ� 11�� �Է� �޵��� �߸��� �Է��̶� ����ϰ� �ٽ� �Է� ����
						System.out.println("�ȳ�> �߸��� �Է��Դϴ�.");
					}
				}
			}
			else if (playerDrawCardIndex[playerDrawCount-1][1] > 9) {	// J,Q,K�� ��� ������ ���� ��� �Ҷ� 10���� ó��
				sum += 10;
			}
			else {
				sum += playerDrawCardIndex[playerDrawCount-1][1] + 1;	// �� ���� ��� playerDrawCardIndex(�÷��̾ ���ݱ��� ���� ī�忡 �ش��ϴ� �ε���)�� ����Ǿ��ִ� ���� �����Ͽ� �ش��ϴ� ���ڸ� �̿��Ͽ� ��� ex) ��ó�� ���� ī�尡 d2��� card[3][1]�� �ش��� -> playerDrawCardIndex[0][0] = 3 ,[0][1] = 1�̵� (������ ��� ������ �ε��� ���� 1�� ���� ���� ��¥ ���� ��)
			}
		}
		return sum;	// ����� ���� ���� ����
	}
	
	public static int BossCalculate(int situation, int bossDrawCardIndex[][], int bossDrawCount, int sum) {	// ������ ���� ī���� ������ ���� ����ϴ� �Լ�
		if(situation == 1) {	// �� ó�� �� ī�带 ���� ���� ���� ��� �� ��
			for(int i=0; i< bossDrawCount; i++) {	// bossDrawCount = 2��
				if(bossDrawCardIndex[i][1] == 0) {	// Aī���� ���
					if(sum + 11 > 21) 	// 11�� ����� �� ����Ʈ�� �ȴٸ� 1�� ��
						sum += 1;
					else				// ����Ʈ�� ���� �ʴ� �ٸ� 11�� ��(����) 
						sum += 11;
				}
				else if (bossDrawCardIndex[i][1] > 9) {	// J,Q,K�� ��� ������ ���� ��� �Ҷ� 10���� ó��
					sum += 10;
				}
				else {
					sum += bossDrawCardIndex[i][1] + 1;	// �� ���� ��� bossDrawCardIndex(������ ���ݱ��� ���� ī�忡 �ش��ϴ� �ε���)�� ����Ǿ��ִ� ���� �����Ͽ� �ش��ϴ� ���ڸ� �̿��Ͽ� ��� ex) ��ó�� ���� ī�尡 d2��� card[3][1]�� �ش��� -> bossDrawCardIndex[0][0] = 3 ,[0][1] = 1�̵� (������ ��� ������ �ε��� ���� 1�� ���� ���� ��¥ ���� ��) 
				}
			}
		}
		else {		// hit�� ī�带 �� ���� ��
			if(bossDrawCardIndex[bossDrawCount-1][1] == 0) {	// Aī���� ���
				if(sum + 11 > 21) 	// 11�� ����� �� ����Ʈ�� �ȴٸ� 1�� ��
					sum += 1;
				else 				// ����Ʈ�� ���� �ʴ� �ٸ� 11�� ��(����) 
					sum += 11;		
			}
			else if (bossDrawCardIndex[bossDrawCount-1][1] > 9) {	// J,Q,K�� ��� ������ ���� ��� �Ҷ� 10���� ó��
				sum += 10;
			}
			else {
				sum += bossDrawCardIndex[bossDrawCount-1][1] + 1;	// �� ���� ��� bossDrawCardIndex(������ ���ݱ��� ���� ī�忡 �ش��ϴ� �ε���)�� ����Ǿ��ִ� ���� �����Ͽ� �ش��ϴ� ���ڸ� �̿��Ͽ� ��� ex) ��ó�� ���� ī�尡 d2��� card[3][1]�� �ش��� -> bossDrawCardIndex[0][0] = 3 ,[0][1] = 1�̵� (������ ��� ������ �ε��� ���� 1�� ���� ���� ��¥ ���� ��) 
			}
		}
		return sum;	// ����� ���� ���� ����
	}
	
	public static int DrawCard(int gamerDrawCardIndex[][], int gamerDrawCount, int cardOwner[][]) {	// ī�带 �̴� �Լ�(��Ʈ �Ǵ� ù 2���� ī�带 ���� �� �̿�)
		int randNumber;	// ������ ī�带 �̱����� ����
		while(true) {	// cardOwner�� 0��(������ ����) ī�带 ���� ������ �ݺ�(�ߺ��� ������)
			randNumber = (int)(Math.random()*52);	// randNumber : 0 ~ 51
			if(cardOwner[randNumber/13][randNumber%13] == 0) {	// cardOwner�� 0�� ��� 
				cardOwner[randNumber/13][randNumber%13] = 1;	// �ش� ī�尡 �����ٰ� ó�����ְ� 1�� ����
				gamerDrawCardIndex[gamerDrawCount][0] = randNumber/13;	// �ش� ī�带 ���� ����� ī�� �ε����� �ٷ�� ������ ���� ī���� �ε����� ����
				gamerDrawCardIndex[gamerDrawCount][1] = randNumber%13;	// �ش� ī�带 ���� ����� ī�� �ε����� �ٷ�� ������ ���� ī���� �ε����� ����
				gamerDrawCount++;	// �ش� ī�带 ���� ����� ���� ī�� ���� 1���� ��Ŵ
				break;	// while���� ��������(�ߺ��� �����Ƿ�)
			}
		}
		return gamerDrawCount;	// �ش� ī�带 ���� ����� ���� ī�� ���� ��������
	}
	
	public static void ShowCard(int situation, int bossDrawCardIndex[][], int playerDrawCardIndex[][], String card[][], int bossDrawCount, int playerDrawCount) {	// ���� ī���� ��Ȳ�� �˷���(situation: 1�� ��� ������ ������(�ι�°) ī��� ?�� ������ ����, 2�� ��� ��� ���� ī�� ���� ����)
		if(situation == 1) {	// boss�� �� �̰���
			System.out.print("�ȳ�> ���� : ");
			for(int i = 0; i < bossDrawCount-1; i++) {	// ������ ī��� �������� ����
				System.out.print(card[bossDrawCardIndex[i][0]][bossDrawCardIndex[i][1]]);	// ������ ī�� ���� ���
				if(i + 1 < bossDrawCount) {	// �̹� ī�尡 ������ ī�尡 �ƴ϶�� / ���
					System.out.print("/");
				}
			}
			System.out.print("? | �÷��̾� : ");	// ������ ī��� ?�� ���
			for(int i = 0; i < playerDrawCount; i++) {	
				System.out.print(card[playerDrawCardIndex[i][0]][playerDrawCardIndex[i][1]]);	// �÷��̾��� ��� ī�� ���� ����
				if(i + 1 < playerDrawCount) { // �̹� ī�尡 ������ ī�尡 �ƴ϶�� / ���
					System.out.print("/");
				}
			}
			System.out.println("");
		}
		if(situation == 2) {	// boss�� �� ����
			System.out.print("�ȳ�> ���� : ");
			for(int i = 0; i < bossDrawCount; i++) {
				System.out.print(card[bossDrawCardIndex[i][0]][bossDrawCardIndex[i][1]]);	// ������ ��� ī�� ���� ���
				if(i + 1 < bossDrawCount) {	// �̹� ī�尡 ������ ī�尡 �ƴ϶�� / ���
					System.out.print("/");
				}
			}
			System.out.print(" | �÷��̾� : ");
			for(int i = 0; i < playerDrawCount; i++) {
				System.out.print(card[playerDrawCardIndex[i][0]][playerDrawCardIndex[i][1]]);	// �÷��̾��� ��� ī�� ���� ����
				if(i + 1 < playerDrawCount) { // �̹� ī�尡 ������ ī�尡 �ƴ϶�� / ���
					System.out.print("/");
				}
			}
			System.out.println("");
		}
	}
}

class Player {	// �÷��̾� ��ǥ �� ü�� ����, ������ óġ�� ���� �ٷ�
	int x;	// �ʿ����� ��ġ�� �ٷ�� ����
	int y;	// �ʿ����� ��ġ�� �ٷ�� ����
	int curhp;	// ���� ü���� �ٷ�� ����
	int maxhp;	// �ִ� ü���� �ٷ�� ����
	int killBossCount;	// ������ �̱� ��
	
	public Player(int x, int y) {	// �÷��̾ ���� �� ��ġ ������ �޾� ���� ��
		this.x = x;
		this.y = y;
		this.curhp = 5;
		this.maxhp = 5;
		this.killBossCount = 0;
	}
	
	public void ClearBoss() {	// ������ �̱� ��� ü���� 2������Ű�� killBossCount�� 1������Ŵ 
		this.curhp += 2;
		this.maxhp += 2;
		this.killBossCount += 1;
	}
	
	public void regame(int x, int y) {	// ������ �ٽ��� ��� ���� ������ x,y��ǥ�� �̵��ϰ� ü�°� killBossCount�� ���� �� ������ �ʱ�ȭ ��
		this.x = x;
		this.y = y;
		this.curhp = 5;
		this.maxhp = 5;
		this.killBossCount = 0;
	}
}

class BossMonster{	// ���� ������ ����(1�� ����, 2�� ����, 3�� ����, 4�� ����), �ش� ������ ü��, �ش� ������ ���ݷ��� �ٷ�
	int bossNum;	// ������ ��ȣ
	int bossMaxhp;	// ������ �ִ� ü��
	int bossCurhp;	// ������ ���� ü��
	int bossAtk;	// ������ ���ݷ�
	
	public BossMonster(int bossNum) {	// ������ ���� �� ���� ������ ���ڸ� �̿��Ͽ� ����
		this.bossNum = bossNum;	
		this.bossMaxhp = 4 + bossNum;
		this.bossCurhp = bossMaxhp;	
		this.bossAtk = bossNum;
	}
}