package wp_midterm_2019253025;

public class SupplyDepot extends Building{
	
	public SupplyDepot() {		// SupplyDepot의 생성자
		super("SupplyDepot",350, 1);	// buildingName로 SupplyDepot, buildingHp값으로 350, armor값으로 1을 저장(Building class의 생성자를 이용)
	}
	
	public void build() {		// 건물 생성
		buildingCount++;		// 건물 개수 1개 증가
		System.out.println(buildingName + "을 생성합니다. 현재 건물의 수: " + buildingCount);	// 해당 건물을 생성한다고 출력, 해당 건물의 수도 출력
		if(population<200) {	// 전체 인구수가 200이면 전체 인구수가 더 이상 증가하지 않음(최대치)
			population += 10;	// 전체 인구수가 200을 넘지 않으면 계속해서 서플라이 디폿이 생길때 마다 전체 인구수가 10씩 증가
		}
	}
	
	public void destroy() {			// 건물 삭제
		if(buildingCount>0) {		// 건물이 1개라도 있으면 삭제 진행
			buildingCount--;		// 건물 개수 1개 감소
			System.out.println(buildingName + "을 삭제합니다. 현재 건물의 수: " + buildingCount);	// 해당 건물을 삭제한다고 출력, 해당 건물의 수도 출력
			if(buildingCount<19) {	// 서플라이 디폿이 19개보다 같거나 많은 경우 전체 인구수는 줄어들지 않음
				population -= 10;	// 서플라이 디폿이 19개보다 적어진다면 서플라이 디폿의 갯수에 맞춰 전체 인구수가 10씩 감소함
			}
		}
		else {		// 삭제할 건물이 없으면 
			System.out.println(buildingName + "이 존재하지 않습니다.");	// 해당 건물이 존재하지 않는다고 출력
		} 
	}
} 