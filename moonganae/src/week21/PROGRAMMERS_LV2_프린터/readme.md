## Progrmmers LV2 프린터
- 스택/큐
- Level 2
- https://programmers.co.kr/learn/courses/30/lessons/42587
<br>

### 문제설명

> 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.

```
1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
3. 그렇지 않으면 J를 인쇄합니다.
```

> 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다. <br>
내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다. <br>
현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.


### 제한사항
- 현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
- 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
- location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.

### 입출력 예

|priorities|	location|	return|
|----|----|----|
|[2, 1, 3, 2]|	2|	1|
|[1, 1, 9, 1, 1, 1]|	0|	5|


#### 입출력 예 설명
예제 #1

- 문제에 나온 예와 같습니다.

예제 #2

- 6개의 문서(A, B, C, D, E, F)가 인쇄 대기목록에 있고 중요도가 1 1 9 1 1 1 이므로 C D E F A B 순으로 인쇄합니다.

### 풀이 및 과정
작업 큐를 위한 일반 큐, 우선순위 작업을 사용하기 위한 우선순위큐를 사용하여 해결하였습니다.

먼저 priorities 배열을 사용하여 작업큐와 우선순위큐에 넣습니다. <br>
이때, 작업큐에는 인덱스와 우선순위를, 우선순위큐에는 우선순위만 넣습니다.

```java
// 인쇄의 작업 큐
Queue<Doc> q = new LinkedList<>();

// 중요도를 정렬할 우선순위 큐
PriorityQueue<Integer> pq = new PriorityQueue<>( (o1, o2) -> o2 - o1);

// 작업 큐와 우선순위 큐에 넣음
int curPrior = priorities[location];
for(int i=0; i<priorities.length; i++){
    q.offer(new Doc(i, priorities[i]));
    pq.offer(priorities[i]);
}
```

작업 큐를 돌면서 가장 높은 우선순위 작업일때 처리하고, 아니면 다시 작업큐 맨뒤로 넣습니다.


### 소스코드
```java
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        // 인쇄의 작업 큐
        Queue<Doc> q = new LinkedList<>();
        
        // 중요도를 정렬할 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>( (o1, o2) -> o2 - o1);
        
        // 작업 큐와 우선순위 큐에 넣음
        int curPrior = priorities[location];
        for(int i=0; i<priorities.length; i++){
            q.offer(new Doc(i, priorities[i]));
            pq.offer(priorities[i]);
        }
        
        
        while(!q.isEmpty()){
            
            Doc cur = q.poll();
            // 우선순위가 높은 작업이라면
            if(pq.peek() == cur.priority){
                pq.poll();
                // 작업완료
                answer++;
                
                // 내가 인쇄요청한 문서라면
                if(cur.idx == location){
                    // 중단
                    break;
                }
            }else{
                // 처리못하면 다시 넣음.
                q.offer(cur);
            }
        }
                    
        return answer;
    }
    class Doc {
        int idx, priority;
        public Doc(int idx, int priority){
            this.idx = idx;
            this.priority = priority;
        }
    }
}
```

### 결과
```
테스트 1 〉	통과 (1.44ms, 52.1MB)
테스트 2 〉	통과 (2.04ms, 52.5MB)
테스트 3 〉	통과 (1.33ms, 52.6MB)
테스트 4 〉	통과 (1.29ms, 52.3MB)
테스트 5 〉	통과 (0.99ms, 53.7MB)
테스트 6 〉	통과 (1.34ms, 52.8MB)
테스트 7 〉	통과 (1.34ms, 52.9MB)
테스트 8 〉	통과 (1.82ms, 52.6MB)
테스트 9 〉	통과 (1.18ms, 51.8MB)
테스트 10 〉	통과 (1.49ms, 52.3MB)
테스트 11 〉	통과 (4.56ms, 52.9MB)
테스트 12 〉	통과 (1.29ms, 52.9MB)
테스트 13 〉	통과 (1.62ms, 52.8MB)
테스트 14 〉	통과 (1.06ms, 53.1MB)
테스트 15 〉	통과 (1.42ms, 52.9MB)
테스트 16 〉	통과 (1.32ms, 52.3MB)
테스트 17 〉	통과 (2.04ms, 53.1MB)
테스트 18 〉	통과 (1.16ms, 52.9MB)
테스트 19 〉	통과 (3.59ms, 52.1MB)
테스트 20 〉	통과 (1.62ms, 52.5MB)
```

