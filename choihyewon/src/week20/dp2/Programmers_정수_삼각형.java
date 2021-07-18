package week20.dp2;

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
