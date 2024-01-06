package wp_midterm_2019253025;

import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		int orderNum = 0;			// 주 명령어를 다루기 위한 변수
		int checkNum = 0;			// 주 명령어와 함께 명령어를 다루기 위한 변수(보조 명령어)
		int p[] = {0,0,0,0};		// 각 유닛의 사용 인구수를 저장하기 위한 배열
		int totalUnitPopulation;	// 각 유닛의 사용 인구수를 모두 합한 값을 저장하기 위한 변수
		int limitPopulation;		// 최대로 가질 수 있는 인구수(총인구수)를 저장하기 위한 변수
		Scanner scan = new Scanner(System.in);		// System.in에 대한 Scanner객체를 만듦
		Building buildings[] = {new CommandCenter(), new SupplyDepot(), new Barrack()};	// buildings배열에 각각 CommandCenter와 SupplyDepot, Barrack을 할당 함(생성자를 이용) 
		Unit units[]= {new SCV(), new Marine(), new Firebat(), new Medic()};			// units배열에 각각 SCV, Marine, Firebat, Medic을 할당 함(생성자를 이용)
		
		for(int i=0; i<4; i++) {	// p배열에 각 유닛의 사용 인구수를 저장함
			p[i] = units[i].returnTotalUnitPopulation();	 
		}
		totalUnitPopulation = p[0] + p[1] + p[2] + p[3];	// 각 유닛의 사용 인구수를 모두 합한 값을 계산
		
		while(true) {	// 시스템 종료로 while문을 빠져나가기 전까지 계속해서 반복(기능 목록 출현으로 돌아가게 됨)
			//for(int i=0; i<4; i++) {
			//	p[i] = units[i].returnTotalUnitPopulation();	// 
			//}
			//totalUnitPopulation = p[0] + p[1] + p[2] + p[3];
			limitPopulation = buildings[1].returnPopulation();	// 최대로 가질 수 있는 인구수는 SupplyDepot의 Population값임
			orderNum = showMenuList(scan, orderNum);	// 기능 목록 출현(기능을 숫자로 선택하여 orderNum으로 넘겨줌)
			if(orderNum == 1) {		// 1.건물 건설
				orderNum = showBuildingList(scan, orderNum);	// 건설할 건물의 목록을 보여주고 명령어를 orderNum에 넘겨줌
				if(orderNum == 1 || orderNum == 2 || orderNum == 3) {		// orderNum이 1(CommandCenter건설)또는 2(SupplyDepot건설)또는 3(Barrack건설)인 경우
					checkNum = checkBuildingLimit(orderNum, buildings);		// 건설이 가능한지 확인하여 조건을 만족하면 countNum이 1이 됨 (건설이 불가능하다면 함수내에서 불가능한 이유가 출력되고 countNum이 0이 됨)
					if(checkNum == 1) {		// 건설이 가능하다면
						buildings[orderNum-1].build();	// 해당 건물을 건설 함
					}
				}
				else if(orderNum == 4) {	// orderNum이 4(취소)인 경우
					System.out.println("건물 건설을 취소합니다. 메인 메뉴로 돌아갑니다.");	// 건물 건설을 취소하고 메인 메뉴로 돌아간다고 출력
				}
				else {	// 이 외의 경우 잘못된 입력이므로
					System.out.println("잘못된 입력입니다. 메인 메뉴로 돌아갑니다.");	// 잘못된 입력이며 메인 메뉴로 돌아간다고 출력
				}
			}
			else if(orderNum == 2) {	// 2.유닛 조회
				orderNum = showUnitList(p, units, totalUnitPopulation, limitPopulation);	// 생성된 유닛 목록 및 사용 인구수를 출력함 
			}
			else if(orderNum == 3) {	// 3.유닛 생성
				orderNum = showUnitCreateList(scan, buildings, orderNum);	// 생성 가능한 유닛의 목록을 출력함(생성할 유닛을 숫자로 선택하여 orderNum으로 넘겨줌)
				if(orderNum == -1) {	// orderNum이 -1(생성 가능한 유닛 없음)인 경우
					System.out.println("생성할 수 있는 유닛이 없습니다.");	// 생성할 수 있는 유닛이 없다고 출력
				}
				else if(orderNum == 1 || orderNum == 2 || orderNum == 3 || orderNum == 4) {		// orderNum이 1(SCV생성), 2(Marine생성), 3(Firebat생성), 4(Medic생성)인 경우
					System.out.println("유닛의 정보");
					units[orderNum-1].printUnitInfo();	// 해당 유닛의 정보를 출력
					System.out.print("해당 유닛을 생성하시겠습니까(0:yes, 1:no)?: ");	// 해당 유닛을 생성할 것인지 물어보는 메세지 출력
					checkNum = scan.nextInt();	// 값을 입력받음(0:yes, 1:no, 나머지: 잘못된 입력)
					if(checkNum == 0) {		// 0을 입력받은 경우(생성하는 경우)
						checkNum = checkUsingPopulation(units, orderNum, totalUnitPopulation, limitPopulation);		// 사용인구수를 확인함((사용인구수+생성인구수)<=총인구수 인 경우 생성이 가능하므로 checkNum이 0이됨, 아닌 경우 인구수가 부족하여 생성이 불가능하므로 checkNum이 1이됨)
						if(checkNum == 0) {	// 생성 가능한 경우
							String s = units[orderNum-1].returnUnitName();	// 해당 유닛의 이름을 s에 저장(아래 메세지에 이용)
							units[orderNum-1].buildUnit();					// 해당 유닛을 생성함
							System.out.println(s + "유닛이 생성되었습니다.");		// 해당 유닛이 생성되었다는 메세지 출력
							p[orderNum-1] = units[orderNum-1].returnTotalUnitPopulation();	// 해당 유닛의 변동된 인구수 정보를 p배열에 저장(해당 유닛 정보를 담고 있는 인덱스에 저장)
							totalUnitPopulation = p[0] + p[1] + p[2] + p[3];	// 변동된 총 인구수 정보를 다시 계산
							System.out.println("인구수 : " + totalUnitPopulation + " / " + limitPopulation);	// 변동된 인구수 정보를 출력
						}
						else {	// 생성이 불가능한 경우
							System.out.println("유닛이 생성될 수 없습니다.(사용 가능한 인구수가 부족합니다)");	// 생성이 불가능하다고 메세지 출력
							System.out.println("인구수 : " + totalUnitPopulation + " / " + limitPopulation);	// 인구수 정보를 출력
						}
					}
					else if(checkNum == 1) { // 1을 입력받은 경우(취소하는 경우)
						System.out.println("생성을 취소합니다. 메인 메뉴로 돌아갑니다.");	// 생성을 취소하고 메인메뉴로 이동한다고 출력
					}
					else {	// 0과 1이 아닌 값을 입력받은 경우(잘못된 입력)
						System.out.println("잘못된 입력입니다. 메인 메뉴로 돌아갑니다.");	// 입력이 잘못되었으며 메인메뉴로 이동한다고 출력
					}
				}
				else if(orderNum == 5) {	// orderNum이 5(취소)인 경우
					System.out.println("생성을 취소합니다. 메인 메뉴로 돌아갑니다.");		// 생성을 취소하고 메인메뉴로 이동한다고 출력
				}
				else {	// orderNum이 잘못 입력된 경우
					System.out.println("잘못된 입력입니다. 메인 메뉴로 돌아갑니다.");		// 입력이 잘못되었으며 메인메뉴로 이동한다고 출력
				}
			}
			else if(orderNum == 4) {	// 4. 유닛 삭제
				orderNum = showUnitList(p, units, totalUnitPopulation, limitPopulation);	// 생성된 유닛들의 목록을 출력(생성된 유닛이 없으면 0을 반환, 있으면 1을 반환)
				if(orderNum == 1) {		// 생성된 유닛이 있는 경우
					System.out.print("삭제하실 유닛을 선택해 주십시오(숫자(0:취소)): ");	// 위에서 생성한 목록을 보고 삭제할 유닛을 선택
					orderNum = scan.nextInt();	// 삭제할 유닛(숫자)을 입력 받음
					if(orderNum == 0) {	// orderNum이 0(취소)인 경우
						System.out.println("삭제를 취소합니다. 메인 메뉴로 돌아갑니다.");	// 삭제를 취소하고 메인메뉴로 이동한다고 출력
					}
					else if(orderNum == 1 || orderNum == 2 || orderNum == 3 || orderNum == 4) {		// orderNum이 1(SCV), 2(Marine), 3(Firebat), 4(Medic)인 경우
						String s = units[orderNum-1].returnUnitName();			// 해당 유닛의 이름을 s에 저장(아래 메세지에 이용됨)
						int unitCount = units[orderNum-1].returnUnitCount();	// 해당 유닛의 유닛의 개수를 unitCount에 저장
						if(unitCount > 0) {		// 해당 유닛이 존재하는 경우(1개 이상인 경우)
							units[orderNum-1].killUnit();	// 해당 유닛 1개를 삭제
							unitCount = units[orderNum-1].returnUnitCount();	// 변경된 유닛 개수 저장
							System.out.println(s + "유닛이 삭제되었습니다. 남은 " + s + "유닛수: " + unitCount);	// 유닛의 삭제와 해당 유닛의 남은 개수를 출력
							p[orderNum-1] = units[orderNum-1].returnTotalUnitPopulation();	// 해당 유닛의 변동된 인구수 정보를 p배열에 저장(해당 유닛 정보를 담고 있는 인덱스에 저장)
							totalUnitPopulation = p[0] + p[1] + p[2] + p[3];	// 변동된 총 인구수 정보를 다시 계산 
							System.out.println("인구수 : " + totalUnitPopulation + " / " + limitPopulation);	// 변동된 인구수 정보를 출력
						}
						else {	// 해당 유닛이 존재하지 않는 경우
							System.out.println("삭제할 " + s + "유닛이 존재하지 않습니다.");	// 삭제할 유닛이 존재하지 않는다고 출력
						}
					}
					else {	// orderNum이 잘못 입력된 경우
						System.out.println("잘못된 입력입니다. 메인 메뉴로 돌아갑니다.");	// 입력이 잘못되었으며 메인메뉴로 이동한다고 출력
					}
				}	
				else {	// 생성된 유닛이 없는 경우(모든 유닛이 없음 - 삭제할 유닛도 없음)
					System.out.println("삭제할 유닛이 존재하지 않습니다.");	// 삭제할 유닛이 존재하지 않는다고 출력
				}
			}
			else if(orderNum == 5) {	// 5.건물 삭제
				orderNum = showExistBuildingList(scan, orderNum, buildings);	// 생성된 건물들의 목록을 출력(생성된 건물이 없으면 0을 반환, 있으면 1을 반환)
				if(orderNum == 1) {		// 생성된 건물이 있는 경우
					System.out.print("삭제하실 건물을 선택해 주십시오: ");	// 위에서 생성한 목록을 보고 삭제할 건물을 선택
					orderNum = scan.nextInt();	// 삭제할 건물(숫자)을 입력 받음
					if(orderNum == 4) {	// orderNum이 4(취소)인 경우
						System.out.println("삭제를 취소합니다. 메인 메뉴로 돌아갑니다.");	// 삭제를 취소하고 메인메뉴로 이동한다고 출력
					}
					else if(orderNum == 1 || orderNum == 2 || orderNum == 3) {		// orderNum이 1(CommandCenter), 2(SupplyDepot), 3(Barrack)인 경우
						String s = buildings[orderNum-1].returnBuildingName();			// 해당 건물의 이름을 s에 저장(아래 메세지에 이용됨)
						int buildingCount = buildings[orderNum-1].returnBuildingCount();		// 해당 건물의 개수를 buildingCount에 저장
						if(buildingCount > 0) {		// 해당 건물이 존재하는 경우(1개 이상인 경우)
							buildings[orderNum-1].destroy();	// 해당 건물 1개를 삭제
							buildingCount = buildings[orderNum-1].returnBuildingCount();	// 변경된 건물 개수 저장
							System.out.println(s + "이 삭제되었습니다. 남은 " + s + "수: " + buildingCount);	// 건물의 삭제와 해당 건물의 남은 개수를 출력
						}
						else {	// 해당 건물이 존재하지 않는 경우
							System.out.println("삭제할 " + s + "이 존재하지 않습니다.");	// 삭제할 건물이 존재하지 않는다고 출력
						}
					}
					else {	// orderNum이 잘못 입력된 경우
						System.out.println("잘못된 입력입니다. 메인 메뉴로 돌아갑니다.");	// 입력이 잘못되었으며 메인메뉴로 이동한다고 출력
					}
				}	
				else {	// 생성된 건물이 없는 경우(삭제할 건물도 없음)
					System.out.println("삭제할 건물이 존재하지 않습니다.");	// 삭제할 건물이 존재하지 않는다고 출력
				}
			}
			else if(orderNum == 6) {	// 6. 시스템 종료
				System.out.println("시스템을 종료합니다.");	// 시스템을 종료한다고 출력
				break;	// break로 while문을 빠져나가 프로그램이 종료됨
			}
			else {	// orderNum이 잘못 입력된 경우
				System.out.println("잘못된 입력입니다.");	// 잘못된 입력이라고 출력
			}
		}
		scan.close();
	}
	
	public static int showMenuList(Scanner scan, int orderNum) {	// 기능 목록 출력
		System.out.println("기능 목록 ");		// 각 기능의 목록들을 출력
		System.out.println("1.건물 건설");
		System.out.println("2.유닛 조회");
		System.out.println("3.유닛 생성");
		System.out.println("4.유닛 삭제");
		System.out.println("5.건물 삭제");
		System.out.println("6.종료");
		System.out.print("원하시는 기능을 선택해 주십시오(숫자): ");	// 원하는 기능을 선택하라고 출력
		orderNum = scan.nextInt();	// 숫자로 입력받아 orderNum에 저장하고
		return orderNum;	// orderNum을 리턴함(이 orderNum을 이용해 main에서 기능 실행)
	}
	
	public static int showBuildingList(Scanner scan, int orderNum) {	// 건물 종류 출력
		System.out.println("건물 종류");		
		System.out.println("1.CommandCenter");
		System.out.println("2.SupplyDepot");
		System.out.println("3.Barrack");
		System.out.println("4.취소");
		System.out.print("원하시는 건물을 선택해 주십시오(숫자): ");	// 원하는 기능(설치,삭제 할 건물)을 선택하라고 출력
		orderNum = scan.nextInt();	// 숫자로 입력받아 orderNum에 저장하고
		return orderNum;	// orderNum을 리턴함(이 orderNum을 이용해 main에서 기능 실행)
	}
	
	public static int checkBuildingLimit(int orderNum, Building buildings[]) {	// 건물 설치 시 조건을 만족하는지 확인함(조건을 만족하면 1을 return 아니면 0을 return)
		int buildingCount = 0;	// 해당 건물의 개수(설치된 건물 수)를 확인
		String buildingName;	// 해당 건물의 이름을 저장하기 위한 변수(출력에서 사용)
		if(orderNum == 1) {		// orderNum이 1(CommandCenter설치)인 경우
			buildingCount = buildings[0].isBuild();	// CommandCenter건물이 지어져있는지 아닌지 출력하고 CommandCenter건물의 개수를 buildingCount에 저장(별다른 조건 없음)
		}
		else {	// orderNum이 2(SupplyDepot), 3(Barrack)인 경우(나머지는 main에서 걸러짐)
			buildingCount = buildings[0].isBuild();	// CommandCenter건물이 지어져있는지 아닌지 출력하고 CommandCenter건물의 개수를 buildingCount에 저장
			if(buildingCount == 0) {	// CommandCenter건물이 없는 경우 (SupplyDepot과 Barrack은 건설 불가능)
				buildingName = buildings[orderNum-1].returnBuildingName();	// 만드려고 했던 건물의 이름을 buildingName에 저장
				System.out.println(buildingName + "을 건설하기 위해서는 CommandCenter가 필요합니다.");	// 해당 건물을 건설하려면 CommandCenter가 필요하다고 출력
				return 0;	// 0을 리턴
			}
			else {	// CommandCenter건물이 있는 경우
				buildingCount = buildings[orderNum-1].isBuild();	// CommandCenter건물이 지어져있는지 아닌지 출력하고 CommandCenter건물의 개수를 buildingCount에 저장
			}
		}
		return 1;	// 건설이 가능한 경우 1을 리턴(불가능한 경우는 중간에 0으로 리턴 처리를 미리 해줌)
	}
	
	public static int showUnitList(int p[], Unit units[], int totalUnitPopulation, int limitPopulation) {	// 생성된 유닛들의 목록 출력
		System.out.println("생성된 유닛들의 목록");
		if(totalUnitPopulation == 0) {	// 생성된 유닛이 없는 경우(인구수 0 = 유닛수 0)
			System.out.println("생성된 유닛은 없습니다.");		// 생성된 유닛이 없다고 출력
			return 0;	// 0을 리턴
		}
		else {	// 생성된 유닛이 있는 경우
			for(int i=0; i<4; i++) {	// for문으로 p배열을 이용하여
				if(p[i] > 0) {			// p[i] > 0이면(해당 유닛이 있는 경우) 
					System.out.print((i+1) + ".");	// 해당 유닛에 해당하는 번호와 (1: SCV, 2: Marine, 3: Firebat, 4: Medic)
					units[i].printUnitPopulation();	// 해당 유닛의 개수와 해당 유닛으로 사용되고 있는 인구수를 출력
				}
			}
		}
		System.out.println("인구수 : " + totalUnitPopulation + " / " + limitPopulation);	// 인구수 정보를 출력
		return 1;	// 1을 리턴(생성된 유닛이 있는 경우 = 삭제할 유닛이 있는 경우)
	}
	
	public static int showUnitCreateList(Scanner scan, Building buildings[], int orderNum) {	// 생성 가능한 유닛들의 목록 출력
		int buildingCount1 = buildings[0].isBuild();	// CommandCenter건물의 개수
		int buildingCount2 = buildings[2].isBuild();	// Barrack건물의 개수
		System.out.println("생성 가능한 유닛들의 목록");		// 생성 가능한 유닛들의 목록 출력
		if(buildingCount1 > 0) {	// CommandCenter건물이 있는 경우(SCV생성 가능)
			System.out.println("1.SCV");	// 생성 가능 목록에 SCV추가
		}
		if(buildingCount2 > 0) {	// Barrack건물이 있는 경우(Marine, Firebat, Medic생성 가능)
			System.out.println("2.Marine");	// 생성 가능 목록에 Marine, Firebat, Medic추가
			System.out.println("3.Firebat");
			System.out.println("4.Medic");
		}
		if(buildingCount1 == 0 && buildingCount2 == 0) {	// CommandCenter건물과 Barrack건물이 둘 다 없는 경우
			orderNum = -1;	// orderNum을 -1로 만듦(나중에 리턴할때 -1이 리턴되어 main에서 생성할 수 있는 유닛이 없다고 출력 됨)
		}
		else {	// CommandCenter건물과 Barrack건물중 하나라도 있는 경우
			System.out.println("5.취소");		// 옵션으로 취소도 추가
			System.out.print("생성을 원하시는 유닛을 선택해 주십시오(숫자): ");	// 생성을 원하는 유닛을 입력받기 위한 메세지 출력
			orderNum = scan.nextInt();		// 생성을 원하는 유닛의 번호를 입력 받음
		}
		
		if(orderNum == 1) {	// orderNum이 1인 경우(SCV생성)	(이 if문이 있는 이유는 이 if문이 없을 시 위의 목록에서 SCV만 생성가능하더라도 2(Marine)를 누르면 Marine이 생성될 수 있기 때문)
			if(buildingCount1 > 0) {	// CommandCenter건물이 있는 경우(SCV생성)
				return orderNum;		// 1을 리턴(main에서 SCV생성)
			}
			else {	// CommandCenter건물이 없는 경우(SCV생성 불가능)
				System.out.println("해당 유닛은 생성할 수 없습니다.");	// 해당 유닛은 생성할 수 없다고 메세지 출력
				return 5;	// 5를 리턴(main에서 취소 기능을 하도록 함(생성할 수 없다는 메세지는 방금 출력하였으므로))
			}
		}
		else if(orderNum==2 || orderNum==3 || orderNum==4) {	// orderNum이 2(Marine생성) 또는 3(Firebat생성) 또는 4(Medic생성)인 경우
			if(buildingCount2 > 0) {	// Barrack건물이 있는 경우(Marine, Firebat, Medic생성 가능)
				return orderNum;	// 해당 orderNum을 리턴(main에서 생성을 하게 함)
			}
			else {	// Barrack건물이 없는 경우(Marine, Firebat, Medic생성 불가능)
				System.out.println("해당 유닛은 생성할 수 없습니다.");	// 해당 유닛은 생성할 수 없다고 메세지 출력
				return 5;	// 5를 리턴(main에서 취소 기능을 하도록 함(생성할 수 없다는 메세지는 방금 출력하였으므로))
			}
		}
		return orderNum;	// orderNum이 잘못 입력되었거나 둘다 없어서 -1인 경우 그 값을 리턴
	}
	
	public static int checkUsingPopulation(Unit units[], int orderNum, int totalUnitPopulation, int limitPopulation) {	// 유닛이 생성 가능한지 확인하는 함수
		int addPopulation = units[orderNum-1].returnUnitPopulation();	// 유닛이 생성될 경우 추가되는 생성 인구수
		if((totalUnitPopulation + addPopulation) <= limitPopulation) {	// 사용 인구수 + 생성인구수 <= 가능한 총 인구수인 경우(생성 가능)
			orderNum = 0; 	// 유닛을 생성하기 위해 0을 반환
		}
		else {	// 사용 인구수 + 생성인구수 > 가능한 총 인구수인 경우(생성 불가능)
			orderNum = 1;	// 유닛을 생성하기 못하므로 1을 반환
		}
		
		return orderNum;	// orderNum을 리턴(0: 유닛 생성, 1: 유닛 생성 불가)
	}
	
	public static int showExistBuildingList(Scanner scan, int orderNum, Building buildings[]) {	// 생성된 건물들의 목록 출력
		int buildingCount = 0;	// 생성된 건물들의 개수(총 합)
		String buildingName;	// 해당 건물의 이름
		System.out.println("생성된 건물들의 목록");	
		for(int i=0; i<3;i++) {	
			int checkCount = buildings[i].returnBuildingCount();	// 건물의 개수를 checkCount에 저장
			if(checkCount > 0) {	// 건물이 있는 경우
				buildingName = buildings[i].returnBuildingName();	// 해당 건물의 이름을 buildingName에 저장
				System.out.println((i+1) + "." + buildingName);		// (해당 번호).(건물 명)과 같이 출력
			}
			buildingCount += checkCount;	// buildingCount(건물의 개수 총합)에 checkCount(해당 건물의 개수)를 더함
		}
		if(buildingCount == 0) {	// 생성된 건물이 없는 경우(삭제될 건물이 없는 경우)
			System.out.println("생성된 건물은 없습니다.");		// 생성될 건물이 없다고 출력
			return 0;	// 0를 리턴
		}
		else {	// 생성된 건물이 있는 경우
			System.out.println("4.취소");		// 선택지에 취소 추가
		}
		return 1;	// 1을 리턴(생성된 건물이 있는 경우 = 삭제할 건물이 있는 경우)
	}
	
}