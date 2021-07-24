package week21.PRG_Lv3_등굣길;

public class Solution_PRG_Lv3_등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int dp[][] = new int[n+1][m+1];

        for(int[] puddle : puddles){
            dp[puddle[1]][puddle[0]] = -1;
        }
        //puddle 매핑
        
        dp[1][1] = 1;
        
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(dp[i][j]==-1) continue; //puddle이면 pass
                
                if(dp[i-1][j]!=-1) dp[i][j] += dp[i-1][j] % 1000000007; //위에가 puddle이 아니면
                if(dp[i][j-1]!=-1) dp[i][j] += dp[i][j-1] % 1000000007; //왼쪽이 puddle이 아니면
            }
        }
        
        return (dp[n][m] % 1000000007);
    }
}
