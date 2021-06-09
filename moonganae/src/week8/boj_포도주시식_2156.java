package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_포도주시식_2156{

	/* 포도주 잔의 수 */
	static int N;
	/* 각 잔에 담긴 포도주 양 */
	static int[] jan;
	/* dp[i] : i번째 잔의 고려하여 선택의 시간을 가졌을때의 최댓값 */
	static int[]dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		jan = new int[N];
		dp = new int[N];
		
		for(int i=0; i<N; i++) {
			jan[i] = Integer.parseInt(br.readLine());
		}
		
		/* 첫잔은 원샷 */
		dp[0] = jan[0];
		
		/* 2잔부터는 일단 다 마셔보자 */
		if(N > 1) {
			dp[1] = jan[0] + jan[1];  
		}
		
		/* 3잔부터는 고려해야지 */
		if(N>2) {
			/* 3반쩨 잔을 선택한다고 고려했을때의 최댓값 */
			dp[2] = Math.max(jan[0], jan[1]) + jan[2];
			/* 3번째 잔을 선택하지 않았을때의 최댓값 */
			dp[2] = Math.max(dp[1], dp[2]);
		}
		
		for(int i=3; i<N; i++) {
			
			/* i번째 잔을 선택했을때의 최댓값 
			 * 		dp[i-3] + jan[i-1] : i-2번째 잔을 마시지 않았을 경우 i-1번째 잔을 마실 수 있다.
			 * 		dp[i-2] : i-2번째 잔을 마셨을 경우 i-1번째 잔은 마시지 못한다. 
			 * */
			dp[i] = Math.max(dp[i-3] + jan[i-1], dp[i-2]) + jan[i];
			
			/* i번째 잔을 선택하지 않았을 때의 최댓값과 비교하여 선택한다. */
			dp[i] = Math.max(dp[i-1], dp[i]);
		}
		
		System.out.println(dp[N-1]);
	}

}
