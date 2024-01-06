package wp_midterm_2019253025;

abstract class Building {
	protected String buildingName;						// �ǹ��� �̸�
	protected int buildingCount = 0;					// �ǹ��� ����
	protected int buildingHp;							// �ǹ��� ü��
	protected int armor;								// �ǹ��� ����
	protected int population = 10;						// �ִ� �α���(�⺻ �α��� 10)
	
	public Building(String buildingName, int buildingHp, int armor) {	// Building class�� ������ (�� �ǹ����� �⺻ ���� ���� ��� �� ������ �Ҵ���)
		this.buildingName = buildingName;	// Building class�� buildingName������ �о�� buildingName���� �Ҵ�
		this.buildingHp = buildingHp;		// Building class�� buildingHp������ �о�� buildingHp���� �Ҵ�
		this.armor = armor;					// Building class�� armor������ �о�� armor���� �Ҵ�
	}
	
	public int isBuild() {		// �ǹ��� ������ �ִ����� �˾ƺ��� �Լ�
		if(buildingCount>0) {	// �ǹ��� ������ �ִ� ���(�ǹ� ������ 0���� ū ���)
			System.out.println(buildingName + "�� �Ǽ��� �Ǿ��ֽ��ϴ�.");	// �ǹ��� �Ǽ��Ǿ��ִٰ� ���
		}
		else {					// �ǹ��� ���������� ���� ���
			System.out.println(buildingName + "�� �Ǽ��Ǿ����� �ʽ��ϴ�.");	// �ǹ��� �Ǽ��Ǿ����� �ʴٰ� ���
		}
		return buildingCount;	// �ش� �ǹ��� ������ ��ȯ(0: ����, 1�̻�: ����)
	}
	
	public abstract void build();	// �޼ҵ��� ���� ������ ��� ���� ���� Ŭ������ ���� �޶����� ��(�ǹ� ����)
	
	public abstract void destroy();	// �޼ҵ��� ���� ������ ��� ���� ���� Ŭ������ ���� �޶����� ��(�ǹ� ����)
	
	public String returnBuildingName() {	// �ǹ��� �̸��� ��������
		return buildingName;
	}
	
	public int returnPopulation() {	// �ǹ�(SupplyDepot)�� ���� �ִ� �α����� ��������
		return population;
	}
	
	public int returnBuildingCount() {	// �ش� �ǹ��� ������ ��������
		return buildingCount;
	}
}
