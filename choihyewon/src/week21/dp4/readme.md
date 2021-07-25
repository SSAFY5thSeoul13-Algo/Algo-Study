## Programmers - 도둑질 
- dp
- Level4

🔗[도둑질 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42897)

## 풀이

먼저 첫번째 집을 터는 경우와 첫번째 집을 털지않는 경우 훔칠 수 있는 최대의 돈의 값을 담은 2차원 배열을 생성합니다.

~~~java
        int[][] dp = new int[money.length][2];
~~~

각각 배열의 0번째,1번째에 맞는 값을 넣어주고 index가 2일때 부터 최대값을 구합니다.

각각 i-1번째 집에서까지 훔친 돈의 최대값과 i-2번째 집에서까지 훔친돈의 최댓값에 i번째 집에서 훔칠 수 있는 돈을 합한 값과 비교하여 더 큰 수를 i번째 배열에 각각 저장해줍니다.

~~~java
        for(int i=2; i<money.length; i++) {
        	dp[i][0] = Math.max(dp[i-1][0], dp[i-2][0]+money[i]);
        	dp[i][1] = Math.max(dp[i-1][1], dp[i-2][1]+money[i]);
        }
~~~

첫번째 집과 마지막에 위치한 집은 서로 인접하므로, 첫번째 집을 터는 경우 마지막을 집을 털 수 없습니다. 따라서 첫번째 집을 턴 경우 마지막 집에 다다랐을 경우 직전 index의 최대값을 넣어줍니다.

~~~java
        dp[money.length-1][0] = dp[money.length-2][0];
~~~

첫번째 집을 턴 경우와 첫번째 집을 털지 않은 경우 money.length-1에 저장된 각각의 값중 더 큰 수를answer에 저장하여 답을 구합니다.



## 소스코드
~~~java
public class Programmers_도둑질 {
	public int solution(int[] money) {
        int answer = 0;
        // 첫번째 집을 터는 경우 dp[n][0], 첫번째 집을 털지 않는 경우 dp[n][1]
        int[][] dp = new int[money.length][2];
        
        dp[0][0] = money[0];
        dp[1][0] = dp[0][0];
        
        dp[1][1] = money[1];
        
        for(int i=2; i<money.length; i++) {
        	dp[i][0] = Math.max(dp[i-1][0], dp[i-2][0]+money[i]);
        	dp[i][1] = Math.max(dp[i-1][1], dp[i-2][1]+money[i]);
        }
        // 첫번째 집을 털게되면 마지막 집은 털 수 없다.
        dp[money.length-1][0] = dp[money.length-2][0];
        
        answer = Math.max(dp[money.length-1][0], dp[money.length-1][1]);
        
        return answer;
    }

}
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.11ms, 52MB)|
|테스트 2 |	통과 (0.31ms, 52.6MB)|
|테스트 3 |	통과 (0.20ms, 52.7MB)|
|테스트 4 |	통과 (0.05ms, 52.4MB)|
|테스트 5 |	통과 (0.10ms, 54.3MB)|
|테스트 6 |	통과 (0.17ms, 52.5MB)|
|테스트 7 |	통과 (0.14ms, 52.8MB)|
|테스트 8 |	통과 (0.11ms, 52.6MB)|
|테스트 9 |	통과 (0.24ms, 52.1MB)|
|테스트 10 |	통과 (0.09ms, 52.9MB)|

-------

|효율성 | 테스트 |
|---|---|
|테스트 1 |	통과 (97.61ms, 115MB)|
|테스트 2 |	통과 (92.14ms, 115MB)|
|테스트 3 |	통과 (93.86ms, 116MB)|
|테스트 4 |	통과 (96.66ms, 116MB)|
|테스트 5 |	통과 (84.69ms, 113MB)|
|테스트 6 |	통과 (94.17ms, 115MB)|
|테스트 7 |	통과 (79.72ms, 88.5MB)|
|테스트 8 |	통과 (84.66ms, 92.8MB)|
|테스트 9 |	통과 (45.62ms, 92.2MB)|
|테스트 10 |	통과 (88.76ms, 116MB)|