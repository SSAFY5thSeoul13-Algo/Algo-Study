## Programmers - 정수 삼각형 
- dp
- Level3

🔗[정수 삼각형 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/43105)

## 풀이

triangle 배열 위에서부터 아래까지 최대값을 각각 누적해서 dp 배열에 각각 누적해준다면 아래와 같다.

~~~
7  0  0 0 0 
10 15 0 0 0 
18 0 15 0 0 
20 0 0 19 0 
24 0 0 0 24 
~~~
아래칸으로 이동할때는 대각선 방향으로 한칸 오른쪽, 또는 왼쪽으로만 가능하기 때문에 제일 왼쪽에 있는 숫자와 오른쪽에 있는 숫자는 dp 배열에 누적될 값의 경우의 수가 한가지이다. 
그리고 그 외의 값들은 대각선 방향으로 오른쪽, 왼쪽 값중 서로 더 큰값을 누적해주면 값을 구할 수 있다.


## 소스코드
~~~java
public class Programmers_정수_삼각형 {
	public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][triangle.length];
        // triangle 배열에서 맨 위층의 값을 dp[0][0]에 넣어준다.
        dp[0][0] = triangle[0][0];
        for(int i=1; i<triangle.length; i++) {
        	for(int j=0; j<triangle[i].length; j++) {
        		// 가장 왼쪽 
        		if(j==0) {
        			dp[i][j] = triangle[i][j] + dp[i-1][0];
        		}
        		// 가장 오른쪽 
        		else if(j==triangle[i].length-1) {
        			dp[i][j] = triangle[i][j] + dp[i-1][j-1];
        		}
        		else {
        			dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
        		}
        		
        	}
        }
        // 맨 마지막 배열중 max 값 찾기 
        for(int i=0; i<dp[dp.length-1].length; i++) {
        	answer = Math.max(dp[dp.length-1][i], answer);
        }
        return answer;
	} 
}
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.05ms, 52MB)|
|테스트 2 |	통과 (0.04ms, 51.9MB)|
|테스트 3 |	통과 (0.07ms, 52.6MB)|
|테스트 4 |	통과 (0.10ms, 52.5MB)|
|테스트 5 |	통과 (0.42ms, 52.9MB)|
|테스트 6 |	통과 (0.16ms, 52.5MB)|
|테스트 7 |	통과 (0.42ms, 53.5MB)|
|테스트 8 |	통과 (0.14ms, 52.2MB)|
|테스트 9 |	통과 (0.03ms, 52.9MB)|
|테스트 10 |	통과 (0.11ms, 52MB)|

-------

| 효율성 |  테스트 |
|테스트 1 |	통과 (9.58ms, 60.6MB)|
|테스트 2 |	통과 (8.66ms, 59.1MB)|
|테스트 3 |	통과 (11.42ms, 60.5MB)|
|테스트 4 |	통과 (8.83ms, 62.1MB)|
|테스트 5 |	통과 (9.87ms, 60.5MB)|
|테스트 6 |	통과 (11.96ms, 61.5MB)|
|테스트 7 |	통과 (9.61ms, 60.8MB)|
|테스트 8 |	통과 (9.27ms, 61.1MB)|
|테스트 9 |	통과 (9.10ms, 60.7MB)|
|테스트 10 |	통과 (8.55ms, 58.1MB)|