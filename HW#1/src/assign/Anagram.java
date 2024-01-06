package assign;

import java.util.Scanner;

public class Anagram {
	public static void main(String[] args) {
		String a;				// 입력 받은 단어1을 저장하기 위한 변수 a
		String b;				// 입력 받은 단어2를 저장하기 위한 변수 b
		String end = "quit";	// 끝을 확인하기 위한 문자열 end(a 또는 b가 end("quit")와 같다면 종료)
		int check = 0;			// a와 b가 anagram인지 확인하기 위한 변수 check
		Assign1 get_word = new Assign1();		// Assign1클래스인 get_word를 생성(anagram인지 아닌지 확인 및 알파벳 개수 카운팅)
		Assign2 arrange_word = new Assign2();	// Assign2클래스인 arrange_word를 생성(anagram인 쌍과 아닌 쌍들을 분류해서 저장, quit입력시 쌍들을 출력)
		Scanner scan = new Scanner(System.in);	// System.in에 대한 Scanner객체를 만듦(a,b값을 얻기 위해)
		
		while(true) {			// 중간에 a 또는 b가 end("quit")과 같기 전까지 계속해서 작업을 반복함
			System.out.println("두 단어를 입력하세요(스페이스바로 분류): ");	// 두 단어를 입력하라고 출력
			a = scan.next();										// a를 입력 받음
			if(a.equals(end)) {		// a가 "quit"인 경우
				arrange_word.print_CoupleList();		// arrange_word에 저장되어 있는 anagram인 쌍들을 모두 출력하는 함수
				arrange_word.print_NotCoupleList();		// arrange_word에 저장되어 있는 anagram이 아닌 쌍들을 모두 출력하는 함수
				break;				// while문을 빠져나오고 종료하게 함
			}
			b = scan.next();		// b를 입력 받음
			if(b.equals(end)) {		// b가 "quit"인 경우
				arrange_word.print_CoupleList();		// arrange_word에 저장되어 있는 anagram인 쌍들을 모두 출력하는 함수
				arrange_word.print_NotCoupleList();		// arrange_word에 저장되어 있는 anagram이 아닌 쌍들을 모두 출력하는 함수
				break;				// while문을 빠져나오고 종료하게 함
			}
			else {	// a도 b도 quit이 아닌 경우 입력 받은 두 문자를 비교하여 지금까지 입력된 모든 단어들에 사용된 알파벳의 수를 카운트하여 출력하고 anagram인지 또는 anagram이 아닌지 분류하여 해당 쌍에 추가 시킴
				get_word.getString(a, b);			// get_word의 String x,y에 a,b를 저장
				get_word.Split();				// get_word에 저장된 x와 y에 사용한 알파벳의 수를 x_add_number, y_add_number에 저장함(일종의 분리 작업)
				check = get_word.Compare();		// x_add_number과 y_add_number의 비교를 통해 x와 y가 anagram이 아니면 1을 맞으면 0을 반환하며 x_add_number와 y_add_number를 지금까지 입력된 모든 알파벳들의 합인 x_number와 y_number에 더하는 작업을 함 		 
				if(check == 1) 	// anagram이 아닌 경우
					arrange_word.get_Not_Couple_XY(a, b);	// arrange_word의 anagram이 아닌 쌍들(not_couple_x,not_couple_y)에 a와 b값을 저장함
				else 			// anagram이 맞는 경우
					arrange_word.get_Couple_XY(a, b);		// arrange_word의 anagram이 맞는 쌍들(couple_x,couple_y)에 a와 b값을 저장함
			}
		}
		
		scan.close();	// scan을 종료함
	}
	
	static class Assign1 {		// 입력받은 단어 a,b를 x,y로 가지고와 알파벳으로 분류한 뒤 anagram인지 아닌지 구분하고 입력된 두 단어들에 사용된 알파벳의 수를 카운트하는 클래스
		private String x;		// 입력받은 단어 a를 저장하기 위한 변수 x
		private String y;		// 입력받은 단어 b를 저장하기 위한 변수 y
		private int[] x_add_number = new int[26];	// 지금 입력받은 단어(a)에 사용된 알파벳의 수를 저장하기 위한 변수 x_add_number(y_add_number과 비교하여 anagram인지 아닌지 판별하기 위함 ,x_add_number[0]은 알파벳a의 갯수, x_add_number[1]은 알파벳b의 갯수와 같은 방식)
		private int[] y_add_number = new int[26];	// 지금 입력받은 단어(b)에 사용된 알파벳의 수를 저장하기 위한 변수 y_add_number(x_add_number과 비교하여 anagram인지 아닌지 판별하기 위함 ,y_add_number[0]은 알파벳a의 갯수, y_add_number[1]은 알파벳b의 갯수와 같은 방식)
		
