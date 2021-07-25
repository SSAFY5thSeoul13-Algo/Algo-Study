package week21.PROGRAMMERS_LV3_등굣길;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] dp = new int[n+1][m+1];
        // 웅덩이 기록하기
        for(int[] puddle : puddles){
            dp[puddle[1]][puddle[0]] = -1;
        }
        
        // 집은 1
        dp[1][1] = 1;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                // 웅덩이면 넘어가기
                if(dp[i][j] == -1) continue;
                // 위쪽이 웅덩이가 아니라면 더하기
                if(dp[i-1][j] != -1)
                    dp[i][j] += dp[i-1][j] % 1000000007;
                // 왼쪽이 웅덩이가 아니라면 더하기
                if(dp[i][j-1] != -1)
                    dp[i][j] += dp[i][j-1] % 1000000007;
            }
        }
        // 최단경로 갯수 반환
        return dp[n][m]%1000000007;
        
    }
}
