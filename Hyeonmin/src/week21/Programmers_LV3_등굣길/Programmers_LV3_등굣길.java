package week21.Programmers_LV3_등굣길;


public class Programmers_LV3_등굣길 {
	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		
		int[][] puddles = {{2,2}};
		
		System.out.println(solution(m,n,puddles));

	}
	
	static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[101][101];
        
        //웅덩이가 있는 곳을 -1로 표시
        for(int[] arr : puddles){
            dp[arr[0]][arr[1]] = -1;
        }
        
        //집이있는 위치
        dp[1][1] = 1;
        
        for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				//집이 있는 위치는 다음위치로 바로 이동
				if(i==1 && j==1)
					continue;
				
				//웅덩이가 있는 경우는 그 이후 경로에 영향을 주면 안되므로 0으로 변경
				if(dp[i][j] == -1) {
					dp[i][j] = 0;
				}
				//현재 위치까지 오는 최단 경로의 수는 현재 위치의 왼쪽과 위쪽까지 오는 최단 경로의 수의 합이다
				else {
					dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
				}
			}
		}
        
        int answer = dp[n][m] % 1000000007;
        return answer;
	}

}
