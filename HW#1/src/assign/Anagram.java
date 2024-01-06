package assign;

import java.util.Scanner;

public class Anagram {
	public static void main(String[] args) {
		String a;				// �Է� ���� �ܾ�1�� �����ϱ� ���� ���� a
		String b;				// �Է� ���� �ܾ�2�� �����ϱ� ���� ���� b
		String end = "quit";	// ���� Ȯ���ϱ� ���� ���ڿ� end(a �Ǵ� b�� end("quit")�� ���ٸ� ����)
		int check = 0;			// a�� b�� anagram���� Ȯ���ϱ� ���� ���� check
		Assign1 get_word = new Assign1();		// Assign1Ŭ������ get_word�� ����(anagram���� �ƴ��� Ȯ�� �� ���ĺ� ���� ī����)
		Assign2 arrange_word = new Assign2();	// Assign2Ŭ������ arrange_word�� ����(anagram�� �ְ� �ƴ� �ֵ��� �з��ؼ� ����, quit�Է½� �ֵ��� ���)
		Scanner scan = new Scanner(System.in);	// System.in�� ���� Scanner��ü�� ����(a,b���� ��� ����)
		
		while(true) {			// �߰��� a �Ǵ� b�� end("quit")�� ���� ������ ����ؼ� �۾��� �ݺ���
			System.out.println("�� �ܾ �Է��ϼ���(�����̽��ٷ� �з�): ");	// �� �ܾ �Է��϶�� ���
			a = scan.next();										// a�� �Է� ����
			if(a.equals(end)) {		// a�� "quit"�� ���
				arrange_word.print_CoupleList();		// arrange_word�� ����Ǿ� �ִ� anagram�� �ֵ��� ��� ����ϴ� �Լ�
				arrange_word.print_NotCoupleList();		// arrange_word�� ����Ǿ� �ִ� anagram�� �ƴ� �ֵ��� ��� ����ϴ� �Լ�
				break;				// while���� ���������� �����ϰ� ��
			}
			b = scan.next();		// b�� �Է� ����
			if(b.equals(end)) {		// b�� "quit"�� ���
				arrange_word.print_CoupleList();		// arrange_word�� ����Ǿ� �ִ� anagram�� �ֵ��� ��� ����ϴ� �Լ�
				arrange_word.print_NotCoupleList();		// arrange_word�� ����Ǿ� �ִ� anagram�� �ƴ� �ֵ��� ��� ����ϴ� �Լ�
				break;				// while���� ���������� �����ϰ� ��
			}
			else {	// a�� b�� quit�� �ƴ� ��� �Է� ���� �� ���ڸ� ���Ͽ� ���ݱ��� �Էµ� ��� �ܾ�鿡 ���� ���ĺ��� ���� ī��Ʈ�Ͽ� ����ϰ� anagram���� �Ǵ� anagram�� �ƴ��� �з��Ͽ� �ش� �ֿ� �߰� ��Ŵ
				get_word.getString(a, b);			// get_word�� String x,y�� a,b�� ����
				get_word.Split();				// get_word�� ����� x�� y�� ����� ���ĺ��� ���� x_add_number, y_add_number�� ������(������ �и� �۾�)
				check = get_word.Compare();		// x_add_number�� y_add_number�� �񱳸� ���� x�� y�� anagram�� �ƴϸ� 1�� ������ 0�� ��ȯ�ϸ� x_add_number�� y_add_number�� ���ݱ��� �Էµ� ��� ���ĺ����� ���� x_number�� y_number�� ���ϴ� �۾��� �� 		 
				if(check == 1) 	// anagram�� �ƴ� ���
					arrange_word.get_Not_Couple_XY(a, b);	// arrange_word�� anagram�� �ƴ� �ֵ�(not_couple_x,not_couple_y)�� a�� b���� ������
				else 			// anagram�� �´� ���
					arrange_word.get_Couple_XY(a, b);		// arrange_word�� anagram�� �´� �ֵ�(couple_x,couple_y)�� a�� b���� ������
			}
		}
		
		scan.close();	// scan�� ������
	}
	
