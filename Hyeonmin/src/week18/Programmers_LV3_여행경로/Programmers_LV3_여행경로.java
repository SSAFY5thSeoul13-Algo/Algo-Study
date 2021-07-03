package week18.Programmers_LV3_여행경로;

import java.util.Arrays;

public class Programmers_LV3_여행경로 {
	static String[][] tickets = {
			{"ICN", "SFO"},
			{"ICN", "ATL"},
			{"SFO", "ATL"},
			{"ATL", "ICN"},
			{"ATL","SFO"}
	};
	
	static int size;
	static boolean[] isUsed;
	static String[] cities;
	static String[] answer;

	public static void main(String[] args) {
		String[] answer = solution();
		
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i]+" ");
		}
	}

	static String[] solution() {
		//티켓의 수
		size = tickets.length;
		cities = new String[size+1];
		answer = new String[size+1];
		
		//시작 위치
		cities[0] = "ICN";
		
		//각 티켓을 처음으로 탈 수 있는지 확인
		for (int i = 0; i < size; i++) {
			isUsed = new boolean[size];
			
			//처음으로 시작이 가능한 티켓으로 여행 시작
			if(tickets[i][0].equals("ICN"))
				dfs(i, 1);
		}
		
		return answer;
	}
	
	static void dfs(int idx, int cnt) {
		//티켓을 사용해서 도착한 도시 저장
		cities[cnt] = tickets[idx][1];
		
		//모든 티켓을 사용한 경우
		if(cnt == size) {
			//저장된 결과가 없는 경우
			if(answer[0] == null) {
				answer = Arrays.copyOf(cities, size+1);
				return;
			}
			
			//저장된 결과가 있는 경우
			for (int i = 0; i < size+1; i++) {
				//i번째 도착한 도시가 다를 때
				if(!cities[i].equals(answer[i])) {
					//도시이름을 비교
					for (int j = 0; j < 3; j++) {
						//도시의 이름을 비교해 새로 구한 배열의 알파벳 순서가 앞인 경우 결과 변경
						if(cities[i].charAt(j) < answer[i].charAt(j)) {
							answer = Arrays.copyOf(cities, size+1);
							return;
						}
						//새로 구한 배열의 알파벳 순서가 뒤인 경우
						else if(cities[i].charAt(j) > answer[i].charAt(j)) {
							return;
						}
					}
					return;
				}
			}
			
			return;
		}
		
		//티켓 사용 체크
		isUsed[idx] = true;
		
		//다음 출발지
		String nextCity = tickets[idx][1];
		
		for (int i = 0; i < size; i++) {
			//아직 사용안한 티켓중에 출발지가 지금 도시와 같은 경우 
			if(!isUsed[i] && nextCity.equals(tickets[i][0])) {
				dfs(i, cnt+1);
			}
		}
		
		//티켓 사용 체크 해제
		isUsed[idx] = false;
	}
	
}
