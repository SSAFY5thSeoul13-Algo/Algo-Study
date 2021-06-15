package week15.BOJ_1051_S3_숫자정사각형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1051_S3_숫자정사각형 {
	
/*	
	꼭짓점에 쓰여 있는 수가 모두 같은 가장 큰 정사각형을 찾는 프로그램
	정사각형의 크기를 출력하라.
	*/
	
	static int N, M, map[][],ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		ans = 1;
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j)-'0';
				
			}
		}
	
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				for (int l = 1; l < Math.min(N, M); l++) {
					if(i+l<N&&j+l<M) { //range 체크
						if(map[i][j]==map[i][j+l]) {
							if(map[i][j+l]==map[i+l][j]) {
								if(map[i+l][j]==map[i+l][j+l]) {
									ans = Math.max(ans, (l+1)*(l+1));
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