	static class Assign1 {		// �Է¹��� �ܾ� a,b�� x,y�� ������� ���ĺ����� �з��� �� anagram���� �ƴ��� �����ϰ� �Էµ� �� �ܾ�鿡 ���� ���ĺ��� ���� ī��Ʈ�ϴ� Ŭ����
		private String x;		// �Է¹��� �ܾ� a�� �����ϱ� ���� ���� x
		private String y;		// �Է¹��� �ܾ� b�� �����ϱ� ���� ���� y
		private int[] x_add_number = new int[26];	// ���� �Է¹��� �ܾ�(a)�� ���� ���ĺ��� ���� �����ϱ� ���� ���� x_add_number(y_add_number�� ���Ͽ� anagram���� �ƴ��� �Ǻ��ϱ� ���� ,x_add_number[0]�� ���ĺ�a�� ����, x_add_number[1]�� ���ĺ�b�� ������ ���� ���)
		private int[] y_add_number = new int[26];	// ���� �Է¹��� �ܾ�(b)�� ���� ���ĺ��� ���� �����ϱ� ���� ���� y_add_number(x_add_number�� ���Ͽ� anagram���� �ƴ��� �Ǻ��ϱ� ���� ,y_add_number[0]�� ���ĺ�a�� ����, y_add_number[1]�� ���ĺ�b�� ������ ���� ���)
		
		public void getString(String a, String b) {	// �Է� ���� a�� b�� ���� x�� y�� ������
			x = a;
			y = b;
		}
		public void Split() {	// x�� y�� �ҹ��ڷ� ��ȯ ��Ų�� ���� �Է¹��� ���ĺ��� ��(x_add_number, y_add_number)�� ���� �ʱ�ȭ�Ͽ� ��Ȯ�� �����
			int i=0;	// �ݺ����� ���� ���� i
			String alphabet = "abcdefghijklmnopqrstuvwxyz";	// ��� ���ĺ��� ������ ���� alphabet(x�� y �� �ܾ ���� ���ĺ��� �з��� �� ����)
			x = x.toLowerCase();	// x�� �̷�� �ִ� ���ĺ��� ���� �ҹ��ڷ� �ٲ�
			y = y.toLowerCase();	// y�� �̷�� �ִ� ���ĺ��� ���� �ҹ��ڷ� �ٲ�
			
			reset_add_num();		// ���� �Է¹��� ���ĺ��� ��(x_add_number, y_add_number)�� ���� �ʱ�ȭ(��� 0����)��(������ ����� ��� ����� ���� �״�� �����ֱ� ����) 
			
			while(i < x.length()) {	// x�� �ܾ� �� ��ŭ �ݺ���(x�� ���Ե� ��� ���ĺ��� �м� ����)
				for(int j=0; j<26; j++) {	// ���ĺ��� ������ŭ �ݺ���
					if(x.charAt(i)==alphabet.charAt(j)) {	// x�� i��°(ex:abc�� ��� 0��°�� a, 1��°�� b, 2��°�� c)�� �ִ� ���ĺ��� ������ ��� ���ĺ��� ������ ������ j��° ���ĺ�(0��° a, 1��° b, ...)�� ���ٸ� x�� i��°�� �ִ� ���ĺ��� j��° ���ĺ��̹Ƿ� 
						x_add_number[j]++;	// �ش� ���ĺ��� ���� Ƚ���� 1 ����
						i++;				// i�� 1�����Ͽ� x�� ���� ���ĺ��� �м���
						break;				// for���� ���� ����(���� ���ĺ��� �����ϱ� ���ؼ�)
					}
				}
			}
			i = 0;		// ������ �ٽ� 0���� �ʱ�ȭ
			
			while(i < y.length()) {	// y�� �ܾ� �� ��ŭ �ݺ���(y�� ���Ե� ��� ���ĺ��� �м� ����)
				for(int j=0; j<26; j++) {	// ���ĺ��� ������ŭ �ݺ���
					if(y.charAt(i)==alphabet.charAt(j)) {	// x�� i��°(ex:abc�� ��� 0��°�� a, 1��°�� b, 2��°�� c)�� �ִ� ���ĺ��� ������ ��� ���ĺ��� ������ ������ j��° ���ĺ�(0��° a, 1��° b, ...)�� ���ٸ� x�� i��°�� �ִ� ���ĺ��� j��° ���ĺ��̹Ƿ�
						y_add_number[j]++;	// �ش� ���ĺ��� ���� Ƚ���� 1 ����
						i++;				// i�� 1�����Ͽ� y�� ���� ���ĺ��� �м���	
						break;				// for���� ���� ����(���� ���ĺ��� �����ϱ� ���ؼ�)
					}
				}
			}
		}
		
