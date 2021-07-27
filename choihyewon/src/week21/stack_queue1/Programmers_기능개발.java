package week21.stack_queue1;

import java.util.*;

public class Programmers_기능개발 {
	static class Work{
		int idx;
		int progress;
		public Work(int idx,int progress) {
			this.idx = idx;
			this.progress = progress;
		}
	}
	public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<Work> queue = new LinkedList<>();
 
        for(int i=0; i<progresses.length; i++) {
        	queue.add(new Work(i,progresses[i]));
        }
        
        int day = 0;
        while(!queue.isEmpty()) {
        	day++;
        	int cnt = 0;
        	while(!queue.isEmpty()) {
        		Work work = queue.peek();
        		int idx = work.idx;
        		int progress = work.progress;
        		int tmp = progress + speeds[idx]*day;
        		if(tmp>=100) {
        			// 배포를 할 수 있다면 queue에서 poll
        			queue.poll();
        			// 배포시 작업의 개수 count
        			cnt++;
        		}else {
        			break;
        		}
        	}
        	
        	// 배포를 한 경우에만 list에 담는다.
        	if(cnt!=0) {
        		list.add(cnt);
        	}
        	
        	
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
        	answer[i] = list.get(i);
        }
        
        return answer;
    }

}
