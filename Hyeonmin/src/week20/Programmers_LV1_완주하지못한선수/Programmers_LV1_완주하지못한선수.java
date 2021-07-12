package week20.Programmers_LV1_완주하지못한선수;

import java.util.HashMap;
import java.util.Map;

public class Programmers_LV1_완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		
		System.out.println(solution(participant, completion));
	}
	
	static String solution(String[] participant, String[] completion) {
		Map<String, Integer> map = new HashMap<>();
		
		//참여한 선수들 등록
		for (int i = 0; i < participant.length; i++) {
			//동명이인의 선수인 경우
			if(map.containsKey(participant[i])) {
				//해당 이름 선수의 수를 1 증가
				int num = map.get(participant[i]);
				
				map.put(participant[i], num+1);
			}
			//등록한 선수중 같은 이름이 없는 경우
			else {
				map.put(participant[i], 1);
			}
		}
		
		String answer = "";

		//완주자 선수들 확인
		for (int i = 0; i < completion.length; i++) {
			//해당 이름의 선수 수
			int num = map.get(completion[i]);
			
			//한명만 있는 경우 제거
			if(num == 1) {
				map.remove(completion[i]);
			}
			//2명 이상인 경우 숫자 1 감소
			else {
				map.replace(completion[i], num-1);
			}
		}
		
		//남은 선수의 이름을 answer에 저장
		for (String key : map.keySet()) {
			answer = key;
		}
		
		return answer;
	}
}
