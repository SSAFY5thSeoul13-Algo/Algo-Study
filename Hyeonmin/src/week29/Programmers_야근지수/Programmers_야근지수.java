package week29.Programmers_야근지수;

import java.util.*;

public class Programmers_야근지수 {

	class Solution {
	    public long solution(int n, int[] works) {
	        long answer = 0;
	        
	        int count = works.length;
	        
	        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

	        for(int i = 0; i < count; i++){
	            pq.offer(works[i]);
	        }
	        
	        while(!pq.isEmpty()){
	            if(n <= 0) break;
	            
	            int num = Math.max(n/count, 1);
	            
	            int work = pq.poll();
	            
	            work -= num;
	            n -= num;
	            
	            pq.offer(work);
	        }
	        
	        while(!pq.isEmpty()){            
	            int work = pq.poll();
	            
	            if(work <= 0)   continue;
	            
	            answer += Math.pow(work,2);
	        }
	        return answer;
	    }
	}
}
