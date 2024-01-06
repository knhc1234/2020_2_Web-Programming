package wp_midterm_2019253025;

class CommandCenter extends Building{
	
	public CommandCenter() {	// CommandCenter�� ������
		super("CommandCenter",1500, 1);		// buildingName�� CommandCenter, buildingHp������ 1500, armor������ 1�� ����(Building class�� �����ڸ� �̿�)
	}
	
	public void build() {	// �ǹ� ����
		buildingCount++;	// �ǹ� ���� 1�� ����
		System.out.println(buildingName + "�� �����մϴ�. ���� �ǹ��� ��: " + buildingCount);	// �ش� �ǹ��� �����Ѵٰ� ���, �ش� �ǹ��� ���� ���
	}
	
	public void destroy() {		// �ǹ� ����
		if(buildingCount>0) {	// �ǹ��� 1���� ������ ���� ����
			buildingCount--;	// �ǹ� ���� 1�� ����
			System.out.println(buildingName + "�� �����մϴ�. ���� �ǹ��� ��: " + buildingCount);	// �ش� �ǹ��� �����Ѵٰ� ���, �ش� �ǹ��� ���� ���
		}
		else {		// ������ �ǹ��� ������ 
			System.out.println(buildingName + "�� �������� �ʽ��ϴ�.");	// �ش� �ǹ��� �������� �ʴ´ٰ� ���
		}
	}
}
