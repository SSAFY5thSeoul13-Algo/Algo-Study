## Programmers 야근 지수

- level3
- PQ

<br><br>


### 🔍 문제 설명

https://programmers.co.kr/learn/courses/30/lessons/12927

회사원 Demi는 가끔은 야근을 하는데요, 야근을 하면 야근 피로도가 쌓입니다. 야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값입니다. Demi는 N시간 동안 야근 피로도를 최소화하도록 일할 겁니다.Demi가 1시간 동안 작업량 1만큼을 처리할 수 있다고 할 때, 퇴근까지 남은 N 시간과 각 일에 대한 작업량 works에 대해 야근 피로도를 최소화한 값을 리턴하는 함수 solution을 완성해주세요.

<br>

#### ✔ 제한사항

- `works`는 길이 1 이상, 20,000 이하인 배열입니다.

- `works`의 원소는 50000 이하인 자연수입니다.

- `n`은 1,000,000 이하인 자연수입니다.

  

#### ✔ 입출력 

| works     | n    | result |
| --------- | ---- | ------ |
| [4, 3, 3] | 4    | 12     |
| [2, 1, 2] | 1    | 6      |
| [1,1]     | 3    | 0      |


<br>


<br>

###  💡 풀이



야근지수는 각 남은 일의 작업량을 `제곱`해서 더하게 된다.

즉 남은 일의 양이 골고루 작아야하므로, 남은 일이 많은 것 부터 먼저 하면 야근지수를 최소화 할 수 있다.



<br>

가장 일이 많은 것 부터 꺼내기 위해 `PriorityQueue`를 사용했다.

```java
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) pq.add(work);
```



`reversOrder()`를 이용하여 큰 값부터  pq에서 뺀 뒤 `-1`하고 다시 집어넣었다.

이를 n번 반복한다.



```java
        for(int i =0 ; i< n; i++){
            if(pq.peek()==0) break;
            pq.add(pq.poll()-1);
        }
```



이 때, pq에서 꺼낸 값이 0일 경우를 예외처리 해야한다.



일을 다 하고나서 pq의 값들을 꺼내 야근지수를 계산하여 `answer`를 구한다.

```java
        while(!pq.isEmpty()) answer += Math.pow(pq.poll(),2);
        return answer;
```



<br><br>

###  💬 소스코드

```java
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        //남은 일의 작업량을 제곱하여 더한다. => 모든 일들의 숫자가 골고루 작아야됨
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        //n번동안 제일 큰 값들을 꺼내서 하나 줄이고 다시 넣고 반복하기
        
        for(int work : works) pq.add(work);
        for(int i =0 ; i< n; i++){
            if(pq.peek()==0) break;
            pq.add(pq.poll()-1);
        }
        while(!pq.isEmpty()) answer += Math.pow(pq.poll(),2);
        return answer;
    }
}
```

<br><br>


###  💯 채점 결과

```
테스트 1 〉통과 (0.77ms, 79.3MB)
테스트 2 〉통과 (0.64ms, 87.7MB)
테스트 3 〉통과 (0.38ms, 74.5MB)
테스트 4 〉통과 (0.54ms, 73.4MB)
테스트 5 〉통과 (0.47ms, 73.8MB)
테스트 6 〉통과 (0.48ms, 76.5MB)
테스트 7 〉통과 (0.63ms, 82.6MB)
테스트 8 〉통과 (1.99ms, 71.6MB)
테스트 9 〉통과 (2.37ms, 76.4MB)
테스트 10 〉통과 (0.45ms, 76.2MB)
테스트 11 〉통과 (0.49ms, 69.9MB)
테스트 12 〉통과 (0.52ms, 85MB)
테스트 13 〉통과 (0.40ms, 79.6MB)
테스트 1 〉통과 (145.73ms, 67.6MB)
테스트 2 〉통과 (121.29ms, 69.3MB)
```