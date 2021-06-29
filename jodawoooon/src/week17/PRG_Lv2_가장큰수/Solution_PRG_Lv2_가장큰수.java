package week17.PRG_Lv2_가장큰수;

import java.util.*;

public class Solution_PRG_Lv2_가장큰수 {
	
	public static void main(String[] args) {
		int[] numbers = new int[] {3, 30, 34, 5, 9};
		System.out.println(solution(numbers));
	}
	public static String solution(int[] numbers) {
        String answer = "";
        
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i : numbers) {
			numList.add(i);
		}
        
        Collections.sort(numList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// 정수를 이어 붙여 만들 수 있는 가장 큰 수
				String a = o1+""+o2;
				String b = o2+""+o1;
				return b.compareTo(a);
			}
        	
        });
        
        //정답은 문자열로 출력
        for (int i : numList) {
			answer += i;
		}
        
        if(answer.charAt(0)=='0') answer="0"; //원소가 모두 0일때
        return answer;
    }
}
