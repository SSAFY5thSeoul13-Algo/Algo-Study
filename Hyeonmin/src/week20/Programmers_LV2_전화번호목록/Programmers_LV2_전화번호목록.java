package week20.Programmers_LV2_전화번호목록;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Programmers_LV2_전화번호목록 {

	public static void main(String[] args) {
		String[] phone_book = {"12","123","1235","567","88"};
		boolean answer = solution(phone_book);
		
		System.out.println(answer);
	}

	static boolean solution(String[] phone_book) {
		//접두사를 저장할 해시셋
		Set<String> set = new HashSet<>();
		
		//번호 문자열을 정렬
		Arrays.sort(phone_book);
		
		//배열의 역순으로
		for (int i = phone_book.length-1; i >=0 ; i--) {
			
			//각 배열의 문자열
			String string = phone_book[i];

			//접두사 목록에 있는 경우 false반환
			if(set.contains(string))
				return false;
			
			//해당 번호의 접두사가 될 수 있는 문자열들을 set에 저장
			for (int j = 1; j < string.length(); j++) {
				String str = string.substring(0, j);
				set.add(str);
			}
		}
		
		//접두사가 없는 경우
		return true;
	}
}
