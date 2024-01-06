package chap4;

import java.util.Scanner;

public class ex2 {
	public static void main(String[] args) {
		int x = 0;		// � ���� �Է� �ޱ� ���� ����
		int n = 0;		// � ���� �ŵ� ���� Ƚ���� �Է� �ޱ� ���� ����
		int result = 0;	// � ���� �ŵ� ���� �� ���� ��� ���� �����ϱ� ���� ����
		Scanner scan = new Scanner(System.in);	// System.in�� ���� Scanner��ü�� ����(x,n���� ��� ����)
		
		System.out.println("� ��(x)�� �Է��ϼ��� : ");	// � ���� �Է��϶�� ��� 
		x = scan.nextInt();							// x���� �Է� ����
		System.out.println("���(n) �ŵ������� �� �� �Է��ϼ���.");	// ��� �ŵ����� �� �� �Է��϶�� ���
		n = scan.nextInt();									// n���� �Է� ����
		
		result = fastPower(x,n);	// fastPower�Լ��� ���� x�� nȸ �ŵ������� ���� ����(�̶� overflow�� �߻��ߴٸ� �������� �ִ�(Integer.MAX_VALUE)�� ������)
		if (result == Integer.MAX_VALUE) 	// result���� ���� ������ �ִ�(Integer.MAX_VALUE)�� ��� �����÷ο찡 �߻��� ���� 
			System.out.println("Overflow�� �߻��Ͽ����ϴ�. ��� �� �� �ִ� ������ �ʰ��Ͽ����ϴ�.");		// ���� �����÷ο찡 �߻��Ͽ��ٰ� ���
		else	// �����÷ο찡 �߻����� ���� ���
			System.out.println(x + "�� " + n + "�� �ŵ����� �� �� : " + result);	// x�� n�� �ŵ������� ��� ���� ��� ��
		
		scan.close();	// scan�� ������
	}
	
	public static int fastPower(int x, int n) {
		int a = x;	// x���� ������ ����(x���� ����ؼ� �� ������ ���� x��(a)�� ���Ͽ� �ŵ������� �س����� ���ؼ�)
	
		if(n%2 == 0) {	// ���� n�� ¦���� ��� ������ ���� n/2ȸ �ŵ������� �� ������ ����
			for(int i=1;i<n/2; i++) {	// x���� ����ϹǷ� i=1���� �����ϸ� ((n/2)-1ȸ �ݺ�)
				if(x > Integer.MAX_VALUE / a) 	// ���� overflow�� �߻��Ѵٸ�(x*a�� ������ �ٷ�� �ִ� �ִ�(Integer.MAX_VALUE)���� ū ���)
					return Integer.MAX_VALUE;	// ����� �� ���� ���� �� �����Ƿ� overflow�� �߻��ߴٴ� ���� ǥ���ϱ� ���� ������ �ٷ�� �ִ� �ִ�(Integer.MAX_VALUE)�� ��ȯ��				
				x = x*a;		// overflow�� �߻��Ͽ� return���� ���� ��� �� ���� ���� ����ؼ� �ŵ����� �� ����(else�� �Ⱥٿ��� if���� �ɸ��� �Լ��� ������ ������ ���� ���� �ʿ�� ����)
			}
			if(x > Integer.MAX_VALUE / x) // n/2ȸ ��ŭ�� �ŵ������� ������ ������ �Ҷ� // ���� overflow�� �߻��Ѵٸ�(x*x�� ������ �ٷ�� �ִ� �ִ�(Integer.MAX_VALUE)���� ū ���)
				return Integer.MAX_VALUE; // ����� �� ���� ���� �� �����Ƿ� overflow�� �߻��ߴٴ� ���� ǥ���ϱ� ���� ������ �ٷ�� �ִ� �ִ�(Integer.MAX_VALUE)�� ��ȯ��
			x = x*x;	// overflow�� �߻��Ͽ� return���� ���� ��� �� ���� ���� ���������� ������ ��(else�� �Ⱥٿ��� if���� �ɸ��� �Լ��� ������ ������ ���� ���� �ʿ�� ����)
		}
		else {			// ���� n�� Ȧ���� ��� nȸ �ŵ������� ��
			for(int i=1;i<n; i++) {		// x���� ����ϹǷ� i=1���� �����ϸ� (n-1ȸ �ݺ�)
				if(x > Integer.MAX_VALUE / a) 	// ���� overflow�� �߻��Ѵٸ�(x*a�� ������ �ٷ�� �ִ� �ִ�(Integer.MAX_VALUE)���� ū ���)
					return Integer.MAX_VALUE;	// ����� �� ���� ���� �� �����Ƿ� overflow�� �߻��ߴٴ� ���� ǥ���ϱ� ���� ������ �ٷ�� �ִ� �ִ�(Integer.MAX_VALUE)�� ��ȯ��
				x = x*a;		// overflow�� �߻��Ͽ� return���� ���� ��� �� ���� ���� ����ؼ� �ŵ����� �� ����(else�� �Ⱥٿ��� if���� �ɸ��� �Լ��� ������ ������ ���� ���� �ʿ�� ����)
			}
		}
		return x;	// ������ overflow�� �߻����� �ʰ� ���������� �۵��� ��� ������ x�� nȸ��ŭ ������ ��(��� �� x)�� return��
	}
	
}
