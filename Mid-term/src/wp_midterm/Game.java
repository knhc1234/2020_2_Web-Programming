package wp_midterm_2019253025;

import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		int orderNum = 0;			// �� ��ɾ �ٷ�� ���� ����
		int checkNum = 0;			// �� ��ɾ�� �Բ� ��ɾ �ٷ�� ���� ����(���� ��ɾ�)
		int p[] = {0,0,0,0};		// �� ������ ��� �α����� �����ϱ� ���� �迭
		int totalUnitPopulation;	// �� ������ ��� �α����� ��� ���� ���� �����ϱ� ���� ����
		int limitPopulation;		// �ִ�� ���� �� �ִ� �α���(���α���)�� �����ϱ� ���� ����
		Scanner scan = new Scanner(System.in);		// System.in�� ���� Scanner��ü�� ����
		Building buildings[] = {new CommandCenter(), new SupplyDepot(), new Barrack()};	// buildings�迭�� ���� CommandCenter�� SupplyDepot, Barrack�� �Ҵ� ��(�����ڸ� �̿�) 
		Unit units[]= {new SCV(), new Marine(), new Firebat(), new Medic()};			// units�迭�� ���� SCV, Marine, Firebat, Medic�� �Ҵ� ��(�����ڸ� �̿�)
		
		for(int i=0; i<4; i++) {	// p�迭�� �� ������ ��� �α����� ������
			p[i] = units[i].returnTotalUnitPopulation();	 
		}
		totalUnitPopulation = p[0] + p[1] + p[2] + p[3];	// �� ������ ��� �α����� ��� ���� ���� ���
		
		while(true) {	// �ý��� ����� while���� ���������� ������ ����ؼ� �ݺ�(��� ��� �������� ���ư��� ��)
			//for(int i=0; i<4; i++) {
			//	p[i] = units[i].returnTotalUnitPopulation();	// 
			//}
			//totalUnitPopulation = p[0] + p[1] + p[2] + p[3];
			limitPopulation = buildings[1].returnPopulation();	// �ִ�� ���� �� �ִ� �α����� SupplyDepot�� Population����
			orderNum = showMenuList(scan, orderNum);	// ��� ��� ����(����� ���ڷ� �����Ͽ� orderNum���� �Ѱ���)
			if(orderNum == 1) {		// 1.�ǹ� �Ǽ�
				orderNum = showBuildingList(scan, orderNum);	// �Ǽ��� �ǹ��� ����� �����ְ� ��ɾ orderNum�� �Ѱ���
				if(orderNum == 1 || orderNum == 2 || orderNum == 3) {		// orderNum�� 1(CommandCenter�Ǽ�)�Ǵ� 2(SupplyDepot�Ǽ�)�Ǵ� 3(Barrack�Ǽ�)�� ���
					checkNum = checkBuildingLimit(orderNum, buildings);		// �Ǽ��� �������� Ȯ���Ͽ� ������ �����ϸ� countNum�� 1�� �� (�Ǽ��� �Ұ����ϴٸ� �Լ������� �Ұ����� ������ ��µǰ� countNum�� 0�� ��)
					if(checkNum == 1) {		// �Ǽ��� �����ϴٸ�
						buildings[orderNum-1].build();	// �ش� �ǹ��� �Ǽ� ��
					}
				}
				else if(orderNum == 4) {	// orderNum�� 4(���)�� ���
					System.out.println("�ǹ� �Ǽ��� ����մϴ�. ���� �޴��� ���ư��ϴ�.");	// �ǹ� �Ǽ��� ����ϰ� ���� �޴��� ���ư��ٰ� ���
				}
				else {	// �� ���� ��� �߸��� �Է��̹Ƿ�
					System.out.println("�߸��� �Է��Դϴ�. ���� �޴��� ���ư��ϴ�.");	// �߸��� �Է��̸� ���� �޴��� ���ư��ٰ� ���
				}
			}
			else if(orderNum == 2) {	// 2.���� ��ȸ
				orderNum = showUnitList(p, units, totalUnitPopulation, limitPopulation);	// ������ ���� ��� �� ��� �α����� ����� 
			}
			else if(orderNum == 3) {	// 3.���� ����
				orderNum = showUnitCreateList(scan, buildings, orderNum);	// ���� ������ ������ ����� �����(������ ������ ���ڷ� �����Ͽ� orderNum���� �Ѱ���)
				if(orderNum == -1) {	// orderNum�� -1(���� ������ ���� ����)�� ���
					System.out.println("������ �� �ִ� ������ �����ϴ�.");	// ������ �� �ִ� ������ ���ٰ� ���
				}
				else if(orderNum == 1 || orderNum == 2 || orderNum == 3 || orderNum == 4) {		// orderNum�� 1(SCV����), 2(Marine����), 3(Firebat����), 4(Medic����)�� ���
					System.out.println("������ ����");
					units[orderNum-1].printUnitInfo();	// �ش� ������ ������ ���
					System.out.print("�ش� ������ �����Ͻðڽ��ϱ�(0:yes, 1:no)?: ");	// �ش� ������ ������ ������ ����� �޼��� ���
					checkNum = scan.nextInt();	// ���� �Է¹���(0:yes, 1:no, ������: �߸��� �Է�)
					if(checkNum == 0) {		// 0�� �Է¹��� ���(�����ϴ� ���)
						checkNum = checkUsingPopulation(units, orderNum, totalUnitPopulation, limitPopulation);		// ����α����� Ȯ����((����α���+�����α���)<=���α��� �� ��� ������ �����ϹǷ� checkNum�� 0�̵�, �ƴ� ��� �α����� �����Ͽ� ������ �Ұ����ϹǷ� checkNum�� 1�̵�)
						if(checkNum == 0) {	// ���� ������ ���
							String s = units[orderNum-1].returnUnitName();	// �ش� ������ �̸��� s�� ����(�Ʒ� �޼����� �̿�)
							units[orderNum-1].buildUnit();					// �ش� ������ ������
							System.out.println(s + "������ �����Ǿ����ϴ�.");		// �ش� ������ �����Ǿ��ٴ� �޼��� ���
							p[orderNum-1] = units[orderNum-1].returnTotalUnitPopulation();	// �ش� ������ ������ �α��� ������ p�迭�� ����(�ش� ���� ������ ��� �ִ� �ε����� ����)
							totalUnitPopulation = p[0] + p[1] + p[2] + p[3];	// ������ �� �α��� ������ �ٽ� ���
							System.out.println("�α��� : " + totalUnitPopulation + " / " + limitPopulation);	// ������ �α��� ������ ���
						}
						else {	// ������ �Ұ����� ���
							System.out.println("������ ������ �� �����ϴ�.(��� ������ �α����� �����մϴ�)");	// ������ �Ұ����ϴٰ� �޼��� ���
							System.out.println("�α��� : " + totalUnitPopulation + " / " + limitPopulation);	// �α��� ������ ���
						}
					}
					else if(checkNum == 1) { // 1�� �Է¹��� ���(����ϴ� ���)
						System.out.println("������ ����մϴ�. ���� �޴��� ���ư��ϴ�.");	// ������ ����ϰ� ���θ޴��� �̵��Ѵٰ� ���
					}
					else {	// 0�� 1�� �ƴ� ���� �Է¹��� ���(�߸��� �Է�)
						System.out.println("�߸��� �Է��Դϴ�. ���� �޴��� ���ư��ϴ�.");	// �Է��� �߸��Ǿ����� ���θ޴��� �̵��Ѵٰ� ���
					}
				}
				else if(orderNum == 5) {	// orderNum�� 5(���)�� ���
					System.out.println("������ ����մϴ�. ���� �޴��� ���ư��ϴ�.");		// ������ ����ϰ� ���θ޴��� �̵��Ѵٰ� ���
				}
				else {	// orderNum�� �߸� �Էµ� ���
					System.out.println("�߸��� �Է��Դϴ�. ���� �޴��� ���ư��ϴ�.");		// �Է��� �߸��Ǿ����� ���θ޴��� �̵��Ѵٰ� ���
				}
			}
			else if(orderNum == 4) {	// 4. ���� ����
				orderNum = showUnitList(p, units, totalUnitPopulation, limitPopulation);	// ������ ���ֵ��� ����� ���(������ ������ ������ 0�� ��ȯ, ������ 1�� ��ȯ)
				if(orderNum == 1) {		// ������ ������ �ִ� ���
					System.out.print("�����Ͻ� ������ ������ �ֽʽÿ�(����(0:���)): ");	// ������ ������ ����� ���� ������ ������ ����
					orderNum = scan.nextInt();	// ������ ����(����)�� �Է� ����
					if(orderNum == 0) {	// orderNum�� 0(���)�� ���
						System.out.println("������ ����մϴ�. ���� �޴��� ���ư��ϴ�.");	// ������ ����ϰ� ���θ޴��� �̵��Ѵٰ� ���
					}
					else if(orderNum == 1 || orderNum == 2 || orderNum == 3 || orderNum == 4) {		// orderNum�� 1(SCV), 2(Marine), 3(Firebat), 4(Medic)�� ���
						String s = units[orderNum-1].returnUnitName();			// �ش� ������ �̸��� s�� ����(�Ʒ� �޼����� �̿��)
						int unitCount = units[orderNum-1].returnUnitCount();	// �ش� ������ ������ ������ unitCount�� ����
						if(unitCount > 0) {		// �ش� ������ �����ϴ� ���(1�� �̻��� ���)
							units[orderNum-1].killUnit();	// �ش� ���� 1���� ����
							unitCount = units[orderNum-1].returnUnitCount();	// ����� ���� ���� ����
							System.out.println(s + "������ �����Ǿ����ϴ�. ���� " + s + "���ּ�: " + unitCount);	// ������ ������ �ش� ������ ���� ������ ���
							p[orderNum-1] = units[orderNum-1].returnTotalUnitPopulation();	// �ش� ������ ������ �α��� ������ p�迭�� ����(�ش� ���� ������ ��� �ִ� �ε����� ����)
							totalUnitPopulation = p[0] + p[1] + p[2] + p[3];	// ������ �� �α��� ������ �ٽ� ��� 
							System.out.println("�α��� : " + totalUnitPopulation + " / " + limitPopulation);	// ������ �α��� ������ ���
						}
						else {	// �ش� ������ �������� �ʴ� ���
							System.out.println("������ " + s + "������ �������� �ʽ��ϴ�.");	// ������ ������ �������� �ʴ´ٰ� ���
						}
					}
					else {	// orderNum�� �߸� �Էµ� ���
						System.out.println("�߸��� �Է��Դϴ�. ���� �޴��� ���ư��ϴ�.");	// �Է��� �߸��Ǿ����� ���θ޴��� �̵��Ѵٰ� ���
					}
				}	
				else {	// ������ ������ ���� ���(��� ������ ���� - ������ ���ֵ� ����)
					System.out.println("������ ������ �������� �ʽ��ϴ�.");	// ������ ������ �������� �ʴ´ٰ� ���
				}
			}
			else if(orderNum == 5) {	// 5.�ǹ� ����
				orderNum = showExistBuildingList(scan, orderNum, buildings);	// ������ �ǹ����� ����� ���(������ �ǹ��� ������ 0�� ��ȯ, ������ 1�� ��ȯ)
				if(orderNum == 1) {		// ������ �ǹ��� �ִ� ���
					System.out.print("�����Ͻ� �ǹ��� ������ �ֽʽÿ�: ");	// ������ ������ ����� ���� ������ �ǹ��� ����
					orderNum = scan.nextInt();	// ������ �ǹ�(����)�� �Է� ����
					if(orderNum == 4) {	// orderNum�� 4(���)�� ���
						System.out.println("������ ����մϴ�. ���� �޴��� ���ư��ϴ�.");	// ������ ����ϰ� ���θ޴��� �̵��Ѵٰ� ���
					}
					else if(orderNum == 1 || orderNum == 2 || orderNum == 3) {		// orderNum�� 1(CommandCenter), 2(SupplyDepot), 3(Barrack)�� ���
						String s = buildings[orderNum-1].returnBuildingName();			// �ش� �ǹ��� �̸��� s�� ����(�Ʒ� �޼����� �̿��)
						int buildingCount = buildings[orderNum-1].returnBuildingCount();		// �ش� �ǹ��� ������ buildingCount�� ����
						if(buildingCount > 0) {		// �ش� �ǹ��� �����ϴ� ���(1�� �̻��� ���)
							buildings[orderNum-1].destroy();	// �ش� �ǹ� 1���� ����
							buildingCount = buildings[orderNum-1].returnBuildingCount();	// ����� �ǹ� ���� ����
							System.out.println(s + "�� �����Ǿ����ϴ�. ���� " + s + "��: " + buildingCount);	// �ǹ��� ������ �ش� �ǹ��� ���� ������ ���
						}
						else {	// �ش� �ǹ��� �������� �ʴ� ���
							System.out.println("������ " + s + "�� �������� �ʽ��ϴ�.");	// ������ �ǹ��� �������� �ʴ´ٰ� ���
						}
					}
					else {	// orderNum�� �߸� �Էµ� ���
						System.out.println("�߸��� �Է��Դϴ�. ���� �޴��� ���ư��ϴ�.");	// �Է��� �߸��Ǿ����� ���θ޴��� �̵��Ѵٰ� ���
					}
				}	
				else {	// ������ �ǹ��� ���� ���(������ �ǹ��� ����)
					System.out.println("������ �ǹ��� �������� �ʽ��ϴ�.");	// ������ �ǹ��� �������� �ʴ´ٰ� ���
				}
			}
			else if(orderNum == 6) {	// 6. �ý��� ����
				System.out.println("�ý����� �����մϴ�.");	// �ý����� �����Ѵٰ� ���
				break;	// break�� while���� �������� ���α׷��� �����
			}
			else {	// orderNum�� �߸� �Էµ� ���
				System.out.println("�߸��� �Է��Դϴ�.");	// �߸��� �Է��̶�� ���
			}
		}
		scan.close();
	}
	
	public static int showMenuList(Scanner scan, int orderNum) {	// ��� ��� ���
		System.out.println("��� ��� ");		// �� ����� ��ϵ��� ���
		System.out.println("1.�ǹ� �Ǽ�");
		System.out.println("2.���� ��ȸ");
		System.out.println("3.���� ����");
		System.out.println("4.���� ����");
		System.out.println("5.�ǹ� ����");
		System.out.println("6.����");
		System.out.print("���Ͻô� ����� ������ �ֽʽÿ�(����): ");	// ���ϴ� ����� �����϶�� ���
		orderNum = scan.nextInt();	// ���ڷ� �Է¹޾� orderNum�� �����ϰ�
		return orderNum;	// orderNum�� ������(�� orderNum�� �̿��� main���� ��� ����)
	}
	
	public static int showBuildingList(Scanner scan, int orderNum) {	// �ǹ� ���� ���
		System.out.println("�ǹ� ����");		
		System.out.println("1.CommandCenter");
		System.out.println("2.SupplyDepot");
		System.out.println("3.Barrack");
		System.out.println("4.���");
		System.out.print("���Ͻô� �ǹ��� ������ �ֽʽÿ�(����): ");	// ���ϴ� ���(��ġ,���� �� �ǹ�)�� �����϶�� ���
		orderNum = scan.nextInt();	// ���ڷ� �Է¹޾� orderNum�� �����ϰ�
		return orderNum;	// orderNum�� ������(�� orderNum�� �̿��� main���� ��� ����)
	}
	
	public static int checkBuildingLimit(int orderNum, Building buildings[]) {	// �ǹ� ��ġ �� ������ �����ϴ��� Ȯ����(������ �����ϸ� 1�� return �ƴϸ� 0�� return)
		int buildingCount = 0;	// �ش� �ǹ��� ����(��ġ�� �ǹ� ��)�� Ȯ��
		String buildingName;	// �ش� �ǹ��� �̸��� �����ϱ� ���� ����(��¿��� ���)
		if(orderNum == 1) {		// orderNum�� 1(CommandCenter��ġ)�� ���
			buildingCount = buildings[0].isBuild();	// CommandCenter�ǹ��� �������ִ��� �ƴ��� ����ϰ� CommandCenter�ǹ��� ������ buildingCount�� ����(���ٸ� ���� ����)
		}
		else {	// orderNum�� 2(SupplyDepot), 3(Barrack)�� ���(�������� main���� �ɷ���)
			buildingCount = buildings[0].isBuild();	// CommandCenter�ǹ��� �������ִ��� �ƴ��� ����ϰ� CommandCenter�ǹ��� ������ buildingCount�� ����
			if(buildingCount == 0) {	// CommandCenter�ǹ��� ���� ��� (SupplyDepot�� Barrack�� �Ǽ� �Ұ���)
				buildingName = buildings[orderNum-1].returnBuildingName();	// ������� �ߴ� �ǹ��� �̸��� buildingName�� ����
				System.out.println(buildingName + "�� �Ǽ��ϱ� ���ؼ��� CommandCenter�� �ʿ��մϴ�.");	// �ش� �ǹ��� �Ǽ��Ϸ��� CommandCenter�� �ʿ��ϴٰ� ���
				return 0;	// 0�� ����
			}
			else {	// CommandCenter�ǹ��� �ִ� ���
				buildingCount = buildings[orderNum-1].isBuild();	// CommandCenter�ǹ��� �������ִ��� �ƴ��� ����ϰ� CommandCenter�ǹ��� ������ buildingCount�� ����
			}
		}
		return 1;	// �Ǽ��� ������ ��� 1�� ����(�Ұ����� ���� �߰��� 0���� ���� ó���� �̸� ����)
	}
	
	public static int showUnitList(int p[], Unit units[], int totalUnitPopulation, int limitPopulation) {	// ������ ���ֵ��� ��� ���
		System.out.println("������ ���ֵ��� ���");
		if(totalUnitPopulation == 0) {	// ������ ������ ���� ���(�α��� 0 = ���ּ� 0)
			System.out.println("������ ������ �����ϴ�.");		// ������ ������ ���ٰ� ���
			return 0;	// 0�� ����
		}
		else {	// ������ ������ �ִ� ���
			for(int i=0; i<4; i++) {	// for������ p�迭�� �̿��Ͽ�
				if(p[i] > 0) {			// p[i] > 0�̸�(�ش� ������ �ִ� ���) 
					System.out.print((i+1) + ".");	// �ش� ���ֿ� �ش��ϴ� ��ȣ�� (1: SCV, 2: Marine, 3: Firebat, 4: Medic)
					units[i].printUnitPopulation();	// �ش� ������ ������ �ش� �������� ���ǰ� �ִ� �α����� ���
				}
			}
		}
		System.out.println("�α��� : " + totalUnitPopulation + " / " + limitPopulation);	// �α��� ������ ���
		return 1;	// 1�� ����(������ ������ �ִ� ��� = ������ ������ �ִ� ���)
	}
	
	public static int showUnitCreateList(Scanner scan, Building buildings[], int orderNum) {	// ���� ������ ���ֵ��� ��� ���
		int buildingCount1 = buildings[0].isBuild();	// CommandCenter�ǹ��� ����
		int buildingCount2 = buildings[2].isBuild();	// Barrack�ǹ��� ����
		System.out.println("���� ������ ���ֵ��� ���");		// ���� ������ ���ֵ��� ��� ���
		if(buildingCount1 > 0) {	// CommandCenter�ǹ��� �ִ� ���(SCV���� ����)
			System.out.println("1.SCV");	// ���� ���� ��Ͽ� SCV�߰�
		}
		if(buildingCount2 > 0) {	// Barrack�ǹ��� �ִ� ���(Marine, Firebat, Medic���� ����)
			System.out.println("2.Marine");	// ���� ���� ��Ͽ� Marine, Firebat, Medic�߰�
			System.out.println("3.Firebat");
			System.out.println("4.Medic");
		}
		if(buildingCount1 == 0 && buildingCount2 == 0) {	// CommandCenter�ǹ��� Barrack�ǹ��� �� �� ���� ���
			orderNum = -1;	// orderNum�� -1�� ����(���߿� �����Ҷ� -1�� ���ϵǾ� main���� ������ �� �ִ� ������ ���ٰ� ��� ��)
		}
		else {	// CommandCenter�ǹ��� Barrack�ǹ��� �ϳ��� �ִ� ���
			System.out.println("5.���");		// �ɼ����� ��ҵ� �߰�
			System.out.print("������ ���Ͻô� ������ ������ �ֽʽÿ�(����): ");	// ������ ���ϴ� ������ �Է¹ޱ� ���� �޼��� ���
			orderNum = scan.nextInt();		// ������ ���ϴ� ������ ��ȣ�� �Է� ����
		}
		
		if(orderNum == 1) {	// orderNum�� 1�� ���(SCV����)	(�� if���� �ִ� ������ �� if���� ���� �� ���� ��Ͽ��� SCV�� ���������ϴ��� 2(Marine)�� ������ Marine�� ������ �� �ֱ� ����)
			if(buildingCount1 > 0) {	// CommandCenter�ǹ��� �ִ� ���(SCV����)
				return orderNum;		// 1�� ����(main���� SCV����)
			}
			else {	// CommandCenter�ǹ��� ���� ���(SCV���� �Ұ���)
				System.out.println("�ش� ������ ������ �� �����ϴ�.");	// �ش� ������ ������ �� ���ٰ� �޼��� ���
				return 5;	// 5�� ����(main���� ��� ����� �ϵ��� ��(������ �� ���ٴ� �޼����� ��� ����Ͽ����Ƿ�))
			}
		}
		else if(orderNum==2 || orderNum==3 || orderNum==4) {	// orderNum�� 2(Marine����) �Ǵ� 3(Firebat����) �Ǵ� 4(Medic����)�� ���
			if(buildingCount2 > 0) {	// Barrack�ǹ��� �ִ� ���(Marine, Firebat, Medic���� ����)
				return orderNum;	// �ش� orderNum�� ����(main���� ������ �ϰ� ��)
			}
			else {	// Barrack�ǹ��� ���� ���(Marine, Firebat, Medic���� �Ұ���)
				System.out.println("�ش� ������ ������ �� �����ϴ�.");	// �ش� ������ ������ �� ���ٰ� �޼��� ���
				return 5;	// 5�� ����(main���� ��� ����� �ϵ��� ��(������ �� ���ٴ� �޼����� ��� ����Ͽ����Ƿ�))
			}
		}
		return orderNum;	// orderNum�� �߸� �ԷµǾ��ų� �Ѵ� ��� -1�� ��� �� ���� ����
	}
	
	public static int checkUsingPopulation(Unit units[], int orderNum, int totalUnitPopulation, int limitPopulation) {	// ������ ���� �������� Ȯ���ϴ� �Լ�
		int addPopulation = units[orderNum-1].returnUnitPopulation();	// ������ ������ ��� �߰��Ǵ� ���� �α���
		if((totalUnitPopulation + addPopulation) <= limitPopulation) {	// ��� �α��� + �����α��� <= ������ �� �α����� ���(���� ����)
			orderNum = 0; 	// ������ �����ϱ� ���� 0�� ��ȯ
		}
		else {	// ��� �α��� + �����α��� > ������ �� �α����� ���(���� �Ұ���)
			orderNum = 1;	// ������ �����ϱ� ���ϹǷ� 1�� ��ȯ
		}
		
		return orderNum;	// orderNum�� ����(0: ���� ����, 1: ���� ���� �Ұ�)
	}
	
	public static int showExistBuildingList(Scanner scan, int orderNum, Building buildings[]) {	// ������ �ǹ����� ��� ���
		int buildingCount = 0;	// ������ �ǹ����� ����(�� ��)
		String buildingName;	// �ش� �ǹ��� �̸�
		System.out.println("������ �ǹ����� ���");	
		for(int i=0; i<3;i++) {	
			int checkCount = buildings[i].returnBuildingCount();	// �ǹ��� ������ checkCount�� ����
			if(checkCount > 0) {	// �ǹ��� �ִ� ���
				buildingName = buildings[i].returnBuildingName();	// �ش� �ǹ��� �̸��� buildingName�� ����
				System.out.println((i+1) + "." + buildingName);		// (�ش� ��ȣ).(�ǹ� ��)�� ���� ���
			}
			buildingCount += checkCount;	// buildingCount(�ǹ��� ���� ����)�� checkCount(�ش� �ǹ��� ����)�� ����
		}
		if(buildingCount == 0) {	// ������ �ǹ��� ���� ���(������ �ǹ��� ���� ���)
			System.out.println("������ �ǹ��� �����ϴ�.");		// ������ �ǹ��� ���ٰ� ���
			return 0;	// 0�� ����
		}
		else {	// ������ �ǹ��� �ִ� ���
			System.out.println("4.���");		// �������� ��� �߰�
		}
		return 1;	// 1�� ����(������ �ǹ��� �ִ� ��� = ������ �ǹ��� �ִ� ���)
	}
	
}