package chap3;

import java.util.Scanner;

public class ex2 {

	public static void main(String[] args) {
		double kg = 0.0d;	// �����Ը� �Է� ���� ������ ����
		double m = 0.0d;	// Ű�� �Է� ���� Ű ����
		
		Scanner scan = new Scanner(System.in);
		// System.in�� ���� Scanner��ü�� ����(kg, m���� ��� ����)
		System.out.println("������(kg) : ");	// ������(kg)�� ���� �Է��϶�� ���
		kg = scan.nextDouble();				// ������(kg)�� ���� �Է� ����
		
		System.out.println("Ű(m) : ");		// Ű(m)�� ���� �Է��϶�� ���
		m = scan.nextDouble();				// Ű(m)�� ���� �Է� ����
		
		double BMI = kg / (m*m);			// BMI�� ���(������ / Ű�� ����)
		System.out.println("BMI : " + BMI);	// BMI�� �� ���
		
		if(BMI < 15.0) {							// BMI 15.0�̸��� ���
			System.out.println("������ ��ü���Դϴ�.");	// ���� ���(������ ��ü��)
		}
		else if(BMI >= 15.0 && BMI < 18.5) {	// BMI�� 15.0�̻� 18.5�̸��� ���
			System.out.println("��ü���Դϴ�.");	// ���� ���(��ü��)
		}
		else if(BMI >= 18.5 && BMI < 23.0) {	// BMI�� 18.5�̻� 23.0�̸��� ���
			System.out.println("�����Դϴ�.");		// ���� ���(����)
		}
		else if(BMI >= 23.0 && BMI < 27.5) {	// BMI�� 23.0�̻� 27.5�̸��� ���
			System.out.println("��ü���Դϴ�.");	// ���� ���(��ü��)
		}
		else if(BMI >= 27.5 && BMI < 40.0) {	// BMI�� 27.5�̻� 40.0�̸��� ���
			System.out.println("���Դϴ�.");		// ���� ���(��)
		}
		else {										// BMI�� 40�̻��� ���
			System.out.println("������ ���Դϴ�.");	// ���� ���(������ ��)
		}
		scan.close();	// scan�� ������
	}

}

