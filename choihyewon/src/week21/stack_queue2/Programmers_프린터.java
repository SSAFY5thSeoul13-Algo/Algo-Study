package week21.stack_queue2;

import java.util.*;

public class Programmers_프린터 {
	class Print{
		int idx;
		int importance;
		public Print(int idx,int importance) {
			this.idx = idx;
			this.importance = importance;
		}
		
	}
	public int solution(int[] priorities, int location) {
        int answer = 0;
        // 중요도가 높을수록 우선순위가 높으므로 내림차순 정렬 
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // 인쇄 대기 목록 
        Queue<Print> queue = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++) {
        	pq.add(priorities[i]);
        	queue.add(new Print(i,priorities[i]));
        }
        
        int cnt = 1;
        while(!queue.isEmpty()) {
        	// 인쇄대기목록의 가장 앞에 있는 문서를 대기목록에서 꺼낸다.
        	Print print = queue.poll();
        	// 현재 중요도가 제일 높은 문서의 중요도 
        	int priority = pq.poll();
        	
        	// 현재 문서의 중요도가 젤 높다면 
        	if(print.importance>=priority) {
        		if(print.idx == location) {
        			answer = cnt;
        			break;
        		}
        		// 문서를 프린트 했으면 순서를 증가시켜준다. 
    			cnt++;
    		}else {
    			// 더 우선순위가 높은 문서가 있으므로 큐와 우선순위 큐에 넣어준다.
    			queue.add(print);
    			pq.add(priority);
    		}
        }
        return answer;
    }

}
