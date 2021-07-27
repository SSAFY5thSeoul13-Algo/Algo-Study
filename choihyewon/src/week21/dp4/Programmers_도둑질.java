package week21.dp4;

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
