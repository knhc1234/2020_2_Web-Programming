package chap4;

import java.util.Scanner;

public class ex1 {
	public static void main(String[] args) {
		double x = 0.0d;						// 입력 받을 값을 저장할 변수 x선언
		double result;							// 결과 값을 저장할 변수 answer 선언
		Scanner scan = new Scanner(System.in);	// System.in에 대한 Scanner객체를 만듦(x값을 얻기 위해)
		
		System.out.println("제곱근을 원하는 수 x를 입력하시오: ");	// 제곱근을 원하는 값을 입려하라고 출력
		x = scan.nextDouble();					// 제곱근을 원하는 값 x를 입력 받음
		result = babylonianSqrt(x);				// 바빌로니안제곱근 함수를 실행하여 얻은 결과값을 result에 넣음
		System.out.println("바빌로니안 알고리즘으로 얻은 결과 값: " + result);	// 바빌로니안 알고리즘으로 얻은 결과 값 출력
		
		scan.close();	// scan을 종료함
	}
	
	public static double babylonianSqrt(double x) {	// 바빌로니안 제곱근 알고리즘(t의 제곱근을 구한다면 x(n) = (x(n-1) + t/x(n-1)) / 2 를 반복하여 t의 제곱근의 근삿값을 구할 수 있음)
		double precision =  0.00000001;		// 소숫점 이하 8자리까지 정확히 구하기 위해 설정한 값(구한 값이 이 값보다 작다면 오차가 8자리 이하인 것이므로 해당 결과 값을 return하며 함수의 역할이 끝남) 
		double diff = 0.0d;					// 실제 제곱근 값과 바빌로니안 제곱근으로 구한 값의 차이를 위한 변수(target - result)
		double result = 1;					// 바빌로니안 제곱근으로 구한 값을 저장할 변수(처음에 x0의 값을 1부터 시작함)
		double target = Math.sqrt(x);		// 실제 제곱근 값		
		
		do {	// 최소 1번은 작동, 조건문((실제 제곱근 값 - 바빌로니안 제곱근으로 구한 값)이 원하는 정확도 0.00000001보다 크면 반복 (더 작다면 오차가 8자리 이하인 것이므로 반복문을 빠져나와 해당 값을 return함)
			result = (result + (x/result))/2;	// 바빌로니안 제곱근을 구함(x(n) = (x(n-1) + t/x(n-1)) / 2 , 이때 t는 제곱근을 구하길 원하는 값)
			diff = target - result;				// 실제 제곱근 값 - 바빌로니안 제곱근으로 구한 값으로 둘의 오차를 계산 함 
			
			if(diff < 0) {	// 만약 오차 값이 음수라면
				diff = -diff;	// 양수로 바꿔줌(절댓값 역할)
			}
			
			System.out.println("현재 오차 : " + diff + "   현재 결과 값 : " + result); // 현재 구한 현재 오차값과 현재 결과 값(바빌로니안 제곱근값)을 출력 
			
		} while(diff > precision);	// (실제 제곱근 값 - 바빌로니안 제곱근으로 구한 값)이 소숫점 8자리 이하까지 정확하다면 반복문을 빠져나옴
		
		return result;		// 해당 결과 값을 반환함
	}
}
