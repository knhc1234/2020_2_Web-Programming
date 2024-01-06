package wp_midterm_2019253025;

public class Barrack extends Building{
	
	public Barrack() {	// Barrack의 생성자
		super("Barrack",1000, 1);	// buildingName로 Barrack, buildingHp값으로 1000, armor값으로 1을 저장(Building class의 생성자를 이용)
	}
	
	public void build() {	// 건물 생성
		buildingCount++;	// 건물 개수 1개 증가
		System.out.println(buildingName + "을 생성합니다. 현재 건물의 수: " + buildingCount);	// 해당 건물을 생성한다고 출력, 해당 건물의 수도 출력
	}
	
	public void destroy() {		// 건물 삭제
		if(buildingCount>0) {	// 건물이 1개라도 있으면 삭제 진행
			buildingCount--;	// 건물 개수 1개 감소
			System.out.println(buildingName + "을 삭제합니다. 현재 건물의 수: " + buildingCount);	// 해당 건물을 삭제한다고 출력, 해당 건물의 수도 출력
		}
		else {		// 삭제할 건물이 없으면 
			System.out.println(buildingName + "이 존재하지 않습니다.");	// 해당 건물이 존재하지 않는다고 출력
		}
	}
}
