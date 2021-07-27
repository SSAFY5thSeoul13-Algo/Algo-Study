## Programmers Lv4 도둑질
- DP
- level4

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42897

각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.

각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.


#### 제한사항
이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
<br><br>

###  💡 풀이

변수
`int[][] dp` : 0번집을 터는 경우와 안터는 경우의 훔칠 수 있는 돈의 합을 저장할 dp배열
`int length` : 집의 개수


<br>

집의 개수만큼의 길이늘 갖는 2차원배열 `dp`를 만든다
`dp`는 0번집을 터는 경우와 안터는 경우 2가지 경우의 값들을 각각 `dp[][0]`, `dp[][1]`에 저장한다

```java
		//[][0]: 0번 집을 터는 경우, [][1]: 0번 집을 안터는 경우 
		int[][] dp = new int[length][2];
```

0번집을 터는 경우와 안터는 경우의 초기 값을 세팅한다

```java
		//0번집을 터는 경우의 초기 값
		dp[0][0] = money[0];
		dp[1][0] = money[0];
		dp[2][0] = money[0] + money[2]; 
		
		//0번 집을 안터는 경우의 초기 값
		dp[1][1] = money[1];
		dp[2][1] = Math.max(money[1], money[2]);
```

초기값을 세팅한 이후의 집들에서 `i`번쨰 집을 터는 경우와 안터는 경우 중 더 값이 큰 경우를 `dp[i][0]`에 저장한다 

```java
		//i번째 집을 터는 경우와 안터는 경우 중 훔치는 돈이 더 큰 경우를 dp[i][]에 저장
		for (int i = 3; i < length; i++) {
			//0번집을 안터는 경우
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-2][0], dp[i-3][0])+money[i]);
			//0번집을 터는 경우
			dp[i][1] = Math.max(dp[i-1][1], Math.max(dp[i-2][1], dp[i-3][1])+money[i]);
		}
```

0번째 집을 터는 경우 마지막집은 무조건 털지 못하기 때문에 `length-1`집을 터는 경우는 고려할 필요가 없기 때문에 `dp[length-1][0]`의 값을 `length-2`번째 집까지 고려한 경우의 값으로 변경한다

```java
		//0번집을 터는 경우는 마지막집은 무조건 털지 못함
		dp[length-1][0] = dp[length-2][0];
```

0번째 집을 턴 경우와 안 턴 경우 둘 중 더 큰 값을 저장하고 리턴한다

```java
		int result = Math.max(dp[length-1][0], dp[length-1][1]);
		
		return result;
```


<br><br>

###  💡 소스코드
```java
public class Programmers_LV4_도둑질 {

	public static void main(String[] args) {
		int[] money = {1, 2, 3, 1, 4};
		System.out.println(solution(money));
	}
	
	static int solution(int[] money) {
		int length = money.length;
		//[][0]: 0번 집을 터는 경우, [][1]: 0번 집을 안터는 경우 
		int[][] dp = new int[length][2];
		
		//0번집을 터는 경우의 초기 값
		dp[0][0] = money[0];
		dp[1][0] = money[0];
		dp[2][0] = money[0] + money[2]; 
		
		//0번 집을 안터는 경우의 초기 값
		dp[1][1] = money[1];
		dp[2][1] = Math.max(money[1], money[2]);
		
		//i번째 집을 터는 경우와 안터는 경우 중 훔치는 돈이 더 큰 경우를 dp[i][]에 저장
		for (int i = 3; i < length; i++) {
			//0번집을 안터는 경우
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-2][0], dp[i-3][0])+money[i]);
			//0번집을 터는 경우
			dp[i][1] = Math.max(dp[i-1][1], Math.max(dp[i-2][1], dp[i-3][1])+money[i]);
		}
		
		//0번집을 터는 경우는 마지막집은 무조건 털지 못함
		dp[length-1][0] = dp[length-2][0];
		
		int result = Math.max(dp[length-1][0], dp[length-1][1]);
		
		return result;
	}

}



```


<br>


정확성  테스트
테스트 1 〉	통과 (0.14ms, 52.3MB)
테스트 2 〉	통과 (0.33ms, 52.5MB)
테스트 3 〉	통과 (0.14ms, 53.3MB)
테스트 4 〉	통과 (0.05ms, 51.9MB)
테스트 5 〉	통과 (0.11ms, 52.8MB)
테스트 6 〉	통과 (0.23ms, 52.1MB)
테스트 7 〉	통과 (0.25ms, 52.3MB)
테스트 8 〉	통과 (0.14ms, 53.1MB)
테스트 9 〉	통과 (0.32ms, 52.1MB)
테스트 10 〉	통과 (0.11ms, 52.9MB)
효율성  테스트
테스트 1 〉	통과 (102.17ms, 116MB)
테스트 2 〉	통과 (99.94ms, 115MB)
테스트 3 〉	통과 (103.44ms, 116MB)
테스트 4 〉	통과 (100.23ms, 116MB)
테스트 5 〉	통과 (94.75ms, 114MB)
테스트 6 〉	통과 (100.94ms, 115MB)
테스트 7 〉	통과 (84.65ms, 90.9MB)
테스트 8 〉	통과 (94.08ms, 92MB)
테스트 9 〉	통과 (51.45ms, 92.1MB)
테스트 10 〉	통과 (99.44ms, 116MB)