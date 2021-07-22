## Programmers - 등굣길  
- DP
- Level3

🔗[등굣길 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42898)

## 풀이

n행 m열의 2차원 배열을 만들어주고, 시작점에는 1, 웅덩이에는 -1으로 표시해주었습니다.
n,m 위치까지 최단경로로 이동하기 위해서는 우측,아래측으로만 이동하면 되기때문에 이전에 갈 수 있는 좌표 i-1행 j열과 i행 j-1열의 값을 더해주어 구하였습니다. (1행인 경우 제외)  

## 소스코드
~~~java
public class Programmers_등굣길 {
	public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n+1][m+1];
        // 웅덩이는 -1로 표시 
        for(int i=0; i<puddles.length; i++) {
        	int r = puddles[i][1];
        	int c = puddles[i][0];
        	dp[r][c] = -1;
        }
        // 시작점 
        dp[1][1] = 1;
        
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=m; j++) {
        		// 웅덩이일 경우 0으로 바꾸어주고 continue;
        		if(dp[i][j] == -1) {
        			dp[i][j] = 0;
        			continue;
        		}
        		
        		if(i==1) {
        			dp[i][j] += dp[i][j-1];
        		}else {
        			dp[i][j] = dp[i-1][j] + dp[i][j-1];
        		}
        		
        		
        	}
        }
        
        answer = dp[n][m] % 1000000007;
        
        return answer;
    }


}
~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.02ms, 52.4MB)|
|테스트 2 |	통과 (0.03ms, 54.5MB)|
|테스트 3 |	통과 (0.03ms, 53.5MB)|
|테스트 4 |	통과 (0.03ms, 52.4MB)|
|테스트 5 |	통과 (0.04ms, 52.4MB)|
|테스트 6 |	통과 (0.04ms, 52.8MB)|
|테스트 7 |	통과 (0.03ms, 53.1MB)|
|테스트 8 |	통과 (0.06ms, 52.4MB)|
|테스트 9 |	통과 (0.03ms, 52.3MB)|
|테스트 10 |	통과 (0.03ms, 52.4MB)|


-----


| 효율성 | 테스트 |
|----|----|
|테스트 1 |	통과 (0.77ms, 52.1MB)|
|테스트 2 |	통과 (0.27ms, 52.5MB)|
|테스트 3 |	통과 (0.22ms, 52.5MB)|
|테스트 4 |	통과 (0.54ms, 52.6MB)|
|테스트 5 |	통과 (0.40ms, 51.6MB)|
|테스트 6 |	통과 (0.64ms, 53MB)|
|테스트 7 |	통과 (0.33ms, 51.7MB)|
|테스트 8 |	통과 (0.53ms, 52.6MB)|
|테스트 9 |	통과 (0.49ms, 52.4MB)|
|테스트 10 |	통과 (0.53ms, 53MB)|