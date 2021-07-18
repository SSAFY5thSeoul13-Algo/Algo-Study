package week19.Programmers_LV2_더맵게;

import java.util.PriorityQueue;

public class Programmers_LV2_더맵게 {

	public static void main(String[] args) {
		int K = 7;
		int[] scoville = {1, 2, 3, 9, 10, 12};
		
		int result = solution(scoville, K);
		
		System.out.println(result);

	}
	
	static int solution(int[] scoville, int K) {
		//현재 만들어진 스코빌 지수를 저장할 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		//우선순위 큐에 값 넣음
		for (int i = 0; i < scoville.length; i++) {
			pq.offer(scoville[i]);
		}
		
		//모든 스코빌 지수가 K이상 되는지 여부
		boolean canMake = false;
		//음식을 섞은 횟수
		int count = 0;
		
		while(!pq.isEmpty()) {
			//가장 낮은 스코빌 지수
			int first = pq.poll();
			
			//가장 낮은게 목표 스코빌 지수보다 높은 경우
			if(first >= K) {
				canMake = true;
				break;
			}
			//더 남은 음식이 없는 경우
			else if(pq.isEmpty()) {
				break;
			}
			
			//두번째로 낮은 스코빌 지수
			int second = pq.poll();
			
			//두 개를 섞은 스코빌지수를 큐에 저장
			pq.offer(first + second*2);
			//섞은 횟수 증가
			count++;
			
		}
		
		//모든 스코빌지수가 K이상인 경우 섞은 횟수를 리턴
		if(canMake) {
			return count;
		}
		//K이상으로 만들지 못하면 -1 리턴
		else {
			return -1;
		}
	}

}
