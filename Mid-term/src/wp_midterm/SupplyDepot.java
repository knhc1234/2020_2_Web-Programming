package wp_midterm_2019253025;

public class SupplyDepot extends Building{
	
	public SupplyDepot() {		// SupplyDepot�� ������
		super("SupplyDepot",350, 1);	// buildingName�� SupplyDepot, buildingHp������ 350, armor������ 1�� ����(Building class�� �����ڸ� �̿�)
	}
	
	public void build() {		// �ǹ� ����
		buildingCount++;		// �ǹ� ���� 1�� ����
		System.out.println(buildingName + "�� �����մϴ�. ���� �ǹ��� ��: " + buildingCount);	// �ش� �ǹ��� �����Ѵٰ� ���, �ش� �ǹ��� ���� ���
		if(population<200) {	// ��ü �α����� 200�̸� ��ü �α����� �� �̻� �������� ����(�ִ�ġ)
			population += 10;	// ��ü �α����� 200�� ���� ������ ����ؼ� ���ö��� ������ ���涧 ���� ��ü �α����� 10�� ����
		}
	}
	
	public void destroy() {			// �ǹ� ����
		if(buildingCount>0) {		// �ǹ��� 1���� ������ ���� ����
			buildingCount--;		// �ǹ� ���� 1�� ����
			System.out.println(buildingName + "�� �����մϴ�. ���� �ǹ��� ��: " + buildingCount);	// �ش� �ǹ��� �����Ѵٰ� ���, �ش� �ǹ��� ���� ���
			if(buildingCount<19) {	// ���ö��� ������ 19������ ���ų� ���� ��� ��ü �α����� �پ���� ����
				population -= 10;	// ���ö��� ������ 19������ �������ٸ� ���ö��� ������ ������ ���� ��ü �α����� 10�� ������
			}
		}
		else {		// ������ �ǹ��� ������ 
			System.out.println(buildingName + "�� �������� �ʽ��ϴ�.");	// �ش� �ǹ��� �������� �ʴ´ٰ� ���
		} 
	}
} 