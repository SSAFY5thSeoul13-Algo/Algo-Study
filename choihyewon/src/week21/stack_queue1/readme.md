## Programmers - 기능개발 
- Queue
- Level2

🔗[기능개발 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42586)

## 풀이
먼저 index와 작업의 진로를 담고 있는 Work 객체를 생성하여 Queue에 담아줍니다.

~~~java
static class Work{
		int idx;
		int progress;
		public Work(int idx,int progress) {
			this.idx = idx;
			this.progress = progress;
		}
	}
~~~

그리고 큐에 담긴 모든 작업이 다 배포가 될때까지 (큐가 비어있으면 반복문 종료) 현재 작업량 + 배포일자*개발속도 값을 비교하여 100보다 같거나 크다면 배포를 할 수 있으므로 큐에서 빼주고 그날 하루의 배포가 된 작업을 count를 해주었습니다.

~~~java
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
~~~

count를 했을때 0이라면, 그날 하루는 배포된 작업이 없으므로 answer에 넣을 값이 없습니다.


## 소스코드
~~~java
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
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.27ms, 52MB)|
|테스트 2 |	통과 (0.60ms, 52.1MB)|
|테스트 3 |	통과 (0.44ms, 51.9MB)|
|테스트 4 |	통과 (0.45ms, 52.4MB)|
|테스트 5 |	통과 (0.39ms, 53.5MB)|
|테스트 6 |	통과 (0.36ms, 52.8MB)|
|테스트 7 |	통과 (0.53ms, 52.7MB)|
|테스트 8 |	통과 (3.42ms, 52.5MB)|
|테스트 9 |	통과 (0.48ms, 52MB)|
|테스트 10 |	통과 (0.50ms, 52.2MB)|
|테스트 11 |	통과 (0.38ms, 52.5MB)|