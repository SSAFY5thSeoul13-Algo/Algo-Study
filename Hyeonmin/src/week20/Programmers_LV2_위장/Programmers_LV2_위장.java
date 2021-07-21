package week20.Programmers_LV2_위장;

import java.util.HashMap;
import java.util.Map;

public class Programmers_LV2_위장 {
	public static void main(String[] args) {
		String[][] clothes = {
				{"yellowhat", "headgear"},
				{"bluesunglasses", "eyewear"},
				{"green_turban", "headgear"}
		};
		
		System.out.println(solution(clothes));
	}
	
	static int solution(String[][] clothes) {
		//String: 옷의 종류, Integer: 각 종류별 옷의 개수
		Map<String, Integer> map = new HashMap<>();
		
		
		for (String[] item : clothes) {
			//옷의 종류
			String category = item[1];
			
			//이미 있는 종류면 value를 1 증가시킨다
			if(map.containsKey(category)) {
				map.put(category, map.get(category)+1);
			}
			//없는 종류면 새로 map에 넣어준다
			else {
				map.put(category, 1);
			}
		}
		
		//가능한 옷의 조합 수
		int result = 1;
		
		//가능한 옷의 조합 수 구하기
		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			System.out.println(entry.getValue());
			//해당 옷을 선택하지 않는 것도 하나의 경우로 생각하여 value에 +1한 경우로 조합을 구한다
			result *= (entry.getValue()+1);
		}
		
		//선택을 안하는 경우로 +1을 하여 곱했기 때문에 모두 선택하지 않는 경우가 포함되므로 그 경우를 제외하고 리턴한다 
		return result-1;
		
	}
}
