package week21.dp1;

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
