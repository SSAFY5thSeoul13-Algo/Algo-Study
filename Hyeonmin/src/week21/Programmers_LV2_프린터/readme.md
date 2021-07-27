## Programmers LV2 프린터
- 스택/큐
- level2

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42587

아래와 같은 방식으로 인쇄 작업을 수행합니다.

1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
3. 그렇지 않으면 J를 인쇄합니다.

예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.

내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.

현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.


#### 제한사항
현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
<br><br>

###  💡 풀이

변수
`Queue<int[]> queue` : 각 문서작업의 인덱스와 중요도를 저장할 큐. [0]: 인덱스, [1]: 우선순위 
`PriorityQueue<Integer> pq` : 현재 남아있는 문서작업의 중요도들을 내림차순으로 저장할 우선순위 큐
`int count` : 인쇄된 문서의 수


<br>

int[]를 저장하는 큐 `queue`를 만들어서 각 문서작업의 인덱스와 중요도를 저장하고, 우선순위 큐 `pq`를 만들어서 모든 작업의 중요도를 내림차순으로 저장한다.

```java
		//각 문서의 번호와 중요도를 저장할 큐
		Queue<int[]> queue = new LinkedList<>();
		//남아 있는 문서의 중요도들을 내림차순으로 저장할 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		//문서의 중요도와 번호를 저장
		for (int i = 0; i < priorities.length; i++) {
			queue.offer(new int[] {i, priorities[i]});
			pq.offer(priorities[i]);
		}
```

인쇄된 작업의 수를 저장할 `count`변수를 만들고 while문을 모든 문서 작업이 완료될 때 까지 반복한다.
`pq`에서 가장 높은 중요도를 꺼낸 다음 `queue`에 있는 작업들을 순서대로 꺼내서 중요도를 비교하고 가장 중요한 작업인 경우 완료시켜 `count`를 증가시킨다.

완료시킨 작업이 `location`과 일치하면 그 때 까지 완료시킨 작업의 수가 `location`이 완료된 순서와 같으므로 `count`를 리턴시킨다.

`queue`에서 꺼낸 작업보다 `priority`가 더 높은 경우 해당 작업을 다시 `queue`에 넣고 같은 과정을 반복한다. 

```java
		//인쇄된 문서의 수
		int count = 0;
		
		//남은 문서 작업이 있는 경우
		while(!pq.isEmpty()) {
			//현재 남아있는 작업 중 가장 높은 우선순위
			int priority = pq.poll();
			
			while(!queue.isEmpty()) {
				//다음 순서에 있던 문서 작업
				int[] document = queue.poll();
				
				//해당 문서 작업의 우선순위가 지금 남아있는 문서들의 중요도중 가장 높은 경우
				if(priority == document[1]) {
					//문서를 인쇄
					count++;
					
					//인쇄한 문서가 목표 문서인 경우 그 문서가 몇번째로 인쇄된 문서인지 리턴
					if(document[0] == location)
						return count;
					
					break;
				}
				//해당 문서보다 높은 중요도의 작업이 있는 경우 해당 작업을 다시 큐에 저장
				else {
					queue.offer(document);
				}
			}
			
		}
```



<br><br>

###  💡 소스코드
```java
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_LV2_프린터 {
	public static void main(String[] args) {
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		
		System.out.println(solution(priorities, location));
	}
	
	static int solution(int[] priorities, int location) {
		//각 문서의 번호와 중요도를 저장할 큐
		Queue<int[]> queue = new LinkedList<>();
		//남아 있는 문서의 중요도들을 내림차순으로 저장할 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		//문서의 중요도와 번호를 저장
		for (int i = 0; i < priorities.length; i++) {
			queue.offer(new int[] {i, priorities[i]});
			pq.offer(priorities[i]);
		}
		
		//인쇄된 문서의 수
		int count = 0;
		
		//남은 문서 작업이 있는 경우
		while(!pq.isEmpty()) {
			//현재 남아있는 작업 중 가장 높은 우선순위
			int priority = pq.poll();
			
			while(!queue.isEmpty()) {
				//다음 순서에 있던 문서 작업
				int[] document = queue.poll();
				
				//해당 문서 작업의 우선순위가 지금 남아있는 문서들의 중요도중 가장 높은 경우
				if(priority == document[1]) {
					//문서를 인쇄
					count++;
					
					//인쇄한 문서가 목표 문서인 경우 그 문서가 몇번째로 인쇄된 문서인지 리턴
					if(document[0] == location)
						return count;
					
					break;
				}
				//해당 문서보다 높은 중요도의 작업이 있는 경우 해당 작업을 다시 큐에 저장
				else {
					queue.offer(document);
				}
			}
			
		}
		
		return count;
		
	}
}



```


<br>


테스트 1 〉	통과 (1.60ms, 52.4MB)
테스트 2 〉	통과 (1.78ms, 53MB)
테스트 3 〉	통과 (0.90ms, 52.9MB)
테스트 4 〉	통과 (0.81ms, 53MB)
테스트 5 〉	통과 (0.58ms, 53.1MB)
테스트 6 〉	통과 (0.70ms, 52.8MB)
테스트 7 〉	통과 (0.85ms, 52.7MB)
테스트 8 〉	통과 (1.51ms, 52.3MB)
테스트 9 〉	통과 (0.49ms, 53MB)
테스트 10 〉	통과 (0.99ms, 52.7MB)
테스트 11 〉	통과 (1.40ms, 52.1MB)
테스트 12 〉	통과 (0.77ms, 53.2MB)
테스트 13 〉	통과 (1.08ms, 52.2MB)
테스트 14 〉	통과 (0.69ms, 52.5MB)
테스트 15 〉	통과 (0.69ms, 52.6MB)
테스트 16 〉	통과 (0.75ms, 53.7MB)
테스트 17 〉	통과 (1.41ms, 52MB)
테스트 18 〉	통과 (0.75ms, 51.8MB)
테스트 19 〉	통과 (3.47ms, 53.2MB)
테스트 20 〉	통과 (0.87ms, 52.5MB)