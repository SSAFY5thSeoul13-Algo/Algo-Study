package week19.PRG_Lv2_더맵게;

import java.util.*;

public class Solution_PRG_Lv2_더맵게 {
	public static void main(String[] args) {
		System.out.println(solution(new int[]{1,2,3}, 11));
	}
	
	public static int solution(int[] scoville, int K) {
		int answer = 0; // 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
		
		PriorityQueue<Integer> scovillePQ = new PriorityQueue<Integer>();
		for(int value : scoville) {
			scovillePQ.add(value);
		}
		
		while(true) {
			if(scovillePQ.isEmpty()) return -1;
			
			if(scovillePQ.peek()>=K) break;
			//모든 음식의 스코빌 지수가 K 이상이 될 때까지 => 가장 작은 스코빌 지수가 K이상인지 확인

			
			if(scovillePQ.size()<2) return -1;
			//모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우
			

			
			int val1 = scovillePQ.poll(); //스코빌 지수가 가장 낮은 값
			int val2 = scovillePQ.poll(); //스코빌 지수가 두번째로 낮은 값
			
			//가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
			int mixVal = val1 + (val2*2); //섞은 스코빌 지수
			answer++;

			scovillePQ.add(mixVal);
			
		}
		
		return answer;
    }
}
