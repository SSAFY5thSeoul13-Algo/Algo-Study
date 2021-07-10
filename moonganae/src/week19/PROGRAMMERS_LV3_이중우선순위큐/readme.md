## Progrmmers LV3 이중우선순위큐
- 힙
- Level 3
- https://programmers.co.kr/learn/courses/30/lessons/42628
<br>

### 문제설명

>이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.

|명령어|	수신 탑(높이)|
|---|-------|
|I 숫자|	큐에 주어진 숫자를 삽입합니다.|
|D 1|	큐에서 최댓값을 삭제합니다.|
|D -1|	큐에서 최솟값을 삭제합니다.|

> 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.

### 제한사항
- operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
- operations의 원소는 큐가 수행할 연산을 나타냅니다.
    - 원소는 “명령어 데이터” 형식으로 주어집니다.
    - 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
- 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.

### 입출력 예
|operations|	return|
|-------|----|
|["I 16","D 1"]|	[0,0]|
|["I 7","I 5","I -5","D -1"]	|[7,5]|

#### 입출력 예 설명
16을 삽입 후 최댓값을 삭제합니다. 비어있으므로 [0,0]을 반환합니다.
7,5,-5를 삽입 후 최솟값을 삭제합니다. 최대값 7, 최소값 5를 반환합니다.

### 풀이 및 과정
우선순위 큐를 2개 사용하여 문제를 해결하였습니다. 

- 먼저 최대힙, 최소힙을 우선순위큐를 이용하여 선언
- 숫자 삽입은 최대힙, 최소힙 모두 삭제합니다.
- 숫자 삭제
    - 최솟값은 최소힙에서 꺼내 그값을 최대힙에서도 제거 해줍니다.
    - 최댓값은 최대힙에서 꺼내 그값을 최소힙에서도 제거 해줍니다.


### 소스코드
```java
import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        // 최대힙, 최소힙 선언
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1,o2)->o2-o1);
        PriorityQueue<Integer> minPq = new PriorityQueue<>((o1,o2)->o1-o2);
        
        for(String oper : operations){
            StringTokenizer st = new StringTokenizer(oper);
            
            char cmd = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            // 숫자삽입
            if(cmd == 'I'){
                maxPq.offer(num);
                minPq.offer(num);
            }
            // 숫자 삭제
            else if(!maxPq.isEmpty()){
                // 최솟값 삭제
                if(num == -1){
                    int min = minPq.poll();
                    maxPq.remove(min);
                }
                // 최댓값 삭제
                else{
                    int max = maxPq.poll();
                    // System.out.println(max + "삭제");
                    minPq.remove(max);
                }
            }
        }
        // 큐가 비어있다면 0,0 반환
        if(maxPq.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            answer[0] = maxPq.poll();
            answer[1] = minPq.poll();
            
        }
        
        return answer;
    }
}
```

### 결과
```
테스트 1 〉	통과 (3.74ms, 52.6MB)
테스트 2 〉	통과 (1.46ms, 52.3MB)
테스트 3 〉	통과 (1.41ms, 53.1MB)
테스트 4 〉	통과 (1.46ms, 52.8MB)
테스트 5 〉	통과 (1.44ms, 52.9MB)
테스트 6 〉	통과 (1.52ms, 53.1MB)
```