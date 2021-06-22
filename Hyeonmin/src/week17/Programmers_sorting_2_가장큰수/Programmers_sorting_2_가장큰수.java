package week17.Programmers_sorting_2_가장큰수;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Programmers_sorting_2_가장큰수 {
	static int[] numbers = {3, 30, 34, 5, 9};
	
	public static void main(String[] args) {
		String result = solution();
		
		System.out.println(result);
	}
	
	static String solution() {
		List<String> list = new ArrayList<String>();
		
		//숫자들을 문자열로 바꿔서 저장
		for (int num : numbers) {
			list.add(Integer.toString(num));
		}
		
		//정렬
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				//맨 앞 숫자가 다르면 바로 비교
				if(o1.charAt(0) != o2.charAt(0))
					return o2.compareTo(o1);
				
				//맨 앞 숫자가 같은 경우
				//o1이 앞에 오는 숫자
				int num1 = Integer.parseInt(o1+o2);
				//o2가 앞에 오는 숫자
				int num2 = Integer.parseInt(o2+o1);
				
				//새로 만든 두 수중에 더 큰것이 앞쪽으로 정렬
				return num2 - num1;
			}
			
		});
		
		
		StringBuilder sb = new StringBuilder();
		
		//정렬된 순서대로 숫자를 이어 붙임
		for (String str : list) {
			sb.append(str);
		}
		
		//맨 첫 숫자가 0이면 0이 여러개 일 수 있으니 "0"리턴
		if(sb.toString().charAt(0) == '0')
			return "0";
		
		//완성된 숫자 리턴
		return sb.toString();
	}
}
