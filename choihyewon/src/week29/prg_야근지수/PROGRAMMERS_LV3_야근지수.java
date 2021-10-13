package week29.prg_야근지수;

import java.util.*;

public class PROGRAMMERS_LV3_야근지수 {
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public long solution(int n, int[] works) {
        long answer = 0;
        
        for(int i=0; i<works.length; i++){
            pq.add(works[i]);
        }
        
        for(int i=0; i<n; i++){
            int num = pq.poll();
            if(num>0){
                pq.add(num-1);
            }else{
                pq.add(0);
            }
            
        }
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(),2);
        }
        
        
        return answer;
    }

}
