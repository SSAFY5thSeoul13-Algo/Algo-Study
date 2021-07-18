package week19.heap3;

import java.util.Collections;
import java.util.PriorityQueue;

public class 이중_우선순위_큐 {
	class Solution {
	    public int[] solution(String[] operations) {
	    	// 최댓값/최솟값 출력 
	        int[] answer = new int[2];
	        
	        PriorityQueue<Integer> pq = new PriorityQueue<>();
	        PriorityQueue<Integer> reversepq = new PriorityQueue<>(Collections.reverseOrder());
	        
	        for(int i=0; i<operations.length; i++) {
	        	String str = operations[i];
	        	String[] op = str.split(" ");
	        	
	        	if(op[0].equals("I")) {
	        		int num = Integer.parseInt(op[1]);
	        		pq.add(num);
	        		reversepq.add(num);
	        	}else if(op[0].equals("D")) {
	        		int num = Integer.parseInt(op[1]);
	        		if(!pq.isEmpty()) {
	        			int remove_num = 0;
		        		if(num==-1) {
		        			remove_num = pq.peek();
		        		}else if(num==1) {
		        			remove_num = reversepq.peek();
		        		}
		        		
		        		pq.remove(remove_num);
		        		reversepq.remove(remove_num);
	        		}
	        	}
	        }
	        
	        if(pq.isEmpty()) {
	        	answer[0] = 0;
		        answer[1] = 0;
	        }else {
	        	answer[0] = reversepq.poll();
		        answer[1] = pq.poll();
	        }
	        
	        
	        return answer;
	    }
	}
	

}
