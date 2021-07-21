## Programmers - 프린터 
- PriorityQueue, Queue
- Level2 

🔗[프린터 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42587)

## 풀이

Print 객체를 담는 큐와 문서의 중요도를 담은 우선순위큐(내림차순이므로 Collections.reverseOrder()) 를 생성합니다.

그리고 큐에 값이 있다는 것은 문서가 아직 다 프린트되지 않은 것 이므로 큐가 empty될때까지 반복문을 실행합니다.
현재 문서의 중요도가 제일 높다면 만약 내가 인쇄를 요청한 문서라면 answer에 cnt를 담고 break, 아닌 경우 cnt를 증가시킵니다.
만약 문서의 중요도가 제일 높지 않다면, 큐와 우선순위큐에 값을 다시 넣어줍니다.

## 소스코드
~~~java
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
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.86ms, 52.1MB)|
|테스트 2 |	통과 (4.29ms, 53MB)|
|테스트 3 |	통과 (1.10ms, 52.8MB)|
|테스트 4 |	통과 (1.22ms, 52.8MB)|
|테스트 5 |	통과 (0.97ms, 52.9MB)|
|테스트 6 |	통과 (1.68ms, 53.7MB)|
|테스트 7 |	통과 (1.53ms, 53.3MB)|
|테스트 8 |	통과 (3.05ms, 52.7MB)|
|테스트 9 |	통과 (0.95ms, 54.6MB)|
|테스트 10 |	통과 (1.33ms, 52.9MB)|
|테스트 11 |	통과 (2.06ms, 52.8MB)|
|테스트 12 |	통과 (1.07ms, 52.3MB)|
|테스트 13 |	통과 (2.90ms, 53.9MB)|
|테스트 14 |	통과 (0.95ms, 52.2MB)|
|테스트 15 |	통과 (1.06ms, 52.8MB)|
|테스트 16 |	통과 (1.26ms, 52.4MB)|
|테스트 17 |	통과 (2.62ms, 52.6MB)|
|테스트 18 |	통과 (1.21ms, 53.9MB)|
|테스트 19 |	통과 (2.54ms, 52.8MB)|
|테스트 20 |	통과 (1.30ms, 53.1MB)|