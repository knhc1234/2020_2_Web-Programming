package chap3;

import java.util.Scanner;

public class ex1 {
	
	public static void main(String[] args) {
		double a = 0.0d;	
		double b = 0.0d;
		double c = 0.0d;
		// �Է� ���� double Ÿ�� ��� a,b,c�� ����
		Scanner scan = new Scanner(System.in);
		// System.in�� ���� Scanner��ü�� ����(a,b,c���� ��� ����)
		System.out.println("��� a : ");	// ��� a�� ���� �Է��϶�� ���
		a = scan.nextDouble();			// ��� a�� ���� �Է� ����
		
		while (a == 0) {	// a�� 0�ΰ�� �ٸ� ���� �ٽ� �Է� ����(���� ����: a!=0), �ٽ� �Է� ���� ���� 0�� �ƴ� ������ �ݺ�
 			System.out.println("a�� ���� 0�� �� �� �����ϴ�. �ٸ� ���� �Է��� �ּ���.");	// a�� ���� 0�� �Ǹ� �ȵ�(���� ����)
			System.out.println("��� a : ");	// ��� a�� ���� �Է��϶�� ���
			a = scan.nextDouble();			// ��� a�� ���� �Է� ����
		}
		
		System.out.println("��� b : ");		// ��� b�� ���� �Է��϶�� ���
		b = scan.nextDouble();				// ��� b�� ���� �Է� ����
		
		System.out.println("��� c : ");		// ��� c�� ���� �Է��϶�� ���
		c = scan.nextDouble();				// ��� c�� ���� �Է� ����
		
		double d = b*b - 4*a*c;		// b^2 - 4ac�� ���� ���(��Ʈó���� ���� ���ֱ� ���ؼ� + d>0�̸� �� �Ǳ��� ������, d=0�̸� �ϳ��� �߱��� ������ d<0�̸� �Ǳ��� ����)
		
		if(d > 0) {	// �� �Ǳ��� �����ϴ� ���
			double answer1 = (-b - Math.sqrt(d)) / (2*a);	// ù��° �Ǳ��� ���� ���
			double answer2 = (-b + Math.sqrt(d)) / (2*a);	// �ι�° �Ǳ��� ���� ���
			
			
			System.out.println("�Ǳ� 1 : " + answer1);		// ù��° �Ǳ��� ���� ���
			System.out.println("�Ǳ� 2 : " + answer2);		// �ι�° �Ǳ��� ���� ���
		}
		else if(d == 0) {	// �ϳ��� �Ǳ�(�߱�)�� ���� ���
			double answer = (-b + Math.sqrt(d)) / (2*a);	// �ϳ��� �Ǳ�(�߱�)�� ���� ���
			System.out.println("�Ǳ� : " + answer);			// �ϳ��� �Ǳ�(�߱�)�� ���� ���
		}
		else {	// �Ǳ��� ���� ���
			System.out.println("�Ǳ��� ����");					// �Ǳ��� ���ٰ� ���
		}
		
		scan.close();	// scan�� ������
	}
}