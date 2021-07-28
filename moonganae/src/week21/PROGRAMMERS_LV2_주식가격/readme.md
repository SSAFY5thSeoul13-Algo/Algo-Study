## Progrmmers LV2 주식가격
- 스택/큐
- Level 2
- https://programmers.co.kr/learn/courses/30/lessons/42584
<br>

### 문제설명

> 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.


### 제한사항
- prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
- prices의 길이는 2 이상 100,000 이하입니다.

### 입출력 예
|prices|	return|
|----|----|
|[1, 2, 3, 2, 3]|	[4, 3, 1, 1, 0]|


#### 입출력 예 설명
- 1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
- 2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
- 3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
- 4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
- 5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.

### 풀이 및 과정
떨어지지 않은 주식을 관리하는 자료구조로 큐를 사용하였습니다.

새로운 주식가격이 들어올때 마다, 큐를 순회하며 가격이 떨어졌는지 확인합니다.
- 만약 떨어졌다면 기간을 현재 인덱스 - 떨어진 가격의 인덱스를 통해 기록합니다.
- 만약 떨어지지 않았다면 다시 큐에 담습니다.

### 소스코드
```java
import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 떨어지지 않은 주식을 담을 큐
        Queue<Price> q = new LinkedList<>();
        
        // 주식가격 순회
        for(int i=0; i<prices.length; i++){
            
            // 현재 떨어지지 않은 주식가격들 순회
            int size = q.size();
            for(int j=0; j<size; j++){
                Price cur = q.poll();
                
                // 가격이 떨어짐.  
                if(prices[i] < cur.price){
                    // 떨어진 기간 기록
                    answer[cur.idx] = i - cur.idx;
                }
                // 안떨어짐.
                else{
                    // 다시 큐에 담기
                    q.offer(cur);
                }
            }
            // 방금 들어온 주식가격 큐에 넣기
            q.offer(new Price(i, prices[i]));
        }
        
        // 아직까지 떨어지지 않은 주식들을 순회하며, 마지막 시간(prices.length-1)과의 차이를 구함.
        while(!q.isEmpty()){
                Price cur = q.poll();
                answer[cur.idx] = prices.length-1 - cur.idx;
            }
        return answer;
    }
    class Price {
        int idx, price;
        public Price(int idx, int price){
            this.idx = idx;
            this.price = price;
        }
    }
}
```

### 결과
```
<정확성 테스트>
테스트 1 〉	통과 (0.35ms, 52MB)
테스트 2 〉	통과 (0.56ms, 52.8MB)
테스트 3 〉	통과 (2.26ms, 52.3MB)
테스트 4 〉	통과 (2.26ms, 53.3MB)
테스트 5 〉	통과 (8.11ms, 52.9MB)
테스트 6 〉	통과 (0.51ms, 51.6MB)
테스트 7 〉	통과 (2.58ms, 53.4MB)
테스트 8 〉	통과 (2.89ms, 53.2MB)
테스트 9 〉	통과 (0.56ms, 52.3MB)
테스트 10 〉	통과 (3.58ms, 54.6MB)

<효율성 테스트>
테스트 1 〉	통과 (61.10ms, 76.5MB)
테스트 2 〉	통과 (57.34ms, 76.7MB)
테스트 3 〉	통과 (71.42ms, 78.3MB)
테스트 4 〉	통과 (74.39ms, 74MB)
테스트 5 〉	통과 (59.18ms, 71.8MB)
```


