package wp_midterm_2019253025;

public class Unit {
	protected String name;				// 유닛의 이름
	protected int maxhp;				// 유닛의 최대 체력
	protected int atk;					// 유닛의 공격력
	protected int armor;				// 유닛의 방어력
	protected int unitPopulation;		// 유닛의 사용 인구수
	protected int unitCount = 0;		// 해당 유닛의 숫자(개수)
	protected int totalUnitPopulation;	// 해당 유닛에 사용되고 있는 총 인구수
	
	public Unit(String name, int maxhp, int atk, int armor, int unitPopulation) {	// Unit의 생성자 (각 유닛들의 기본 정보 값을 얻어 각 변수에 할당함)
		this.name = name;	// Unit class의 name변수에 읽어온 name값을 할당
		this.maxhp = maxhp;	// Unit class의 maxhp변수에 읽어온 maxhp값을 할당
		this.atk = atk;		// Unit class의 atk변수에 읽어온 atk값을 할당
		this.armor = armor;	// Unit class의 armor변수에 읽어온 armor값을 할당
		this.unitPopulation = unitPopulation;	// Unit class의 unitPopulation변수에 읽어온 unitPopulation값을 할당
	}
	
	public void buildUnit() {	// 유닛을 생성하는 함수
		unitCount++;			// 해당 유닛을 생성(해당 유닛의 숫자 증가)
		totalUnitPopulation = unitPopulation * unitCount; 	// 변경된 유닛에 사용되고 있는 총 인구수 정보를 수정
	}
	
	public void killUnit() {	// 유닛을 삭제하는 함수
		if(unitCount>0) {		// 해당 유닛이 존재한다면(해당 유닛의 개수가 0보다 크다면)
			unitCount--;		// 해당 유닛을 삭제하고(해당 유닛의 숫자 감소)
			totalUnitPopulation = unitPopulation * unitCount;	// 변경된 유닛에 사용되고 있는 총 인구수 정보를 수정
		}
		else {	// 해당 유닛이 존재하지 않다면(0)
			System.out.println("죽일 수 있는 유닛이 없습니다.");		// 죽일 유닛이 없다고 출력
		}
	}
	
	public void printUnitInfo() {	// 각 유닛의 정보를 출력하는 함수
		System.out.println("유닛의 이름: " + name);			// 유닛의 이름 출력
		System.out.println("유닛의 최대 체력: " + maxhp);	// 유닛의 최대 체력 출력
		System.out.println("유닛의 공격력: " + atk);		// 유닛의 공격력 출력
		System.out.println("유닛의 방어력: " + armor);		// 유닛의 방어력 출력
		System.out.println("한 유닛에 필요한 인구수: " + unitPopulation);	// 한 유닛에 필요한 인구수 출력
	}
	
	public void printUnitPopulation() {		// 해당 유닛의 숫자와 해당 유닛으로 사용되고 있는 인구수를 출력
		System.out.println(name + "유닛의 숫자(개수): " + unitCount);	// 해당 유닛의 숫자(개수)를 출력
		System.out.println(name + "유닛으로 사용되고 있는 인구수: " + totalUnitPopulation);	// 해당 유닛으로 사용되고 있는 인구수를 출력
	}
	
	public String returnUnitName() {	// 해당 유닛의 이름을 리턴해줌
		return name;
	}
	
	public int returnUnitPopulation() {	// 해당 유닛의 사용 인구수를 리턴해줌
		return unitPopulation;
	}
	
	public int returnUnitCount() {		// 해당 유닛의 개수를 리턴해줌
		return unitCount;
	}
	
	public int returnTotalUnitPopulation() {	// 해당 유닛에 사용되고 있는 총 인구수를 리턴해줌
		return totalUnitPopulation;
	}
}
