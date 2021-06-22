package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_2156_포도주시식 {
	/*
	 * 
	 *
	 * - 과정 
	 * dp[i][d]에서 i은 포도주 잔의 번호이고, d이 0이면 직전 포도주잔을 안먹은거고, 1이면 직전 포도주잔을 먹은 것입니다.
	 * 
	 * 먼저 i=1, i=2일때부터 dp배열에 값을 넣어주었고 
	 * (i=1이면 한잔 마시는 거니까 arr[1], 
	 * i=2이면 직전 잔 안마셨으면 arr[2], 직전 잔 마셨다면 dp[1][0] + arr[2])
	 * 
	 * 그리고 i=3부터 N까지 돌면서
	 *  d==0이면 
	 *  i-1번째 포도주 잔을 안먹은것이므로, 현재 포도주잔을 먹을 수 있습니다 
	 *  따라서 dp[i-2]값 중 큰 값에 현재 포도주잔의 양(arr[i]을 더합니다.
	 * 
	 * d==1이면 
	 * i-1번째 포도주 잔을 먹은것이므로, i-2번째 포도주잔을 먹었는지 안먹었는지 확인해야합니다 
	 * i-2번째 포도주를 먹었다면 (dp[i-1][1]) 
	 * 현재 포도주를 먹을 수 없고 i-2번째 포도주를 먹지 않았다면 (dp[i-1][0]) 현재 포도주를 먹습니다.
	 * 
	 * 이러한 방식으로 답을 도출했고 결과값은 max(dp[N][0], dp[N][1])이 됩니다.
	 * 
	 * - 어려웠던 점 
	 * dp문제를 거의 안 풀어봐서 너무 낯설게 느껴져서 BOJ 단계별로 풀어보기 - 동적계획법 1을 
	 * 일부 풀고 해당 문제에 접근했습니다.
	 * 
	 * 
	 * - 결과 메모리 13312 시간 108 
	 * 
	 * 
	 * 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시
	 * 놓아야 한다. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다. 될 수 있는 대로 많은 양의 포도주를 맛보고 싶다. 1~n 번호의 포도 주
	 * 잔
	 */

	static int N, arr[], dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 포도주 잔의 개수

		arr = new int[N + 1]; // 각 잔의 포도주 양
		dp = new int[N + 1][2]; // dp[i][d] : i은 포도주 잔의 번호, 직전 포도주 먹었는지

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// N=1
		dp[1][0] = arr[1]; // 한 잔 마심

		if (N > 1) { // 체크 안하면 array 인덱스 에러
			// N=2
			dp[2][0] = arr[2]; // 직전에 안먹었으니까 2번째 잔만 마심
			dp[2][1] = dp[1][0] + arr[2]; // 첫번째잔+두번째잔 다 마심
		}

		for (int i = 3; i <= N; i++) {
			dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + arr[i];
			// 직전 포도주를 안먹었다.
			// 그 직전 dp[i-2]에 이번 포도주 먹는다
			dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + arr[i]);
			// 직전 포도주를 먹었다면 =>
			// 그 전에도 포도주를 먹었다 ? 이번 포도주 먹으면 안됨 => dp[i-1][1]
			// 그 전에는 안먹었다? 이번 포도주까지는 먹어도됨 => dp[i-1][0] + arr[i];
		}

		int ans = Math.max(dp[N][0], dp[N][1]);
		System.out.println(ans);

	}
}
