package week21.PROGRAMMERS_LV4_도둑질;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        int[] dp = new int[money.length];
        
        // 첫번째 선택했을 경우
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        for(int i=2; i<money.length-1; i++){
            dp[i] = Math.max(dp[i-2] + money[i], dp[i-1]);
        }
        
        // 첫번째걸 선택했으면 마지막은 무조건 선택x
        answer = dp[money.length-2];
        
        
        // 첫번째를 선택하지 않았을 경우
        dp[0] = 0;
        dp[1] = money[1];
        
        for(int i=2; i<money.length; i++){
            dp[i] = Math.max(dp[i-2] + money[i], dp[i-1]);
        }
        
        // 첫번째 DP와 두번째 DP 비교 
        return Math.max(answer, dp[money.length-1]);
    }
}

