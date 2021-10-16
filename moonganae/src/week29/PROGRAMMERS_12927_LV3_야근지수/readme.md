## Progrmmers LV3 야근 지수
- 우선순위큐
- Level 3

<br>

### 문제설명

> 회사원 Demi는 가끔은 야근을 하는데요, 야근을 하면 야근 피로도가 쌓입니다. 야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값입니다. Demi는 N시간 동안 야근 피로도를 최소화하도록 일할 겁니다.Demi가 1시간 동안 작업량 1만큼을 처리할 수 있다고 할 때, 퇴근까지 남은 N 시간과 각 일에 대한 작업량 works에 대해 야근 피로도를 최소화한 값을 리턴하는 함수 solution을 완성해주세요.


### 제한사항
- works는 길이 1 이상, 20,000 이하인 배열입니다.
- works의 원소는 50000 이하인 자연수입니다.
- n은 1,000,000 이하인 자연수입니다.

### 풀이 및 과정
우선순위큐를 활용하여 문제를 해결하였습니다.

야근지수를 제일 작게 만들기 위해서는 가장큰 야근작업량을 최대한 낮게 만들어야합니다.

따라서, 최댓값이 제일 먼저 뱉는 우선순위큐를 만들어 n만큼 작업수를 줄여줍니다.

이때, 남은 야근 작업량이 0일경우 순회를 멈춥니다.


### 소스코드
```java
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = bf(n, works);
        return answer;
    }
    // 무지성으로 최댓값 하나씩 줄이기!
    public long  bf(int n, int[] works){
        
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works){
            q.offer(work);
        }
        
        /* 남은 정규시간 N시간 적용하기 */
        while(n-- != 0){
            int val = q.poll();
            // 남은 최대 작업량이 0일경우 야근 안해도 된다!
            if(val == 0) {
                q.clear();
                break;
            }
            
            // 아닐경우 최대 야근작업 1줄이기
            q.offer(val-1);
        }
        
        /* 야근지수 계산 */
        long cnt = 0;
        while(!q.isEmpty()){
            int val = q.poll();
            cnt += val*val;
        }
        return cnt;
    }
    
    public long cal(int[] works){
        
        long cnt = 0;
        
        for(int i=0; i<works.length; i++){
            int val = works[i];
            cnt += val*val;
        }
        
        return cnt;
    }
}
```

### 결과
```
<정확성>
테스트 1 〉	통과 (0.46ms, 75.6MB)
테스트 2 〉	통과 (0.50ms, 75.9MB)
테스트 3 〉	통과 (0.37ms, 75.9MB)
테스트 4 〉	통과 (0.47ms, 76.6MB)
테스트 5 〉	통과 (0.55ms, 78.1MB)
테스트 6 〉	통과 (0.55ms, 71.7MB)
테스트 7 〉	통과 (0.72ms, 75.9MB)
테스트 8 〉	통과 (2.23ms, 74.3MB)
테스트 9 〉	통과 (2.25ms, 75.1MB)
테스트 10 〉	통과 (0.39ms, 74.7MB)
테스트 11 〉	통과 (0.45ms, 77.9MB)
테스트 12 〉	통과 (0.42ms, 73.9MB)
테스트 13 〉	통과 (0.42ms, 78.3MB)

<효율성>
테스트 1 〉	통과 (130.83ms, 67.8MB)
테스트 2 〉	통과 (112.21ms, 67.8MB)
```

