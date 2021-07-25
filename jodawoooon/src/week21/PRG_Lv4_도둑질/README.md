## Programmers Lv4 도둑질
- 동적계획법
- level4



<br><br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42897

도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.  

![](https://images.velog.io/images/jodawooooon/post/d6f746f0-d223-41d2-bede-d27a25fe4067/image.png)

각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.  

각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.  

<br>

#### ✔ 제한사항
- 이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
- money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
<br>
 
#### ✔ 입출력 예
|money|return|
|--|--|
|[1, 2, 3, 1]|4|



<br><br>

###  💡 풀이

집들이 원형으로 둘러쌓여 있다.  
인접한 두 집을 털면 경보가 울리므로 한 칸씩 건너서 털어야한다.  

먼저 첫 번째 집을 털고 두 번째 집을 안 털었을 경우를 `dp1`으로 두고,  
첫 번째 집을 안 털고 두 번째 집을 털었을 경우를 `dp2`로 뒀다.  


```java
        int dp1[] = new int[LEN];
        int dp2[] = new int[LEN];
        
        dp1[0] = money[0];
        dp1[1] = dp1[0];
        //첫번째 집 털었다 => 두번째 집 못털음
        
        dp2[0] = 0;
        dp2[1] = money[1];
        //첫번째 집 안털었다 => 두번째 집 털었다
```

그리고 3번째 집부터 경우의 수를 따져본다.

현재 집의 번호를 `i`라고 둘 때, 연속된 집은 털 수 없으므로  
`i-2번째 집을 털고 현재 i번째 집을 터는 경우`, `i-1번째 집을 터는 경우` 2가지 중 더 큰 값을 dp배열에 저장하면 된다.  

이 때 첫 번째 집을 턴 경우, 집이 원 형태로 배치되어 있으므로 마지막 집과 인접해있다. 따라서 마지막 집은 털 수 없다.

```java
        for(int i = 2 ; i < LEN ; i++){ 
            
            dp2[i] = Math.max(dp2[i-1], dp2[i-2]+money[i]); 
            //i-1까지 털었을 때 돈, i-2까지 털고 지금 i번째 터는 돈 중 더 큰 값
 
            if(i==LEN-1) continue; //dp1 : 첫번째 집 털었다 => 마지막 집 털면 안되니까 LEN-2까지
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+money[i]);
            //i-1까지 털었을 때 돈, i-2까지 털고 지금 i번째 터는 돈 중 더 큰 값
            
            
        }
       
```

위 과정을 마친 뒤 `dp1`과 `dp2` 중 max값을 구하면 된다.

```java
        answer = Math.max(dp1[LEN-2], dp2[LEN-1]);
```
<br><br>

###  💡 소스코드



```java
import java.util.*;
class Solution {
    public int solution(int[] money) {
        int answer = 0;
        //money[] : 각 집에 있는 돈
        
        int LEN = money.length; //집의 개수
        
        //인접한 두집 털면 경보울림
        //한칸씩 건너서 훔쳐보자
        
        int dp1[] = new int[LEN];
        int dp2[] = new int[LEN];

        dp1[0] = money[0];
        dp1[1] = dp1[0];
        //첫번째 집 털었다 => 두번째 집 못털음
        
        dp2[0] = 0;
        dp2[1] = money[1];
        //첫번째 집 안털었다 => 두번째 집 털었다
        
        
        for(int i = 2 ; i < LEN ; i++){ 
            
            dp2[i] = Math.max(dp2[i-1], dp2[i-2]+money[i]); 
            //i-1까지 털었을 때 돈, i-2까지 털고 지금 i번째 터는 돈 중 더 큰 값
 
            if(i==LEN-1) continue; //dp1 : 첫번째 집 털었다 => 마지막 집 털면 안되니까 LEN-2까지
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+money[i]);
            //i-1까지 털었을 때 돈, i-2까지 털고 지금 i번째 터는 돈 중 더 큰 값
            
            
        }
        
        answer = Math.max(dp1[LEN-2], dp2[LEN-1]);
        return answer;
    }
}
```

<br><br>

### 🚩 결과
정확성  테스트
테스트 1 〉	통과 (0.09ms, 52.7MB)
테스트 2 〉	통과 (0.19ms, 52.8MB)
테스트 3 〉	통과 (0.08ms, 52.2MB)
테스트 4 〉	통과 (0.04ms, 52.4MB)
테스트 5 〉	통과 (0.08ms, 52.5MB)
테스트 6 〉	통과 (0.12ms, 53.4MB)
테스트 7 〉	통과 (0.10ms, 53.3MB)
테스트 8 〉	통과 (0.11ms, 53MB)
테스트 9 〉	통과 (0.21ms, 52.2MB)
테스트 10 〉	통과 (0.06ms, 52.5MB)
효율성  테스트
테스트 1 〉	통과 (20.54ms, 103MB)
테스트 2 〉	통과 (19.97ms, 102MB)
테스트 3 〉	통과 (20.29ms, 102MB)
테스트 4 〉	통과 (20.50ms, 102MB)
테스트 5 〉	통과 (30.55ms, 100MB)
테스트 6 〉	통과 (19.12ms, 102MB)
테스트 7 〉	통과 (17.67ms, 76.7MB)
테스트 8 〉	통과 (17.72ms, 89.9MB)
테스트 9 〉	통과 (18.31ms, 94.4MB)
테스트 10 〉	통과 (19.86ms, 102MB)