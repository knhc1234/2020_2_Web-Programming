package chap3;

import java.util.Scanner;

public class ex1 {
	
	public static void main(String[] args) {
		double a = 0.0d;	
		double b = 0.0d;
		double c = 0.0d;
		// 입력 받을 double 타입 계수 a,b,c를 선언
		Scanner scan = new Scanner(System.in);
		// System.in에 대한 Scanner객체를 만듦(a,b,c값을 얻기 위함)
		System.out.println("계수 a : ");	// 계수 a의 값을 입력하라고 출력
		a = scan.nextDouble();			// 계수 a의 값을 입력 받음
		
		while (a == 0) {	// a가 0인경우 다른 값을 다시 입력 받음(문제 조건: a!=0), 다시 입력 받은 값이 0이 아닐 때까지 반복
 			System.out.println("a의 값은 0이 될 수 없습니다. 다른 값을 입력해 주세요.");	// a의 값은 0이 되면 안됨(문제 조건)
			System.out.println("계수 a : ");	// 계수 a의 값을 입력하라고 출력
			a = scan.nextDouble();			// 계수 a의 값을 입력 받음
		}
		
		System.out.println("계수 b : ");		// 계수 b의 값을 입력하라고 출력
		b = scan.nextDouble();				// 계수 b의 값을 입력 받음
		
		System.out.println("계수 c : ");		// 계수 c의 값을 입력하라고 출력
		c = scan.nextDouble();				// 계수 c의 값을 입력 받음
		
		double d = b*b - 4*a*c;		// b^2 - 4ac의 값을 계산(루트처리를 따로 해주기 위해서 + d>0이면 두 실근을 가지고, d=0이면 하나의 중근을 가지며 d<0이면 실근이 없음)
		
		if(d > 0) {	// 두 실근이 존재하는 경우
			double answer1 = (-b - Math.sqrt(d)) / (2*a);	// 첫번째 실근의 값을 계산
			double answer2 = (-b + Math.sqrt(d)) / (2*a);	// 두번째 실근의 값을 계산
			
			
			System.out.println("실근 1 : " + answer1);		// 첫번째 실근의 값을 출력
			System.out.println("실근 2 : " + answer2);		// 두번째 실근의 값을 출력
		}
		else if(d == 0) {	// 하나의 실근(중근)을 갖는 경우
			double answer = (-b + Math.sqrt(d)) / (2*a);	// 하나의 실근(중근)의 값을 계산
			System.out.println("실근 : " + answer);			// 하나의 실근(중근)의 값을 출력
		}
		else {	// 실근이 없는 경우
			System.out.println("실근이 없음");					// 실근이 없다고 출력
		}
		
		scan.close();	// scan을 종료함
	}
}