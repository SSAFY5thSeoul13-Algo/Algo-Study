package week21.stack_queue4;

import java.util.*;

public class Programmers_주식가격 {	
	public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> queue = new LinkedList<>();
        
        // 주식가격을 queue에 넣는다.
        for(int i=0; i<prices.length; i++) {
        	queue.add(prices[i]);
        }
        
        // 주식가격의 순서와 현재 시간을 나타내는 변수 
        int index = 0;
        int price = 0;
        
        while(!queue.isEmpty()) {
        	int time = 0;
        	price = queue.poll();
        	for(int i=index+1; i<prices.length; i++) {
        		// 가격이 떨어지는 경우 
        		if(price>prices[i]) {
        			time++;
        			break;
        		}
        		// 가격이 떨어지지 않는 경우 
        		else {
        			time++;
        		}
        	}
        	answer[index] = time;
        	index++;
        
        }
        return answer;
    }

}
