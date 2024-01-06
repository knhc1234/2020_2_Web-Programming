package wp_midterm_2019253025;

public class Unit {
	protected String name;				// ������ �̸�
	protected int maxhp;				// ������ �ִ� ü��
	protected int atk;					// ������ ���ݷ�
	protected int armor;				// ������ ����
	protected int unitPopulation;		// ������ ��� �α���
	protected int unitCount = 0;		// �ش� ������ ����(����)
	protected int totalUnitPopulation;	// �ش� ���ֿ� ���ǰ� �ִ� �� �α���
	
	public Unit(String name, int maxhp, int atk, int armor, int unitPopulation) {	// Unit�� ������ (�� ���ֵ��� �⺻ ���� ���� ��� �� ������ �Ҵ���)
		this.name = name;	// Unit class�� name������ �о�� name���� �Ҵ�
		this.maxhp = maxhp;	// Unit class�� maxhp������ �о�� maxhp���� �Ҵ�
		this.atk = atk;		// Unit class�� atk������ �о�� atk���� �Ҵ�
		this.armor = armor;	// Unit class�� armor������ �о�� armor���� �Ҵ�
		this.unitPopulation = unitPopulation;	// Unit class�� unitPopulation������ �о�� unitPopulation���� �Ҵ�
	}
	
	public void buildUnit() {	// ������ �����ϴ� �Լ�
		unitCount++;			// �ش� ������ ����(�ش� ������ ���� ����)
		totalUnitPopulation = unitPopulation * unitCount; 	// ����� ���ֿ� ���ǰ� �ִ� �� �α��� ������ ����
	}
	
	public void killUnit() {	// ������ �����ϴ� �Լ�
		if(unitCount>0) {		// �ش� ������ �����Ѵٸ�(�ش� ������ ������ 0���� ũ�ٸ�)
			unitCount--;		// �ش� ������ �����ϰ�(�ش� ������ ���� ����)
			totalUnitPopulation = unitPopulation * unitCount;	// ����� ���ֿ� ���ǰ� �ִ� �� �α��� ������ ����
		}
		else {	// �ش� ������ �������� �ʴٸ�(0)
			System.out.println("���� �� �ִ� ������ �����ϴ�.");		// ���� ������ ���ٰ� ���
		}
	}
	
	public void printUnitInfo() {	// �� ������ ������ ����ϴ� �Լ�
		System.out.println("������ �̸�: " + name);			// ������ �̸� ���
		System.out.println("������ �ִ� ü��: " + maxhp);	// ������ �ִ� ü�� ���
		System.out.println("������ ���ݷ�: " + atk);		// ������ ���ݷ� ���
		System.out.println("������ ����: " + armor);		// ������ ���� ���
		System.out.println("�� ���ֿ� �ʿ��� �α���: " + unitPopulation);	// �� ���ֿ� �ʿ��� �α��� ���
	}
	
	public void printUnitPopulation() {		// �ش� ������ ���ڿ� �ش� �������� ���ǰ� �ִ� �α����� ���
		System.out.println(name + "������ ����(����): " + unitCount);	// �ش� ������ ����(����)�� ���
		System.out.println(name + "�������� ���ǰ� �ִ� �α���: " + totalUnitPopulation);	// �ش� �������� ���ǰ� �ִ� �α����� ���
	}
	
	public String returnUnitName() {	// �ش� ������ �̸��� ��������
		return name;
	}
	
	public int returnUnitPopulation() {	// �ش� ������ ��� �α����� ��������
		return unitPopulation;
	}
	
	public int returnUnitCount() {		// �ش� ������ ������ ��������
		return unitCount;
	}
	
	public int returnTotalUnitPopulation() {	// �ش� ���ֿ� ���ǰ� �ִ� �� �α����� ��������
		return totalUnitPopulation;
	}
}
