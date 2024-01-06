package wp_midterm_2019253025;

abstract class Building {
	protected String buildingName;						// 건물의 이름
	protected int buildingCount = 0;					// 건물의 개수
	protected int buildingHp;							// 건물의 체력
	protected int armor;								// 건물의 방어력
	protected int population = 10;						// 최대 인구수(기본 인구수 10)
	
	public Building(String buildingName, int buildingHp, int armor) {	// Building class의 생성자 (각 건물들의 기본 정보 값을 얻어 각 변수에 할당함)
		this.buildingName = buildingName;	// Building class의 buildingName변수에 읽어온 buildingName값을 할당
		this.buildingHp = buildingHp;		// Building class의 buildingHp변수에 읽어온 buildingHp값을 할당
		this.armor = armor;					// Building class의 armor변수에 읽어온 armor값을 할당
	}
	
	public int isBuild() {		// 건물이 지어져 있는지를 알아보는 함수
		if(buildingCount>0) {	// 건물이 지어져 있는 경우(건물 개수가 0보다 큰 경우)
			System.out.println(buildingName + "은 건설이 되어있습니다.");	// 건물이 건설되어있다고 출력
		}
		else {					// 건물이 지어져있지 않은 경우
			System.out.println(buildingName + "은 건설되어있지 않습니다.");	// 건물이 건설되어있지 않다고 출력
		}
		return buildingCount;	// 해당 건물의 개수를 반환(0: 없음, 1이상: 있음)
	}
	
	public abstract void build();	// 메소드의 실제 동작은 상속 받은 하위 클래스에 따라 달라지게 함(건물 생성)
	
	public abstract void destroy();	// 메소드의 실제 동작은 상속 받은 하위 클래스에 따라 달라지게 함(건물 삭제)
	
	public String returnBuildingName() {	// 건물의 이름을 리턴해줌
		return buildingName;
	}
	
	public int returnPopulation() {	// 건물(SupplyDepot)로 인한 최대 인구수를 리턴해줌
		return population;
	}
	
	public int returnBuildingCount() {	// 해당 건물의 개수를 리턴해줌
		return buildingCount;
	}
}
