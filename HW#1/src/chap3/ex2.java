package chap3;

import java.util.Scanner;

public class ex2 {

	public static void main(String[] args) {
		double kg = 0.0d;	// 몸무게를 입력 받을 몸무게 선언
		double m = 0.0d;	// 키를 입력 받을 키 선언
		
		Scanner scan = new Scanner(System.in);
		// System.in에 대한 Scanner객체를 만듦(kg, m값을 얻기 위함)
		System.out.println("몸무게(kg) : ");	// 몸무게(kg)의 값을 입력하라고 출력
		kg = scan.nextDouble();				// 몸무게(kg)의 값을 입력 받음
		
		System.out.println("키(m) : ");		// 키(m)의 값을 입력하라고 출력
		m = scan.nextDouble();				// 키(m)의 값을 입력 받음
		
		double BMI = kg / (m*m);			// BMI를 계산(몸무게 / 키의 제곱)
		System.out.println("BMI : " + BMI);	// BMI의 값 출력
		
		if(BMI < 15.0) {							// BMI 15.0미만인 경우
			System.out.println("병적인 저체중입니다.");	// 판정 출력(병적인 저체중)
		}
		else if(BMI >= 15.0 && BMI < 18.5) {	// BMI가 15.0이상 18.5미만인 경우
			System.out.println("저체중입니다.");	// 판정 출력(저체중)
		}
		else if(BMI >= 18.5 && BMI < 23.0) {	// BMI가 18.5이상 23.0미만인 경우
			System.out.println("정상입니다.");		// 판정 출력(정상)
		}
		else if(BMI >= 23.0 && BMI < 27.5) {	// BMI가 23.0이상 27.5미만인 경우
			System.out.println("과체중입니다.");	// 판정 출력(과체중)
		}
		else if(BMI >= 27.5 && BMI < 40.0) {	// BMI가 27.5이상 40.0미만인 경우
			System.out.println("비만입니다.");		// 판정 출력(비만)
		}
		else {										// BMI가 40이상인 경우
			System.out.println("병적인 비만입니다.");	// 판정 출력(병적인 비만)
		}
		scan.close();	// scan을 종료함
	}

}

