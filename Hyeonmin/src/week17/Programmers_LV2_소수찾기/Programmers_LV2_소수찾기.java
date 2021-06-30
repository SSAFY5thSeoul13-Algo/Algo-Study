package week17.Programmers_LV2_소수찾기;

public class Programmers_LV2_소수찾기 {
	static boolean[] selected;
	static int[] number;
	static int[] arr;
	static int length;
	static String numberString="011";
			
	public static void main(String[] args) {
		solution();
	}
	
	static int solution() {
		length = numberString.length();
		
		//순열에서 선택 여부를 저장하는 배열
		selected = new boolean[length];
		//순열로 선택한 위치 인덱스를 순서대로 저장할 배열
		arr = new int[length];
		//만들어진 숫자를 표시할 배열
		number = new int[10000000];
		
		//순열
		permutation(0,0);
		
		//소수가 아는 경우 해당 위치의 값을 1 줄임
		for (int i = 2; i*i < 10000000; i++) {
			for (int j = i*i; j < 10000000; j+=i) {
				number[j]--;
			}
		}
		
		int result = 0;
		
		//남은 값중에 숫자가 1이상인 경우는 소수가 아니고 순열을 통해서 만들어진 숫자 뿐
		for (int i = 2; i < 10000000; i++) {
			if(number[i] >= 1)
				result++;
		}
		
		System.out.println(result);
		
		return 0;
	}
	
	static void permutation(int idx, int cnt) {
		//하나라도 숫자를 고른 경우
		if(cnt != 0) {
			String str = "";
			
			//str에 고른 순서대로 숫자를 이어 붙임
			for (int i = 0; i < cnt; i++) {
				int num = arr[i];
				
				str+=numberString.charAt(num);
			}
			
			//만든 숫자에 해당하는 배열의 인덱스 값을 1로 만들음
			number[Integer.parseInt(str)] = 1;
		}
		
		//문자열의 마지막까지갔으면 종료
		if(idx == length) {
			return;
		}
		
		//다음 숫자를 고름
		for (int i = 0; i < length; i++) {
			//이미 고른 경우는 스킵
			if(selected[i])	continue;
			
			//숫자 선택
			selected[i] = true;
			//선택한 위치의 인덱스를 저장
			arr[cnt] = i;
			//다음 선택으로
			permutation(idx+1, cnt+1);
			//선택 초기화
			selected[i] = false;
		}
	}
}