		public void reset_add_num() {	// ���� �Է¹��� ���ĺ��� ��(x_add_number, y_add_number)�� ���� �ʱ�ȭ(��� 0����)�ϴ� �Լ�(������ ����� ��� ����� ���� �״�� �����ֱ� ����)
			for(int i=0; i<26; i++) {	// ���ĺ��� ������ŭ �ݺ���	
				x_add_number[i] = 0;
				y_add_number[i] = 0;
			}
		}
		
		public int Compare() {	// x_add_number�� y_add_number�� �񱳸� ���� x�� y�� anagram�� �ƴϸ� 1�� ������ 0�� ��ȯ�ϸ�  �Էµ� �� �ܾ �̷�� �ִ� ���ĺ��� ������ �����
			int flag = 0;	// x�� y�� anagram���� �ƴ����� �����ϱ� ���� return�ϴ� ���� flag(1: anagram�ƴ�, 0: anagram����)
			for(int j=0;j<26;j++) {		// ���ĺ��� ������ŭ �ݺ���
				if(x_add_number[j] != y_add_number[j])	// ���� x�� y�� ���� �ܾ�� ���� ���ĺ��� ������ �ٸ��ٸ� anagram�� �ƴϹǷ�
					flag = 1;			// flag���� 1�� �ٲ�
				int count = x_add_number[j]+y_add_number[j];	// ���� �Էµ� �� �ܾ���� �̷���ִ� ���ĺ� �������� ���� count�� ��(j�� 0�̸� a, 1�̸� b, 2�̸� c, ...)
				System.out.printf((char)('a'+ j) + " = " + count + ", ");	// a = "a����", b = "b����", ... �� ���� ����� ��
			}
			
			return flag;	// anagram���� �ƴ����� �����ϱ� ���� ���� return ��(1: anagram�ƴ�, 0: anagram����)
		}
	}
	
	static class Assign2 {		// anagram�� �ֵ�� anagram�� �ƴ� �ֵ��� ���� �����ϰ� �ش� �ֵ��� ����� ����ϴ� Ŭ����
		private String[] couple_x = new String[20];			// anagram�� �´� �ֵ� �� x������ �����ϱ� ���� ����
		private String[] couple_y = new String[20];			// anagram�� �´� �ֵ� �� y������ �����ϱ� ���� ����
		private String[] not_couple_x = new String[20];		// anagram�� �ƴ� �ֵ� �� x������ �����ϱ� ���� ����
		private String[] not_couple_y = new String[20];		// anagram�� �ƴ� �ֵ� �� y������ �����ϱ� ���� ����
		
		
		public void get_Couple_XY(String x, String y) {	// anagram�� x�� y���� �����ͼ� �迭(couple_x, couple_y)�� �ֱ� ���� �Լ�
			int i = 0;	// �ش��ϴ� �ε����� ã�� ���� ����(�迭�� �� �ڸ� ã�� ��)
			while (true) {	// couple_x[i]�� null�̸� �ݺ����� �������� (�̷��� i�� ã�� ����)
				if(couple_x[i]==null)	// couple_x[i]�� null�̸� �ݺ����� ��������
					break;
				i++;		// �׶����� i�� 1�� ���ذ��鼭 ��� ã�ƺ�
			}
			couple_x[i]=x;	// ���� �Է� ���� anagram�� �ܾ� x�� �迭�� ���ڸ��� ä��
			couple_y[i]=y;	// ���� �Է� ���� anagram�� �ܾ� y�� �迭�� ���ڸ��� ä��	
		}
		
