## Programmers 야근 지수

### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/12927

회사원 Demi는 가끔은 야근을 하는데요, 야근을 하면 야근 피로도가 쌓입니다. 야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값입니다. Demi는 N시간 동안 야근 피로도를 최소화하도록 일할 겁니다.Demi가 1시간 동안 작업량 1만큼을 처리할 수 있다고 할 때, 퇴근까지 남은 N 시간과 각 일에 대한 작업량 works에 대해 야근 피로도를 최소화한 값을 리턴하는 함수 solution을 완성해주세요.

#### 제한 사항
works는 길이 1 이상, 20,000 이하인 배열입니다.
works의 원소는 50000 이하인 자연수입니다.
n은 1,000,000 이하인 자연수입니다.

#### 입출력 예 설명
입출력 예 #1
n=4 일 때, 남은 일의 작업량이 [4, 3, 3] 이라면 야근 지수를 최소화하기 위해 4시간동안 일을 한 결과는 [2, 2, 2]입니다. 이 때 야근 지수는 22 + 22 + 22 = 12 입니다.

입출력 예 #2
n=1일 때, 남은 일의 작업량이 [2,1,2]라면 야근 지수를 최소화하기 위해 1시간동안 일을 한 결과는 [1,1,2]입니다. 야근지수는 12 + 12 + 22 = 6입니다.

###  💡 풀이

변수
`int[][] map` : 입력된 숫자를 저장할 배열
`int[][] maxDp` : 지나오면서 더 큰 값을 가진 경우를 저장하는 배열
`int[][] minDp` : 지나오면서 더 작은 값을 가진 경우를 저장하는 배열


<br>

가장 큰 수에서 값을 빼가면서 풀었다

우선순의 큐를 숫자의 내림차순으로 정렬하도록 만든 후 모든 작업량을 집어 넣었다

가장 큰 작업량을 우선순위 큐 `pq`에서 꺼낸 후 모든 작업에 대해 뺄 수 있는 작업량 `n/count`혹은 `1`중에 큰 값으로 빼 주었다

이 작업을 `n`이 0이하가 될 때 까지 반복한 후 다시 while문을 통해 `pq`에 있는 모든 수의 제곱의 합을 구했다



<br><br>

###  💡 소스코드
```java
import java.util.*;

public class Programmers_야근지수 {

	class Solution {
	    public long solution(int n, int[] works) {
	        long answer = 0;
	        
	        int count = works.length;
	        
	        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

	        for(int i = 0; i < count; i++){
	            pq.offer(works[i]);
	        }
	        
	        while(!pq.isEmpty()){
	            if(n <= 0) break;
	            
	            int num = Math.max(n/count, 1);
	            
	            int work = pq.poll();
	            
	            work -= num;
	            n -= num;
	            
	            pq.offer(work);
	        }
	        
	        while(!pq.isEmpty()){            
	            int work = pq.poll();
	            
	            if(work <= 0)   continue;
	            
	            answer += Math.pow(work,2);
	        }
	        return answer;
	    }
	}
}





```


<br>



#### 정확성  테스트

번호|결과
--|--
테스트 1 |	통과 (0.55ms, 78MB)
테스트 2 |	통과 (0.51ms, 73.9MB)
테스트 3 |	통과 (0.41ms, 79.1MB)
테스트 4 |	통과 (0.44ms, 78.9MB)
테스트 5 |	통과 (0.40ms, 73.5MB)
테스트 6 |	통과 (0.43ms, 76.4MB)
테스트 7 |	통과 (0.47ms, 77.5MB)
테스트 8 |	통과 (1.95ms, 73.3MB)
테스트 9 |	통과 (2.45ms, 73.1MB)
테스트 10 |	통과 (0.40ms, 77.8MB)
테스트 11 |	통과 (0.42ms, 71.1MB)
테스트 12 |	통과 (0.45ms, 73.3MB)
테스트 13 |	통과 (0.48ms, 80.6MB)

#### 효율성  테스트

번호|결과
--|--
테스트 1 |	통과 (20.51ms, 52.6MB)
테스트 2 |	통과 (20.01ms, 53MB)