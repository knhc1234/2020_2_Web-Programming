package chap4;

import java.util.Scanner;

public class ex2 {
	public static void main(String[] args) {
		int x = 0;		// 어떤 수를 입력 받기 위한 변수
		int n = 0;		// 어떤 수의 거듭 제곱 횟수를 입력 받기 위한 변수
		int result = 0;	// 어떤 수가 거듭 제곱 한 후의 결과 값을 저장하기 위한 변수
		Scanner scan = new Scanner(System.in);	// System.in에 대한 Scanner객체를 만듦(x,n값을 얻기 위해)
		
		System.out.println("어떤 수(x)를 입력하세요 : ");	// 어떤 수를 입력하라고 출력 
		x = scan.nextInt();							// x값을 입력 받음
		System.out.println("몇번(n) 거듭제곱을 할 지 입력하세요.");	// 몇번 거듭제곱 할 지 입력하라고 출력
		n = scan.nextInt();									// n값을 입력 받음
		
		result = fastPower(x,n);	// fastPower함수를 통해 x의 n회 거듭제곱된 값을 얻어옴(이때 overflow가 발생했다면 정수형의 최댓값(Integer.MAX_VALUE)을 가져옴)
		if (result == Integer.MAX_VALUE) 	// result값이 만약 정수의 최댓값(Integer.MAX_VALUE)인 경우 오버플로우가 발생한 것임 
			System.out.println("Overflow가 발생하였습니다. 계산 할 수 있는 범위를 초과하였습니다.");		// 따라서 오버플로우가 발생하였다고 출력
		else	// 오버플로우가 발생하지 않은 경우
			System.out.println(x + "를 " + n + "번 거듭제곱 한 값 : " + result);	// x를 n번 거듭제곱한 결과 값을 출력 함
		
		scan.close();	// scan을 종료함
	}
	
	public static int fastPower(int x, int n) {
		int a = x;	// x값을 저장해 놓음(x값에 계속해서 이 저장해 놓은 x값(a)을 곱하여 거듭제곱을 해나가기 위해서)
	
		if(n%2 == 0) {	// 만약 n이 짝수인 경우 문제와 같이 n/2회 거듭제곱한 후 제곱을 해줌
			for(int i=1;i<n/2; i++) {	// x값을 사용하므로 i=1부터 시작하며 ((n/2)-1회 반복)
				if(x > Integer.MAX_VALUE / a) 	// 만약 overflow가 발생한다면(x*a가 정수로 다룰수 있는 최댓값(Integer.MAX_VALUE)보다 큰 경우)
					return Integer.MAX_VALUE;	// 제대로 된 값을 얻을 수 없으므로 overflow가 발생했다는 것을 표시하기 위해 정수로 다룰수 있는 최댓값(Integer.MAX_VALUE)을 반환함				
				x = x*a;		// overflow가 발생하여 return되지 않은 경우 별 문제 없이 계속해서 거듭제곱 해 나감(else를 안붙여도 if문에 걸리면 함수가 끝나기 때문에 굳이 붙일 필요는 없음)
			}
			if(x > Integer.MAX_VALUE / x) // n/2회 만큼의 거듭제곱이 끝나고 제곱을 할때 // 만약 overflow가 발생한다면(x*x가 정수로 다룰수 있는 최댓값(Integer.MAX_VALUE)보다 큰 경우)
				return Integer.MAX_VALUE; // 제대로 된 값을 얻을 수 없으므로 overflow가 발생했다는 것을 표시하기 위해 정수로 다룰수 있는 최댓값(Integer.MAX_VALUE)을 반환함
			x = x*x;	// overflow가 발생하여 return되지 않은 경우 별 문제 없이 마지막으로 제곱을 함(else를 안붙여도 if문에 걸리면 함수가 끝나기 때문에 굳이 붙일 필요는 없음)
		}
		else {			// 만약 n이 홀수인 경우 n회 거듭제곱을 함
			for(int i=1;i<n; i++) {		// x값을 사용하므로 i=1부터 시작하며 (n-1회 반복)
				if(x > Integer.MAX_VALUE / a) 	// 만약 overflow가 발생한다면(x*a가 정수로 다룰수 있는 최댓값(Integer.MAX_VALUE)보다 큰 경우)
					return Integer.MAX_VALUE;	// 제대로 된 값을 얻을 수 없으므로 overflow가 발생했다는 것을 표시하기 위해 정수로 다룰수 있는 최댓값(Integer.MAX_VALUE)을 반환함
				x = x*a;		// overflow가 발생하여 return되지 않은 경우 별 문제 없이 계속해서 거듭제곱 해 나감(else를 안붙여도 if문에 걸리면 함수가 끝나기 때문에 굳이 붙일 필요는 없음)
			}
		}
		return x;	// 끝까지 overflow가 발생하지 않고 정상적으로 작동한 경우 원래의 x를 n회만큼 제곱한 값(결과 값 x)를 return함
	}
	
}
