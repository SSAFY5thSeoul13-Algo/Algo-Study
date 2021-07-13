package week19.heap;

import java.util.PriorityQueue;

public class 더_맵게 {
	public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for(int i=0; i<scoville.length; i++) {
        	pq.add(scoville[i]);
        }
        
        int cnt = 0;
        while(true) {
        	// 모든 음식의 스코빌 지수가 K이상이 되면 break
        	if(pq.peek()>=K) {
        		answer = cnt;
        		break;
        	}
        	
        	if(pq.size()==1) {
        		return -1;
        	}
        	
        	cnt++;
        	int first = pq.poll();
        	int second = pq.poll();
        	
        	int mixed = first + (second * 2);
        	
        	pq.add(mixed);

        }
        
        
        return answer;
    }

}
