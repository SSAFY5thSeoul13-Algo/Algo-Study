package week19.PROGRAMMERS_LV2_더맵게;

import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1-o2);
        
        for(int i=0; i<scoville.length; i++){
            pq.offer(scoville[i]);
        }
        
        // 1개이상이고, 가장 맵지 않은 음식의 스코빌지수가 K미만인 경우만 로직실행
        while(pq.size() > 1 && pq.peek() < K){
            int first = pq.poll();
            int second = pq.poll();
            
            pq.offer(first + second*2);
            answer++;
        }
        // 모든 스코빌 지수가 K이상이 되지 않으면, -1
        if(pq.peek() < K ) answer = -1;
        
        return answer;
    }
}

