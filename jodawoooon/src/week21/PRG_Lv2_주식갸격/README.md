## Programmers Lv2 주식가격
- 스택/큐..인데 안씀
- level2



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42584

초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.


<br>

#### ✔ 제한사항
- prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
- prices의 길이는 2 이상 100,000 이하입니다.
<br>
 
#### ✔ 입출력 예
![](https://images.velog.io/images/jodawooooon/post/ce23d581-1544-407f-88fb-eda97087f5e7/image.png)

<br>

###  💡 풀이

단순하게 for문으로 풀었다..  

현재가격 기준으로 뒤에를 탐색하며 가격이 언제 떨어지는지 확인했다.  

```java
        for(int i = 0 ; i < len ; i++){
            int cur = prices[i]; //현재 가격
            int time = 0; //가격이 떨어지지 않은 기간
            
            for (int j = i+1 ; j <len ; j++){
            	time++;
                if(cur>prices[j]) break;
            }
            answer[i] = time;
        }
```

<br><br>

###  💡 소스코드



```java
package week21.PRG_Lv2_주식갸격;

public class Programmers_PRG_Lv2_주식가격 {
	public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        
        for(int i = 0 ; i < len ; i++){
            int cur = prices[i]; //현재 가격
            int time = 0; //가격이 떨어지지 않은 기간
            
            for (int j = i+1 ; j <len ; j++){
            	time++;
                if(cur>prices[j]) break;
            }
            answer[i] = time;
        }
        return answer;
    }
}


```

<br>

### 🚩 결과
정확성  테스트
테스트 1 〉	통과 (0.02ms, 53.1MB)
테스트 2 〉	통과 (0.03ms, 51.8MB)
테스트 3 〉	통과 (0.18ms, 53MB)
테스트 4 〉	통과 (0.18ms, 52.8MB)
테스트 5 〉	통과 (0.24ms, 53.1MB)
테스트 6 〉	통과 (0.01ms, 53.1MB)
테스트 7 〉	통과 (0.11ms, 52.4MB)
테스트 8 〉	통과 (0.13ms, 51.6MB)
테스트 9 〉	통과 (0.02ms, 52.4MB)
테스트 10 〉	통과 (0.24ms, 53.5MB)
효율성  테스트
테스트 1 〉	통과 (16.14ms, 73.9MB)
테스트 2 〉	통과 (11.13ms, 66.1MB)
테스트 3 〉	통과 (18.23ms, 78.1MB)
테스트 4 〉	통과 (13.28ms, 70.4MB)
테스트 5 〉	통과 (10.76ms, 62.3MB)