		public void getString(String a, String b) {	// 입력 받은 a와 b를 각각 x와 y에 저장함
			x = a;
			y = b;
		}
		public void Split() {	// x와 y를 소문자로 변환 시킨후 지금 입력받은 알파벳의 수(x_add_number, y_add_number)의 값을 초기화하여 정확히 계산함
			int i=0;	// 반복문을 위한 변수 i
			String alphabet = "abcdefghijklmnopqrstuvwxyz";	// 모든 알파벳을 저장한 변수 alphabet(x와 y 각 단어에 사용된 알파벳을 분류할 때 사용됨)
			x = x.toLowerCase();	// x를 이루고 있는 알파벳을 전부 소문자로 바꿈
			y = y.toLowerCase();	// y를 이루고 있는 알파벳을 전부 소문자로 바꿈
			
			reset_add_num();		// 지금 입력받은 알파벳의 수(x_add_number, y_add_number)의 값을 초기화(모두 0으로)함(이전에 사용한 경우 저장된 값이 그대로 남아있기 때문) 
			
			while(i < x.length()) {	// x의 단어 수 만큼 반복함(x에 포함된 모든 알파벳을 분석 가능)
				for(int j=0; j<26; j++) {	// 알파벳의 종류만큼 반복함
					if(x.charAt(i)==alphabet.charAt(j)) {	// x의 i번째(ex:abc의 경우 0번째가 a, 1번째가 b, 2번째가 c)에 있는 알파벳이 위에서 모든 알파벳을 저장한 변수의 j번째 알파벳(0번째 a, 1번째 b, ...)과 같다면 x의 i번째에 있는 알파벳이 j번째 알파벳이므로 
						x_add_number[j]++;	// 해당 알파벳이 나온 횟수를 1 증가
						i++;				// i를 1증가하여 x의 다음 알파벳을 분석함
						break;				// for문을 빠져 나옴(다음 알파벳을 조사하기 위해서)
					}
				}
			}
			i = 0;		// 변수를 다시 0으로 초기화
			
			while(i < y.length()) {	// y의 단어 수 만큼 반복함(y에 포함된 모든 알파벳을 분석 가능)
				for(int j=0; j<26; j++) {	// 알파벳의 종류만큼 반복함
					if(y.charAt(i)==alphabet.charAt(j)) {	// x의 i번째(ex:abc의 경우 0번째가 a, 1번째가 b, 2번째가 c)에 있는 알파벳이 위에서 모든 알파벳을 저장한 변수의 j번째 알파벳(0번째 a, 1번째 b, ...)과 같다면 x의 i번째에 있는 알파벳이 j번째 알파벳이므로
						y_add_number[j]++;	// 해당 알파벳이 나온 횟수를 1 증가
						i++;				// i를 1증가하여 y의 다음 알파벳을 분석함	
						break;				// for문을 빠져 나옴(다음 알파벳을 조사하기 위해서)
					}
				}
			}
		}
		
		public void reset_add_num() {	// 지금 입력받은 알파벳의 수(x_add_number, y_add_number)의 값을 초기화(모두 0으로)하는 함수(이전에 사용한 경우 저장된 값이 그대로 남아있기 때문)
			for(int i=0; i<26; i++) {	// 알파벳의 종류만큼 반복함	
				x_add_number[i] = 0;
				y_add_number[i] = 0;
			}
		}
		
		public int Compare() {	// x_add_number과 y_add_number의 비교를 통해 x와 y가 anagram이 아니면 1을 맞으면 0을 반환하며  입력된 두 단어를 이루고 있는 알파벳의 갯수를 출력함
			int flag = 0;	// x와 y가 anagram인지 아닌지를 구분하기 위해 return하는 변수 flag(1: anagram아님, 0: anagram맞음)
			for(int j=0;j<26;j++) {		// 알파벳의 종류만큼 반복함
				if(x_add_number[j] != y_add_number[j])	// 만약 x와 y의 현재 단어에서 나온 알파벳의 개수가 다르다면 anagram이 아니므로
					flag = 1;			// flag값을 1로 바꿈
				int count = x_add_number[j]+y_add_number[j];	// 현재 입력된 두 단어들을 이루고있는 알파벳 개수들의 값이 count가 됨(j가 0이면 a, 1이면 b, 2이면 c, ...)
				System.out.printf((char)('a'+ j) + " = " + count + ", ");	// a = "a개수", b = "b개수", ... 와 같이 출력이 됨
			}
			
			return flag;	// anagram인지 아닌지를 구분하기 위한 값이 return 됨(1: anagram아님, 0: anagram맞음)
		}
	}
	
