package week19.heap2;

import java.util.*;

public class 디스크_컨트롤러 {
	static class Request implements Comparable<Request>{
		int start;
		int work;
		public Request(int start,int work) {
			this.start = start;
			this.work = work;
		}
		@Override
		public int compareTo(Request o) {
			return this.work - o.work;
		}
	}
	class Solution {
	    public int solution(int[][] jobs) {
	        int answer = 0;
	        // 실행시간이 적은 것으로 우선순위 정렬 
	        PriorityQueue<Request> pq = new PriorityQueue<Request>();
	        List<Request> list = new ArrayList<Request>();
	        
	        for(int i=0; i<jobs.length; i++) {
	        	int starttime = jobs[i][0];
	        	int worktime = jobs[i][1];
	        	pq.add(new Request(starttime,worktime));
	        }
	        
	        // pq에 우선선위 정렬된 요청을 list에 순차적으로 삽입한다.
	        for(int i=0; i<jobs.length; i++) {
	        	list.add(pq.poll());
	        }
	        
	        // 요청이 걸린 시간의 총 합 
	        int sum = 0;
	        // 현재 시간  
	        int time = 0;
	        
	        while(list.size()>0) {
	        	for(int i=0; i<list.size(); i++) {
	        		Request request = list.get(i);
	        		// 요청 시작시간이 현재 시간보다 작거나 같은 경우 
	        		if(time>=request.start) {
	        			time += request.work;
	        			sum += time - request.start;
	        			list.remove(i);
	        			break;
	        		}
	        		// 시작시간이 현재 시간보다 적은 것이 없다면 1초씩 증가 
	        		if(i==list.size()-1){
	        			time++;
	        		}
	        	}
	
	        }
	        // answer에 요청의 평균시간을 저장 
	        answer = sum / jobs.length;
	        
	        return answer;
	    }
	}
	

}
