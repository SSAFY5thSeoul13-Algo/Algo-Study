package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_앱_7579 {
	/* 앱의 횟수, 확보해야할 최소메모리 */
	static int N, M;
	/* 앱별 메모리 */
	static int[] memory;
	/* 앱별 비활성비용 */
	static int[] cost;
	/* dp[i][j] : i앱까지 고려했을때 j코스트를 가지고 확보할 수 있는 메모리크기 */
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		memory = new int[N+1];
		cost = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 현재앱들로 만들수 있는 최대 코스트의 크기 */
		int costMax = 1;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int c = Integer.parseInt(st.nextToken());
			cost[i] = c;
			costMax += c;
			
		}
		dp = new int[N+1][costMax];

		/* ans : 최소비용 */
		int ans = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			int m = memory[i];
			int c = cost[i];
			
			for(int j=0; j<costMax; j++) {
				/* 현재 앱i의 코스트보다 작다면 : 앱을 비활성시킬수 없다면 */
				if(c > j) {
					/* 기존껄 그대로 */
					dp[i][j] = dp[i-1][j];
				}else {
					/* 아니라면 앱i의 코스트를 포함했을때의 메모리와 또는 기존꺼 중 큰용량을 확보한다. */
					dp[i][j] = Math.max(dp[i-1][j-c] + m, dp[i-1][j]);
				}
				/* 필요한 M 용량 기준을 충족했다면 cost를 비교한다. */
				if(dp[i][j] >=M) {
					ans = Math.min(ans, j);
				}
			}
		}
		/* 최소비용 출력 */
		System.out.println(ans);
	}
}