	static class Assign2 {		// anagram인 쌍들과 anagram이 아닌 쌍들을 각각 저장하고 해당 쌍들의 목록을 출력하는 클래스
		private String[] couple_x = new String[20];			// anagram이 맞는 쌍들 중 x값들을 저장하기 위한 변수
		private String[] couple_y = new String[20];			// anagram이 맞는 쌍들 중 y값들을 저장하기 위한 변수
		private String[] not_couple_x = new String[20];		// anagram이 아닌 쌍들 중 x값들을 저장하기 위한 변수
		private String[] not_couple_y = new String[20];		// anagram이 아닌 쌍들 중 y값들을 저장하기 위한 변수
		
		
		public void get_Couple_XY(String x, String y) {	// anagram인 x와 y값을 가져와서 배열(couple_x, couple_y)에 넣기 위한 함수
			int i = 0;	// 해당하는 인덱스를 찾기 위한 변수(배열의 빈 자리 찾기 용)
			while (true) {	// couple_x[i]가 null이면 반복문을 빠져나옴 (이러한 i를 찾기 위함)
				if(couple_x[i]==null)	// couple_x[i]가 null이면 반복문을 빠져나옴
					break;
				i++;		// 그때까지 i를 1씩 더해가면서 계속 찾아봄
			}
			couple_x[i]=x;	// 새로 입력 받은 anagram인 단어 x를 배열의 빈자리에 채움
			couple_y[i]=y;	// 새로 입력 받은 anagram인 단어 y를 배열의 빈자리에 채움	
		}
		
		public void get_Not_Couple_XY(String x, String y) {	// anagram이 아닌 x와 y값을 가져와서 배열(not_couple_x, not_couple_y)에 넣기 위한 함수
			int i = 0;	// 해당하는 인덱스를 찾기 위한 변수(배열의 빈 자리 찾기 용)
			while (true) {	// not_couple_x[i]가 null이면 반복문을 빠져나옴 (이러한 i를 찾기 위함)
				if(not_couple_x[i]==null)	// not_couple_x[i]가 null이면 반복문을 빠져나옴
					break;
				i++;		// 그때까지 i를 1씩 더해가면서 계속 찾아봄
			}
			not_couple_x[i]=x;	// 새로 입력 받은 anagram이 아닌 단어 x를 배열의 빈자리에 채움
			not_couple_y[i]=y;	// 새로 입력 받은 anagram이 아닌 단어 y를 배열의 빈자리에 채움
		}
		
		public void print_CoupleList() {	// anagram인 단어 쌍들을 출력하는 함수
			int i=0;	// 해당하는 인덱스를 찾기 위한 변수(배열의 빈 자리 찾기 용)
			System.out.println("anagram인 단어 쌍 목록");	// anagram인 단어 쌍 목록이라고 출력함
			while (true) {	// couple_x[i]가 null이면 반복문을 빠져나옴 (이러한 i를 찾기 위함)
				if(couple_x[i]==null)	// couple_x[i]가 null이면 반복문을 빠져나옴
					break;
				System.out.println(couple_x[i] + " " + couple_y[i]);	// 그때까지 해당 인덱스에 저장된 단어 쌍들을 출력하고
				i++;		// i를 1씩 더해가면서 해당 단어 쌍들을 계속 찾아서 출력함
			}
			if (i == 0)	// 만약 i가 0인 경우 anagram인 단어쌍이 없는 경우이므로
				System.out.println("anagram인 단어 쌍은 없습니다.");	// anagram인 단어 쌍은 없습니다 라고 출력
			else		// anagram인 단어쌍이 1개라도 있는 경우
				System.out.println("anagram인 단어 쌍의 개수 : " + i);	// anagram인 단어 쌍의 개수를 출력함(i와 같음)
		}
		
		public void print_NotCoupleList() {	// anagram이 아닌 단어 쌍들을 출력하는 함수
			int i=0;	// 해당하는 인덱스를 찾기 위한 변수(배열의 빈 자리 찾기 용)
			System.out.println("anagram이 아닌 단어 쌍 목록");		// anagram이 아닌 단어 쌍 목록이라고 출력함
			while (true) {	// not_couple_x[i]가 null이면 반복문을 빠져나옴 (이러한 i를 찾기 위함)
				if(not_couple_x[i]==null)	// not_couple_x[i]가 null이면 반복문을 빠져나옴
					break;
				System.out.println(not_couple_x[i] + " " + not_couple_y[i]);	// 그때까지 해당 인덱스에 저장된 단어 쌍들을 출력하고
				i++;		// i를 1씩 더해가면서 해당 단어 쌍들을 계속 찾아서 출력함
			}
			if (i == 0)	// 만약 i가 0인 경우 anagram이 아닌 단어쌍이 없는 경우이므로
				System.out.println("anagram이 아닌 단어 쌍은 없습니다.");		// anagram이 아닌 단어 쌍은 없습니다 라고 출력
			else		// anagram이 아닌 단어쌍이 1개라도 있는 경우
				System.out.println("anagram이 아닌 단어 쌍의 개수 : " + i);		// anagram이 아닌 단어 쌍의 개수를 출력함(i와 같음)
		}
	}
}
