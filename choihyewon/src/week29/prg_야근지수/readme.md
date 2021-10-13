## Programmers - 야근지수 

- PriorityQueue 
- 연습문제 
- Level 3 
🔗[야근지수 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/12927)


## 풀이

저는 우선순위 큐를 사용하여 문제를 풀었습니다.
먼저 works 배열에 있는 작업량을 우선순위 큐에 넣어준뒤 Collections.reverseOrder()를 통해 내림차순 정렬을 해줍니다.

그리고 N번만큼 제일 큰수에서 1을 빼준뒤 나머지 값들의 제곱을 더하면 야근 피로도의 최소값을 구할 수 있습니다.

따라서 우선순위큐에서 poll한 값을 -1해서 다시 우선순위 큐에 넣어주고 poll한 값이 0인 경우는 -1을 하면 음수가 들어가기 때문에 0을 넣어주었습니다.

그리고 우선순위큐가 빈 상태가 될때까지 남은 작업량의 제곱값을 answer에 더해 값을 구하였습니다.


## 소스코드
~~~java

import java.util.*;

public class PROGRAMMERS_LV3_야근지수 {
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public long solution(int n, int[] works) {
        long answer = 0;
        
        for(int i=0; i<works.length; i++){
            pq.add(works[i]);
        }
        
        for(int i=0; i<n; i++){
            int num = pq.poll();
            if(num>0){
                pq.add(num-1);
            }else{
                pq.add(0);
            }
            
        }
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(),2);
        }
        
        
        return answer;
    }

}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.14ms, 76.1MB)|
|테스트 2 |	통과 (0.25ms, 76.6MB)|
|테스트 3 |	통과 (0.08ms, 76.3MB)|
|테스트 4 |	통과 (0.17ms, 73.9MB)|
|테스트 5 |	통과 (0.13ms, 71.2MB)|
|테스트 6 |	통과 (0.10ms, 73.8MB)|
|테스트 7 |	통과 (0.13ms, 74.6MB)|
|테스트 8 |	통과 (1.60ms, 67.3MB)|
|테스트 9 |	통과 (2.14ms, 82.5MB)|
|테스트 10 |	통과 (0.11ms, 74MB)|
|테스트 11 |	통과 (0.09ms, 74.1MB)|
|테스트 12 |	통과 (0.12ms, 78.9MB)|
|테스트 13 |	통과 (0.09ms, 76.4MB)|

-------

|효율성 | 테스트 |
|---|---|
|테스트 1 |	통과 (145.09ms, 68MB)|
|테스트 2 |	통과 (109.83ms, 69.1MB)|

