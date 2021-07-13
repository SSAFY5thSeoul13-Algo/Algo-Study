## Programmers Lv2 더 맵게
- 힙
- level2



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42626

매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다. 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.

> 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)

Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.

#### 제한사항
- scoville의 길이는 2 이상 1,000,000 이하입니다.
- K는 0 이상 1,000,000,000 이하입니다.
- scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
- 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
 
#### 입출력 예
| scoville | K | return |   
|--|--|--|  
| [1, 2, 3, 9, 10, 12] | 7 | 2 |  

1. 스코빌 지수가 1인 음식과 2인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.  
새로운 음식의 스코빌 지수 = 1 + (2 * 2) = 5  
가진 음식의 스코빌 지수 = [5, 3, 9, 10, 12]  

2. 스코빌 지수가 3인 음식과 5인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.  
새로운 음식의 스코빌 지수 = 3 + (5 * 2) = 13  
가진 음식의 스코빌 지수 = [13, 9, 10, 12]  

모든 음식의 스코빌 지수가 7 이상이 되었고 이때 섞은 횟수는 2회입니다.  

<br><br>

###  💡 풀이
- `int answer` : 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
- `PriorityQueue<Integer> scovillePQ` : 스코빌 지수를 저장할 PQ

<br>

PriorityQueue를 이용하여 스코빌지수가 가장 낮은 순서대로 queue에서 `poll`할 수 있도록 합니다.  

그리고 문제에서 주어진 대로 스코빌지수가 가장 낮은 2개를 `poll`한 뒤,  
`int mixVal = val1 + (val2*2)` 하여 `mixVal`을 다시 PQ에 `add`합니다.


###  💡 소스코드
```
import java.util.*;

class Solution {
    public static int solution(int[] scoville, int K) {
		int answer = 0; // 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수
		
		PriorityQueue<Integer> scovillePQ = new PriorityQueue<Integer>();
		for(int value : scoville) {
			scovillePQ.add(value);
		}
		
		while(true) {
			if(scovillePQ.isEmpty()) return -1;
			
			if(scovillePQ.peek()>=K) break;
			//모든 음식의 스코빌 지수가 K 이상이 될 때까지 => 가장 작은 스코빌 지수가 K이상인지 확인

			
			if(scovillePQ.size()<2) return -1;
			//모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우
			

			
			int val1 = scovillePQ.poll(); //스코빌 지수가 가장 낮은 값
			int val2 = scovillePQ.poll(); //스코빌 지수가 두번째로 낮은 값
			
			//가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
			int mixVal = val1 + (val2*2); //섞은 스코빌 지수
			answer++;

			scovillePQ.add(mixVal);
			
		}
		
		return answer;
    }
}
```

<br>

### 결과

정확성  테스트  
테스트 1 〉	통과 (0.40ms, 53.2MB)  
테스트 2 〉	통과 (0.43ms, 52.3MB)  
테스트 3 〉	통과 (0.49ms, 52.2MB)  
테스트 4 〉	통과 (0.45ms, 52.9MB)  
테스트 5 〉	통과 (0.42ms, 52.5MB)  
테스트 6 〉	통과 (4.24ms, 53MB)  
테스트 7 〉	통과 (2.45ms, 53.3MB)  
테스트 8 〉	통과 (1.05ms, 52.6MB)  
테스트 9 〉	통과 (0.92ms, 51.9MB)  
테스트 10 〉	통과 (2.17ms, 53MB)  
테스트 11 〉	통과 (3.44ms, 53.5MB)  
테스트 12 〉	통과 (4.09ms, 52.4MB)  
테스트 13 〉	통과 (5.23ms, 52.4MB)  
테스트 14 〉	통과 (0.73ms, 53.2MB)  
테스트 15 〉	통과 (8.18ms, 54.3MB)  
테스트 16 〉	통과 (0.42ms, 53.7MB)  

효율성  테스트  
테스트 1 〉	통과 (146.51ms, 65.8MB)  
테스트 2 〉	통과 (290.80ms, 88.6MB)  
테스트 3 〉	통과 (1434.14ms, 123MB)  
테스트 4 〉	통과 (110.83ms, 64MB)  
테스트 5 〉	통과 (1385.55ms, 127MB)  

채점 결과  
정확성: 76.2  
효율성: 23.8  
합계: 100.0 / 100.0  