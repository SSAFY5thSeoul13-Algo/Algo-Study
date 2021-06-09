package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_전깃줄_2565 {

	/* 전깃줄 수, 연결할 수 있는 최대 전깃줄 */
	static int N, max = -1;
	/* dp[i] : i전깃줄까지 고려했을때 최대 연결가능한 전깃줄 */
	static int[] dp;
	/* 전깃줄 리스트 */
	static List<Line> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			list.add(new Line(s,e));
		}
		
		/* 전기줄 시작지점을 기준으로 정렬 */
		Collections.sort(list, (e1,e2) -> (e1.s-e2.s));
		
		for(int i=0; i<N; i++) {
			dp[i] = 1;					// 현재 연결가능한거 1개
			int cur = list.get(i).e;	// 현재 전깃줄 시작번호
			
			/* 0부터 i-1까지만 수행 */
			for(int j=0; j<i; j++) {
				if(cur > list.get(j).e) {	// 즉, 겹치지않고 연결가능하면
					dp[i] = Math.max(dp[i], dp[j]+1);	// 현재 최댓값과 비교하여 큰거 넣기
				}
			}
			
			max = Math.max(max, dp[i]);		// 최대로 연결가능한 전깃줄 수 갱신
		}
		
		/* 정답 : 제거해야할 전깃줄 = 전체 전깃줄 - 최대 연결가능한 전깃줄 */
		System.out.println(N-max);
	}
	static class Line{
		int s, e;

		public Line(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}
	}
}