		public void get_Not_Couple_XY(String x, String y) {	// anagram�� �ƴ� x�� y���� �����ͼ� �迭(not_couple_x, not_couple_y)�� �ֱ� ���� �Լ�
			int i = 0;	// �ش��ϴ� �ε����� ã�� ���� ����(�迭�� �� �ڸ� ã�� ��)
			while (true) {	// not_couple_x[i]�� null�̸� �ݺ����� �������� (�̷��� i�� ã�� ����)
				if(not_couple_x[i]==null)	// not_couple_x[i]�� null�̸� �ݺ����� ��������
					break;
				i++;		// �׶����� i�� 1�� ���ذ��鼭 ��� ã�ƺ�
			}
			not_couple_x[i]=x;	// ���� �Է� ���� anagram�� �ƴ� �ܾ� x�� �迭�� ���ڸ��� ä��
			not_couple_y[i]=y;	// ���� �Է� ���� anagram�� �ƴ� �ܾ� y�� �迭�� ���ڸ��� ä��
		}
		
		public void print_CoupleList() {	// anagram�� �ܾ� �ֵ��� ����ϴ� �Լ�
			int i=0;	// �ش��ϴ� �ε����� ã�� ���� ����(�迭�� �� �ڸ� ã�� ��)
			System.out.println("anagram�� �ܾ� �� ���");	// anagram�� �ܾ� �� ����̶�� �����
			while (true) {	// couple_x[i]�� null�̸� �ݺ����� �������� (�̷��� i�� ã�� ����)
				if(couple_x[i]==null)	// couple_x[i]�� null�̸� �ݺ����� ��������
					break;
				System.out.println(couple_x[i] + " " + couple_y[i]);	// �׶����� �ش� �ε����� ����� �ܾ� �ֵ��� ����ϰ�
				i++;		// i�� 1�� ���ذ��鼭 �ش� �ܾ� �ֵ��� ��� ã�Ƽ� �����
			}
			if (i == 0)	// ���� i�� 0�� ��� anagram�� �ܾ���� ���� ����̹Ƿ�
				System.out.println("anagram�� �ܾ� ���� �����ϴ�.");	// anagram�� �ܾ� ���� �����ϴ� ��� ���
			else		// anagram�� �ܾ���� 1���� �ִ� ���
				System.out.println("anagram�� �ܾ� ���� ���� : " + i);	// anagram�� �ܾ� ���� ������ �����(i�� ����)
		}
		
		public void print_NotCoupleList() {	// anagram�� �ƴ� �ܾ� �ֵ��� ����ϴ� �Լ�
			int i=0;	// �ش��ϴ� �ε����� ã�� ���� ����(�迭�� �� �ڸ� ã�� ��)
			System.out.println("anagram�� �ƴ� �ܾ� �� ���");		// anagram�� �ƴ� �ܾ� �� ����̶�� �����
			while (true) {	// not_couple_x[i]�� null�̸� �ݺ����� �������� (�̷��� i�� ã�� ����)
				if(not_couple_x[i]==null)	// not_couple_x[i]�� null�̸� �ݺ����� ��������
					break;
				System.out.println(not_couple_x[i] + " " + not_couple_y[i]);	// �׶����� �ش� �ε����� ����� �ܾ� �ֵ��� ����ϰ�
				i++;		// i�� 1�� ���ذ��鼭 �ش� �ܾ� �ֵ��� ��� ã�Ƽ� �����
			}
			if (i == 0)	// ���� i�� 0�� ��� anagram�� �ƴ� �ܾ���� ���� ����̹Ƿ�
				System.out.println("anagram�� �ƴ� �ܾ� ���� �����ϴ�.");		// anagram�� �ƴ� �ܾ� ���� �����ϴ� ��� ���
			else		// anagram�� �ƴ� �ܾ���� 1���� �ִ� ���
				System.out.println("anagram�� �ƴ� �ܾ� ���� ���� : " + i);		// anagram�� �ƴ� �ܾ� ���� ������ �����(i�� ����)
		}
	}
}
