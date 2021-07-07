## Programmers - 더 맵게 
- heap, PriorityQueue
- Level3

## 풀이
> Request class를 생성하여 실행시간이 적은 것을 우선순위로 해주었습니다. 그리고 우선순위큐로 정렬된 pq를 list에 다시 넣어주어 list의 길이가 0보다 클동안 순차적으로 list를 순회하면서 요청을 시작한 시간이 현재시간보다 빠르거나 같다면 요청을 수행했습니다. 모든 요청시간의 합은 sum, 현재 시간은 time에 저장하고 요청을 수행하면 list에서 삭제를 해주었습니다. 또한 현재시간에 수행할 요청이 없다면 시간을 1초 증가시켰습니다. 

## 막힌점
> 현재시간에 수행할 요청이 없는 경우를 생각해주지 못해서 틀린 답을 구했었습니다. 

## 소스코드
~~~java
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
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (3.40ms, 52.5MB)|
|테스트 2 |	통과 (2.95ms, 53.2MB)|
|테스트 3 |	통과 (2.34ms, 54.7MB)|
|테스트 4 |	통과 (2.64ms, 52.3MB)|
|테스트 5 |	통과 (2.85ms, 52.6MB)|
|테스트 6 |	통과 (0.84ms, 51.7MB)|
|테스트 7 |	통과 (2.77ms, 52.1MB)|
|테스트 8 |	통과 (2.83ms, 52.5MB)|
|테스트 9 |	통과 (2.65ms, 52.5MB)|
|테스트 10 |	통과 (3.48ms, 52.4MB)|
|테스트 11 |	통과 (0.58ms, 52.2MB)|
|테스트 12 |	통과 (0.73ms, 52.2MB)|
|테스트 13 |	통과 (0.68ms, 52.3MB)|
|테스트 14 |	통과 (0.57ms, 52.1MB)|
|테스트 15 |	통과 (0.73ms, 52.9MB)|
|테스트 16 |	통과 (0.72ms, 52.1MB)|
|테스트 17 |	통과 (0.63ms, 53MB)|
|테스트 18 |	통과 (0.89ms, 51.7MB)|
|테스트 19 |	통과 (0.66ms, 52.2MB)|
|테스트 20 |	통과 (0.72ms, 52.6MB)|
