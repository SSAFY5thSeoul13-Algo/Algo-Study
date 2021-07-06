## Programmers - 더 맵게 
- heap, PriorityQueue
- Level2

## 풀이
> 저는 PriorityQueue를 사용하여 문제를 풀었습니다. 먼저 반복문은 pq.peek()이 제일 스코빌이 적은 값이기 때문에 이 값이 K보다 크거나 같으면 break를 해주었습니다. <br>
그리고 아직 스코빌 지수가 젤 낮은 음식이 K보다 작으면서 큐의 사이즈가 1인 경우 더 이상 스코빌지수를 K이상으로 만들 구 없으므로 -1을 return 합니다.<br>
다음 섞는 횟수는 cnt에 저장하고 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2) 공식을 이용하여 문제를 해결하였습니다.

## 소스코드
~~~
import java.util.PriorityQueue;

public class 더_맵게 {
	public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for(int i=0; i<scoville.length; i++) {
        	pq.add(scoville[i]);
        }
        
        int cnt = 0;
        while(true) {
        	// 모든 음식의 스코빌 지수가 K이상이 되면 break
        	if(pq.peek()>=K) {
        		answer = cnt;
        		break;
        	}
        	
        	if(pq.size()==1) {
        		return -1;
        	}
        	
        	cnt++;
        	int first = pq.poll();
        	int second = pq.poll();
        	
        	int mixed = first + (second * 2);
        	
        	pq.add(mixed);

        }
        
        
        return answer;
    }

}
~~~

## 결과 
-------

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.46ms, 51.7MB)|
|테스트 2 |	통과 (0.36ms, 52.1MB)|
|테스트 3 |	통과 (0.60ms, 53.3MB)|
|테스트 4 |	통과 (0.46ms, 51.8MB)|
|테스트 5 |	통과 (0.44ms, 51.9MB)|
|테스트 6 |	통과 (2.89ms, 53.1MB)|
|테스트 7 |	통과 (4.25ms, 52MB)|
|테스트 8 |	통과 (0.95ms, 51.9MB)|
|테스트 9 |	통과 (1.00ms, 51.8MB)|
|테스트 10 |	통과 (2.08ms, 53.3MB)|
|테스트 11 |	통과 (1.53ms, 53.8MB)|
|테스트 12 |	통과 (6.35ms, 52.1MB)|
|테스트 13 |	통과 (2.69ms, 53.3MB)|
|테스트 14 |	통과 (0.54ms, 53MB)|
|테스트 15 |	통과 (2.94ms, 54.1MB)|
|테스트 16 |	통과 (0.42ms, 52MB)|

----------


|	효율성  |	테스트	|
|----|----|
|테스트 1 |	통과 (180.77ms, 67.1MB)|
|테스트 2 |	통과 (306.21ms, 87.3MB)|
|테스트 3 |	통과 (1449.83ms, 122MB)|
|테스트 4 |	통과 (113.23ms, 63.6MB)|
|테스트 5 |	통과 (1806.22ms, 123MB)|
